package com.bamuel.spllang;

import com.bamuel.spllang.psi.SPLTypes;
import com.intellij.lang.documentation.DocumentationProvider;
import com.intellij.lang.documentation.psi.PsiElementDocumentationTarget;
import com.intellij.lang.Language;
import com.intellij.platform.backend.documentation.DocumentationTarget;
import com.intellij.platform.backend.documentation.DocumentationTargetProvider;
import com.intellij.platform.backend.documentation.PsiDocumentationTargetProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SPLDocumentation implements DocumentationTargetProvider {

    @Override
    public @NotNull List<? extends @NotNull DocumentationTarget> documentationTargets(@NotNull PsiFile file, int offset) {
        var element = file.findElementAt(offset);
        //System.out.println("element: " + element);
        if (element == null) {
            return Collections.emptyList();
        }

        List<DocumentationTarget> targets = new ArrayList<>();
        targets.add(new SPLDocumentationTarget(element, element));
        return targets;
    }

}
