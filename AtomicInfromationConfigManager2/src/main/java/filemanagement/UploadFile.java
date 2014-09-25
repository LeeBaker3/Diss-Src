/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filemanagement;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.ArtefactController;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.JsfUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
 

/**
 *  UploadFile Class. This class is used to upload an artefact to the database
 *  The class extract the filename from the Header of the Part and copies it to the filename 
 *  attribute of the Artefact entity. The upload file is attached to the Artefact
 *  entity in the file attribute as a Byte array
 * 
 *  @author Lee Baker
 *  @version 1.0
 */

//START LEE BAKER GENERATED CODE
@Stateless
@Named("uploadfile")
public class UploadFile {
    @Inject
    private ArtefactController artefactController;
    private Part file; //Part representation of upload File

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    /**
     * getFilename method. The method is used to extract the filename from the header
     * of the part and return a string value. Filename include file extension.
     * 
     * The code for this method was reused and is available at 
     * https://github.com/karrirasinmaki/EveryConvo/blob/master/EveryConvoAPI/src/main/java/fi/raka/everyconvo/api/servelets/UploadServelet.java
     * 
     * @param part the upload file received via http POST
     * @return string representing the upload filename
     */
    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")){
            if (cd.trim().startsWith("filename")){
                String filename = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);//MSIE fix
            }
        }
        return null;
    }
    
    /**
     * upload method. This method is used to upload a file, convert it to a Byte[], attach it to
     * artefactFile attribute of the Artefact entity and persist it to the database. The method also
     * attaches the filename string of the Part to the artefactFilename attribute of the Artefact entity.
     * The File size is limited to MAX_VALUE for an integer due the Byte[] being set by an integer.
     * 
     * @return string representation of the JSF page to be navigated to when upload has finished
     * @throws IOException 
     */
    public String upload() throws IOException{   
    try {
            InputStream inputStream = file.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(getFilename(file));
         
            Long fileSizeLong;
            fileSizeLong = file.getSize();
            
            //Check file isn't to big for Byte[] to hold
            if(fileSizeLong > Integer.MAX_VALUE){
                throw new Exception("File to big");
            }
            else
            /*  If the file isn't to big the Inputstream of the part
                is read into the Byte[] by the following section of code
            */    
            {
                int fileSizeInt;// Used to set Byte[] to correct size
                fileSizeInt = fileSizeLong.intValue();

                byte[] buffer = new byte[fileSizeInt];// Byte[] to hold upload file
                int bytesRead = 0;

                while(true){
                    bytesRead = inputStream.read(buffer);
                    if(bytesRead > 0){
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    else {
                        break;
                    }  
                }
                
                outputStream.close();
                inputStream.close();
                
                //Attaches the filename and file to the attributes of the Artefact entity then persists it to the database
                artefactController.getCurrent().setArtefactFilename(getFilename(file));
                artefactController.getCurrent().setArtefactFile(buffer);
                artefactController.update();
               
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactUpload"));
                return "Success";
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("UploadFailed"));
            return "Success";
        }
    }
}
//END LEE BAKER GENERATED CODE
