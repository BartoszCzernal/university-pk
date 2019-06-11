package warstwa_biznesowa;

import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Stateful;

@Stateful
public class Fasada_warstwy_biznesowej_ejb {
   
    Fasada_warstwy_biznesowej fasada = new Fasada_warstwy_biznesowej();
    
    public void utworz_produkt(String dane[], Date data) {
        fasada.utworz_produkt(dane, data);
    }
    
    public String[] dane_produktu() {
        return fasada.dane_produktu();
    }
    
    public ArrayList<ArrayList<String>> items() {
        return fasada.items();
    }
}
