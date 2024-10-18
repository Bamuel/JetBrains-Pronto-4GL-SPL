package com.bamuel.spllang;

import com.bamuel.spllang.psi.SPLTypes;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.lang.BracePair;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class SPLBraceMatcher implements PairedBraceMatcher {
    private final BracePair[] PAIRS;

    public SPLBraceMatcher() {
        PAIRS = new BracePair[]{
                    new BracePair(SPLTypes.LPAREN, SPLTypes.RPAREN, false),
                    new BracePair(SPLTypes.LBRACE, SPLTypes.RBRACE, false)
        };
    }

    public BracePair[] getPairs() {
        return PAIRS;
    }

    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    public int getCodeConstructStart(final PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
