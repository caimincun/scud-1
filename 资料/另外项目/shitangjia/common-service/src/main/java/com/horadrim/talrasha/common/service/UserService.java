package com.horadrim.talrasha.common.service;


import com.horadrim.talrasha.common.model.Order;
import com.horadrim.talrasha.common.model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface UserService extends GenericService<User, Integer> {
//	User getByUsername(final String username);
//
boolean countUserByPhone(String phone);
//
//    int login(final String username, final String password);
//
//    List<Map> listUserByGroup(int groupId);
//
//    ComPagerWithTitleRes listUnderCompany(User user,String userName, int pageindex, int size);
//
//    void updatePwd(String newPwd, int userid,String realName);

List<User> queryUsers(int canteenId,int currentPage,int size,int sort);

}

