/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.base;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *  BaseSaveRetrieveAbstract interface. The class is based on the NetBeans Facade Interface template 
 *  and as modified for this project. The template provides a standard set of methods for CRUD including 
 *  create, edit, remove, count and find entities. The interface has been modified to include methods
 *  that carryout the same CRUD functions for entities based on the Boolean value of their EntityActive
 *  and IsCurrent attributes.
 * 
 *  @author Lee Baker
 *  @version 1.0
 */

//START OF IDE GENERATED CODE
public abstract class BaseSaveRetrieveAbstract<T> {
    private Class<T> entityClass;

    public BaseSaveRetrieveAbstract(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
    /**
     * findRange method. Returns a specific number of a Type of entity as a List<> 
     * @param range an array of integers used to identify the size to be returned List<>
     * @return List<> of entity entities found
     */
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    /**
     * findAll method. Returns a List<> of a Type of entity
     * @return List<> of entity entities found
     */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    /**
     * count method. Return an integer of the number of entities in the database that match an entity Type
     * @return integer returned with the number if entities found
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Number) q.getSingleResult()).intValue();
    }
    //END OF IDE GENERATED CODE
    
    //START LEE BAKER GENERATED CODE
    /**
     * entityInactive method. This method was added so that when a entity is deleted
     * it is removed from the database. Instead its EntityActive attribute is set
     * to false.
     * @param entity entity to be persisted
     */
    public void entityInactive (T entity) {
       getEntityManager().merge(entity); 
    }
     
    /**
     * findAllEntityActiveIsCurrentVersion method. This method returns a List<> of all entities that match the 
     * criteria passed for the EntityActive and isCurrentVersion attributes 
     * @param entityActive entity attribute criteria set to True or False
     * @param isCurrentVersion entity attribute criteria set to True or False
     * @return a List<> of entity objects based on the Type of object determined by the class that inherits
     * this interface
     */
    public List<T> findAllEntityActiveIsCurrentVersion(Boolean entityActive, Boolean isCurrentVersion) {
        TypedQuery<T> query = getEntityManager().createNamedQuery(entityClass.getSimpleName() + ".findByEntityActiveAndIsCurrentVersion", entityClass);
        query.setParameter("entityActive", entityActive);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        List<T> results = query.getResultList();
        return results;
    }
    
    /**
     * findRangeEntityActiveIsCurrentVersion method. This method returns a List<> of entities of a specific size 
     * that match the criteria passed for the EntityActive and isCurrentVersion attributes
     * @param range an array of integers that is used to determine the number of entities to be returned 
     * @param entityActive entity attribute criteria set to True or False
     * @param isCurrentVersion entity attribute criteria set to True or False
     * @return a List<> of entity objects based on the Type of object determined by the class that inherits
     * this interface
     */
    public List<T> findRangeEntityActiveIsCurrentVersion(int[] range, Boolean entityActive, Boolean isCurrentVersion) {
        TypedQuery<T> query = getEntityManager().createNamedQuery(entityClass.getSimpleName() + ".findByEntityActiveAndIsCurrentVersion", entityClass);
        query.setParameter("entityActive", entityActive);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        List<T> results = query.getResultList();
        return results;
    }
    
    /**
     * countEntityActiveIsCurrentVersion method.  This method returns an integer of the number of entities
     * found in the database that match the criteria passed for the EntityActive and isCurrentVersion attributes
     * @param entityActive entity attribute criteria set to True or False
     * @param isCurrentVersion entity attribute criteria set to True or False
     * @return an integer of the number of entities in the database that match the criteria
     */
    public int countEntityActiveIsCurrentVersion(Boolean entityActive, Boolean isCurrentVersion){
        TypedQuery<T> query = getEntityManager().createNamedQuery(entityClass.getSimpleName() + ".findByEntityActiveAndIsCurrentVersion", entityClass);
        query.setParameter("entityActive", entityActive);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        List<T> results = query.getResultList();
        return results.size();
    }
}
//END LEE BAKER GENERATED CODE
