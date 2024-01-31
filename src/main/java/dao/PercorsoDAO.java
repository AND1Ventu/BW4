package org.example.DAO;

import java.time.LocalDate;
import java.util.List;
public class PercorsoDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public PercorsoDAO() {

        emf = Persistence.createEntityManagerFactory("trasporto_pubblico");
        em = emf.createEntityManager();
    }

    public void save(Percorso e) {
        EntityTransaction et = em.getTransaction();

        et.begin();

        em.persist(e);

        et.commit();

        em.refresh(e);
    }

    public Percorso getById(int id) {

        return em.find(Percorso.class, id);

    }

    public void delete(int id) {
        EntityTransaction et = em.getTransaction();

        et.begin();

        em.remove(getById(id));

        et.commit();

    }

}
