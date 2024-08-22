// This is a generated file. Not intended for manual editing.
package com.bamuel.spllang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.bamuel.spllang.psi.SPLTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class SPLParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return splFile(b, l + 1);
  }

  /* ********************************************************** */
  // property | othersymbols
  //            | FUNCTION_DECLARATION
  //            | KEYWORD
  //            | STRING
  //            | NUMBER
  //            | IDENTIFIER
  //            | COMMENT
  //            | BLOCK_COMMENT
  public static boolean element_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "element_")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ELEMENT_, "<element>");
    r = property(b, l + 1);
    if (!r) r = othersymbols(b, l + 1);
    if (!r) r = consumeToken(b, FUNCTION_DECLARATION);
    if (!r) r = consumeToken(b, KEYWORD);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, BLOCK_COMMENT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // element_
  static boolean item_(PsiBuilder b, int l) {
    return element_(b, l + 1);
  }

  /* ********************************************************** */
  // SYMBOLS | SEMICOLON | DOT | EQUALS | LBRACE | RBRACE | LPAREN | RPAREN | COMMA | AT
  public static boolean othersymbols(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "othersymbols")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OTHERSYMBOLS, "<othersymbols>");
    r = consumeToken(b, SYMBOLS);
    if (!r) r = consumeToken(b, SEMICOLON);
    if (!r) r = consumeToken(b, DOT);
    if (!r) r = consumeToken(b, EQUALS);
    if (!r) r = consumeToken(b, LBRACE);
    if (!r) r = consumeToken(b, RBRACE);
    if (!r) r = consumeToken(b, LPAREN);
    if (!r) r = consumeToken(b, RPAREN);
    if (!r) r = consumeToken(b, COMMA);
    if (!r) r = consumeToken(b, AT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (KEY? SEPARATOR VALUE?) | KEY
  public static boolean property(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property")) return false;
    if (!nextTokenIs(b, "<property>", KEY, SEPARATOR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROPERTY, "<property>");
    r = property_0(b, l + 1);
    if (!r) r = consumeToken(b, KEY);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // KEY? SEPARATOR VALUE?
  private static boolean property_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = property_0_0(b, l + 1);
    r = r && consumeToken(b, SEPARATOR);
    r = r && property_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEY?
  private static boolean property_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_0_0")) return false;
    consumeToken(b, KEY);
    return true;
  }

  // VALUE?
  private static boolean property_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_0_2")) return false;
    consumeToken(b, VALUE);
    return true;
  }

  /* ********************************************************** */
  // item_*
  static boolean splFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "splFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "splFile", c)) break;
    }
    return true;
  }

}
