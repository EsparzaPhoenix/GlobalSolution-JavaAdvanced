package br.com.fiap.global.controller;

import br.com.fiap.global.model.painel.PainelSolar;
import br.com.fiap.global.repository.PainelSolarRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarrinhoController {

    private final PainelSolarRepository painelSolarRepository;

    private final List<PainelSolar> carrinho = new ArrayList<>();

    public CarrinhoController(PainelSolarRepository painelSolarRepository) {
        this.painelSolarRepository = painelSolarRepository;
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
            painelSolarRepository.deleteById(painelId); // Remove da lista de compra
        }
        return "redirect:/carrinho";
    }
}
