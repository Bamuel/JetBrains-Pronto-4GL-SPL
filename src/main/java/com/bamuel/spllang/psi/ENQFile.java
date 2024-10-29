package com.bamuel.spllang.psi;

import com.bamuel.spllang.ENQFileType;
import com.bamuel.spllang.ENQLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class ENQFile extends PsiFileBase {

    public ENQFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, ENQLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return ENQFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "ENQ File";
    }

}