package com.zhunismp.project1.services.impl;

import com.zhunismp.project1.entity.Order;
import com.zhunismp.project1.exception.OrderNotFoundException;
import com.zhunismp.project1.exception.ProductNotEnoughException;
import com.zhunismp.project1.services.OrderServiceCustom;
import com.zhunismp.project1.services.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class OrderServiceCustomImpl implements OrderServiceCustom {

    @Autowired
    ProductService productService;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void completeOrderById(int id) {
        Order order = entityManager.find(Order.class,id);
        productService.updateAmountByAdd(order.getProductId(),-order.getAmount());
        order.setStatus(0);
        entityManager.merge(order);
    }

}
