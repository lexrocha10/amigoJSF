package util;

import dominio.SugestaoPresente;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class ExcluirSugestaoPresente {

    public static void main(String[] args) throws ParseException {
        EntityManager em = JpaUtil2.getEntityManager();
        SugestaoPresente sugestaoPresente = em.find(SugestaoPresente.class, 22);
        try {
            em.getTransaction().begin(); 
            em.remove(sugestaoPresente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
