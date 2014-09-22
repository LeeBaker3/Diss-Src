/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.artefactdistribution;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseSaveRetrieveAbstract;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *  ArtefactdistributionSaveRetrieve Class. This class inherits from the base class BaseSaveRetrieve and implements
 *  the BaseSaveRetrieve Interface via the BaseSaveRetrieve class. The primary purpose of this class 
 *  is to act as the the database access layer for the Artefactdistribution Entity. The methods on the BaseSaveRetrieve 
 *  Class and BaseSaveRetrieve Interface are extend in this class for the specific needs of the Artefactdistribution entity.
 *  The class is predominantly used by the Controller part of the MVC pattern  
 * 
 *  The class uses the JPA (Java Persistence API) to access and persist data in a relational database. 
 *  
 *  The class is based on the NetBeans Facade template and modified for this project and makes use of
 *  several NamedQueries in the Artefactdistribution Class.
 * 
 * @author Lee Baker
 * @version 1.0
 */

//START IDE GENERATED CODE
@Stateless
public class ArtefactdistributionSaveRetrieve extends BaseSaveRetrieveAbstract<Artefactdistribution> {
    @PersistenceContext(unitName = "AtomicInformationConfigManagerPU")
    private EntityManager em;

    public ArtefactdistributionSaveRetrieve() {
        super(Artefactdistribution.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    //END IDE GENERATED CODE
    
    //START LEE BAKER GENERATED CODE
    /**
     * findByEntityActiveAndArtefactIDAndIsCurrentVersion method. The purpose of this method is to return a List<> of Artefactdistribution entities
     * from a database that match the criteria for a specific artefact and the EntityActive and isCurrentVersion attributes for the entity 
     * 
     * @param artefact is the artefact entity to matched on
     * @param entityActive can either be active entity or not by setting True or False 
     * @param isCurrentVersion can either be the most current version of an entity or not by setting True or False 
     * @return a List<Artefactdistribution> entities found in the database matching the criteria for the parameters of the method
     */
    public List<Artefactdistribution> findByEntityActiveAndArtefactIDAndIsCurrentVersion (Artefact artefact, boolean entityActive, boolean isCurrentVersion){
        TypedQuery<Artefactdistribution> query = em.createNamedQuery("Artefactdistribution.findByEntityActiveAndArtefactIDAndIsCurrentVersion", Artefactdistribution.class);
        query.setParameter("artefactID", artefact);
        query.setParameter("entityActive", entityActive);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        List<Artefactdistribution> results = query.getResultList();
        return results;
    }
    
    /**
     * findRangeEntityActiveAndArtefactIDAndIsCurrentVersion method. The purpose of this method is to return a specific number of Artefactdistribution 
     * entities as a List<> from a database that match the criteria for a specific artefact and the EntityActive and isCurrentVersion 
     * attributes for the entity
     * 
     * @param range an array of integers used to identify the size to be returned List<>
     * @param entityActive can either be active entity or not by setting True or False 
     * @param artefact is the artefact entity to matched on
     * @param isCurrentVersion can either be the most current version of an entity or not by setting True or False 
     * @return a List<Artefactdistribution> entities found in the database matching the criteria for the parameters of the method
     */
    public List<Artefactdistribution> findRangeEntityActiveAndArtefactIDAndIsCurrentVersion (int[] range, Boolean entityActive, Artefact artefact, boolean isCurrentVersion){
        TypedQuery<Artefactdistribution> query = em.createNamedQuery("Artefactdistribution.findByEntityActiveAndArtefactIDAndIsCurrentVersion", Artefactdistribution.class);
        query.setParameter("artefactID", artefact);
        query.setParameter("entityActive", entityActive);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        List<Artefactdistribution> results = query.getResultList();
        return results;
    }
    
    /**
     * countEntityActiveAndArtefactIDIAndsCurrentVersion method. This method returns an integer of the number of Artefactdistribution entities
     * found in the database that match the criteria for a specific artefact and the EntityActive and isCurrentVersion attributes 
     * for the entity
     * 
     * @param artefact is the artefact entity to matched on
     * @param entityActive can either be active entity or not by setting True or False 
     * @param isCurrentVersion can either be the most current version of an entity or not by setting True or False 
     * @return an integer of the number of entities in the database that match the criteria
     */
    public int countEntityActiveAndArtefactIDIAndsCurrentVersion(Artefact artefact, Boolean entityActive, boolean isCurrentVersion){
        TypedQuery<Artefactdistribution> query = em.createNamedQuery("Artefactdistribution.findByEntityActiveAndArtefactIDAndIsCurrentVersion", Artefactdistribution.class);
        query.setParameter("artefactID", artefact);
        query.setParameter("entityActive", entityActive);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        List<Artefactdistribution> results = query.getResultList();
        return results.size();
    }
    //END LEE BAKER GENERATED CODE 
}
