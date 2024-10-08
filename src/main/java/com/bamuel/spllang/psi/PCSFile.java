package com.bamuel.spllang.psi;

import com.bamuel.spllang.PCSFileType;
import com.bamuel.spllang.PCSLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class PCSFile extends PsiFileBase {

    public PCSFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, PCSLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return PCSFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "PCS File";
    }

}