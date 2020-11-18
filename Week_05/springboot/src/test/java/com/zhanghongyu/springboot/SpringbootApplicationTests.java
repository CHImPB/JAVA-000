package com.zhanghongyu.springboot;

import com.zhanghongyu.springboot.jdbc.PureJDBC;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {

	@Test
	void contextLoads() {
		PureJDBC jdbc = new PureJDBC();
		jdbc.crud();
	}

}
