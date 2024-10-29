package com.bamuel.spllang.psi;

import com.bamuel.spllang.ENQLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class ENQElementType extends IElementType {

    public ENQElementType(@NotNull @NonNls String debugName) {
        super(debugName, ENQLanguage.INSTANCE);
    }

}