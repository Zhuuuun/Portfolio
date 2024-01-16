package com.zhunismp.project1.services;

import com.zhunismp.project1.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductService extends CrudRepository<Product,Integer>,ProductServiceCustom {
    Product findById(int id);
}
