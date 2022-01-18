package com.elosinfo.customerapi.service;

import com.elosinfo.customerapi.entity.CustomerEntity;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    public List<CustomerEntity> getAll();

    public Optional<CustomerEntity> getById(Long id);

    public void create(CustomerEntity customer);

    public void update(Long id, CustomerEntity customer);

    public void delete(Long id);
}
