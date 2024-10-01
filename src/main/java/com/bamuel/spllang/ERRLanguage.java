package com.bamuel.spllang;

import com.intellij.lang.Language;

public class ERRLanguage extends Language {
    public static final ERRLanguage INSTANCE = new ERRLanguage();

    private ERRLanguage() {
        super("ERR");
    }
}