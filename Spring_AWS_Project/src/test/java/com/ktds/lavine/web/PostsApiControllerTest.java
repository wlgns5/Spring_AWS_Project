package com.ktds.lavine.web;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.ktds.lavine.domain.posts.Posts;
import com.ktds.lavine.domain.posts.PostsRepository;
import com.ktds.lavine.web.dto.PostsSaveRequestDto;
import com.ktds.lavine.web.dto.PostsUpdateRequestDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PostsRepository postsRepository;
	
	@AfterEach
	public void tearDown() throws Exception {
		postsRepository.deleteAll();
	}
	
	
	@Ignore
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
	
	@Test
	public void Posts_Update() throws Exception {
		
		//given
		Posts savedPosts = postsRepository.save(Posts.builder()
				.title("title")
				.content("content")
				.author("author")
				.build());
		
		Long updateId = savedPosts.getId();
		String expectdTitle = "title2";
		String expectedContent = "content2";
			
		PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
				.title(expectdTitle)
				.content(expectedContent)
				.build();
		
		String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;
		
		HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
		
		ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
		
		assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(responseEntity.getBody(), greaterThan(0L));
		
		
		// service? 
		
		List<Posts> all = postsRepository.findAll();
		assertThat(all.get(0).getTitle(),equalTo(expectdTitle));
		assertThat(all.get(0).getContent(),equalTo(expectedContent));
		
		
		
	}
	
}
