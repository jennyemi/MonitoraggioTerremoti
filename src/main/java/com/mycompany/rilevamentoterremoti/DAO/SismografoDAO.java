/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rilevamentoterremoti.DAO;

import com.mycompany.rilevamentoterremoti.DAO.exceptions.NonexistentEntityException;
import com.mycompany.rilevamentoterremoti.DAO.exceptions.PreexistingEntityException;
import com.mycompany.rilevamentoterremoti.entity.Sismografo;
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
public class SismografoDAO implements Serializable {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void create(Sismografo sismografo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sismografo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSismografo(sismografo.getId()) != null) {
                throw new PreexistingEntityException("Sismografo " + sismografo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static void edit(Sismografo sismografo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sismografo = em.merge(sismografo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = sismografo.getId();
                if (findSismografo(id) == null) {
                    throw new NonexistentEntityException("The sismografo with id " + id + " no longer exists.");
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
            Sismografo sismografo;
            try {
                sismografo = em.getReference(Sismografo.class, id);
                sismografo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sismografo with id " + id + " no longer exists.", enfe);
            }
            em.remove(sismografo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static List<Sismografo> findSismografoEntities() {
        return findSismografoEntities(true, -1, -1);
    }

    public static List<Sismografo> findSismografoEntities(int maxResults, int firstResult) {
        return findSismografoEntities(false, maxResults, firstResult);
    }

    private static List<Sismografo> findSismografoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sismografo.class));
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

    public static Sismografo findSismografo(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sismografo.class, id);
        } finally {
            em.close();
        }
    }

    public static int getSismografoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sismografo> rt = cq.from(Sismografo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
