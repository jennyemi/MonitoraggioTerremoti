/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rilevamentoterremoti.DAO;

import static com.mycompany.rilevamentoterremoti.DAO.UtenteDAO.getEntityManager;
import com.mycompany.rilevamentoterremoti.DAO.exceptions.NonexistentEntityException;
import com.mycompany.rilevamentoterremoti.entity.Rilevazione;
import com.mycompany.rilevamentoterremoti.entity.Utente;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Jennifer
 */
public class RilevazioneDAO implements Serializable {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void create(Rilevazione rilevazione) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(rilevazione);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static void edit(Rilevazione rilevazione) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            rilevazione = em.merge(rilevazione);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = rilevazione.getId();
                if (findRilevazione(id) == null) {
                    throw new NonexistentEntityException("The rilevazione with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rilevazione rilevazione;
            try {
                rilevazione = em.getReference(Rilevazione.class, id);
                rilevazione.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rilevazione with id " + id + " no longer exists.", enfe);
            }
            em.remove(rilevazione);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static List<Rilevazione> findRilevazioneEntities() {
        return findRilevazioneEntities(true, -1, -1);
    }

    public static List<Rilevazione> findRilevazioneEntities(int maxResults, int firstResult) {
        return findRilevazioneEntities(false, maxResults, firstResult);
    }

    private static List<Rilevazione> findRilevazioneEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rilevazione.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static Rilevazione findRilevazione(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rilevazione.class, id);
        } finally {
            em.close();
        }
    }

    public static List<Rilevazione> findRilevazionebyDate(String data) {
        EntityManager em = getEntityManager();
        List<Rilevazione> l_rilevazione = em.createQuery("SELECT data FROM Rilevazione r WHERE r.dataOra LIKE :data")
                .setParameter("data", data).getResultList();
        return l_rilevazione;
    }

    public static List<Rilevazione> findRilevazionebyLocalita(String localita) {
        EntityManager em = getEntityManager();
        List<Rilevazione> l_rilevazione = em.createQuery("SELECT località FROM Rilevazione r WHERE r.località LIKE '%Italia%'")
                .setParameter("localita", localita).getResultList();
        return l_rilevazione;
    }

    public static List<Rilevazione> findRilevazionebyMagnitudo(String magni) {
        EntityManager em = getEntityManager();
        Float magnitudo = Float.parseFloat(magni);
        List<Rilevazione> l_rilevazione = em.createQuery("SELECT magnitudo FROM Rilevazione r WHERE r.magnitudo > 3")
                .setParameter("magnitudo", magnitudo).getResultList();
        return l_rilevazione;
    }

    public static List<Rilevazione> findRilevazionebyProfondita(String prof) {
        EntityManager em = getEntityManager();
        int profondita = Integer.parseInt(prof);
        List<Rilevazione> l_rilevazione = em.createQuery("SELECT profondità FROM Rilevazione r WHERE r.profondità LIKE :profondita")
                .setParameter("profondita", profondita).getResultList();
        return l_rilevazione;
    }

    public static int getRilevazioneCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rilevazione> rt = cq.from(Rilevazione.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
