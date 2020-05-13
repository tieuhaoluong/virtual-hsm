package com.hada.virtual.hsm.config;

import com.hada.virtual.hsm.domain.Authority;
import com.hada.virtual.hsm.domain.User;
import com.hada.virtual.hsm.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataInit implements ApplicationRunner {
    private UserRepository userRepository;

    public DataInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.count() == 0) {
            User admin = new User();
            Authority roleAdmin = new Authority();
            roleAdmin.setName("ROLE_ADMIN");
            roleAdmin.setDescription("Default");
            admin.setFullName("System Admin");
            admin.setLogin("sysadmin");
            admin.setEmail("admin@system");
            admin.setAuthorities(Collections.singleton(roleAdmin));
            admin.setPassword("12345678");
        }
    }
}
