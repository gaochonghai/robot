package com.movie.springboot;


import javafx.application.Application;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringbootApplication.class})
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
class SpringbootApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	@Before
public void init() {
	System.out.println("开始测试-----------------");
}

	@After
	public void after() {
		System.out.println("测试结束-----------------");
	}
}
