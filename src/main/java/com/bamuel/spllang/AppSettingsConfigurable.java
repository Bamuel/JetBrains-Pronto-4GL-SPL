package com.bamuel.spllang;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

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
        AppSettings.State state = Objects.requireNonNull(AppSettings.getInstance().getState());
        return !Objects.equals(mySettingsComponent.getApplicationText(), state.application) ||
                !Objects.equals(mySettingsComponent.getData_area(), state.dataarea);
    }

    @Override
    public void apply() {
        AppSettings.State state = Objects.requireNonNull(AppSettings.getInstance().getState());
        state.application = mySettingsComponent.getApplicationText(); // Save the application
        state.dataarea = mySettingsComponent.getData_area(); // Save the dataarea list
    }

    @Override
    public void reset() {
        AppSettings.State state = Objects.requireNonNull(AppSettings.getInstance().getState());
        mySettingsComponent.setApplicationText(state.application); // Load the application into the UI
        mySettingsComponent.setData_area(state.dataarea); // Load the dataarea list into the UI
    }

    @Override
    public void disposeUIResources() {
        mySettingsComponent = null;
    }
}