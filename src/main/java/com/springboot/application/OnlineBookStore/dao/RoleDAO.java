package com.springboot.application.OnlineBookStore.dao;

import com.springboot.application.OnlineBookStore.entity.Role;

public interface RoleDAO {

    public Role findRoleByName(String roleName);
}
