package com.carrillo.logistica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/reportes")
    public String reportes() {
        // Simula un error 500 - funcionalidad no implementada
        throw new RuntimeException("Funcionalidad de reportes aún no implementada");
    }
}
