package com.udemy.msb.restfulstarter.bootstrap;

import com.udemy.msb.restfulstarter.domain.User;
import com.udemy.msb.restfulstarter.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    private UserService userService;

    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(userService.totalUsers() == 0) {
            loadUserData();
        }
    }

    private void loadUserData() {
        User user1 = new User(null, "John", "Jackson", new Date());
        User user2 = new User(null, "Jack", "Johnson", new Date());

        userService.saveUser(user1);
        userService.saveUser(user2);

        System.out.println("Loaded users: " + userService.totalUsers());
    }
}
