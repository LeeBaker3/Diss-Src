/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filemanagement;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.ArtefactController;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.JsfUtil;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.odftoolkit.simple.TextDocument;
 

/**
 *
 * @author Lee Baker
 */
@Stateless
@Named("uploadfile")
public class UploadFile {

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")){
            if (cd.trim().startsWith("filename")){
                String filename = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);//MSIE fix
            }
        }
        return null;
    }
    @Inject
    private ArtefactController artefactController;
    
    private Part file;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    
    
    public String upload() throws IOException{   
    try {
            InputStream inputStream = file.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(getFilename(file));
         
            Long fileSizeLong;
            fileSizeLong = file.getSize();

            if(fileSizeLong > Integer.MAX_VALUE){
                return "FileToBig";
            }
            else
            {
                int fileSizeInt;
                fileSizeInt = fileSizeLong.intValue();

                byte[] buffer = new byte[fileSizeInt];
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
