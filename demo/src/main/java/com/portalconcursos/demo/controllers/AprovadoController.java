package com.portalconcursos.demo.controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.portalconcursos.demo.dto.AprovadoRequest;
import com.portalconcursos.demo.dto.AprovadoResponse;


@RestController
@RequestMapping("/api/aprovados")
@CrossOrigin
public class AprovadoController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<String> cadastrar(@RequestBody AprovadoRequest request) throws Exception {

        // Validação simples (nível júnior)
        if (request.getNome() == null || request.getNome().isBlank()) {
            return ResponseEntity.badRequest().body("Nome é obrigatório");
        }

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body("E-mail é obrigatório");
        }

        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> data = new HashMap<>();
        data.put("nome", request.getNome());
        data.put("email", request.getEmail());
        data.put("telefone", request.getTelefone());
        data.put("concursos", request.getConcursos());
        data.put("criadoEm", System.currentTimeMillis());

        db.collection("aprovados").add(data);

        return ResponseEntity.ok("Aprovado cadastrado com sucesso!");
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AprovadoResponse>> listar() throws Exception {

        Firestore db = FirestoreClient.getFirestore();

        List<AprovadoResponse> lista = new ArrayList<>();

        for (var doc : db.collection("aprovados").get().get().getDocuments()) {

            lista.add(
                    new AprovadoResponse(
                            doc.getId(),
                            doc.getString("nome"),
                            doc.getString("email"),
                            doc.getString("telefone"),
                            doc.getString("concursos")));
        }

        return ResponseEntity.ok(lista);
    }

}
