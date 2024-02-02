package dao;

import entities.DistributoreAutorizzato;
import entities.Manutenzione;
import entities.Percorso;
import entities.Tratta;
import dao.*;

import javax.persistence.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercorsoDAO extends BaseDAO {

    public PercorsoDAO() {
        super("trasporto_pubblico");
    }

    public void aggiungiPercorso(Percorso percorso) {
        EntityManager em = emf.createEntityManager();

        try {
            beginTransaction(em);
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

        try {
            beginTransaction(em);
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

    public static List<Percorso> getAllPercorsi() {
        EntityManager em = emf.createEntityManager();

        try {
            String jpql = "SELECT p FROM Percorso p";
            TypedQuery<Percorso> query = em.createQuery(jpql, Percorso.class);
            return query.getResultList();
        } finally {
            closeEntityManager(em);
        }
    }

}
