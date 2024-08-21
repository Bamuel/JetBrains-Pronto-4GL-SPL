package com.bamuel.spllang;

import com.intellij.lexer.FlexAdapter;

public class SPLLexerAdapter extends FlexAdapter {

    public SPLLexerAdapter() {
        super(new SPLLexer(null));
    }

}