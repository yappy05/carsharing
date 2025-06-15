package com.example.carsharing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class CarsharingApplicationTests {

	@Test
	void contextLoads() {
		assertNotEquals(1, 0, "1 не должно быть равно 0");
	}

}
