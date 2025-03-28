package sv.edu.udb.spring.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sv.edu.udb.spring.repository.domain.Post;
import sv.edu.udb.spring.repository.domain.PostComment;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PostCommentRepositoryTest {

    @Autowired
    PostCommentRepository postCommentRepository;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void initData(){

        //Creacion de post
        final Long expectedId = 1L;
        final String expectedTitle = "Este es un post para test";
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
    void cleanData(){
        postRepository.deleteAll();
        postCommentRepository.deleteAll();
    }

    @Test
    @Transactional
    void shouldSaveNewPostComment(){
        final Long expectedId= 2L;
        final String expectedReview = "This is a comment of the first post";
        final LocalDate expectedDate = LocalDate.of(2024, 8, 29);
        final Post expectedPost = postRepository.findById(1L).orElse(null);
        assertNotNull(expectedPost);

        final PostComment newPostComment = PostComment
                .builder()
                .id(expectedId)
                .review(expectedReview)
                .commentDate(expectedDate)
                .post(expectedPost)
                .build();

        postCommentRepository.save(newPostComment);

        final PostComment actualPostComment = postCommentRepository.findById(expectedId).orElse(null);
        assertNotNull(actualPostComment);
        assertEquals(actualPostComment.getId(), expectedId);
        assertEquals(actualPostComment.getReview(), expectedReview);
        assertEquals(actualPostComment.getCommentDate(), expectedDate);
        assertEquals(actualPostComment.getPost(), expectedPost);
    }

    @Test
    void shouldDeleteWhenPostCommentExists(){
        final Long expectedId= 3L;
        final String expectedReview = "This is the second comment of the first post";
        final LocalDate expectedDate = LocalDate.of(2025, 9, 15);
        final Post expectedPost = postRepository.findById(1L).orElse(null);
        assertNotNull(expectedPost);

        final PostComment newPostComment = PostComment
                .builder()
                .id(expectedId)
                .review(expectedReview)
                .commentDate(expectedDate)
                .post(expectedPost)
                .build();

        postCommentRepository.save(newPostComment);

        final PostComment actualPostComment = postCommentRepository.findById(expectedId).orElse(null);
        assertNotNull(actualPostComment);

        postCommentRepository.delete(actualPostComment);

        final PostComment deletedPostComment = postCommentRepository.findById(expectedId).orElse(null);
        assertNull(deletedPostComment);
    }

    @Test
    void shoulgetPostCommentsByPost(){
        final Post expectedPost = postRepository.findById(1L).orElse(null);
        assertNotNull(expectedPost);

        final List<PostComment> postComments = postCommentRepository.findPostCommentByPost(expectedPost);
        assertNotNull(postComments);
    }
}
