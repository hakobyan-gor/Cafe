package com.cafe;

import com.cafe.utils.constants.RoleConstants;
import com.cafe.repository.ManagerRepository;
import com.cafe.repository.RoleRepository;
import com.cafe.model.entity.user.Manager;
import com.cafe.model.entity.user.Role;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Component
public class ApplicationRefreshed implements ApplicationListener<ContextRefreshedEvent> {

    private final ManagerRepository mManagerRepository;
    private final PasswordEncoder mPasswordEncoder;
    private final RoleRepository mRoleRepository;

    @Autowired
    public ApplicationRefreshed(
            ManagerRepository managerRepository,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository
    ) {
        this.mRoleRepository = roleRepository;
        this.mManagerRepository = managerRepository;
        this.mPasswordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(@NotNull ContextRefreshedEvent event) {
//        try {
//            seedData();
//        } catch (EntityNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    private void seedData() throws EntityNotFoundException {
        Role adminRole = new Role();
        adminRole.setRoleName(RoleConstants.ADMIN_NAME);
        adminRole.setDescription("Admin - Has permission to perform admin tasks");
        adminRole.setDeleted(false);
        mRoleRepository.save(adminRole);

        Role managerRole = new Role();
        managerRole.setRoleName(RoleConstants.MANAGER_NAME);
        managerRole.setDescription("Manger User - Has permission to create waiters, tables");
        managerRole.setDeleted(false);
        mRoleRepository.save(managerRole);

        Role waiterRole = new Role();
        waiterRole.setRoleName(RoleConstants.WAITER_NAME);
        waiterRole.setDescription("Waiter - Has permission to create orders");
        waiterRole.setDeleted(false);
        mRoleRepository.save(waiterRole);

        Manager cafeManager = new Manager();
        cafeManager.setPassword(mPasswordEncoder.encode("cafemanager2021/"));
        cafeManager.setEmail("jobs@sflpro.com");
        cafeManager.setDeleted(false);
        cafeManager.setUsername("jobs@sflpro.com" + new Date().getTime());
        cafeManager.setRole(mRoleRepository.findById(RoleConstants.MANAGER_ID).orElseThrow(() -> new EntityNotFoundException("Role Manager not found")));
        mManagerRepository.save(cafeManager);

    }

}