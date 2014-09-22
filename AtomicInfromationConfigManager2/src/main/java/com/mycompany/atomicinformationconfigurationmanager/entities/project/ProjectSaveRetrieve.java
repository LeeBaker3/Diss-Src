/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.project;

import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseSaveRetrieveAbstract;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *  ProjectSaveRetrieve Class. This class inherits from the base class BaseSaveRetrieve and implements
 *  the BaseSaveRetrieve Interface via the BaseSaveRetrieve class. The primary purpose of this class 
 *  is to act as the the database access layer for the Project Entity. The methods on the BaseSaveRetrieve 
 *  Class and BaseSaveRetrieve Interface are extend in this class for the specific needs of the Project entity.
 *  The class is predominantly used by the Controller part of the MVC pattern  
 * 
 *  The class uses the JPA (Java Persistence API) to access and persist data in a relational database. 
 *  
 *  The class is based on the NetBeans Facade template and modified for this project and makes use of
 *  several NamedQueries in the Project Class.
 * 
 * @author Lee Baker
 * @version 1.0
 */

//START IDE GENERATED CODE
@Stateless
public class ProjectSaveRetrieve extends BaseSaveRetrieveAbstract<Project> {
    @PersistenceContext(unitName = "AtomicInformationConfigManagerPU")
    private EntityManager em;

    public ProjectSaveRetrieve() {
        super(Project.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
//END IDE GENERATED CODE
