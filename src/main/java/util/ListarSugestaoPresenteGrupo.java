/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.GrupoDao;
import dao.SugestaoPresenteDao;
import dominio.Grupo;
import dominio.SugestaoPresente;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Rocha
 */
public class ListarSugestaoPresenteGrupo {
    public static void main(String[] args) throws Exception {
        GrupoDao dao = new GrupoDao();
        SugestaoPresenteDao dao2 = new SugestaoPresenteDao();
        List<Grupo> grupos = dao.getGrupos();
        System.out.println("###########  Sugestões de Presente por Grupo  #########");
        for(Grupo gp: grupos) {
            List<SugestaoPresente> sugestaoPresentes = dao2.getSugestaoPresentesGrupo(gp);
            for(SugestaoPresente sp: sugestaoPresentes) {
                System.out.println("----------------------------------------------");
                System.out.println("Nome do Presente: "+sp.getDescricao()+" | Grupo: "+gp.getNome());
                System.out.println("Link do Presente: "+sp.getLink()+" | Grupo: "+gp.getNome());
                System.out.println("Categoria: "+sp.getCategoriaPresente().getDescricao()+" | Grupo: "+gp.getNome());
            }
            System.out.println("###########################################");
        }
    }
}
