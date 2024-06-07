package com.example.server;

import com.example.server.model.ComicPost;
import com.example.server.repository.ComicPostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ComicPostRepositoryTests {

    @Autowired
    private ComicPostRepository comicPostRepository;

    @Test
    public void testSaveAndFindComicPost() {
        // 데이터베이스에 저장할 ComicPost 객체 생성
        ComicPost comicPost = new ComicPost();
        comicPost.setComicId("1");
        comicPost.setImageUrl("http://example.com/image.jpg");
        comicPost.setDescription("A sample comic post");

        // 저장
        comicPostRepository.save(comicPost);

        // 저장된 데이터 조회
        ComicPost retrievedComicPost = comicPostRepository.findById(comicPost.getId()).orElse(null);

        // 검증
        assertThat(retrievedComicPost).isNotNull();
        assertThat(retrievedComicPost.getComicId()).isEqualTo("1");
        assertThat(retrievedComicPost.getImageUrl()).isEqualTo("http://example.com/image.jpg");
        assertThat(retrievedComicPost.getDescription()).isEqualTo("A sample comic post");
    }
}
