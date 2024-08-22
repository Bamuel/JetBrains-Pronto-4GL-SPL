// This is a generated file. Not intended for manual editing.
package com.bamuel.spllang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.bamuel.spllang.psi.impl.*;

public interface SPLTypes {

  IElementType ELEMENT_ = new SPLElementType("ELEMENT_");
  IElementType OTHERSYMBOLS = new SPLElementType("OTHERSYMBOLS");
  IElementType PROPERTY = new SPLElementType("PROPERTY");

  IElementType AT = new SPLTokenType("AT");
  IElementType BLOCK_COMMENT = new SPLTokenType("BLOCK_COMMENT");
  IElementType COMMA = new SPLTokenType("COMMA");
  IElementType COMMENT = new SPLTokenType("COMMENT");
  IElementType DOT = new SPLTokenType("DOT");
  IElementType EQUALS = new SPLTokenType("EQUALS");
  IElementType FUNCTION_DECLARATION = new SPLTokenType("FUNCTION_DECLARATION");
  IElementType IDENTIFIER = new SPLTokenType("IDENTIFIER");
  IElementType KEY = new SPLTokenType("KEY");
  IElementType KEYWORD = new SPLTokenType("KEYWORD");
  IElementType LBRACE = new SPLTokenType("LBRACE");
  IElementType LPAREN = new SPLTokenType("LPAREN");
  IElementType NUMBER = new SPLTokenType("NUMBER");
  IElementType RBRACE = new SPLTokenType("RBRACE");
  IElementType RPAREN = new SPLTokenType("RPAREN");
  IElementType SEMICOLON = new SPLTokenType("SEMICOLON");
  IElementType SEPARATOR = new SPLTokenType("SEPARATOR");
  IElementType STRING = new SPLTokenType("STRING");
  IElementType VALUE = new SPLTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ELEMENT_) {
        return new SPLElement_Impl(node);
      }
      else if (type == OTHERSYMBOLS) {
        return new SPLOthersymbolsImpl(node);
      }
      else if (type == PROPERTY) {
        return new SPLPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
