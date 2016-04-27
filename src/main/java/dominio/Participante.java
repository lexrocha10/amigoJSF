package dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Rocha
 */
@Entity
        @Table(name = "participante")



@NamedQueries({
    @NamedQuery(name = "Participante.findParticipanteByGrupo",
            query = "select p from Participante p where p.grupos = :grupoPassado"),
    @NamedQuery(name = "Participante.findParticipanteByLogin",
            query = "select p from Participante p where p.login = :loginPassado")
})
public class Participante implements Serializable {

    @Id
    @SequenceGenerator(name = "participante_id_gen",
            sequenceName = "participante_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "participante_id_gen")
    private Integer id;
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;
    @Column(name = "cpf", length = 15, nullable = false, unique = true)
    private String cpf;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sexo_id", nullable = true)
    private Sexo sexo;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "data_nascimento", length = 12, nullable = false)
    private Date dataNascimento;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Telefone telefone;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Participante amigoSecreto;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "grupo_participante", joinColumns
            = @JoinColumn(name = "participante_id"),
            inverseJoinColumns
            = @JoinColumn(name = "grupo_id"))
    private List<Grupo> grupos;

    @OneToMany(mappedBy = "administrador", targetEntity = Grupo.class, cascade = CascadeType.ALL)
    private List<Grupo> gruposAdministrados;

    @OneToMany(mappedBy = "participante", targetEntity = SugestaoPresente.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<SugestaoPresente> listaDesejos;

    @OneToMany(mappedBy = "amigoDestino", targetEntity = Mensagem.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Mensagem> mensagens;
    
    @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = true)
    private Login login;

    @Embedded
    private Endereco endereco;

    public Participante() {
        endereco = new Endereco();
        telefone = new Telefone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SugestaoPresente> getListaDesejos() {
        return listaDesejos;
    }

    public void setListaDesejos(List<SugestaoPresente> listaDesejos) {
        this.listaDesejos = listaDesejos;
    }

    public List<Grupo> getGruposAdministrados() {
        return gruposAdministrados;
    }

    public void setGruposAdministrados(List<Grupo> gruposAdministrados) {
        this.gruposAdministrados = gruposAdministrados;
    }

    public Participante getAmigoSecreto() {
        return amigoSecreto;
    }

    public void setAmigoSecreto(Participante amigoSecreto) {
        this.amigoSecreto = amigoSecreto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Participante other = (Participante) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

}
