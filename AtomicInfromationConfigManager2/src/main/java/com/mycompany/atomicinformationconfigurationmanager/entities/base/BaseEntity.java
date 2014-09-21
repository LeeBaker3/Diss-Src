/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.base;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

/**
 *  BaseEntity Class. This class describes common attributes for many entity class in this system to allow
 *  the management of entity version control and preventing the entity from being returned when EntityActive is False
 *  rather than deleting it from the database. 
 *  
 *  The class uses the JPA (Java Persistence API) to access and persist data in a relational database. 
 *  The class also inherits several methods from the BaseEntity class. All Attributes of the  entity are 
 *  Annotated according to the JPA specification to meet the underlying database schema.
 *  
 *  No methods have been commented as they conform and are believed to be self explanatory and consistent with JPA
 *  @author Lee Baker
 *  @version 1.0
 * */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Basic(optional = false)
protected Integer id;

@Basic(optional = false)
@NotNull
@Column(name = "VersionNumber")
protected int versionNumber;

@Basic(optional = false)
@NotNull
@Column(name = "IsCurrentVersion")
protected boolean isCurrentVersion;

@Column(name = "PreviousVersionReference")
protected Integer previousVersionReference;

@Basic(optional = false)
@NotNull
@Column(name = "EntityActive")
protected boolean entityActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int VersionNumber) {
        this.versionNumber = VersionNumber;
    }

    public boolean isIsCurrentVersion() {
        return isCurrentVersion;
    }

    public void setIsCurrentVersion(boolean IsCurrentVersion) {
        this.isCurrentVersion = IsCurrentVersion;
    }

    public Integer getPreviousVersionReference() {
        return previousVersionReference;
    }

    public void setPreviousVersionReference(Integer PreviousVersionReference) {
        this.previousVersionReference = PreviousVersionReference;
    }

    public boolean isEntityActive() {
        return entityActive;
    }

    public void setEntityActive(boolean EntityActive) {
        this.entityActive = EntityActive;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
