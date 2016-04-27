/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dominio.Grupo;
import dominio.Login;
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
public class ParticipanteDao extends DaoGenerico<Object> {
    
    public ParticipanteDao() {
        super(Participante.class);
    }
    
    public List<Participante> getParticipantes(Grupo grupoPassado) {

        EntityManager em = JpaUtil2.getEntityManager();
        List<Participante> participantes;

        try {
            Query query = em.createNamedQuery("Participante.findParticipanteByGrupo");
            query.setParameter("grupoPassado", grupoPassado);
            participantes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            participantes = new ArrayList();
        } finally {
            em.close();
        }
        return participantes;
    }
    
    public Participante getParticipanteByLogin(Login loginPassado) {

        EntityManager em = JpaUtil2.getEntityManager();
        Participante participante;
        System.out.println("Uusuario: "+loginPassado.getUsuario()
                + " Senha: "+loginPassado.getSenha()+"ID: "+loginPassado.getId());

        try {
            Query query = em.createNamedQuery("Participante.findParticipanteByLogin");
            query.setParameter("loginPassado", loginPassado);
            participante = (Participante) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            participante = new Participante();
        } finally {
            em.close();
        }
        return participante;
    }
    
}
