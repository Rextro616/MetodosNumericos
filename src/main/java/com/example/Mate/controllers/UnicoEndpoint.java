package com.example.Mate.controllers;

import com.example.Mate.models.Ecuacion;
import com.example.Mate.models.MetodosNumericos;
import com.example.Mate.services.Calculos;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/calcular")
public class UnicoEndpoint {
    @Autowired
    Calculos calculos;

    @PostMapping()
    public ResponseEntity<ArrayList<ArrayList<MetodosNumericos>>> create (@RequestBody Ecuacion ecuacion) {
        ArrayList<ArrayList<MetodosNumericos>> respuesta = calculos.respuesta(ecuacion);
        return ResponseEntity.ok().body(respuesta);
    }

}
