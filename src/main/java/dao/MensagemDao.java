/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dominio.Grupo;
import dominio.Mensagem;
import dominio.Participante;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JpaUtil2;

/**
 *
 * @author Rocha
 */
public class MensagemDao extends DaoGenerico<Object> {
    
    public MensagemDao() {
        super(Participante.class);
    }
    
    public List<Mensagem> getMensagens(Grupo grupoPassado) {

        EntityManager em = JpaUtil2.getEntityManager();
        List<Mensagem> mensagens;

        try {
            Query query = em.createNamedQuery("Mensagem.findMensagemByGrupo");
            query.setParameter("grupoPassado", grupoPassado);
            mensagens = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            mensagens = new ArrayList();
        } finally {
            em.close();
        }
        return mensagens;
    }
    
    public List<Mensagem> getMinhasMensagens(Participante pPassado) {

        EntityManager em = JpaUtil2.getEntityManager();
        List<Mensagem> mensagens;

        try {
            Query query = em.createNamedQuery("Mensagem.findMensagemByParticipante");
            query.setParameter("pPassado", pPassado);
            mensagens = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            mensagens = new ArrayList();
        } finally {
            em.close();
        }
        return mensagens;
    }
    
    public List<Mensagem> getMinhasMensagensEnviadas(Participante pPassado) {

        EntityManager em = JpaUtil2.getEntityManager();
        List<Mensagem> mensagens;

        try {
            Query query = em.createNamedQuery("Mensagem.findMensagemEnviadaByParticipante");
            query.setParameter("pPassado", pPassado);
            mensagens = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            mensagens = new ArrayList();
        } finally {
            em.close();
        }
        return mensagens;
    }
    
    public void add(Mensagem m) {
        EntityManager em = JpaUtil2.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(m);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
