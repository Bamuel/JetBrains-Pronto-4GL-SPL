package com.bamuel.spllang.psi;

import com.bamuel.spllang.ENQLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class ENQTokenType extends IElementType {

    public ENQTokenType(@NotNull @NonNls String debugName) {
        super(debugName, ENQLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "ENQTokenType." + super.toString();
    }

}