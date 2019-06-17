/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_biznesowa.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author bartek
 */
public class Produkt_dto implements Serializable {
    
    protected long id;
    protected String nazwa;
    protected float cena;
    protected int promocja;
    protected Date data_produkcji;
    protected float cena_brutto;
    
    public Produkt_dto() {}
    public Produkt_dto(String[] dane, Date data) {
        nazwa = dane[0];
        cena = Float.parseFloat(dane[1]);
        promocja = Integer.parseInt(dane[2]);
        data_produkcji = data;
    }
    public Produkt_dto(Produkt_dto o) {
        nazwa = o.getNazwa();
        cena = o.getCena();
        promocja = o.getPromocja();
        data_produkcji = o.getData_produkcji();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public int getPromocja() {
        return promocja;
    }

    public void setPromocja(int promocja) {
        this.promocja = promocja;
    }

    public Date getData_produkcji() {
        return data_produkcji;
    }

    public void setData_produkcji(Date data_produkcji) {
        this.data_produkcji = data_produkcji;
    }

    public float getCena_brutto() {
        return cena_brutto;
    }

    public void setCena_brutto(float cena_brutto) {
        this.cena_brutto = cena_brutto;
    }
    
    
}
