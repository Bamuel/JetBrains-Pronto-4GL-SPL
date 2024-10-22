package com.bamuel.spllang;

import com.intellij.lang.documentation.DocumentationMarkup;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.intellij.model.Pointer;
import com.intellij.platform.backend.documentation.DocumentationResult;
import com.intellij.platform.backend.documentation.DocumentationTarget;
import com.intellij.platform.backend.presentation.TargetPresentation;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SPLDocumentationTarget implements DocumentationTarget {

    private final PsiElement element;
    private final PsiElement originalElement;

    public SPLDocumentationTarget(@NotNull PsiElement element, @Nullable PsiElement originalElement) {
        this.element = element;
        this.originalElement = originalElement;
    }

    @Override
    public @Nullable DocumentationResult computeDocumentation() {

        String documentationSource = loadDocumentationFromFile("docs/procedure_declaration.htm");
        if (documentationSource == null) {
            documentationSource = "<p>Error loading documentation.</p>";
        }
        String documentationContent = generateDoc(element, originalElement, documentationSource);
        return DocumentationResult.documentation(documentationContent);
    }

    @Override
    public @Nullable String computeDocumentationHint() {
        return generateDocumentationHint(element);
    }

    @Override
    public @NotNull TargetPresentation computePresentation() {
        return TargetPresentation.builder(element.getText()).presentation();
    }

    @Override
    public @NotNull Pointer<? extends DocumentationTarget> createPointer() {
        return new Pointer<DocumentationTarget>() {
            @Override
            public DocumentationTarget dereference() {
                return new SPLDocumentationTarget(element, originalElement);
            }
        };
    }

    public @Nullable String generateDoc(PsiElement element, @Nullable PsiElement originalElement, @Nullable String documentationSource) {
        // Retrieve necessary details for documentation
        String description = getDescription(documentationSource);
        System.out.println("description: " + description);
        String syntax = getSyntax(element);
        String clauses = getClauses(element);
        String notes = getNotes(element);

        StringBuilder documentationBuilder = new StringBuilder();

        documentationBuilder.append(DocumentationMarkup.DEFINITION_START);
        documentationBuilder.append("<strong>4GL language reference: ").append(element.getText());
        documentationBuilder.append(DocumentationMarkup.DEFINITION_END);

        documentationBuilder.append(DocumentationMarkup.CONTENT_START);
        documentationBuilder.append("<strong>Description:</strong><br>").append(description);
        documentationBuilder.append(DocumentationMarkup.CONTENT_END);

        documentationBuilder.append(DocumentationMarkup.INFORMATION_ICON);
        documentationBuilder.append(DocumentationMarkup.SECTIONS_START);

        addKeyValueSection("Syntax:", syntax, documentationBuilder);
        addKeyValueSection("Clauses:", clauses, documentationBuilder);
        addKeyValueSection("Notes:", notes, documentationBuilder);

        documentationBuilder.append(DocumentationMarkup.SECTIONS_END);

        return documentationBuilder.toString();
    }

    private void addKeyValueSection(String key, String value, StringBuilder sb) {
        sb.append(DocumentationMarkup.SECTION_HEADER_START);
        sb.append("<strong>").append(key).append("</strong>");
        sb.append(DocumentationMarkup.SECTION_SEPARATOR);
        sb.append("<p>").append(value).append(DocumentationMarkup.SECTION_END);
    }

    private String generateDocumentationHint(@NotNull PsiElement element) {
        return "Definition: A brief overview of '" + element.getText() + "'.";
    }

    private String getDescription(String documentationSource) {
        StringBuilder description = new StringBuilder(); // Use StringBuilder
        try {
            Document doc = Jsoup.parse(documentationSource);
            Element mainContent = doc.getElementById("mc-main-content");
            if (mainContent != null) {
                Elements elements = mainContent.children();
                for (Element element : elements) {
                    if (!element.select(".MCExpandingBody").isEmpty()) {
                        element.select(".MCExpandingBody").remove();
                    }
                    //remove JS
                    //removeJavaScript(element);

                    if (element.tagName().equals("p")) {
                        description.append(element.outerHtml());
                    }
                    if (element.text().contains("Syntax")) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "<p>Error extracting description.</p>";
        }
        // Return the description, or a fallback message if it's empty
        return description.length() == 0 ? "<p>No description available.</p>" : description.toString();
    }

    private String getSyntax(PsiElement element) {
        // Logic to extract the syntax for the documentation
        // Placeholder implementation - replace with actual logic
        return "Syntax of " + element.getText() + ".";
    }

    private String getClauses(PsiElement element) {
        // Logic to extract clauses for the documentation
        // Placeholder implementation - replace with actual logic
        return "Clauses related to " + element.getText() + ".";
    }

    private String getNotes(PsiElement element) {
        // Logic to extract notes for the documentation
        // Placeholder implementation - replace with actual logic
        return "Additional notes about " + element.getText() + ".";
    }

    // Helper method to load the HTML content from the resources directory
    private @Nullable String loadDocumentationFromFile(String resourcePath) {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(resourcePath); // No leading slash
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            if (inputStream == null) {
                return null;
            }

            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to remove JavaScript and event handlers from an element
    private void removeJavaScript(Element element) {
        // Remove all 'onclick' and other event handler attributes
        element.removeAttr("onclick");
        element.removeAttr("onmouseover");
        element.removeAttr("onmouseout");
        element.removeAttr("onload");
        // Add more as needed (e.g., onsubmit, onfocus, etc.)

        // If you want to remove all script elements from the document:
        element.select("script").remove();
    }
}
