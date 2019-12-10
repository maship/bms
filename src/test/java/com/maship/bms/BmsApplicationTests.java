package com.maship.bms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class BmsApplicationTests {

  @Resource
	private PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
	}

	@Test
	void testBCrypt() {
		System.out.println(passwordEncoder.encode("admin"));
	}

}
