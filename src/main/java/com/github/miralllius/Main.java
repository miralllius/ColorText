package com.github.miralllius;

import com.github.miralllius.filemanagement.DocumentManager;
import com.github.miralllius.text.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Main {
    public static void main(String[] args) {
        DocumentManager documentManager = new DocumentManager();
        XWPFDocument document = documentManager.loadDocumentFromUserChoice();
        Document.processDocument(document);
        documentManager.writeDocument(document);
    }
}
