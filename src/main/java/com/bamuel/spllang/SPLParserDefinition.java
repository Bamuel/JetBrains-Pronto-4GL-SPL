package com.bamuel.spllang;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.bamuel.spllang.parser.SPLParser;
import com.bamuel.spllang.psi.SPLFile;
import com.bamuel.spllang.psi.SPLTokenSets;
import com.bamuel.spllang.psi.SPLTypes;
import org.jetbrains.annotations.NotNull;

final class SPLParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE = new IFileElementType(SPLLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new SPLLexerAdapter();
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return SPLTokenSets.COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiParser createParser(final Project project) {
        return new SPLParser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new SPLFile(viewProvider);
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return SPLTypes.Factory.createElement(node);
    }

}