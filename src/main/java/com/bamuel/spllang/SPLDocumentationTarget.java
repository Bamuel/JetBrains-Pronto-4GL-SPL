package com.bamuel.spllang;



import com.intellij.model.Pointer;
import com.intellij.platform.backend.documentation.DocumentationResult;
import com.intellij.platform.backend.documentation.DocumentationTarget;
import com.intellij.platform.backend.presentation.TargetPresentation;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SPLDocumentationTarget implements DocumentationTarget {

    private final PsiElement element;
    private final PsiElement originalElement;

    public SPLDocumentationTarget(@NotNull PsiElement element, @Nullable PsiElement originalElement) {
        this.element = element;
        this.originalElement = originalElement;
    }

    @Override
    public @Nullable DocumentationResult computeDocumentation() {
        return DocumentationResult.documentation("Documentation for: " + element.getText());
    }

    @Override
    public @Nullable String computeDocumentationHint() {
        return "Documentation hint for: " + element.getText();
    }

    @Override
    public @NotNull TargetPresentation computePresentation() {
        return TargetPresentation.builder(element.getText()).presentation();
    }

    @Override
    public @NotNull Pointer<? extends DocumentationTarget> createPointer() {
        return new Pointer<DocumentationTarget>() {
            @Override
            public DocumentationTarget dereference() {
                return new SPLDocumentationTarget(element, originalElement);
            }
        };
    }
}