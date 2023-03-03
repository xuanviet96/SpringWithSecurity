package com.example.springwithsecurity;

import com.example.springwithsecurity.entity.Role;
import com.example.springwithsecurity.entity.User;
import com.example.springwithsecurity.repository.RoleRepository;
import com.example.springwithsecurity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Rollback(false)
public class RoleRepositoryTests {
    @Autowired
    private RoleRepository repo;

    @Autowired
    private UserRepository userRepository;
    @Test
    public void testCreateRoles(){
        Role editor = new Role("ROLE_EDITOR");
        repo.save(editor);
        long numberOfRoles = repo.count();
        assertEquals(4, numberOfRoles);
    }
    @Test
    public void testAssignRoleToUser() {
        String useremail = "linh@gmail.com";
        String roleName = "ROLE_USER";

        try{
            User myUser = userRepository.findByEmail(useremail).get();
            Role myRole = repo.findByRoleName(roleName).get();
            Set<Role> roles = new HashSet<>();
            roles.add(myRole);
            myUser.setRoles(roles);
            userRepository.save(myUser);
            assertThat(myUser.getRoles()).hasSize(1);
        } catch(Exception e) {
            System.out.println("user not fould");

        }
    }
    @Test
    public void testFindRole() {
        String roleName = "ROLE_USER";
            Role myRole = repo.findByRoleName(roleName).get();
            System.out.println(myRole.getRoleName());
            assertEquals(myRole.getRoleName(), roleName);
    }

}
