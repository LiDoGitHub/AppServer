package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.UserOrders;

public interface UserOrdersMapper {
    int deleteByPrimaryKey(String orderid);

    int insert(UserOrders record);

    int insertSelective(UserOrders record);

    UserOrders selectByPrimaryKey(String orderid);

    int updateByPrimaryKeySelective(UserOrders record);

    int updateByPrimaryKey(UserOrders record);
}