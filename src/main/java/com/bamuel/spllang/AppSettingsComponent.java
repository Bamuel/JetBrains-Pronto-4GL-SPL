package com.bamuel.spllang;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog.
 * It dynamically updates a preview of the command string and provides a clickable hyperlink.
 */
public class AppSettingsComponent {

    private final JPanel myMainPanel;
    private final JBTextField myApplicationText = new JBTextField();
    private final JBTextField myPathText = new JBTextField();
    private final JLabel myCommandPreview = new JLabel(); // Label for displaying the dynamic preview
    private final JButton myHyperlinkButton = new JButton("Test Command"); // Button that acts as a clickable hyperlink

    public AppSettingsComponent() {
        myMainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("Application:"), myApplicationText, 1, false)
                .addLabeledComponent(new JBLabel("Path:"), myPathText, 1, false)
                .addLabeledComponent(new JBLabel("Command Preview:"), myCommandPreview, 1, false)
                .addComponent(myHyperlinkButton, 1)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();


        // Set initial preview and hyperlink button state
        updateCommandPreview();

        // Add listeners to update preview and hyperlink when user types
        myApplicationText.getDocument().addDocumentListener((SimpleDocumentListener) e -> updateCommandPreview());
        myPathText.getDocument().addDocumentListener((SimpleDocumentListener) e -> updateCommandPreview());

        // Add action listener to the hyperlink button
        myHyperlinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLinkInBrowser();
            }
        });
    }

    // Update the preview command dynamically as user types
    private void updateCommandPreview() {
        String application = myApplicationText.getText().trim();
        String path = myPathText.getText().trim();

        // Construct the dynamic command preview
        String command = application + ":" + path + " bmsmenu";
        myCommandPreview.setText(command);
    }

    // Open the dynamically generated link in the browser
    private void openLinkInBrowser() {
        try {
            String application = myApplicationText.getText().trim();
            String path = myPathText.getText().trim();
            String command = application + ":" + path + " bmsmenu";
            URI uri = new URI(command.replace(" ", "%20"));

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(uri);
            } else {
                System.err.println("Desktop is not supported on this platform.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JPanel getPanel() {
        return myMainPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return myApplicationText;
    }

    @NotNull
    public String getApplicationText() {
        return myApplicationText.getText();
    }

    public void setApplicationText(@NotNull String newText) {
        myApplicationText.setText(newText);
        updateCommandPreview();
    }

    @NotNull
    public String getPathText() {
        return myPathText.getText();
    }

    public void setPathText(@NotNull String newText) {
        myPathText.setText(newText);
        updateCommandPreview();
    }

    // Document listener helper for dynamic updates
    @FunctionalInterface
    public interface SimpleDocumentListener extends javax.swing.event.DocumentListener {
        void update(javax.swing.event.DocumentEvent e);

        @Override
        default void insertUpdate(javax.swing.event.DocumentEvent e) {
            update(e);
        }

        @Override
        default void removeUpdate(javax.swing.event.DocumentEvent e) {
            update(e);
        }

        @Override
        default void changedUpdate(javax.swing.event.DocumentEvent e) {
            update(e);
        }
    }

}