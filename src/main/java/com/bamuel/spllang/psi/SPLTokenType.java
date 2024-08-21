package com.bamuel.spllang.psi;

import com.intellij.psi.tree.IElementType;
import com.bamuel.spllang.SPLLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class SPLTokenType extends IElementType {

    public SPLTokenType(@NotNull @NonNls String debugName) {
        super(debugName, SPLLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "SPLTokenType." + super.toString();
    }

}