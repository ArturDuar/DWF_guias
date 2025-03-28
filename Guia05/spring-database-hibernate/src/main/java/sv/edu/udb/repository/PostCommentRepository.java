package sv.edu.udb.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import sv.edu.udb.repository.domain.PostComment;

import java.util.List;

@Repository
public class PostCommentRepository {

    private final SessionFactory sessionFactory;

    public PostCommentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<PostComment> findAll() {
        final String Query = "SELECT pc FROM PostComment pc";
        return sessionFactory
                .getCurrentSession()
                .createQuery(Query, PostComment.class)
                .getResultList();
    }

    public PostComment findById(final Long id) {
        return sessionFactory
                .getCurrentSession()
                .find(PostComment.class, id);
    }

    @Transactional
    public void save(final PostComment postComment) {
        sessionFactory
                .getCurrentSession()
                .persist(postComment);
    }

    public void delete(final PostComment postComment) {
        sessionFactory
                .getCurrentSession()
                .remove(postComment);
    }

    public void deleteById(final Long id) {
        final String Query = "DELETE FROM PostComment pc WHERE pc.id = :id";
        sessionFactory
                .getCurrentSession()
                .createMutationQuery(Query)
                .setParameter("id", id)
                .executeUpdate();
    }

}

