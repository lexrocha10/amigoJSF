/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Grupo;
import dominio.Login;
import dominio.Participante;
import dominio.Sexo;
import dominio.TipoTelefone;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
@ManagedBean(name = "participanteBean")
@ViewScoped
public class ParticipanteBean implements Serializable {

    private List<Sexo> sexos = new ArrayList<>();
    private List<Grupo> grupos = new ArrayList<>();
    private List<TipoTelefone> tiposTelefone = new ArrayList<>();
    private Participante participante = new Participante();
    private Integer id;
    //private Integer idLogin;
    private Login login = new Login();

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            setParticipante((Participante) daoFactory.getParticipanteDao().findById(getId()));
        } else {
            participante = new Participante();
        }
    }

    public String cancel() {
        return "participanteView?faces-redirect=true";
    }

    public String salvar() {
        DaoFactory daoFactory = new DaoFactory();
        FacesContext fc = FacesContext.getCurrentInstance();

        if (participante.getId() != null) {
            daoFactory.getParticipanteDao().upd(participante);
        } else {
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            //busca o login na sessao e salva no participante
            System.out.println("###### PASSEI 1 #######");
            setLogin(daoFactory.getLoginDao().findById((Integer) session.getAttribute("ID_LOGIN")));
            System.out.println("Login Usuario: "+getLogin().getUsuario());
            participante.setLogin(login);
            System.out.println("###### PASSEI 2 #######");
            daoFactory.getParticipanteDao().upd(participante);
        }
        FacesMessage msg = new FacesMessage("Sucesso!", "Participante: " + getParticipante().getNome() + " salvo com sucesso!");
        fc.addMessage(null, msg);
        fc.getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Sexo> getSexos() {
        if (sexos.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setSexos(daoFactory.getSexoDao().listaAll());
        }
        return sexos;
    }

    public void setSexos(List<Sexo> sexos) {
        this.sexos = sexos;
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

    public List<Grupo> getGrupos() {
        if (grupos.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setGrupos(daoFactory.getGrupoDao().listaAll());
        }
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
