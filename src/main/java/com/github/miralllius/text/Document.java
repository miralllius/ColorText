package com.github.miralllius.text;

import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.ArrayList;
import java.util.List;

import static com.github.miralllius.filemanagement.FileMapper.mapParagraphToWordArray;
import static com.github.miralllius.filemanagement.FileMapper.mapWordToRun;

@UtilityClass
public class Document {

    public void processDocument(XWPFDocument document) {
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            processParagraph(paragraph);
        }
    }

    private void processParagraph(XWPFParagraph paragraph) {
        List<String> allText = new ArrayList<>();
        int originalNbRuns = paragraph.getRuns().size();
        for (XWPFRun run : paragraph.getRuns()) {
            allText.add(run.getText(0));
        }
        for (String text : allText) {
            processRun(paragraph, text);
        }
        for (int i = 0; i < originalNbRuns; i++) {
            paragraph.removeRun(0);
        }
    }

    private void processRun(XWPFParagraph paragraph, String text) {
        Word[] wordsDto = mapParagraphToWordArray(text);
        for (Word wordObj : wordsDto) {
            wordObj.applyAllRules();
            mapWordToRun(paragraph, wordObj);
        }
    }

}
