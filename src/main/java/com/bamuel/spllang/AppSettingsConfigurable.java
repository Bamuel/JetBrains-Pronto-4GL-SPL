package com.bamuel.spllang;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

/**
 * Provides controller functionality for application settings.
 */
final class AppSettingsConfigurable implements Configurable {

    private AppSettingsComponent mySettingsComponent;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Pronto Xi Settings";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return mySettingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        mySettingsComponent = new AppSettingsComponent();
        return mySettingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        AppSettings.State state =
                Objects.requireNonNull(AppSettings.getInstance().getState());
        return !mySettingsComponent.getApplicationText().equals(state.application) ||
                !mySettingsComponent.getPathText().equals(state.path);
    }

    @Override
    public void apply() {
        AppSettings.State state =
                Objects.requireNonNull(AppSettings.getInstance().getState());
        state.application = mySettingsComponent.getApplicationText();
        state.path = mySettingsComponent.getPathText();
    }

    @Override
    public void reset() {
        AppSettings.State state =
                Objects.requireNonNull(AppSettings.getInstance().getState());
        mySettingsComponent.setApplicationText(state.application);
        mySettingsComponent.setPathText(state.path);
    }

    @Override
    public void disposeUIResources() {
        mySettingsComponent = null;
    }

}
