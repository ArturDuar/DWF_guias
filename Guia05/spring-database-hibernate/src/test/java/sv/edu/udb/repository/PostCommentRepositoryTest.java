package sv.edu.udb.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sv.edu.udb.repository.domain.Post;
import sv.edu.udb.repository.domain.PostComment;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PostCommentRepositoryTest {

    @Autowired
    private PostCommentRepository postCommentRepository;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    @Transactional
    void initData(){
        final Long expectedId = 1L;
        final String expectedTitle = "SpringBoot as a back-end";
        final LocalDate expectedDate = LocalDate.of(2023, 9, 29);
        final Post newPost = Post
                .builder()
                .id(expectedId)
                .title(expectedTitle)
                .postDate(expectedDate)
                .build();
        postRepository.save(newPost);

        final Long id = 1L;
        final String expectedReview = "Good Framework";
        final LocalDate reviewDate = LocalDate.of(2024, 12, 1);
        final PostComment newPostComment = PostComment
                .builder()
                .id(id)
                .review(expectedReview)
                .commentDate(reviewDate)
                .post(newPost)
                .build();
        postCommentRepository.save(newPostComment);
    }

    @AfterEach
    @Transactional
    void cleanData(){
        postCommentRepository.deleteById(1L);
    }

    @Test
    @Transactional
    void shouldGetOne_When_FindAllPostComment(){
        final int expectedPostCommentAmount = 1;
        final List<PostComment> actualPostComments = postCommentRepository.findAll();
        assertNotNull(actualPostComments);
        assertEquals(expectedPostCommentAmount, actualPostComments.size());
    }

    @Test
    @Transactional
    void shouldGetPostComment_When_PostCommentIsNew(){
        final Long expectedPostCommentId = 2L;
        final String expectedComment = "Wow, interesting framework!!";
        final LocalDate commentDate = LocalDate.of(2024, 11, 4);
        final Post expectedPost = postRepository.finById(1L);
        final PostComment newPostComment = PostComment
                .builder()
                .id(expectedPostCommentId)
                .commentDate(commentDate)
                .review(expectedComment)
                .post(expectedPost)
                .build();
        postCommentRepository.save(newPostComment);

        final PostComment actualPostComment = postCommentRepository.findById(2L);
        assertNotNull(actualPostComment);
        assertEquals(expectedPostCommentId, actualPostComment.getId());
        assertEquals(expectedComment, actualPostComment.getReview());
        assertEquals(commentDate, actualPostComment.getCommentDate());

        final Post actualPost = postRepository.finById(actualPostComment.getPost().getId());
        assertNotNull(actualPost);
        assertEquals(expectedPost.getId(), actualPost.getId());


        postCommentRepository.delete(actualPostComment);
    }
}
