package com.ktds.lavine.service.posts;

import org.springframework.stereotype.Service;

import com.ktds.lavine.domain.posts.PostsRepository;
import com.ktds.lavine.web.dto.PostsSaveRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {
	
	private final PostsRepository postsRepository;
	
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}
	
}
