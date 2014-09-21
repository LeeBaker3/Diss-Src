/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.base;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *  BaseSaveRetrieve Class. The class is based on the NetBeans Facade template and modified only by 
 *  changing its name to fall in-line with the design of this solution. It is 
 *  inherited by all other derived SaveRetrive classes that us the 
 *  PersistenceContext AtomicInformationConfigManagerPU
 * 
 *  @author Lee Baker
 *  @version 1.0
 */

//START OF IDE GENERATED CODE
@Stateless
public class BaseSaveRetrieve extends BaseSaveRetrieveAbstract<BaseEntity> {
    @PersistenceContext(unitName = "AtomicInformationConfigManagerPU")
    private EntityManager em;

    public BaseSaveRetrieve() {
        super(BaseEntity.class);
    }
    
    /**
     * getEntityManager method. Implemented as abstract in the 
     * BaseSaveRetrieveAbstract interface
     * 
     * @return em EntityManager
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
//END OF IDE GENERATED CODE
