package util;

import dominio.CategoriaPresente;
import dominio.Grupo;
import dominio.Participante;
import dominio.SugestaoPresente;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class InsereSugestaoPresente_Final {

    public static void main(String[] args) throws ParseException {

        SugestaoPresente sugestaoPresente = new SugestaoPresente();
        
        EntityManager em = JpaUtil2.getEntityManager();
        
        /*
        sugestaoPresente.setCategoriaPresente(em.find(CategoriaPresente.class,2));
        sugestaoPresente.setDescricao("DVD Call Off Duty PS4");
        sugestaoPresente.setLink("www.eagames.com/calloffduty/244"); */
        
        sugestaoPresente.setCategoriaPresente(em.find(CategoriaPresente.class,8));
        sugestaoPresente.setDescricao("Teste");
        sugestaoPresente.setLink("www.teste.com/4353");
        
        //para o grupo
        //sugestaoPresente.setGrupo(em.find(Grupo.class,1));
        //para o participante
        sugestaoPresente.setParticipante(em.find(Participante.class,2));
        
        try {
            em.getTransaction().begin();
            em.persist(sugestaoPresente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}