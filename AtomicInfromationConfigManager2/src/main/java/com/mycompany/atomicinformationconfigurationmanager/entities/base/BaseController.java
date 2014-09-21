/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.base;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Lee Baker
 */
@Named("baseController")
@SessionScoped
abstract public class BaseController implements Serializable{

    
    public void setEntityActive(BaseEntity entity){
        entity.entityActive = true;
    }
    
    public void setEntityInActive(BaseEntity entity){
        entity.entityActive = false;
    }
      
    public void updateVersionNumber(BaseEntity oldEntity, BaseEntity newEntity){
        newEntity.setVersionNumber(oldEntity.getVersionNumber() + 1);
    }
    
    public void setPreviousReference (BaseEntity oldEntity, BaseEntity newEntity) {
        newEntity.setPreviousVersionReference(oldEntity.getId());
    }
    
    public void prepareVersion (BaseEntity oldEntity, BaseSaveRetrieveAbstract saveRetrieve) {
        oldEntity.setIsCurrentVersion(false);
        saveRetrieve.edit(oldEntity);   
    }    

    public void manageVersion(BaseEntity oldEntity, BaseEntity newEntity) {
        updateVersionNumber(oldEntity, newEntity);
        setPreviousReference(oldEntity, newEntity);
        newEntity.setId(null);
        newEntity.setEntityActive(true);
        newEntity.setIsCurrentVersion(true);
    }
    
    public void rollBackVersion (BaseEntity oldEntity, BaseSaveRetrieveAbstract saveRetrieve){
        oldEntity.setIsCurrentVersion(true);
        saveRetrieve.edit(oldEntity);
    }
    
}
