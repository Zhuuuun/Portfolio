package com.zhunismp.project1.services;

import com.zhunismp.project1.entity.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService extends CrudRepository<Order,Integer> , OrderServiceCustom{
    Order findById(int id);
    @Query(value = "from Order where status = 0")
    List<Order> findAllCompleteOrders();
    @Query(value = "from Order where status = 1")
    List<Order> findAllUnCompleteOrders();
}
