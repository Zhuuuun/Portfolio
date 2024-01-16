package com.zhunismp.project1.services.impl;

import com.zhunismp.project1.entity.Order;
import com.zhunismp.project1.entity.Product;
import com.zhunismp.project1.exception.ProductNotEnoughException;
import com.zhunismp.project1.services.ProductService;
import com.zhunismp.project1.services.ProductServiceCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceCustomImpl implements ProductServiceCustom {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    @Transactional
    public void updateAmountByAdd(int productId,int amt) {
        Product product = entityManager.find(Product.class,productId);
        int isEnough = product.getAmount() + amt;

        if(isEnough >= 0) product.setAmount(product.getAmount() + amt);
        else throw new ProductNotEnoughException("Product isn't enough for this operation, need more " + -isEnough + " ea");
        entityManager.merge(product);
    }

    @Override
    public Map<String,Integer> determineNeedAmount(int productId) {
        Product product = entityManager.find(Product.class,productId);
        List<Order> orders = product.getOrders();
        Map<String,Integer> map = new HashMap<>();

        // sum up total amount of this product
        int totalAmt = 0;
        for(Order order : orders) { totalAmt += order.getAmount(); }

        // if there need to manufacture then return map with amount that needed
        // else return 0, that's mean no need to manufacture for now
        int neededAmount = totalAmt - product.getAmount();
        map.put(product.getName(), Math.max(neededAmount, 0));
        return map;
    }

    @Override
    public Map<String, Integer> determineNeedAmountAll() {
        Map<String,Integer> map = new HashMap<>();


        TypedQuery<Product> query = entityManager.createQuery("FROM Product",Product.class);

        List<Product> products = query.getResultList();
        products.forEach(product -> {
            String productName = product.getName();
            int amt = determineNeedAmount(product.getId()).get(productName);
            map.put(productName,amt);
        });

        return map;
    }
}
