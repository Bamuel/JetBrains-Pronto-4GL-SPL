package com.bamuel.spllang;

import com.bamuel.spllang.psi.ERRTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ERRSyntaxHighlighter extends SyntaxHighlighterBase {

    // Custom attributes for ERROR, WARNING, and INFO with specific colors and bold fonts
    public static final TextAttributesKey ERROR = TextAttributesKey.createTextAttributesKey(
            "LOG_ERROR", new TextAttributes(new JBColor(new Color(218, 53, 68), new Color(218, 53, 68)), null, null, null, Font.BOLD)
    );

    public static final TextAttributesKey WARNING = TextAttributesKey.createTextAttributesKey(
            "LOG_WARNING", new TextAttributes(new JBColor(new Color(253, 191, 7), new Color(253, 191, 7)), null, null, null, Font.BOLD)
    );

    public static final TextAttributesKey INFO = TextAttributesKey.createTextAttributesKey(
            "LOG_INFO", new TextAttributes(new JBColor(new Color(23, 161, 183), new Color(23, 161, 183)), null, null, null, Font.BOLD)
    );

    public static final TextAttributesKey KEYWORD = TextAttributesKey.createTextAttributesKey("LOG_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey FILEPATH = TextAttributesKey.createTextAttributesKey("LOG_FILEPATH", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey LINE_NUMBER = TextAttributesKey.createTextAttributesKey("LOG_LINE_NUMBER", DefaultLanguageHighlighterColors.METADATA);
    public static final TextAttributesKey VARIABLE = TextAttributesKey.createTextAttributesKey("LOG_VARIABLE", DefaultLanguageHighlighterColors.STRING);

    private static final Map<IElementType, TextAttributesKey[]> TOKEN_HIGHLIGHT_MAP = new HashMap<>();

    static {
        TOKEN_HIGHLIGHT_MAP.put(ERRTypes.ERROR, new TextAttributesKey[]{ERROR});
        TOKEN_HIGHLIGHT_MAP.put(ERRTypes.WARNING, new TextAttributesKey[]{WARNING});
        TOKEN_HIGHLIGHT_MAP.put(ERRTypes.INFO, new TextAttributesKey[]{INFO});
        TOKEN_HIGHLIGHT_MAP.put(ERRTypes.KEYWORD, new TextAttributesKey[]{KEYWORD});
        TOKEN_HIGHLIGHT_MAP.put(ERRTypes.FILEPATH_, new TextAttributesKey[]{FILEPATH});
        TOKEN_HIGHLIGHT_MAP.put(ERRTypes.LINE_NUMBER, new TextAttributesKey[]{LINE_NUMBER});
        TOKEN_HIGHLIGHT_MAP.put(ERRTypes.VARIABLE, new TextAttributesKey[]{VARIABLE});
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new ERRLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        return TOKEN_HIGHLIGHT_MAP.getOrDefault(tokenType, new TextAttributesKey[0]);
    }
}