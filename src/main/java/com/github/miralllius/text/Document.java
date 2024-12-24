package com.github.miralllius.text;

import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import static com.github.miralllius.filemanagement.FileMapper.mapParagraphToWordArray;
import static com.github.miralllius.filemanagement.FileMapper.mapWordToRun;

@UtilityClass
public class Document {

    public static void processDocument(XWPFDocument document) {
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            processParagraph(paragraph);
        }
    }

    private static void processParagraph(XWPFParagraph paragraph) {
        Word[] wordsDto = mapParagraphToWordArray(paragraph);
        paragraph.removeRun(0); // Clear current runs to start from scratch, i.e. we remove all formating
        for (Word wordObj : wordsDto) {
            wordObj.applyAllRules();
            mapWordToRun(paragraph, wordObj);
        }
    }

}
