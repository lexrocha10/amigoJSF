/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dominio.Login;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JpaUtil2;

/**
 *
 * @author Rocha
 */
public class LoginDao extends DaoGenerico<Object> {
    
    public LoginDao() {
        super(Login.class);
    }
    
    public Login fazerLogin(Login loginPassado) {

        EntityManager em = JpaUtil2.getEntityManager();
        Login login;

        try {
            Query query = em.createNamedQuery("Login.efetuarLogin");
            query.setParameter("strUsuario", loginPassado.getUsuario());
            query.setParameter("strSenha", Criptografia.criptografar(loginPassado.getSenha()));
            login = (Login) query.getSingleResult();
        } catch (Exception e) {
            login = new Login();
        } finally {
            em.close();
        }
        return login;
    }
    
    public Login add(Login l) {
        EntityManager em = JpaUtil2.getEntityManager();
        String novaSenha = Criptografia.criptografar(l.getSenha());
        l.setSenha(novaSenha);
        try {
            em.getTransaction().begin();
            em.persist(l);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
            l = new Login();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return l;
    }
    
    public void upd(Login l) {
        EntityManager em = JpaUtil2.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(l);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
