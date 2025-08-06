package com.beyond.board.author.service;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.dto.AuthorCreateDto;
import com.beyond.board.author.dto.AuthorDetailDto;
import com.beyond.board.author.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void  authorSaveTest(){
//        given
        AuthorCreateDto authorCreateDto = AuthorCreateDto.builder()
                .name("test")
                .email("test1234@test.com")
                .password("123412341234")
                .build();
//        when
        authorService.save(authorCreateDto);
        Author authorDb = authorRepository.findByEmail("test1234@test.com").orElse(null);
//        then
        Assertions.assertEquals(authorCreateDto.getEmail(),authorDb.getEmail());
    }

    @Test
    public void findAllTest(){
//        findAll을 통한 결과값의 개수 맞는 지를 검증
//        최초개수 구한 후
        int authorSize = authorService.findAll().size();
//        데이터 추가(ex) 3개) 후
        for(int i=0; i<3; i++){
            Author author = Author.builder()
                    .name("test"+i)
                    .email("test"+i+"@test1.com")
                    .password("123456789")
                    .build();
            authorRepository.save(author);
        }
//        마지막 개수 구해서 최초 개수 + 3 하고 일치하는 지 여부 검증
        int authorSizeDb = authorService.findAll().size();
        Assertions.assertEquals(authorSize+3,authorSizeDb);
    }
}