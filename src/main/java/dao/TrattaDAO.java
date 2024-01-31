package dao;

import entities.Manutenzione;
import entities.Mezzo;
import entities.Tratta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class TrattaDAO {

    private EntityManagerFactory emf;

    public TrattaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void closeEntityManager(EntityManager em) {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    public void beginTransaction(EntityManager em) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    public void commitTransaction(EntityManager em) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        closeEntityManager(em);
    }

    public void rollbackTransaction(EntityManager em) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        closeEntityManager(em);
    }

    public void aggiungiTratta(Tratta tratta) {
        EntityManager em = emf.createEntityManager();
        try {
            beginTransaction(em);
            em.persist(tratta);
            commitTransaction(em);
        } catch (Exception e) {
            rollbackTransaction(em);
            throw e;
        } finally {
            closeEntityManager(em);
        }
    }

    public int numeroDiVoltePercorso (long id_mezzo, long id_tratta){

        EntityManager em = emf.createEntityManager();
        try{
            Mezzo mezzo = em.find(Mezzo.class, id_mezzo);
            Tratta tratta = em.find(Tratta.class, id_tratta);

            if ((mezzo != null) && (tratta != null)){
                String jpql = "SELECT t FROM Tratta t WHERE t.id = :id_tratta";
                List<Tratta> tratteId = em.createQuery(jpql, Tratta.class).setParameter("id_tratta", id_tratta).getResultList();
                tratteId
            }
        }
    }

}
