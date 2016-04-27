/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dominio.Grupo;
import dominio.Participante;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JpaUtil2;

/**
 *
 * @author Rocha
 */
public class GrupoDao extends DaoGenerico<Object> {

    public GrupoDao() {
        super(Grupo.class);
    }

    public List<Grupo> getGrupos() {

        EntityManager em = JpaUtil2.getEntityManager();
        List<Grupo> grupos;

        try {
            Query query = em.createNamedQuery("Grupo.findGrupos");
            grupos = query.getResultList();
        } catch (Exception e) {
            grupos = new ArrayList();
        } finally {
            em.close();
        }
        return grupos;
    }

    public List<Grupo> getMeusGrupos(Participante participante) {

        EntityManager em = JpaUtil2.getEntityManager();
        List<Grupo> grupos;

        try {
            Query query = em.createNamedQuery("Grupo.meusGrupos");
            query.setParameter("participantePassado", participante);
            grupos = query.getResultList();
        } catch (Exception e) {
            grupos = new ArrayList();
        } finally {
            em.close();
        }
        return grupos;
    }

    public List<Grupo> getGruposParticipo(Participante participante) {

        EntityManager em = JpaUtil2.getEntityManager();
        List<Grupo> grupos;

        try {
            Query query = em.createNamedQuery("Grupo.gruposParticipo");
            query.setParameter("participantePassado", participante);
            grupos = query.getResultList();
        } catch (Exception e) {
            grupos = new ArrayList();
        } finally {
            em.close();
        }
        return grupos;
    }

    public List<Grupo> JpqlLike(String busca, Participante p) {
        EntityManager em = JpaUtil2.getEntityManager();
        List<Grupo> grupos;
        try {
            Query query = em.createNamedQuery("Grupo.buscarGrupo");
            query.setParameter("pPassado", p);
            query.setParameter("buscar", "%"+busca+"%");
            grupos = query.getResultList();
        } catch (Exception e) {
            grupos = new ArrayList();
        } finally {
            em.close();
        }
        return grupos;
    }
    
    public List<Grupo> getGrupoNome(String busca) {
        EntityManager em = JpaUtil2.getEntityManager();
        List<Grupo> grupos;
        try {
            Query query = em.createNamedQuery("Grupo.buscarGrupoNome");
            query.setParameter("buscar", "%"+busca+"%");
            grupos = query.getResultList();
        } catch (Exception e) {
            grupos = new ArrayList();
        } finally {
            em.close();
        }
        return grupos;
    }

}
