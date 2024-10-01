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

public class SPLOthersymbolsImpl extends ASTWrapperPsiElement implements SPLOthersymbols {

  public SPLOthersymbolsImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SPLVisitor visitor) {
    visitor.visitOthersymbols(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SPLVisitor) accept((SPLVisitor)visitor);
    else super.accept(visitor);
  }

}