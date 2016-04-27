/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.CategoriaPresente;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "categoriaPresenteViewBean")
@RequestScoped
public class CategoriaPresenteViewBean {

    private List<CategoriaPresente> categoriaPresentes = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirCategoriaPresente(CategoriaPresente a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getCategoriaPresenteDao().del(a);
        String msg = "Categoria " + a.getDescricao()+ " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "categoriaPresenteView?faces-redirect=true";
    }
    public String prepararAdicionarCategoriaPresente() {
        return "categoriaPresenteIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarCategoriaPresente(CategoriaPresente categoriaPresente) {
        return "categoriaPresenteIncluir?faces-redirect=true&id=" + categoriaPresente.getId();
    }
    
    public void buscarCategoriaPresente() {
        DaoFactory daoFactory = new DaoFactory();
        categoriaPresentes.clear();
        categoriaPresentes = daoFactory.getCategoriaPresenteDao().JpqlLike(getValorBusca(), "descricao");
        exibe = true;
    }
    
    public List<CategoriaPresente> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        categoriaPresentes = daoFactory.getCategoriaPresenteDao().listaAll();
        exibe = false;
        return categoriaPresentes;
    }
    
    public List<CategoriaPresente> getCategoriaPresentes() {
        if ((categoriaPresentes.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            categoriaPresentes = daoFactory.getCategoriaPresenteDao().listaAll();
        }
        return categoriaPresentes;
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
}
