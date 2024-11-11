package com.bamuel.spllang;

import com.bamuel.spllang.psi.SPLTypes;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

final class SPLLineMarkerProvider extends RelatedItemLineMarkerProvider {
    private static final Map<String, NavigationGutterIconBuilder<PsiElement>> ICONS = new HashMap<>();

    static {
        // Initialize BUTTON_ICONS map
        ICONS.put("MSG_BOX_OK", NavigationGutterIconBuilder.create(AllIcons.Actions.SetDefault).setTooltipText("Message Box OK"));
        ICONS.put("MSG_BOX_YES", NavigationGutterIconBuilder.create(AllIcons.Actions.SetDefault).setTooltipText("Message Box Yes"));
        ICONS.put("MSG_BOX_CANCEL", NavigationGutterIconBuilder.create(AllIcons.Actions.Cancel).setTooltipText("Message Box Cancel"));
        ICONS.put("MSG_BOX_NO", NavigationGutterIconBuilder.create(AllIcons.Actions.Cancel).setTooltipText("Message Box No"));
        ICONS.put("MSG_BOX_RETRY", NavigationGutterIconBuilder.create(AllIcons.Actions.Redo).setTooltipText("Message Box Retry"));

        //todo: find icons for these
        ICONS.put("MSG_BOX_OK_CANCEL", NavigationGutterIconBuilder.create(AllIcons.Actions.SetDefault).setTooltipText("Message Box OK & Cancel"));
        ICONS.put("MSG_BOX_YES_NO", NavigationGutterIconBuilder.create(AllIcons.Actions.SetDefault).setTooltipText("Message Box Yes & No"));
        ICONS.put("MSG_BOX_YES_NO_CANCEL", NavigationGutterIconBuilder.create(AllIcons.Actions.SetDefault).setTooltipText("Message Box Yes & No & Cancel"));

        ICONS.put("MSG_BOX_STOP", NavigationGutterIconBuilder.create(AllIcons.General.Error).setTooltipText("Message Box Stop Icon"));
        ICONS.put("MSG_BOX_WARNING", NavigationGutterIconBuilder.create(AllIcons.General.Warning).setTooltipText("Message Box Warning Icon"));
        ICONS.put("MSG_BOX_INFORMATION", NavigationGutterIconBuilder.create(AllIcons.General.Information).setTooltipText("Message Box Information Icon"));
        ICONS.put("MSG_BOX_QUESTION", NavigationGutterIconBuilder.create(SPLIcons.QUESTION).setTooltipText("Message Box Question Icon"));
        ICONS.put("MSG_BOX_EXCLAMATION", NavigationGutterIconBuilder.create(SPLIcons.QUESTION).setTooltipText("Message Box Question Icon"));
    }

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        String elementText = element.getText().toUpperCase();

        if (element.getNode().getElementType() == SPLTypes.MESSAGEBOX_BUTTONS_VALUES
        || element.getNode().getElementType() == SPLTypes.MESSAGEBOX_ICON_VALUES) {
            handleIcons(element, result, elementText);
        }
    }

    private void handleIcons(PsiElement element, Collection<? super RelatedItemLineMarkerInfo<?>> result, String elementText) {
        if (ICONS.containsKey(elementText)) {
            result.add(ICONS.get(elementText).setTargets(element).createLineMarkerInfo(element));
        }
    }
}
