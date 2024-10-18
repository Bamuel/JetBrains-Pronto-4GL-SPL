package com.bamuel.spllang;

import com.bamuel.spllang.psi.PCSTypes;
import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class PCSBraceMatcher implements PairedBraceMatcher {
    private final BracePair[] PAIRS;

    public PCSBraceMatcher() {
        PAIRS = new BracePair[]{
                    new BracePair(PCSTypes.LPAREN, PCSTypes.RPAREN, false),
                    new BracePair(PCSTypes.LBRACE, PCSTypes.RBRACE, false),
                    new BracePair(PCSTypes.LSQUARE, PCSTypes.RSQUARE, false)
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
