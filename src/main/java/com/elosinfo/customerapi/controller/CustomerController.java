package com.elosinfo.customerapi.controller;

import com.elosinfo.customerapi.entity.CustomerEntity;
import com.elosinfo.customerapi.repository.ICustomerRepository;
import com.elosinfo.customerapi.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerRepository repository;
    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> getById(@PathVariable Long id){
        Optional<CustomerEntity> entity = this.customerService.getById(id);

        return entity
                .map(customerEntity -> ResponseEntity.status(HttpStatus.OK).body(customerEntity))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody CustomerEntity customerEntity){
        customerService.create(customerEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody CustomerEntity customerEntity){
        this.customerService.update(id, customerEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        this.customerService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
