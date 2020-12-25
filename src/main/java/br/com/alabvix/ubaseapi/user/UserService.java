package br.com.alabvix.ubaseapi.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User signUp(SignUpUser signUpUser) {

        logger.info("Singing up a new user with email: " + signUpUser.email);

        // TODO: validate confirmed password

        User user = new User(null,
                signUpUser.name,
                signUpUser.email,
                bCryptPasswordEncoder.encode(signUpUser.password));

        return userRepository.save(user);
    }



    public List<User> findAll() {
        return userRepository.findAll();
    }


}
