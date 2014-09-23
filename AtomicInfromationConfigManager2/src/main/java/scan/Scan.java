/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scan;

import com.mycompany.atomicinformationconfigurationmanager.businessrules.SelectedProject;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.ArtefactController;
import com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.Atomicinformation;
import com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.AtomicinformationController;
import com.mycompany.atomicinformationconfigurationmanager.entities.project.Project;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.JsfUtil;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ResourceBundle;
import javax.inject.Inject;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.ini4j.InvalidFileFormatException;

/**
 * Scan Class. The purpose of this abstract class is to scan a file in an attempt to find text that matches the text
 * of the content attribute of an Artefact entity. The text is extracted from the file and 'cleaned' using a 'cleanString' 
 * method and returned in a manner to allow easier matching against Atomicinformation entity content attribute. If any 
 * Atomicinformation is found this is passed to a createArtefactAtomicInfoRecords class for processing other a null is returned.
 * 
 * The class implements an abstract method 'scan' which is implemented in different if derived classes for different file 
 * formats. The class supports methods for checking that a file is available and of a support file format. 
 * 
 * @author Lee Baker
 * @version 1.0
 */

//START LEE BAKER GENERATED CODE
abstract public class Scan {

    @Inject
    private AtomicinformationController atomicinformationController;
    @Inject
    private ArtefactController artefactController;
    @Inject
    private SelectedProject selectedProject;
    @Inject 
    private CreateArtefactAtomicInfoRecords createArtefactAtomicInfoRecords;

    /**
     * scanArtefact method. This is the main control method the class and coordinates the interaction of other methods to achieve the purpose
     * of this class. 
     * 
     * @return String value that either be the navigation path to a JSF page to create database records of Atomicinformation entities found
     * or null if an errors occurs or now Atomicinformation entities are found.
     */
    public String scanArtefact(){    
        List<Atomicinformation> listAtomicInfoAll;//List of all Atomicinformation entities related to the Project property of the class
        List<Atomicinformation> listAtomicInfoFound;//List of Atomicinformation entities found in the artefactFile attribute of the 'artefact' property 
        Artefact artefact; //Artefact entity that hold the file as an attribute artefactFile to be scanned
        Project project;//Project entity that the Artefact and List<> of Atomicinformation belong too

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
                return null;
            }
            else
            {
                return createArtefactAtomicInfoRecords.createInfoRecords(listAtomicInfoFound); 
            }
        } 
        catch (FileNotFoundException  e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("NoFileAvailabletoScan"));
            return null;  
        }
        catch (InvalidFileFormatException e){
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("ScanningOfThisFileFormatIsNotSupported"));
            return null;
        }
        catch (Exception e){
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("CouldNotScanDocument"));
            return null;
        }
    } 
    
    /**
     * Scan method. This class is used to scan the file and return a list of Atomicinformation entities that
     * have been found. This method is implemented differently in derived classed based on the file format.
     * 
     * @param artefact Artefact entity that hold the file as an attribute artefactFile to be scanned
     * @param projectAtomicinfo List of all Atomicinformation entities to be searched for in file
     * @return List of all Atomicinformation entities found in file
     */
    abstract public List<Atomicinformation> scan (Artefact artefact, List<Atomicinformation> projectAtomicinfo);
    
    /**
     * cleanString method. This method is passed a string of text, the 'dirty string' and returns a 
     * 'cleaned string'. The cleaning process involves removing white spaces, accents and making all
     * characters lowercase. This process could be extend to remove punctuation as well.  
     * 
     * The purpose of this process is simplify matching between two strings.
     * 
     * @param dirtySrString a string that is unprocessed and is to be cleaned
     * @return returns a string that has been cleaned
     */
    protected String cleanString(String dirtySrString){
       String cleanString;
       cleanString = StringUtils.lowerCase(dirtySrString);
       cleanString = StringUtils.deleteWhitespace(cleanString);
       cleanString = StringUtils.stripAccents(cleanString);
       return cleanString;
    }
    
    /**
     * fileAvailable method. Simple check to ensure that the Artefact entity has a Byte[] representation of the 
     * file available to scan. If not an error is thrown FileNotFoundException
     * 
     * @param artefact Artefact entity to be checked
     * @throws FileNotFoundException 
     */
    protected void fileAvailable(Artefact artefact) throws FileNotFoundException {
            if (artefact.getArtefactFile() == null || artefact.getArtefactFile().length == 0){
                throw new FileNotFoundException();
            }
    }
    
    /**
     * supportedFileType method. Simple check to ensure that the Artefact entity file format is of a type
     * that is supported for scanning f not an error is thrown InvalidFileFormatException
     * 
     * @param artefact Artefact entity to be checked file attribute to be checked
     * @throws InvalidFileFormatException 
     */
    protected void supportedFileType(Artefact artefact) throws InvalidFileFormatException {
        String fileExt = FilenameUtils.getExtension(artefact.getArtefactFilename());
        /*  This code could be extended/modified to check for additional file types that are pulled from 
        *   db table of config file
        */
        if (!"odt".equals(fileExt)){
            throw new InvalidFileFormatException("File type not supported for scanning. File Type is: " + fileExt);
        }
    }
}
//END LEE BAKER GENERATED CODE
