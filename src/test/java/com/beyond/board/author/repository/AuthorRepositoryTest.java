package com.beyond.board.author.repository;

import com.beyond.board.author.domain.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // 테스트 완료 후 데이터가 실제 insert되지 않고, 롤백
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName("회원가입 repository 테스트")
    @Test
    public void authoSaveTest(){
//        테스트 검증 로직 : 객체 생성 -> save -> 재조회 -> 조회한 객체와 저장한 객체가 동일한지를 비교
//        준비(prepare, given)
        Author author = Author.builder()
                .name("test")
                .email("test1234@test.com")
                .password("12341234")
                .build();
        
//        실행(execute, when)
        authorRepository.save(author);
        
//        검증(then)
        Author authorDb = authorRepository.findByEmail("test1234@test.com").orElse(null);
        Assertions.assertEquals(author.getEmail(), authorDb.getEmail());
    }
}