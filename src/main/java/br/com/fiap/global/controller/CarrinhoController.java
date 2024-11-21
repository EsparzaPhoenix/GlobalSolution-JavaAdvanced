package br.com.fiap.global.controller;

import br.com.fiap.global.model.painel.PainelSolar;
import br.com.fiap.global.model.pedido.Pedidos;
import br.com.fiap.global.model.user.UserAccount;
import br.com.fiap.global.repository.PainelSolarRepository;
import br.com.fiap.global.repository.PedidosRepository;
import br.com.fiap.global.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CarrinhoController {

    private final PainelSolarRepository painelSolarRepository;
    private final UserRepository userRepository;
    private final PedidosRepository pedidosRepository;

    private final List<PainelSolar> carrinho = new ArrayList<>();

    public CarrinhoController(PainelSolarRepository painelSolarRepository, PedidosRepository pedidosRepository, UserRepository userRepository) {
        this.painelSolarRepository = painelSolarRepository;
        this.pedidosRepository = pedidosRepository;
        this.userRepository = userRepository;

    }

    @GetMapping("/carrinho")
    public String exibirCarrinho(Model model) {
        model.addAttribute("carrinho", carrinho);
        return "carrinho";
    }

    @PostMapping("/carrinho/adicionar")
    public String adicionarAoCarrinho(@RequestParam("id") Long painelId) {
        PainelSolar painel = painelSolarRepository.findById(painelId).orElse(null);
        if (painel != null) {
            carrinho.add(painel);
        }
        return "redirect:/carrinho";
    }

    @PostMapping("/carrinho/finalizar")
    public String finalizarPedido(Authentication authentication, Model model) {
        // Obter o nome do usuário autenticado
        String username = authentication.getName();

        // Buscar o UserAccount no banco de dados
        UserAccount user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("Usuário não encontrado");
        }

        // Criar um novo pedido com os itens do carrinho
        Pedidos pedido = new Pedidos();
        pedido.setPedido(System.currentTimeMillis());
        pedido.setQuantidade(carrinho.size());
        pedido.setValorTotal(carrinho.stream().mapToDouble(PainelSolar::getValor).sum());
        pedido.setDataPedido(new Date());

        // Relacionar o pedido ao usuário
        pedido.setUser(user);

        // Salvar o pedido no banco de dados
        pedidosRepository.save(pedido);

        // Limpar o carrinho após finalizar o pedido
        carrinho.clear();

        // Adicionar mensagem ao modelo para exibir no template
        model.addAttribute("mensagem", "Seu pedido foi finalizado com sucesso!");
        model.addAttribute("pedidoId", pedido.getPedido());
        model.addAttribute("valorTotal", pedido.getValorTotal());

        return "pedido-finalizado";
    }

}