package com.mycompany.service;

import com.mycompany.repository.UserRepository;
import com.mycompany.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public void save(User user) {
        repo.save(user);
    }
}
