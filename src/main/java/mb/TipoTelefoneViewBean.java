/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.TipoTelefone;
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
@ManagedBean(name = "tipoTelefoneViewBean")
@RequestScoped
public class TipoTelefoneViewBean {

    private List<TipoTelefone> tiposTelefone = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirTipoTelefone(TipoTelefone a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getTipoTelefoneDao().del(a);
        String msg = "Categoria " + a.getDescricao()+ " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "tipoTelefoneView?faces-redirect=true";
    }
    public String prepararAdicionarTipoTelefone() {
        return "tipoTelefoneIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarTipoTelefone(TipoTelefone tipoTelefone) {
        return "tipoTelefoneIncluir?faces-redirect=true&id=" + tipoTelefone.getId();
    }
    
    public void buscarTipoTelefone() {
        DaoFactory daoFactory = new DaoFactory();
        tiposTelefone.clear();
        tiposTelefone = daoFactory.getTipoTelefoneDao().JpqlLike(getValorBusca(), "descricao");
        exibe = true;
    }
    
    public List<TipoTelefone> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        tiposTelefone = daoFactory.getTipoTelefoneDao().listaAll();
        exibe = false;
        return tiposTelefone;
    }
    
    public List<TipoTelefone> getTiposTelefone() {
        if ((tiposTelefone.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            tiposTelefone = daoFactory.getTipoTelefoneDao().listaAll();
        }
        return tiposTelefone;
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
