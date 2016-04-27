package util;

import dominio.CategoriaPresente;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class AtualizaCategoriaPresente {

    public static void main(String[] args) throws ParseException {

        EntityManager em = JpaUtil2.getEntityManager();

        CategoriaPresente categoriaPresente = em.find(CategoriaPresente.class, 2);
        categoriaPresente.setDescricao("Brinquedos");

        try {

            em.getTransaction().begin(); 

            em.persist(categoriaPresente);

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();

        } finally {
            em.close();
        }

    }

}
