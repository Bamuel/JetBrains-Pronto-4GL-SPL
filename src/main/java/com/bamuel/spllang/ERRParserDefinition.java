package com.bamuel.spllang;

import com.bamuel.spllang.parser.ERRParser;
import com.bamuel.spllang.psi.ERRFile;
import com.bamuel.spllang.psi.ERRTokenSets;
import com.bamuel.spllang.psi.ERRTypes;
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

final class ERRParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE = new IFileElementType(ERRLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new ERRLexerAdapter();
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return ERRTokenSets.COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiParser createParser(final Project project) {
        return new ERRParser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new ERRFile(viewProvider);
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return ERRTypes.Factory.createElement(node);
    }

}