/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.TipoGrupo;
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
@ManagedBean(name = "tipoGrupoViewBean")
@RequestScoped
public class TipoGrupoViewBean {

    private List<TipoGrupo> tipoGrupos = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirTipoGrupo(TipoGrupo a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getTipoGrupoDao().del(a);
        String msg = "Categoria " + a.getDescricao()+ " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "tipoGrupoView?faces-redirect=true";
    }
    public String prepararAdicionarTipoGrupo() {
        return "tipoGrupoIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarTipoGrupo(TipoGrupo tipoGrupo) {
        return "tipoGrupoIncluir?faces-redirect=true&id=" + tipoGrupo.getId();
    }
    
    public void buscarTipoGrupo() {
        DaoFactory daoFactory = new DaoFactory();
        tipoGrupos.clear();
        tipoGrupos = daoFactory.getTipoGrupoDao().JpqlLike(getValorBusca(), "descricao");
        exibe = true;
    }
    
    public List<TipoGrupo> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        tipoGrupos = daoFactory.getTipoGrupoDao().listaAll();
        exibe = false;
        return tipoGrupos;
    }
    
    public List<TipoGrupo> getTipoGrupos() {
        if ((tipoGrupos.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            tipoGrupos = daoFactory.getTipoGrupoDao().listaAll();
        }
        return tipoGrupos;
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
