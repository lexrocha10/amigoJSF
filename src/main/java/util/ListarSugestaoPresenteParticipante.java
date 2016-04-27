package util;

import dao.GrupoDao;
import dao.ParticipanteDao;
import dao.SugestaoPresenteDao;
import dominio.Grupo;
import dominio.Participante;
import dominio.SugestaoPresente;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Rocha
 */
public class ListarSugestaoPresenteParticipante {
    public static void main(String[] args) throws Exception {
        EntityManager em = JpaUtil2.getEntityManager();
        ParticipanteDao dao = new ParticipanteDao();
        SugestaoPresenteDao dao2 = new SugestaoPresenteDao();
        Participante pt = em.find(Participante.class,7);
        System.out.println("###########  Sugestões de Presente por Participante  #########");
            List<SugestaoPresente> sugestaoPresentes = dao2.getMinhasSugestoes(pt);
            for(SugestaoPresente sp: sugestaoPresentes) {
                System.out.println("Nome do Presente: "+sp.getDescricao()+" | Participante: "+pt.getNome());
                System.out.println("Link do Presente: "+sp.getLink()+" | Participante: "+pt.getNome());
                System.out.println("Categoria: "+sp.getCategoriaPresente().getDescricao()+" | Participante: "+pt.getNome());
                System.out.println("----------------------------------------------");
            }
            System.out.println("###########################################");
        }
}
