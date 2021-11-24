package com.ktds.lavine.domain.posts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
// 자동으로 H2 DB를 실행해주는 Annotation
@SpringBootTest
public class PostsRepositoryTest {
	
	@Autowired
	PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		postsRepository.deleteAll();
	}
	
	@Test
	public void post_save() {
		String title = "Test title";
		String content = "Test content";
		
		// DB Insert
		postsRepository.save(Posts.builder()
				.title(title)
				.content(content)
				.author("zihun35@gmail.com")
				.build());
		
		// DB Select
		List<Posts> postsList = postsRepository.findAll();
		
		Posts posts = postsList.get(0);
		assertThat(posts.getTitle(), equalTo(title));
		assertThat(posts.getContent(), equalTo(content));
	}
	

}
