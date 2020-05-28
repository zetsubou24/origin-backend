package com.akhil.origin.repository;

import com.akhil.origin.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    public User findById(int id);

    public User findByUserEmail(String email);

}
