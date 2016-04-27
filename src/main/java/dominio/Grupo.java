package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Rocha
 */
@Entity
@Table(name = "grupo")

@NamedQueries({
    @NamedQuery(name = "Grupo.findGrupos",
            query = "select g from Grupo g"),
    @NamedQuery(name = "Grupo.meusGrupos",
            query = "select g from Grupo g where g.administrador = :participantePassado"),
    @NamedQuery(name = "Grupo.gruposParticipo",
            query = "select g from Participante p join p.grupos g where p = :participantePassado"),
    @NamedQuery(name = "Grupo.buscarGrupo",
            query = "select g from Participante p join p.grupos g where p = :participantePassado")
})
public class Grupo implements Serializable {

    @Id
    @SequenceGenerator(name = "grupo_id_gen",
            sequenceName = "grupo_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "grupo_id_gen")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrador_id", nullable = true)
    private Participante administrador;

    @Column(name = "nome", length = 60, nullable = false)
    private String nome;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "data_sorteio", length = 12, nullable = false)
    private Date dataSorteio;

    @Column(name = "valor_minimo_presente", length = 5, nullable = false)
    private float valorMinimoPresente;

    @Column(name = "valor_maximo_presente", length = 5, nullable = false)
    private float valorMaximoPresente;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "data_confraternizacao", length = 12, nullable = false)
    private Date dataConfraternizacao;

    @Temporal(javax.persistence.TemporalType.TIME)
    @Column(name = "hora_confraternizacao", nullable = false)
    private Date horaConfraternizacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipogrupo_id", nullable = true)
    private TipoGrupo tipoGrupo;

    @ManyToMany(fetch = FetchType.EAGER ,cascade=CascadeType.REMOVE)
    @JoinTable(name = "grupo_participante", joinColumns
            = @JoinColumn(name = "grupo_id"), inverseJoinColumns
            = @JoinColumn(name = "participante_id"))
    private List<Participante> participantes = new ArrayList<>();
    //mappedBy referencia o nome da variável na outra classe
    @OneToMany(mappedBy = "grupoDestino", targetEntity = Mensagem.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Mensagem> mensagens;

    @OneToMany(mappedBy = "grupo", targetEntity = SugestaoPresente.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<SugestaoPresente> listaDesejos;

    @Column(name = "observacoes", length = 300, nullable = true)
    private String observacoes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Participante getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Participante administrador) {
        this.administrador = administrador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataSorteio() {
        return dataSorteio;
    }

    public void setDataSorteio(Date dataSorteio) {
        this.dataSorteio = dataSorteio;
    }

    public float getValorMinimoPresente() {
        return valorMinimoPresente;
    }

    public void setValorMinimoPresente(float valorMinimoPresente) {
        this.valorMinimoPresente = valorMinimoPresente;
    }

    public float getValorMaximoPresente() {
        return valorMaximoPresente;
    }

    public void setValorMaximoPresente(float valorMaximoPresente) {
        this.valorMaximoPresente = valorMaximoPresente;
    }

    public Date getDataConfraternizacao() {
        return dataConfraternizacao;
    }

    public void setDataConfraternizacao(Date dataConfraternizacao) {
        this.dataConfraternizacao = dataConfraternizacao;
    }

    public Date getHoraConfraternizacao() {
        return horaConfraternizacao;
    }

    public void setHoraConfraternizacao(Date horaConfraternizacao) {
        this.horaConfraternizacao = horaConfraternizacao;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    public List<SugestaoPresente> getListaDesejos() {
        return listaDesejos;
    }

    public void setListaDesejos(List<SugestaoPresente> listaDesejos) {
        this.listaDesejos = listaDesejos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Grupo other = (Grupo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the tipoGrupo
     */
    public TipoGrupo getTipoGrupo() {
        return tipoGrupo;
    }

    /**
     * @param tipoGrupo the tipoGrupo to set
     */
    public void setTipoGrupo(TipoGrupo tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    /**
     * @return the observacoes
     */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * @param observacoes the observacoes to set
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

}
