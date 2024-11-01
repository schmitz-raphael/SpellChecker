package lu.uni.jakartaee.jpa;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class SpellingErrorRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(SpellingError error) {
        em.persist(error);
    }

    public List<SpellingError> findTopErrors(int x) {
        return em.createQuery("SELECT e.wrongWord, e.correctWord, COUNT(e) FROM SpellingError e GROUP BY e.wrongWord, e.correctWord ORDER BY COUNT(e) DESC")
            .setMaxResults(x)
            .getResultList();
    }
}

