package com.elosinfo.customerapi.controller;

import com.elosinfo.customerapi.entity.CustomerEntity;
import com.elosinfo.customerapi.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerRepository repository;

    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> getById(@PathVariable Long id){
        Optional<CustomerEntity> entity = this.repository.findById(id);

        if (entity.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(entity.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody CustomerEntity customerEntity){
        try {
            this.repository.save(customerEntity);
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(false);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody CustomerEntity customerEntity){
        Optional<CustomerEntity> entity = this.repository.findById(id);

        if (!entity.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        customerEntity.setId(id);
        this.repository.save(customerEntity);

        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id){
        Optional<CustomerEntity> entity = this.repository.findById(id);

        if (!entity.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        this.repository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
