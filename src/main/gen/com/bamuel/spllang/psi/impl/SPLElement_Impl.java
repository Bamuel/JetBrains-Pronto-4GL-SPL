// This is a generated file. Not intended for manual editing.
package com.bamuel.spllang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.bamuel.spllang.psi.SPLTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.bamuel.spllang.psi.*;

public class SPLElement_Impl extends ASTWrapperPsiElement implements SPLElement_ {

  public SPLElement_Impl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SPLVisitor visitor) {
    visitor.visitElement_(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SPLVisitor) accept((SPLVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SPLProperty getProperty() {
    return findChildByClass(SPLProperty.class);
  }

}
