package com.github.miralllius.filemanagement;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class DocumentManager {

    private String pathToDocument = "";

    public XWPFDocument loadDocumentFromUserChoice() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Word Documents (.docx)", "docx"));

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.getName().endsWith(".docx")) {
                this.pathToDocument = selectedFile.getAbsolutePath();
                return loadDocumentFromPath(this.pathToDocument);
            } else {
                JOptionPane.showMessageDialog(null, "Seuls les documents .docx sont acceptés.");
            }
        } else {
            System.exit(0);
        }
        return null;
    }

    public void writeDocument(XWPFDocument document) {
        try (FileOutputStream fos = new FileOutputStream(addSuffixWithIndex(this.pathToDocument, "_avec_Couleurs")))
        {
            document.write(fos);
        } catch (FileNotFoundException e) {
            log.error("The specified file was not found: {}", e.getMessage());
        } catch (IOException e) {
            log.error("An error occurred while writing the document: {}", e.getMessage());
        } finally {
            try {
                document.close();
            } catch (IOException e) {
                log.error("An error occurred while closing the document: {}", e.getMessage());
            }
        }
    }

    private XWPFDocument loadDocumentFromPath(String path) {
        XWPFDocument document = null;
        try (FileInputStream fis = new FileInputStream(path))
        {
            document = new XWPFDocument(fis);
        } catch (FileNotFoundException e) {
            log.error("The specified file was not found: {}", e.getMessage());
        } catch (IOException e) {
            log.error("An error occurred while reading the document: {}", e.getMessage());
        }
        return document;
    }

    public static String addSuffixWithIndex(String absoluteFilePath, String suffix) {
        Path path = Paths.get(absoluteFilePath);
        String fileName = path.getFileName().toString();
        String directory = path.getParent().toString();
        String baseName = fileName.substring(0, fileName.lastIndexOf("."));
        String extension = fileName.substring(fileName.lastIndexOf("."));

        // Construct initial filename with suffix
        String newFileName = baseName + suffix + extension;
        File newFile = new File(directory, newFileName);

        // Check for existing files and add index if needed (without creating files)
        int index = 1;
        while (newFile.exists()) {
            newFileName = String.format("%s%s (%d)%s", baseName, suffix, index, extension);
            newFile = new File(directory, newFileName);
            index++;
        }

        return Paths.get(directory, newFileName).toAbsolutePath().toString();
    }
}
