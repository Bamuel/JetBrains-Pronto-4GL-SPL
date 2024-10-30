package com.bamuel.spllang;

import com.bamuel.spllang.psi.SPLTypes;
import com.intellij.openapi.editor.ElementColorProvider;
import com.intellij.psi.PsiElement;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class SPLColorProvider implements ElementColorProvider {

    @Override
    public @Nullable Color getColorFrom(@NotNull PsiElement element) {
        if (element.getParent().getNode() == null) {
            return null;
        }
        if (element.getNode().getElementType() == SPLTypes.COLOUR_NAME) {
            String colorText = element.getText();
            switch (colorText) {
                // https://developer.mozilla.org/en-US/docs/Web/CSS/named-color
                // Based on 16 named colors
                case "aqua":
                    return new JBColor(new Color(0x00FFFF), new Color(0x00FFFF)); // RGB(0, 255, 255)
                case "black":
                    return new JBColor(new Color(0x000000), new Color(0x000000)); // RGB(0, 0, 0)
                case "blue":
                    return new JBColor(new Color(0x0000FF), new Color(0x0000FF)); // RGB(0, 0, 255)
                case "fuchsia":
                    return new JBColor(new Color(0xFF00FF), new Color(0xFF00FF)); // RGB(255, 0, 255)
                case "green":
                    return new JBColor(new Color(0x008000), new Color(0x008000)); // RGB(0, 128, 0)
                case "grey":
                case "gray":
                    return new JBColor(new Color(0x808080), new Color(0x808080)); // RGB(128, 128, 128)
                case "lime":
                    return new JBColor(new Color(0x00FF00), new Color(0x00FF00)); // RGB(0, 255, 0)
                case "maroon":
                    return new JBColor(new Color(0x800000), new Color(0x800000)); // RGB(128, 0, 0)
                case "navy":
                    return new JBColor(new Color(0x000080), new Color(0x000080)); // RGB(0, 0, 128)
                case "olive":
                    return new JBColor(new Color(0x808000), new Color(0x808000)); // RGB(128, 128, 0)
                case "purple":
                    return new JBColor(new Color(0x800080), new Color(0x800080)); // RGB(128, 0, 128)
                case "red":
                    return new JBColor(new Color(0xFF0000), new Color(0xFF0000)); // RGB(255, 0, 0)
                case "silver":
                    return new JBColor(new Color(0xC0C0C0), new Color(0xC0C0C0)); // RGB(192, 192, 192)
                case "teal":
                    return new JBColor(new Color(0x008080), new Color(0x008080)); // RGB(0, 128, 128)
                case "white":
                    return new JBColor(new Color(0xFFFFFF), new Color(0xFFFFFF)); // RGB(255, 255, 255)
                case "yellow":
                    return new JBColor(new Color(0xFFFF00), new Color(0xFFFF00)); // RGB(255, 255, 0)
            }
        }
        if (element.getParent().getNode().getElementType() == SPLTypes.COLOURNAMES_){
            if (element.getNode().getElementType() == SPLTypes.NUMBER) {
                try {
                    //System.out.println("Element text: " + element.getText());
                    int numberColor = Integer.parseInt(element.getText());
                    //0 returns 88,88,88
                    if (numberColor == 0) {
                        return new JBColor(Gray._88, Gray._88); // RGB(88, 88, 88)
                    }

                    //needs to be number above 0x1000000
                    if (numberColor < 0x1000000 || numberColor > (0xFFFFFF+0x1000000)) {
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
