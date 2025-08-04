package com.beyond.board.post.controller;

import com.beyond.board.post.dto.PostCreateDto;
import com.beyond.board.post.dto.PostDetailDto;
import com.beyond.board.post.dto.PostListDto;
import com.beyond.board.post.service.PostService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public String create(@Valid PostCreateDto postCreateDTO) {
        postService.save(postCreateDTO);
        return null;
    }

    @GetMapping("/list")
    public String findAll() {
        List<PostListDto> postListDTO = postService.findAll();
        return null;
    }

    @GetMapping("/listPaging")
    public String findAll(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostListDto> postListDTO = postService.findAll(pageable);
        return null;
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id) {
        PostDetailDto postDetailDTO = postService.findById(id);
        return null;
    }
}