package com.springboot.application.OnlineBookStore.service;

import com.springboot.application.OnlineBookStore.dto.BookStoreUserDTO;
import com.springboot.application.OnlineBookStore.dao.repository.CustomerRepository;
import com.springboot.application.OnlineBookStore.dao.RoleDAO;
import com.springboot.application.OnlineBookStore.dao.UserDAO;
import com.springboot.application.OnlineBookStore.entity.Customer;
import com.springboot.application.OnlineBookStore.entity.Role;
import com.springboot.application.OnlineBookStore.entity.User;
import com.springboot.application.OnlineBookStore.service_interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User findByUserName(String userName) {

        return userDAO.findByUserName(userName);
    }

    @Override
    @Transactional
    public void save(BookStoreUserDTO bookStoreUserDTO) {

        User user = saveDetails(bookStoreUserDTO);

        user.setRoles(Arrays.asList(roleDAO.findRoleByName("ROLE_CUSTOMER")));

        userDAO.save(user);
    }

    @Transactional
    @Override
    public void saveAdmin(BookStoreUserDTO bookStoreUserDTO)
    {
        User user = saveDetails(bookStoreUserDTO);
        user.setRoles(Arrays.asList(roleDAO.findRoleByName("ROLE_ADMIN")));
        userDAO.save(user);
    }

    @Override
    public User findRecentUser() {
        return userDAO.findRecentUser();
    }

    public Customer getCustomerFromUsername(String userName)
    {
        User user = userDAO.findByUserName(userName);

        Customer customer = user.getCustomer();

        return customer;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUserName(username);

        if(user == null)
        throw new UsernameNotFoundException("Invalid username or password");

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
    {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public User saveDetails(BookStoreUserDTO bookStoreUserDTO)
    {
        User user = new User();

        user.setUserName(bookStoreUserDTO.getUserName());
        user.setPassword(passwordEncoder.encode(bookStoreUserDTO.getPassword()));
        user.setFirstName(bookStoreUserDTO.getFirstName());
        user.setLastName(bookStoreUserDTO.getLastName());
        user.setEmail(bookStoreUserDTO.getEmail());

        return user;
    }
}
