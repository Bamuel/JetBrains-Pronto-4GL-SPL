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

import java.util.HashMap;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class SPLSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey KEYWORD = createTextAttributesKey("SPL_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey FUNCTION_DECLARATION = createTextAttributesKey("SPL_FUNCTION_DECLARATION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey STRING = createTextAttributesKey("SPL_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey NUMBER = createTextAttributesKey("SPL_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey COMMA = createTextAttributesKey("SPL_COMMA", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey LPARENTHESES = createTextAttributesKey("SPL_LPAREN", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey RPARENTHESES = createTextAttributesKey("SPL_RPAREN", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey COMMENT = createTextAttributesKey("SPL_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT = createTextAttributesKey("SPL_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("SPL_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final Map<IElementType, TextAttributesKey[]> TOKEN_HIGHLIGHT_MAP = new HashMap<>();

    static {
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.KEYWORD, new TextAttributesKey[]{KEYWORD});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.FUNCTION_DECLARATION, new TextAttributesKey[]{FUNCTION_DECLARATION});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.STRING, new TextAttributesKey[]{STRING});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.NUMBER, new TextAttributesKey[]{NUMBER});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.COMMA, new TextAttributesKey[]{COMMA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.LPAREN, new TextAttributesKey[]{LPARENTHESES});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.RPAREN, new TextAttributesKey[]{RPARENTHESES});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.COMMENT, new TextAttributesKey[]{COMMENT});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.BLOCK_COMMENT, new TextAttributesKey[]{BLOCK_COMMENT});
        TOKEN_HIGHLIGHT_MAP.put(TokenType.BAD_CHARACTER, new TextAttributesKey[]{BAD_CHARACTER});
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new SPLLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        return TOKEN_HIGHLIGHT_MAP.getOrDefault(tokenType, new TextAttributesKey[0]);
    }
}
