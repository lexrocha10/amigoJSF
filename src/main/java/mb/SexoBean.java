package mb;

import dao.DaoFactory;
import dominio.Sexo;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "sexoBean")
@RequestScoped
public class SexoBean {

    private Sexo sexo = new Sexo();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            sexo = (Sexo) daoFactory.getSexoDao().findById(getId());
        } else {
            sexo = new Sexo();
        }
    }

    public String cancel() {
        return "sexoView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (sexo.getId() != null) {
            daoFactory.getSexoDao().upd(sexo);
            FacesMessage msg = new FacesMessage("Sexo " + sexo.getDescricao() + " atualizado com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            daoFactory.getSexoDao().add(sexo);
            FacesMessage msg = new FacesMessage("Sexo " + sexo.getDescricao() + " salvo com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return "sexoView?faces-redirect=true";
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
