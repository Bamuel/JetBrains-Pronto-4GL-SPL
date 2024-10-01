package com.bamuel.spllang;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class OP7FileType extends LanguageFileType {
    public static final OP7FileType INSTANCE = new OP7FileType();

    private OP7FileType() {
        super(OP7Language.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "SPL Compiled File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "SPL compiled file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "op7";
    }

    @Override
    public Icon getIcon() {
        return OP7Icons.FILE;
    }
}
