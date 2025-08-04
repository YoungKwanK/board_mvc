package com.beyond.board.author.controller;

import com.beyond.board.author.dto.*;
import com.beyond.board.author.service.AuthorService;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/create")
    public String createScreen() {
        return "author/author_register";
    }

    @PostMapping("/create")
    public String save(@Valid AuthorCreateDto authorCreateDto) {
        authorService.save(authorCreateDto);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String findByAll(){
        List<AuthorListDto> authorListDtoList = authorService.findAll();
        return null;
    }

    @GetMapping("/detail/{id}")
    public String findById(@PathVariable Long id) {
        AuthorDetailDto authorDetailDto = authorService.findById(id);
        return null;
    }

    @PatchMapping("/updatepw")
    public void updatepw(@RequestBody AuthorUpdatePwDto authorUpdatePwDto) {
        authorService.updatePassword(authorUpdatePwDto);
    }

    @DeleteMapping("/de/{id}")
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
}
