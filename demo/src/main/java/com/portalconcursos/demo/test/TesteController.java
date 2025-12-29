package com.portalconcursos.demo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @GetMapping("/ping")
    public String ping() {
        return "API funcionando!";
    }
}

