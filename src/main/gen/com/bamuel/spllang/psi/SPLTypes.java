// This is a generated file. Not intended for manual editing.
package com.bamuel.spllang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.bamuel.spllang.psi.impl.*;

public interface SPLTypes {

  IElementType PROPERTY = new SPLElementType("PROPERTY");

  IElementType COMMENT = new SPLTokenType("COMMENT");
  IElementType CRLF = new SPLTokenType("CRLF");
  IElementType KEY = new SPLTokenType("KEY");
  IElementType SEPARATOR = new SPLTokenType("SEPARATOR");
  IElementType VALUE = new SPLTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROPERTY) {
        return new SPLPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
