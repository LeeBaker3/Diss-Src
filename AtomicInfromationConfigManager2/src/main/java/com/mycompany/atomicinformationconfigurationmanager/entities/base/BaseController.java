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
 *  BaseController Class. The BaseController class is a base class and provide various methods 
 *  for managing the version control of entities. The Base Controller Class is an abstract class 
 *  that is inherited by other controller classes.
 *  
 *  @author Lee Baker
 *  @version 1.0
 */

//START LEE BAKER GENERATED CODE
@Named("baseController")
@SessionScoped
abstract public class BaseController implements Serializable{

    /**
     * setEntityActive method. Sets an entity to active (True) so it can be 
     * returned by database queries as a current record
     * 
     * @param entity the entity that is to be set Active
     */
    public void setEntityActive(BaseEntity entity){
        entity.entityActive = true;
    }
    
    /**
     * setEntityInActive method. Sets an entity to inActive (False) so it can't 
     * be returned by database queries rather than being deleted from the database.
     * 
     * @param entity the entity that is to be set InActive
     */
    public void setEntityInActive(BaseEntity entity){
        entity.entityActive = false;
    }
    
    /**
     * updateVersionNumber method. This method takes the version number of the old
     * entity and increments it by one to create the version number of the new entity
     * 
     * @param oldEntity used to get the last version number
     * @param newEntity new version number = old version number + 1
     */
    public void updateVersionNumber(BaseEntity oldEntity, BaseEntity newEntity){
        newEntity.setVersionNumber(oldEntity.getVersionNumber() + 1);
    }
    
    /**
     * setPreviousReference method. This method creates a reference to the previous
     * version entity in the new version entity by inserting a reference to the 
     * old version entity ID into the new version entity PreviousVersionReference
     * attribute
     * 
     * @param oldEntity used to get the ID of the old entity
     * @param newEntity used to set a reference to the ID of the old entity
     */
    public void setPreviousReference (BaseEntity oldEntity, BaseEntity newEntity) {
        newEntity.setPreviousVersionReference(oldEntity.getId());
    }
    
    /**
     * prepareVersion method. This method prepares entity controller to update and create
     * a new version of am entity. The entity to be updated is set to Not Current Version
     * and persisted to the database.
     * 
     * @param oldEntity Entity that is being version updated
     * @param saveRetrieve Entity Controller SaveRetrieve class that is used to persist that Class of entity to the database 
     */
    public void prepareVersion (BaseEntity oldEntity, BaseSaveRetrieveAbstract saveRetrieve) {
        oldEntity.setIsCurrentVersion(false);
        saveRetrieve.edit(oldEntity);   
    }    
    
    /**
     * manageVersion method. The controlling method of the class that manages most of the version control
     * of entity version update activity. New version number is created, reference to the old version
     * entity Database ID is created. The new Entity ID is set to null so it can be created by the 
     * database when the entity is persisted to the database. Finally the new entity is set to active (True)
     * and current version is set to True
     * 
     * @param oldEntity entity that updates will be made from
     * @param newEntity entity hold updates from the old entity 
     */
    public void manageVersion(BaseEntity oldEntity, BaseEntity newEntity) {
        updateVersionNumber(oldEntity, newEntity);
        setPreviousReference(oldEntity, newEntity);
        newEntity.setId(null);
        newEntity.setEntityActive(true);
        newEntity.setIsCurrentVersion(true);
    }
    
    /**
     * rollBackVersion method. If an entity version update fails and a new entity version
     * isn't created this method can be called to roll back changes that have been made to
     * the old entity. The is updated to be the current version (True).
     * 
     * @param oldEntity entity that will be rolled back to as current version
     * @param saveRetrieve Entity Controller SaveRetrieve class that is used to persist that Class of entity to the database 
     */
    public void rollBackVersion (BaseEntity oldEntity, BaseSaveRetrieveAbstract saveRetrieve){
        oldEntity.setIsCurrentVersion(true);
        saveRetrieve.edit(oldEntity);
    }
}
//END LEE BAKER GENERATED CODE
