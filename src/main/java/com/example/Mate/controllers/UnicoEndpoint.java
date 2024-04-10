package com.example.Mate.controllers;

import com.example.Mate.models.Ecuacion;
import com.example.Mate.services.Calculos;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/calcular")
public class UnicoEndpoint {
    @Autowired
    Calculos calculos;

    @PostMapping()
    public ResponseEntity<String> create (@RequestBody Ecuacion ecuacion) {
        calculos.respuesta(ecuacion);
        return ResponseEntity.ok().body("ok");
    }

}
