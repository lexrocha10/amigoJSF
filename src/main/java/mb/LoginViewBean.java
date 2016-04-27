/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Login;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "loginViewBean")
@RequestScoped
public class LoginViewBean {

    private List<Login> logins = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String prepararAdicionarLogin() {
        return "loginIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarLogin(Login login) {
        return "loginIncluir?faces-redirect=true&id=" + login.getId();
    }
    
    public void buscarLogin() {
        DaoFactory daoFactory = new DaoFactory();
        logins.clear();
        logins = daoFactory.getLoginDao().JpqlLike(getValorBusca(), "descricao");
        exibe = true;
    }
    
    public List<Login> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        logins = daoFactory.getLoginDao().listaAll();
        exibe = false;
        return logins;
    }
    
    public List<Login> getLogins() {
        if ((logins.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            logins = daoFactory.getLoginDao().listaAll();
        }
        return logins;
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
