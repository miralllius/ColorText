package com.github.miralllius.filemanagement;

import com.github.miralllius.text.Color;
import com.github.miralllius.text.Word;
import lombok.experimental.UtilityClass;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class FileMapper {
    private static final String SPACE_CHAR = "\\s+";

    public static Word[] mapParagraphToWordArray(String text) {
        String[] words = text.split("(?<=\\p{L})(?=\\P{L})|(?<=\\P{L})(?=\\p{L})"); // Splits the text by punctuation and space while keeping them.
        // I.e. "J'aime ." becomes ["J", "'", "aime", " ", "."]
        return mapStringsToWords(words);
    }

    /**
     * Maps a Word to a XWPFRun with the corresponding colors. Add a space after the word.
     */
    public static void mapWordToRun(XWPFParagraph paragraph, Word wordObj) {
        String word = wordObj.getText();
        List<Color> colors = wordObj.getColors();

        for (int i = 0; i < word.length(); i++) {
            XWPFRun run = paragraph.createRun();
            run.setText(String.valueOf(word.charAt(i)));
            run.setColor(colors.get(i).getHexCode());
        }
    }

    private Word[] mapStringsToWords (String[] words) {
        return Arrays.stream(words)
                .filter(string -> !string.isEmpty())
                .map(Word::new)
                .toArray(Word[]::new);
    }

}
