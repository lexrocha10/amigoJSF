/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author marinaldosantos
 * @param <T>
 */
public interface IDaoGenerico<T> {

    public void add(T u);
    
    public void upd(T u);

    public void del(T u);

    public T findById(Integer id);

    public List<T> listaAll();

    public List<T> JpqlLike(String busca, String campo);

}
