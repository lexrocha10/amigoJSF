package mb;

import dao.DaoFactory;
import dominio.CategoriaPresente;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "categoriaPresenteBean")
@RequestScoped
public class CategoriaPresenteBean {

    private CategoriaPresente categoriaPresente = new CategoriaPresente();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            categoriaPresente = (CategoriaPresente) daoFactory.getCategoriaPresenteDao().findById(getId());
        } else {
            categoriaPresente = new CategoriaPresente();
        }
    }

    public String cancel() {
        return "categoriaPresenteView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (categoriaPresente.getId() != null) {
            daoFactory.getCategoriaPresenteDao().upd(categoriaPresente);
            FacesMessage msg = new FacesMessage("Categoria " + categoriaPresente.getDescricao() + " Atualizada com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            daoFactory.getCategoriaPresenteDao().add(categoriaPresente);
            FacesMessage msg = new FacesMessage("Categoria " + categoriaPresente.getDescricao() + " Salva com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return "categoriaPresenteView?faces-redirect=true";
    }

    public CategoriaPresente getCategoriaPresente() {
        return categoriaPresente;
    }

    public void setCategoriaPresente(CategoriaPresente categoriaPresente) {
        this.categoriaPresente = categoriaPresente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
