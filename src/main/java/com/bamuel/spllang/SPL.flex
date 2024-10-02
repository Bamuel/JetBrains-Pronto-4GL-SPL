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
//KEYWORDCONTROL=(exit|break)
//KEYWORDOTHER=(menu|do|set|get|link|report|display|accept|print|mode|primary|refresh|reenter|object|field|version-number|open|initialise|ZERO|SPACES|TRUE|FALSE|YES|NO)
//KEYWORDSECTION=(parameters|returning|local field|local|detail|before|after)

//IDENTIFIER=(like|pic|type|x|int|blob|boolean|alpha|xml-handle|string|date|time)

//Local Syntax: local [field] field-name field-definition ...
//API Syntax: api api-function-name
//                [procedure-clauses]
//                procedural-statements
//            [end-api]
FUNCTION_DECLARATION=(api|field|local|menu|mode|object|parameter|procedure|returning|screen|confirmed|validation)
FUNCTION_DECLARATION_END=(end-api|end-menu|end-procedure|end-screen|endapi|endmenu|endprocedure|endscreen|end-confirm|end-validations|endconfirm|endvalidations)
EXPORT_CLAUSE=(export|user-trigger)
RESPONSIVE_CLAUSE=(responsive)

// SQL Keywords // SQL Operators // SQL Clauses
KEYWORD_SQL=(SELECT|DISTINCT|FROM|WHERE|UNION|ALL|ORDER|BY|DESC|GROUP|HAVING|FOR|UPDATE|INNER|JOIN|LEFT|RIGHT|FULL|OUTER|ON|EXISTS|NOT|IN|ALL|ANY|COMPARISON)
OPERATOR_SQL=(=|<>|!=|<|>|<=|>=|AND|OR|NOT|BETWEEN|LIKE)
CLAUSE_SQL=(SELECT|FROM|WHERE|UNION|ORDER|BY|GROUP|HAVING|JOIN|ON|INNER|LEFT|RIGHT|FULL|OUTER)

STATEMENTS=(abort|accept|acknowledge|audit|back-to-detail|begin work|box|break|call|call-url|check-box|clear|close|command|commit work|confirm|continue|continue-entry|delete|disable-all-triggers|display|do|drop-down|enquiry|exit|extract|field-group|for|get|initialise|insert|link|lock-method|message|message-box|need|open|option|page|pause|pop|position|print|push|query|radio-button|re-enter|refresh|repeat|report|report section|re-select|restore|rollback work|save|screen-group|screen-section|select|serial|set|set-date-validation|skip|spl|sql-delete|sql-update|statement-group|string|switch|transaction|unlock|update|version-number|web-client-local-agent|while|workstation-local-agent)
ARITHMETIC_EXPRESSIONS=(\+|-|\*|\/|%)
RELATIONAL_EXPRESSIONS=(=|<>|!=|<=|>=|<|>|like|not like)
LOGICAL_EXPRESSIONS=(and|or|not)

//FUNCTIONS
ARITHMETIC_FUNCTIONS=(aand|abs|anot|aor|cos|fraction|integer|lshift|max-value|max-presentation-value|min-value|power-of|random|rshift|sign-of|sin|smallest-increment|square-root|sum|sum-array|tan)
DATETIME_FUNCTIONS=(add-month|client-date-time-string|date-from-date-time|date-time|date-to-julian|day|day-name|days-in-month|dow|gmt|hour|julian|julian-to-date|leap-year|minute|month|month-name|second|systime|time-from-date-time|time-zone|tod|today|year)
ENVIRONMENT_FUNCTIONS=(active-pid|api-application-name|batched|can-dde|check-auth|colour-picker|create-db-schema|create-db-user|currency-sign|database-type|db-command|db-table-name|dde-error-status|dde-execute|dde-initiate|dde-poke|dde-request|dde-terminate|delete-registry-value|enable-status-bar|enable-system-menu|enable-tool-bar|error-description|escape|exit-status|find-parameter|get-env|get-field-value|get-field-value-numeric|get-function-code|get-module-code|get-param|get-registry-enum-key|get-registry-enum-value|get-registry-value|get-system-metrics|gid|grant-db-schema|hide-dockable-windows|idx|if-then-else|io-count|line-no|local-no and local-yes|login-id|mail-add-line|mail-attach|mail-cancel|mail-from-name|mail-reply-to|mail-send|mail-start|max-screen-columns|max-screen-rows|message-status|mode-name|mouse-column|mouse-row|node-name|occurrence|operating-system|page-no|param-cnt|pid|pronto-release|prouser-flags|refresh-quick-links|report-is-xml|review-row|revoke-db-schema|rgb-to-colour|screen-mode|search|search-mode|set-app-user function|set-data-area-name|set-background-image|set-background-url|set-environment|set-function-code|set-help-context|set-keyboard-focus|set-module-code|set-registry-value|set-web-window|sleep|spool-file-name|time-elapsed|transaction-active|tty|uid|user-group|valid-activation-key|wait-for-input)
FILEHANDLING_FUNCTIONS=(cd|cd-without-close-all|client-file-browse|dir|file-exists|file-name|file-owner|file-size|file-status|file-version|finish-dir-search|is-a-dir|local-cd|local-cd-without-close-all|local-dir|mkdir|modification-time|next-dir-entry|rmdir|start-dir-search)
OLE_FUNCTIONS=(ole-addref|ole-advise-event|ole-bulk-put|ole-call-interactive-method|ole-call-method|ole-create-control|ole-create-instance|ole-enum-next|ole-enum-reset|ole-error-description|ole-get-active-object|ole-get-dispatch-id|ole-get-event|ole-get-property|ole-put-property|ole-put-property-byref|ole-query-interface|ole-release|ole-status|ole-unadvise-all|ole-unadvise-event)
SECURITY_FUNCTIONS=(crc32|decrypt|encrypt|security-level|sign-data|verify-signed-data)
STRING_FUNCTIONS=(ascii-char|ascii-num|base64-to-blob|blob-append|blob-append-base64|blob-section|blob-to-base64|concat|format-picture|free-blob|fstr|left-justify|lowercase|ltrim|max-alpha-value|num|param-text|pattern|read-blob-from-file|reserved|right-justify|rtrim|size-of|sql-substring|str-concat|str|str-len|sub-string|substring-utf8|uppercase|valid-number|write-blob-to-file|zstr)
XML_FUNCTIONS=(xml-add-child-node|xml-add-child-node-blob|xml-add-child-node-clob|xml-add-child-node-no-quotes|xml-add-child-node-number|xml-add-child-node-text|xml-add-node-attribute|xml-add-ns|xml-child-node-blob|xml-child-node-clob|xml-child-node-text|xml-close-document|xml-copy-node-handle|xml-delete-node|xml-delete-node-attribute|xml-document-is-json|xml-free-node-handle|xml-get-child-by-name|xml-get-doc-encoding|xml-get-first-attribute-name|xml-get-first-child-node|xml-get-last-child-node|xml-get-next-attribute-name|xml-get-next-node|xml-get-node-attribute|xml-get-ns-prefix-url|xml-get-prev-node|xml-get-root-node|xml-modify-node-attribute|xml-modify-node-text|xml-new-document|xml-next-element-sibling|xml-node-blob|xml-node-clob|xml-node-name|xml-node-ns-prefix|xml-node-ns-url|xml-node-string|xml-node-text|xml-node-type|xml-parse-file|xml-parse-text|xml-prev-element-sibling|xml-save-as-file|xml-save-as-file-ex|xml-save-as-text|xml-save-as-text-ex|xml-set-json-array|xml-validate-doc)

PREFDEFINED=(true|false|yes|no|zero|zero_date|zero_time|zero_date_time|space|spaces)
PREPROCESSOR=(#include|#define|#define|#undef|#ifdef|#ifndef|#if|#else|#endif)
KEYWORDS_OTHER=(if|else|elseif|endif|then|endselect|end-select|case|end-on|endswitch|end-switch|parameters|returning|local field|local|detail|before|after)
//if|for|while|endif|endfor|endwhile
//SQL_DEFINE=(select|endselect|get|update|insert)
//OPERATION_SIGN=(to|or|and|not|in|\\+|-|=|\\*|\/|\\+=|-=|\\*=|\/=|!=|<=|>=|<|>|<>)
//MACROS=#(define|include|ifndef|if|else|endif)

%%

<YYINITIAL> {WHITE_SPACE}+                                  { return TokenType.WHITE_SPACE; }

<YYINITIAL> {COMMENT}                                       { return SPLTypes.COMMENT; }
<YYINITIAL> {BLOCK_COMMENT}                                 { return SPLTypes.BLOCK_COMMENT; }

<YYINITIAL> {FUNCTION_DECLARATION}                          { return SPLTypes.FUNCTION_DECLARATION; }
<YYINITIAL> {FUNCTION_DECLARATION_END}                      { return SPLTypes.FUNCTION_DECLARATION; }
<YYINITIAL> {EXPORT_CLAUSE}                                 { return SPLTypes.FUNCTION_DECLARATION; }
<YYINITIAL> {RESPONSIVE_CLAUSE}                             { return SPLTypes.FUNCTION_DECLARATION; }
<YYINITIAL> {ARITHMETIC_FUNCTIONS}                          { return SPLTypes.FUNCTION_DECLARATION; }
<YYINITIAL> {DATETIME_FUNCTIONS}                            { return SPLTypes.FUNCTION_DECLARATION; }
<YYINITIAL> {ENVIRONMENT_FUNCTIONS}                         { return SPLTypes.FUNCTION_DECLARATION; }
<YYINITIAL> {FILEHANDLING_FUNCTIONS}                        { return SPLTypes.FUNCTION_DECLARATION; }
<YYINITIAL> {OLE_FUNCTIONS}                                 { return SPLTypes.FUNCTION_DECLARATION; }
<YYINITIAL> {SECURITY_FUNCTIONS}                            { return SPLTypes.FUNCTION_DECLARATION; }
<YYINITIAL> {STRING_FUNCTIONS}                              { return SPLTypes.FUNCTION_DECLARATION; }
<YYINITIAL> {XML_FUNCTIONS}                                 { return SPLTypes.FUNCTION_DECLARATION; }

<YYINITIAL> {PREPROCESSOR}                                  { return SPLTypes.KEYWORD; }
<YYINITIAL> {PREFDEFINED}                                   { return SPLTypes.KEYWORD; }
<YYINITIAL> {STATEMENTS}                                    { return SPLTypes.KEYWORD; }
<YYINITIAL> {KEYWORDS_OTHER}                                { return SPLTypes.KEYWORD; }

<YYINITIAL> {SINGLEQUOTE}                                   { return SPLTypes.STRING; }
<YYINITIAL> {DOUBLEQUOTE}                                   { return SPLTypes.STRING; }

<YYINITIAL> {NUMBER}                                        { return SPLTypes.NUMBER; }

<YYINITIAL> {IDENTIFIER}                                    { return SPLTypes.IDENTIFIER; }

<YYINITIAL> ";"                                             { return SPLTypes.SEMICOLON; }
<YYINITIAL> "."                                             { return SPLTypes.DOT; }
<YYINITIAL> "="                                             { return SPLTypes.EQUALS; }
<YYINITIAL> "{"                                             { return SPLTypes.LBRACE; }
<YYINITIAL> "}"                                             { return SPLTypes.RBRACE; }
<YYINITIAL> "["                                             { return SPLTypes.LBRACE; }
<YYINITIAL> "]"                                             { return SPLTypes.RBRACE; }
<YYINITIAL> "("                                             { return SPLTypes.LPAREN; }
<YYINITIAL> ")"                                             { return SPLTypes.RPAREN; }
<YYINITIAL> ","                                             { return SPLTypes.COMMA; }
<YYINITIAL> "@"                                             { return SPLTypes.AT; }

<YYINITIAL> "\\"                                            { return SPLTypes.SYMBOLS; }
<YYINITIAL> "+"                                             { return SPLTypes.SYMBOLS; }
<YYINITIAL> "-"                                             { return SPLTypes.SYMBOLS; }
<YYINITIAL> "*"                                             { return SPLTypes.SYMBOLS; }
<YYINITIAL> "/"                                             { return SPLTypes.SYMBOLS; }
<YYINITIAL> "%"                                             { return SPLTypes.SYMBOLS; }
<YYINITIAL> "!="                                            { return SPLTypes.SYMBOLS; }
<YYINITIAL> ":"                                             { return SPLTypes.SYMBOLS; }
<YYINITIAL> ":"                                             { return SPLTypes.SYMBOLS; }
<YYINITIAL> ">"                                             { return SPLTypes.SYMBOLS; }
<YYINITIAL> "<"                                             { return SPLTypes.SYMBOLS; }
<YYINITIAL> "<>"                                            { return SPLTypes.SYMBOLS; }

[^]                                                         { return TokenType.BAD_CHARACTER; }