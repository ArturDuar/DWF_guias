package sv.edu.udb.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.spring.repository.domain.Post;
import sv.edu.udb.spring.repository.domain.PostComment;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    List<PostComment> findPostCommentByPost(Post post);
}
