package com.bamuel.spllang;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class ENQFileType extends LanguageFileType {
    public static final ENQFileType INSTANCE = new ENQFileType();

    private ENQFileType() {
        super(ENQLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "SPL ENQ File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "SPL enquiry file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "enq";
    }

    @Override
    public Icon getIcon() {
        return ENQIcons.FILE;
    }
}
