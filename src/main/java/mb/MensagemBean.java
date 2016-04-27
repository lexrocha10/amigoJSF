/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Grupo;
import dominio.Mensagem;
import dominio.Participante;
import dominio.TipoTelefone;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "mensagemBean")
@SessionScoped
public class MensagemBean implements Serializable {

    private List<Mensagem> mensagens = new ArrayList<>();
    private List<Grupo> grupos = new ArrayList<>();
    private List<Participante> participantes = new ArrayList<>();
    private List<TipoTelefone> tiposTelefone = new ArrayList<>();
    private Mensagem mensagem = new Mensagem();
    private String valorRadio;
    private Participante participante = new Participante();
    private Date currentDate;
    private Integer id;
    private Integer idUsuario;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            mensagem = (Mensagem) daoFactory.getMensagemDao().findById(getId());
        } else {
            mensagem = new Mensagem();
        }
    }

    @PostConstruct
    public void init() {
        setCurrentDate(new Date());
    }

    public String cancel() {
        return "mensagemView?faces-redirect=true";
    }

    public String enviar() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        DaoFactory daoFactory = new DaoFactory();
        System.out.println("ENTREI 1!!!!!!");
        if (mensagem.getGrupoDestino() == null
                && mensagem.getAmigoDestino() == null) {
            FacesMessage msg = new FacesMessage("Erro!", "Selecione um destinatário!");
            fc.addMessage(null, msg);
            return "enviarMensagem?faces-redirect=true";
        }
        System.out.println("SAI 1!!!!!!");
        if (mensagem.getId() != null) {
            mensagem.setDataEnvio(getCurrentDate());
            daoFactory.getMensagemDao().upd(mensagem);
        } else {
            System.out.println("ENTREI 2!!!!!!!");
            mensagem.setRemetente(daoFactory.getParticipanteDao().findById((Integer) session.getAttribute("ID_PARTICIPANTE")));
            mensagem.setDataEnvio(getCurrentDate());
            daoFactory.getMensagemEspecialistaDao().add(mensagem);
        }
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage("Sucesso!", "Mensagem enviada com sucesso!");
        context.addMessage(null, msg);
        return "mensagemView?faces-redirect=true";
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TipoTelefone> getTiposTelefone() {
        if (tiposTelefone.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setTiposTelefone(daoFactory.getTipoTelefoneDao().listaAll());
        }
        return tiposTelefone;
    }

    public void setTiposTelefone(List<TipoTelefone> tiposTelefone) {
        this.tiposTelefone = tiposTelefone;
    }

    public List<Mensagem> getMensagens() {
        if (mensagens.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setMensagens(daoFactory.getMensagemDao().listaAll());
        }
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    public String getValorRadio() {
        return valorRadio;
    }

    public void setValorRadio(String valorRadio) {
        this.valorRadio = valorRadio;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Grupo> getGrupos() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        if (grupos.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setParticipante(daoFactory.getParticipanteDao().findById((Integer) session.getAttribute("ID_PARTICIPANTE")));
            grupos = daoFactory.getGrupoEspecialistaDao().getGruposParticipo(getParticipante());
        }
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public List<Participante> getParticipantes() {
         if(participantes.isEmpty()) {
            DaoFactory df = new DaoFactory();
            participantes = df.getParticipanteDao().listaAll();
        }
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }
}
