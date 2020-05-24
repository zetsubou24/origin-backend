package com.akhil.origin.service;

import com.akhil.origin.dao.UserDAO;
import com.akhil.origin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> getUsers() {
        return userDAO.getUsers();
    }
}
