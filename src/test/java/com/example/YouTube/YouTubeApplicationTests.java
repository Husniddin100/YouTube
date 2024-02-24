package com.example.YouTube;

import com.example.YouTube.service.HttpTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YouTubeApplicationTests {
	@Autowired
	private HttpTestService httpTestService;

	@Test
	void contextLoads() {
		httpTestService.taskGetAll();
	}

}
