package util;

import dominio.Grupo;
import dominio.Mensagem;
import dominio.Participante;
import java.text.ParseException;
import java.util.Date;
import javax.persistence.EntityManager;

public class EnviarMensagem_Final {

    public static void main(String[] args) throws ParseException {

        Mensagem mensagem = new Mensagem();
        EntityManager em = JpaUtil2.getEntityManager();
        mensagem.setTitulo("Testando");
        mensagem.setCorpoMensagem("Enviando mensagem anônima para o grupo");
        Date agora = new Date(System.currentTimeMillis());
        mensagem.setDataEnvio(agora);
        //mensagem.setGrupoDestino(em.find(Grupo.class,13));
        mensagem.setAmigoDestino(em.find(Participante.class,35));
        mensagem.setRemetente(em.find(Participante.class,1));
        
        mensagem.setPublicaBoolean(Boolean.FALSE);
        //mensagem anônima
        //mensagem.setTipoMensagem(em.find(TipoMensagem.class, 1));
        
        try {
            em.getTransaction().begin();
            em.persist(mensagem);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}