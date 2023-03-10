package com.example.springwithsecurity;

import com.example.springwithsecurity.entity.Role;
import com.example.springwithsecurity.entity.User;
import com.example.springwithsecurity.repository.RoleRepository;
import com.example.springwithsecurity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Rollback(false)
public class SpringwithSecurityApplicationTests {

	@Autowired
	private UserRepository userRepository;

//	@Test
//	void testCreateUser() {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String password =passwordEncoder.encode("Xuan96");
//		User newUser = new User("editor@email.com", "abc", password);
//		User savedUser = userRepository.save(newUser);
//		assertThat(savedUser).isNotNull();
//		assertThat(savedUser.getUserId()).isGreaterThan(0);
//
//	}


}
