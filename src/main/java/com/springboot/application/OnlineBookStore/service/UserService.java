package com.springboot.application.OnlineBookStore.service;

import com.springboot.application.OnlineBookStore.entity.User;
import com.springboot.application.OnlineBookStore.user.BookStoreUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public User findByUserName(String userName);

    public void save(BookStoreUser bookStoreUser);

    public User findRecentUser();
}
