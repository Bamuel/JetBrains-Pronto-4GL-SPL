package com.bamuel.spllang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.bamuel.spllang.psi.SPLTypes;
import com.intellij.psi.TokenType;

%%

%class SPLLexer
%implements FlexLexer
%unicode
%ignorecase
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

//FUNCTIONS
OLE_FUNCTIONS=(ole-addref|ole-advise-event|ole-bulk-put|ole-call-interactive-method|ole-call-method|ole-create-control|ole-create-instance|ole-enum-next|ole-enum-reset|ole-error-description|ole-get-active-object|ole-get-dispatch-id|ole-get-event|ole-get-property|ole-put-property|ole-put-property-byref|ole-query-interface|ole-release|ole-status|ole-unadvise-all|ole-unadvise-event)
SECURITY_FUNCTIONS=(crc32|decrypt|encrypt|security-level|sign-data|verify-signed-data)
XML_FUNCTIONS=(xml-add-child-node|xml-add-child-node-blob|xml-add-child-node-clob|xml-add-child-node-no-quotes|xml-add-child-node-number|xml-add-child-node-text|xml-add-node-attribute|xml-add-ns|xml-child-node-blob|xml-child-node-clob|xml-child-node-text|xml-close-document|xml-copy-node-handle|xml-delete-node|xml-delete-node-attribute|xml-document-is-json|xml-free-node-handle|xml-get-child-by-name|xml-get-doc-encoding|xml-get-first-attribute-name|xml-get-first-child-node|xml-get-last-child-node|xml-get-next-attribute-name|xml-get-next-node|xml-get-node-attribute|xml-get-ns-prefix-url|xml-get-prev-node|xml-get-root-node|xml-modify-node-attribute|xml-modify-node-text|xml-new-document|xml-next-element-sibling|xml-node-blob|xml-node-clob|xml-node-name|xml-node-ns-prefix|xml-node-ns-url|xml-node-string|xml-node-text|xml-node-type|xml-parse-file|xml-parse-text|xml-prev-element-sibling|xml-save-as-file|xml-save-as-file-ex|xml-save-as-text|xml-save-as-text-ex|xml-set-json-array|xml-validate-doc)

PREDEFINED=(true|false|yes|no|zero|zero[-_]?date|zero[-_]?time|zero[-_]?date[-_]?time|space|spaces)

%%

<YYINITIAL> {WHITE_SPACE}+                                  { return TokenType.WHITE_SPACE; }

<YYINITIAL> {COMMENT}                                       { return SPLTypes.COMMENT; }
<YYINITIAL> {BLOCK_COMMENT}                                 { return SPLTypes.BLOCK_COMMENT; }

<YYINITIAL> {OLE_FUNCTIONS}                                 { return SPLTypes.FUNCTION_DECLARATION; }
<YYINITIAL> {SECURITY_FUNCTIONS}                            { return SPLTypes.FUNCTION_DECLARATION; }
<YYINITIAL> {XML_FUNCTIONS}                                 { return SPLTypes.FUNCTION_DECLARATION; }

<YYINITIAL> {SINGLEQUOTE}                                   { return SPLTypes.STRING; }
<YYINITIAL> {DOUBLEQUOTE}                                   { return SPLTypes.STRING; }
<YYINITIAL> {NUMBER}                                        { return SPLTypes.NUMBER; }
<YYINITIAL> {DATE}                                          { return SPLTypes.DATE; }
<YYINITIAL> {TIME}                                          { return SPLTypes.TIME; }


<YYINITIAL> {PREDEFINED}                                    { return SPLTypes.PREDEFINED; }

//Preprocessor
<YYINITIAL> (#include)                                      { return SPLTypes.PREPROCESSOR_INCLUDE; }
<YYINITIAL> (#define)                                       { return SPLTypes.PREPROCESSOR_DEFINE; }
<YYINITIAL> (#undef)                                        { return SPLTypes.PREPROCESSOR_UNDEF; }
<YYINITIAL> (#ifdef)                                        { return SPLTypes.PREPROCESSOR_IFDEF; }
<YYINITIAL> (#ifndef)                                       { return SPLTypes.PREPROCESSOR_IFNDEF; }
<YYINITIAL> (#if)                                           { return SPLTypes.PREPROCESSOR_IF; }
<YYINITIAL> (#else)                                         { return SPLTypes.PREPROCESSOR_ELSE; }
<YYINITIAL> (#endif)                                        { return SPLTypes.PREPROCESSOR_ENDIF; }

//Declaration functions
<YYINITIAL> (screen)                                        { return SPLTypes.SCREEN; }
<YYINITIAL> (end[-_]?screen)                                { return SPLTypes.SCREEN_END; }
<YYINITIAL> (procedure)                                     { return SPLTypes.PROCEDURE; }
<YYINITIAL> (end[-_]?procedure)                             { return SPLTypes.PROCEDURE_END; }
<YYINITIAL> (api)                                           { return SPLTypes.API; }
<YYINITIAL> (end[-_]?api)                                   { return SPLTypes.API_END; }
<YYINITIAL> (field|fields)                                  { return SPLTypes.FIELD; }
<YYINITIAL> (local)                                         { return SPLTypes.LOCAL; }
<YYINITIAL> (menu)                                          { return SPLTypes.MENU; }
<YYINITIAL> (end[-_]?menu)                                  { return SPLTypes.MENU_END; }
<YYINITIAL> (mode)                                          { return SPLTypes.MODE; }
<YYINITIAL> (object)                                        { return SPLTypes.OBJECT; }

//Clauses
<YYINITIAL> (title)                                         { return SPLTypes.TITLE; }
<YYINITIAL> (default)                                       { return SPLTypes.DEFAULT; }
<YYINITIAL> (window)                                        { return SPLTypes.WINDOW; }
<YYINITIAL> (prompt)                                        { return SPLTypes.PROMPT; }
<YYINITIAL> (detail)                                        { return SPLTypes.DETAIL; }
<YYINITIAL> (icon)                                          { return SPLTypes.ICON; }
<YYINITIAL> (tag)                                           { return SPLTypes.TAG; }
<YYINITIAL> (help[-_]?context)                              { return SPLTypes.HELP_CONTEXT; }
<YYINITIAL> (optional)                                      { return SPLTypes.OPTIONAL; }
<YYINITIAL> (bell)                                          { return SPLTypes.BELL; }
<YYINITIAL> (no[-_]?bell)                                   { return SPLTypes.NOBELL; }
<YYINITIAL> (colour|color)                                  { return SPLTypes.COLOUR; }
<YYINITIAL> (type)                                          { return SPLTypes.TYPE; }
<YYINITIAL> (pic|picture)                                   { return SPLTypes.PIC; }
<YYINITIAL> (occurs)                                        { return SPLTypes.OCCURS; }
<YYINITIAL> (like)                                          { return SPLTypes.LIKE; }
<YYINITIAL> (justify)                                       { return SPLTypes.JUSTIFY; }
<YYINITIAL> (use[-_]?name[-_]?in[-_]?db)                    { return SPLTypes.USE_NAME_IN_DB; }
<YYINITIAL> (db[-_]?column[-_]?name)                        { return SPLTypes.DB_COLUMN_NAME; }
<YYINITIAL> (drill[-_]?back)                                { return SPLTypes.DRILL_BACK; }
<YYINITIAL> (relative to|RELATIVE TO)                       { return SPLTypes.RELATIVE_TO; }
<YYINITIAL> (window[-_]?position)                           { return SPLTypes.WINDOW_POSITION; }
<YYINITIAL> (parameters|parameter)                          { return SPLTypes.PARAMETERS; }
<YYINITIAL> (returning)                                     { return SPLTypes.RETURNING; }
<YYINITIAL> (stop[-_]?exit[-_]?all[-_]?key)                 { return SPLTypes.STOP_EXIT_ALL_KEY; }
<YYINITIAL> (export|user[-_]?trigger)                       { return SPLTypes.EXPORT; }
<YYINITIAL> (can[-_]?override|no[-_]?override)              { return SPLTypes.CAN_OVERRIDE; }
<YYINITIAL> (before)                                        { return SPLTypes.BEFORE; }
<YYINITIAL> (after)                                         { return SPLTypes.AFTER; }
<YYINITIAL> (separator)                                     { return SPLTypes.SEPARATOR; }
<YYINITIAL> (file)                                          { return SPLTypes.FILE; }
<YYINITIAL> (record)                                        { return SPLTypes.RECORD; }
<YYINITIAL> (end[-_]?record)                                { return SPLTypes.RECORD_END; }
<YYINITIAL> (key)                                           { return SPLTypes.KEY; }
<YYINITIAL> (desc|descending)                               { return SPLTypes.DESC; }
<YYINITIAL> (unique)                                        { return SPLTypes.UNIQUE; }
<YYINITIAL> (db[-_]?index[-_]?only)                         { return SPLTypes.DB_INDEX_ONLY; }
<YYINITIAL> (compress)                                      { return SPLTypes.COMPRESS; }
<YYINITIAL> (no[-_]?joins)                                  { return SPLTypes.NOJOINS; }
<YYINITIAL> (no[-_]?rowid)                                  { return SPLTypes.NOROWID; }
<YYINITIAL> (column[-_]?title)                              { return SPLTypes.COLUMNTITLE; }
<YYINITIAL> (no[-_]?log)                                    { return SPLTypes.NOLOG; }
<YYINITIAL> (no[-_]?title)                                  { return SPLTypes.NOTITLE; }
<YYINITIAL> (multi[-_]?line)                                { return SPLTypes.MULTILINE; }
<YYINITIAL> (rows)                                          { return SPLTypes.ROWS; }
<YYINITIAL> (columns)                                       { return SPLTypes.COLUMNS; }
<YYINITIAL> (blank[-_]?when[-_]?zero)                       { return SPLTypes.BLANK_WHEN_ZERO; }
<YYINITIAL> (blank[-_]?decimals[-_]?when[-_]?zero)          { return SPLTypes.BLANK_DECIMALS_WHEN_ZERO; }
<YYINITIAL> (allow)                                         { return SPLTypes.ALLOW; }
<YYINITIAL> (disallow)                                      { return SPLTypes.DISALLOW; }
<YYINITIAL> (auto[-_]?return)                               { return SPLTypes.AUTO_RETURN; }
<YYINITIAL> (time[-_]?out)                                  { return SPLTypes.TIMEOUT; }
<YYINITIAL> (edit|edits)                                    { return SPLTypes.EDIT; }
<YYINITIAL> (fill)                                          { return SPLTypes.FILL; }
<YYINITIAL> (lookup)                                        { return SPLTypes.LOOKUP; }
<YYINITIAL> (left)                                          { return SPLTypes.LEFT; }
<YYINITIAL> (centre)                                        { return SPLTypes.CENTRE; }
<YYINITIAL> (right)                                         { return SPLTypes.RIGHT; }
<YYINITIAL> (centre[-_]?coord)                              { return SPLTypes.CENTRE_COORD; }
<YYINITIAL> (right[-_]?coord)                               { return SPLTypes.RIGHT_COORD; }
<YYINITIAL> (bold)                                          { return SPLTypes.BOLD; }
<YYINITIAL> (dim)                                           { return SPLTypes.DIM; }
<YYINITIAL> (flashing)                                      { return SPLTypes.FLASHING; }
<YYINITIAL> (inverse)                                       { return SPLTypes.INVERSE; }
<YYINITIAL> (italic)                                        { return SPLTypes.ITALIC; }
<YYINITIAL> (underlined)                                    { return SPLTypes.UNDERLINED; }
<YYINITIAL> (foreground)                                    { return SPLTypes.FOREGROUND; }
<YYINITIAL> (background)                                    { return SPLTypes.BACKGROUND; }
<YYINITIAL> (blank)                                         { return SPLTypes.BLANK; }
<YYINITIAL> (error)                                         { return SPLTypes.ERROR; }
<YYINITIAL> (end[-_]?on)                                    { return SPLTypes.END_ON; }
<YYINITIAL> (up[-_]?arrow|down[-_]?arrow|left[-_]?arrow|right[-_]?arrow|help[-_]?key) { return SPLTypes.FUNCTION_KEY; }
<YYINITIAL> (help[-_]?screen)                               { return SPLTypes.HELP_SCREEN; }
<YYINITIAL> (showing)                                       { return SPLTypes.SHOWING; }
<YYINITIAL> (using)                                         { return SPLTypes.USING; }
<YYINITIAL> (show[-_]?value)                                { return SPLTypes.SHOW_VALUE; }
<YYINITIAL> (no[-_]?clear)                                  { return SPLTypes.NO_CLEAR; }
<YYINITIAL> (suppress)                                      { return SPLTypes.SUPPRESS; }
<YYINITIAL> (conditional[-_]?suppress)                      { return SPLTypes.CONDITIONAL_SUPPRESS; }
<YYINITIAL> (read[-_]?only)                                 { return SPLTypes.READ_ONLY; }
<YYINITIAL> (fixed[-_]?width[-_]?font)                      { return SPLTypes.FIXED_WIDTH_FONT; }
<YYINITIAL> (before[-_]?accept)                             { return SPLTypes.BEFORE_ACCEPT; }
<YYINITIAL> (end[-_]?before[-_]?accept)                     { return SPLTypes.END_BEFORE_ACCEPT; }
<YYINITIAL> (validations|validation)                        { return SPLTypes.VALIDATIONS; }
<YYINITIAL> (end[-_]?validations|end[-_]?validation)        { return SPLTypes.END_VALIDATIONS; }
<YYINITIAL> (use[-_]?validate[-_]?trigger)                  { return SPLTypes.USE_VALIDATE_TRIGGER; }
<YYINITIAL> (scale)                                         { return SPLTypes.SCALE; }
<YYINITIAL> (no[-_]?warning)                                { return SPLTypes.NO_WARNING; }
<YYINITIAL> (change)                                        { return SPLTypes.CHANGE; }
<YYINITIAL> (leaving)                                       { return SPLTypes.LEAVING; }
<YYINITIAL> (free[-_]?locks)                                { return SPLTypes.FREE_LOCKS; }
<YYINITIAL> (double)                                        { return SPLTypes.DOUBLE; }
<YYINITIAL> (no[-_]?cross)                                  { return SPLTypes.NO_CROSS; }
<YYINITIAL> (sunken)                                        { return SPLTypes.SUNKEN; }
<YYINITIAL> (absolute[-_]?coordinates)                      { return SPLTypes.ABSOLUTE_COORDINATES; }
<YYINITIAL> (max[-_]?parameters)                            { return SPLTypes.MAX_PARAMETERS; }
<YYINITIAL> (home)                                          { return SPLTypes.HOME; }
<YYINITIAL> (batch)                                         { return SPLTypes.BATCH; }
<YYINITIAL> (leave[-_]?files[-_]?open)                      { return SPLTypes.LEAVE_FILES_OPEN; }
<YYINITIAL> (external)                                      { return SPLTypes.EXTERNAL; }
<YYINITIAL> (no[-_]?wait)                                   { return SPLTypes.BACKGROUNDNOWAITWAIT; }
<YYINITIAL> (http[-_]?headers)                              { return SPLTypes.HTTP_HEADERS; }
<YYINITIAL> (http[-_]?body)                                 { return SPLTypes.HTTP_BODY; }
<YYINITIAL> (http[-_]?method)                               { return SPLTypes.HTTP_METHOD; }
<YYINITIAL> (http[-_]?proxy)                                { return SPLTypes.HTTP_PROXY; }
<YYINITIAL> (http[-_]?auth[-_]?any)                         { return SPLTypes.HTTP_AUTH_ANY; }
<YYINITIAL> (ca[-_]?certificate)                            { return SPLTypes.CA_CERTIFICATE; }
<YYINITIAL> (ssl[-_]?certificate)                           { return SPLTypes.SSL_CERTIFICATE; }
<YYINITIAL> (ssl[-_]?key)                                   { return SPLTypes.SSL_KEY; }
<YYINITIAL> (ssl[-_]?key[-_]?password)                      { return SPLTypes.SSL_KEY_PASSWORD; }
<YYINITIAL> (allow[-_]?redirection)                         { return SPLTypes.ALLOW_REDIRECTION; }
<YYINITIAL> (sftp[-_]?command)                              { return SPLTypes.SFTP_COMMAND; }
<YYINITIAL> (sftp[-_]?post[-_]?command)                     { return SPLTypes.SFTP_POST_COMMAND; }
<YYINITIAL> (sftp[-_]?create[-_]?dirs)                      { return SPLTypes.SFTP_CREATE_DIRS; }
<YYINITIAL> (url[-_]?user)                                  { return SPLTypes.URL_USER; }
<YYINITIAL> (url[-_]?login[-_]?options)                     { return SPLTypes.URL_LOGIN_OPTIONS; }
<YYINITIAL> (values)                                        { return SPLTypes.VALUES; }
<YYINITIAL> (right[-_]?coordinate)                          { return SPLTypes.RIGHT_COORDINATE; }
<YYINITIAL> (before[-_]?check[-_]?box)                      { return SPLTypes.BEFORE_CHECK_BOX; }
<YYINITIAL> (end[-_]?before[-_]?check[-_]?box)              { return SPLTypes.END_BEFORE_CHECK_BOX; }
<YYINITIAL> (message[-_]?buttons)                           { return SPLTypes.MESSAGEBOX_BUTTONS; }
<YYINITIAL> (data)                                          { return SPLTypes.DATA; }
<YYINITIAL> (no[-_]?message)                                { return SPLTypes.NO_MESSAGE; }
<YYINITIAL> (no[-_]?refresh)                                { return SPLTypes.NO_REFRESH; }
<YYINITIAL> (no[-_]?xterm)                                  { return SPLTypes.NO_XTERM; }
<YYINITIAL> (auto)                                          { return SPLTypes.AUTO; }
<YYINITIAL> (no[-_]?prompt)                                 { return SPLTypes.NO_PROMPT; }
<YYINITIAL> (no[-_]?window)                                 { return SPLTypes.NO_WINDOW; }
<YYINITIAL> (confirmed)                                     { return SPLTypes.CONFIRMED; }
<YYINITIAL> (not[-_]?confirmed)                             { return SPLTypes.NOT_CONFIRMED; }
<YYINITIAL> (col)                                           { return SPLTypes.COL; }
<YYINITIAL> (bitmap)                                        { return SPLTypes.BITMAP; }
<YYINITIAL> (help[-_]?is)                                   { return SPLTypes.HELP_IS; }
<YYINITIAL> (same)                                          { return SPLTypes.SAME; }
<YYINITIAL> (once)                                          { return SPLTypes.ONCE; }
<YYINITIAL> (sub[-_]?screen)                                { return SPLTypes.SUB_SCREEN; }
<YYINITIAL> (initial[-_]?mode)                              { return SPLTypes.INITIAL_MODE; }
<YYINITIAL> (next)                                          { return SPLTypes.NEXT; }
<YYINITIAL> (no[-_]?section)                                { return SPLTypes.NO_SECTION; }
<YYINITIAL> (dynamic)                                       { return SPLTypes.DYNAMIC; }
<YYINITIAL> (concat[-_]?title)                              { return SPLTypes.CONCAT_TITLE; }
<YYINITIAL> (width)                                         { return SPLTypes.WIDTH; }
<YYINITIAL> (previous)                                      { return SPLTypes.PREVIOUS; }
<YYINITIAL> (finish)                                        { return SPLTypes.FINISH; }
<YYINITIAL> (all)                                           { return SPLTypes.ALL; }
<YYINITIAL> (where)                                         { return SPLTypes.WHERE; }
<YYINITIAL> (wrap)                                          { return SPLTypes.WRAP; }
<YYINITIAL> (vertical)                                      { return SPLTypes.VERTICAL; }
<YYINITIAL> (down)                                          { return SPLTypes.DOWN; }
<YYINITIAL> (step)                                          { return SPLTypes.STEP; }
<YYINITIAL> (responsive)                                    { return SPLTypes.SCREEN_RESPONSIVE; }
<YYINITIAL> (primary)                                       { return SPLTypes.SCREEN_PRIMARY; }
<YYINITIAL> (index)                                         { return SPLTypes.INDEX; }
<YYINITIAL> (same|different)                                { return SPLTypes.SCREEN_SAME_DIFFERENT; }
<YYINITIAL> (quick[-_]?link)                                { return SPLTypes.SCREEN_QUICK_LINK; }
<YYINITIAL> (review colour)                                 { return SPLTypes.SCREEN_REVIEW; }
<YYINITIAL> (allowed)                                       { return SPLTypes.SCREEN_ALLOWED; }
<YYINITIAL> (no[-_]?prompt[-_]?for[-_]?search)              { return SPLTypes.SCREEN_PROMPT_FOR_SEARCH; }
<YYINITIAL> (find[-_]?for[-_]?currency)                     { return SPLTypes.SCREEN_FIND_FOR_CURRENCY; }
<YYINITIAL> (data[-_]?grid)                                 { return SPLTypes.SCREEN_DATA_GRID; }
<YYINITIAL> (review occurs)                                 { return SPLTypes.SCREEN_REVIEW_OCCURS; }
<YYINITIAL> (review[-_]?from[-_]?start|review[-_]?from[-_]?current|review[-_]?from[-_]?end|review[-_]?bottom[-_]?to[-_]?top) { return SPLTypes.SCREEN_REVIEW_FROM_START; }
<YYINITIAL> (no[-_]?review[-_]?row[-_]?separators)          { return SPLTypes.SCREEN_NO_REVIEW_ROW_SEPARATORS; }
<YYINITIAL> (no[-_]?review[-_]?column[-_]?separators)       { return SPLTypes.SCREEN_NO_REVIEW_COLUMN_SEPARATORS; }
<YYINITIAL> (no[-_]?review[-_]?separators)                  { return SPLTypes.SCREEN_NO_REVIEW_SEPARATORS; }
<YYINITIAL> (start[-_]?on[-_]?current[-_]?record)           { return SPLTypes.SCREEN_START_ON_CURRENT_RECORD; }
<YYINITIAL> (leave[-_]?parent[-_]?screen)                   { return SPLTypes.SCREEN_LEAVE_PARENT_SCREEN; }
<YYINITIAL> (when)                                          { return SPLTypes.WHEN; }
<YYINITIAL> (position[-_]?on[-_]?ok|no[-_]?ok[-_]?cancel)   { return SPLTypes.SCREEN_POSITION_ON_OK; }
<YYINITIAL> (stay[-_]?in[-_]?correct)                       { return SPLTypes.SCREEN_STAY_IN_CORRECT; }
<YYINITIAL> (form[-_]?entry)                                { return SPLTypes.SCREEN_FORM_ENTRY; }
<YYINITIAL> (auto[-_]?transaction)                          { return SPLTypes.AUTO_TRANSACTION; }
<YYINITIAL> (tree[-_]?menu)                                 { return SPLTypes.TREEMENU; }
<YYINITIAL> (no[-_]?hide)                                   { return SPLTypes.NOHIDE; }
<YYINITIAL> (button[-_]?width)                              { return SPLTypes.MENU_BUTTON_WIDTH; }
<YYINITIAL> (help)                                          { return SPLTypes.HELP; }
<YYINITIAL> (security)                                      { return SPLTypes.SECURITY; }
<YYINITIAL> (process)                                       { return SPLTypes.PROCESS; }
<YYINITIAL> (currency)                                      { return SPLTypes.MODECURRENCY; }
<YYINITIAL> (lock)                                          { return SPLTypes.LOCK; }
<YYINITIAL> (perform)                                       { return SPLTypes.PERFORM; }
<YYINITIAL> (always[-_]?show)                               { return SPLTypes.ALWAYS_SHOW; }
<YYINITIAL> (always[-_]?add)                                { return SPLTypes.ALWAYS_ADD; }
<YYINITIAL> (from)                                          { return SPLTypes.FROM; }
<YYINITIAL> (first)                                         { return SPLTypes.FIRST; }
<YYINITIAL> (last)                                          { return SPLTypes.LAST; }
<YYINITIAL> (current)                                       { return SPLTypes.CURRENT; }
<YYINITIAL> (then)                                          { return SPLTypes.THEN; }
<YYINITIAL> (else)                                          { return SPLTypes.ELSE; }
<YYINITIAL> (else[-_]?if)                                   { return SPLTypes.ELSEIF; }
<YYINITIAL> (no[-_]?main)                                   { return SPLTypes.NO_MAIN; }
<YYINITIAL> (wait[-_]?with[-_]?timeout)                     { return SPLTypes.WAIT_WITH_TIMEOUT; }
<YYINITIAL> (create)                                        { return SPLTypes.CREATE; }
<YYINITIAL> (truncate)                                      { return SPLTypes.TRUNCATE; }
<YYINITIAL> (permanent|temporary)                           { return SPLTypes.PERMANENTTEMPORARY; }
<YYINITIAL> (private)                                       { return SPLTypes.PRIVATE; }
<YYINITIAL> (no[-_]?triggers)                               { return SPLTypes.NO_TRIGGERS; }
<YYINITIAL> (index[-_]?on[-_]?close)                        { return SPLTypes.INDEX_ON_CLOSE; }
<YYINITIAL> (log)                                           { return SPLTypes.LOG; }
<YYINITIAL> (bitmap[-_]?pushed)                             { return SPLTypes.BITMAP_PUSHED; }
<YYINITIAL> (bitmap[-_]?focus)                              { return SPLTypes.BITMAP_FOCUS; }
<YYINITIAL> (bitmap[-_]?hover)                              { return SPLTypes.BITMAP_HOVER; }
<YYINITIAL> (bitmap[-_]?greyed)                             { return SPLTypes.BITMAP_GREYED; }
<YYINITIAL> (no[-_]?aspect[-_]?ratio)                       { return SPLTypes.NO_ASPECT_RATIO; }
<YYINITIAL> (button[-_]?style)                              { return SPLTypes.BUTTON_STYLE; }
<YYINITIAL> (default[-_]?button)                            { return SPLTypes.DEFAULT_BUTTON; }
<YYINITIAL> (text[-_]?position)                             { return SPLTypes.TEXT_POSITION; }
<YYINITIAL> (hot[-_]?key)                                   { return SPLTypes.HOTKEY; }
<YYINITIAL> (hidden)                                        { return SPLTypes.HIDDEN; }
<YYINITIAL> (no[-_]?theme)                                  { return SPLTypes.NO_THEME; }
<YYINITIAL> (no[-_]?header)                                 { return SPLTypes.NO_HEADER; }
<YYINITIAL> (no[-_]?footer)                                 { return SPLTypes.NO_FOOTER; }
<YYINITIAL> (on)                                            { return SPLTypes.ON; }
<YYINITIAL> (column)                                        { return SPLTypes.COLUMN; }
<YYINITIAL> (subscript)                                     { return SPLTypes.SUBSCRIPT; }
<YYINITIAL> (superscript)                                   { return SPLTypes.SUPERSCRIPT; }
<YYINITIAL> (font)                                          { return SPLTypes.FONT; }
<YYINITIAL> (section)                                       { return SPLTypes.SECTION; }
<YYINITIAL> (no[-_]?newline)                                { return SPLTypes.NO_NEWLINE; }
<YYINITIAL> (no[-_]?pause)                                  { return SPLTypes.NO_PAUSE; }
<YYINITIAL> (spool)                                         { return SPLTypes.SPOOL; }
<YYINITIAL> (button[-_]?when)                               { return SPLTypes.BUTTON_WHEN; }
<YYINITIAL> (before[-_]?radio[-_]?button)                   { return SPLTypes.BEFORE_RADIO_BUTTON; }
<YYINITIAL> (end[-_]?before[-_]?radio[-_]?button)           { return SPLTypes.END_BEFORE_RADIO_BUTTON; }
<YYINITIAL> (whens)                                         { return SPLTypes.WHENS; }
<YYINITIAL> (prompts)                                       { return SPLTypes.PROMPTS; }
<YYINITIAL> (review)                                        { return SPLTypes.REVIEW; }
<YYINITIAL> (until)                                         { return SPLTypes.UNTIL; }
<YYINITIAL> (direct)                                        { return SPLTypes.DIRECT; }
<YYINITIAL> (name)                                          { return SPLTypes.NAME; }
<YYINITIAL> (header)                                        { return SPLTypes.HEADER; }
<YYINITIAL> (footer)                                        { return SPLTypes.FOOTER; }
<YYINITIAL> (depth)                                         { return SPLTypes.DEPTH; }
<YYINITIAL> (length)                                        { return SPLTypes.LENGTH; }
<YYINITIAL> (margin)                                        { return SPLTypes.MARGIN; }
<YYINITIAL> (full[-_]?page)                                 { return SPLTypes.FULL_PAGE; }
<YYINITIAL> (form)                                          { return SPLTypes.FORM; }
<YYINITIAL> (no[-_]?xml)                                    { return SPLTypes.NO_XML; }
<YYINITIAL> (spool[-_]?only)                                { return SPLTypes.SPOOL_ONLY; }
<YYINITIAL> (full[-_]?xml)                                  { return SPLTypes.FULL_XML; }
<YYINITIAL> (can[-_]?hide)                                  { return SPLTypes.CAN_HIDE; }
<YYINITIAL> (style[-_]?class)                               { return SPLTypes.STYLE_CLASS; }
<YYINITIAL> (row)                                           { return SPLTypes.ROW; }
<YYINITIAL> (group[-_]?style)                               { return SPLTypes.GROUP_STYLE; }
<YYINITIAL> (section[-_]?when)                              { return SPLTypes.SECTION_WHEN; }
<YYINITIAL> (hide)                                          { return SPLTypes.HIDE; }
<YYINITIAL> (distinct)                                      { return SPLTypes.DISTINCT; }
<YYINITIAL> (database[-_]?sql)                              { return SPLTypes.DATABASE_SQL; }
<YYINITIAL> (local[-_]?sql)                                 { return SPLTypes.LOCAL_SQL; }
<YYINITIAL> (order)                                         { return SPLTypes.ORDER; }
<YYINITIAL> (group)                                         { return SPLTypes.GROUP; }
<YYINITIAL> (for[-_]?update)                                { return SPLTypes.FOR_UPDATE; }
<YYINITIAL> (having)                                        { return SPLTypes.HAVING; }
<YYINITIAL> (inserting)                                     { return SPLTypes.INSERTING; }
<YYINITIAL> (replacing)                                     { return SPLTypes.REPLACING; }
<YYINITIAL> (deleting)                                      { return SPLTypes.DELETING; }
<YYINITIAL> (appending)                                     { return SPLTypes.APPENDING; }
<YYINITIAL> (case)                                          { return SPLTypes.CASE; }
<YYINITIAL> (begin)                                         { return SPLTypes.BEGIN; }
<YYINITIAL> (commit)                                        { return SPLTypes.COMMIT; }
<YYINITIAL> (rollback)                                      { return SPLTypes.ROLLBACK; }


//Constants
<YYINITIAL> (MSG_BOX_OK|MSG_BOX_CANCEL|MSG_BOX_YES|MSG_BOX_NO|MSG_BOX_RETRY|MSG_BOX_OK_CANCEL|MSG_BOX_YES_NO|MSG_BOX_YES_NO_CANCEL) { return SPLTypes.MESSAGEBOX_BUTTONS_VALUES; }
<YYINITIAL> (MSG_BOX_STOP|MSG_BOX_WARNING|MSG_BOX_INFORMATION|MSG_BOX_QUESTION|MSG_BOX_EXCLAMATION) { return SPLTypes.MESSAGEBOX_ICON_VALUES; }
<YYINITIAL> (entry|correct|remove|duplicate|entry[-_]?once|find|next[-_]?scr|prev[-_]?scr) { return SPLTypes.MODEENTRY; }



//Statements
//STATEMENTS=(abort|accept|acknowledge|audit|back-to-detail|begin work|box|break|call|call-url|check-box|clear|close|command|commit work|confirm|continue|continue-entry|delete|disable-all-triggers|display|do|drop-down|enquiry|exit|extract|field-group|for|get|initialise|insert|link|lock-method|need|open|option|page|pause|pop|position|print|push|query|radio-button|re-enter|refresh|repeat|report|report section|re-select|restore|rollback work|save|screen-group|screen-section|select|serial|set|set-date-validation|skip|spl|sql-delete|sql-update|statement-group|switch|transaction|unlock|update|version-number|web-client-local-agent|while|workstation-local-agent)
<YYINITIAL> (abort)                                         { return SPLTypes.ABORT; }
<YYINITIAL> (accept)                                        { return SPLTypes.ACCEPT; }
<YYINITIAL> (acknowledge)                                   { return SPLTypes.ACKNOWLEDGE; }
<YYINITIAL> (audit|auditon)                                 { return SPLTypes.AUDIT; }
<YYINITIAL> (back[-_]?to[-_]?detail)                        { return SPLTypes.BACK_TO_DETAIL; }
<YYINITIAL> (begin work)                                    { return SPLTypes.BEGIN_WORK; }
<YYINITIAL> (box)                                           { return SPLTypes.BOX; }
<YYINITIAL> (break)                                         { return SPLTypes.BREAK; }
<YYINITIAL> (call)                                          { return SPLTypes.CALL; }
<YYINITIAL> (call[-_]?url)                                  { return SPLTypes.CALL_URL; }
<YYINITIAL> (check[-_]?box)                                 { return SPLTypes.CHECK_BOX; }
<YYINITIAL> (end[-_]?check[-_]?box)                         { return SPLTypes.CHECK_BOX_END; }
<YYINITIAL> (clear)                                         { return SPLTypes.CLEAR; }
<YYINITIAL> (close)                                         { return SPLTypes.CLOSE; }
<YYINITIAL> (command)                                       { return SPLTypes.COMMAND; }
<YYINITIAL> (commit work)                                   { return SPLTypes.COMMIT_WORK; }
<YYINITIAL> (confirm)                                       { return SPLTypes.CONFIRM; }
<YYINITIAL> (end[-_]?confirm)                               { return SPLTypes.CONFIRM_END; }
<YYINITIAL> (continue)                                      { return SPLTypes.CONTINUE; }
<YYINITIAL> (continue[-_]?entry)                            { return SPLTypes.CONTINUE_ENTRY; }
<YYINITIAL> (delete)                                        { return SPLTypes.DELETE; }
<YYINITIAL> (disable[-_]?all[-_]?triggers)                  { return SPLTypes.DISABLE_ALL_TRIGGERS; }
<YYINITIAL> (display)                                       { return SPLTypes.DISPLAY; }
<YYINITIAL> (do)                                            { return SPLTypes.DO; }
<YYINITIAL> (drop[-_]?down)                                 { return SPLTypes.DROP_DOWN; }
<YYINITIAL> (end[-_]?drop[-_]?down)                         { return SPLTypes.DROP_DOWN_END; }
<YYINITIAL> (enquiry)                                       { return SPLTypes.QUERY; }
<YYINITIAL> (exit)                                          { return SPLTypes.EXIT; }
<YYINITIAL> (extract)                                       { return SPLTypes.EXTRACT; }
<YYINITIAL> (end[-_]?extract)                               { return SPLTypes.EXTRACT_END; }
<YYINITIAL> (field[-_]?group)                               { return SPLTypes.FIELD_GROUP; }
<YYINITIAL> (end[-_]?field[-_]?group)                       { return SPLTypes.FIELD_GROUP_END; }
<YYINITIAL> (for)                                           { return SPLTypes.FOR; }
<YYINITIAL> (end[-_]?for)                                   { return SPLTypes.FOR_END; }
<YYINITIAL> (get)                                           { return SPLTypes.GET; }
<YYINITIAL> (if)                                            { return SPLTypes.IF; }
<YYINITIAL> (end[-_]?if)                                    { return SPLTypes.ENDIF; }
<YYINITIAL> (initialise)                                    { return SPLTypes.INITIALISE; }
<YYINITIAL> (insert)                                        { return SPLTypes.INSERT; }
<YYINITIAL> (link)                                          { return SPLTypes.LINK; }
<YYINITIAL> (lock[-_]?method)                               { return SPLTypes.LOCK_METHOD; }
<YYINITIAL> (message)                                       { return SPLTypes.MESSAGE; }
<YYINITIAL> (message[-_]?box)                               { return SPLTypes.MESSAGEBOX; }
<YYINITIAL> (need)                                          { return SPLTypes.NEED; }
<YYINITIAL> (open)                                          { return SPLTypes.OPEN; }
<YYINITIAL> (option)                                        { return SPLTypes.OPTION; }
<YYINITIAL> (end[-_]?option)                                { return SPLTypes.OPTION_END; }
<YYINITIAL> (page)                                          { return SPLTypes.PAGE; }
<YYINITIAL> (pause)                                         { return SPLTypes.PAUSE; }
<YYINITIAL> (pop)                                           { return SPLTypes.POP; }
<YYINITIAL> (position)                                      { return SPLTypes.POSITION; }
<YYINITIAL> (print)                                         { return SPLTypes.PRINT; }
<YYINITIAL> (push)                                          { return SPLTypes.PUSH; }
<YYINITIAL> (query)                                         { return SPLTypes.QUERY; }
<YYINITIAL> (radio[-_]?button)                              { return SPLTypes.RADIO_BUTTON; }
<YYINITIAL> (end[-_]?radio[-_]?button)                      { return SPLTypes.RADIO_BUTTON_END; }
<YYINITIAL> (re[-_]?enter)                                  { return SPLTypes.RE_ENTER; }
<YYINITIAL> (refresh)                                       { return SPLTypes.REFRESH; }
<YYINITIAL> (repeat)                                        { return SPLTypes.REPEAT; }
<YYINITIAL> (end[-_]?repeat)                                { return SPLTypes.REPEAT_END; }
<YYINITIAL> (report)                                        { return SPLTypes.REPORT; }
<YYINITIAL> (re[-_]?select)                                 { return SPLTypes.RE_SELECT; }
<YYINITIAL> (restore)                                       { return SPLTypes.RESTORE; }
<YYINITIAL> (rollback work)                                 { return SPLTypes.ROLLBACK_WORK; }
<YYINITIAL> (save)                                          { return SPLTypes.SAVE; }
<YYINITIAL> (screen[-_]?group)                              { return SPLTypes.SCREEN_GROUP; }
<YYINITIAL> (end[-_]?screen[-_]?group)                      { return SPLTypes.SCREEN_GROUP_END; }
<YYINITIAL> (screen[-_]?section)                            { return SPLTypes.SCREEN_SECTION; }
<YYINITIAL> (end[-_]?screen[-_]?section)                    { return SPLTypes.SCREEN_SECTION_END; }
<YYINITIAL> (select)                                        { return SPLTypes.SELECT; }
<YYINITIAL> (end[-_]?select)                                { return SPLTypes.SELECT_END; }
<YYINITIAL> (serial)                                        { return SPLTypes.SERIAL; }
<YYINITIAL> (set)                                           { return SPLTypes.SET; }
<YYINITIAL> (set[-_]?date[-_]?validation)                   { return SPLTypes.SET_DATE_VALIDATION; }
<YYINITIAL> (skip)                                          { return SPLTypes.SKIP; }
<YYINITIAL> (spl)                                           { return SPLTypes.CALL; }
<YYINITIAL> (sql[-_]?delete)                                { return SPLTypes.SQL_DELETE; }
<YYINITIAL> (sql[-_]?update)                                { return SPLTypes.SQL_UPDATE; }
<YYINITIAL> (statement[-_]?group)                           { return SPLTypes.STATEMENT_GROUP; }
<YYINITIAL> (end[-_]?statement[-_]?group)                   { return SPLTypes.STATEMENT_GROUP_END; }
<YYINITIAL> (string)                                        { return SPLTypes.STRINGSTATMENT; }
<YYINITIAL> (switch)                                        { return SPLTypes.SWITCH; }
<YYINITIAL> (end[-_]?switch)                                { return SPLTypes.SWITCH_END; }
<YYINITIAL> (transaction)                                   { return SPLTypes.TRANSACTION; }
<YYINITIAL> (unlock)                                        { return SPLTypes.UNLOCK; }
<YYINITIAL> (update)                                        { return SPLTypes.UPDATE; }
<YYINITIAL> (version[-_]?number)                            { return SPLTypes.VERSION_NUMBER; }
<YYINITIAL> (web[-_]?client[-_]?local[-_]?agent|workstation[-_]?local[-_]?agent) { return SPLTypes.WEB_CLIENT_LOCAL_AGENT; }
<YYINITIAL> (while)                                         { return SPLTypes.WHILE; }
<YYINITIAL> (end[-_]?while)                                 { return SPLTypes.WHILE_END; }


//Arithmetic functions
<YYINITIAL> (aand)                                          { return SPLTypes.AAND; }
<YYINITIAL> (abs)                                           { return SPLTypes.ABS; }
<YYINITIAL> (anot)                                          { return SPLTypes.ANOT; }
<YYINITIAL> (aor)                                           { return SPLTypes.AOR; }
<YYINITIAL> (cos)                                           { return SPLTypes.COS; }
<YYINITIAL> (fraction)                                      { return SPLTypes.FRACTION; }
<YYINITIAL> (integer)                                       { return SPLTypes.INTEGER; }
<YYINITIAL> (lshift)                                        { return SPLTypes.LSHIFT; }
<YYINITIAL> (max[-_]?value)                                 { return SPLTypes.MAX_VALUE; }
<YYINITIAL> (max[-_]?presentation[-_]?value)                { return SPLTypes.MAX_PRESENTATION_VALUE; }
<YYINITIAL> (min[-_]?value)                                 { return SPLTypes.MIN_VALUE; }
<YYINITIAL> (power[-_]?of)                                  { return SPLTypes.POWER_OF; }
<YYINITIAL> (random)                                        { return SPLTypes.RANDOM; }
<YYINITIAL> (rshift)                                        { return SPLTypes.RSHIFT; }
<YYINITIAL> (sign[-_]?of)                                   { return SPLTypes.SIGN_OF; }
<YYINITIAL> (sin)                                           { return SPLTypes.SIN; }
<YYINITIAL> (smallest[-_]?increment)                        { return SPLTypes.SMALLEST_INCREMENT; }
<YYINITIAL> (square[-_]?root)                               { return SPLTypes.SQUARE_ROOT; }
<YYINITIAL> (sum)                                           { return SPLTypes.SUM; }
<YYINITIAL> (sum[-_]?array)                                 { return SPLTypes.SUM_ARRAY; }
<YYINITIAL> (tan)                                           { return SPLTypes.TAN; }

//DateTime functions
<YYINITIAL> (add[-_]?month)                                 { return SPLTypes.ADD_MONTH; }
<YYINITIAL> (client[-_]?date[-_]?time[-_]?string)           { return SPLTypes.CLIENT_DATE_TIME_STRING; }
<YYINITIAL> (date[-_]?from[-_]?date[-_]?time)               { return SPLTypes.DATE_FROM_DATE_TIME; }
<YYINITIAL> (date[-_]?time)                                 { return SPLTypes.DATE_TIME; }
<YYINITIAL> (date[-_]?to[-_]?julian)                        { return SPLTypes.DATE_TO_JULIAN; }
<YYINITIAL> (day)                                           { return SPLTypes.DAY; }
<YYINITIAL> (day[-_]?name)                                  { return SPLTypes.DAY_NAME; }
<YYINITIAL> (days[-_]?in[-_]?month)                         { return SPLTypes.DAYS_IN_MONTH; }
<YYINITIAL> (dow)                                           { return SPLTypes.DOW; }
<YYINITIAL> (gmt|gmtime)                                    { return SPLTypes.GMT; }
<YYINITIAL> (hour)                                          { return SPLTypes.HOUR; }
<YYINITIAL> (julian)                                        { return SPLTypes.JULIAN; }
<YYINITIAL> (julian[-_]?to[-_]?date|julian2date)            { return SPLTypes.JULIAN_TO_DATE; }
<YYINITIAL> (leap[-_]?year)                                 { return SPLTypes.LEAP_YEAR; }
<YYINITIAL> (minute)                                        { return SPLTypes.MINUTE; }
<YYINITIAL> (month)                                         { return SPLTypes.MONTH; }
<YYINITIAL> (month[-_]?name)                                { return SPLTypes.MONTH_NAME; }
<YYINITIAL> (second)                                        { return SPLTypes.SECOND; }
<YYINITIAL> (sys[-_]?time)                                  { return SPLTypes.SYSTIME; }
<YYINITIAL> (time[-_]?from[-_]?date[-_]?time)               { return SPLTypes.TIME_FROM_DATE_TIME; }
<YYINITIAL> (time[-_]?zone)                                 { return SPLTypes.TIME_ZONE; }
<YYINITIAL> (tod)                                           { return SPLTypes.TOD; }
<YYINITIAL> (today)                                         { return SPLTypes.TODAY; }
<YYINITIAL> (year)                                          { return SPLTypes.YEAR; }

//Environment functions
<YYINITIAL> (active[-_]?pid)                                { return SPLTypes.ACTIVE_PID; }
<YYINITIAL> (api[-_]?application[-_]?name)                  { return SPLTypes.API_APPLICATION_NAME; }
<YYINITIAL> (batched)                                       { return SPLTypes.BATCHED; }
<YYINITIAL> (can[-_]?dde)                                   { return SPLTypes.CAN_DDE; }
<YYINITIAL> (check[-_]?auth)                                { return SPLTypes.CHECK_AUTH; }
<YYINITIAL> (colour[-_]?picker)                             { return SPLTypes.COLOUR_PICKER; }
<YYINITIAL> (create[-_]?db[-_]?schema)                      { return SPLTypes.CREATE_DB_SCHEMA; }
<YYINITIAL> (create[-_]?db[-_]?user)                        { return SPLTypes.CREATE_DB_USER; }
<YYINITIAL> (currency[-_]?sign)                             { return SPLTypes.CURRENCY_SIGN; }
<YYINITIAL> (database[-_]?type)                             { return SPLTypes.DATABASE_TYPE; }
<YYINITIAL> (db[-_]?command)                                { return SPLTypes.DB_COMMAND; }
<YYINITIAL> (db[-_]?table[-_]?name)                         { return SPLTypes.DB_TABLE_NAME; }
<YYINITIAL> (dde[-_]?error[-_]?status)                      { return SPLTypes.DDE_ERROR_STATUS; }
<YYINITIAL> (dde[-_]?execute)                               { return SPLTypes.DDE_EXECUTE; }
<YYINITIAL> (dde[-_]?initiate)                              { return SPLTypes.DDE_INITIATE; }
<YYINITIAL> (dde[-_]?poke)                                  { return SPLTypes.DDE_POKE; }
<YYINITIAL> (dde[-_]?request)                               { return SPLTypes.DDE_REQUEST; }
<YYINITIAL> (dde[-_]?terminate)                             { return SPLTypes.DDE_TERMINATE; }
<YYINITIAL> (delete[-_]?registry[-_]?value)                 { return SPLTypes.DELETE_REGISTRY_VALUE; }
<YYINITIAL> (enable[-_]?status[-_]?bar)                     { return SPLTypes.ENABLE_STATUS_BAR; }
<YYINITIAL> (enable[-_]?system[-_]?menu)                    { return SPLTypes.ENABLE_SYSTEM_MENU; }
<YYINITIAL> (enable[-_]?tool[-_]?bar)                       { return SPLTypes.ENABLE_TOOL_BAR; }
<YYINITIAL> (error[-_]?description)                         { return SPLTypes.ERROR_DESCRIPTION; }
<YYINITIAL> (escape)                                        { return SPLTypes.ESCAPE; }
<YYINITIAL> (exit[-_]?status)                               { return SPLTypes.EXIT_STATUS; }
<YYINITIAL> (find[-_]?parameter)                            { return SPLTypes.FIND_PARAMETER; }
<YYINITIAL> (get[-_]?env)                                   { return SPLTypes.GETENV; }
<YYINITIAL> (get[-_]?field[-_]?value)                       { return SPLTypes.GET_FIELD_VALUE; }
<YYINITIAL> (get[-_]?field[-_]?value[-_]?numeric)           { return SPLTypes.GET_FIELD_VALUE_NUMERIC; }
<YYINITIAL> (get[-_]?function[-_]?code)                     { return SPLTypes.GET_FUNCTION_CODE; }
<YYINITIAL> (get[-_]?module[-_]?code)                       { return SPLTypes.GET_MODULE_CODE; }
<YYINITIAL> (get[-_]?param)                                 { return SPLTypes.GET_PARAM; }
<YYINITIAL> (get[-_]?registry[-_]?enum[-_]?key)             { return SPLTypes.GET_REGISTRY_ENUM_KEY; }
<YYINITIAL> (get[-_]?registry[-_]?enum[-_]?value)           { return SPLTypes.GET_REGISTRY_ENUM_VALUE; }
<YYINITIAL> (get[-_]?registry[-_]?value)                    { return SPLTypes.GET_REGISTRY_VALUE; }
<YYINITIAL> (get[-_]?system[-_]?metrics)                    { return SPLTypes.GET_SYSTEM_METRICS; }
<YYINITIAL> (gid)                                           { return SPLTypes.GID; }
<YYINITIAL> (grant[-_]?db[-_]?schema)                       { return SPLTypes.GRANT_DB_SCHEMA; }
<YYINITIAL> (hide[-_]?dockable[-_]?windows)                 { return SPLTypes.HIDE_DOCKABLE_WINDOWS; }
<YYINITIAL> (idx)                                           { return SPLTypes.IDX; }
<YYINITIAL> (if[-_]?then[-_]?else)                          { return SPLTypes.IF_THEN_ELSE; }
<YYINITIAL> (io[-_]?count)                                  { return SPLTypes.IO_COUNT; }
<YYINITIAL> (line[-_]?no)                                   { return SPLTypes.LINE_NO; }
<YYINITIAL> (local[-_]?no|local[-_]?yes)                    { return SPLTypes.LOCAL_NO_AND_LOCAL_YES; }
<YYINITIAL> (login[-_]?id)                                  { return SPLTypes.LOGIN_ID; }
<YYINITIAL> (mail[-_]?add[-_]?line)                         { return SPLTypes.MAIL_ADD_LINE; }
<YYINITIAL> (mail[-_]?attach)                               { return SPLTypes.MAIL_ATTACH; }
<YYINITIAL> (mail[-_]?cancel)                               { return SPLTypes.MAIL_CANCEL; }
<YYINITIAL> (mail[-_]?from[-_]?name)                        { return SPLTypes.MAIL_FROM_NAME; }
<YYINITIAL> (mail[-_]?reply[-_]?to)                         { return SPLTypes.MAIL_REPLY_TO; }
<YYINITIAL> (mail[-_]?send)                                 { return SPLTypes.MAIL_SEND; }
<YYINITIAL> (mail[-_]?start)                                { return SPLTypes.MAIL_START; }
<YYINITIAL> (max[-_]?screen[-_]?columns)                    { return SPLTypes.MAX_SCREEN_COLUMNS; }
<YYINITIAL> (max[-_]?screen[-_]?rows)                       { return SPLTypes.MAX_SCREEN_ROWS; }
<YYINITIAL> (message[-_]?status)                            { return SPLTypes.MESSAGE_STATUS; }
<YYINITIAL> (mode[-_]?name)                                 { return SPLTypes.MODE_NAME; }
<YYINITIAL> (mouse[-_]?column)                              { return SPLTypes.MOUSE_COLUMN; }
<YYINITIAL> (mouse[-_]?row)                                 { return SPLTypes.MOUSE_ROW; }
<YYINITIAL> (node[-_]?name)                                 { return SPLTypes.NODE_NAME; }
<YYINITIAL> (occurrence)                                    { return SPLTypes.OCCURRENCE; }
<YYINITIAL> (operating[-_]?system)                          { return SPLTypes.OPERATING_SYSTEM; }
<YYINITIAL> (page[-_]?no)                                   { return SPLTypes.PAGE_NO; }
<YYINITIAL> (param[-_]?cnt)                                 { return SPLTypes.PARAM_CNT; }
<YYINITIAL> (pid)                                           { return SPLTypes.PID; }
<YYINITIAL> (pronto[-_]?release)                            { return SPLTypes.PRONTO_RELEASE; }
<YYINITIAL> (prouser[-_]?flags)                             { return SPLTypes.PROUSER_FLAGS; }
<YYINITIAL> (refresh[-_]?quick[-_]?links)                   { return SPLTypes.REFRESH_QUICK_LINKS; }
<YYINITIAL> (report[-_]?is[-_]?xml)                         { return SPLTypes.REPORT_IS_XML; }
<YYINITIAL> (review[-_]?row)                                { return SPLTypes.REVIEW_ROW; }
<YYINITIAL> (revoke[-_]?db[-_]?schema)                      { return SPLTypes.REVOKE_DB_SCHEMA; }
<YYINITIAL> (rgb[-_]?to[-_]?colour)                         { return SPLTypes.RGB_TO_COLOUR; }
<YYINITIAL> (screen[-_]?mode)                               { return SPLTypes.SCREEN_MODE; }
<YYINITIAL> (search)                                        { return SPLTypes.SEARCH; }
<YYINITIAL> (search[-_]?mode)                               { return SPLTypes.SEARCH_MODE; }
<YYINITIAL> (set[-_]?app[-_]?user)                          { return SPLTypes.SET_APP_USER; }
<YYINITIAL> (set[-_]?data[-_]?area[-_]?name)                { return SPLTypes.SET_DATA_AREA_NAME; }
<YYINITIAL> (set[-_]?background[-_]?image)                  { return SPLTypes.SET_BACKGROUND_IMAGE; }
<YYINITIAL> (set[-_]?background[-_]?url)                    { return SPLTypes.SET_BACKGROUND_URL; }
<YYINITIAL> (set[-_]?environment)                           { return SPLTypes.SET_ENVIRONMENT; }
<YYINITIAL> (set[-_]?function[-_]?code)                     { return SPLTypes.SET_FUNCTION_CODE; }
<YYINITIAL> (set[-_]?help[-_]?context)                      { return SPLTypes.SET_HELP_CONTEXT; }
<YYINITIAL> (set[-_]?keyboard[-_]?focus)                    { return SPLTypes.SET_KEYBOARD_FOCUS; }
<YYINITIAL> (set[-_]?module[-_]?code)                       { return SPLTypes.SET_MODULE_CODE; }
<YYINITIAL> (set[-_]?registry[-_]?value)                    { return SPLTypes.SET_REGISTRY_VALUE; }
<YYINITIAL> (set[-_]?web[-_]?window)                        { return SPLTypes.SET_WEB_WINDOW; }
<YYINITIAL> (sleep)                                         { return SPLTypes.SLEEP; }
<YYINITIAL> (spool[-_]?file[-_]?name)                       { return SPLTypes.SPOOL_FILE_NAME; }
<YYINITIAL> (time[-_]?elapsed)                              { return SPLTypes.TIME_ELAPSED; }
<YYINITIAL> (transaction[-_]?active)                        { return SPLTypes.TRANSACTION_ACTIVE; }
<YYINITIAL> (tty)                                           { return SPLTypes.TTY; }
<YYINITIAL> (uid)                                           { return SPLTypes.UID; }
<YYINITIAL> (user[-_]?group)                                { return SPLTypes.USER_GROUP; }
<YYINITIAL> (valid[-_]?activation[-_]?key)                  { return SPLTypes.VALID_ACTIVATION_KEY; }
<YYINITIAL> (wait[-_]?for[-_]?input)                        { return SPLTypes.WAIT_FOR_INPUT; }

//File handling functions
<YYINITIAL> (cd)                                            { return SPLTypes.CD; }
<YYINITIAL> (cd[-_]?without[-_]?close[-_]?all)              { return SPLTypes.CD_WITHOUT_CLOSE_ALL; }
<YYINITIAL> (client[-_]?file[-_]?browse)                    { return SPLTypes.CLIENT_FILE_BROWSE; }
<YYINITIAL> (dir)                                           { return SPLTypes.DIR; }
<YYINITIAL> (file[-_]?exists)                               { return SPLTypes.FILE_EXISTS; }
<YYINITIAL> (file[-_]?name)                                 { return SPLTypes.FILE_NAME; }
<YYINITIAL> (file[-_]?owner)                                { return SPLTypes.FILE_OWNER; }
<YYINITIAL> (file[-_]?size)                                 { return SPLTypes.FILE_SIZE; }
<YYINITIAL> (file[-_]?status)                               { return SPLTypes.FILE_STATUS; }
<YYINITIAL> (file[-_]?version)                              { return SPLTypes.FILE_VERSION; }
<YYINITIAL> (finish[-_]?dir[-_]?search)                     { return SPLTypes.FINISH_DIR_SEARCH; }
<YYINITIAL> (is[-_]?a[-_]?dir)                              { return SPLTypes.IS_A_DIR; }
<YYINITIAL> (local[-_]?cd)                                  { return SPLTypes.LOCAL_CD; }
<YYINITIAL> (local[-_]?cd[-_]?without[-_]?close[-_]?all)    { return SPLTypes.LOCAL_CD_WITHOUT_CLOSE_ALL; }
<YYINITIAL> (local[-_]?dir)                                 { return SPLTypes.LOCAL_DIR; }
<YYINITIAL> (mkdir)                                         { return SPLTypes.MKDIR; }
<YYINITIAL> (modification[-_]?time)                         { return SPLTypes.MODIFICATION_TIME; }
<YYINITIAL> (next[-_]?dir[-_]?entry)                        { return SPLTypes.NEXT_DIR_ENTRY; }
<YYINITIAL> (rmdir)                                         { return SPLTypes.RMDIR; }
<YYINITIAL> (start[-_]?dir[-_]?search|next[-_]?dir[-_]?entry|finish[-_]?dir[-_]?search) { return SPLTypes.START_DIR_SEARCH; }

//String functions
<YYINITIAL> (ascii[-_]?char)                                { return SPLTypes.ASCII_CHAR; }
<YYINITIAL> (ascii[-_]?num|ascii|asc)                       { return SPLTypes.ASCII_NUM; }
<YYINITIAL> (base64[-_]?to[-_]?blob)                        { return SPLTypes.BASE64TOBLOB; }
<YYINITIAL> (blob[-_]?append)                               { return SPLTypes.BLOBAPPEND; }
<YYINITIAL> (blob[-_]?append[-_]?base64)                    { return SPLTypes.BLOBAPPENDBASE64; }
<YYINITIAL> (blob[-_]?section)                              { return SPLTypes.BLOBSECTION; }
<YYINITIAL> (blob[-_]?to[-_]?base64)                        { return SPLTypes.BLOBTOBASE64; }
<YYINITIAL> (concat)                                        { return SPLTypes.CONCAT; }
<YYINITIAL> (format[-_]?picture)                            { return SPLTypes.FORMATPICTURE; }
<YYINITIAL> (free[-_]?blob)                                 { return SPLTypes.FREEBLOB; }
<YYINITIAL> (fstr)                                          { return SPLTypes.FSTR; }
<YYINITIAL> (left[-_]?justify)                              { return SPLTypes.LEFTJUSTIFY; }
<YYINITIAL> (lowercase)                                     { return SPLTypes.LOWERCASE; }
<YYINITIAL> (ltrim)                                         { return SPLTypes.LTRIM; }
<YYINITIAL> (max[-_]?alpha[-_]?value)                       { return SPLTypes.MAXALPHAVALUE; }
<YYINITIAL> (num)                                           { return SPLTypes.NUM; }
<YYINITIAL> (pattern)                                       { return SPLTypes.PATTERN; }
<YYINITIAL> (param[-_]?text)                                { return SPLTypes.PARAMTEXT; }
<YYINITIAL> (read[-_]?blob[-_]?from[-_]?file)               { return SPLTypes.READBLOBFROMFILE; }
<YYINITIAL> (reserved)                                      { return SPLTypes.RESERVED; }
<YYINITIAL> (right[-_]?justify)                             { return SPLTypes.RIGHTJUSTIFY; }
<YYINITIAL> (rtrim)                                         { return SPLTypes.RTRIM; }
<YYINITIAL> (size[-_]?of)                                   { return SPLTypes.SIZEOF; }
<YYINITIAL> (sql[-_]?substring)                             { return SPLTypes.SQLSUBSTRING; }
<YYINITIAL> (str)                                           { return SPLTypes.STR_FUNC; }
<YYINITIAL> (str[-_]?concat)                                { return SPLTypes.STRCONCAT; }
<YYINITIAL> (str[-_]?len)                                   { return SPLTypes.STRLEN; }
<YYINITIAL> (sub[-_]?string)                                { return SPLTypes.SUBSTRING; }
<YYINITIAL> (substring[-_]?utf8)                            { return SPLTypes.SUBSTRINGUTF8; }
<YYINITIAL> (uppercase)                                     { return SPLTypes.UPPERCASE; }
<YYINITIAL> (valid[-_]?number)                              { return SPLTypes.VALIDNUMBER; }
<YYINITIAL> (write[-_]?blob[-_]?to[-_]?file)                { return SPLTypes.WRITEBLOBTOFILE; }
<YYINITIAL> (zstr)                                          { return SPLTypes.ZSTR; }


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