package com.hongyuchang.multidatabase;

import com.hongyuchang.multidatabase.service.OrderService;
import com.hongyuchang.multidatabase.service.RoutingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class MultiDatabaseApplicationTests {
	@Autowired
	OrderService orderService;

	@Autowired
	RoutingService routingService;
	@Test
	void contextLoads() {
//		List<Map<String,Object>> orders = orderService.getTop10Order();
//		for (Map<String,Object> map:orders) {
//			map.forEach((k,v)->{
//				System.out.println(k+"="+v);
//			});
//		}
//		orderService.addOrder();

		List<Map<String,Object>> orders = routingService.getTop10Order();
		for (Map<String,Object> map:orders) {
			map.forEach((k,v)->{
				System.out.println(k+"="+v);
			});
		}
	}

}
