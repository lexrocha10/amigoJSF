package dominio;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Rocha
 */
@Entity
@Table(name="categoria_presente")
public class CategoriaPresente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "descricao", length = 120, nullable = false, unique = true)
    private String descricao;
    
    @OneToMany(mappedBy = "categoriaPresente", targetEntity = SugestaoPresente.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<SugestaoPresente> sugestoesPresente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
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
        final CategoriaPresente other = (CategoriaPresente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<SugestaoPresente> getSugestoesPresente() {
        return sugestoesPresente;
    }

    public void setSugestoesPresente(List<SugestaoPresente> sugestoesPresente) {
        this.sugestoesPresente = sugestoesPresente;
    }
    
    
}
