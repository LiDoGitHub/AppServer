package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.OrderStatus;

public interface OrderStatusMapper {
    int deleteByPrimaryKey(String statusid);

    int insert(OrderStatus record);

    int insertSelective(OrderStatus record);

    OrderStatus selectByPrimaryKey(String statusid);

    int updateByPrimaryKeySelective(OrderStatus record);

    int updateByPrimaryKey(OrderStatus record);
}