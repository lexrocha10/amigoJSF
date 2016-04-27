/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Sexo;
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
@ManagedBean(name = "sexoViewBean")
@RequestScoped
public class SexoViewBean {

    private List<Sexo> sexos = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirSexo(Sexo a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getSexoDao().del(a);
        String msg = "Categoria " + a.getDescricao()+ " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "sexoView?faces-redirect=true";
    }
    public String prepararAdicionarSexo() {
        return "sexoIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarSexo(Sexo sexo) {
        return "sexoIncluir?faces-redirect=true&id=" + sexo.getId();
    }
    
    public void buscarSexo() {
        DaoFactory daoFactory = new DaoFactory();
        sexos.clear();
        sexos = daoFactory.getSexoDao().JpqlLike(getValorBusca(), "descricao");
        exibe = true;
    }
    
    public List<Sexo> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        sexos = daoFactory.getSexoDao().listaAll();
        exibe = false;
        return sexos;
    }
    
    public List<Sexo> getSexos() {
        if ((sexos.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            sexos = daoFactory.getSexoDao().listaAll();
        }
        return sexos;
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
