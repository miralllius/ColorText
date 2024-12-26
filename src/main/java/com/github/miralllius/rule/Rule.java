package com.github.miralllius.rule;

import com.github.miralllius.text.Color;
import com.github.miralllius.text.Word;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Set;

import static com.github.miralllius.rule.RuleConstants.*;

public record Rule(String id, int order, int numberLetterAffected, @NotNull Color color,
                   @NotNull RuleExcpt exception, @Nullable String description) {

    public void execute(Word word) {
        if (ruleIsValid(word)) {
            applyRule(word);
        }
    }

    private boolean ruleIsValid(Word word){
        return this.numberLetterAffected <= word.length() && !this.exception.exceptions.contains(word.getText().toLowerCase());
    }

    private void applyRule(Word word){
        String text = word.getTextLowerCase();
        switch (this.id) {
            case VOWEL_ID:
                applyVowelRule(word, text);
                break;
            case CONSONANT_ID:
                applyConsonantRule(word, text);
                break;
            case EOW_DOUBLE_MUTE_LETTER_ID:
                applyEowDoubleMuteLetterRule(word, text);
                break;
            case QU_GU_RULE_ID:
                applyQuGuRule(word, text);
                break;
            case H_NOT_BEHIND_C_ID:
                applyHRule(word, text);
                break;
            case EOW_CONSONANT_ID:
                applyEowConsonantRule(word, text);
                break;
            case EOW_EZ_ER_ET_ID:
                applyEowEzEtErRule(word, text);
                break;
            case EST_LES_DES_ID:
                applyEstLesDesRule(word, text);
                break;
            case PATTERN_FOLLOWED_VOWEL_ID:
                applyPatternFollowedVowelRule(word, text);
                break;
            case ETRE_VERB_ID:
                applyEtreVerbeRule(word, text);
                break;
            default:
                throw new NotImplementedException("Rule not implemented: " + this.id);
        }
    }

    private void applyVowelRule(Word word, String text) {
        for (int i = 0; i < text.length(); i++) {
            if (VOWEL_SET.contains(text.charAt(i))) {
                word.setCharColor(i, this.color);
            }
        }
    }

    private void applyConsonantRule(Word word, String text) {
        for (int i = 0; i < text.length(); i++) {
            if (CONSONANT_SET.contains( text.charAt(i))) {
                word.setCharColor(i, this.color);
            }
        }
    }

    private void applyEowDoubleMuteLetterRule(Word word, String text){
        boolean endsWithMuteLetter = EOW_DOUBLE_MUTE_LETTER_SET.stream().anyMatch(text::endsWith);
        if (endsWithMuteLetter) {
            int length = text.length();
            word.setCharColor(length - 1, this.color);
            word.setCharColor(length - 2, this.color);
        }
    }

    private void applyQuGuRule(Word word, String text) {
        int index1 = text.indexOf(PATTERN_QU);
        int index2 = text.indexOf(PATTERN_GU);

        while (index1 != -1 || index2 != -1) {
            // Determine which pattern's occurrence is earlier
            if (index2 == -1 || (index1 != -1 && index1 < index2)) {
                word.setCharColor(index1 + 1, this.color);  // Apply operation on 'u' index for pattern1
                index1 = text.indexOf(PATTERN_QU, index1 + 2);  // Move to next pattern1
            } else {
                word.setCharColor(index2 + 1, this.color);  // Apply operation on 'u' index for pattern2
                index2 = text.indexOf(PATTERN_GU, index2 + 2);  // Move to next pattern2
            }
        }
    }

    private void applyHRule(Word word, String text) {
        int index = text.indexOf('h');
        while (index != -1) {
            if (index == 0) {
                word.setCharColor(0, this.color);
            }
            else if (text.charAt(index - 1) != 'c') {
                word.setCharColor(index, this.color);
            }
            index = text.indexOf('h', index + 1);
        }
    }

    private void applyEowConsonantRule(Word word, String text) {
        Collection<String> consonantSet = CONSONANT_SET.stream()
                .map(String::valueOf)
                .toList();
        boolean endsWithConsonant = consonantSet.stream().anyMatch(text::endsWith);
        boolean endsWithE = text.endsWith("e");
        if (endsWithConsonant || endsWithE) {
            int length = text.length();
            word.setCharColor(length - 1, this.color);
        }
    }

    private void applyEowEzEtErRule(Word word, String text) {
        boolean endsWithPattern = Set.of(PATTERN_EZ, PATTERN_ER, PATTERN_ET).stream().anyMatch(text::endsWith);
        if (endsWithPattern) {
            int length = text.length();
            word.setCharColor(length - 1, this.color);
            word.setCharColor(length - 2, this.color);
        }
    }

    private void applyEstLesDesRule(Word word, String text) {
        if (text.equals(PATTERN_DES) || text.equals(PATTERN_LES)) {
            word.setCharColor(1, this.color);
            word.setCharColor(2, this.color);
        } else if (text.equals(PATTERN_EST)) {
            word.setCharColor(0, this.color);
            word.setCharColor(1, this.color);
        }
    }

    private void applyPatternFollowedVowelRule(Word word, String text) {
        for (String pattern : SET_PATTERN) {
            int index = text.indexOf(pattern);
            int patternLength = pattern.length();
            while (index >= 0) {
                if (index + patternLength == text.length() ||!VOWEL_SET.contains(text.charAt(index + patternLength))) {
                    for (int i = index + 1; i < index + patternLength && i < text.length(); i++) {
                        word.setCharColor(i, this.color);
                    }
                }
                index = text.indexOf(pattern, index + 1);
            }
        }
    }

    private void applyEtreVerbeRule(Word word, String text) {
        switch (text) {
            case "es":
                word.setCharColor(0, Color.RED);
                word.setCharColor(1, Color.RED);
                break;
            case "est":
                word.setCharColor(0, Color.RED);
                word.setCharColor(1, Color.RED);
                word.setCharColor(2, Color.RED);
                break;
            case "sommes":
                word.setCharColor(0,Color.BLACK);
                word.setCharColor(1,Color.RED);
                word.setCharColor(2,Color.BLACK);
                word.setCharColor(3,Color.BLACK);
                word.setCharColor(4,Color.GREY);
                word.setCharColor(5,Color.GREY);
                break;
            case "êtes", "etes":
                word.setCharColor(0,Color.RED);
                word.setCharColor(1,Color.BLACK);
                word.setCharColor(2,Color.GREY);
                word.setCharColor(3,Color.GREY);
                break;
            default:
        }
    }

}