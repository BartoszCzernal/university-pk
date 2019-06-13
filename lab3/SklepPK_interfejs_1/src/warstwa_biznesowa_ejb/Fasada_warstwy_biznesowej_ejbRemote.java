/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_biznesowa_ejb;

import java.util.ArrayList;
import javax.ejb.Remote;
import warstwa_biznesowa.dto.Produkt_dto;

/**
 *
 * @author bartek
 */
@Remote
public interface Fasada_warstwy_biznesowej_ejbRemote {
    public void utworz_produkt(Produkt_dto produkt_dto);
    public Produkt_dto dane_produktu();
    public ArrayList<ArrayList<String>> items();
    public ArrayList<Produkt_dto> items_();
}
