package com.bamuel.spllang.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.bamuel.spllang.SPLFileType;
import com.bamuel.spllang.SPLLanguage;
import org.jetbrains.annotations.NotNull;

public class SPLFile extends PsiFileBase {

    public SPLFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, SPLLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return SPLFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "SPL File";
    }

}