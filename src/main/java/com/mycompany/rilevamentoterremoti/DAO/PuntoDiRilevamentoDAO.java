/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rilevamentoterremoti.DAO;

import com.mycompany.rilevamentoterremoti.DAO.exceptions.NonexistentEntityException;
import com.mycompany.rilevamentoterremoti.DAO.exceptions.PreexistingEntityException;
import com.mycompany.rilevamentoterremoti.entity.PuntoDiRilevamento;
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
public class PuntoDiRilevamentoDAO implements Serializable {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void create(PuntoDiRilevamento puntoDiRilevamento) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(puntoDiRilevamento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPuntoDiRilevamento(puntoDiRilevamento.getId()) != null) {
                throw new PreexistingEntityException("PuntoDiRilevamento " + puntoDiRilevamento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static void edit(PuntoDiRilevamento puntoDiRilevamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            puntoDiRilevamento = em.merge(puntoDiRilevamento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = puntoDiRilevamento.getId();
                if (findPuntoDiRilevamento(id) == null) {
                    throw new NonexistentEntityException("The puntoDiRilevamento with id " + id + " no longer exists.");
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
            PuntoDiRilevamento puntoDiRilevamento;
            try {
                puntoDiRilevamento = em.getReference(PuntoDiRilevamento.class, id);
                puntoDiRilevamento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The puntoDiRilevamento with id " + id + " no longer exists.", enfe);
            }
            em.remove(puntoDiRilevamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static List<PuntoDiRilevamento> findPuntoDiRilevamentoEntities() {
        return findPuntoDiRilevamentoEntities(true, -1, -1);
    }

    public static List<PuntoDiRilevamento> findPuntoDiRilevamentoEntities(int maxResults, int firstResult) {
        return findPuntoDiRilevamentoEntities(false, maxResults, firstResult);
    }

    private static List<PuntoDiRilevamento> findPuntoDiRilevamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PuntoDiRilevamento.class));
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

    public static PuntoDiRilevamento findPuntoDiRilevamento(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PuntoDiRilevamento.class, id);
        } finally {
            em.close();
        }
    }

    public static int getPuntoDiRilevamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PuntoDiRilevamento> rt = cq.from(PuntoDiRilevamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
