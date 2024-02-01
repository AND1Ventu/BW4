package dao;

import entities.Manutenzione;
import entities.Mezzo;
import entities.Percorso;
import entities.Tratta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.time.Duration;

public class TrattaDAO {

    private EntityManagerFactory emf;

    public TrattaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public TrattaDAO() {
        this.emf = Persistence.createEntityManagerFactory("trasporto_pubblico");
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

    public Long numeroDiVoltePercorso (long id_mezzo, long id_tratta){

        Long conteggioNTratte = 0L;

        EntityManager em = emf.createEntityManager();
        try {
            Mezzo mezzo = em.find(Mezzo.class, id_mezzo);
            Tratta tratta = em.find(Tratta.class, id_tratta);

            if ((mezzo != null) && (tratta != null)) {
                String jpql = "SELECT m FROM Mezzo m WHERE m.id = :id_mezzo";
                Mezzo mezzoId = em.createQuery(jpql, Mezzo.class).setParameter("id_mezzo", id_mezzo).getSingleResult();
                Percorso id_percorso = mezzoId.getPercorso();
                String jpql2 = "SELECT COUNT(DISTINCT t) FROM Tratta t WHERE t.percorso.id = :id_percorso";
                conteggioNTratte = em.createQuery(jpql2, Long.class).setParameter("id_percorso", id_percorso.getIdPercorso()).getSingleResult();
            }
        } finally {
            closeEntityManager(em);
        }
        return conteggioNTratte;
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

    public void closeEntityManager(EntityManager em) {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    public Duration getTempoPercorrenzaTratta(Long id){
        EntityManager em = emf.createEntityManager();
        Tratta tratta = em.find(Tratta.class, id);

        if (tratta.getDataOraInizioTratta() != null && tratta.getDataOraFineTratta() != null){
            return Duration.between(tratta.getDataOraInizioTratta(), tratta.getDataOraFineTratta());
        } else {
            return Duration.ZERO;
        }
    }



}
