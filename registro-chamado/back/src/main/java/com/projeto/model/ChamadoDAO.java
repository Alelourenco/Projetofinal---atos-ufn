package com.projeto.model;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ChamadoDAO {
    private static final EntityManagerFactory emf;
    private EntityManager em;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("default");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ChamadoDAO() {
        em = emf.createEntityManager();
    }


    //SELECT * FROM produto
    public List<Chamado> listarTodos() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Chamado> cq = cb.createQuery(Chamado.class);
        Root<Chamado> rootEntry = cq.from(Chamado.class);
        CriteriaQuery<Chamado> all = cq.select(rootEntry);
        TypedQuery<Chamado> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
    
    public Long inserir(Chamado chamado) throws Exception {
        try {
            em.getTransaction().begin();

            em.persist(chamado);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
            //TODO: handle exception
        }

        return chamado.getChamado();
    }

    public void atualizar(Chamado chamado) throws Exception {
        try {
            em.getTransaction().begin();

            Chamado prod = em.find(Chamado.class, chamado.getChamado());
            prod.setTitulo(chamado.getTitulo());
            prod.setStatus(chamado.getStatus());
             

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
    public void remover(Chamado chamado) throws Exception {
        try {
            em.getTransaction().begin();

            Chamado prod = em.find(Chamado.class, chamado.getChamado());
            em.remove(prod);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }

    }
}
