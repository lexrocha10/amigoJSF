package dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Rocha
 */
@Entity
@Table(name="mensagem")

@NamedQueries({
    @NamedQuery(name = "Mensagem.findMensagemByGrupo",
            query = "select m from Mensagem m where m.grupoDestino = :grupoPassado"),
    @NamedQuery(name = "Mensagem.findMensagemByParticipante",
            query = "select m from Mensagem m where m.amigoDestino = :pPassado"),
    @NamedQuery(name = "Mensagem.findMensagemEnviadaByParticipante",
            query = "select m from Mensagem m where m.remetente = :pPassado")
})
public class Mensagem implements Serializable {
    @Id
    @SequenceGenerator(name = "mensagem_id_gen",
            sequenceName = "mensagem_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "mensagem_id_gen")
    private Integer id;
    @Column(name = "titulo", length = 50, nullable = false)
    private String titulo;
    @Column(name = "corpo_mensagem", length = 3000, nullable = false)
    private String corpoMensagem;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "data_envio", length = 12, nullable = false)
    private Date dataEnvio;
    
    @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = false)
    private Participante remetente;
    /*dica de envio:
    Se o tipo da mensagens for para todos, enviar automaticante para todos do grupo ao qual o
    remetente pertence.
    caso seja para uma pessoa específica: listar usuários do grupo a qual ele pertece e enviar a mensagem.
    /*
    */
    //verificar o método de envio das mensagens
    //private List<Participante> destinatarios;
    
    
    //anonimo ou mostrando a identidade
    private Boolean publicaBoolean;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="grupo_id",nullable = true)
    private Grupo grupoDestino;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="amigo_id",nullable = true)
    private Participante amigoDestino;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorpoMensagem() {
        return corpoMensagem;
    }

    public void setCorpoMensagem(String corpoMensagem) {
        this.corpoMensagem = corpoMensagem;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Participante getRemetente() {
        return remetente;
    }

    public void setRemetente(Participante remetente) {
        this.remetente = remetente;
    }

    public Grupo getGrupoDestino() {
        return grupoDestino;
    }

    public void setGrupoDestino(Grupo grupoDestino) {
        this.grupoDestino = grupoDestino;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mensagem other = (Mensagem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Participante getAmigoDestino() {
        return amigoDestino;
    }

    public void setAmigoDestino(Participante amigoDestino) {
        this.amigoDestino = amigoDestino;
    }

    public Boolean getPublicaBoolean() {
        return publicaBoolean;
    }

    public void setPublicaBoolean(Boolean publicaBoolean) {
        this.publicaBoolean = publicaBoolean;
    }
    
    
}
