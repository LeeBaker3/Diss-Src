/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filemanagement;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.ArtefactController;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
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
    @Inject
    private ArtefactController artefactController;
    
    private Part file;
    private File outputFile;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    public String upload() throws IOException{   
        InputStream inputStream = file.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(getFilename(file));
        
        byte[] buffer = new byte[4096];
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
        
        return "Success";
    }

    public String uploadODF() throws IOException{
        try {
            TextDocument oldDocument = TextDocument.newTextDocument();
//            InputStream inputStream = file.getInputStream();
//            oldDocument = TextDocument.loadDocument(inputStream);
//            oldDocument.addParagraph("New text added");
//            oldDocument.save("C:\\Users\\Lee Baker\\Desktop\\TestFileUpdate.odt");
            // TO Be completed out
        } catch (Exception e) {
            
        }
        return "Success";
    }
    
    
    private static String getFilename(Part part){
        for (String cd : part.getHeader("content-disposition").split(";")){
            if (cd.trim().startsWith("filename")){
                String filename = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);//MSIE fix
            }
        }
        return null;
    }

}
