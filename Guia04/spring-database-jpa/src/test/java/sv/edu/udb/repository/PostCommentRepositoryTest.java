package sv.edu.udb.repository;

import jakarta.transaction.Transactional;
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

    @Test
    void deberiaEncontrarUnoAlObtenerTodos(){
        int expectedNumber = 1;
        final List<PostComment> actualPostCommentList = postCommentRepository.findAll();
        assertNotNull(actualPostCommentList);
        assertEquals(expectedNumber, actualPostCommentList.size());
    }

    @Test
    void deberiaEncontrarUnoAlBuscarPorId(){
        Long idEsperado = 1L;
        final String reviewEsperada = "Great post";
        final LocalDate fechaEsperada = LocalDate.of(2023,10, 5);
        final PostComment resultadoActual = postCommentRepository.findById(idEsperado);

        assertNotNull(resultadoActual);
        assertEquals(idEsperado, resultadoActual.getId());
        assertEquals(reviewEsperada, resultadoActual.getReview());
        assertEquals(fechaEsperada, resultadoActual.getCommentDate());
    }

    @Test
    @Transactional
    void deberiaGuardarUnComentario_CuandoUnComentarioEsNuevo(){
        final Long expectedId = 2L;
        final String expectedReview = "Cualquiera cosa que quiera escribir como comentario";
        final LocalDate expectedDate = LocalDate.of(2024,8,24);
        final Long expectedPostId = 1L;
        final Post post = postRepository.findById(expectedPostId);
        assertNotNull(post);
        assertEquals(expectedPostId, post.getId());

        final PostComment newPostComment = PostComment
                .builder()
                .id(expectedId)
                .commentDate(expectedDate)
                .review(expectedReview)
                .post(post)
                .build();
        postCommentRepository.save(newPostComment);

        final PostComment actualPost = postCommentRepository.findById(expectedId);
        assertNotNull(actualPost);
        assertEquals(expectedId, actualPost.getId());
        assertEquals(expectedReview, actualPost.getReview());
        assertEquals(expectedDate, actualPost.getCommentDate());

        postCommentRepository.delete(newPostComment);
    }




}
