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

final class PCSColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Override screen definition & Override statement", PCSSyntaxHighlighter.FUNCTION_DECLARATION),
            new AttributesDescriptor("Clause for statement", PCSSyntaxHighlighter.KEYWORDS),
            new AttributesDescriptor("String", PCSSyntaxHighlighter.STRING),
            new AttributesDescriptor("Number", PCSSyntaxHighlighter.NUMBER)
    };

    @Override
    public Icon getIcon() {
        return PCSIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new PCSSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return  "#PRONTO Screen Customiser File (Override statements only).\n" +
                "screen test-enquiry\n" +
                "\t{\n" +
                "\twindow\n" +
                "\t\t{\n" +
                "\t\twindow_size = 21,50\n" +
                "\t\t}\n" +
                "\t}\n" +
                "screen enquire-on-accountcode\n" +
                "\t{\n" +
                "\taccept\n" +
                "\t\t{\n" +
                "\t\tname = lf-balance\n" +
                "\t\ttitle = \"Balance\"\n" +
                "\t\t}\n" +
                "\tdisplay\n" +
                "\t\t{\n" +
                "\t\tname = lf-deb-status-desc\n" +
                "\t\ttitle = \"Description\"\n" +
                "\t\t}\n" +
                "\twindow\n" +
                "\t\t{\n" +
                "\t\tdefault = md-keyword\n" +
                "\t\twindow_size = 25,114\n" +
                "\t\t}\n" +
                "\t}\n";
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
        return "PCS";
    }

}