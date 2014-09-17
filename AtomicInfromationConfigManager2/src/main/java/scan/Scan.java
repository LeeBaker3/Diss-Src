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
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.ini4j.InvalidFileFormatException;

abstract public class Scan {

    @Inject
    private AtomicinformationController atomicinformationController;
    @Inject
    private ArtefactController artefactController;
    @Inject
    private SelectedProject selectedProject;
    @Inject 
    private CreateArtefactAtomicInfoRecords createArtefactAtomicInfoRecords;

    //public List<Atomicinformation> scanArtefact(){
    public void scanArtefact(){    
        List<Atomicinformation> listAtomicInfoAll;
        List<Atomicinformation> listAtomicInfoFound;
        Artefact artefact;
        Project project;

        try {
            artefact = artefactController.getCurrent();
            fileAvailable(artefact);
            supportedFileType(artefact);
            
            project = selectedProject.getProject();
            listAtomicInfoAll = atomicinformationController.getSaveRetrieve().findByEntityActiveAndProjectID(true, project);
            listAtomicInfoFound = scan(artefact, listAtomicInfoAll);
            if(listAtomicInfoFound.isEmpty())
            {
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FileScannedNothingFound"));
                //return null;
            }
            else
            {
                createArtefactAtomicInfoRecords.CreateInfoRecords(listAtomicInfoFound, artefact); 
                //return listAtomicInfoFound;
            }
        } 
        catch (FileNotFoundException  e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("NoFileAvailabletoScan"));
            //return null;  
        }
        catch (InvalidFileFormatException e){
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("ScanningOfThisFileFormatIsNotSupported"));
            //return null;
        }
        catch (Exception e){
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("CouldNotScanDocument"));
            //return null;
        }
    } 
    
    abstract public List<Atomicinformation> scan (Artefact artefact, List<Atomicinformation> projectAtomicinfo);
    
    protected String cleanString(String dirtySrString){
       String cleanString;
       cleanString = StringUtils.lowerCase(dirtySrString);
       cleanString = StringUtils.deleteWhitespace(cleanString);
       cleanString = StringUtils.stripAccents(cleanString);
       return cleanString;
    }
    
    protected void fileAvailable(Artefact artefact) throws FileNotFoundException {
            if (artefact.getArtefactFile() == null || artefact.getArtefactFile().length == 0){
                throw new FileNotFoundException();
            }
    }
    
    protected void supportedFileType(Artefact artefact) throws InvalidFileFormatException {
        String fileExt = FilenameUtils.getExtension(artefact.getArtefactFilename());
        /*  This code could be extended to check for additional file types that are pulled from 
        *   db table of config file
        */
        if (!"odt".equals(fileExt)){
            throw new InvalidFileFormatException("File type not supported for scanning. File Type is: " + fileExt);
        }
    }
}
