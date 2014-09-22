/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation;

import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseSaveRetrieveAbstract;
import com.mycompany.atomicinformationconfigurationmanager.entities.project.Project;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *  AtomicinformationSaveRetrieve Class. This class inherits from the base class BaseSaveRetrieve and implements
 *  the BaseSaveRetrieve Interface via the BaseSaveRetrieve class. The primary purpose of this class 
 *  is to act as the the database access layer for the Atomicinformation Entity. The methods on the BaseSaveRetrieve 
 *  Class and BaseSaveRetrieve Interface are extend in this class for the specific needs of the Atomicinformation entity.
 *  The class is predominantly used by the Controller part of the MVC pattern  
 * 
 *  The class uses the JPA (Java Persistence API) to access and persist data in a relational database. 
 *  
 *  The class is based on the NetBeans Facade template and modified for this project and makes use of
 *  several NamedQueries in the Atomicinformation Class.
 * 
 * @author Lee Baker
 * @version 1.0
 */

//START IDE GENERATED CODE
@Stateless
public class AtomicinformationSaveRetrieve extends BaseSaveRetrieveAbstract<Atomicinformation> {
    @PersistenceContext(unitName = "AtomicInformationConfigManagerPU")
    private EntityManager em;

    public AtomicinformationSaveRetrieve() {
        super(Atomicinformation.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    //END IDE GENERATED CODE
    
    //START LEE BAKER GENERATED CODE
    /**
     * findByEntityActiveAndProjectIDAndIsCurrentVersion method. The purpose of this method is to return a List<> of Atomicinformation entities
     * from a database that match the criteria for a specific project and the EntityActive and isCurrentVersion attributes for the entity 
     * 
     * @param project is the project entity to matched on
     * @param entityActive can either be active entity or not by setting True or False 
     * @param isCurrentVersion can either be the most current version of an entity or not by setting True or False 
     * @return a List<Atomicinformation> entities found in the database matching the criteria for the parameters of the method
     */
    public List<Atomicinformation> findByEntityActiveAndProjectIDAndIsCurrentVersion(Boolean entityActive, Project project, boolean isCurrentVersion){
        TypedQuery<Atomicinformation> query = em.createNamedQuery("Atomicinformation.findByEntityActiveAndProjectIDAndIsCurrentVersion", Atomicinformation.class);
        query.setParameter("entityActive", entityActive);
        query.setParameter("projectID", project);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        List<Atomicinformation> results = query.getResultList();
        return results;
    }
    
    /**
     * findRangeEntityActiveAndProjectIDAndISCurrentVersion method. The purpose of this method is to return a specific number of Atomicinformation 
     * entities as a List<> from a database that match the criteria for a specific project and  the EntityActive and isCurrentVersion 
     * attributes for the entity
     * 
     * @param range an array of integers used to identify the size to be returned List<>
     * @param entityActive can either be active entity or not by setting True or False 
     * @param project is the project entity to matched on
     * @param isCurrentVersion can either be the most current version of an entity or not by setting True or False 
     * @return a List<Atomicinformation> entities found in the database matching the criteria for the parameters of the method
     */
    public List<Atomicinformation> findRangeEntityActiveAndProjectIDAndISCurrentVersion(int[] range, Boolean entityActive, Project project, boolean isCurrentVersion){
        TypedQuery<Atomicinformation> query = em.createNamedQuery("Atomicinformation.findByEntityActiveAndProjectIDAndIsCurrentVersion", Atomicinformation.class);
        query.setParameter("entityActive", entityActive);
        query.setParameter("projectID", project);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        List<Atomicinformation> results = query.getResultList();
        return results;
    }
    
    /**
     * countEntityActiveAndProjectIDAndIsCurrentVersion method. This method returns an integer of the number of Atomicinformation entities
     * found in the database that match the criteria for a specific project and the EntityActive and isCurrentVersion attributes 
     * for the entity
     * 
     * @param project is the project entity to matched on
     * @param entityActive can either be active entity or not by setting True or False 
     * @param isCurrentVersion can either be the most current version of an entity or not by setting True or False 
     * @return an integer of the number of entities in the database that match the criteria
     */
    public int countEntityActiveAndProjectIDAndIsCurrentVersion(Boolean entityActive, Project project, boolean isCurrentVersion){
        TypedQuery<Atomicinformation> query = em.createNamedQuery("Atomicinformation.findByEntityActiveAndProjectIDAndIsCurrentVersion", Atomicinformation.class);
        query.setParameter("entityActive", entityActive);
        query.setParameter("projectID", project);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        List<Atomicinformation> results = query.getResultList();
        return results.size();
    }
    
    /**
     * findByEntityActiveAndProjectID method. The purpose of this method is to return a List<> of Atomicinformation entities
     * from a database that match the criteria for a specific project and the EntityActive attributes for the entity 
     * 
     * @param project is the project entity to matched on
     * @param entityActive can either be active entity or not by setting True or False 
     * @return a List<Atomicinformation> entities found in the database matching the criteria for the parameters of the method
     */
    public List<Atomicinformation> findByEntityActiveAndProjectID(Boolean entityActive, Project project){
        TypedQuery<Atomicinformation> query = em.createNamedQuery("Atomicinformation.findByEntityActiveAndProjectID", Atomicinformation.class);
        query.setParameter("entityActive", entityActive);
        query.setParameter("projectID", project);
        List<Atomicinformation> results = query.getResultList();
        return results;
    }
    //END LEE BAKER GENERATED CODE 
}
