package com.example.diploma;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest
class DiplomaApplicationTests {
	private final GenericContainer<?> myApp = new GenericContainer<>("app:1.0").withExposedPorts(8800);

	@Test
	void containerTest(){
		myApp.start();
	}

}
