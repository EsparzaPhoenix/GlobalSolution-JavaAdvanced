package br.com.fiap.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuPrincipalController {

    @GetMapping("/menu-principal")
    public String showMenuPrincipal() {
        return "menu-principal";
    }

}