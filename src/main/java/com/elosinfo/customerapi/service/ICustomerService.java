package com.elosinfo.customerapi.service;

import com.elosinfo.customerapi.dto.CustomerDto;
import com.elosinfo.customerapi.entity.CustomerEntity;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    public List<CustomerEntity> getAll();

    public Optional<CustomerEntity> getById(Long id);

    public void create(CustomerDto customerDto);

    public void update(Long id, CustomerDto customerDto);

    public void delete(Long id);
}
