package sv.edu.udb.spring.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sv.edu.udb.spring.repository.domain.Post;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void init(){
        final Long expectedId = 1L;
        final String expectedTitle = "Anything you want to write";
        final LocalDate expectedDate = LocalDate.of(2024, 8, 24);
        final Post newPost = Post
                .builder()
                .id(expectedId)
                .title(expectedTitle)
                .postDate(expectedDate)
                .build();
        postRepository.save(newPost);
    }

    @AfterEach
    void clean(){
        postRepository.deleteAll();
    }

    @Test
    void shouldHasOnePost_When_FindAll(){
        int expectedPostNumber = 1;
        final List<Post> actualPostList = postRepository.findAll();
        assertNotNull(actualPostList);
        assertEquals(expectedPostNumber, actualPostList.size());
    }

    @Test
    @Transactional
    void shouldSavePost_When_PostIsNew(){
        final Long expectedId = 2L;
        final String expectedTitle = "Anything you want to write";
        final LocalDate expectedDate = LocalDate.of(2024, 8, 24);
        final Post newPost = Post
                .builder()
                .id(expectedId)
                .title(expectedTitle)
                .postDate(expectedDate)
                .build();
        postRepository.save(newPost);

        final Post actualPost = postRepository.findById(expectedId).orElse(null);
        assertNotNull(actualPost);
        assertEquals(expectedId, actualPost.getId());
        assertEquals(expectedTitle, actualPost.getTitle());
        assertEquals(expectedDate, actualPost.getPostDate());
    }

    @Test
    void shouldDeletePost_When_PostExist(){
        final Long expectedId = 3L;
        final String expectedTitle = "Deleted";
        final LocalDate expectedDate = LocalDate.of(2024,8,24);

        final Post newPost = Post
                .builder()
                .id(expectedId)
                .title(expectedTitle)
                .postDate(expectedDate)
                .build();

        postRepository.save(newPost);

        final Post actualPost = postRepository.findById(expectedId).orElse(null);
        assertNotNull(actualPost);

        postRepository.delete(newPost);

        final Post deletedPost = postRepository.findById(expectedId).orElse(null);
        assertNull(deletedPost);
    }

    @Test
    void getAllPostByDate(){
        final LocalDate expectedDate= LocalDate.of(2024,05,1);
        final List<Post> posts = postRepository.findByPostDate(expectedDate);
        assertNotNull(posts);

    }

}
