package util;

import dao.GrupoDao;
import dao.MensagemDao;
import dominio.Grupo;
import dominio.Mensagem;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Rocha
 */
public class ListarMensagensGrupo {
    public static void main(String[] args) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        GrupoDao dao = new GrupoDao();
        MensagemDao dao2 = new MensagemDao();
        List<Grupo> grupos = dao.getGrupos();
        System.out.println("###########  Mensagens do Grupo  #########");
        for(Grupo gp: grupos) {
            List<Mensagem> mensagens = dao2.getMensagens(gp);
            for(Mensagem ms: mensagens) {
                System.out.println("-------------------------------------");
                System.out.println("Mensagem: "+ms.getCorpoMensagem()+" | Grupo: "+gp.getNome());
                System.out.println("Data Envio: "+df.format(ms.getDataEnvio())+" | Grupo: "+gp.getNome());
                if(ms.getPublicaBoolean() == false) {
                    System.out.println("Remetente é anônimo | Grupo: "+gp.getNome());
                }else{
                    System.out.println("Remetente: "+ms.getRemetente().getNome()+" | Grupo: "+gp.getNome());
                }
            }
            System.out.println("###########################################");
        }
    }
    
}
