package sv.edu.udb.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.spring.repository.domain.Post;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByPostDate(LocalDate postDate);
}
