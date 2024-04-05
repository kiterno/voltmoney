package com.volt.money.service;

import com.volt.money.model.ServiceOperator;
import com.volt.money.repository.ServiceOperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorService {
    @Autowired
    private ServiceOperatorRepository serviceOperatorRepository;

    public List<ServiceOperator> getAllOperators() {
        return serviceOperatorRepository.findAll();
    }

    public ServiceOperator addOperator(String name) {
        if (serviceOperatorRepository.findByName(name) != null) {
            throw new IllegalArgumentException("Operator with the same name already exists");
        }

        // Create a new operator
        ServiceOperator operator = new ServiceOperator(name);
        return serviceOperatorRepository.save(operator);
    }
}
