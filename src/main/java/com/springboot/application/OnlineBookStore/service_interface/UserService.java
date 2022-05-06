package com.springboot.application.OnlineBookStore.service_interface;

import com.springboot.application.OnlineBookStore.dto.BookStoreUserDTO;
import com.springboot.application.OnlineBookStore.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public User findByUserName(String userName);

    public void save(BookStoreUserDTO bookStoreUserDTO);

    public User findRecentUser();

    public void saveAdmin(BookStoreUserDTO user);
}
