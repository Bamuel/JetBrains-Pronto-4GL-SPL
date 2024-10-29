package com.bamuel.spllang;

import com.bamuel.spllang.parser.ENQParser;
import com.bamuel.spllang.psi.ENQFile;
import com.bamuel.spllang.psi.ENQTokenSets;
import com.bamuel.spllang.psi.ENQTypes;
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

final class ENQParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE = new IFileElementType(ENQLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new ENQLexerAdapter();
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return ENQTokenSets.COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiParser createParser(final Project project) {
        return new ENQParser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new ENQFile(viewProvider);
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return ENQTypes.Factory.createElement(node);
    }

}