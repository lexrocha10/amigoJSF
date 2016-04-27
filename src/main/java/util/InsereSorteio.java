package util;

import dominio.Sexo;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class InsereSorteio {

    public static void main(String[] args) throws ParseException {

        Sexo sexo = new Sexo();
        
        sexo.setDescricao("Feminino");
        //tipoSexo.setDescricao("Masculino");
        EntityManager em = JpaUtil2.getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(sexo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}