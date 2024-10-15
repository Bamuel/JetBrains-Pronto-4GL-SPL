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
NUMBER=-?[0-9]+

DATE=(\d{2}-[A-Z]{3}-\d{4})
TIME=(\d{2}:\d{2}:\d{2})

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

FUNCTION_DECLARATION=(confirmed)
FUNCTION_DECLARATION_END=(end-confirm|endconfirm)

// SQL Keywords // SQL Operators // SQL Clauses
KEYWORD_SQL=(SELECT|DISTINCT|FROM|WHERE|UNION|ALL|ORDER|BY|DESC|GROUP|HAVING|FOR|UPDATE|INNER|JOIN|LEFT|RIGHT|FULL|OUTER|ON|EXISTS|NOT|IN|ALL|ANY|COMPARISON)
OPERATOR_SQL=(=|<>|!=|<|>|<=|>=|AND|OR|NOT|BETWEEN|LIKE)
CLAUSE_SQL=(SELECT|FROM|WHERE|UNION|ORDER|BY|GROUP|HAVING|JOIN|ON|INNER|LEFT|RIGHT|FULL|OUTER)

//STATEMENTS=(abort|accept|acknowledge|audit|back-to-detail|begin work|box|break|call|call-url|check-box|clear|close|command|commit work|confirm|continue|continue-entry|delete|disable-all-triggers|display|do|drop-down|enquiry|exit|extract|field-group|for|get|initialise|insert|link|lock-method|need|open|option|page|pause|pop|position|print|push|query|radio-button|re-enter|refresh|repeat|report|report section|re-select|restore|rollback work|save|screen-group|screen-section|select|serial|set|set-date-validation|skip|spl|sql-delete|sql-update|statement-group|switch|transaction|unlock|update|version-number|web-client-local-agent|while|workstation-local-agent)
ARITHMETIC_EXPRESSIONS=(\+|-|\*|\/|%)
RELATIONAL_EXPRESSIONS=(=|<>|!=|<=|>=|<|>|like|not like)
LOGICAL_EXPRESSIONS=(and|or|not)

//FUNCTIONS
//ARITHMETIC_FUNCTIONS=(aand|abs|anot|aor|cos|fraction|integer|lshift|max-value|max-presentation-value|min-value|power-of|random|rshift|sign-of|sin|smallest-increment|square-root|sum|sum-array|tan)
//DATETIME_FUNCTIONS=(add-month|client-date-time-string|date-from-date-time|date-time|date-to-julian|day|day-name|days-in-month|dow|gmt|hour|julian|julian-to-date|leap-year|minute|month|month-name|second|systime|time-from-date-time|time-zone|tod|today|year)
//ENVIRONMENT_FUNCTIONS=(active-pid|api-application-name|batched|can-dde|check-auth|colour-picker|create-db-schema|create-db-user|currency-sign|database-type|db-command|db-table-name|dde-error-status|dde-execute|dde-initiate|dde-poke|dde-request|dde-terminate|delete-registry-value|enable-status-bar|enable-system-menu|enable-tool-bar|error-description|escape|exit-status|find-parameter|get-env|get-field-value|get-field-value-numeric|get-function-code|get-module-code|get-param|get-registry-enum-key|get-registry-enum-value|get-registry-value|get-system-metrics|gid|grant-db-schema|hide-dockable-windows|idx|if-then-else|io-count|line-no|local-no and local-yes|login-id|mail-add-line|mail-attach|mail-cancel|mail-from-name|mail-reply-to|mail-send|mail-start|max-screen-columns|max-screen-rows|message-status|mode-name|mouse-column|mouse-row|node-name|occurrence|operating-system|page-no|param-cnt|pid|pronto-release|prouser-flags|refresh-quick-links|report-is-xml|review-row|revoke-db-schema|rgb-to-colour|screen-mode|search|search-mode|set-app-user function|set-data-area-name|set-background-image|set-background-url|set-environment|set-function-code|set-help-context|set-keyboard-focus|set-module-code|set-registry-value|set-web-window|sleep|spool-file-name|time-elapsed|transaction-active|tty|uid|user-group|valid-activation-key|wait-for-input)
OLE_FUNCTIONS=(ole-addref|ole-advise-event|ole-bulk-put|ole-call-interactive-method|ole-call-method|ole-create-control|ole-create-instance|ole-enum-next|ole-enum-reset|ole-error-description|ole-get-active-object|ole-get-dispatch-id|ole-get-event|ole-get-property|ole-put-property|ole-put-property-byref|ole-query-interface|ole-release|ole-status|ole-unadvise-all|ole-unadvise-event)
SECURITY_FUNCTIONS=(crc32|decrypt|encrypt|security-level|sign-data|verify-signed-data)
//STRING_FUNCTIONS=(blob-section|blob-to-base64|format-picture|free-blob|fstr|left-justify|ltrim|max-alpha-value|num|pattern|read-blob-from-file|reserved|right-justify|rtrim|size-of|sql-substring|str-len|sub-string|substring-utf8|valid-number|write-blob-to-file|zstr)
XML_FUNCTIONS=(xml-add-child-node|xml-add-child-node-blob|xml-add-child-node-clob|xml-add-child-node-no-quotes|xml-add-child-node-number|xml-add-child-node-text|xml-add-node-attribute|xml-add-ns|xml-child-node-blob|xml-child-node-clob|xml-child-node-text|xml-close-document|xml-copy-node-handle|xml-delete-node|xml-delete-node-attribute|xml-document-is-json|xml-free-node-handle|xml-get-child-by-name|xml-get-doc-encoding|xml-get-first-attribute-name|xml-get-first-child-node|xml-get-last-child-node|xml-get-next-attribute-name|xml-get-next-node|xml-get-node-attribute|xml-get-ns-prefix-url|xml-get-prev-node|xml-get-root-node|xml-modify-node-attribute|xml-modify-node-text|xml-new-document|xml-next-element-sibling|xml-node-blob|xml-node-clob|xml-node-name|xml-node-ns-prefix|xml-node-ns-url|xml-node-string|xml-node-text|xml-node-type|xml-parse-file|xml-parse-text|xml-prev-element-sibling|xml-save-as-file|xml-save-as-file-ex|xml-save-as-text|xml-save-as-text-ex|xml-set-json-array|xml-validate-doc)

PREFDEFINED=(true|false|yes|no|zero|zero_date|zero_time|zero_date_time|space|spaces)
PREPROCESSOR=(#include|#define|#define|#undef|#ifdef|#ifndef|#if|#else|#endif|#INCLUDE|#DEFINE|#DEFINE|#UNDEF|#IFDEF|#IFNDEF|#IF|#ELSE|#ENDIF)
KEYWORDS_OTHER=(if|else|elseif|endif|then|endselect|end-select|case|end-on|endswitch|end-switch|local field)
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
//<YYINITIAL> {ARITHMETIC_FUNCTIONS}                          { return SPLTypes.FUNCTION_DECLARATION; }
//<YYINITIAL> {DATETIME_FUNCTIONS}                            { return SPLTypes.FUNCTION_DECLARATION; }
//<YYINITIAL> {ENVIRONMENT_FUNCTIONS}                         { return SPLTypes.FUNCTION_DECLARATION; }
//<YYINITIAL> {FILEHANDLING_FUNCTIONS}                        { return SPLTypes.FUNCTION_DECLARATION; }
<YYINITIAL> {OLE_FUNCTIONS}                                 { return SPLTypes.FUNCTION_DECLARATION; }
<YYINITIAL> {SECURITY_FUNCTIONS}                            { return SPLTypes.FUNCTION_DECLARATION; }
//<YYINITIAL> {STRING_FUNCTIONS}                              { return SPLTypes.FUNCTION_DECLARATION; }
<YYINITIAL> {XML_FUNCTIONS}                                 { return SPLTypes.FUNCTION_DECLARATION; }

<YYINITIAL> {PREPROCESSOR}                                  { return SPLTypes.PREPROCESSOR; }
<YYINITIAL> {PREFDEFINED}                                   { return SPLTypes.KEYWORD; }
//<YYINITIAL> {STATEMENTS}                                    { return SPLTypes.KEYWORD; }
<YYINITIAL> {KEYWORDS_OTHER}                                { return SPLTypes.KEYWORD; }

<YYINITIAL> {SINGLEQUOTE}                                   { return SPLTypes.STRING; }
<YYINITIAL> {DOUBLEQUOTE}                                   { return SPLTypes.STRING; }

<YYINITIAL> {NUMBER}                                        { return SPLTypes.NUMBER; }

//Clauses
<YYINITIAL> (title|TITLE)                                        { return SPLTypes.TITLE; }
<YYINITIAL> (default|DEFAULT)                                    { return SPLTypes.DEFAULT; }
<YYINITIAL> (window|WINDOW)                                      { return SPLTypes.WINDOW; }
<YYINITIAL> (prompt|PROMPT)                                      { return SPLTypes.PROMPT; }
<YYINITIAL> (detail|DETAIL)                                      { return SPLTypes.DETAIL; }
<YYINITIAL> (icon|ICON)                                          { return SPLTypes.ICON; }
<YYINITIAL> (tag|TAG)                                            { return SPLTypes.TAG; }
<YYINITIAL> (help-context|HELP-CONTEXT|helpcontext|HELPCONTEXT)  { return SPLTypes.HELP_CONTEXT; }
<YYINITIAL> (optional|OPTIONAL)                                  { return SPLTypes.OPTIONAL; }
<YYINITIAL> (bell|BELL)                                          { return SPLTypes.BELL; }
<YYINITIAL> (no-bell|NO-BELL|nobell|NOBELL)                      { return SPLTypes.NOBELL; }
<YYINITIAL> (colour|COLOUR|color|COLOR)                          { return SPLTypes.COLOUR; }
<YYINITIAL> (type|TYPE)                                          { return SPLTypes.TYPE; }
<YYINITIAL> (pic|PIC|picture|PICTURE)                            { return SPLTypes.PIC; }
<YYINITIAL> (occurs|OCCURS)                                      { return SPLTypes.OCCURS; }
<YYINITIAL> (like|LIKE)                                          { return SPLTypes.LIKE; }
<YYINITIAL> (justify|JUSTIFY)                                    { return SPLTypes.JUSTIFY; }
<YYINITIAL> (use-name-in-db|USE-NAME-IN-DB)                      { return SPLTypes.USE_NAME_IN_DB; }
<YYINITIAL> (db-column-name|DB-COLUMN-NAME)                      { return SPLTypes.DB_COLUMN_NAME; }
<YYINITIAL> (drill-back|DRILL-BACK)                              { return SPLTypes.DRILL_BACK; }
<YYINITIAL> (relative to|RELATIVE TO)                            { return SPLTypes.RELATIVE_TO; }
<YYINITIAL> (window-position|WINDOW-POSITION|windowposition|WINDOWPOSITION) { return SPLTypes.WINDOW_POSITION; }
<YYINITIAL> (parameters|PARAMETERS|parameter|PARAMETER)          { return SPLTypes.PARAMETERS; }
<YYINITIAL> (returning|RETURNING)                                { return SPLTypes.RETURNING; }
<YYINITIAL> (stop-exit-all-key|STOP-EXIT-ALL-KEY)                { return SPLTypes.STOP_EXIT_ALL_KEY; }
<YYINITIAL> (export|user-trigger|EXPORT|USER-TRIGGER)            { return SPLTypes.EXPORT; }
<YYINITIAL> (can-override|no-override|CAN-OVERRIDE|NO-OVERRIDE)  { return SPLTypes.CAN_OVERRIDE; }
<YYINITIAL> (before|BEFORE)                                      { return SPLTypes.BEFORE; }
<YYINITIAL> (after|AFTER)                                        { return SPLTypes.AFTER; }
<YYINITIAL> (separator|SEPARATOR)                                { return SPLTypes.SEPARATOR; }
<YYINITIAL> (file|FILE)                                          { return SPLTypes.FILE; }
<YYINITIAL> (record|RECORD)                                      { return SPLTypes.RECORD; }
<YYINITIAL> (end-record|endrecord|END-RECORD|ENDRECORD)          { return SPLTypes.RECORD_END; }
<YYINITIAL> (key|KEY)                                            { return SPLTypes.KEY; }
<YYINITIAL> (desc|DESC|descending|DESCENDING)                    { return SPLTypes.DESC; }
<YYINITIAL> (unique|UNIQUE)                                      { return SPLTypes.UNIQUE; }
<YYINITIAL> (db-index-only|DB-INDEX-ONLY)                        { return SPLTypes.DB_INDEX_ONLY; }
<YYINITIAL> (compress|COMPRESS)                                  { return SPLTypes.COMPRESS; }
<YYINITIAL> (nojoins|NOJOINS|no-joins|NO-JOINS)                  { return SPLTypes.NOJOINS; }
<YYINITIAL> (norowid|NOROWID|no-rowid|NO-ROWID)                  { return SPLTypes.NOROWID; }
<YYINITIAL> (column-title|COLUMN-TITLE)                           { return SPLTypes.COLUMNTITLE; }
<YYINITIAL> (no-log|nolog|NO-LOG|NOLOG)                          { return SPLTypes.NOLOG; }
<YYINITIAL> (no-title|notitle|NO-TITLE|NOTITLE)                  { return SPLTypes.NOTITLE; }
<YYINITIAL> (multi-line|multiline|MULTI-LINE|MULTILINE)          { return SPLTypes.MULTILINE; }
<YYINITIAL> (rows|ROWS)                                          { return SPLTypes.ROWS; }
<YYINITIAL> (columns|COLUMNS)                                    { return SPLTypes.COLUMNS; }
<YYINITIAL> (blank-when-zero|BLANK-WHEN-ZERO)                    { return SPLTypes.BLANK_WHEN_ZERO; }
<YYINITIAL> (blank-decimals-when-zero|BLANK-DECIMALS-WHEN-ZERO)  { return SPLTypes.BLANK_DECIMALS_WHEN_ZERO; }
<YYINITIAL> (allow|ALLOW)                                        { return SPLTypes.ALLOW; }
<YYINITIAL> (disallow|DISALLOW)                                  { return SPLTypes.DISALLOW; }
<YYINITIAL> (auto-return|AUTO-RETURN)                            { return SPLTypes.AUTO_RETURN; }
<YYINITIAL> (time-out|TIME-OUT|timeout|TIMEOUT)                  { return SPLTypes.TIMEOUT; }
<YYINITIAL> (edit|EDIT|edits|EDITS)                              { return SPLTypes.EDIT; }
<YYINITIAL> (fill|FILL)                                          { return SPLTypes.FILL; }
<YYINITIAL> (lookup|LOOKUP)                                      { return SPLTypes.LOOKUP; }
<YYINITIAL> (left|LEFT)                                          { return SPLTypes.LEFT; }
<YYINITIAL> (centre|CENTRE)                                      { return SPLTypes.CENTRE; }
<YYINITIAL> (right|RIGHT)                                        { return SPLTypes.RIGHT; }
<YYINITIAL> (centre-coord|CENTRE-COORD)                          { return SPLTypes.CENTRE_COORD; }
<YYINITIAL> (right-coord|RIGHT-COORD)                            { return SPLTypes.RIGHT_COORD; }
<YYINITIAL> (bold|BOLD)                                          { return SPLTypes.BOLD; }
<YYINITIAL> (dim|DIM)                                            { return SPLTypes.DIM; }
<YYINITIAL> (flashing|FLASHING)                                  { return SPLTypes.FLASHING; }
<YYINITIAL> (inverse|INVERSE)                                    { return SPLTypes.INVERSE; }
<YYINITIAL> (italic|ITALIC)                                      { return SPLTypes.ITALIC; }
<YYINITIAL> (underlined|UNDERLINED)                              { return SPLTypes.UNDERLINED; }
<YYINITIAL> (foreground|FOREGROUND)                              { return SPLTypes.FOREGROUND; }
<YYINITIAL> (background|BACKGROUND)                              { return SPLTypes.BACKGROUND; }
<YYINITIAL> (blank|BLANK)                                        { return SPLTypes.BLANK; }
<YYINITIAL> (on error|ON ERROR)                                  { return SPLTypes.ON_ERROR; }
<YYINITIAL> (end-on|END-ON|endon|ENDON)                          { return SPLTypes.END_ON; }
<YYINITIAL> (UP-ARROW|DOWN-ARROW|LEFT-ARROW|RIGHT-ARROW|HELP-KEY) { return SPLTypes.FUNCTION_KEY; }
<YYINITIAL> (help-screen|HELP-SCREEN)                            { return SPLTypes.HELP_SCREEN; }
<YYINITIAL> (showing|SHOWING)                                    { return SPLTypes.SHOWING; }
<YYINITIAL> (using|USING)                                        { return SPLTypes.USING; }
<YYINITIAL> (show-value|SHOW-VALUE)                              { return SPLTypes.SHOW_VALUE; }
<YYINITIAL> (no-clear|NO-CLEAR)                                  { return SPLTypes.NO_CLEAR; }
<YYINITIAL> (suppress|SUPPRESS)                                  { return SPLTypes.SUPPRESS; }
<YYINITIAL> (conditional-suppress|CONDITIONAL-SUPPRESS)          { return SPLTypes.CONDITIONAL_SUPPRESS; }
<YYINITIAL> (read-only|READ-ONLY|readonly|READONLY)              { return SPLTypes.READ_ONLY; }
<YYINITIAL> (fixed-width-font|FIXED-WIDTH-FONT)                  { return SPLTypes.FIXED_WIDTH_FONT; }
<YYINITIAL> (before-accept|BEFORE-ACCEPT)                        { return SPLTypes.BEFORE_ACCEPT; }
<YYINITIAL> (end-before-accept|END-BEFORE-ACCEPT)                { return SPLTypes.END_BEFORE_ACCEPT; }
<YYINITIAL> (validations|VALIDATIONS|validation|VALIDATION)      { return SPLTypes.VALIDATIONS; }
<YYINITIAL> (end-validations|END-VALIDATIONS|end-validation|END-VALIDATION) { return SPLTypes.END_VALIDATIONS; }
<YYINITIAL> (use-validate-trigger|USE-VALIDATE-TRIGGER)          { return SPLTypes.USE_VALIDATE_TRIGGER; }
<YYINITIAL> (scale|SCALE)                                        { return SPLTypes.SCALE; }
<YYINITIAL> (no warning|NO WARNING)                              { return SPLTypes.NO_WARNING; }
<YYINITIAL> (on change|ON CHANGE)                                { return SPLTypes.ON_CHANGE; }
<YYINITIAL> (leaving|LEAVING)                                    { return SPLTypes.LEAVING; }
<YYINITIAL> (free-locks|FREE-LOCKS)                              { return SPLTypes.FREE_LOCKS; }
<YYINITIAL> (double|DOUBLE)                                      { return SPLTypes.DOUBLE; }
<YYINITIAL> (no-cross|NO-CROSS)                                  { return SPLTypes.NO_CROSS; }
<YYINITIAL> (sunken|SUNKEN)                                      { return SPLTypes.SUNKEN; }
<YYINITIAL> (absolute-coordinates|ABSOLUTE-COORDINATES)          { return SPLTypes.ABSOLUTE_COORDINATES; }
<YYINITIAL> (max-parameters|MAX-PARAMETERS)                      { return SPLTypes.MAX_PARAMETERS; }
<YYINITIAL> (home|HOME)                                          { return SPLTypes.HOME; }
<YYINITIAL> (batch|BATCH)                                        { return SPLTypes.BATCH; }
<YYINITIAL> (leave-files-open|LEAVE-FILES-OPEN)                  { return SPLTypes.LEAVE_FILES_OPEN; }
<YYINITIAL> (external|EXTERNAL)                                  { return SPLTypes.EXTERNAL; }
<YYINITIAL> (no-wait|wait)                                       { return SPLTypes.BACKGROUNDNOWAITWAIT; }
<YYINITIAL> (http-headers|HTTP-HEADERS)                          { return SPLTypes.HTTP_HEADERS; }
<YYINITIAL> (http-body|HTTP-BODY)                                { return SPLTypes.HTTP_BODY; }
<YYINITIAL> (http-method|HTTP-METHOD)                            { return SPLTypes.HTTP_METHOD; }
<YYINITIAL> (http-proxy|HTTP-PROXY)                              { return SPLTypes.HTTP_PROXY; }
<YYINITIAL> (http-auth-any|HTTP-AUTH-ANY)                        { return SPLTypes.HTTP_AUTH_ANY; }
<YYINITIAL> (ca-certificate|CA-CERTIFICATE)                      { return SPLTypes.CA_CERTIFICATE; }
<YYINITIAL> (ssl-certificate|SSL-CERTIFICATE)                    { return SPLTypes.SSL_CERTIFICATE; }
<YYINITIAL> (ssl-key|SSL-KEY)                                    { return SPLTypes.SSL_KEY; }
<YYINITIAL> (ssl_key_password|SSL_KEY_PASSWORD)                  { return SPLTypes.SSL_KEY_PASSWORD; }
<YYINITIAL> (allow-redirection|ALLOW-REDIRECTION)                { return SPLTypes.ALLOW_REDIRECTION; }
<YYINITIAL> (sftp-command|SFTP-COMMAND)                          { return SPLTypes.SFTP_COMMAND; }
<YYINITIAL> (sftp-post-command|SFTP-POST-COMMAND)                { return SPLTypes.SFTP_POST_COMMAND; }
<YYINITIAL> (sftp-create-dirs|SFTP-CREATE-DIRS)                  { return SPLTypes.SFTP_CREATE_DIRS; }
<YYINITIAL> (url-user|URL-USER)                                  { return SPLTypes.URL_USER; }
<YYINITIAL> (url-login-options|URL-LOGIN-OPTIONS)                { return SPLTypes.URL_LOGIN_OPTIONS; }
<YYINITIAL> (values|VALUES)                                      { return SPLTypes.VALUES; }
<YYINITIAL> (right-coordinate|RIGHT-COORDINATE)                  { return SPLTypes.RIGHT_COORDINATE; }
<YYINITIAL> (before-check-box|BEFORE-CHECK-BOX)                  { return SPLTypes.BEFORE_CHECK_BOX; }
<YYINITIAL> (end-before-check-box|END-BEFORE-CHECK-BOX)          { return SPLTypes.END_BEFORE_CHECK_BOX; }
<YYINITIAL> (message-buttons|MESSAGE-BUTTONS)                    { return SPLTypes.MESSAGEBOX_BUTTONS; }
<YYINITIAL> (MSG_BOX_OK|MSG_BOX_CANCEL|MSG_BOX_YES|MSG_BOX_NO|MSG_BOX_RETRY|MSG_BOX_OK_CANCEL|MSG_BOX_YES_NO|MSG_BOX_YES_NO_CANCEL) { return SPLTypes.MESSAGEBOX_BUTTONS_VALUES; }
<YYINITIAL> (MSG_BOX_STOP|MSG_BOX_WARNING|MSG_BOX_INFORMATION|MSG_BOX_QUESTION|MSG_BOX_EXCLAMATION) { return SPLTypes.MESSAGEBOX_ICON_VALUES; }





//Statements
//STATEMENTS=(abort|accept|acknowledge|audit|back-to-detail|begin work|box|break|call|call-url|check-box|clear|close|command|commit work|confirm|continue|continue-entry|delete|disable-all-triggers|display|do|drop-down|enquiry|exit|extract|field-group|for|get|initialise|insert|link|lock-method|need|open|option|page|pause|pop|position|print|push|query|radio-button|re-enter|refresh|repeat|report|report section|re-select|restore|rollback work|save|screen-group|screen-section|select|serial|set|set-date-validation|skip|spl|sql-delete|sql-update|statement-group|switch|transaction|unlock|update|version-number|web-client-local-agent|while|workstation-local-agent)
<YYINITIAL> (abort|ABORT)                                     { return SPLTypes.ABORT; }
<YYINITIAL> (accept|ACCEPT)                                   { return SPLTypes.ACCEPT; }
<YYINITIAL> (acknowledge|ACKNOWLEDGE)                         { return SPLTypes.ACKNOWLEDGE; }
<YYINITIAL> (audit|AUDIT|auditon|AUDITON)                     { return SPLTypes.AUDIT; }
<YYINITIAL> (back-to-detail|BACK-TO-DETAIL)                   { return SPLTypes.BACK_TO_DETAIL; }
<YYINITIAL> (begin work|BEGIN WORK)                           { return SPLTypes.BEGIN_WORK; }
<YYINITIAL> (box|BOX)                                         { return SPLTypes.BOX; }
<YYINITIAL> (break|BREAK)                                     { return SPLTypes.BREAK; }
<YYINITIAL> (call|CALL)                                       { return SPLTypes.CALL; }
<YYINITIAL> (call-url|CALL-URL)                               { return SPLTypes.CALL_URL; }
<YYINITIAL> (check-box|CHECK-BOX)                             { return SPLTypes.CHECK_BOX; }
<YYINITIAL> (end-check-box|end-check-box)                     { return SPLTypes.CHECK_BOX_END; }


<YYINITIAL> (message-box|messagebox|MESSAGE-BOX|MESSAGEBOX)   { return SPLTypes.MESSAGEBOX; }
<YYINITIAL> (message|MESSAGE)                                 { return SPLTypes.MESSAGE; }

<YYINITIAL> (else|ELSE)                                        { return SPLTypes.ELSE; }

//Arithmetic functions
<YYINITIAL> (aand|AAND)                                     { return SPLTypes.AAND; }
<YYINITIAL> (abs|ABS)                                       { return SPLTypes.ABS; }
<YYINITIAL> (anot|ANOT)                                     { return SPLTypes.ANOT; }
<YYINITIAL> (aor|AOR)                                       { return SPLTypes.AOR; }
<YYINITIAL> (cos|COS)                                       { return SPLTypes.COS; }
<YYINITIAL> (fraction|FRACTION)                             { return SPLTypes.FRACTION; }
<YYINITIAL> (integer|INTEGER)                               { return SPLTypes.INTEGER; }
<YYINITIAL> (lshift|LSHIFT)                                 { return SPLTypes.LSHIFT; }
<YYINITIAL> (max-value|MAX-VALUE)                           { return SPLTypes.MAX_VALUE; }
<YYINITIAL> (max-presentation-value|MAX-PRESENTATION-VALUE) { return SPLTypes.MAX_PRESENTATION_VALUE; }
<YYINITIAL> (min-value|MIN-VALUE)                           { return SPLTypes.MIN_VALUE; }
<YYINITIAL> (power-of|POWER-OF)                             { return SPLTypes.POWER_OF; }
<YYINITIAL> (random|RANDOM)                                 { return SPLTypes.RANDOM; }
<YYINITIAL> (rshift|RSHIFT)                                 { return SPLTypes.RSHIFT; }
<YYINITIAL> (sign-of|SIGN-OF)                               { return SPLTypes.SIGN_OF; }
<YYINITIAL> (sin|SIN)                                       { return SPLTypes.SIN; }
<YYINITIAL> (smallest-increment|SMALLEST-INCREMENT)         { return SPLTypes.SMALLEST_INCREMENT; }
<YYINITIAL> (square-root|SQUARE-ROOT)                       { return SPLTypes.SQUARE_ROOT; }
<YYINITIAL> (sum|SUM)                                       { return SPLTypes.SUM; }
<YYINITIAL> (sum-array|SUM-ARRAY)                           { return SPLTypes.SUM_ARRAY; }
<YYINITIAL> (tan|TAN)                                       { return SPLTypes.TAN; }

//DateTime functions
<YYINITIAL> {DATE}                                          { return SPLTypes.DATE; }
<YYINITIAL> {TIME}                                          { return SPLTypes.TIME; }
<YYINITIAL> (add-month|ADD-MONTH)                           { return SPLTypes.ADD_MONTH; }
<YYINITIAL> (client-date-time-string|CLIENT-DATE-TIME-STRING) { return SPLTypes.CLIENT_DATE_TIME_STRING; }
<YYINITIAL> (date-from-date-time|DATE-FROM-DATE-TIME)       { return SPLTypes.DATE_FROM_DATE_TIME; }
<YYINITIAL> (date-time|DATE-TIME)                           { return SPLTypes.DATE_TIME; }
<YYINITIAL> (date-to-julian|DATE-TO-JULIAN)                 { return SPLTypes.DATE_TO_JULIAN; }
<YYINITIAL> (day|DAY)                                       { return SPLTypes.DAY; }
<YYINITIAL> (day-name|DAY-NAME)                             { return SPLTypes.DAY_NAME; }
<YYINITIAL> (days-in-month|DAYS-IN-MONTH)                   { return SPLTypes.DAYS_IN_MONTH; }
<YYINITIAL> (dow|DOW)                                       { return SPLTypes.DOW; }
<YYINITIAL> (gmt|GMT|gmtime|GMTIME)                         { return SPLTypes.GMT; }
<YYINITIAL> (hour|HOUR)                                     { return SPLTypes.HOUR; }
<YYINITIAL> (julian|JULIAN)                                 { return SPLTypes.JULIAN; }
<YYINITIAL> (julian-to-date|JULIAN-TO-DATE|julian2date|JULIAN2DATE) { return SPLTypes.JULIAN_TO_DATE; }
<YYINITIAL> (leap-year|LEAP-YEAR)                           { return SPLTypes.LEAP_YEAR; }
<YYINITIAL> (minute|MINUTE)                                 { return SPLTypes.MINUTE; }
<YYINITIAL> (month|MONTH)                                   { return SPLTypes.MONTH; }
<YYINITIAL> (month-name|MONTH-NAME)                         { return SPLTypes.MONTH_NAME; }
<YYINITIAL> (second|SECOND)                                 { return SPLTypes.SECOND; }
<YYINITIAL> (systime|SYSTIME|sys-time|SYS-TIME)             { return SPLTypes.SYSTIME; }
<YYINITIAL> (time-from-date-time|TIME-FROM-DATE-TIME)       { return SPLTypes.TIME_FROM_DATE_TIME; }
<YYINITIAL> (time-zone|TIME-ZONE)                           { return SPLTypes.TIME_ZONE; }
<YYINITIAL> (tod|TOD)                                       { return SPLTypes.TOD; }
<YYINITIAL> (today|TODAY)                                   { return SPLTypes.TODAY; }
<YYINITIAL> (year|YEAR)                                     { return SPLTypes.YEAR; }

//Environment functions
<YYINITIAL> (active-pid|ACTIVE-PID)                         { return SPLTypes.ACTIVE_PID; }
<YYINITIAL> (api-application-name|API-APPLICATION-NAME)     { return SPLTypes.API_APPLICATION_NAME; }
<YYINITIAL> (batched|BATCHED)                               { return SPLTypes.BATCHED; }
<YYINITIAL> (can-dde|CAN-DDE)                               { return SPLTypes.CAN_DDE; }
<YYINITIAL> (check-auth|CHECK-AUTH)                         { return SPLTypes.CHECK_AUTH; }
<YYINITIAL> (colour-picker|COLOUR-PICKER)                   { return SPLTypes.COLOUR_PICKER; }
<YYINITIAL> (create-db-schema|CREATE-DB-SCHEMA)             { return SPLTypes.CREATE_DB_SCHEMA; }
<YYINITIAL> (create-db-user|CREATE-DB-USER)                 { return SPLTypes.CREATE_DB_USER; }
<YYINITIAL> (currency-sign|CURRENCY-SIGN)                   { return SPLTypes.CURRENCY_SIGN; }
<YYINITIAL> (database-type|DATABASE-TYPE)                   { return SPLTypes.DATABASE_TYPE; }
<YYINITIAL> (db-command|DB-COMMAND)                         { return SPLTypes.DB_COMMAND; }
<YYINITIAL> (db-table-name|DB-TABLE-NAME)                   { return SPLTypes.DB_TABLE_NAME; }
<YYINITIAL> (dde-error-status|DDE-ERROR-STATUS)             { return SPLTypes.DDE_ERROR_STATUS; }
<YYINITIAL> (dde-execute|DDE-EXECUTE)                       { return SPLTypes.DDE_EXECUTE; }
<YYINITIAL> (dde-initiate|DDE-INITIATE)                     { return SPLTypes.DDE_INITIATE; }
<YYINITIAL> (dde-poke|DDE-POKE)                             { return SPLTypes.DDE_POKE; }
<YYINITIAL> (dde-request|DDE-REQUEST)                       { return SPLTypes.DDE_REQUEST; }
<YYINITIAL> (dde-terminate|DDE-TERMINATE)                   { return SPLTypes.DDE_TERMINATE; }
<YYINITIAL> (delete-registry-value|DELETE-REGISTRY-VALUE)   { return SPLTypes.DELETE_REGISTRY_VALUE; }
<YYINITIAL> (enable-status-bar|ENABLE-STATUS-BAR)           { return SPLTypes.ENABLE_STATUS_BAR; }
<YYINITIAL> (enable-system-menu|ENABLE-SYSTEM-MENU)         { return SPLTypes.ENABLE_SYSTEM_MENU; }
<YYINITIAL> (enable-tool-bar|ENABLE-TOOL-BAR)               { return SPLTypes.ENABLE_TOOL_BAR; }
<YYINITIAL> (error-description|ERROR-DESCRIPTION)           { return SPLTypes.ERROR_DESCRIPTION; }
<YYINITIAL> (escape|ESCAPE)                                 { return SPLTypes.ESCAPE; }
<YYINITIAL> (exit-status|EXIT-STATUS)                       { return SPLTypes.EXIT_STATUS; }
<YYINITIAL> (find-parameter|FIND-PARAMETER)                 { return SPLTypes.FIND_PARAMETER; }
<YYINITIAL> (get-env|getenv|GET-ENV|GETENV)                 { return SPLTypes.GETENV; }
<YYINITIAL> (get-field-value|GET-FIELD-VALUE)               { return SPLTypes.GET_FIELD_VALUE; }
<YYINITIAL> (get-field-value-numeric|GET-FIELD-VALUE-NUMERIC) { return SPLTypes.GET_FIELD_VALUE_NUMERIC; }
<YYINITIAL> (get-function-code|GET-FUNCTION-CODE)           { return SPLTypes.GET_FUNCTION_CODE; }
<YYINITIAL> (get-module-code|GET-MODULE-CODE)               { return SPLTypes.GET_MODULE_CODE; }
<YYINITIAL> (get-param|GET-PARAM)                           { return SPLTypes.GET_PARAM; }
<YYINITIAL> (get-registry-enum-key|GET-REGISTRY-ENUM-KEY)   { return SPLTypes.GET_REGISTRY_ENUM_KEY; }
<YYINITIAL> (get-registry-enum-value|GET-REGISTRY-ENUM-VALUE) { return SPLTypes.GET_REGISTRY_ENUM_VALUE; }
<YYINITIAL> (get-registry-value|GET-REGISTRY-VALUE)         { return SPLTypes.GET_REGISTRY_VALUE; }
<YYINITIAL> (get-system-metrics|GET-SYSTEM-METRICS)         { return SPLTypes.GET_SYSTEM_METRICS; }
<YYINITIAL> (gid|GID)                                       { return SPLTypes.GID; }
<YYINITIAL> (grant-db-schema|GRANT-DB-SCHEMA)               { return SPLTypes.GRANT_DB_SCHEMA; }
<YYINITIAL> (hide-dockable-windows|HIDE-DOCKABLE-WINDOWS)   { return SPLTypes.HIDE_DOCKABLE_WINDOWS; }
<YYINITIAL> (idx|IDX)                                       { return SPLTypes.IDX; }
<YYINITIAL> (if-then-else|IF-THEN-ELSE)                     { return SPLTypes.IF_THEN_ELSE; }
<YYINITIAL> (io-count|IO-COUNT)                             { return SPLTypes.IO_COUNT; }
<YYINITIAL> (line-no|LINE-NO)                               { return SPLTypes.LINE_NO; }
<YYINITIAL> (local-no|local-yes|LOCAL-NO|LOCAL-YES)         { return SPLTypes.LOCAL_NO_AND_LOCAL_YES; }
<YYINITIAL> (login-id|LOGIN-ID)                             { return SPLTypes.LOGIN_ID; }
<YYINITIAL> (mail-add-line|MAIL-ADD-LINE)                   { return SPLTypes.MAIL_ADD_LINE; }
<YYINITIAL> (mail-attach|MAIL-ATTACH)                       { return SPLTypes.MAIL_ATTACH; }
<YYINITIAL> (mail-cancel|MAIL-CANCEL)                       { return SPLTypes.MAIL_CANCEL; }
<YYINITIAL> (mail-from-name|MAIL-FROM-NAME)                 { return SPLTypes.MAIL_FROM_NAME; }
<YYINITIAL> (mail-reply-to|MAIL-REPLY-TO)                   { return SPLTypes.MAIL_REPLY_TO; }
<YYINITIAL> (mail-send|MAIL-SEND)                           { return SPLTypes.MAIL_SEND; }
<YYINITIAL> (mail-start|MAIL-START)                         { return SPLTypes.MAIL_START; }
<YYINITIAL> (max-screen-columns|MAX-SCREEN-COLUMNS)         { return SPLTypes.MAX_SCREEN_COLUMNS; }
<YYINITIAL> (max-screen-rows|MAX-SCREEN-ROWS)               { return SPLTypes.MAX_SCREEN_ROWS; }
<YYINITIAL> (message-status|MESSAGE-STATUS)                 { return SPLTypes.MESSAGE_STATUS; }
<YYINITIAL> (mode-name|MODE-NAME)                           { return SPLTypes.MODE_NAME; }
<YYINITIAL> (mouse-column|MOUSE-COLUMN)                     { return SPLTypes.MOUSE_COLUMN; }
<YYINITIAL> (mouse-row|MOUSE-ROW)                           { return SPLTypes.MOUSE_ROW; }
<YYINITIAL> (node-name|NODE-NAME)                           { return SPLTypes.NODE_NAME; }
<YYINITIAL> (occurrence|OCCURRENCE)                         { return SPLTypes.OCCURRENCE; }
<YYINITIAL> (operating-system|OPERATING-SYSTEM)             { return SPLTypes.OPERATING_SYSTEM; }
<YYINITIAL> (page-no|PAGE-NO)                               { return SPLTypes.PAGE_NO; }
<YYINITIAL> (param-cnt|PARAM-CNT)                           { return SPLTypes.PARAM_CNT; }
<YYINITIAL> (pid|PID)                                       { return SPLTypes.PID; }
<YYINITIAL> (pronto-release|PRONTO-RELEASE)                 { return SPLTypes.PRONTO_RELEASE; }
<YYINITIAL> (prouser-flags|PROUSER-FLAGS)                   { return SPLTypes.PROUSER_FLAGS; }
<YYINITIAL> (refresh-quick-links|REFRESH-QUICK-LINKS)       { return SPLTypes.REFRESH_QUICK_LINKS; }
<YYINITIAL> (report-is-xml|REPORT-IS-XML)                   { return SPLTypes.REPORT_IS_XML; }
<YYINITIAL> (review-row|REVIEW-ROW)                         { return SPLTypes.REVIEW_ROW; }
<YYINITIAL> (revoke-db-schema|REVOKE-DB-SCHEMA)             { return SPLTypes.REVOKE_DB_SCHEMA; }
<YYINITIAL> (rgb-to-colour|RGB-TO-COLOUR)                   { return SPLTypes.RGB_TO_COLOUR; }
<YYINITIAL> (screen-mode|SCREEN-MODE)                       { return SPLTypes.SCREEN_MODE; }
<YYINITIAL> (search|SEARCH)                                 { return SPLTypes.SEARCH; }
<YYINITIAL> (search-mode|SEARCH-MODE)                       { return SPLTypes.SEARCH_MODE; }
<YYINITIAL> (set-app-user|SET-APP-USER)                     { return SPLTypes.SET_APP_USER; }
<YYINITIAL> (set-data-area-name|SET-DATA-AREA-NAME)         { return SPLTypes.SET_DATA_AREA_NAME; }
<YYINITIAL> (set-background-image|SET-BACKGROUND-IMAGE)     { return SPLTypes.SET_BACKGROUND_IMAGE; }
<YYINITIAL> (set-background-url|SET-BACKGROUND-URL)         { return SPLTypes.SET_BACKGROUND_URL; }
<YYINITIAL> (set-environment|SET-ENVIRONMENT|set-env|SET-ENV) { return SPLTypes.SET_ENVIRONMENT; }
<YYINITIAL> (set-function-code|SET-FUNCTION-CODE)           { return SPLTypes.SET_FUNCTION_CODE; }
<YYINITIAL> (set-help-context|SET-HELP-CONTEXT)             { return SPLTypes.SET_HELP_CONTEXT; }
<YYINITIAL> (set-keyboard-focus|SET-KEYBOARD-FOCUS)         { return SPLTypes.SET_KEYBOARD_FOCUS; }
<YYINITIAL> (set-module-code|SET-MODULE-CODE)               { return SPLTypes.SET_MODULE_CODE; }
<YYINITIAL> (set-registry-value|SET-REGISTRY-VALUE)         { return SPLTypes.SET_REGISTRY_VALUE; }
<YYINITIAL> (set-web-window|SET-WEB-WINDOW)                 { return SPLTypes.SET_WEB_WINDOW; }
<YYINITIAL> (sleep|SLEEP)                                   { return SPLTypes.SLEEP; }
<YYINITIAL> (spool-file-name|SPOOL-FILE-NAME)               { return SPLTypes.SPOOL_FILE_NAME; }
<YYINITIAL> (time-elapsed|TIME-ELAPSED)                     { return SPLTypes.TIME_ELAPSED; }
<YYINITIAL> (transaction-active|TRANSACTION-ACTIVE)         { return SPLTypes.TRANSACTION_ACTIVE; }
<YYINITIAL> (tty|TTY)                                       { return SPLTypes.TTY; }
<YYINITIAL> (uid|UID)                                       { return SPLTypes.UID; }
<YYINITIAL> (user-group|USER-GROUP)                         { return SPLTypes.USER_GROUP; }
<YYINITIAL> (valid-activation-key|VALID-ACTIVATION-KEY)     { return SPLTypes.VALID_ACTIVATION_KEY; }
<YYINITIAL> (wait-for-input|WAIT-FOR-INPUT)                 { return SPLTypes.WAIT_FOR_INPUT; }

//File handling functions
<YYINITIAL> (cd|CD)                                         { return SPLTypes.CD; }
<YYINITIAL> (cd-without-close-all|CD-WITHOUT-CLOSE-ALL)     { return SPLTypes.CD_WITHOUT_CLOSE_ALL; }
<YYINITIAL> (client-file-browse|CLIENT-FILE-BROWSE)         { return SPLTypes.CLIENT_FILE_BROWSE; }
<YYINITIAL> (dir|DIR)                                       { return SPLTypes.DIR; }
<YYINITIAL> (file-exists|FILE-EXISTS)                       { return SPLTypes.FILE_EXISTS; }
<YYINITIAL> (file-name|FILE-NAME)                           { return SPLTypes.FILE_NAME; }
<YYINITIAL> (file-owner|FILE-OWNER)                         { return SPLTypes.FILE_OWNER; }
<YYINITIAL> (file-size|FILE-SIZE)                           { return SPLTypes.FILE_SIZE; }
<YYINITIAL> (file-status|FILE-STATUS)                       { return SPLTypes.FILE_STATUS; }
<YYINITIAL> (file-version|FILE-VERSION)                     { return SPLTypes.FILE_VERSION; }
<YYINITIAL> (finish-dir-search|FINISH-DIR-SEARCH)           { return SPLTypes.FINISH_DIR_SEARCH; }
<YYINITIAL> (is-a-dir|IS-A-DIR)                             { return SPLTypes.IS_A_DIR; }
<YYINITIAL> (local-cd|LOCAL-CD)                             { return SPLTypes.LOCAL_CD; }
<YYINITIAL> (local-cd-without-close-all|LOCAL-CD-WITHOUT-CLOSE-ALL) { return SPLTypes.LOCAL_CD_WITHOUT_CLOSE_ALL; }
<YYINITIAL> (local-dir|LOCAL-DIR)                           { return SPLTypes.LOCAL_DIR; }
<YYINITIAL> (mkdir|MKDIR)                                   { return SPLTypes.MKDIR; }
<YYINITIAL> (modification-time|MODIFICATION-TIME)           { return SPLTypes.MODIFICATION_TIME; }
<YYINITIAL> (next-dir-entry|NEXT-DIR-ENTRY)                 { return SPLTypes.NEXT_DIR_ENTRY; }
<YYINITIAL> (rmdir|RMDIR)                                   { return SPLTypes.RMDIR; }
<YYINITIAL> (start-dir-search|START-DIR-SEARCH|next-dir-entry|finish-dir-search|NEXT-DIR-ENTRY|FINISH-DIR-SEARCH) { return SPLTypes.START_DIR_SEARCH; }

//String functions
<YYINITIAL> (ascii-char|ASCII-CHAR)                         { return SPLTypes.ASCII_CHAR; }
<YYINITIAL> (ascii-num|ascii|asc|ASCII-NUM|ASCII|ASC)       { return SPLTypes.ASCII_NUM; }
<YYINITIAL> (base64-to-blob|BASE64-TO-BLOB)                 { return SPLTypes.BASE64TOBLOB; }
<YYINITIAL> (blob-append|BLOB-APPEND)                       { return SPLTypes.BLOBAPPEND; }
<YYINITIAL> (blob-append-base64|BLOB-APPEND-BASE64)         { return SPLTypes.BLOBAPPENDBASE64; }
<YYINITIAL> (blob-section|BLOB-SECTION)                     { return SPLTypes.BLOBSECTION; }
<YYINITIAL> (blob-to-base64|BLOB-TO-BASE64)                 { return SPLTypes.BLOBTOBASE64; }
<YYINITIAL> (concat|CONCAT)                                 { return SPLTypes.CONCAT; }
<YYINITIAL> (format-picture|FORMAT-PICTURE)                 { return SPLTypes.FORMATPICTURE; }
<YYINITIAL> (free-blob|FREE-BLOB)                           { return SPLTypes.FREEBLOB; }
<YYINITIAL> (fstr|FSTR)                                     { return SPLTypes.FSTR; }
<YYINITIAL> (left-justify|LEFT-JUSTIFY)                     { return SPLTypes.LEFTJUSTIFY; }
<YYINITIAL> (lowercase|LOWERCASE)                           { return SPLTypes.LOWERCASE; }
<YYINITIAL> (ltrim|LTRIM)                                   { return SPLTypes.LTRIM; }
<YYINITIAL> (max-alpha-value|MAX-ALPHA-VALUE)               { return SPLTypes.MAXALPHAVALUE; }
<YYINITIAL> (num|NUM)                                       { return SPLTypes.NUM; }
<YYINITIAL> (pattern|PATTERN)                               { return SPLTypes.PATTERN; }
<YYINITIAL> (param-text|PARAM-TEXT|paramtext|PARAMTEXT)     { return SPLTypes.PARAMTEXT; }
<YYINITIAL> (read-blob-from-file|READ-BLOB-FROM-FILE)       { return SPLTypes.READBLOBFROMFILE; }
<YYINITIAL> (reserved|RESERVED)                             { return SPLTypes.RESERVED; }
<YYINITIAL> (right-justify|RIGHT-JUSTIFY)                   { return SPLTypes.RIGHTJUSTIFY; }
<YYINITIAL> (rtrim|RTRIM)                                   { return SPLTypes.RTRIM; }
<YYINITIAL> (size-of|SIZE-OF)                               { return SPLTypes.SIZEOF; }
<YYINITIAL> (sql-substring|SQL-SUBSTRING)                   { return SPLTypes.SQLSUBSTRING; }
<YYINITIAL> (str|STR)                                       { return SPLTypes.STR_FUNC; }
<YYINITIAL> (str-concat|STR-CONCAT|strconcat|STRCONCAT)     { return SPLTypes.STRCONCAT; }
<YYINITIAL> (str-len|STR-LEN)                               { return SPLTypes.STRLEN; }
<YYINITIAL> (sub-string|SUB-STRING)                         { return SPLTypes.SUBSTRING; }
<YYINITIAL> (substring-utf8|SUBSTRING-UTF8)                 { return SPLTypes.SUBSTRINGUTF8; }
<YYINITIAL> (uppercase|UPPERCASE)                           { return SPLTypes.UPPERCASE; }
<YYINITIAL> (valid-number|VALID-NUMBER)                     { return SPLTypes.VALIDNUMBER; }
<YYINITIAL> (write-blob-to-file|WRITE-BLOB-TO-FILE)         { return SPLTypes.WRITEBLOBTOFILE; }
<YYINITIAL> (zstr|ZSTR)                                     { return SPLTypes.ZSTR; }

//SCREEN DEFINITION
<YYINITIAL> (screen|SCREEN)                                 { return SPLTypes.SCREEN; }
<YYINITIAL> (end-screen|END-SCREEN|endscreen|ENDSCREEN)     { return SPLTypes.SCREEN_END; }
<YYINITIAL> (responsive|RESPONSIVE)                         { return SPLTypes.SCREEN_RESPONSIVE; }


<YYINITIAL> (primary|PRIMARY)                               { return SPLTypes.SCREEN_PRIMARY; }
<YYINITIAL> (on index|ON INDEX)                             { return SPLTypes.SCREEN_ON; }
<YYINITIAL> (same|different|SAME|DIFFERENT)                 { return SPLTypes.SCREEN_SAME_DIFFERENT; }
<YYINITIAL> (select|SELECT)                                 { return SPLTypes.SCREEN_SELECT; }
<YYINITIAL> (quick-link|QUICK-LINK)                         { return SPLTypes.SCREEN_QUICK_LINK; }
<YYINITIAL> (review colour|REVIEW COLOUR)                   { return SPLTypes.SCREEN_REVIEW; }
<YYINITIAL> (allowed|ALLOWED)                               { return SPLTypes.SCREEN_ALLOWED; }
<YYINITIAL> (no-prompt-for-search|prompt-for-search)        { return SPLTypes.SCREEN_PROMPT_FOR_SEARCH; }
<YYINITIAL> (find-for-currency|FIND-FOR-CURRENCY)           { return SPLTypes.SCREEN_FIND_FOR_CURRENCY; }
<YYINITIAL> (data-grid|DATA-GRID)                           { return SPLTypes.SCREEN_DATA_GRID; }
<YYINITIAL> (review occurs|REVIEW OCCURS)                   { return SPLTypes.SCREEN_REVIEW_OCCURS; }
<YYINITIAL> (review-from-start|review-from-current|review-from-end|review-bottom-to-top) { return SPLTypes.SCREEN_REVIEW_FROM_START; }
<YYINITIAL> (no-review-row-separators)                      { return SPLTypes.SCREEN_NO_REVIEW_ROW_SEPARATORS; }
<YYINITIAL> (no-review-column-separators)                   { return SPLTypes.SCREEN_NO_REVIEW_COLUMN_SEPARATORS; }
<YYINITIAL> (no-review-separators)                          { return SPLTypes.SCREEN_NO_REVIEW_SEPARATORS; }
<YYINITIAL> (start-on-current-record|START-ON-CURRENT-RECORD) { return SPLTypes.SCREEN_START_ON_CURRENT_RECORD; }
<YYINITIAL> (leave-parent-screen|LEAVE-PARENT-SCREEN)       { return SPLTypes.SCREEN_LEAVE_PARENT_SCREEN; }
<YYINITIAL> (when|WHEN)                                     { return SPLTypes.WHEN; }
<YYINITIAL> (position-on-ok|no-ok-cancel)                   { return SPLTypes.SCREEN_POSITION_ON_OK; }
<YYINITIAL> (stay-in-correct|STAY-IN-CORRECT)               { return SPLTypes.SCREEN_STAY_IN_CORRECT; }
<YYINITIAL> (form-entry|FORM-ENTRY)                         { return SPLTypes.SCREEN_FORM_ENTRY; }

<YYINITIAL> (help-context|HELP-CONTEXT)                     { return SPLTypes.HELP_CONTEXT; }



//proceedure
<YYINITIAL> (procedure|PROCEDURE)                           { return SPLTypes.PROCEDURE; }
<YYINITIAL> (end-procedure|END-PROCEDURE|endprocedure|ENDPROCEDURE) { return SPLTypes.PROCEDURE_END; }
<YYINITIAL> (auto-transaction|AUTO-TRANSACTION|autotransaction|AUTOTRANSACTION) { return SPLTypes.AUTO_TRANSACTION; }

//api
<YYINITIAL> (api|API)                                       { return SPLTypes.API; }
<YYINITIAL> (end-api|END-API|endapi|ENDAPI)                 { return SPLTypes.API_END; }

//field & local field
<YYINITIAL> (field|FIELD|fields|FIELDS)                     { return SPLTypes.FIELD; }
<YYINITIAL> (local|LOCAL)                                   { return SPLTypes.LOCAL; }

//menu
<YYINITIAL> (menu|MENU)                                     { return SPLTypes.MENU; }
<YYINITIAL> (end-menu|endmenu|END-MENU|ENDMENU)             { return SPLTypes.MENU_END; }
<YYINITIAL> (treemenu|TREEMENU|tree-menu|TREE-MENU)         { return SPLTypes.TREEMENU; }
<YYINITIAL> (nohide|NOHIDE|no-hide|NO-HIDE)                 { return SPLTypes.NOHIDE; }
<YYINITIAL> (button-width|BUTTON-WIDTH|buttonwidth|BUTTONWIDTH) { return SPLTypes.MENU_BUTTON_WIDTH; }

//mode
<YYINITIAL> (mode|MODE)                                     { return SPLTypes.MODE; }
<YYINITIAL> (help|HELP)                                     { return SPLTypes.HELP; }
<YYINITIAL> (security|SECURITY)                             { return SPLTypes.SECURITY; }
<YYINITIAL> (entry|correct|remove|duplicate|entry-once|find|next-scr|prev-scr) { return SPLTypes.MODEENTRY; }
<YYINITIAL> (process|PROCESS)                               { return SPLTypes.PROCESS; }
<YYINITIAL> (currency|CURRENCY)                             { return SPLTypes.MODECURRENCY; }
<YYINITIAL> (lock|LOCK)                                     { return SPLTypes.LOCK; }
<YYINITIAL> (perform|PERFORM)                               { return SPLTypes.PERFORM; }
<YYINITIAL> (always-show|ALWAYS-SHOW)                       { return SPLTypes.ALWAYS_SHOW; }
<YYINITIAL> (always-add|ALWAYS-ADD)                         { return SPLTypes.ALWAYS_ADD; }

//object
<YYINITIAL> (object|OBJECT)                                 { return SPLTypes.OBJECT; }


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
<YYINITIAL> {IDENTIFIER}                                    { return SPLTypes.IDENTIFIER; }
[^]                                                         { return TokenType.BAD_CHARACTER; }