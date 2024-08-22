package com.bamuel.spllang;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

final class SPLColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("FUNCTION_DECLARATION", SPLSyntaxHighlighter.FUNCTION_DECLARATION),
            new AttributesDescriptor("Key", SPLSyntaxHighlighter.KEY),
            new AttributesDescriptor("Separator", SPLSyntaxHighlighter.SEPARATOR),
            new AttributesDescriptor("Value", SPLSyntaxHighlighter.VALUE),
            new AttributesDescriptor("Bad value", SPLSyntaxHighlighter.BAD_CHARACTER)
    };

    @Override
    public Icon getIcon() {
        return SPLIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new SPLSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "//This is a Comment\n" +
                "procedure main\n" +
                "    local lf-full-name like shortname\n" +
                "    do s_get-last-name\n" +
                "        parameters \"Joel\"\n" +
                "        returning lf-full-name\n" +
                "    message lf-full-name\n" +
                "end-procedure\n" +
                "\n" +
                "screen s_get-last-name\n" +
                "    parameters\n" +
                "        lp-first-name        pic x(10)\n" +
                "    returning\n" +
                "        lr-full-name         like shortname\n" +
                "    local\n" +
                "        lf-last-name         pic x(10)\n" +
                "    //\n" +
                "    window @1,1 to @25,40 title \"window prompt\"\n" +
                "    //\n" +
                "    accept lf-last-name @2,14\n" +
                "        title \"Last Name:\"\n" +
                "    validation\n" +
                "    end-validations\n" +
                "    //\n" +
                "    confirm auto\n" +
                "    confirmed\n" +
                "        set lr-full-name = concat(str-concat(lp-first-name),\" \",lf-last-name)\n" +
                "        exit\n" +
                "    end-confirm\n" +
                "end-screen\n";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "SPL";
    }

}