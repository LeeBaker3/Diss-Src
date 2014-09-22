/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.businessrules;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.ArtefactController;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation.Artefactatomicinformation;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation.ArtefactatomicinformationController;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.JsfUtil;
import java.util.ResourceBundle;
import javax.ejb.Stateless;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *  UpdateArtefactAtomicInformationLinks Class. When an Artefact is updated to a new version references to 
 *  the Atomic Information entities from the old version of the Artefact entity can be created for the new 
 *  version of the Artefact entity. This class is used to create new ArtefactAtomicInformation entities and 
 *  copy the references
 * 
 *  @author Lee Baker
 *  @version 1.0
 */

//START LEE BAKER GENERATED CODE
@Stateless
@Named("updateArtefactAtomicInformationLinks")

public class UpdateArtefactAtomicInformationLinks {

    @Inject
    private ArtefactatomicinformationController artefactatomicinformationController;
    @Inject 
    private ArtefactController artefactController;
    int count;// number of new artefactatomicinformation entities to be created
    private boolean update = false;// set to true if new artefactatomicinformation entities are to be created during update
    private DataModel<Artefactatomicinformation> oldItems;// DataModel of Artefactatomicinformation entities for the old Artetact Entity
    private Artefact oldArtefact;// holds a reference to the old version of the Artefact entity
    private Artefact newArtefact;// holds a reference to the new version of the Artefact entity
    
    public DataModel<Artefactatomicinformation> getOldItems() {
        return oldItems;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
    
    /**
     *  update method. The is method determines if the update flag has been set to true when a Artefact entity
     *  has had a version update. If the flag is set to true then new artefactatomicinformation entities
     *  are created. If the flag is set to false then no new artefactatomicinformation entities are created 
     *  for the new version of the Artefact entity. When the process is finished a string is returned that 
     *  represent the xhtml JSF page to be displayed.
     * 
     *  @return string of the .xhtml JSF page to be navigated too
     */
    public String update() {
        String result = null;
        try {
             if (isUpdate()== true){
                    newArtefact = artefactController.getCurrent();
                    oldArtefact = artefactController.getOld();
                    createDataModel();
                }              
                result = artefactController.update();
            
            if (isUpdate()== true){
                if(result.equals("/Faces/artefact/View")){
                    copyEntities();
                }
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
        setUpdate(false);
        return result;
    }
    
    /**
     *  createDataModel method. Creates a DataModel of the  artefactatomicinformation entities that reference old version of the Artefact entity
     *  When the the DataModel is created the number of new entities to be created is identified and set in the count 
     *  property
     */
    public void createDataModel() {
        oldItems = new ListDataModel(artefactatomicinformationController.getSaveRetrieve().findByEntityActiveAndArtefactIDAndIsCurrentVersion(true, oldArtefact, true));
        count = oldItems.getRowCount();
    }    
    
    /**
     *  copyEntities method. Loops through each of the artefactatomicinformation entities in the DataModel for the old version of the Artefact entity
     *  and creates new artefactatomicinformation entities that reference the same atomicinformation entities as 
     *  original artefactatomicinformation. 
     */
    private void copyEntities (){
        for (int i = 0; i < count; i++) {
            getOldItems().setRowIndex(i);
            Artefactatomicinformation oldArtefactatomicinformation = getOldItems().getRowData();       
            artefactatomicinformationController.setCurrent(oldArtefactatomicinformation);
            artefactatomicinformationController.prepareUpdateVersion();
            artefactatomicinformationController.getCurrent().setArtefactID(newArtefact);
            artefactatomicinformationController.update();
        }
    }
}
//END LEE BAKER GENERATED CODE
