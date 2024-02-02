package dao;

import entities.DistributoreAutorizzato;
import entities.Mezzo;
import entities.Manutenzione;
import entities.Ticket;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class MezzoDAO extends BaseDAO {

    public MezzoDAO() {
        super("trasporto_pubblico");
    }

    public void aggiungiMezzo(Mezzo mezzo) {
        EntityManager em = emf.createEntityManager();

        try {
            beginTransaction(em);
            em.merge(mezzo);
//            em.refresh(distributore);
            commitTransaction(em);
        } catch (Exception e) {
            rollbackTransaction(em);
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void rimuoviMezzo(Long id) {
        EntityManager em = emf.createEntityManager();

        try {
            beginTransaction(em);
            Mezzo mezzo = em.find(Mezzo.class, id);
            if (mezzo != null) {
                em.remove(mezzo);
            }
            commitTransaction(em);
        } catch (Exception e) {
            rollbackTransaction(em);
            throw e;
        } finally {
            closeEntityManager(em);
        }
    }

    public Mezzo ricercaMezzoPerId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Mezzo.class, id);
        } finally {
            closeEntityManager(em);
        }
    }

    public List<Manutenzione> statoManutenzione(Long id) {

        EntityManager em = emf.createEntityManager();
        try {
            Mezzo mezzo = em.find(Mezzo.class, id);
            if (mezzo.isManutenzione()){
                String jpql = "SELECT m FROM Manutenzione m WHERE m.mezzo.id = :id";
                return em.createQuery(jpql, Manutenzione.class)
                        .setParameter("id", id)
                        .getResultList();
            }
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public int numeroBigliettiMezzo(Long id){

        EntityManager em = emf.createEntityManager();
        try{
            Mezzo mezzo = em.find(Mezzo.class, id);
            if (mezzo != null){
                List<Ticket> tickets = mezzo.getTickets();
                return (int) tickets.size();
            }
            return 0;
        } finally {
            closeEntityManager(em);
        }
    }

    public Mezzo getMezzoById(Long id) {
        EntityManager em = emf.createEntityManager();
        Mezzo mezzo = em.find(Mezzo.class, id);
        em.close();
        return mezzo;
    }

    public static List<Mezzo> getAllMezzi() {
        EntityManager em = emf.createEntityManager();

        try {
            String jpql = "SELECT m FROM Mezzo m";
            TypedQuery<Mezzo> query = em.createQuery(jpql, Mezzo.class);
            return query.getResultList();
        } finally {
            closeEntityManager(em);
        }
    }
}

