package com.github.miralllius.rule;

import com.github.miralllius.text.Color;
import lombok.experimental.UtilityClass;

import java.util.Set;

import static com.github.miralllius.rule.RuleConstants.*;

@UtilityClass
// EOW stands for End Of Word
public class RuleData {

    //TODO two new rules:
    //e des verbes en "ger" doit être noir (avec les conjugaisons)
    //Les terminaisons des verbes du premier groupe des conjugaisons (1ere, 2eme singulier et 3eme singulier et pluriel en gris)


    public static final Rule VOWEL = new Rule(VOWEL_ID, 999, 1, Color.RED,
            new RuleExcpt(), "Voyelle solitaire");
    public static final Rule CONSONANT = new Rule(CONSONANT_ID, 999, 1,
            Color.BLACK, new RuleExcpt(), "Consonne solitaire");
    public static final Rule EOW_DOUBLE_MUTE_LETTER = new Rule(EOW_DOUBLE_MUTE_LETTER_ID, 10, 2,
            Color.GREY, new RuleExcpt(Set.of()),"Double lettre muette en fin de mot");
    public static final Rule QU_GU_RULE = new Rule(QU_GU_RULE_ID, 998, 2, Color.BLACK,
            QU_GU_EXCPT, "U après un Q ou un G est noir");
    public static final Rule H_NOT_BEHIND_C_RULE = new Rule(H_NOT_BEHIND_C_ID,998, 1, Color.GREY,
            new RuleExcpt(Set.of()), "Le h ne se prononce pas (sauf derrière un c)");
    public static final Rule EOW_CONSONANT = new Rule(EOW_CONSONANT_ID, 20, 2, Color.GREY,
            EOW_CONSONANT_EXCPT, "Les consonnes et e en fin de mot ne se prononcent pas.");
    public static final Rule EOW_EZ_ER_ET = new Rule(EOW_EZ_ER_ET_ID, 10, 2, Color.RED,
            new RuleExcpt(Set.of()), "'Ez', 'er' et 'et' de find de mot en rouge.");
    public static final Rule DES_LES_EST = new Rule(EST_LES_DES_ID, 1, 3, Color.RED,
            new RuleExcpt(Set.of()), "'Es' dans des, les et est en rouge");
    public static final Rule PATTERN_FOLLOWED_VOWEL = new Rule(PATTERN_FOLLOWED_VOWEL_ID, 10, 2,
            Color.RED, new RuleExcpt(Set.of()), "on , om, an, en, am, in,im, un, ain, ein sauf si suivi d'une voyelle en rouge");
    public static final Rule ETRE_VERB = new Rule(ETRE_VERB_ID, 0, 0, Color.UNPROCESSED, new RuleExcpt(Set.of()),
            "Conjugaison du vebre être au présent.");


    public static final Set<Rule> ALL_RULES = Set.of(VOWEL, CONSONANT, EOW_DOUBLE_MUTE_LETTER, QU_GU_RULE,
            H_NOT_BEHIND_C_RULE, EOW_CONSONANT, EOW_EZ_ER_ET, DES_LES_EST, PATTERN_FOLLOWED_VOWEL, ETRE_VERB);
}
