package com.bamuel.spllang.psi;

import com.bamuel.spllang.PCSLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class PCSElementType extends IElementType {

    public PCSElementType(@NotNull @NonNls String debugName) {
        super(debugName, PCSLanguage.INSTANCE);
    }

}