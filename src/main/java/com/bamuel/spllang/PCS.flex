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
FLOAT_COORDINATES=[0-9]+\.[0-9]+,[0-9]+\.[0-9]+
PICTURE=x\([0-9]+\)
BOOLEAN=(true|false)


SINGLEQUOTE="'"[^']*"'"
DOUBLEQUOTE="\""[^\"]*"\""

OVERRIDE_SCREEN_DEFINITION=(screen|menu|procedure)
OVERRIDE_STATEMENT=(accept|display|option|mode|confirm|trigger|window|box|message)
COMMON_CLAUSES_STATEMENTS=(suppress|name|orig_coordinates|orig_coordinate|orig_text|tag|text|helpline|title|default|allow|allowed|disallow|disallowed|add|supress|readonly|read_only|scale|coordinate|coordinates|title_coordinate|title_coordinates|picture|window_size)

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

//trigger_point
//trigger_point = screen_load|screen_exit|before_accept|after_accept|on_help|before_confirm|confirmed|not_confirmed|validate_mode|option|before_option|display
<YYINITIAL> "trigger_point"                                 { return PCSTypes.TRIGGER_POINT; }
<YYINITIAL> "screen_load"                                   { return PCSTypes.TRIGGER_POINT_SCREEN_LOAD; }
<YYINITIAL> "screen_exit"                                   { return PCSTypes.TRIGGER_POINT_SCREEN_EXIT; }
<YYINITIAL> "before_accept"                                 { return PCSTypes.TRIGGER_POINT_BEFORE_ACCEPT; }
<YYINITIAL> "after_accept"                                  { return PCSTypes.TRIGGER_POINT_AFTER_ACCEPT; }
<YYINITIAL> "on_help"                                       { return PCSTypes.TRIGGER_POINT_ON_HELP; }
<YYINITIAL> "before_confirm"                                { return PCSTypes.TRIGGER_POINT_BEFORE_CONFIRM; }
<YYINITIAL> "confirmed"                                     { return PCSTypes.TRIGGER_POINT_CONFIRMED; }
<YYINITIAL> "not_confirmed"                                 { return PCSTypes.TRIGGER_POINT_NOT_CONFIRMED; }
<YYINITIAL> "validate_mode"                                 { return PCSTypes.TRIGGER_POINT_VALIDATE_MODE; }
<YYINITIAL> "option"                                        { return PCSTypes.TRIGGER_POINT_OPTION; }
<YYINITIAL> "before_option"                                 { return PCSTypes.TRIGGER_POINT_BEFORE_OPTION; }
<YYINITIAL> "display"                                       { return PCSTypes.TRIGGER_POINT_DISPLAY; }

//trigger_type
//trigger_type = windows|windows_with_status|unix|command|unix_with_status|command_with_status|pronto|export_data_grid
<YYINITIAL> (type|trigger_type)                            { return PCSTypes.TRIGGER_TYPE; }
<YYINITIAL> "windows"                                      { return PCSTypes.TRIGGER_TYPE_WINDOWS; }
<YYINITIAL> "windows_with_status"                          { return PCSTypes.TRIGGER_TYPE_WINDOWS_WITH_STATUS; }
<YYINITIAL> "unix"                                         { return PCSTypes.TRIGGER_TYPE_UNIX; }
<YYINITIAL> "command"                                      { return PCSTypes.TRIGGER_TYPE_COMMAND; }
<YYINITIAL> "unix_with_status"                             { return PCSTypes.TRIGGER_TYPE_UNIX_WITH_STATUS; }
<YYINITIAL> "command_with_status"                          { return PCSTypes.TRIGGER_TYPE_COMMAND_WITH_STATUS; }
<YYINITIAL> "pronto"                                       { return PCSTypes.TRIGGER_TYPE_PRONTO; }
<YYINITIAL> "export_data_grid"                             { return PCSTypes.TRIGGER_TYPE_EXPORT_DATA_GRID; }

//run
<YYINITIAL> "run"                                          { return PCSTypes.RUN; }

//color
//aqua | black | blue | fuchsia | green | grey | lime | maroon | navy | olive | purple | red | silver | teal | white | yellow
<YYINITIAL> (color|colour)                                 { return PCSTypes.COLOR; }
<YYINITIAL> "aqua"                                         { return PCSTypes.COLOR_AQUA; }
<YYINITIAL> "black"                                        { return PCSTypes.COLOR_BLACK; }
<YYINITIAL> "blue"                                         { return PCSTypes.COLOR_BLUE; }
<YYINITIAL> "fuchsia"                                      { return PCSTypes.COLOR_FUCHSIA; }
<YYINITIAL> "green"                                        { return PCSTypes.COLOR_GREEN; }
<YYINITIAL> "grey"                                         { return PCSTypes.COLOR_GREY; }
<YYINITIAL> "lime"                                         { return PCSTypes.COLOR_LIME; }
<YYINITIAL> "maroon"                                       { return PCSTypes.COLOR_MAROON; }
<YYINITIAL> "navy"                                         { return PCSTypes.COLOR_NAVY; }
<YYINITIAL> "olive"                                        { return PCSTypes.COLOR_OLIVE; }
<YYINITIAL> "purple"                                       { return PCSTypes.COLOR_PURPLE; }
<YYINITIAL> "red"                                          { return PCSTypes.COLOR_RED; }
<YYINITIAL> "silver"                                       { return PCSTypes.COLOR_SILVER; }
<YYINITIAL> "teal"                                         { return PCSTypes.COLOR_TEAL; }
<YYINITIAL> "white"                                        { return PCSTypes.COLOR_WHITE; }
<YYINITIAL> "yellow"                                       { return PCSTypes.COLOR_YELLOW; }

//attributes
//underline | underlined | prompt | data | inverse | bold | italic | flash | fixed | sunken | background | center | right | left
<YYINITIAL> "attributes"                                   { return PCSTypes.ATTRIBUTES; }
<YYINITIAL> "underline"                                    { return PCSTypes.ATTRIBUTES_UNDERLINE; }
<YYINITIAL> "underlined"                                   { return PCSTypes.ATTRIBUTES_UNDERLINED; }
<YYINITIAL> "prompt"                                       { return PCSTypes.ATTRIBUTES_PROMPT; }
<YYINITIAL> "data"                                         { return PCSTypes.ATTRIBUTES_DATA; }
<YYINITIAL> "inverse"                                      { return PCSTypes.ATTRIBUTES_INVERSE; }
<YYINITIAL> "bold"                                         { return PCSTypes.ATTRIBUTES_BOLD; }
<YYINITIAL> "italic"                                       { return PCSTypes.ATTRIBUTES_ITALIC; }
<YYINITIAL> "flash"                                        { return PCSTypes.ATTRIBUTES_FLASH; }
<YYINITIAL> "fixed"                                        { return PCSTypes.ATTRIBUTES_FIXED; }
<YYINITIAL> "sunken"                                       { return PCSTypes.ATTRIBUTES_SUNKEN; }
<YYINITIAL> "background"                                   { return PCSTypes.ATTRIBUTES_BACKGROUND; }
<YYINITIAL> "center"                                       { return PCSTypes.ATTRIBUTES_CENTER; }
<YYINITIAL> "right"                                        { return PCSTypes.ATTRIBUTES_RIGHT; }
<YYINITIAL> "left"                                         { return PCSTypes.ATTRIBUTES_LEFT; }


// Match identifiers
<YYINITIAL> {NUMBER}                                        { return PCSTypes.NUMBER; }
<YYINITIAL> {FLOAT}                                         { return PCSTypes.FLOAT; }
<YYINITIAL> {COORDINATES}                                   { return PCSTypes.COORDINATES; }
<YYINITIAL> {FLOAT_COORDINATES}                             { return PCSTypes.FLOAT_COORDINATES; }
<YYINITIAL> {PICTURE}                                       { return PCSTypes.PICTURE; }
<YYINITIAL> {BOOLEAN}                                       { return PCSTypes.BOOLEAN; }
<YYINITIAL> ","                                             { return PCSTypes.COMMA; }

// Match special characters and symbols
<YYINITIAL> "["                                            { return PCSTypes.LSQUARE; }
<YYINITIAL> "]"                                            { return PCSTypes.RSQUARE; }
<YYINITIAL> "{"                                            { return PCSTypes.LBRACE; }
<YYINITIAL> "}"                                            { return PCSTypes.RBRACE; }
<YYINITIAL> "="                                            { return PCSTypes.EQUALS; }
<YYINITIAL> ";"                                            { return PCSTypes.SEMICOLON; }
<YYINITIAL> "/"                                            { return PCSTypes.SLASH; }
<YYINITIAL> {IDENTIFIER}                                   { return PCSTypes.IDENTIFIER; }

// Catch-all for bad characters
[^]                                                         { return TokenType.BAD_CHARACTER; }