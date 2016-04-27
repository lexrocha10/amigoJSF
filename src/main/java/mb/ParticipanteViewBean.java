/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Participante;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "participanteViewBean")
@RequestScoped
public class ParticipanteViewBean {

    private List<Participante> participantes = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;
    private Participante participanteSelecionado;
    private Participante participante = new Participante();
    

    public String excluirParticipante() {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getParticipanteDao().del(participanteSelecionado);
        String msg = "Participante: " + participanteSelecionado.getNome() + " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        participanteSelecionado = null;
        return "participanteView?faces-redirect=true";
    }
    public String prepararAdicionarParticipante() {
        return "participanteIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarParticipante() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        return "participanteIncluir?faces-redirect=true&id=" +
                (Integer) session.getAttribute("ID_PARTICIPANTE");
    }
    
    public void buscarParticipante() {
        DaoFactory daoFactory = new DaoFactory();
        participantes.clear();
        participantes = daoFactory.getParticipanteDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<Participante> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        participantes = daoFactory.getParticipanteDao().listaAll();
        exibe = false;
        return participantes;
    }
    
    public List<Participante> getParticipantes() {
        if ((participantes.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            participantes = daoFactory.getParticipanteDao().listaAll();
        }
        return participantes;
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

    public Participante getParticipanteSelecionado() {
        return participanteSelecionado;
    }

    public void setParticipanteSelecionado(Participante participanteSelecionado) {
        this.participanteSelecionado = participanteSelecionado;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }
}
