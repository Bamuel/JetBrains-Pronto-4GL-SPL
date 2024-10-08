package com.bamuel.spllang;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.bamuel.spllang.psi.SPLTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class SPLSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey SEPARATOR =
            createTextAttributesKey("SPL_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey KEY =
            createTextAttributesKey("SPL_KEY", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("SPL_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey FUNCTION_DECLARATION =
            createTextAttributesKey("SPL_FUNCTION_DECLARATION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey VALUE =
            createTextAttributesKey("SPL_VALUE", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey STRING =
            createTextAttributesKey("SPL_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey NUMBER =
            createTextAttributesKey("SPL_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey COMMA =
            createTextAttributesKey("SPL_COMMA", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey LPARENTHESES =
            createTextAttributesKey("LPAREN", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey RPARENTHESES =
            createTextAttributesKey("RPAREN", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("SPL_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT =
            createTextAttributesKey("SPL_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("SPL_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPARATOR};
    private static final TextAttributesKey[] KEY_KEYS = new TextAttributesKey[]{KEY};
    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] FUNCTION_DECLARATION_KEYS = new TextAttributesKey[]{FUNCTION_DECLARATION};
    private static final TextAttributesKey[] VALUE_KEYS = new TextAttributesKey[]{VALUE};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};
    private static final TextAttributesKey[] COMMA_KEYS = new TextAttributesKey[]{COMMA};
    private static final TextAttributesKey[] PARENTHESES_KEYS = new TextAttributesKey[]{LPARENTHESES, RPARENTHESES};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] BLOCK_COMMENT_KEYS = new TextAttributesKey[]{BLOCK_COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new SPLLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(SPLTypes.SEPARATOR)) {
            return SEPARATOR_KEYS;
        }
        if (tokenType.equals(SPLTypes.KEY)) {
            return KEY_KEYS;
        }
        if (tokenType.equals(SPLTypes.KEYWORD)) {
            return KEYWORD_KEYS;
        }
        if (tokenType.equals(SPLTypes.FUNCTION_DECLARATION)) {
            return FUNCTION_DECLARATION_KEYS;
        }
        if (tokenType.equals(SPLTypes.VALUE)) {
            return VALUE_KEYS;
        }
        if (tokenType.equals(SPLTypes.STRING)) {
            return STRING_KEYS;
        }
        if (tokenType.equals(SPLTypes.NUMBER)) {
            return NUMBER_KEYS;
        }
        if (tokenType.equals(SPLTypes.COMMA)) {
            return COMMA_KEYS;
        }
        if (tokenType.equals(SPLTypes.LPAREN) || tokenType.equals(SPLTypes.RPAREN)) {
            return PARENTHESES_KEYS;
        }
        if (tokenType.equals(SPLTypes.COMMENT)) {
            return COMMENT_KEYS;
        }
        if (tokenType.equals(SPLTypes.BLOCK_COMMENT)) {
            return BLOCK_COMMENT_KEYS;
        }
        if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        }
        return EMPTY_KEYS;
    }
}