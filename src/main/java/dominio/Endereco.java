package dominio;

import java.io.Serializable;
import javax.persistence.Column;

/**
 *
 * @author Rocha
 */
public class Endereco implements Serializable {
    @Column(name = "cidade", length = 120, nullable = false)
    private String cidade;
    @Column(name = "cep", length = 10, nullable = false)
    private String cep;
    @Column(name = "logradouro", length = 80, nullable = false)
    private String logradouro;
    @Column(name = "bairro", length = 80, nullable = false)
    private String bairro;
    @Column(name = "numero", length = 10, nullable = true)
    private String numero;
    @Column(name = "complemento", length = 30, nullable = false)
    private String complemento;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
