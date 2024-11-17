package br.com.fiap.global.controller;

import br.com.fiap.global.model.painel.Orcamento;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calculo-orcamento")
public class OrcamentoController {

    @GetMapping
    public String showCalculationForm(Model model) {
        model.addAttribute("calculo", new Orcamento());
        return "calculo-orcamento";
    }

    @PostMapping
    public String calculateBudget(@ModelAttribute("calculo") Orcamento calculo, Model model) {
        double resultado = calcularOrcamento(calculo.getAreaPainel(), calculo.getEficienciaPainel(), calculo.getCustoInstalacao());
        model.addAttribute("resultado", resultado);
        model.addAttribute("calculo", calculo);
        return "calculo-orcamento";
    }


    private double calcularOrcamento(double area, double eficiencia, double custo) {
        return area * eficiencia * custo; // Simples cálculo de orçamento para demonstrar funcionalidade
    }
}