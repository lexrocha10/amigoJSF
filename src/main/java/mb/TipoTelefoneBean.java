package mb;

import dao.DaoFactory;
import dominio.TipoTelefone;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "tipoTelefoneBean")
@RequestScoped
public class TipoTelefoneBean {

    private TipoTelefone tipoTelefone = new TipoTelefone();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            tipoTelefone = (TipoTelefone) daoFactory.getTipoTelefoneDao().findById(getId());
        } else {
            tipoTelefone = new TipoTelefone();
        }
    }

    public String cancel() {
        return "tipoTelefoneView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (tipoTelefone.getId() != null) {
            daoFactory.getTipoTelefoneDao().upd(tipoTelefone);
            FacesMessage msg = new FacesMessage("Categoria " + tipoTelefone.getDescricao() + " Atualizada com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            daoFactory.getTipoTelefoneDao().add(tipoTelefone);
            FacesMessage msg = new FacesMessage("Categoria " + tipoTelefone.getDescricao() + " Salva com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return "tipoTelefoneView?faces-redirect=true";
    }

    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
