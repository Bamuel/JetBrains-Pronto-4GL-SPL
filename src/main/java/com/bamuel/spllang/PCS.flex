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
FLOAT=[0-9]+\.[0-9]+
COORDINATES=[0-9]+,[0-9]+
PICTURE=x\([0-9]+\)
BOOLEAN=(true|false)

SINGLEQUOTE="'"[^']*"'"
DOUBLEQUOTE="\""[^\"]*"\""

OVERRIDE_SCREEN_DEFINITION=(screen|menu|procedure)
OVERRIDE_STATEMENT=(accept|display|option|mode|confirm|trigger|window|box|message)
COMMON_CLAUSES_STATEMENTS=(run|suppress|name|orig_coordinates|orig_coordinate|orig_text|tag|text|helpline|title|default|allow|allowed|disallow|disallowed|add|supress|readonly|read_only|scale|coordinate|coordinates|title_coordinate|title_coordinates|picture|attributes|color|colour|window_size)

//TRIGGER STATEMENTS
TRIGGER_POINT=(trigger_point)
TRIGGER_POINT_EQUALS = (screen_load|screen_exit|before_accept|after_accept|on_help|before_confirm|confirmed|not_confirmed|validate_mode|option|before_option|display)
TRIGGER_TYPE=(type|trigger_type)
TRIGGER_TYPE_EQUALS=(windows|windows_with_status|unix|command|unix_with_status|command_with_status|pronto|export_data_grid)

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
<YYINITIAL> {OVERRIDE_SCREEN_DEFINITION}                    { return PCSTypes.OVERRIDE_SCREEN_DEFINITION; }
<YYINITIAL> {OVERRIDE_STATEMENT}                            { return PCSTypes.OVERRIDE_STATEMENT; }
<YYINITIAL> {COMMON_CLAUSES_STATEMENTS}                     { return PCSTypes.COMMON_CLAUSES_STATEMENTS; }

<YYINITIAL> {TRIGGER_POINT}                                { return PCSTypes.TRIGGER_POINT; }
<YYINITIAL> {TRIGGER_POINT_EQUALS}                         { return PCSTypes.TRIGGER_POINT_EQUALS; }
<YYINITIAL> {TRIGGER_TYPE}                                 { return PCSTypes.TRIGGER_TYPE; }
<YYINITIAL> {TRIGGER_TYPE_EQUALS}                          { return PCSTypes.TRIGGER_TYPE_EQUALS; }


// Match identifiers
<YYINITIAL> {IDENTIFIER}                                    { return PCSTypes.IDENTIFIER; }
<YYINITIAL> {NUMBER}                                        { return PCSTypes.NUMBER; }
<YYINITIAL> {FLOAT}                                         { return PCSTypes.FLOAT; }
<YYINITIAL> {COORDINATES}                                   { return PCSTypes.COORDINATES; }
<YYINITIAL> {PICTURE}                                       { return PCSTypes.PICTURE; }
<YYINITIAL> {BOOLEAN}                                       { return PCSTypes.BOOLEAN; }
<YYINITIAL> ","                                             { return PCSTypes.COMMA; }

// Match special characters and symbols
<YYINITIAL> "{"                                            { return PCSTypes.LBRACE; }
<YYINITIAL> "}"                                            { return PCSTypes.RBRACE; }
<YYINITIAL> "="                                            { return PCSTypes.EQUALS; }
<YYINITIAL> ";"                                            { return PCSTypes.SEMICOLON; }

// Catch-all for bad characters
[^]                                                         { return TokenType.BAD_CHARACTER; }