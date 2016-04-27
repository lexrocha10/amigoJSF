package util;

import dao.GrupoDao;
import dominio.Grupo;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Rocha
 */
public class ListarGrupos {
    public static void main(String[] args) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        GrupoDao dao = new GrupoDao();
        List<Grupo> grupos = dao.getGrupos();
        System.out.println("###########################################");
        for(Grupo gp: grupos) {
            System.out.println("ID Grupo: "+gp.getId());
            System.out.println("Nome: "+gp.getNome());
            System.out.println("Administrador: "+gp.getAdministrador().getNome());
            System.out.println("Data Sorteio: "+df.format(gp.getDataSorteio()));
            System.out.println("Data Confraternização: "+df.format(gp.getDataConfraternizacao()));
            System.out.println("Horário: "+gp.getHoraConfraternizacao());
            System.out.println("Valor minimo presente: "+gp.getValorMinimoPresente());
            System.out.println("Valor máximo presente: "+gp.getValorMaximoPresente());
            System.out.println("Tipo de Grupo: "+gp.getTipoGrupo().getDescricao());
            System.out.println("Observações: "+gp.getObservacoes());
            System.out.println("###########################################");
        }
    }
}
