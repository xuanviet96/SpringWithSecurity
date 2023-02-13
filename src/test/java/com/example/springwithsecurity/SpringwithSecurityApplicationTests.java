package com.example.springwithsecurity;

import com.example.springwithsecurity.entity.User;
import com.example.springwithsecurity.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Rollback(false)
class SpringwithSecurityApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Test
	void testCreateUser() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password =passwordEncoder.encode("123");
		User newUser = new User("thanhtu@email.com", password);
		User savedUser = userRepository.save(newUser);
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getUserId()).isGreaterThan(0);
	}

}
