package com.bamuel.spllang;

import com.bamuel.spllang.psi.ENQTypes;
import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class ENQBraceMatcher implements PairedBraceMatcher {
    private final BracePair[] PAIRS;

    public ENQBraceMatcher() {
        PAIRS = new BracePair[]{
                    new BracePair(ENQTypes.LPAREN, ENQTypes.RPAREN, false),
                    new BracePair(ENQTypes.LBRACE, ENQTypes.RBRACE, false),
                    new BracePair(ENQTypes.LSQUARE, ENQTypes.RSQUARE, false)
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
