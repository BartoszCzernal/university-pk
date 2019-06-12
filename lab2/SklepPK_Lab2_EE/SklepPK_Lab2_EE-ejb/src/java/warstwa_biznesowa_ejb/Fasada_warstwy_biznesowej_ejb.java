/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_biznesowa_ejb;

import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Stateless;
import warstwa_biznesowa.Fasada_warstwy_biznesowej;

/**
 *
 * @author bartek
 */
@Stateless
public class Fasada_warstwy_biznesowej_ejb implements Fasada_warstwy_biznesowej_ejbRemote {

    Fasada_warstwy_biznesowej fasada = new Fasada_warstwy_biznesowej();
    
    @Override
    public void utworz_produkt(String dane[], Date data) {
        fasada.utworz_produkt(dane, data);
    }
    
    @Override
    public String[] dane_produktu() {
        return fasada.dane_produktu();
    }
    
    @Override
    public ArrayList<ArrayList<String>> items() {
        return fasada.items();
    }
}
