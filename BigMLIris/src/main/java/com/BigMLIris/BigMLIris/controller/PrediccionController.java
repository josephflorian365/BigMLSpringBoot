package com.BigMLIris.BigMLIris.controller;

import com.BigMLIris.BigMLIris.dto.PredListResDTO;
import com.BigMLIris.BigMLIris.dto.PredResDTO;
import com.BigMLIris.BigMLIris.model.PrediccionBody;
import com.BigMLIris.BigMLIris.service.PrediccionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class PrediccionController {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PrediccionService prediccionService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar() throws IOException, InterruptedException {
        String response = prediccionService.listar().body();
        PredListResDTO predListResDTO = objectMapper.readValue(response, PredListResDTO.class);
        return ResponseEntity.ok(predListResDTO);
    }

    @PostMapping("/predecir")
    public ResponseEntity<?> predecir(@RequestBody PrediccionBody prediccionBody) throws IOException, InterruptedException {
        String response = prediccionService.predecir(prediccionBody).body();
        PredResDTO predResDTO = objectMapper.readValue(response, PredResDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(predResDTO);
    }

    @GetMapping("/listar-prediccion")
    public ResponseEntity<?> buscarPrediccionId(@RequestParam String idPrediccion) throws IOException, InterruptedException {
        String response = prediccionService.buscarPrediccionId(idPrediccion).body();
        PredResDTO predListResDTO = objectMapper.readValue(response, PredResDTO.class);
        return ResponseEntity.ok(predListResDTO);
    }
}
