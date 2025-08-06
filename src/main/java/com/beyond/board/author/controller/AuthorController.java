package com.beyond.board.author.controller;

import com.beyond.board.author.dto.*;
import com.beyond.board.author.service.AuthorService;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/login")
    public String loginScreen(){
        return "author/author_login";
    }

    @GetMapping("/list")
    public String findByAll(Model model){
        List<AuthorListDto> authorListDtoList = authorService.findAll();
        model.addAttribute("authorList", authorListDtoList);
        return "/author/author_list";
    }

    @GetMapping("/detail/{id}")
    public String findById(@PathVariable Long id, Model model) {
        AuthorDetailDto authorDetailDto = authorService.findById(id);
        model.addAttribute("authorDetail", authorDetailDto);
        return "author/author_detail";
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
