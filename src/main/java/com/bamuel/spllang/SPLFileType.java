package com.bamuel.spllang;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class SPLFileType extends LanguageFileType {

    public static final SPLFileType INSTANCE = new SPLFileType();

    private SPLFileType() {
        super(SPLLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "SPL File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "SPL language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "spl";
    }

    @Override
    public Icon getIcon() {
        return SPLIcons.FILE;
    }

}