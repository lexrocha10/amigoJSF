package util;

import dominio.CategoriaPresente;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class InsereCategoriaPresente {

    public static void main(String[] args) throws ParseException {

        CategoriaPresente categoriaPresente = new CategoriaPresente();
        
        //categoriaPresente.setDescricao("Bebidas");
        //categoriaPresente.setDescricao("Vestuário");
        //categoriaPresente.setDescricao("Doces");
        //categoriaPresente.setDescricao("Perfumaria");
        //categoriaPresente.setDescricao("Calçado");
        //categoriaPresente.setDescricao("Esportes");
        categoriaPresente.setDescricao("Informática");
        //categoriaPresente.setDescricao("Video Games");
        //categoriaPresente.setDescricao("Livros");
        EntityManager em = JpaUtil2.getEntityManager();
        
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