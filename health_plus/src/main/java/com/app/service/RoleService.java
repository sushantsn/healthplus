package com.app.service;

import com.app.entities.Role;

public interface RoleService {
    Role getDefaultRole();

    Role getRoleByAuthority(String authority);
}
