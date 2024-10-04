package com.bamuel.spllang;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class AppSettingsComponent {

    private final JPanel myMainPanel;
    private final JBTextField myApplicationText = new JBTextField();
    private final JLabel myCommandPreview = new JLabel();
    private final JButton myHyperlinkButton = new JButton("Test Command");

    private final JBList<String> myPathsList; // List for displaying the dataarea
    private final DefaultListModel<String> pathsListModel; // Data model for the list
    private final JButton addButton = new JButton("Add");
    private final JButton editButton = new JButton("Edit");
    private final JButton removeButton = new JButton("Remove");

    public AppSettingsComponent() {
        pathsListModel = new DefaultListModel<>();
        myPathsList = new JBList<>(pathsListModel); // Create the list component

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(myPathsList, BorderLayout.CENTER);
        listPanel.setPreferredSize(new Dimension(200, 150));
        listPanel.add(new JScrollPane(myPathsList), BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(removeButton);

        JPanel commandTestPanel = new JPanel();
        commandTestPanel.add(myCommandPreview);
        commandTestPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        commandTestPanel.add(myHyperlinkButton);

        //official command format: application:data-area bmsmenu [-execute module function]
        //pronto:data-area bmsmenu [-execute module function]
        myMainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("application:"), myApplicationText, 1, false)
                .addLabeledComponent(new JBLabel("data-area:"), listPanel, 1, false)
                .addComponent(buttonsPanel)
                .addComponent(commandTestPanel)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();

        setupButtonActions();
        updateCommandPreview();

        myApplicationText.getDocument().addDocumentListener((SimpleDocumentListener) e -> updateCommandPreview());
        myPathsList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updateCommandPreview();
            }
        });
        myHyperlinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLinkInBrowser();
            }
        });
    }

    // Set up the button actions for Add, Edit, and Remove
    private void setupButtonActions() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPath = JOptionPane.showInputDialog(myMainPanel, "Enter new dataarea:", "Add dataarea", JOptionPane.PLAIN_MESSAGE);
                if (newPath != null && !newPath.trim().isEmpty()) {
                    pathsListModel.addElement(newPath.trim());
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = myPathsList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String currentPath = pathsListModel.get(selectedIndex);
                    String newPath = JOptionPane.showInputDialog(myMainPanel, "Edit dataarea:", currentPath);
                    if (newPath != null && !newPath.trim().isEmpty()) {
                        pathsListModel.set(selectedIndex, newPath.trim());
                    }
                } else {
                    JOptionPane.showMessageDialog(myMainPanel, "Please select a dataarea to edit.", "No dataarea Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = myPathsList.getSelectedIndex();
                if (selectedIndex != -1) {
                    pathsListModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(myMainPanel, "Please select a dataarea to remove.", "No dataarea Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    // Get the list of dataarea from the UI component
    public List<String> getData_area() {
        List<String> dataarea = new ArrayList<>();
        for (int i = 0; i < pathsListModel.size(); i++) {
            dataarea.add(pathsListModel.get(i));
        }
        return dataarea;
    }

    // Set the list of dataarea in the UI component
    public void setData_area(List<String> dataarea) {
        pathsListModel.clear();
        for (String path : dataarea) {
            pathsListModel.addElement(path);
        }
    }

    private void updateCommandPreview() {
        String application = myApplicationText.getText().trim();
        String dataarea = myPathsList.getSelectedValue();

        String command = application + ":" + dataarea + " bmsmenu";
        myCommandPreview.setText(command);

        //if the application&dataarea is null/blank, disable the hyperlink button
        myHyperlinkButton.setEnabled(application != null && !application.isBlank() && dataarea != null && !dataarea.isBlank());
    }

    private void openLinkInBrowser() {
        try {
            String application = myApplicationText.getText().trim();
            String dataarea = myPathsList.getSelectedValue();
            String command = application + ":" + dataarea + " bmsmenu";
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