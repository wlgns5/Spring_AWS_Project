package com.ktds.lavine.service.posts;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.lavine.domain.posts.Posts;
import com.ktds.lavine.domain.posts.PostsRepository;
import com.ktds.lavine.web.dto.PostsListResponseDto;
import com.ktds.lavine.web.dto.PostsResponseDto;
import com.ktds.lavine.web.dto.PostsSaveRequestDto;
import com.ktds.lavine.web.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {

	private final PostsRepository postsRepository;

	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Posts posts = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("�ش� �Խñ��� �����ϴ�. id=" + id));

		posts.update(requestDto.getTitle(), requestDto.getContent());

		return id;
	}

	public PostsResponseDto findById(Long id) {
		Posts entity = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("�ش� �Խñ��� �����ϴ�. id=" + id));
		return new PostsResponseDto(entity);
	}
	
	@Transactional(readOnly = true)
	public List<PostsListResponseDto> findAllDesc() {
		return postsRepository.findAllDesc().stream()
				.map(PostsListResponseDto::new)
				.collect(Collectors.toList());
		
	}
	
	@Transactional
	public void delete(Long id) {
		Posts posts = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("�ش� �Խñ��� �����ϴ�. id=" + id));
		
		postsRepository.delete(posts);
	}
}
