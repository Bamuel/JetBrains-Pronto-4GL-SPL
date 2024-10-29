package com.bamuel.spllang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.bamuel.spllang.psi.ENQTypes;
import com.intellij.psi.TokenType;

%%

%class ENQLexer
%implements FlexLexer
%unicode
%ignorecase
%function advance
%type IElementType
%eof{  return;
%eof}


// Define whitespace and identifier patterns
WHITE_SPACE=[\ \n\t\f]
IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_-]*
NUMBER=-?[0-9]+
FLOAT=-?[0-9]+\.[0-9]+
PARAMETER=\$[0-9]
//date 00-00-0000
DATE=[0-9]{2}-[0-9]{2}-[0-9]{4}

SINGLEQUOTE="'"[^']*"'"
DOUBLEQUOTE="\""[^\"]*"\""

// Define comment styles
COMMENT="//"[^\r\n]*
BLOCK_COMMENT="/*" !([^]* "*/" [^]*) ("*/")?

%%

// Match whitespace
<YYINITIAL> {WHITE_SPACE}+                                        { return TokenType.WHITE_SPACE; }

<YYINITIAL> {SINGLEQUOTE}                                         { return ENQTypes.STRING; }
<YYINITIAL> {DOUBLEQUOTE}                                         { return ENQTypes.STRING; }

// Match comments
<YYINITIAL> {COMMENT}                                             { return ENQTypes.COMMENT; }
<YYINITIAL> {BLOCK_COMMENT}                                       { return ENQTypes.BLOCK_COMMENT; }

// Match DML keywords
<YYINITIAL> "SELECT"                                             { return ENQTypes.SELECT; }
<YYINITIAL> "INSERT"                                             { return ENQTypes.INSERT; }
<YYINITIAL> "UPDATE"                                             { return ENQTypes.UPDATE; }
<YYINITIAL> "DELETE"                                             { return ENQTypes.DELETE; }
<YYINITIAL> "FROM"                                               { return ENQTypes.FROM; }
<YYINITIAL> "WHERE"                                              { return ENQTypes.WHERE; }
<YYINITIAL> "ORDER"                                              { return ENQTypes.ORDER; }
<YYINITIAL> "BY"                                                 { return ENQTypes.BY; }
<YYINITIAL> "SET"                                                { return ENQTypes.SET; }
<YYINITIAL> "VALUES"                                             { return ENQTypes.VALUES; }

// Match logical keywords
<YYINITIAL> "AND"                                                { return ENQTypes.AND; }
<YYINITIAL> "OR"                                                 { return ENQTypes.OR; }
<YYINITIAL> "NOT"                                                { return ENQTypes.NOT; }
<YYINITIAL> "IN"                                                 { return ENQTypes.IN; }

// Match Pronto SQL logic?
<YYINITIAL> {PARAMETER}                                          { return ENQTypes.PARAMETER; }
<YYINITIAL> "INPUT"                                              { return ENQTypes.INPUT; }
<YYINITIAL> "FORMAT"                                             { return ENQTypes.FORMAT; }
<YYINITIAL> "IS"                                                 { return ENQTypes.IS; }
<YYINITIAL> (PRN|CSV|EXTERNAL|XML|EXCEL|RPT|LST)                 { return ENQTypes.FORMAT_TYPE; }

//Predefined symbols
<YYINITIAL> "YES"                                                 { return ENQTypes.YES; }
<YYINITIAL> "NO"                                                  { return ENQTypes.NO; }
<YYINITIAL> "TRUE"                                                { return ENQTypes.TRUE; }
<YYINITIAL> "FALSE"                                               { return ENQTypes.FALSE; }
<YYINITIAL> "ZERO"                                                { return ENQTypes.ZERO; }
<YYINITIAL> (SPACE|SPACES)                                        { return ENQTypes.SPACE; }

//Artihmetic operators
<YYINITIAL> "+"                                                   { return ENQTypes.PLUS; }
<YYINITIAL> "-"                                                   { return ENQTypes.MINUS; }
<YYINITIAL> "*"                                                   { return ENQTypes.MULT; }
<YYINITIAL> "/"                                                   { return ENQTypes.DIV; }
<YYINITIAL> "%"                                                   { return ENQTypes.MOD; }

//Conditonal operators
<YYINITIAL> "="                                                   { return ENQTypes.EQUAL; }
<YYINITIAL> "!="                                                  { return ENQTypes.NOT_EQUAL; }
<YYINITIAL> "<"                                                   { return ENQTypes.LESS_THAN; }
<YYINITIAL> "<="                                                  { return ENQTypes.LESS_THAN_EQUAL; }
<YYINITIAL> ">"                                                   { return ENQTypes.GREATER_THAN; }
<YYINITIAL> ">="                                                  { return ENQTypes.GREATER_THAN_EQUAL; }
<YYINITIAL> "LIKE"                                                { return ENQTypes.LIKE; }


// Match special characters and symbols
<YYINITIAL> "."                                                   { return ENQTypes.DOT; }
<YYINITIAL> "["                                                   { return ENQTypes.LSQUARE; }
<YYINITIAL> "]"                                                   { return ENQTypes.RSQUARE; }
<YYINITIAL> "("                                                   { return ENQTypes.LPAREN; }
<YYINITIAL> ")"                                                   { return ENQTypes.RPAREN; }
<YYINITIAL> "{"                                                   { return ENQTypes.LBRACE; }
<YYINITIAL> "}"                                                   { return ENQTypes.RBRACE; }
<YYINITIAL> ";"                                                   { return ENQTypes.SEMICOLON; }

// Match identifiers
<YYINITIAL> {IDENTIFIER}                                          { return ENQTypes.IDENTIFIER; }
<YYINITIAL> {NUMBER}                                              { return ENQTypes.NUMBER; }
<YYINITIAL> {FLOAT}                                               { return ENQTypes.FLOAT; }
<YYINITIAL> {DATE}                                               { return ENQTypes.DATE; }
<YYINITIAL> ","                                                   { return ENQTypes.COMMA; }


// Catch-all for bad characters
[^]                                                               { return TokenType.BAD_CHARACTER; }