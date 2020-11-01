package cn.jdd.adminserver.Service;

import Domain.UserDTO;

public interface AdminUserService {
    UserDTO loadByUsername(String userName);
}
