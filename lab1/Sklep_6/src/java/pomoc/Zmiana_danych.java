/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomoc;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

public class Zmiana_danych implements ValueChangeListener {

    int licznik;
    String klucz;

    public Zmiana_danych(String klucz_) {
        klucz = klucz_;
    }

    public Zmiana_danych() {
       klucz="dane";
    }

    @Override
    public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {
        String nazwa;
        FacesContext context = FacesContext.getCurrentInstance();
        String clientId = event.getComponent().getClientId();
        nazwa = "" + event.getNewValue();
        if (!nazwa.equals("")) {
            if (context.getExternalContext().getSessionMap().containsKey(klucz)) {
                licznik = (Integer) context.getExternalContext().getSessionMap().get(klucz);
            }
            licznik++;
            FacesMessage message = new FacesMessage("Stan licznika zmian " + klucz + ": " + licznik);
            context.getExternalContext().getSessionMap().put(klucz, licznik);
            context.addMessage(clientId, message);
        }
    }      
}
