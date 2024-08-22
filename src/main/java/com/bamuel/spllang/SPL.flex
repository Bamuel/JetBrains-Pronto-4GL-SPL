package com.bamuel.spllang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.bamuel.spllang.psi.SPLTypes;
import com.intellij.psi.TokenType;

%%

%class SPLLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

//CRLF=\R
WHITE_SPACE=[\ \n\t\f]
IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_-]*

//Currently correct, but may need to be updated
NUMBER=[0-9]+
SINGLEQUOTE="'"[^']*"'"
DOUBLEQUOTE="\""[^\"]*"\""
COMMENT="//"[^\r\n]*
BLOCK_COMMENT="/*" !([^]* "*/" [^]*) ("*/")?


//CONSTANT_KEYWORDS=(ZERO|SPACES|TRUE|FALSE|YES|NO)
//TYPE_KEYWORDS=(type|x|int|blob|boolean|alpha|xml-handle|string|date|time|like|pic)
//KEYWORD=(if|for|while|endif|endfor|endwhile)
KEYWORDCONTROL=(exit|break)
KEYWORDOTHER=(menu|do|set|get|link|report|display|accept|print|mode|primary|refresh|reenter|object|field|version-number|open|initialise|ZERO|SPACES|TRUE|FALSE|YES|NO)
KEYWORDSECTION=(parameters|returning|local field|local|detail|before|after)

//IDENTIFIER=(like|pic|type|x|int|blob|boolean|alpha|xml-handle|string|date|time)

FUNCTION_DECLARATION=(api|procedure|screen|endprocedure|endscreen|endapi)
//SQL_DEFINE=(select|endselect|get|update|insert)
//OPERATION_SIGN=(to|or|and|not|in|\\+|-|=|\\*|\/|\\+=|-=|\\*=|\/=|!=|<=|>=|<|>|<>)
//MACROS=#(define|include|ifndef|if|else|endif)

%%

<YYINITIAL> {WHITE_SPACE}+                                  { return TokenType.WHITE_SPACE; }

<YYINITIAL> {COMMENT}                                       { return SPLTypes.COMMENT; }
<YYINITIAL> {BLOCK_COMMENT}                                 { return SPLTypes.BLOCK_COMMENT; }

<YYINITIAL> {KEYWORDCONTROL}                                { return SPLTypes.KEYWORD; }
<YYINITIAL> {KEYWORDSECTION}                                { return SPLTypes.KEYWORD; }
<YYINITIAL> {KEYWORDOTHER}                                  { return SPLTypes.KEYWORD; }

<YYINITIAL> {FUNCTION_DECLARATION}                          { return SPLTypes.FUNCTION_DECLARATION; }

<YYINITIAL> {SINGLEQUOTE}                                   { return SPLTypes.STRING; }
<YYINITIAL> {DOUBLEQUOTE}                                   { return SPLTypes.STRING; }

<YYINITIAL> {NUMBER}                                        { return SPLTypes.NUMBER; }

<YYINITIAL> {IDENTIFIER}                                    { return SPLTypes.IDENTIFIER; }

<YYINITIAL> ";"                                             { return SPLTypes.SEMICOLON; }

[^]                                                         { return TokenType.BAD_CHARACTER; }