package com.github.miralllius.rule;

import java.util.Set;

public class RuleExcpt {

    Set<String> exceptions;

    public RuleExcpt() {
        this.exceptions = Set.of();
    }

    public RuleExcpt(Set<String> exceptions) {
        this.exceptions = exceptions;
    }
}
