package br.com.fiap.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login";  // Retorna o template login.html
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";  // Redireciona para a página inicial após logout
    }
}