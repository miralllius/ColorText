package com.github.miralllius.rule;

import com.github.miralllius.text.Word;

public class RuleTest {
    public static void main(String[] args) {
        Word testedWord = new Word("myWordToTest");
        testedWord.applyAllRules();
        System.out.println(testedWord.getText());
        System.out.println(testedWord.getColors());
    }

}
