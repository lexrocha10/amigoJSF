package dao;

import dominio.CategoriaPresente;
import dominio.Grupo;
import dominio.Login;
import dominio.Mensagem;
import dominio.Participante;
import dominio.SugestaoPresente;
import dominio.TipoGrupo;
import dominio.Sexo;
import dominio.TipoTelefone;


public class DaoFactory {
    
    //fabrica de dao's genericos
    public DaoGenerico<Grupo> getGrupoDao() {
        return new DaoGenerico<>(Grupo.class);
    }
    
    public DaoGenerico<Mensagem> getMensagemDao() {
        return new DaoGenerico<>(Mensagem.class);
    }
    
    public DaoGenerico<Participante> getParticipanteDao() {
        return new DaoGenerico<>(Participante.class);
    }
    
    public DaoGenerico<SugestaoPresente> getPresenteDao(){
        return new DaoGenerico<>(SugestaoPresente.class);
    }
    
    public DaoGenerico<CategoriaPresente> getCategoriaPresenteDao(){
        return new DaoGenerico<>(CategoriaPresente.class);
    }
    
    public DaoGenerico<SugestaoPresente> getSugestaoPresenteDao(){
        return new DaoGenerico<>(SugestaoPresente.class);
    }
    
    public DaoGenerico<TipoGrupo> getTipoGrupoDao(){
        return new DaoGenerico<>(TipoGrupo.class);
    }
    
    public DaoGenerico<TipoTelefone> getTipoTelefoneDao(){
        return new DaoGenerico<>(TipoTelefone.class);
    }
    
    public DaoGenerico<Sexo> getSexoDao(){
        return new DaoGenerico<>(Sexo.class);
    }
    
    public DaoGenerico<Login> getLoginDao(){
        return new DaoGenerico<>(Login.class);
    }
    
    //Dao especializados   
    
    public LoginDao getLoginEspecialistaDao() {
        return new LoginDao();
    }
    
    public MensagemDao getMensagemEspecialistaDao() {
        return new MensagemDao();
    }
    
    public GrupoDao getGrupoEspecialistaDao() {
        return new GrupoDao();
    }
    
    public ParticipanteDao getParticipanteEspecialistaDao() {
        return new ParticipanteDao();
    }
    
    public SugestaoPresenteDao getSugestaoEspecialistaDao() {
        return new SugestaoPresenteDao();
    }

}
