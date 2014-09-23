/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scan;

import com.mycompany.atomicinformationconfigurationmanager.businessrules.ReturnToJSFPage;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.ArtefactController;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation.Artefactatomicinformation;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation.ArtefactatomicinformationController;
import com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.Atomicinformation;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.JsfUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * CreateArtefactAtomicInfoRecords Class. The purpose of this class is to identify an of a List<> Atomicinfromation entities 
 * passed to the class has previously been identified for an Artefact entity. For the Atomicinfromation entities that haven't 
 * previously been identified they are reported to a 3rd party. The class also implements a method to create new 
 * Artefactatomicinformation entities and persist them to the database for the newly identified Atomicinfromation entities.
 * 
 * @author Lee Baker
 * @version 1.0
 */

//START LEE BAKER GENERATED CODE
@Stateless
@Named("createArtefactAtomicInfoRecords")
public class CreateArtefactAtomicInfoRecords {

    
    private List<Atomicinformation> foundItems;//A List<> of Atomicinformation entities that will be checked to identify if they have previously been identified.
    private Map<Atomicinformation, Boolean> checked = new HashMap<>();//Used to get the Atomicinformation entities that have been selected tp create Artefactatomicinformation.
    private List<Atomicinformation> newItems;// A List<> of Atomicinformation entities that will be used to create Artefactatomicinformation entities.
    private int size;// Size is used to check that there are some newly identified Atomicinformation entities.
    @Inject
    ArtefactatomicinformationController artefactatomicinformationController;
    @Inject
    ArtefactController artefactController;
    @Inject
    ReturnToJSFPage returnToJSFPage;

    public CreateArtefactAtomicInfoRecords() {
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<Atomicinformation, Boolean> getChecked() {
        return checked;
    }

    public void setChecked(Map<Atomicinformation, Boolean> checked) {
        this.checked = checked;
    }
    
    public List<Atomicinformation> getFoundItems() {
        return foundItems;
    }

    public void setFoundItems(List<Atomicinformation> foundItems) {
        this.foundItems = foundItems;
    }

    public List<Atomicinformation> getNewItems() {
        return newItems;
    }

    
    public void setNewItems(List<Atomicinformation> newItems) {
        this.newItems = newItems;
    }
    
    /**
     * createInfoRecords method. This method is used to control the identify of new Atomicinformation from the ones previously found
     * and set the size property to the number of newly identified Atomicinformation entities.
     * 
     * @param foundItems List<> of Atomicinformation entities to be checked
     * @return a string representing the JSF page to be displayed
     */
    public String createInfoRecords (List<Atomicinformation> foundItems){ 
        setFoundItems(foundItems);
        newItemsFound();
        setSize(newItems.size());
       
        return "/Faces/artefactatomicinformation/ScanList";
    }
    
    /**
     * newItemsFound method. The purpose of this method is extract from the List<> of Atomicinfromation entities FoundItems
     * Atomicinfromation entities that haven't previously been identified for a given artefact and a return a List<> of 
     * Atomicinfromation that newly are discovered
     * 
     * @return List<> of Atomicinfromation that are newly discovered
     */
    private String newItemsFound(){
        List<Atomicinformation> items = getFoundItems();
        newItems = new ArrayList<>();
        List<Atomicinformation> currentItemsAtomic = new ArrayList<>();
        
        //Get List of existing Artefactatomicinformation entities for given Artefact entity and extract Atomicinformation entities
        try{
            List<Artefactatomicinformation> currentItems = artefactatomicinformationController.getSaveRetrieve().findByEntityActiveAndArtefactIDAndIsCurrentVersion(true, artefactController.getCurrent(), true);
            if (currentItems.isEmpty() == false){
                for (Artefactatomicinformation atomic: currentItems){
                    currentItemsAtomic.add(atomic.getAtomicInformationID());
                }
            }
            
            /*  If their are Artefactatomicinformation entities for given Artefact entity then check if any the FoundItems Atomicinformation is
            /   contained in the list. If no match is found then add the atomic information to a List<> of newly found Atomicinformation entities
            */
            if (currentItems.isEmpty() == false){
                for (Atomicinformation atomic: items){
                    if (currentItemsAtomic.contains(atomic) == false){
                        newItems.add(atomic);
                    }
                }
                return null;
            }
            else
            {
                newItems.addAll(items);
                return null;
            }
        }
        catch(Exception e)
        {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("ScanListArtefactatomicinformationFail"));
        }
        return returnToJSFPage.returnToArtefactView();
    }
    
    /**
     * create method. The purpose of this method is to take the selected Atomicinfromation entities from 3rd party, usually a JSF page 
     * and create new Artefactatomicinformation entities that establishes the relationship between the the Artefact entity and the 
     * Atomicinformation entity.
     * 
     * @return a string for the path of the JSF page to be navigated too
     */
    public String create() {
        try{
            for(Atomicinformation item: newItems){
                if(checked.get(item.getId()) !=null){
                    if(checked.get(item.getId())){
                        artefactatomicinformationController.prepareCreateFromArtefact();
                        artefactatomicinformationController.getCurrent().setAtomicInformationID(item);
                        artefactatomicinformationController.create("prepareCreateFromArtefact");
                    }
                }
            }
        }
        catch (Exception e){
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("ScanListArtefactatomicinformationFail"));
        }
        finally{
            checked.clear();
            newItems.clear();
        }
        
        return returnToJSFPage.returnToArtefactView();
    }
}
//END LEE BAKER GENERATED CODE
