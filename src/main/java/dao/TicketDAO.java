package dao;

import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class TicketDAO {

    private EntityManagerFactory emf;

    public TicketDAO() {
        this.emf = Persistence.createEntityManagerFactory("trasporto_pubblico");
    }

    public void aggiungiTicket(Ticket ticket) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ticket);
        em.getTransaction().commit();
        em.close();
    }

    public void rimuoviTicket(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Ticket ticket = em.find(Ticket.class, id);
        if (ticket != null) {
            em.remove(ticket);
        }
        em.getTransaction().commit();
        em.close();
    }

    public Ticket ricercaTicketPerId(Long id) {
        EntityManager em = emf.createEntityManager();
        Ticket result = em.createQuery("SELECT t FROM Ticket t WHERE t.id_ticket = :id_ticket", Ticket.class)
                .setParameter("id_ticket", id);

        if (result.Tipologia.ABBONAMENTO.equals(tipologia)){

            LocalDateTime data_attivazione = result.DataAttivazione;
            Validita validita = result.Validita;


            if(!data_attivazione) {
                System.out.println("Il biglietto è da attivare");
            } else if((data_attivazione) && (validita == "WEEK") && data_attivazione.plusDays(7).isBefore(LocalDateTime.now())){
                    System.out.println("Il biglietto settimanale è ancora valido e scade il " + data_attivazione.plusDays(7));
            } else if((data_attivazione) && (validita == "MONTH")){

                }
        }

        em.close();

        return prestito;
    }

    public List<Prestito> ricercaPrestitiUtente(Utente utente) {
        EntityManager em = emf.createEntityManager();
        List<Prestito> result = em.createQuery("SELECT p FROM Prestito p WHERE p.utente = :utente", Prestito.class)
                .setParameter("utente", utente)
                .getResultList();
        em.close();
        return result;
    }

    public List<Prestito> ricercaPrestitiScadutiNonRestituiti() {
        EntityManager em = emf.createEntityManager();
        List<Prestito> result = em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < :oggi AND p.dataRestituzioneEffettiva IS NULL", Prestito.class)
                .setParameter("oggi", LocalDate.now())
                .getResultList();
        em.close();
        return result;
    }

}

