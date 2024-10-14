package com.bamuel.spllang.psi;

import com.bamuel.spllang.ERRLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class ERRElementType extends IElementType {

    public ERRElementType(@NotNull @NonNls String debugName) {
        super(debugName, ERRLanguage.INSTANCE);
    }

}