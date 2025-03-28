package sv.edu.udb.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import sv.edu.udb.repository.domain.PostComment;

import java.util.List;

@Repository
public class PostCommentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<PostComment> findAll() {
        final String QUERY = "select pc from PostComment pc";
        return entityManager
                .createQuery(QUERY, PostComment.class)
                .getResultList();
    }

    public PostComment findById(final Long id) {

        return entityManager.find(PostComment.class, id);
    }

    @Transactional
    public void save(final PostComment postComment)
    {
        entityManager.persist(postComment);
    }

    public void delete(final PostComment postComment){
        entityManager.remove(postComment);
    }


}
