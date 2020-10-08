package cn.jdd.reactwebworkflowvertx;

import cn.jdd.reactwebworkflowvertx.Entity.Travel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReactWebWorkflowVertxApplicationTests {

	@Autowired
	Travel travel;

	@Test
	void contextLoads() {
		System.out.println(travel);
	}

}
