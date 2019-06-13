package warstwa_biznesowa;

import java.util.ArrayList;
import java.util.Date;
import warstwa_biznesowa.dto.Produkt_dto;
import warstwa_biznesowa.entity.Produkt1;

public class Fasada_warstwy_biznesowej {

    static long klucz = 0;
    private ArrayList<Produkt1> produkty = new ArrayList();
    boolean stan = false;

    public boolean isStan() {
        return stan;
    }
    
    public void setStan(boolean stan) {
        this.stan = stan;
    }
    
    public int count() {
        return produkty.size();
    }
    
    public ArrayList<Produkt_dto> findRange(int[] range) {
        ArrayList<Produkt_dto> pom = new ArrayList();
        for (int i = range[0]; i < range[1]; i++ ) {
            pom.add(produkt_transfer(getProdukty().get(i)));
        }
        return pom;
    }
    
    public ArrayList<Produkt1> getProdukty() {
        return produkty;
    }

    public void setProdukty(ArrayList<Produkt1> produkty) {
        this.produkty = produkty;
    }

    public void utworz_produkt(Produkt_dto produkt_dto) {
        Produkt1 produkt = new Produkt1();
        klucz++;
        produkt.setId(new Long(klucz));
        produkt.setNazwa(produkt_dto.getNazwa());
        produkt.setCena(produkt_dto.getCena());
        produkt.setPromocja(produkt_dto.getPromocja());
        produkt.setData_produkcji(produkt_dto.getData_produkcji());
        dodaj_produkt(produkt);
    }

    protected void dodaj_produkt(Produkt1 produkt) {
        if (!produkty.contains(produkt)) {
            produkty.add(produkt);
            stan = true;
        } else {
            stan = false;
        }
    }

    public Produkt_dto dane_produktu() {
        if (stan) {
            Produkt1 produkt = produkty.get(produkty.size() - 1);
            return produkt_transfer(produkt);
        }
        return null;
    }
    
    public Produkt_dto produkt_transfer(Produkt1 produkt) {
        Produkt_dto pom = new Produkt_dto();
        pom.setId(produkt.getId());
        pom.setNazwa(produkt.getNazwa());
        pom.setCena(produkt.getCena());
        pom.setPromocja(produkt.getPromocja());
        pom.setData_produkcji(produkt.getData_produkcji());
        pom.setCena_brutto(produkt.cena_brutto());
        return pom;
    }

    public ArrayList<Produkt_dto> items_() {
        ArrayList<Produkt_dto> dane = new ArrayList();
        for (Produkt1 produkt : produkty) {
            dane.add(produkt_transfer(produkt));
        }
        return dane;
    }
    
    public ArrayList<ArrayList<String>> items() {
        ArrayList<ArrayList<String>> dane = new ArrayList();
        for (Produkt1 p : produkty) {
            ArrayList<String> wiersz = new ArrayList();
            wiersz.add(p.getId().toString());
            wiersz.add(p.getNazwa());
            wiersz.add("" + p.getCena());
            wiersz.add("" + p.getPromocja());
            wiersz.add(p.getData_produkcji().toString());
            wiersz.add("" + p.cena_brutto());
            dane.add(wiersz);
        }
        return dane;
    }

}
