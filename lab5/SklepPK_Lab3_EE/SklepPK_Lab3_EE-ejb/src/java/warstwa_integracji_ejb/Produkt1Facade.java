/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_integracji_ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import warstwa_biznesowa.entity.Produkt1;

/**
 *
 * @author bartek
 */
@Stateless
public class Produkt1Facade extends AbstractFacade<Produkt1> implements Produkt1FacadeLocal {

    @PersistenceContext(unitName = "SklepPK_Lab3_EE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Produkt1Facade() {
        super(Produkt1.class);
    }
    
}
