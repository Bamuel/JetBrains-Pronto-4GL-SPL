package com.bamuel.spllang;

import com.intellij.lexer.FlexAdapter;

public class ERRLexerAdapter extends FlexAdapter {

    public ERRLexerAdapter() {
        super(new ERRLexer(null));
    }

}