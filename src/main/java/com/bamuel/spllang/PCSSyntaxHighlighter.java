package com.bamuel.spllang;

import com.bamuel.spllang.psi.PCSTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class PCSSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey OVERRIDE_SCREEN_DEFINITION = createTextAttributesKey("PCS_OVERRIDE_SCREEN_DEFINITION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey OVERRIDE_STATEMENT = createTextAttributesKey("PCS_OVERRIDE_STATEMENT", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey NUMBER = createTextAttributesKey("PCS_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey STRING = createTextAttributesKey("PCS_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey COMMA = createTextAttributesKey("PCS_COMMA", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey LBRACE = createTextAttributesKey("PCS_LBRACE", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey RBRACE = createTextAttributesKey("PCS_RBRACE", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey COMMENT = createTextAttributesKey("PCS_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT = createTextAttributesKey("PCS_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("PCS_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] OVERRIDE_SCREEN_DEFINITION_KEYS = new TextAttributesKey[]{OVERRIDE_SCREEN_DEFINITION};
    private static final TextAttributesKey[] OVERRIDE_STATEMENT_KEYS = new TextAttributesKey[]{OVERRIDE_STATEMENT};
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] COMMA_KEYS = new TextAttributesKey[]{COMMA};
    private static final TextAttributesKey[] PARENTHESES_KEYS = new TextAttributesKey[]{LBRACE, RBRACE};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] BLOCK_COMMENT_KEYS = new TextAttributesKey[]{BLOCK_COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new PCSLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(PCSTypes.OVERRIDE_SCREEN_DEFINITION)) {
            return OVERRIDE_SCREEN_DEFINITION_KEYS;
        }
        if (tokenType.equals(PCSTypes.OVERRIDE_STATEMENT)) {
            return OVERRIDE_STATEMENT_KEYS;
        }
        if (tokenType.equals(PCSTypes.NUMBER)) {
            return NUMBER_KEYS;
        }
        if (tokenType.equals(PCSTypes.STRING)) {
            return STRING_KEYS;
        }
        if (tokenType.equals(PCSTypes.COMMA)) {
            return COMMA_KEYS;
        }
        if (tokenType.equals(PCSTypes.LBRACE) || tokenType.equals(PCSTypes.RBRACE)) {
            return PARENTHESES_KEYS;
        }
        if (tokenType.equals(PCSTypes.COMMENT)) {
            return COMMENT_KEYS;
        }
        if (tokenType.equals(PCSTypes.BLOCK_COMMENT)) {
            return BLOCK_COMMENT_KEYS;
        }
        if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        }
        return EMPTY_KEYS;
    }
}