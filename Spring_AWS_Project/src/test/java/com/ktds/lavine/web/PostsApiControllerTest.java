package com.ktds.lavine.web;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;


import java.util.List;

import com.ktds.lavine.domain.posts.Posts;
import com.ktds.lavine.domain.posts.PostsRepository;
import com.ktds.lavine.web.dto.PostsSaveRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PostsRepository postsRepository;
	
	@After
	public void tearDown() throws Exception {
		postsRepository.deleteAll();
	}
	
	@Test
	public void Posts_Save() throws Exception {
		String title = "title";
		String content = "content";
		PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
				.title(title)
				.content(content)
				.author("author")
				.build();
		
		String url = "http://localhost:" + port + "/api/v1/posts";
		
		ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
		
		assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(responseEntity.getBody(), greaterThan(0L));
		
		List<Posts> all = postsRepository.findAll(); 
		assertThat(all.get(0).getTitle(), equalTo(title));
		assertThat(all.get(0).getContent(), equalTo(content));
		
	}
	
}
