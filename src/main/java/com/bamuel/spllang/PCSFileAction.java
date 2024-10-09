package com.bamuel.spllang;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class PCSFileAction extends AnAction {

    
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            return;
        }

        // Get the current context directory (if applicable)
        VirtualFile currentFile = e.getData(com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE);
        VirtualFile initialDirectory = currentFile != null && currentFile.isDirectory() ? currentFile : null;

        // Choose the directory where the new file will be created
        VirtualFile selectedDirectory;

        if (initialDirectory != null) {
            selectedDirectory = initialDirectory; // Use the current directory if available
        } else {
            // Prompt for directory if current one is not found
            FileChooserDescriptor descriptor = new FileChooserDescriptor(false, true, false, false, false, false);
            selectedDirectory = FileChooser.chooseFile(descriptor, project, null);
        }

        if (selectedDirectory != null) {
            // Prompt the user for the new file name
            String fileName = Messages.showInputDialog(project, "Enter new file name:", "New PCS File", Messages.getQuestionIcon());

            if (fileName != null && !fileName.trim().isEmpty()) {
                // Trim whitespace and check for ".pcs" extension
                String trimmedFileName = fileName.trim();
                if (!trimmedFileName.toLowerCase().endsWith(".pcs")) {
                    trimmedFileName += ".pcs"; // Append .pcs if not already present
                }
                String Outputfile = trimmedFileName;

                // Use WriteAction to create the file
                ApplicationManager.getApplication().runWriteAction(() -> {
                    try {
                        // Use pcsFileAction.this to refer to the outer class
                        VirtualFile newFile = selectedDirectory.createChildData(this, Outputfile);
                        FileEditorManager.getInstance(project).openFile(newFile, true);
                    } catch (Exception ex) {
                        Messages.showErrorDialog(project, "Failed to create file: " + ex.getMessage(), "Error");
                    }
                });
            } else {
                Messages.showErrorDialog(project, "File name cannot be empty.", "Error");
            }
        }
    }
}
