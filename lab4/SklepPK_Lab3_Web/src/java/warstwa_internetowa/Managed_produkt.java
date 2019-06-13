package warstwa_internetowa;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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
import pomoc.JsfUtil;
import pomoc.PaginationHelper;
import pomoc.Zmiana_danych;
import warstwa_biznesowa.dto.Produkt_dto;
import warstwa_biznesowa_ejb.Fasada_warstwy_biznesowej_ejbRemote;

@Named(value = "managed_produkt")
@SessionScoped
public class Managed_produkt implements ActionListener, Serializable {

    @EJB
    private Fasada_warstwy_biznesowej_ejbRemote fasada;

//    @EJB
//    private Fasada_warstwy_biznesowej_ejbRemote fasada;
    

    
    public Managed_produkt() { }
    private int powrot = 1;
    private int zmiana = 1;
    private PaginationHelper pagination;
    private Produkt_dto produkt_dto = new Produkt_dto();
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
    
    public int getZmiana() {
        return zmiana;
    }

    public int getPowrot() {
        return powrot;
    }
    
    Produkt_dto produkt_dto_przed;
    
    public String prepareEdit() {
        produkt_dto = (Produkt_dto) items.getRowData();
        produkt_dto_przed = new Produkt_dto(produkt_dto);
        zmiana = 0;
        return "dodaj_produkt2";
    }
    
    public String update() {
        try {
            boolean wynik = getFasada().edit(produkt_dto_przed, produkt_dto);
            produkt_dto = new Produkt_dto();
            zmiana = 1;
            recreateModel();
            if (wynik) {
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").
                        getString("Produkt_zmieniony"));
            } else {
                throw new Exception();
            }
            return "lista_produktow";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e,
                    ResourceBundle.getBundle("/Bundle").getString("Blad_modyfikacji"));
            return "lista_produktow";
        }
    }
    
    public String prepareView() {
        produkt_dto = (Produkt_dto) items.getRowData();
        powrot = 0;
        stan = 1;
        return "rezultat2";
    }
    
    public String powrot() {
        powrot = 1;
        produkt_dto = new Produkt_dto();
        return "lista_produktow";
    }
    
    public PaginationHelper getPagination() {
        if(pagination == null) {
            pagination = new PaginationHelper(3) {
                @Override
                public int getItemsCount() {
                    return getFasada().count();
                }
                
                @Override
                public DataModel createPageDataModel() {
                    int[] range = {getPageFirstItem(), getPageLastItem() + 1};
                    return new ListDataModel(getFasada().findRange(range));
                }
            };
        }
        return pagination;
    }
    
    private void recreateModel() {
        items = null;
    }
    
    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "lista_produktow";
    }
    
    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "lista_produktow";
    }
    
    public int getMin() {
        return 0;
    }

    public int getMax() {
        return 100;
    }
 
    public Fasada_warstwy_biznesowej_ejbRemote getFasada() {
        return fasada;
    }

    public void setFasada(Fasada_warstwy_biznesowej_ejbRemote fasada) {
        this.fasada = fasada;
    }

    public String getNazwa() {
        return produkt_dto.getNazwa();
    }

    public void setNazwa(String nazwa) {
        this.produkt_dto.setNazwa(nazwa);
    }

    public float getCena() {
        return produkt_dto.getCena();
    }

    public void setCena(float cena) {
        this.produkt_dto.setCena(cena);
    }

    public int getPromocja() {
        return produkt_dto.getPromocja();
    }

    public void setPromocja(int promocja) {
        this.produkt_dto.setPromocja(promocja);
    }

    public int getStan() {
        return stan;
    }

    public void setStan(int stan) {
        this.stan = stan;
    }

    public float getCena_brutto() {
        return produkt_dto.getCena_brutto();
    }
    
    public void setCena_brutto(float cena_brutto) {
        this.produkt_dto.setCena_brutto(cena_brutto);
    }

    public Date getData_produkcji() {
        return produkt_dto.getData_produkcji();
    }

    public void setData_produkcji(Date data_produkcji) {
        this.produkt_dto.setData_produkcji(data_produkcji);
    }

    public void setItems(DataModel items) {
        this.items = items;
    }

    public void dodaj_produkt() {
        fasada.utworz_produkt(produkt_dto);
        powrot = 1;
        zmiana = 1;
        dane_produktu();
        recreateModel();
        getPagination().nextPage();
    }

    public void dane_produktu() {
        stan = 1;
        produkt_dto = fasada.dane_produktu();
        if (produkt_dto == null) {
            produkt_dto = new Produkt_dto();
            stan = 0;
        }
    }

    @Override
   public void processAction(ActionEvent event) throws AbortProcessingException
    {
       dodaj_produkt();
    }
   
   
    public DataModel utworz_DataModel() {
        return new ListDataModel(fasada.items_());
    }

    public DataModel getItems() {
        if (items == null|| fasada.isStan()) {
            items = getPagination().createPageDataModel();
        }
        zmiana = 1;
        powrot = 1;
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
