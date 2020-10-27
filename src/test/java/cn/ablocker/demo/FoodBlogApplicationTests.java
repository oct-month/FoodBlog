package cn.ablocker.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import cn.ablocker.FoodBlog.FoodBlogApplication;

@SpringBootTest(classes = FoodBlogApplication.class)
class FoodBlogApplicationTests
{
	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads()
	{
		assertNotNull(context);
	}
}
