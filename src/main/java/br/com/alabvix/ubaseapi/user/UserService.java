package br.com.alabvix.ubaseapi.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    final Logger logger = LoggerFactory.getLogger(UserService.class);

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

        User user = new User(null,
                signUpUser.username,
                signUpUser.email,
                bCryptPasswordEncoder.encode(signUpUser.password),
                new Date());

        return userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<User> opUser = userRepository.findByUsername(userName);

        if (opUser.isPresent()) {
            return opUser.get();
        } else {
            throw new UsernameNotFoundException("User not found");
        }

    }
}
