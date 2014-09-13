/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scan;

import com.mycompany.atomicinformationconfigurationmanager.businessrules.SelectedProject;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.ArtefactController;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.ArtefactSaveRetrieve;
import com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.Atomicinformation;
import com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.AtomicinformationController;
import com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.AtomicinformationSaveRetrieve;
import com.mycompany.atomicinformationconfigurationmanager.entities.project.Project;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.JsfUtil;
import com.sun.xml.bind.v2.TODO;
import java.util.List;
import java.util.ResourceBundle;
import javax.inject.Inject;

abstract public class Scan {

    @Inject
    private AtomicinformationController atomicinformationController;
    @Inject
    private ArtefactController artefactController;
    @Inject
    private SelectedProject selectedProject;
    private AtomicinformationSaveRetrieve saveRetrieve = new AtomicinformationSaveRetrieve();

    public List<Atomicinformation> scanArtefact(){
        List<Atomicinformation> listAtomicInfoAll;
        List<Atomicinformation> listAtomicInfoFound;
        Artefact artefact;
        Project project;
        CreateArtefactAtomicInfoRecords createArtefactAtomicInfoRecords = new CreateArtefactAtomicInfoRecords();
        
        try {
            artefact = artefactController.getCurrent();
            if (artefact.getArtefactFile()!= null){
               project = selectedProject.getProject();
                listAtomicInfoAll = saveRetrieve.findByEntityActiveAndProjectID(true, project);
                listAtomicInfoFound = scan(artefact, listAtomicInfoAll);
                createArtefactAtomicInfoRecords.CreateInfoRecords(listAtomicInfoAll, artefact); 
                return listAtomicInfoFound;
            }
        else{
            throw new Exception("No document available or selected to scan");
        }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("NoArtefactAvailabletoScan"));
            return null;
        } 
    }
    
    abstract public List<Atomicinformation> scan (Artefact artefact, List<Atomicinformation> projectAtomicinfo);
}
