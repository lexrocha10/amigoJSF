package mb;

import dao.DaoFactory;
import dominio.CategoriaPresente;
import dominio.Grupo;
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
@ManagedBean(name = "sugestaoPresenteBean")
@RequestScoped
public class SugestaoPresenteBean {

    private List<CategoriaPresente> categoriasPresente = new ArrayList<>();
    private List<Grupo> grupos = new ArrayList<>();
    private Participante participante = new Participante();
    private List<Participante> participantes = new ArrayList<>();
    private SugestaoPresente sugestaoPresente = new SugestaoPresente();
    private Integer id;
    private String valorRadio;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            sugestaoPresente = (SugestaoPresente) daoFactory.getSugestaoPresenteDao().findById(getId());
        } else {
            sugestaoPresente = new SugestaoPresente();
        }
    }

    public String cancel() {
        return "sugestaoPresenteView?faces-redirect=true";
    }

    public String salvar() {
        System.out.println("Entrei no Salvar");
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        DaoFactory daoFactory = new DaoFactory();
        if (sugestaoPresente.getId() != null) {
            daoFactory.getSugestaoPresenteDao().upd(sugestaoPresente);
            FacesMessage msg = new FacesMessage("Sugestão " + sugestaoPresente.getDescricao() + " Atualizada com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            setParticipante(daoFactory.getParticipanteDao().findById((Integer) session.getAttribute("ID_PARTICIPANTE")));
            sugestaoPresente.setParticipanteQueSugeriu(participante);
            daoFactory.getSugestaoEspecialistaDao().add(sugestaoPresente);
            FacesMessage msg = new FacesMessage("Sugestão " + sugestaoPresente.getDescricao() + " Salva com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return "sugestaoPresenteView?faces-redirect=true";
    }

    public SugestaoPresente getSugestaoPresente() {
        return sugestaoPresente;
    }

    public void setSugestaoPresente(SugestaoPresente sugestaoPresente) {
        this.sugestaoPresente = sugestaoPresente;
    }

    public List<CategoriaPresente> getCategoriasPresente() {
        if (categoriasPresente.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setCategoriasPresente(daoFactory.getCategoriaPresenteDao().listaAll());
        }
        return categoriasPresente;
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

    public List<Participante> getParticipantes() {
        if (participantes.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setParticipantes(daoFactory.getParticipanteDao().listaAll());
        }
        return participantes;
    }

    public void setCategoriasPresente(List<CategoriaPresente> categoriasPresente) {
        this.categoriasPresente = categoriasPresente;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValorRadio() {
        return valorRadio;
    }

    public void setValorRadio(String valorRadio) {
        this.valorRadio = valorRadio;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

}
