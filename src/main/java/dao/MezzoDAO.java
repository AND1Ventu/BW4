package dao;

import entities.Mezzo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MezzoDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public MezzoDAO(){
        emf = Persistence.createEntityManagerFactory("trasporto_pubblico");
        em = emf.createEntityManager();
    }


}
