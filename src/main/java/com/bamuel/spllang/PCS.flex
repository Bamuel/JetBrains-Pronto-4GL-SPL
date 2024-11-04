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
NUMBER=-?[0-9]+
FLOAT=-?[0-9]+\.[0-9]+
COORDINATES=(-?[0-9]+),(-?[0-9]+)
FLOAT_COORDINATES=(-?[0-9]+\.[0-9]+),(-?[0-9]+\.[0-9]+)
BOOLEAN=(true|false)


SINGLEQUOTE="'"[^']*"'"
DOUBLEQUOTE="\""[^\"]*"\""

// Define comment styles
COMMENT="#"[^\r\n]*
BLOCK_COMMENT="/*" !([^]* "*/" [^]*) ("*/")?

// Define picture string
PICTURESTRING=(([$Z9*,./VSTB-]{4,100}) ([%-]*) ((DR|CR)?))
//Picture X string if 3 or more
PICTURESTRING2=(X{3,100})

%%

// Match whitespace
<YYINITIAL> {WHITE_SPACE}+                                 { return TokenType.WHITE_SPACE; }

<YYINITIAL> {SINGLEQUOTE}                                   { return PCSTypes.STRING; }
<YYINITIAL> {DOUBLEQUOTE}                                   { return PCSTypes.STRING; }

// Match comments
<YYINITIAL> {COMMENT}                                      { return PCSTypes.COMMENT; }
<YYINITIAL> {BLOCK_COMMENT}                                { return PCSTypes.BLOCK_COMMENT; }

// Match main syntax elements
//Override screen definition
<YYINITIAL> "screen"                                        { return PCSTypes.SCREEN; }
<YYINITIAL> "menu"                                          { return PCSTypes.MENU; }
<YYINITIAL> "procedure"                                     { return PCSTypes.PROCEDURE; }
//Override statement
<YYINITIAL> "accept"                                        { return PCSTypes.ACCEPT; }
<YYINITIAL> "display"                                       { return PCSTypes.DISPLAY; }
<YYINITIAL> "option"                                        { return PCSTypes.OPTION; }
<YYINITIAL> "mode"                                          { return PCSTypes.MODE; }
<YYINITIAL> "confirm"                                       { return PCSTypes.CONFIRM; }
<YYINITIAL> "trigger"                                       { return PCSTypes.TRIGGER; }
<YYINITIAL> "window"                                        { return PCSTypes.WINDOW; }
<YYINITIAL> "box"                                           { return PCSTypes.BOX; }
<YYINITIAL> "message"                                       { return PCSTypes.MESSAGE; }


<YYINITIAL> "tab_order"                                     { return PCSTypes.TABORDER; }
<YYINITIAL> "name"                                          { return PCSTypes.NAME; }
<YYINITIAL> "orig_coordinate"                               { return PCSTypes.ORIG_COORDINATE; }
<YYINITIAL> "orig_coordinates"                              { return PCSTypes.ORIG_COORDINATE; }
<YYINITIAL> "orig_text"                                     { return PCSTypes.ORIG_TEXT; }
<YYINITIAL> "tag"                                           { return PCSTypes.TAG; }
<YYINITIAL> "text"                                          { return PCSTypes.TEXT; }
<YYINITIAL> "helpline"                                      { return PCSTypes.HELPLINE; }
<YYINITIAL> "title"                                         { return PCSTypes.TITLE; }
<YYINITIAL> "default"                                       { return PCSTypes.DEFAULT; }
<YYINITIAL> "allow"                                         { return PCSTypes.ALLOW; }
<YYINITIAL> "allowed"                                       { return PCSTypes.ALLOW; }
<YYINITIAL> "disallow"                                      { return PCSTypes.DISALLOW; }
<YYINITIAL> "disallowed"                                    { return PCSTypes.DISALLOW; }
<YYINITIAL> "add"                                           { return PCSTypes.ADD; }
<YYINITIAL> "suppress"                                      { return PCSTypes.SUPPRESS; }
<YYINITIAL> "readonly"                                      { return PCSTypes.READONLY; }
<YYINITIAL> "read_only"                                     { return PCSTypes.READONLY; }
<YYINITIAL> "scale"                                         { return PCSTypes.SCALE; }
<YYINITIAL> "coordinate"                                    { return PCSTypes.COORDINATE; }
<YYINITIAL> "coordinates"                                   { return PCSTypes.COORDINATE; }
<YYINITIAL> "title_coordinate"                              { return PCSTypes.TITLE_COORDINATE; }
<YYINITIAL> "title_coordinates"                             { return PCSTypes.TITLE_COORDINATE; }
<YYINITIAL> "picture"                                       { return PCSTypes.PICTURE; }
<YYINITIAL> "attributes"                                    { return PCSTypes.ATTRIBUTES; }
<YYINITIAL> (color|colour)                                  { return PCSTypes.COLOR; }
<YYINITIAL> "window_size"                                   { return PCSTypes.WINDOW_SIZE; }

//undocumented
<YYINITIAL> (uppercase|lock|filter|column_number)           { return PCSTypes.UNDOCUMENTED; }





//trigger_point
//trigger_point = screen_load|screen_exit|before_accept|after_accept|on_help|before_confirm|confirmed|not_confirmed|validate_mode|option|before_option|display
<YYINITIAL> "trigger_point"                                 { return PCSTypes.TRIGGER_POINT; }

<YYINITIAL> "screen_load"                                   { return PCSTypes.SCREEN_LOAD; }
<YYINITIAL> "screen_exit"                                   { return PCSTypes.SCREEN_EXIT; }
<YYINITIAL> "before_accept"                                 { return PCSTypes.BEFORE_ACCEPT; }
<YYINITIAL> "after_accept"                                  { return PCSTypes.AFTER_ACCEPT; }
<YYINITIAL> "on_help"                                       { return PCSTypes.ON_HELP; }
<YYINITIAL> "before_confirm"                                { return PCSTypes.BEFORE_CONFIRM; }
<YYINITIAL> "confirmed"                                     { return PCSTypes.CONFIRMED; }
<YYINITIAL> "not_confirmed"                                 { return PCSTypes.NOT_CONFIRMED; }
<YYINITIAL> "validate_mode"                                 { return PCSTypes.VALIDATE_MODE; }
<YYINITIAL> "before_option"                                 { return PCSTypes.BEFORE_OPTION; }
<YYINITIAL> "pre_validate"                                  { return PCSTypes.PRE_VALIDATE; }

//trigger_type
<YYINITIAL> (type|trigger_type)                            { return PCSTypes.TRIGGER_TYPE; }

<YYINITIAL> "windows"                                      { return PCSTypes.WINDOWS; }
<YYINITIAL> "windows_with_status"                          { return PCSTypes.WINDOWS_WITH_STATUS; }
<YYINITIAL> "unix"                                         { return PCSTypes.UNIX; }
<YYINITIAL> "command"                                      { return PCSTypes.COMMAND; }
<YYINITIAL> "unix_with_status"                             { return PCSTypes.UNIX_WITH_STATUS; }
<YYINITIAL> "command_with_status"                          { return PCSTypes.COMMAND_WITH_STATUS; }
<YYINITIAL> "pronto"                                       { return PCSTypes.PRONTO; }
<YYINITIAL> "export_data_grid"                             { return PCSTypes.EXPORT_DATA_GRID; }

//run
<YYINITIAL> "run"                                          { return PCSTypes.RUN; }

//color
<YYINITIAL> "aqua"                                         { return PCSTypes.COLOR_AQUA; }
<YYINITIAL> "black"                                        { return PCSTypes.COLOR_BLACK; }
<YYINITIAL> "blue"                                         { return PCSTypes.COLOR_BLUE; }
<YYINITIAL> "fuchsia"                                      { return PCSTypes.COLOR_FUCHSIA; }
<YYINITIAL> "green"                                        { return PCSTypes.COLOR_GREEN; }
<YYINITIAL> (grey|gray)                                    { return PCSTypes.COLOR_GREY; }
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
<YYINITIAL> "underline"                                    { return PCSTypes.UNDERLINE; }
<YYINITIAL> "underlined"                                   { return PCSTypes.UNDERLINED; }
<YYINITIAL> "prompt"                                       { return PCSTypes.PROMPT; }
<YYINITIAL> "data"                                         { return PCSTypes.DATA; }
<YYINITIAL> "inverse"                                      { return PCSTypes.INVERSE; }
<YYINITIAL> "bold"                                         { return PCSTypes.BOLD; }
<YYINITIAL> "italic"                                       { return PCSTypes.ITALIC; }
<YYINITIAL> "flash"                                        { return PCSTypes.FLASH; }
<YYINITIAL> "fixed"                                        { return PCSTypes.FIXED; }
<YYINITIAL> "sunken"                                       { return PCSTypes.SUNKEN; }
<YYINITIAL> "background"                                   { return PCSTypes.BACKGROUND; }
<YYINITIAL> "center"                                       { return PCSTypes.CENTER; }
<YYINITIAL> "right"                                        { return PCSTypes.RIGHT; }
<YYINITIAL> "left"                                         { return PCSTypes.LEFT; }
<YYINITIAL> "border"                                       { return PCSTypes.BORDER; }


// Match identifiers
<YYINITIAL> {NUMBER}                                        { return PCSTypes.NUMBER; }
<YYINITIAL> {FLOAT}                                         { return PCSTypes.FLOAT; }
<YYINITIAL> {COORDINATES}                                   { return PCSTypes.COORDINATES; }
<YYINITIAL> {FLOAT_COORDINATES}                             { return PCSTypes.FLOAT_COORDINATES; }
<YYINITIAL> {BOOLEAN}                                       { return PCSTypes.BOOLEAN; }
<YYINITIAL> ","                                             { return PCSTypes.COMMA; }

// Match special characters and symbols
<YYINITIAL> "["                                            { return PCSTypes.LSQUARE; }
<YYINITIAL> "]"                                            { return PCSTypes.RSQUARE; }
<YYINITIAL> "("                                            { return PCSTypes.LPAREN; }
<YYINITIAL> ")"                                            { return PCSTypes.RPAREN; }
<YYINITIAL> "{"                                            { return PCSTypes.LBRACE; }
<YYINITIAL> "}"                                            { return PCSTypes.RBRACE; }
<YYINITIAL> "="                                            { return PCSTypes.EQUALS; }
<YYINITIAL> "/"                                            { return PCSTypes.SLASH; }
<YYINITIAL> "."                                            { return PCSTypes.DOT; }
<YYINITIAL> {IDENTIFIER}                                   { return PCSTypes.IDENTIFIER; }

// Catch-all for bad characters
[^]                                                         { return TokenType.BAD_CHARACTER; }