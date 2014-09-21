/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.businessrules;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.ArtefactController;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation.ArtefactatomicinformationController;
import com.mycompany.atomicinformationconfigurationmanager.entities.artefactdistribution.ArtefactdistributionController;
import com.mycompany.atomicinformationconfigurationmanager.entities.distributionrecipient.DistributionrecipientController;
import com.mycompany.atomicinformationconfigurationmanager.entities.project.Project;
import com.mycompany.atomicinformationconfigurationmanager.entities.project.ProjectController;
import java.io.Serializable;
import java.util.Objects;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *  SelectedProject Class
 *  This class is used to hold a reference to the currently selected project
 *  When a project is selected a call is made to all entity Models (MVC Pattern) to
 *  recreate the model based on the project that has been selected
 * 
 *  @author Lee Baker
 *  @version 1.0
 */
@Named("selectedProject")
@Stateful
@SessionScoped
public class SelectedProject implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private ProjectController projectController;
    @Inject
    private ArtefactController artefactController;
    @Inject
    private ArtefactatomicinformationController artefactatomicinformationController;
    @Inject
    private DistributionrecipientController distributionrecipientController;
    @Inject
    private ArtefactdistributionController artefactdistributionController;
    
            
    private Project project;

    public Project getProject() {
        return project;
    }
    
    /**
     *  Recreates models of MVC pattern for entities that use the Project Primary Key
     *  as a Foreign Key reference 
     *  @param project selected
     */
    public void setProject(Project project) {
        this.project = project;
        if(project != projectController.getCurrent()){
            projectController.setCurrent(project);
            artefactController.recreateModel();
            artefactatomicinformationController.recreateModel();
            distributionrecipientController.recreateModel();
            artefactatomicinformationController.recreateModel(); 
            artefactdistributionController.recreateModel();
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.project);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SelectedProject other = (SelectedProject) obj;
        if (!Objects.equals(this.project, other.project)) {
            return false;
        }
        return true;
    }
    
    
}
