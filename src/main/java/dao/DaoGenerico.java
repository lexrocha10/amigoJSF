package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.JpaUtil2;

/**
 *
 * @author alexrochatsi
 * @param <T>
 */
public class DaoGenerico<T> implements IDaoGenerico<T> {

    private final Class classe;

    DaoGenerico(Class classe) {
        this.classe = classe;
    }

    @Override
    public void add(T u) {
        EntityManager em = JpaUtil2.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void upd(T u) {
        EntityManager em = JpaUtil2.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void del(T u) {
        EntityManager em = JpaUtil2.getEntityManager();
        try {
            em.getTransaction().begin();
            u = em.merge(u);
            em.remove(u);
            em.flush();
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } catch (Exception ex) {
        } finally {
            em.close();
        }
    }

    @Override
    public T findById(Integer id) {
        EntityManager em = JpaUtil2.getEntityManager();
        T u = (T) em.find(classe, id);
        em.close();
        return u;
    }

    @Override
    public List<T> listaAll() {
        EntityManager em = JpaUtil2.getEntityManager();
        String jpql = "select a from "
                + classe.getSimpleName() + " a";
        List<T> lista = em.createQuery(jpql, classe).getResultList();
        em.close();
        return lista;
    }

    @Override
    public List<T> JpqlLike(String busca,String campo) {
        EntityManager em = JpaUtil2.getEntityManager();
        String jpql = "select t from " + classe.getSimpleName()
                + " t  where  t." + campo + " like :pbusca";
        Query query = em.createQuery(jpql, classe);
        query.setParameter("pbusca", "%" + busca + "%");
        List<T> lista = query.getResultList();
        em.close();
        return lista;
    }
}
