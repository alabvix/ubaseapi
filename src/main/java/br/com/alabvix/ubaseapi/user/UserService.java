package br.com.alabvix.ubaseapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser() {
        return null;


    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


}
