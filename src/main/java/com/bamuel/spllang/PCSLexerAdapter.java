package com.bamuel.spllang;

import com.intellij.lexer.FlexAdapter;

public class PCSLexerAdapter extends FlexAdapter {

    public PCSLexerAdapter() {
        super(new PCSLexer(null));
    }

}