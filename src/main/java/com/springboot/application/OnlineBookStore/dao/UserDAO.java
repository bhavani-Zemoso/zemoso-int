package com.springboot.application.OnlineBookStore.dao;

import com.springboot.application.OnlineBookStore.entity.User;

public interface UserDAO {

    public User findByUserName(String userName);

    public void save(User user);

    public User findRecentUser();
}
