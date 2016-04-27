/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dominio.Grupo;
import dominio.Participante;
import dominio.SugestaoPresente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JpaUtil2;

/**
 *
 * @author Rocha
 */
public class SugestaoPresenteDao extends DaoGenerico<Object> {
    
    public SugestaoPresenteDao() {
        super(SugestaoPresente.class);
    }
    
    public List<SugestaoPresente> getSugestaoPresentesGrupo(Grupo grupoPassado) {

        EntityManager em = JpaUtil2.getEntityManager();
        List<SugestaoPresente> sugestaoPresentes;

        try {
            Query query = em.createNamedQuery("SugestaoPresente.findSugestaoByGrupo");
            query.setParameter("grupoPassado", grupoPassado);
            sugestaoPresentes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            sugestaoPresentes = new ArrayList();
        } finally {
            em.close();
        }
        return sugestaoPresentes;
    }
    
    public List<SugestaoPresente> getMinhasSugestoes(Participante participantePassado) {

        EntityManager em = JpaUtil2.getEntityManager();
        List<SugestaoPresente> sugestaoPresentes;

        try {
            Query query = em.createNamedQuery("SugestaoPresente.findMinhasSugestao");
            query.setParameter("participantePassado", participantePassado);
            sugestaoPresentes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            sugestaoPresentes = new ArrayList();
        } finally {
            em.close();
        }
        return sugestaoPresentes;
    }
    
    public List<SugestaoPresente> getMinhasSugestoesRecebidas(Participante participantePassado) {

        EntityManager em = JpaUtil2.getEntityManager();
        List<SugestaoPresente> sugestaoPresentes;

        try {
            Query query = em.createNamedQuery("SugestaoPresente.findMinhasSugestaoRecebidas");
            query.setParameter("participantePassado", participantePassado);
            sugestaoPresentes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            sugestaoPresentes = new ArrayList();
        } finally {
            em.close();
        }
        return sugestaoPresentes;
    }
    
    public void add(SugestaoPresente sp) {
        EntityManager em = JpaUtil2.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(sp);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
