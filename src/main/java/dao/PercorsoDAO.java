package org.example.DAO;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
public class PercorsoDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public PercorsoDAO() {

        emf = Persistence.createEntityManagerFactory("trasporto_pubblico");
        em = emf.createEntityManager();
    }

    public void save(Percorso e){
        EntityTransaction et = em.getTransaction();

        et.begin();

        em.persist(e);

        et.commit();

        em.refresh(e);
    }

    public Percorso getById(int id){

        return em.find(Percorso.class, id);

    }

    public void delete(int id){
        EntityTransaction et = em.getTransaction();

        et.begin();

        em.remove( getById(id) );

        et.commit();

    }


    public void update(Percorso e) {
        EntityTransaction et = em.getTransaction();

        et.begin();

        em.merge(e);

        et.commit();
    }

    public void getTempoMedio(){
        EntityTransaction et = em.getTransaction();
            List<Tratta> lista = em.createQuery("SELECT t FROM Tratta t", Tratta.class)
                    .getResultList();
            for (Tratta tratta : lista) {
                LocalDateTime inizio = tratta.getDataOraInizioTratta();
                LocalDateTime fine = tratta.getDataOraFineTratta();

                long durataTratta = Duration.between(inizio, fine).toMillis();

                tot += durataTratta;

                double tempoMedio = tot / lista.size();

                int ore = tempoMedio / 60;
                int minuti = tempoMedio % 60;

                System.out.println("Il tempo medio della tratta è: " + ore + " ore, " + minuti + " minuti.");

            }
    }


}
