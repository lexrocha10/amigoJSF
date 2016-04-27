package util;

import dao.GrupoDao;
import dao.ParticipanteDao;
import dominio.Grupo;
import dominio.Participante;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Rocha
 */
public class ListarParticipantesGrupo {
    public static void main(String[] args) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        GrupoDao dao = new GrupoDao();
        ParticipanteDao dao2 = new ParticipanteDao();
        List<Grupo> grupos = dao.getGrupos();
        System.out.println("###########  Participantes do Grupo  #########");
        for(Grupo gp: grupos) {
            List<Participante> participantes = dao2.getParticipantes(gp);
            System.out.println("-------------------------------------");
            for(Participante pt: participantes) {
                System.out.println("Nome Participante: "+pt.getNome()+" | Grupo: "+gp.getNome());
            }
            System.out.println("###########################################");
        }
    }
    
}
