package com.bamuel.spllang;

import com.intellij.lang.Language;

public class PCSLanguage extends Language {
    public static final PCSLanguage INSTANCE = new PCSLanguage();

    private PCSLanguage() {
        super("PCS");
    }
}