package dao;

import entities.DistributoreAutorizzato;
import entities.Manutenzione;
import entities.Percorso;
import entities.Tratta;
import dao.TrattaDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercorsoDAO {

    private EntityManagerFactory emf;


    public PercorsoDAO() {this.emf = Persistence.createEntityManagerFactory("trasporto_pubblico");}

    public void aggiungiPercorso(Percorso percorso) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            beginTransaction(transaction);
            em.persist(percorso);
            commitTransaction(em);
        } catch (Exception e) {
            rollbackTransaction(em);
            throw e;
        } finally {
            closeEntityManager(em);
        }
    }

    public void rimuovipercorso(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            beginTransaction(transaction);
            Percorso percorso = em.find(Percorso.class, id);
            if (percorso != null) {
                em.remove(percorso);
            }
            commitTransaction(em);
        } catch (Exception e) {
            rollbackTransaction(em);
            throw e;
        } finally {
            closeEntityManager(em);
        }
    }

    public void beginTransaction(EntityTransaction transaction) {
        if (!transaction.isActive()) {
            transaction.begin();
        }
    }

    public void commitTransaction(EntityManager em) {
        em.getTransaction().commit();
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

    public Map<String,Long> calcolaTempoMedioPercorso(){
        EntityManager em = emf.createEntityManager();
        TrattaDAO trattaDAO = new TrattaDAO();
        try {
            List<Percorso> percorsi = em.createQuery("SELECT p FROM Percorso p", Percorso.class).getResultList();
            Map<String,Long> tempiMedi = new HashMap<>();

            for (Percorso percorso : percorsi){
                List<Tratta> tratte = percorso.getTratte();
                Duration tempoTotale = Duration.ZERO;



                for (Tratta tratta : tratte) {
                    em.refresh(tratta);
                    tempoTotale = tempoTotale.plus(trattaDAO.getTempoPercorrenzaTratta(tratta.getId()));
                }

                em.refresh(percorso);

                long numeroTratte = tratte.size();
                Long tempoMedioPercorrenza = tempoTotale.toMinutes() / numeroTratte;

                tempiMedi.put(percorso.getIdPercorso().toString(), tempoMedioPercorrenza);
            }


            return tempiMedi;

        } finally {
            em.close();
        }
    }

    public Percorso getPercorsoById(Long id) {
        EntityManager em = emf.createEntityManager();
        Percorso percorso = em.find(Percorso.class, id);
        em.close();
        return percorso;
    }


}
