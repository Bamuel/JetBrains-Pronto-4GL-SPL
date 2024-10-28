package com.bamuel.spllang;

import com.bamuel.spllang.psi.SPLTypes;
import com.intellij.openapi.editor.ElementColorProvider;
import com.intellij.psi.PsiElement;
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
        if (element.getParent().getNode().getElementType() == SPLTypes.COLOURNAMES_){
            if (element.getNode().getElementType() == SPLTypes.COLOUR_NAME) {
                String colorText = element.getText();
                switch (colorText) {
                    case "aqua":
                        return JBColor.CYAN;
                    case "black":
                        return new JBColor(new Color(0x000000), new Color(0x000000)); // RGB(0, 0, 0)
                    case "blue":
                        return JBColor.BLUE;
                    case "fuchsia":
                        return JBColor.MAGENTA;
                    case "green":
                        return JBColor.GREEN;
                    case "grey":
                    case "gray":
                        return JBColor.GRAY;
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
                        return JBColor.RED;
                    case "silver":
                        return new JBColor(new Color(0xC0C0C0), new Color(0xC0C0C0)); // RGB(192, 192, 192)
                    case "teal":
                        return new JBColor(new Color(0x008080), new Color(0x008080)); // RGB(0, 128, 128)
                    case "white":
                        return new JBColor(new Color(0xFFFFFF), new Color(0xFFFFFF)); // RGB(255, 255, 255)
                    case "yellow":
                        return JBColor.YELLOW;
                }
            }
            if (element.getNode().getElementType() == SPLTypes.NUMBER) {
                try {
                    //System.out.println("Element text: " + element.getText());
                    int numberColor = Integer.parseInt(element.getText());
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
