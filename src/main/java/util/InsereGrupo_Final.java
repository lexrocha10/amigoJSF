package util;

import dominio.Grupo;
import dominio.Participante;
import dominio.TipoGrupo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;

public class InsereGrupo_Final {

    public static void main(String[] args) throws ParseException {

        Grupo grupo = new Grupo();
        
        EntityManager em = JpaUtil2.getEntityManager();
        
        grupo.setAdministrador(em.find(Participante.class,5));
        grupo.setNome("Teste");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("05/04/2016");
        grupo.setDataSorteio(date);
        date = sdf.parse("05/04/2016");
        grupo.setDataConfraternizacao(date);
        //grupo.setHoraConfraternizacao("18:00");
        grupo.setTipoGrupo(em.find(TipoGrupo.class,5));
        grupo.setValorMinimoPresente((float) 20.00);
        grupo.setValorMaximoPresente((float) 100.00);
        
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