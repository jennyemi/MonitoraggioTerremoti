/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rilevamentoterremoti.DAO;

import com.mycompany.rilevamentoterremoti.DAO.exceptions.NonexistentEntityException;
import com.mycompany.rilevamentoterremoti.DAO.exceptions.PreexistingEntityException;
import com.mycompany.rilevamentoterremoti.entity.Centralina;
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
public class CentralinaDAO implements Serializable {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void create(Centralina centralina) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(centralina);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCentralina(centralina.getId()) != null) {
                throw new PreexistingEntityException("Centralina " + centralina + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static void edit(Centralina centralina) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            centralina = em.merge(centralina);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = centralina.getId();
                if (findCentralina(id) == null) {
                    throw new NonexistentEntityException("The centralina with id " + id + " no longer exists.");
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
            Centralina centralina;
            try {
                centralina = em.getReference(Centralina.class, id);
                centralina.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The centralina with id " + id + " no longer exists.", enfe);
            }
            em.remove(centralina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static List<Centralina> findCentralinaEntities() {
        return findCentralinaEntities(true, -1, -1);
    }

    public static List<Centralina> findCentralinaEntities(int maxResults, int firstResult) {
        return findCentralinaEntities(false, maxResults, firstResult);
    }

    private static List<Centralina> findCentralinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Centralina.class));
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

    public static Centralina findCentralina(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Centralina.class, id);
        } finally {
            em.close();
        }
    }

    public static int getCentralinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Centralina> rt = cq.from(Centralina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
