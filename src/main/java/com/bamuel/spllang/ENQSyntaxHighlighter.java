package com.bamuel.spllang;

import com.bamuel.spllang.psi.ENQTypes;
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

public class ENQSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey KEYWORDS = createTextAttributesKey("ENQ_KEYWORDS", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey CONSTANT = createTextAttributesKey("ENQ_CONSTANT", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey NUMBER = createTextAttributesKey("ENQ_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey STRING = createTextAttributesKey("ENQ_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey COMMA = createTextAttributesKey("ENQ_COMMA", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey PARENTHESES = createTextAttributesKey("ENQ_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey COMMENT = createTextAttributesKey("ENQ_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT = createTextAttributesKey("ENQ_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("ENQ_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final Map<IElementType, TextAttributesKey[]> TOKEN_HIGHLIGHT_MAP = new HashMap<>();

    static {

        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.SELECT, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.INSERT, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.UPDATE, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.DELETE, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.FROM, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.WHERE, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.ORDER, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.BY, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.SET, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.VALUES, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.AND, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.OR, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.NOT, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.PARAMETER, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.INPUT, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.FORMAT, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.IS, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.FORMAT_TYPE, new TextAttributesKey[]{CONSTANT});

        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.NUMBER, new TextAttributesKey[]{NUMBER});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.DATE, new TextAttributesKey[]{NUMBER});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.FLOAT, new TextAttributesKey[]{NUMBER});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.STRING, new TextAttributesKey[]{STRING});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.COMMA, new TextAttributesKey[]{COMMA});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.LBRACE, new TextAttributesKey[]{PARENTHESES});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.RBRACE, new TextAttributesKey[]{PARENTHESES});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.LPAREN, new TextAttributesKey[]{PARENTHESES});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.RPAREN, new TextAttributesKey[]{PARENTHESES});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.COMMENT, new TextAttributesKey[]{COMMENT});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.BLOCK_COMMENT, new TextAttributesKey[]{BLOCK_COMMENT});
        TOKEN_HIGHLIGHT_MAP.put(TokenType.BAD_CHARACTER, new TextAttributesKey[]{BAD_CHARACTER});
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new ENQLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        return TOKEN_HIGHLIGHT_MAP.getOrDefault(tokenType, new TextAttributesKey[0]);
    }
}