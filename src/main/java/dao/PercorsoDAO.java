package dao;

import entities.Manutenzione;
import entities.Percorso;
import entities.Tratta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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
    public List<Duration> calcolaTempoMedioPercorso(){
        EntityManager em = emf.createEntityManager();
        try {
            List<Percorso> percorsi = em.createQuery("SELECT p FROM Percorso p", Percorso.class).getResultList();
            List<Duration> tempiMedi = new ArrayList<>();

            for (Percorso percorso : percorsi){
                List<Tratta> tratte = percorso.getTratte();
                Duration tempoTotale = Duration.ZERO;

                for (Tratta tratta : tratte) {
                    tempoTotale = tempoTotale.plus(tratta.getTempoPercorrenzaTratta());
                }

                long numeroTratte = tratte.size();
                Duration tempoMedioPercorrenza = tempoTotale.dividedBy(numeroTratte);

                tempiMedi.add(tempoMedioPercorrenza);
            }

            return tempiMedi;

        } finally {
            em.close();
        }
    }


}
