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

import java.util.HashMap;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class PCSSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey FUNCTION_DECLARATION = createTextAttributesKey("PCS_FUNCTION_DECLARATION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey CONSTANT = createTextAttributesKey("PCS_CONSTANT", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey KEYWORDS = createTextAttributesKey("PCS_KEYWORDS", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey NUMBER = createTextAttributesKey("PCS_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey STRING = createTextAttributesKey("PCS_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey COMMA = createTextAttributesKey("PCS_COMMA", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey BRACES = createTextAttributesKey("PCS_BRACES", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey COMMENT = createTextAttributesKey("PCS_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT = createTextAttributesKey("PCS_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("PCS_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final Map<IElementType, TextAttributesKey[]> TOKEN_HIGHLIGHT_MAP = new HashMap<>();

    static {
        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.OVERRIDE_SCREEN_DEFINITION, new TextAttributesKey[]{FUNCTION_DECLARATION});
        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.OVERRIDE_STATEMENT, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.BOOLEAN, new TextAttributesKey[]{KEYWORDS});

        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.COMMON_CLAUSES_STATEMENTS, new TextAttributesKey[]{CONSTANT});
        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.TRIGGER_POINT, new TextAttributesKey[]{CONSTANT});
        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.TRIGGER_TYPE, new TextAttributesKey[]{CONSTANT});
        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.RUN, new TextAttributesKey[]{CONSTANT});
        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.COLOR, new TextAttributesKey[]{CONSTANT});
        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.ATTRIBUTES, new TextAttributesKey[]{CONSTANT});

        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.NUMBER, new TextAttributesKey[]{NUMBER});
        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.FLOAT, new TextAttributesKey[]{NUMBER});
        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.COORDINATES, new TextAttributesKey[]{NUMBER});
        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.FLOAT_COORDINATES, new TextAttributesKey[]{NUMBER});

        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.STRING, new TextAttributesKey[]{STRING});

        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.COMMA, new TextAttributesKey[]{COMMA});
        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.LBRACE, new TextAttributesKey[]{BRACES});
        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.RBRACE, new TextAttributesKey[]{BRACES});
        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.COMMENT, new TextAttributesKey[]{COMMENT});
        TOKEN_HIGHLIGHT_MAP.put(PCSTypes.BLOCK_COMMENT, new TextAttributesKey[]{BLOCK_COMMENT});
        TOKEN_HIGHLIGHT_MAP.put(TokenType.BAD_CHARACTER, new TextAttributesKey[]{BAD_CHARACTER});
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new PCSLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        return TOKEN_HIGHLIGHT_MAP.getOrDefault(tokenType, new TextAttributesKey[0]);
    }
}