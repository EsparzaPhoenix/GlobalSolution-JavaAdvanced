package br.com.fiap.global.controller;

import br.com.fiap.global.model.painel.PainelSolar;
import br.com.fiap.global.model.painel.Estoque;
import br.com.fiap.global.model.painel.enums.TipoOperacao;
import br.com.fiap.global.repository.PainelSolarRepository;
import br.com.fiap.global.repository.EstoqueRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/paineis")
public class PainelSolarController {

    private final PainelSolarRepository painelSolarRepository;
    private final EstoqueRepository estoqueRepository;

    public PainelSolarController(PainelSolarRepository painelSolarRepository, EstoqueRepository estoqueRepository) {
        this.painelSolarRepository = painelSolarRepository;
        this.estoqueRepository = estoqueRepository;
    }

    @GetMapping
    public String listarPaineis(Model model) {
        model.addAttribute("paineis", painelSolarRepository.findAll());
        return "paineis/listar";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/novo")
    public String mostrarFormularioCriacao(Model model) {
        model.addAttribute("painel", new PainelSolar());
        return "paineis/novo";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/novo")
    public String criarPainel(@ModelAttribute PainelSolar painel) {
        // Salva o painel no repositório
        painelSolarRepository.save(painel);

        // Atualiza o estoque relacionado
        Estoque estoque = new Estoque();
        estoque.setPainel(painel);
        estoque.setQuantidade(0); // Quantidade inicial pode ser ajustada conforme necessário
        estoque.setValor(painel.getValor()); // Exemplo: valor do painel
        estoque.setDataEntrada(new Date());
        estoque.setTipoOperacao(TipoOperacao.ENTRADA); // Enum para controle de tipo de operação

        // Salva o estoque no repositório
        estoqueRepository.save(estoque);

        return "redirect:/paineis";
    }
}
