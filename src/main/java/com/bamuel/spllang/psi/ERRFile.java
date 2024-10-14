package com.bamuel.spllang.psi;

import com.bamuel.spllang.ERRFileType;
import com.bamuel.spllang.ERRLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class ERRFile extends PsiFileBase {

    public ERRFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, ERRLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return ERRFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "ERR File";
    }

}