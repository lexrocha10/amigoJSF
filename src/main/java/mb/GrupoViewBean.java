/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Grupo;
import dominio.Participante;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "grupoViewBean")
@ViewScoped
public class GrupoViewBean {

    private List<Grupo> grupos = new ArrayList<>();
    private List<Grupo> gruposGeral = new ArrayList<>();
    private List<Grupo> gruposParticipo = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;
    private Participante participante = new Participante();
    private Grupo grupoSelecionado; 

    public String excluirGrupo(Grupo eGrupo) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getGrupoDao().del(eGrupo);
        String msg = "Grupo: " + eGrupo.getNome() + " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "grupoView?faces-redirect=true";
    }
    
    public String prepararAdicionarGrupo() {
        return "grupoIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararGrupoGeral() {
        return "grupoGeralView?faces-redirect=true";
    }
    
    public String prepararAlterarGrupo(Grupo grupo) {
        return "grupoIncluir?faces-redirect=true&id=" + grupo.getId();
    }
    
    public void buscarGrupo() {
        DaoFactory daoFactory = new DaoFactory();
        grupos.clear();
        grupos = daoFactory.getGrupoDao().JpqlLike(getValorBusca(),"nome");
        exibe = true;
    }
    
    public List<Grupo> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        grupos = daoFactory.getGrupoDao().listaAll();
        exibe = false;
        return grupos;
    }
    
    public void buscarGrupoGeral() {
        DaoFactory daoFactory = new DaoFactory();
        gruposGeral.clear();
        gruposGeral = daoFactory.getGrupoEspecialistaDao().getGrupoNome(getValorBusca());
        System.out.println("Buscando por: "+getValorBusca());
        exibe = true;
    }
    
    public List<Grupo> buscarTodosGeral() {
        DaoFactory daoFactory = new DaoFactory();
        gruposGeral.clear();
        gruposGeral = daoFactory.getGrupoDao().listaAll();
        exibe = false;
        return gruposGeral;
    }
    
    public void buscarMeusGrupos() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        DaoFactory daoFactory = new DaoFactory();
        setParticipante(daoFactory.getParticipanteDao().findById((Integer) session.getAttribute("ID_PARTICIPANTE")));
        grupos.clear();
        grupos = daoFactory.getGrupoEspecialistaDao().JpqlLike(getValorBusca(),participante);
        exibe = true;
    }
    
    public List<Grupo> buscarTodosMeus() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        if ((grupos.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            setParticipante(daoFactory.getParticipanteDao().findById((Integer) session.getAttribute("ID_PARTICIPANTE")));
            grupos = daoFactory.getGrupoEspecialistaDao().getMeusGrupos(participante);
        }
        exibe = false;
        return grupos;
    }
    
    public List<Grupo> getGrupos() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        if ((grupos.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            setParticipante(daoFactory.getParticipanteDao().findById((Integer) session.getAttribute("ID_PARTICIPANTE")));
            grupos = daoFactory.getGrupoEspecialistaDao().getMeusGrupos(participante);
        }
        return grupos;
    }
    
    public String getValorBusca() {
        return valorBusca;
    }
    
    public void setValorBusca(String valorBusca) {
        this.valorBusca = valorBusca;
    }
    
    public boolean isExibe() {
        return exibe;
    }

    public Grupo getGrupoSelecionado() {
        return grupoSelecionado;
    }

    public void setGrupoSelecionado(Grupo grupoSelecionado) {
        this.grupoSelecionado = grupoSelecionado;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public List<Grupo> getGruposGeral() {
        if ((gruposGeral.isEmpty())) {
            DaoFactory daoFactory = new DaoFactory();
            gruposGeral = daoFactory.getGrupoDao().listaAll();
        }
        return gruposGeral;
    }

    public void setGruposGeral(List<Grupo> gruposGeral) {
        this.gruposGeral = gruposGeral;
    }
    
    public List<Grupo> getGruposParticipo() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        if ((gruposParticipo.isEmpty())) {
            DaoFactory daoFactory = new DaoFactory();
            setParticipante(daoFactory.getParticipanteDao().findById((Integer) session.getAttribute("ID_PARTICIPANTE")));
            gruposParticipo = daoFactory.getGrupoEspecialistaDao().getGruposParticipo(participante);
        }
        return gruposParticipo;
    }

    public void setGruposParticipo(List<Grupo> gruposParticipo) {
        this.gruposParticipo = gruposParticipo;
    }

}
