package dao;

import entities.DistributoreAutorizzato;
import entities.RivenditoreAutorizzato;
import entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class DistributoreDAO {

    private EntityManagerFactory emf;

    public DistributoreDAO() {
        this.emf = Persistence.createEntityManagerFactory("trasporto_pubblico");
    }

    public void closeEntityManagerFactory() {
        emf.close();
    }

    public void saveDistributore(DistributoreAutorizzato distributore) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(distributore);
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

    public DistributoreAutorizzato getDistributoreById(Long id) {
        EntityManager em = emf.createEntityManager();
        DistributoreAutorizzato distributore = em.find(DistributoreAutorizzato.class, id);
        em.close();
        return distributore;
    }

    public List<DistributoreAutorizzato> getAllDistributori() {
        EntityManager em = emf.createEntityManager();
        List<DistributoreAutorizzato> distributori = em.createQuery("SELECT d FROM DistributoreAutorizzato d", DistributoreAutorizzato.class)
                .getResultList();
        em.close();
        return distributori;
    }

    public int getTicketPuntoEmissioneById(Long id, LocalDateTime dataInizio, LocalDateTime dataFine) {
        EntityManager em = emf.createEntityManager();
        int conteggioTicket = 0;

        try {
            List<Ticket> tickets = em.createQuery("SELECT t FROM Ticket t WHERE t.rivenditoreAutorizzato.idDistributore = :id", Ticket.class)
                    .setParameter("id", id)
                    .getResultList();

            for (Ticket ticket : tickets) {
                LocalDateTime dataVendita = ticket.getDataVendita();

                if (dataVendita != null && dataVendita.isAfter(dataInizio) && dataVendita.isBefore(dataFine)) {
                    conteggioTicket++;
                }
            }
        } finally {
            em.close();
        }

        return conteggioTicket;
    }

}
