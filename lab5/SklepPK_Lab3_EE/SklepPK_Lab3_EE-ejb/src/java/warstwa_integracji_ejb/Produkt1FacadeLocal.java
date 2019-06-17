/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_integracji_ejb;

import java.util.List;
import javax.ejb.Local;
import warstwa_biznesowa.entity.Produkt1;

/**
 *
 * @author bartek
 */
@Local
public interface Produkt1FacadeLocal {

    void create(Produkt1 produkt1);

    void edit(Produkt1 produkt1);

    void remove(Produkt1 produkt1);

    Produkt1 find(Object id);

    List<Produkt1> findAll();

    List<Produkt1> findRange(int[] range);

    int count();
    
}
