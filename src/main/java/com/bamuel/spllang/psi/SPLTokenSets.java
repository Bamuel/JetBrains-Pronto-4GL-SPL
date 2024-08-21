package com.bamuel.spllang.psi;

import com.intellij.psi.tree.TokenSet;

public interface SPLTokenSets {

    TokenSet IDENTIFIERS = TokenSet.create(SPLTypes.KEY);

    TokenSet COMMENTS = TokenSet.create(SPLTypes.COMMENT);

}