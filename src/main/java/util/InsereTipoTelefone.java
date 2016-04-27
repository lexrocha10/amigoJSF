package util;

import dominio.TipoTelefone;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class InsereTipoTelefone {

    public static void main(String[] args) throws ParseException {

        TipoTelefone tipoTelefone = new TipoTelefone();
        
        //tipoTelefone.setDescricao("Fixo");
        tipoTelefone.setDescricao("Celular");
        EntityManager em = JpaUtil2.getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(tipoTelefone);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}