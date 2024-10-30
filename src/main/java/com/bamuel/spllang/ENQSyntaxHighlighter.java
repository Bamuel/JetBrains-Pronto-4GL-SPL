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
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.PRN, new TextAttributesKey[]{CONSTANT});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.CSV, new TextAttributesKey[]{CONSTANT});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.EXTERNAL, new TextAttributesKey[]{CONSTANT});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.XML, new TextAttributesKey[]{CONSTANT});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.EXCEL, new TextAttributesKey[]{CONSTANT});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.RPT, new TextAttributesKey[]{CONSTANT});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.LST, new TextAttributesKey[]{CONSTANT});

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
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.IN, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.PARAMETER, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.INPUT, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.FORMAT, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.IS, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.YES, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.NO, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.TRUE, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.FALSE, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.ZERO, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.SPACE, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.REPORT, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.WIDTH, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.TITLE, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.LINE, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.FIELD, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.NEW, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.PAGE, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.ON, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.SUPPRESS, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.UNIQUE, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.RECORD, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.TOTAL, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.SUBTOTAL, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.WITH, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.BREAKS, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.SORT, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.DESCENDING, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.DISPLAY, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.TYPE, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.PICTURE, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.OF, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.IF, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.ELSE, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.ENDIF, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.LABEL, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.REPEATING, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.TIMES, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.HEADINGS, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.MARGIN, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.DEPTH, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.LENGTH, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.DEFAULT, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.FONT, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.FORM, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.HEADER, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.FOOTER, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.BEFORE, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.AFTER, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.DETAIL, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.FOR, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.DOWN, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.STEP, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.END_FOR, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.ELSE_IF, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.END_IF, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.NEED, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.LINES, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.PRINT, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.COLUMN, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.UNDERLINED, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.BOLD, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.DIM, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.ITALIC, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.SUBSCRIPT, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.SUPERSCRIPT, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.NONEWLINE, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.SKIP, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.SWITCH, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.END_SWITCH, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.WHILE, new TextAttributesKey[]{KEYWORDS});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.END_WHILE, new TextAttributesKey[]{KEYWORDS});

        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.NUMBER, new TextAttributesKey[]{NUMBER});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.DATE, new TextAttributesKey[]{NUMBER});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.FLOAT, new TextAttributesKey[]{NUMBER});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.STRING, new TextAttributesKey[]{STRING});
        TOKEN_HIGHLIGHT_MAP.put(ENQTypes.PICTURESTRING, new TextAttributesKey[]{STRING});

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