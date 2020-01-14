package com.uh.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.jwt.JwtDecoder;

@SpringBootTest
class ServerApplicationTests {

	@MockBean
	JwtDecoder jwtDecoder;

	@Test
	void contextLoads() {
	}

}
