/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Participante;
import dominio.SugestaoPresente;
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
@ManagedBean(name = "sugestaoPresenteViewBean")
@RequestScoped
public class SugestaoPresenteViewBean {

    private List<SugestaoPresente> sugestaoPresentes = new ArrayList<>();
    private List<SugestaoPresente> sugestaoPresentesRecebidas = new ArrayList<>();
    private Participante participante = new Participante();
    private String valorBusca;
    private boolean exibe = false;
    

    public String excluirSugestaoPresente(SugestaoPresente a) {
        System.out.println("Entrei no excluir");
        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getSugestaoPresenteDao().del(a);
        String msg = "Sugestão " + a.getDescricao() + " excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "sugestaoPresenteView?faces-redirect=true";
    }

    public String prepararAdicionarSugestaoPresente() {
        return "sugestaoPresenteIncluir?faces-redirect=true&id=-1";
    }

    public String prepararAlterarSugestaoPresente(SugestaoPresente sugestaoPresente) {
        return "sugestaoPresenteIncluir?faces-redirect=true&id=" + sugestaoPresente.getId();
    }
    
    public String prepararSugestoesRecebidas() {
        return "sugestaoPresenteRecebidaView?faces-redirect=true&id=-1";
    }

    public void buscarSugestaoPresente() {
        DaoFactory daoFactory = new DaoFactory();
        sugestaoPresentes.clear();
        sugestaoPresentes = daoFactory.getSugestaoPresenteDao().JpqlLike(getValorBusca(), "descricao");
        exibe = true;
    }

    public List<SugestaoPresente> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        sugestaoPresentes = daoFactory.getSugestaoPresenteDao().listaAll();
        exibe = false;
        return sugestaoPresentes;
    }

    public List<SugestaoPresente> getSugestaoPresentes() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        if ((sugestaoPresentes.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            setParticipante(daoFactory.getParticipanteDao().findById((Integer) session.getAttribute("ID_PARTICIPANTE")));
            sugestaoPresentes = daoFactory.getSugestaoEspecialistaDao().getMinhasSugestoes(participante);
        }
        return sugestaoPresentes;
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

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public List<SugestaoPresente> getSugestaoPresentesRecebidas() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        if ((sugestaoPresentesRecebidas.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            setParticipante(daoFactory.getParticipanteDao().findById((Integer) session.getAttribute("ID_PARTICIPANTE")));
            sugestaoPresentesRecebidas = daoFactory.getSugestaoEspecialistaDao().getMinhasSugestoesRecebidas(participante);
        }
        return sugestaoPresentesRecebidas;
    }

    public void setSugestaoPresentesRecebidas(List<SugestaoPresente> sugestaoPresentesRecebidas) {
        this.sugestaoPresentesRecebidas = sugestaoPresentesRecebidas;
    }
}
