package com.bamuel.spllang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.bamuel.spllang.psi.PCSTypes;
import com.intellij.psi.TokenType;

%%

%class PCSLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}


// Define whitespace and identifier patterns
WHITE_SPACE=[\ \n\t\f]
IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_-]*
NUMBER=[0-9]+
SINGLEQUOTE="'"[^']*"'"
DOUBLEQUOTE="\""[^\"]*"\""

OVERRIDE_SCREEN_DEFINITION=(screen|menu|procedure) {IDENTIFIER}
OVERRIDE_STATEMENT=(accept|display|option|mode|confirm|trigger|window|box|message)
CLAUSES_FOR_STATEMENTS={IDENTIFIER} = {IDENTIFIER}

// Define comment styles
COMMENT="#"[^\r\n]*
BLOCK_COMMENT="/*" !([^]* "*/" [^]*) ("*/")?

%%

// Match whitespace
<YYINITIAL> {WHITE_SPACE}+                                 { return TokenType.WHITE_SPACE; }

<YYINITIAL> {SINGLEQUOTE}                                   { return PCSTypes.STRING; }
<YYINITIAL> {DOUBLEQUOTE}                                   { return PCSTypes.STRING; }

// Match comments
<YYINITIAL> {COMMENT}                                      { return PCSTypes.COMMENT; }
<YYINITIAL> {BLOCK_COMMENT}                                { return PCSTypes.BLOCK_COMMENT; }

// Match main syntax elements
<YYINITIAL> {OVERRIDE_SCREEN_DEFINITION}                   { return PCSTypes.OVERRIDE_SCREEN_DEFINITION; }
<YYINITIAL> {OVERRIDE_STATEMENT}                            { return PCSTypes.OVERRIDE_STATEMENT; }
<YYINITIAL> {CLAUSES_FOR_STATEMENTS}                        { return PCSTypes.CLAUSES_FOR_STATEMENTS; }

// Match identifiers
<YYINITIAL> {IDENTIFIER}                                    { return PCSTypes.IDENTIFIER; }
<YYINITIAL> {NUMBER}                                        { return PCSTypes.NUMBER; }
<YYINITIAL> ","                                             { return PCSTypes.COMMA; }

// Match special characters and symbols
<YYINITIAL> "{"                                            { return PCSTypes.LBRACE; }
<YYINITIAL> "}"                                            { return PCSTypes.RBRACE; }
<YYINITIAL> "="                                            { return PCSTypes.EQUALS; }
<YYINITIAL> ";"                                            { return PCSTypes.SEMICOLON; }

// Catch-all for bad characters
[^]                                                         { return TokenType.BAD_CHARACTER; }