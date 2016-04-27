package mb;

import dao.DaoFactory;
import dominio.TipoGrupo;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "tipoGrupoBean")
@RequestScoped
public class TipoGrupoBean {

    private TipoGrupo tipoGrupo = new TipoGrupo();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            tipoGrupo = (TipoGrupo) daoFactory.getTipoGrupoDao().findById(getId());
        } else {
            tipoGrupo = new TipoGrupo();
        }
    }

    public String cancel() {
        return "/sec/tipoGrupoView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (tipoGrupo.getId() != null) {
            daoFactory.getTipoGrupoDao().upd(tipoGrupo);
            FacesMessage msg = new FacesMessage("Categoria " + tipoGrupo.getDescricao() + " Atualizada com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            daoFactory.getTipoGrupoDao().add(tipoGrupo);
            FacesMessage msg = new FacesMessage("Categoria " + tipoGrupo.getDescricao() + " Salva com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return "tipoGrupoView?faces-redirect=true";
    }

    public TipoGrupo getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(TipoGrupo tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
