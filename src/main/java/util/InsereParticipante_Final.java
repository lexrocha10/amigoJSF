package util;

import dominio.CategoriaPresente;
import dominio.Endereco;
import dominio.Grupo;
import dominio.Participante;
import dominio.SugestaoPresente;
import dominio.Telefone;
import dominio.Sexo;
import dominio.TipoTelefone;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

public class InsereParticipante_Final {

    public static void main(String[] args) throws ParseException {

        Participante participante = new Participante();
        Telefone telefone = new Telefone();
        Endereco endereco = new Endereco();
        List<Grupo> grupos = new ArrayList<>();
       // ArrayList presentes = new ArrayList();
        //SugestaoPresente sugestaoPresente = new SugestaoPresente();
        //SugestaoPresente sugestaoPresente2 = new SugestaoPresente();
        
        EntityManager em = JpaUtil2.getEntityManager();
        
        participante.setNome("Marina Rocha");
        participante.setCpf("895779047");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("03/05/1992");
        participante.setDataNascimento(date);
        participante.setEmail("marina@gmail.com");
        participante.setSexo(em.find(Sexo.class,2));
        telefone.setDdd("63");
        telefone.setNumero("8933-6265");
        telefone.setTipoTelefone(em.find(TipoTelefone.class,1));
        participante.setTelefone(telefone);
        endereco.setCidade("Palmas");
        endereco.setCep("77001-002");
        endereco.setBairro("Plano Diretor Sul");
        endereco.setComplemento("");
        endereco.setLogradouro("106 Sul, AL 05");
        endereco.setNumero("10");
        participante.setEndereco(endereco); 
        grupos.add(em.find(Grupo.class, 1));
        participante.setGrupos(grupos);
        
        /*
        participante.setNome("Alex da Silva Rocha");
        participante.setCpf("04608474146");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("03/05/1993");
        participante.setDataNascimento(date);
        participante.setEmail("alexrochatsi@gmail.com");
        participante.setSexo(em.find(TipoSexo.class,2));
        telefone.setDdd("63");
        telefone.setNumero("8443-6265");
        telefone.setTipoTelefone(em.find(TipoTelefone.class,1));
        participante.setTelefone(telefone);
        endereco.setCidade("Palmas");
        endereco.setCep("77021-072");
        endereco.setBairro("Plano Diretor Sul");
        endereco.setComplemento("");
        endereco.setLogradouro("308 Sul, AL 05");
        endereco.setNumero("10");
        participante.setEndereco(endereco); 
        
        participante.setGrupo(em.find(Grupo.class, 1));

        ###############################################################
                
         participante.setNome("Fabio Lima");
        participante.setCpf("78665657898");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("03/03/1993");
        participante.setDataNascimento(date);
        participante.setEmail("fabbiolima@gmail.com");
        participante.setSexo(em.find(TipoSexo.class,2));
        telefone.setDdd("63");
        telefone.setNumero("3214-6599");
        telefone.setTipoTelefone(em.find(TipoTelefone.class,2));
        participante.setTelefone(telefone);
        endereco.setCidade("Palmas");
        endereco.setCep("77943-072");
        endereco.setBairro("Plano Diretor Sul");
        endereco.setComplemento("");
        endereco.setLogradouro("1306 Sul, AL 05");
        endereco.setNumero("20");
        participante.setEndereco(endereco);
        
        sugestaoPresente.setDescricao("Harry Potter e a pedra filosofal");
        sugestaoPresente.setCategoriaPresente(em.find(CategoriaPresente.class,1));
        sugestaoPresente.setLink("www.livrariasaraiva.com.br/23423");
        
        sugestaoPresente.setParticipante(participante);
        
        presentes.add(sugestaoPresente);
        participante.setListaDesejos(presentes);
        
        participante.setGrupo(em.find(Grupo.class, 1));
        
        
        participante.setNome("Paola Alves");
        participante.setCpf("78665657898");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("01/08/1990");
        participante.setDataNascimento(date);
        participante.setEmail("paola@gmail.com");
        participante.setSexo(em.find(TipoSexo.class,1));
        telefone.setDdd("63");
        telefone.setNumero("8452-0324");
        telefone.setTipoTelefone(em.find(TipoTelefone.class,1));
        participante.setTelefone(telefone);
        endereco.setCidade("Palmas");
        endereco.setCep("77034-492");
        endereco.setBairro("Plano Diretor Sul");
        endereco.setComplemento("");
        endereco.setLogradouro("704 Sul, AL 05");
        endereco.setNumero("34");
        participante.setEndereco(endereco);
        
        sugestaoPresente.setDescricao("Tênis Nike Liberty N° 40");
        sugestaoPresente.setCategoriaPresente(em.find(CategoriaPresente.class,5));
        sugestaoPresente.setLink("www.centauros.com.br/nike/23454");
        
        sugestaoPresente2.setDescricao("Vinho tinto ano 80");
        sugestaoPresente2.setCategoriaPresente(em.find(CategoriaPresente.class,9));
        sugestaoPresente2.setLink("www.vinhos.com.br/f976");
        
        sugestaoPresente.setParticipante(participante);
        sugestaoPresente2.setParticipante(participante);
        
        presentes.add(sugestaoPresente);
        presentes.add(sugestaoPresente2);
        participante.setListaDesejos(presentes);
        
        //participante.setGrupo(em.find(Grupo.class, 1));
        */
        try {
            em.getTransaction().begin();
            em.persist(participante);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}