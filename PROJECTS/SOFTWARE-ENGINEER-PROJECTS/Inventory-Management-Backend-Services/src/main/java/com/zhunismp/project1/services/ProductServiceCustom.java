package com.zhunismp.project1.services;

import java.util.Map;

public interface ProductServiceCustom {

    void updateAmountByAdd(int productId,int amt); // current amount += amt

    // find amount that need to manufacture due to order
    // if product is enough for all orders this method will return 0
    Map<String,Integer> determineNeedAmount(int productId);

    Map<String,Integer> determineNeedAmountAll();
}
