package com.elosinfo.customerapi.service;

import com.elosinfo.customerapi.dto.CustomerDto;
import com.elosinfo.customerapi.entity.CustomerEntity;
import com.elosinfo.customerapi.exception.CustomerException;
import com.elosinfo.customerapi.repository.ICustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository repository;

    @Override
    public List<CustomerEntity> getAll() {
        List<CustomerEntity> result;
        try {
            result = this.repository.findAll();
        } catch (Exception e) {
            throw new CustomerException("Error searching customer with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @Override
    public Optional<CustomerEntity> getById(Long id) {
        try {
            return this.repository.findById(id);
        } catch (Exception e) {
            throw new CustomerException("Error searching customer with ID " + id + " with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void create(CustomerDto customerDto) {
        try {
            CustomerEntity entity = new ModelMapper().map(customerDto, CustomerEntity.class);
            this.repository.save(entity);
        } catch (Exception e) {
            throw new CustomerException("Error creating customer with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void update(Long id, CustomerDto customerDto) {
        try {
            Optional<CustomerEntity> searchedEntity = this.repository.findById(id);

            if (searchedEntity.isEmpty()){
                throw new CustomerException("There is not customer with ID " + id, HttpStatus.NOT_FOUND);
            }

            CustomerEntity updatedEntity = new ModelMapper().map(customerDto, CustomerEntity.class);
            updatedEntity.setId(id);

            this.repository.save(updatedEntity);
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
                throw new CustomerException("There is not customer with ID " + id, HttpStatus.NOT_FOUND);
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
