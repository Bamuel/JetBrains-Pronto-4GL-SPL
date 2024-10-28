package com.bamuel.spllang;

import com.bamuel.spllang.psi.PCSTypes;
import com.intellij.openapi.editor.ElementColorProvider;
import com.intellij.psi.PsiElement;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class PCSColorProvider implements ElementColorProvider {

    @Override
    public @Nullable Color getColorFrom(@NotNull PsiElement element) {
        if (element.getParent().getNode().getElementType() == PCSTypes.COLOR_LIST_){
            if (element.getNode().getElementType() == PCSTypes.COLOR_AQUA) {
                return JBColor.CYAN;
            }
            if (element.getNode().getElementType() == PCSTypes.COLOR_BLACK) {
                return new JBColor(new Color(0x000000), new Color(0x000000)); // RGB(0, 0, 0)
            }
            if (element.getNode().getElementType() == PCSTypes.COLOR_BLUE) {
                return JBColor.BLUE;
            }
            if (element.getNode().getElementType() == PCSTypes.COLOR_FUCHSIA) {
                return JBColor.MAGENTA;
            }
            if (element.getNode().getElementType() == PCSTypes.COLOR_GREEN) {
                return JBColor.GREEN;
            }
            if (element.getNode().getElementType() == PCSTypes.COLOR_GREY) {
                return JBColor.GRAY;
            }
            if (element.getNode().getElementType() == PCSTypes.COLOR_LIME) {
                return new JBColor(new Color(0x00FF00), new Color(0x00FF00)); // RGB(0, 255, 0)
            }
            if (element.getNode().getElementType() == PCSTypes.COLOR_MAROON) {
                return new JBColor(new Color(0x800000), new Color(0x800000)); // RGB(128, 0, 0)
            }
            if (element.getNode().getElementType() == PCSTypes.COLOR_NAVY) {
                return new JBColor(new Color(0x000080), new Color(0x000080)); // RGB(0, 0, 128)
            }
            if (element.getNode().getElementType() == PCSTypes.COLOR_OLIVE) {
                return new JBColor(new Color(0x808000), new Color(0x808000)); // RGB(128, 128, 0)
            }
            if (element.getNode().getElementType() == PCSTypes.COLOR_PURPLE) {
                return new JBColor(new Color(0x800080), new Color(0x800080)); // RGB(128, 0, 128)
            }
            if (element.getNode().getElementType() == PCSTypes.COLOR_RED) {
                return JBColor.RED;
            }
            if (element.getNode().getElementType() == PCSTypes.COLOR_SILVER) {
                return new JBColor(new Color(0xC0C0C0), new Color(0xC0C0C0)); // RGB(192, 192, 192)
            }
            if (element.getNode().getElementType() == PCSTypes.COLOR_TEAL) {
                return new JBColor(new Color(0x008080), new Color(0x008080)); // RGB(0, 128, 128)
            }
            if (element.getNode().getElementType() == PCSTypes.COLOR_WHITE) {
                return new JBColor(new Color(0xFFFFFF), new Color(0xFFFFFF)); // RGB(255, 255, 255)
            }
            if (element.getNode().getElementType() == PCSTypes.COLOR_YELLOW) {
                return JBColor.YELLOW;
            }
            if (element.getNode().getElementType() == PCSTypes.NUMBER) {
                try {
                    //System.out.println("Element text: " + element.getText());
                    int numberColor = Integer.parseInt(element.getText());
                    if (numberColor < 0 || numberColor > 0xFFFFFF) {
                        return null;
                    }
                    int r = numberColor & 0xFF;
                    int g = (numberColor >> 8) & 0xFF;
                    int b = (numberColor >> 16) & 0xFF;

                    return new JBColor(new Color(r, g, b), new Color(r, g, b));
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }
        return null;  // Return null if no color is found
    }

    @Override
    public void setColorTo(@NotNull PsiElement element, @NotNull Color color) {
        // Do nothing
        return; // Return nothing
    }
}
