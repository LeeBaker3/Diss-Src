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
import sun.util.calendar.ZoneInfoFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.common.navigation.TextDocumentSelection;
 

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
        artefactController.update();
        
        return "Success";
    }

    public String uploadODF() throws IOException{
        try {
            InputStream inputStream = file.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(oldDocument);
            TextDocument newTextDocument;
            TextDocument oldDocument = TextDocument.newTextDocument();

            oldDocument = TextDocument.loadDocument(inputStream);
            oldDocument.addParagraph("New text added");
            oldDocument.save(outputFile);
            // TO Be completed out
        } catch (Exception e) {
            
        }
        
        
        return "To Do";
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
