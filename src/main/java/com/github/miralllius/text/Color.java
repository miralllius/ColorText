package com.github.miralllius.text;

import lombok.Getter;

@Getter
public enum Color {
    GREY("808080"), // Grey
    BLACK("000000"), // Black
    RED("FF0000"),   // Red
    UNPROCESSED;

    private final String hexCode;

    Color(String hexCode) {
        this.hexCode = hexCode;
    }

    Color() {
        this.hexCode = null;
    }
}
