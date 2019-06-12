/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_biznesowa_ejb;

import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author bartek
 */
@Remote
public interface Fasada_warstwy_biznesowej_ejbRemote {
    public void utworz_produkt(String dane[], Date data);
    public String[] dane_produktu();
    public ArrayList<ArrayList<String>> items();
}
