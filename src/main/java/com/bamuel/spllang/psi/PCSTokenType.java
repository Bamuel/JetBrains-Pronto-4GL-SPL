package com.bamuel.spllang.psi;

import com.bamuel.spllang.PCSLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class PCSTokenType extends IElementType {

    public PCSTokenType(@NotNull @NonNls String debugName) {
        super(debugName, PCSLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "PCSTokenType." + super.toString();
    }

}