package com.github.miralllius.rule;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Set;

@UtilityClass
// EOW stand for End Of Word
class RuleConstants {

    // CONSONANT
    final Collection<Character> CONSONANT_SET = Set.of(
            'b', 'c', 'ç', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z');
    final String CONSONANT_ID = "consonant";

    // VOWEL
    final Collection<Character> VOWEL_SET = Set.of(
            'a', 'e', 'i', 'o', 'u', 'à', 'â', 'é', 'è', 'ë', 'ê', 'î', 'ï','ô', 'ù', 'û', 'ü');
    final String VOWEL_ID = "vowel";

    // EOW_DOUBLE_MUTE_LETTER
    final String PATTERN_PS = "ps";
    final String PATTERN_DS = "ds";
    final Collection<String> EOW_DOUBLE_MUTE_LETTER_SET = Set.of(PATTERN_PS, PATTERN_DS);
    final String EOW_DOUBLE_MUTE_LETTER_ID = "eow_double_mute_letter";

    // Qu and Gu pattern
    final String PATTERN_QU = "qu";
    final String PATTERN_GU = "gu";
    final String QU_GU_RULE_ID = "qu_gu_rule";

    // H not behind a C
    final String H_NOT_BEHIND_C_ID = "h_not_behind_c";

    // EOW_CONSONANT
    final String EOW_CONSONANT_ID = "eow_consonant";
    final RuleExcpt EOW_CONSONANT_EXCPT = new RuleExcpt(Set.of("huit", "bavoir", "coq", "cinq", "truc", "mur"));

    // EOW_EZ
    final String EOW_EZ_ER_ET_ID = "eow_ez";
    final String PATTERN_EZ = "ez";
    final String PATTERN_ER = "er";
    final String PATTERN_ET = "et";

    // EST_LES_DES
    final String EST_LES_DES_ID = "est_les_des";
    final String PATTERN_LES = "les";
    final String PATTERN_EST = "est";
    final String PATTERN_DES = "des";

    // PATTERN_FOLLOWED_BY_VOWEl
    final String PATTERN_FOLLOWED_VOWEL_ID = "followed_vowel";
    final Set<String> SET_PATTERN = Set.of("on" , "om", "an", "en", "am", "in", "im", "un", "ain" , "ein");

}
