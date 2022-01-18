package com.elosinfo.customerapi.service;

import com.elosinfo.customerapi.entity.CustomerEntity;
import com.elosinfo.customerapi.exception.CustomerException;
import com.elosinfo.customerapi.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository repository;

    @Override
    public List<CustomerEntity> getAll() {
        try {
            return this.repository.findAll();
        } catch (Exception e) {
            throw new CustomerException("Error searching customer with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Optional<CustomerEntity> getById(Long id) {
        try {
            return this.repository.findById(id);
        } catch (Exception e) {
            throw new CustomerException("Error searching customer by ID " + id + " with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void create(CustomerEntity customer) {
        try {
            this.repository.save(customer);
        } catch (Exception e) {
            throw new CustomerException("Error creating customer with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void update(Long id, CustomerEntity customer) {
        try {
            Optional<CustomerEntity> searchedEntity = this.repository.findById(id);

            if (searchedEntity.isEmpty()){
                throw new CustomerException("Não foi encontrado cliente com o id " + id, HttpStatus.NOT_FOUND);
            }

            CustomerEntity entityUpdated = searchedEntity.get();
            entityUpdated.setId(id);
            entityUpdated.setActive(customer.getActive());
            entityUpdated.setDocument(customer.getDocument());
            entityUpdated.setFirstName(customer.getFirstName());
            entityUpdated.setLastName(customer.getLastName());
            entityUpdated.setEmail(customer.getEmail());
            entityUpdated.setMobilePhoneCode(customer.getMobilePhoneCode());
            entityUpdated.setMobilePhoneNumber(customer.getMobilePhoneNumber());
            entityUpdated.setUpdatedAt(LocalDateTime.now());

            this.repository.save(entityUpdated);
        } catch (CustomerException c) {
            throw c;
        } catch (Exception e) {
            throw new CustomerException("Error updating customer_id " + id + " with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Optional<CustomerEntity> entity = this.repository.findById(id);

            if (entity.isEmpty()){
                throw new CustomerException("Não foi encontrado cliente com o id " + id, HttpStatus.NOT_FOUND);
            }

            this.repository.deleteById(id);
        } catch (CustomerException c) {
            throw c;
        } catch (Exception e) {
            throw new CustomerException("Error updating customer_id " + id + " with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
