package com.bamuel.spllang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.bamuel.spllang.psi.ERRTypes;
import com.intellij.psi.TokenType;

%%

%class ERRLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}


WHITE_SPACE=[\ \n\t\f]

LINE_NUMBER=line\ [0-9]+ :
VARIABLE="'"[^']*"'"
COMMENT=--.*\n

// Improved FILEPATH pattern to ensure it ends with a file extension
FILEPATH=([a-zA-Z0-9_\-]+[\\/])*[a-zA-Z0-9_\-]+\\.[a-zA-Z0-9]{2,4}

// Messages are alphanumeric words, but excluding those with slashes
MESSAGE=[a-zA-Z0-9_]+

SYMBOL=[^\w\s] // Matches any non-word and non-whitespace character

%%

// Match whitespace
<YYINITIAL> {WHITE_SPACE}+                                 { return TokenType.WHITE_SPACE; }

<YYINITIAL> "ERROR"                                        { return ERRTypes.ERROR; }
<YYINITIAL> "WARNING"                                      { return ERRTypes.WARNING; }
<YYINITIAL> "INFO"                                         { return ERRTypes.INFO; }
<YYINITIAL> "DATABASE-SQL"                                 { return ERRTypes.KEYWORD; }
<YYINITIAL> ","                                            { return ERRTypes.COMMA; }
<YYINITIAL> "-"                                            { return ERRTypes.DASH; }

<YYINITIAL> {MESSAGE}                                      { return ERRTypes.MESSAGE; }
<YYINITIAL> {LINE_NUMBER}                                  { return ERRTypes.LINE_NUMBER; }
<YYINITIAL> {VARIABLE}                                     { return ERRTypes.VARIABLE; }
// Match symbols (e.g., special characters in messages)
<YYINITIAL> {SYMBOL}                                       { return ERRTypes.SYMBOL; }

<YYINITIAL> {COMMENT}                                      { return ERRTypes.COMMENT; }
[^]                                                        { return TokenType.BAD_CHARACTER; }