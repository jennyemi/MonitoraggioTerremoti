/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rilevamentoterremoti.DAO;

import com.mycompany.rilevamentoterremoti.DAO.exceptions.NonexistentEntityException;
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
public class UtenteDAO implements Serializable {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void create(Utente utente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(utente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static void edit(Utente utente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            utente = em.merge(utente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = utente.getId();
                if (findUtente(id) == null) {
                    throw new NonexistentEntityException("The utente with id " + id + " no longer exists.");
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
            Utente utente;
            try {
                utente = em.getReference(Utente.class, id);
                utente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The utente with id " + id + " no longer exists.", enfe);
            }
            em.remove(utente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static List<Utente> findUtenteEntities() {
        return findUtenteEntities(true, -1, -1);
    }

    public static List<Utente> findUtenteEntities(int maxResults, int firstResult) {
        return findUtenteEntities(false, maxResults, firstResult);
    }

    private static List<Utente> findUtenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Utente.class));
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

    public static Utente findUtente(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Utente.class, id);
        } finally {
            em.close();
        }
    }
    
public static Utente findUtentebyUsername(String username){
    EntityManager em = getEntityManager();
    Utente utente = (Utente) em.createQuery("SELECT utn FROM Utente utn WHERE utn.username LIKE :username")
            .setParameter("username", username).getSingleResult();
    return utente;
    }


    public static int getUtenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Utente> rt = cq.from(Utente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
