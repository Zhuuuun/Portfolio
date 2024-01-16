package com.zhunismp.project1.services;

import com.zhunismp.project1.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerService extends CrudRepository<Customer,Integer> {
    Customer findById(int id);
}
