package com.example.demo.UserRepository;

import com.example.demo.UserModel.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRep extends MongoRepository<User,String>{
    User findByEmail(String email);
    User findByUsername(String username);
}
