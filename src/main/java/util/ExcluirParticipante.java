package util;

import dominio.Participante;
import javax.persistence.EntityManager;

public class ExcluirParticipante {

    public static void main(String[] args) {

        EntityManager em = JpaUtil2.getEntityManager();
        Participante participante = em.find(Participante.class, 29);
        try {
            em.getTransaction().begin();
            em.remove(participante);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
