package util;

import dominio.TipoGrupo;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class InsereTipoGrupo {

    public static void main(String[] args) throws ParseException {

        TipoGrupo tipoGrupo = new TipoGrupo();
        
        //tipoGrupo.setDescricao("Família");
        //tipoGrupo.setDescricao("Trabalho");
        //tipoGrupo.setDescricao("Esola");
        //tipoGrupo.setDescricao("Faculdade");
        tipoGrupo.setDescricao("Amigos");
        EntityManager em = JpaUtil2.getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(tipoGrupo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}