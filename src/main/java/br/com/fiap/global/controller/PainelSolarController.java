package br.com.fiap.global.controller;

import br.com.fiap.global.model.painel.PainelSolar;
import br.com.fiap.global.repository.PainelSolarRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/paineis")
public class PainelSolarController {

    private final PainelSolarRepository painelSolarRepository;

    public PainelSolarController(PainelSolarRepository painelSolarRepository) {
        this.painelSolarRepository = painelSolarRepository;
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
        painelSolarRepository.save(painel);
        return "redirect:/paineis";
    }
}
