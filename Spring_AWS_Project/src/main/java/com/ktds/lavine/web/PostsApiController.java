package com.ktds.lavine.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ktds.lavine.service.posts.PostsService;
import com.ktds.lavine.web.dto.PostsResponseDto;
import com.ktds.lavine.web.dto.PostsSaveRequestDto;
import com.ktds.lavine.web.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
	private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findByID (@PathVariable Long id)
    {
        return postsService.findById(id);
    }
    
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
    	postsService.delete(id);
    	return id;
    }
}
