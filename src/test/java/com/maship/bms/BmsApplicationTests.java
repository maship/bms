package com.maship.bms;

import com.maship.bms.config.JwtConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class BmsApplicationTests {

  @Resource
	private PasswordEncoder passwordEncoder;


	@Resource
	private JwtConfig jwtConfig;

	@Test
	void contextLoads() {
	}

	@Test
	void testBCrypt() {
		System.out.println(passwordEncoder.encode("admin"));
	}

	@Test
	void testYmlConfig() {
		System.out.println(jwtConfig.getExpiration());
	}
}
