package com.bamuel.spllang;

import com.intellij.lexer.FlexAdapter;

public class ENQLexerAdapter extends FlexAdapter {

    public ENQLexerAdapter() {
        super(new ENQLexer(null));
    }

}