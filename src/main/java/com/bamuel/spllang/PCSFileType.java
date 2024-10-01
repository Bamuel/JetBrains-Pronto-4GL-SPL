package com.bamuel.spllang;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class PCSFileType extends LanguageFileType {
    public static final PCSFileType INSTANCE = new PCSFileType();

    private PCSFileType() {
        super(PCSLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Pronto Customer Screen File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Pronto customer screen file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "pcs";
    }

    @Override
    public Icon getIcon() {
        return PCSIcons.FILE;
    }
}
