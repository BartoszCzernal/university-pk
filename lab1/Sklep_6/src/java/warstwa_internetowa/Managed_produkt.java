package warstwa_internetowa;

import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.convert.NumberConverter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pomoc.Zmiana_danych;
import warstwa_biznesowa.Fasada_warstwy_biznesowej;

@Named(value = "managed_produkt")
@RequestScoped
public class Managed_produkt implements ActionListener{

    @EJB
    private Fasada_warstwy_biznesowej fasada;
   
    public Managed_produkt() { }
    private String nazwa;
    private float cena;
    private int promocja;
    private float cena_brutto;
    private DataModel items;
    private int stan = 1;
    private Date data_produkcji;
    private Zmiana_danych zmiana1= new Zmiana_danych("nazwa");
    private Zmiana_danych zmiana2= new Zmiana_danych("cena");
    public Zmiana_danych getZmiana1()             {  return zmiana1;  }
    public void setZmiana1(Zmiana_danych zmiana2)  {  this.zmiana1 = zmiana2;    }
    public Zmiana_danych getZmiana2()             {  return zmiana2;    }
    public void setZmiana2(Zmiana_danych zmiana2) {  this.zmiana2 = zmiana2;    }
   
    private NumberConverter number_convert = new NumberConverter();

    public NumberConverter getNumber_convert() {
        this.number_convert.setPattern("######.## zł");
        return number_convert;
    }

    public void setNumber_convert(NumberConverter Number_convert) {
        this.number_convert = Number_convert;
    }

    public int getMin() {
        return 0;
    }

    public int getMax() {
        return 100;
    }
 
    public Fasada_warstwy_biznesowej getFasada() {
        return fasada;
    }

    public void setFasada(Fasada_warstwy_biznesowej fasada) {
        this.fasada = fasada;
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

    public int getStan() {
        return stan;
    }

    public void setStan(int stan) {
        this.stan = stan;
    }

    public float getCena_brutto() {
        return cena_brutto;
    }
    
    public void setCena_brutto(float cena_brutto) {
        this.cena_brutto = cena_brutto;
    }

    public Date getData_produkcji() {
        return data_produkcji;
    }

    public void setData_produkcji(Date data_produkcji) {
        this.data_produkcji = data_produkcji;
    }

    public void setItems(DataModel items) {
        this.items = items;
    }

    public void dodaj_produkt() {
        String[] dane = {"" + nazwa, "" + cena, "" + promocja};
        fasada.utworz_produkt(dane, data_produkcji);
        dane_produktu();
       // return "rezultat2";
    }

    public void dane_produktu() {
        stan = 1;
        String[] dane = fasada.dane_produktu();
        if (dane == null) {
            stan = 0;
        } else {
            nazwa = dane[0];
            cena = Float.parseFloat(dane[1]);
            promocja = Integer.parseInt(dane[2]);
            cena_brutto = Float.parseFloat(dane[3]);
            data_produkcji.setTime(Long.parseLong(dane[4]));
        }
    }

    @Override
   public void processAction(ActionEvent event) throws AbortProcessingException
    {
       dodaj_produkt();
    }
   
   
    public DataModel utworz_DataModel() {
        return new ListDataModel(fasada.items());
    }

    public DataModel getItems() {
        if (items == null|| stan==1) {
            items = utworz_DataModel();
        }
        return items;
    }
    
     public void zakrespromocji(FacesContext context,
            UIComponent toValidate, Object value) {
        stan = 1;
        int input = ((Long) value).intValue();
        if (input < getMin() || input > getMax()) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Dane poza zakresem");
            context.addMessage(toValidate.getClientId(context), message);
            stan = 0;
        }
    }
}
