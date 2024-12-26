package com.github.miralllius.text;

import com.github.miralllius.rule.Rule;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.github.miralllius.rule.RuleData.ALL_RULES;

@Getter
public class Word {

    private final String text;
    private final List<Color> colors;

    public Word(String wordAsString) {
        validateWord(wordAsString);
        text = wordAsString;

        colors = IntStream.range(0, text.length())
                .mapToObj(i -> Character.isLetter(text.charAt(i)) ? Color.UNPROCESSED : Color.BLACK)
                .collect(Collectors.toList());
    }

    public String getTextLowerCase() {
        return text.toLowerCase();
    }

    public int length(){
        return text.length();
    }

    public void applyAllRules() {
        // If the word only contains punctuations, no need to apply the rules.
        if (Pattern.matches("[.,;:!?\"'«»()\\[\\]{}\\-…\\s]*", this.text)) {
            return;
        }
        List<Rule> orderedRule = ALL_RULES.stream().sorted(Comparator.comparingInt(Rule::order)).toList();
        orderedRule.forEach(rule -> rule.execute(this));
    }

    public void setCharColor(int index, Color color){
        if (index < 0 || index >= colors.size()) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        if (colors.get(index) == Color.UNPROCESSED) {
            colors.set(index, color);
        }
    }

    private void validateWord(String word){
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null or empty");
        }
    }

}
