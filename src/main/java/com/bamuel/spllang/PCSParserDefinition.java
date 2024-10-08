package com.bamuel.spllang;

import com.bamuel.spllang.parser.PCSParser;
import com.bamuel.spllang.psi.PCSFile;
import com.bamuel.spllang.psi.PCSTokenSets;
import com.bamuel.spllang.psi.PCSTypes;
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
import org.jetbrains.annotations.NotNull;

final class PCSParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE = new IFileElementType(PCSLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new PCSLexerAdapter();
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return PCSTokenSets.COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiParser createParser(final Project project) {
        return new PCSParser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new PCSFile(viewProvider);
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return PCSTypes.Factory.createElement(node);
    }

}