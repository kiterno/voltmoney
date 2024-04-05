package com.volt.money.controller;


import com.volt.money.model.ServiceOperator;
import com.volt.money.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operators")
public class ServiceOperatorController {
    @Autowired
    private OperatorService operatorService;

    @PostMapping("/add")
    public ResponseEntity<?> addOperator(@RequestParam String name) {
        try {
            ServiceOperator operator = operatorService.addOperator(name);
            return new ResponseEntity<>(operator, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceOperator>> getAllOperators() {
        List<ServiceOperator> operators = operatorService.getAllOperators();
        return new ResponseEntity<>(operators, HttpStatus.OK);
    }
}
