package util;

import dominio.Grupo;
import dominio.TipoGrupo;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class AtualizaGrupo {

    public static void main(String[] args) throws ParseException {

        EntityManager em = JpaUtil2.getEntityManager();

        Grupo grupo = em.find(Grupo.class, 1);
        grupo.setTipoGrupo(em.find(TipoGrupo.class,4));

        try {

            em.getTransaction().begin(); 

            em.persist(grupo);

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();

        } finally {
            em.close();
        }

    }

}
