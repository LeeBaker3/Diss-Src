/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.project;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
import com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.Atomicinformation;
import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseEntity;
import com.mycompany.atomicinformationconfigurationmanager.entities.distributionrecipient.Distributionrecipient;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *  Project Class. This class is an object representation of the Artefact Entity in the database. 
 *  All Artefact in the system must belong to a project. The Project class represent this Physical 
 *  project that Artefacts belong to too.
 * 
 *  The class uses the JPA (Java Persistence API) to access and persist data in a relational database. 
 *  The class also inherits several methods from the BaseEntity class. All Attributes of the  entity are 
 *  Annotated according to the JPA specification to meet the underlying database schema.
 *  
 *  The class is based on the NetBeans Entity template and modified for this project including inheriting 
 *  from the Base Entity and the addition of several NamedQueries with multiple Where clauses.
 *  
 *  No methods have been commented as they conform and are believed to be self explanatory and consistent with JPA   
 *  @author Lee Baker
 *  @version 1.0
 */
@Entity
@Table(name = "project")
@AttributeOverride(name = "id", column = @Column(name = "ProjectID"))

@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Project.findByEntityActiveAndProjectIDAndIsCurrentVersion", query = "SELECT p FROM Project p WHERE p.id = :projectID AND p.entityActive = :entityActive AND p.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Project.findByEntityActiveAndProjectReferenceAndIsCurrentVersion", query = "SELECT p FROM Project p WHERE p.projectReference = :projectReference AND p.entityActive = :entityActive AND p.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Project.findByEntityActiveAndProjectNameAndIsCurrentVersion", query = "SELECT p FROM Project p WHERE p.projectName = :projectName AND p.entityActive = :entityActive AND p.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Project.findByEntityActiveAndVersionNumberAndIsCurrentVersion", query = "SELECT p FROM Project p WHERE p.versionNumber = :versionNumber AND p.entityActive = :entityActive AND p.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Project.findByEntityActiveAndIsCurrentVersion", query = "SELECT p FROM Project p WHERE p.isCurrentVersion = :isCurrentVersion AND p.entityActive = :entityActive"),
    @NamedQuery(name = "Project.findByEntityActiveAndPreviousVersionReferenceAndIsCurrentVersion", query = "SELECT p FROM Project p WHERE p.previousVersionReference = :previousVersionReference AND p.entityActive = :entityActive AND p.isCurrentVersion = :isCurrentVersion"),
    
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findByEntityActive", query = "SELECT p FROM Project p WHERE p.entityActive = :entityActive"),
    @NamedQuery(name = "Project.findByProjectID", query = "SELECT p FROM Project p WHERE p.id = :projectID"),
    @NamedQuery(name = "Project.findByProjectReference", query = "SELECT p FROM Project p WHERE p.projectReference = :projectReference"),
    @NamedQuery(name = "Project.findByProjectName", query = "SELECT p FROM Project p WHERE p.projectName = :projectName"),
    @NamedQuery(name = "Project.findByVersionNumber", query = "SELECT p FROM Project p WHERE p.versionNumber = :versionNumber"),
    @NamedQuery(name = "Project.findByIsCurrentVersion", query = "SELECT p FROM Project p WHERE p.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Project.findByPreviousVersionReference", query = "SELECT p FROM Project p WHERE p.previousVersionReference = :previousVersionReference"),
    @NamedQuery(name = "Project.findByEntityActive", query = "SELECT p FROM Project p WHERE p.entityActive = :entityActive")})
public class Project extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ProjectReference")
    private String projectReference;
    @Size(max = 45)
    @Column(name = "ProjectName")
    private String projectName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectID")
    private Collection<Atomicinformation> atomicinformationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectID")
    private Collection<Artefact> artefactCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectID")
    private Collection<Distributionrecipient> distributionrecipientCollection;

    public Project() {
    }

    public Project(Integer projectID) {
        this.id = projectID;
    }

    public Project(Integer projectID, int versionNumber, boolean isCurrentVersion, boolean entityActive) {
        this.id = projectID;
        this.versionNumber = versionNumber;
        this.isCurrentVersion = isCurrentVersion;
        this.entityActive = entityActive;
    }

    public String getProjectReference() {
        return projectReference;
    }

    public void setProjectReference(String projectReference) {
        this.projectReference = projectReference;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @XmlTransient
    public Collection<Atomicinformation> getAtomicinformationCollection() {
        return atomicinformationCollection;
    }

    public void setAtomicinformationCollection(Collection<Atomicinformation> atomicinformationCollection) {
        this.atomicinformationCollection = atomicinformationCollection;
    }

    @XmlTransient
    public Collection<Artefact> getArtefactCollection() {
        return artefactCollection;
    }

    public void setArtefactCollection(Collection<Artefact> artefactCollection) {
        this.artefactCollection = artefactCollection;
    }

    @XmlTransient
    public Collection<Distributionrecipient> getDistributionrecipientCollection() {
        return distributionrecipientCollection;
    }

    public void setDistributionrecipientCollection(Collection<Distributionrecipient> distributionrecipientCollection) {
        this.distributionrecipientCollection = distributionrecipientCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getProjectName() + " " + getProjectReference();
    }
}
