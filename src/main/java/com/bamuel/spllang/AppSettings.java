package com.bamuel.spllang;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@State(
        name = "com.bamuel.spllang.AppSettings",
        storages = @Storage("SPLlangSettingsPlugin.xml")
)
final class AppSettings implements PersistentStateComponent<AppSettings.State> {
    static class State {
        @NonNls
        public String application = "pronto"; // Default value for application
        public List<String> dataarea = new ArrayList<>(); // Default value for dataarea
    }

    private State myState = new State();

    static AppSettings getInstance() {
        return ApplicationManager.getApplication().getService(AppSettings.class);
    }

    @Override
    public State getState() {
        return myState;
    }

    @Override
    public void loadState(@NotNull State state) {
        myState = state;
    }
}
