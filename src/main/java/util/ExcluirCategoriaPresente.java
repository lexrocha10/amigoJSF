package util;

import dominio.CategoriaPresente;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class ExcluirCategoriaPresente {

    public static void main(String[] args) throws ParseException {

        EntityManager em = JpaUtil2.getEntityManager();

        CategoriaPresente categoriaPresente = em.find(CategoriaPresente.class, 1);

        try {

            em.getTransaction().begin(); 

            em.remove(categoriaPresente);

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();

        } finally {
            em.close();
        }

    }

}
