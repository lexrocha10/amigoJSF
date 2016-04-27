/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Grupo;
import dominio.Participante;
import dominio.Sorteio;
import dominio.TipoGrupo;
import dominio.TipoTelefone;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
@ManagedBean(name = "grupoBean")
@ViewScoped
public class GrupoBean implements Serializable {

    private List<TipoGrupo> tipoGrupos = new ArrayList<>();
    private List<Participante> participantes = new ArrayList<>();
    private List<Grupo> grupos = new ArrayList<>();
    private List<TipoTelefone> tiposTelefone = new ArrayList<>();
    private Grupo grupo = new Grupo();
    private Participante participante = new Participante();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            grupo = (Grupo) daoFactory.getGrupoDao().findById(getId());
        } else {
            grupo = new Grupo();
        }
    }

    public String sortearGrupo(Grupo grupo) {

        DaoFactory daoFactory = new DaoFactory();
        Sorteio sorteio = new Sorteio();
        int tam = participantes.size();
        System.out.println("Entrei");
        if ((tam % 2) == 0) {
            participantes = daoFactory.getParticipanteEspecialistaDao().getParticipantes(grupo);
            Collections.reverse(participantes);
            Collections.shuffle(participantes);
            int ultimoIndex = participantes.size() - 1;
            System.out.println("Passei");
            for (int i = 0; i < participantes.size(); i++) {
                participantes.get(i).setAmigoSecreto(participantes.get(i));
                sorteio.setPartiticante(participantes.get(ultimoIndex));
                sorteio.setTirou(participantes.get(i));
                sorteio.setGrupo(grupo);
                ultimoIndex--;
            }
        } else {
            return "Precisa de mais um participante!";
        }
        return "Sorteado com sucesso!";
    }

    public String cancel() {
        return "grupoView?faces-redirect=true";
    }

    public String salvar() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        DaoFactory daoFactory = new DaoFactory();
        setParticipante(daoFactory.getParticipanteDao().findById((Integer) session.getAttribute("ID_PARTICIPANTE")));
        if (grupo.getId() != null) {
            daoFactory.getGrupoDao().upd(grupo);
        } else {
            grupo.setAdministrador(participante);
            daoFactory.getGrupoDao().add(grupo);
        }
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage("Sucesso!", "Grupo: " + grupo.getNome() + " salvo com sucesso!");
        context.addMessage(null, msg);
        return "grupoView?faces-redirect=true";
    }

    public String entrar(Grupo nGrupo) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        DaoFactory daoFactory = new DaoFactory();
        setParticipante(daoFactory.getParticipanteDao().findById((Integer) session.getAttribute("ID_PARTICIPANTE")));
        System.out.println("Participante: " + participante.getNome());
        if (!nGrupo.getParticipantes().contains(participante)) {
            nGrupo.getParticipantes().add(participante);
            daoFactory.getGrupoDao().upd(nGrupo);
            FacesMessage msg = new FacesMessage("Sucesso!", "Você entrou no grupo " + grupo.getNome() + " com sucesso!");
            fc.addMessage(null, msg);
            return "grupoGeralView?faces-redirect=true";
        }
        if (nGrupo.getParticipantes().contains(participante)) {
            System.out.println("Já entrei");
            FacesMessage msg = new FacesMessage("Erro!", "Você já participa do grupo!");
            fc.addMessage(null, msg);
            return "grupoParticipoView?faces-redirect=true";
        }

        return "grupoParticipoView?faces-redirect=true";
    }

    public String sair(Grupo nGrupo) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        DaoFactory daoFactory = new DaoFactory();
        setParticipante(daoFactory.getParticipanteDao().findById((Integer) session.getAttribute("ID_PARTICIPANTE")));
        System.out.println("Participante: " + participante.getNome());
        if (!nGrupo.getParticipantes().contains(participante)) {
            nGrupo.getParticipantes().remove(participante);
            daoFactory.getGrupoDao().upd(nGrupo);
            FacesMessage msg = new FacesMessage("Sucesso!", "Você entrou no grupo " + grupo.getNome() + " com sucesso!");
            fc.addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Erro!", "Você não participa deste grupo!");
            fc.addMessage(null, msg);
        }

        return "grupoParticipoView?faces-redirect=true";
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TipoGrupo> getTipoGrupos() {
        if (tipoGrupos.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setTipoGrupos(daoFactory.getTipoGrupoDao().listaAll());
        }
        return tipoGrupos;
    }

    public void setTipoGrupos(List<TipoGrupo> tipoGrupos) {
        this.tipoGrupos = tipoGrupos;
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

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }
}
