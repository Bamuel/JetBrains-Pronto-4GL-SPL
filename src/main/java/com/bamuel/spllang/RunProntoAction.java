package com.bamuel.spllang;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class RunProntoAction extends AnAction {

    @Override
    @SuppressWarnings("all")
    public void actionPerformed(@NotNull AnActionEvent event) {
        AppSettings appSettings = AppSettings.getInstance();
        String application = appSettings.getState().application;

        // Check if dataarea is empty
        if (appSettings.getState().dataarea.isEmpty()) {
            Notifications.Bus.notify(
                    new Notification("RunProntoAction", "Error", "Data-Area cannot be blank", NotificationType.ERROR)
            );
            openSettings(); // Open settings for the user to configure
            return;
        }

        // Get the list of dataareas
        var dataareas = appSettings.getState().dataarea;
        String dataarea;

        // Handle selection based on the length of dataareas
        if (dataareas.size() == 1) {
            dataarea = dataareas.get(0); // Get the first dataarea
        } else {
            try {
                dataarea = (String) JOptionPane.showInputDialog(
                        null,
                        "Select Data-Area:",
                        "Data-Area Selection",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        dataareas.toArray(),
                        dataareas.get(0) // Default selection
                );
            }catch (Exception e) {
                dataarea = null;
                }
            // If the user cancels the selection, return early
            if (dataarea == null) {
                return;
            }
        }

        // Check if application is blank
        if (application.isBlank() || dataarea.isBlank()) {
            Notifications.Bus.notify(
                    new Notification("RunProntoAction", "Error", "Application or Data-Area cannot be blank", NotificationType.ERROR)
            );
            openSettings(); // Open settings for the user to configure
            return;
        }

        // Retrieve the currently opened file
        PsiFile psiFile = event.getData(PlatformDataKeys.PSI_FILE);
        if (psiFile == null) {
            Notifications.Bus.notify(
                    new Notification("RunProntoAction", "Error", "No file is currently opened", NotificationType.ERROR)
            );
            return;
        }

        // Get the basename of the file along with its directory
        String fileName = psiFile.getName(); // Get the filename with extension
        String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.')); // Get the filename without extension

        // Get the project and source root
        Project project = event.getProject();
        if (project == null) {
            Notifications.Bus.notify(
                    new Notification("RunProntoAction", "Error", "Project not found", NotificationType.ERROR)
            );
            return;
        }

        VirtualFile sourceRoot = ProjectRootManager.getInstance(project).getFileIndex().getSourceRootForFile(psiFile.getVirtualFile());
        if (sourceRoot == null) {
            Notifications.Bus.notify(
                    new Notification("RunProntoAction", "Error", "Source root not found, Source root must be set!", NotificationType.ERROR)
            );
            return;
        }

        // Get the directory of the current file
        PsiDirectory psiDirectory = psiFile.getContainingDirectory();
        if (psiDirectory == null) {
            Notifications.Bus.notify(
                    new Notification("RunProntoAction", "Error", "Directory not found", NotificationType.ERROR)
            );
            return;
        }

        // Construct the fileNameWithPathFromSourceRoot
        String filePathFromSourceRoot = psiDirectory.getVirtualFile().getPath().replace(sourceRoot.getPath(), "");
        String fileNameWithPathFromSourceRoot = filePathFromSourceRoot + "/" + fileNameWithoutExtension;

        // Construct the command
        String command = String.format("%s:%s %s", application, dataarea, fileNameWithPathFromSourceRoot.replaceFirst("^/", ""));

        // Check if Desktop is supported on this platform
        if (Desktop.isDesktopSupported()) {
            try {
                URI uri = new URI(command.replace(" ", "%20"));
                Desktop.getDesktop().browse(uri);
                Notifications.Bus.notify(
                        new Notification("RunProntoAction", "Info", "Command: " + command, NotificationType.INFORMATION)
                );
            } catch (Exception e) {
                e.printStackTrace(); // Handle exceptions appropriately
                Notifications.Bus.notify(
                        new Notification("RunProntoAction", "Error", "Failed to run Pronto: " + e.getMessage(), NotificationType.ERROR)
                );
            }
        } else {
            System.err.println("Desktop is not supported on this platform.");
            Notifications.Bus.notify(
                    new Notification("RunProntoAction", "Error", "Desktop is not supported on this platform.", NotificationType.ERROR)
            );
        }
    }

    private void openSettings() {
        ShowSettingsUtil.getInstance().showSettingsDialog(null, "Pronto Xi Settings");
    }

    @Override
    @SuppressWarnings("all")
    public void update(AnActionEvent event) {
        super.update(event);
        PsiFile psiFile = event.getData(PlatformDataKeys.PSI_FILE);
        if (psiFile != null) {
            FileType fileType = psiFile.getFileType();
            // Check if the file type is SPL (replace 'SPLFileType' with your actual file type)
            event.getPresentation().setEnabledAndVisible(fileType.getDefaultExtension().equals("spl"));
        } else {
            event.getPresentation().setEnabledAndVisible(false);
        }
    }
}
