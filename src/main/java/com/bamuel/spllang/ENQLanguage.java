package com.bamuel.spllang;

import com.intellij.lang.Language;

public class ENQLanguage extends Language {
    public static final ENQLanguage INSTANCE = new ENQLanguage();

    private ENQLanguage() {
        super("ENQ");
    }
}