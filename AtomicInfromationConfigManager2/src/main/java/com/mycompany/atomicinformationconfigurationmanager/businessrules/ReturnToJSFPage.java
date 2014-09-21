/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.businessrules;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation.ArtefactatomicinformationController;
import com.mycompany.atomicinformationconfigurationmanager.entities.artefactdistribution.ArtefactdistributionController;
import com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.AtomicinformationController;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *  ReturnToJSFPage Class 
 *  This class is used to to recreate the Model part of the MVC pattern for entities when navigating 
 *  back to a JSF page of entities when new records have been created in referenced entities in the ERD
 *  
 *  @author Lee Baker
 *  @version 1.0
 */

//START LEE BAKER GENERATED CODE
@Named("returnToJSFPage")
@Stateless
public class ReturnToJSFPage {
    
    @Inject
    private ArtefactatomicinformationController artefactatomicinformationController;
    @Inject
    private ArtefactdistributionController artefactdistributionController;
    @Inject
    private AtomicinformationController atomicinformationController;
    
    /**
     *  returnToArtefactView method. Recreates the Models (MVC Pattern) of ' Artefact Distribution '
     *  and ' Artefact Atomic Information ' entities when returning to the  
     *  View.xhtml JSF page
     * 
     *  @return the location of the View.xhtml JSF page for the artefact entity
     */
    public String returnToArtefactView(){
        artefactatomicinformationController.recreateModel();
        artefactdistributionController.recreateModel();
        return "/Faces/artefact/View";
    }
    
    /**
     *  returnToArtefactAtomicinformationCreate method. Recreates the Model (MVC Pattern) of the ' Atomic Information ' entity 
     *  when returning to the View.xhtml JSF page 
     * 
     *  @return the location of the CreateFromArtefact.xhtml JSF page for 
     *  the artefactatomicinformation entity
     */
    public String returnToArtefactAtomicinformationCreate(){
        atomicinformationController.recreateModel();
        return "/Faces/artefactatomicinformation/CreateFromArtefact";
    }
}
//END LEE BAKER GENERATED CODE
