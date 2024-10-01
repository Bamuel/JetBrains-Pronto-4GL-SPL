package com.bamuel.spllang;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class ERRFileType extends LanguageFileType {
    public static final ERRFileType INSTANCE = new ERRFileType();

    private ERRFileType() {
        super(ERRLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "SPL Error File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "SPL Error file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "enq";
    }

    @Override
    public Icon getIcon() {
        return ERRIcons.FILE;
    }
}
