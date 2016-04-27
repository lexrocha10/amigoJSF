package dominio;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
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

/**
 *
 * @author Rocha
 */
@Entity
@Table(name="sugestao_presente")

@NamedQueries({
    @NamedQuery(name = "SugestaoPresente.findSugestaoByGrupo",
            query = "select sp from SugestaoPresente sp where sp.grupo = :grupoPassado"),
    @NamedQuery(name = "SugestaoPresente.findSugestaoByParticipante",
            query = "select sp from SugestaoPresente sp where sp.participante = :participantePassado"),
    @NamedQuery(name = "SugestaoPresente.findMinhasSugestao",
            query = "select sp from SugestaoPresente sp where sp.participanteQueSugeriu = :participantePassado"),
    @NamedQuery(name = "SugestaoPresente.findMinhasSugestaoRecebidas",
            query = "select sp from SugestaoPresente sp where sp.participante = :participantePassado")
})
public class SugestaoPresente implements Serializable {
    @Id
    @SequenceGenerator(name = "sugestao_presente_id_gen",
            sequenceName = "sugestao_presente_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "sugestao_presente_id_gen")
    private Integer id;
    private String descricao;
    private String link;
    
    @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = false)
    private Participante participanteQueSugeriu;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="categoriapresente_id",nullable = false)
    private CategoriaPresente categoriaPresente;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="participante_id",nullable = true)
    private Participante participante;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="grupo_id",nullable = true)
    private Grupo grupo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CategoriaPresente getCategoriaPresente() {
        return categoriaPresente;
    }

    public void setCategoriaPresente(CategoriaPresente categoriaPresente) {
        this.categoriaPresente = categoriaPresente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final SugestaoPresente other = (SugestaoPresente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Participante getParticipanteQueSugeriu() {
        return participanteQueSugeriu;
    }

    public void setParticipanteQueSugeriu(Participante participanteQueSugeriu) {
        this.participanteQueSugeriu = participanteQueSugeriu;
    }
    
    
}
