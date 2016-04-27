/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Mensagem;
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
@ManagedBean(name = "mensagemViewBean")
@RequestScoped
public class MensagemViewBean {

    private List<Mensagem> mensagens = new ArrayList<>();
    private List<Mensagem> minhasMensagens = new ArrayList<>();
    private List<Mensagem> minhasMensagensEnviadas = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;
    private Mensagem mensagemSelecionada;
    private Participante participante;
    

    public String excluirMensagem() {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getMensagemDao().del(mensagemSelecionada);
        String msg = "Mensagem excluída com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        mensagemSelecionada = null;
        return "mensagemView?faces-redirect=true";
    }
    public String prepararAdicionarMensagem() {
        return "enviarMensagem?faces-redirect=true&id=-1";
    }
    
    public String prepararMensagensRecebidas() {
        return "mensagemView?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarMensagem(Mensagem mensagem) {
        return "enviarMensagem?faces-redirect=true&id=" + mensagem.getId();
    }
    
    public void buscarMensagem() {
        DaoFactory daoFactory = new DaoFactory();
        mensagens.clear();
        mensagens = daoFactory.getMensagemDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<Mensagem> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        mensagens = daoFactory.getMensagemDao().listaAll();
        exibe = false;
        return mensagens;
    }
    
    public List<Mensagem> getMensagens() {
        if ((mensagens.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            mensagens = daoFactory.getMensagemDao().listaAll();
        }
        return mensagens;
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

    public Mensagem getMensagemSelecionada() {
        return mensagemSelecionada;
    }

    public void setMensagemSelecionada(Mensagem mensagemSelecionada) {
        this.mensagemSelecionada = mensagemSelecionada;
    }

    public List<Mensagem> getMinhasMensagens() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        if ((minhasMensagens.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            setParticipante(daoFactory.getParticipanteDao().findById((Integer) session.getAttribute("ID_PARTICIPANTE")));
            minhasMensagens = daoFactory.getMensagemEspecialistaDao().getMinhasMensagens(getParticipante());
        }
        return minhasMensagens;
    }

    public void setMinhasMensagens(List<Mensagem> minhasMensagens) {
        this.minhasMensagens = minhasMensagens;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public List<Mensagem> getMinhasMensagensEnviadas() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        if ((minhasMensagensEnviadas.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            setParticipante(daoFactory.getParticipanteDao().findById((Integer) session.getAttribute("ID_PARTICIPANTE")));
            minhasMensagensEnviadas = daoFactory.getMensagemEspecialistaDao().getMinhasMensagensEnviadas(getParticipante());
        }
        return minhasMensagensEnviadas;
    }

    public void setMinhasMensagensEnviadas(List<Mensagem> minhasMensagensEnviadas) {
        this.minhasMensagensEnviadas = minhasMensagensEnviadas;
    }
}
