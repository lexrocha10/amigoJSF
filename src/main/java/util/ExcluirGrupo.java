package util;

import dominio.Grupo;
import javax.persistence.EntityManager;

public class ExcluirGrupo {

    public static void main(String[] args) {

        EntityManager em = JpaUtil2.getEntityManager();
        Grupo grupo = em.find(Grupo.class, 1);
        try {
            em.getTransaction().begin();
            em.remove(grupo);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
