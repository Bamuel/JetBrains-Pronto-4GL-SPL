package com.bamuel.spllang.psi;

import com.bamuel.spllang.ERRLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class ERRTokenType extends IElementType {

    public ERRTokenType(@NotNull @NonNls String debugName) {
        super(debugName, ERRLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "ERRTokenType." + super.toString();
    }

}