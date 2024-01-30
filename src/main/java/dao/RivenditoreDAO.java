package dao;

import entities.RivenditoreAutorizzato;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class RivenditoreDAO {

    private EntityManagerFactory emf;

    public RivenditoreDAO() {
        this.emf = Persistence.createEntityManagerFactory("YourPersistenceUnitName");
    }

    public void closeEntityManagerFactory() {
        emf.close();
    }

    public void saveRivenditore(RivenditoreAutorizzato rivenditore) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(rivenditore);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public RivenditoreAutorizzato getRivenditoreById(Long id) {
        EntityManager em = emf.createEntityManager();
        RivenditoreAutorizzato rivenditore = em.find(RivenditoreAutorizzato.class, id);
        em.close();
        return rivenditore;
    }

    public List<RivenditoreAutorizzato> getAllRivenditori() {
        EntityManager em = emf.createEntityManager();
        List<RivenditoreAutorizzato> rivenditori = em.createQuery("SELECT r FROM RivenditoreAutorizzato r", RivenditoreAutorizzato.class)
                .getResultList();
        em.close();
        return rivenditori;
    }


}
