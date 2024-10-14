package com.bamuel.spllang;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.bamuel.spllang.psi.SPLTypes;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class SPLSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey FUNCTION_DECLARATION = createTextAttributesKey("SPL_FUNCTION_DECLARATION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey FUNCTION_CALL = createTextAttributesKey("SPL_FUNCTION_CALL", DefaultLanguageHighlighterColors.FUNCTION_CALL);
    public static final TextAttributesKey METADATA = createTextAttributesKey("SPL_PARAMETER", DefaultLanguageHighlighterColors.METADATA);
    public static final TextAttributesKey CONSTANT = createTextAttributesKey("SPL_CONSTANT", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey KEYWORD = createTextAttributesKey("SPL_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey IDENTIFIER = createTextAttributesKey("SPL_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey STRING = createTextAttributesKey("SPL_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey NUMBER = createTextAttributesKey("SPL_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey COMMA = createTextAttributesKey("SPL_COMMA", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey PARENTHESES = createTextAttributesKey("SPL_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey COMMENT = createTextAttributesKey("SPL_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT = createTextAttributesKey("SPL_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("SPL_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final Map<IElementType, TextAttributesKey[]> TOKEN_HIGHLIGHT_MAP = new HashMap<>();

    static {

        //Function Declaration
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.API, new TextAttributesKey[]{FUNCTION_DECLARATION});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.API_END, new TextAttributesKey[]{FUNCTION_DECLARATION});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.FIELD, new TextAttributesKey[]{FUNCTION_DECLARATION});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.LOCAL, new TextAttributesKey[]{FUNCTION_DECLARATION});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MENU, new TextAttributesKey[]{FUNCTION_DECLARATION});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MENU_END, new TextAttributesKey[]{FUNCTION_DECLARATION});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MODE, new TextAttributesKey[]{FUNCTION_DECLARATION});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.OBJECT, new TextAttributesKey[]{FUNCTION_DECLARATION});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.PARAMETERS, new TextAttributesKey[]{FUNCTION_DECLARATION});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.PROCEDURE, new TextAttributesKey[]{FUNCTION_DECLARATION});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.PROCEDURE_END, new TextAttributesKey[]{FUNCTION_DECLARATION});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.RETURNING, new TextAttributesKey[]{FUNCTION_DECLARATION});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN, new TextAttributesKey[]{FUNCTION_DECLARATION});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_END, new TextAttributesKey[]{FUNCTION_DECLARATION});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.FUNCTION_DECLARATION, new TextAttributesKey[]{FUNCTION_DECLARATION});

        //Metadata but also Function Declaration
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.RECORD, new TextAttributesKey[]{FUNCTION_DECLARATION});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.RECORD_END, new TextAttributesKey[]{FUNCTION_DECLARATION});

        //Metadata
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.TITLE, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.DEFAULT, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.WINDOW, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.PROMPT, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.DETAIL, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.ICON, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.TYPE, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.PIC, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.OCCURS, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.LIKE, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.JUSTIFY, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.USE_NAME_IN_DB, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.DB_COLUMN_NAME, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.DRILL_BACK, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.HELP, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SECURITY, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.PERFORM, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SEPARATOR, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.FILE, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.KEY, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.DESC, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.UNIQUE, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.DB_INDEX_ONLY, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.COMPRESS, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.NOJOINS, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.NOROWID, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.PROCESS, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MODECURRENCY, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.LOCK, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.ALWAYS_SHOW, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.ALWAYS_ADD, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.AUTO_TRANSACTION, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.TREEMENU, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.NOHIDE, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MENU_BUTTON_WIDTH, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MESSAGEBOX_BUTTONS, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.TAG, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.HELP_CONTEXT, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.OPTIONAL, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.BELL, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.COLOUR, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_RESPONSIVE, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.RELATIVE_TO, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.WINDOW_POSITION, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_PRIMARY, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_ON, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_SAME_DIFFERENT, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_SELECT, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_QUICK_LINK, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_REVIEW, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_ALLOWED, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_PROMPT_FOR_SEARCH, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_FIND_FOR_CURRENCY, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_DATA_GRID, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_REVIEW_OCCURS, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_REVIEW_FROM_START, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_NO_REVIEW_ROW_SEPARATORS, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_NO_REVIEW_COLUMN_SEPARATORS, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_NO_REVIEW_SEPARATORS, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_START_ON_CURRENT_RECORD, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_LEAVE_PARENT_SCREEN, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.WHEN, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.STOP_EXIT_ALL_KEY, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_POSITION_ON_OK, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_STAY_IN_CORRECT, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_FORM_ENTRY, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.EXPORT, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.CAN_OVERRIDE, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_HELP_CONTEXT, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.BEFORE, new TextAttributesKey[]{METADATA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.AFTER, new TextAttributesKey[]{METADATA});

        //Keywords
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MESSAGEBOX, new TextAttributesKey[]{KEYWORD});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MESSAGE, new TextAttributesKey[]{KEYWORD});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.PREPROCESSOR, new TextAttributesKey[]{KEYWORD});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.KEYWORD, new TextAttributesKey[]{KEYWORD});

        //Constants
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MESSAGEBOX_BUTTONS_VALUES, new TextAttributesKey[]{CONSTANT});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MESSAGEBOX_ICON_VALUES, new TextAttributesKey[]{CONSTANT});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MODEENTRY, new TextAttributesKey[]{CONSTANT});

        //Environment Functions
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.ACTIVE_PID, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.API_APPLICATION_NAME, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.BATCHED, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.CAN_DDE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.CHECK_AUTH, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.COLOUR_PICKER, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.CREATE_DB_SCHEMA, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.CREATE_DB_USER, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.CURRENCY_SIGN, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.DATABASE_TYPE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.DB_COMMAND, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.DB_TABLE_NAME, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.DDE_ERROR_STATUS, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.DDE_EXECUTE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.DDE_INITIATE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.DDE_POKE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.DDE_REQUEST, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.DDE_TERMINATE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.DELETE_REGISTRY_VALUE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.ENABLE_STATUS_BAR, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.ENABLE_SYSTEM_MENU, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.ENABLE_TOOL_BAR, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.ERROR_DESCRIPTION, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.ESCAPE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.EXIT_STATUS, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.FIND_PARAMETER, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.GETENV, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.GET_FIELD_VALUE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.GET_FIELD_VALUE_NUMERIC, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.GET_FUNCTION_CODE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.GET_MODULE_CODE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.GET_PARAM, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.GET_REGISTRY_ENUM_KEY, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.GET_REGISTRY_ENUM_VALUE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.GET_REGISTRY_VALUE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.GET_SYSTEM_METRICS, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.GID, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.GRANT_DB_SCHEMA, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.HIDE_DOCKABLE_WINDOWS, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.IDX, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.IF_THEN_ELSE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.IO_COUNT, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.LINE_NO, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.LOCAL_NO_AND_LOCAL_YES, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.LOGIN_ID, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MAIL_ADD_LINE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MAIL_ATTACH, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MAIL_CANCEL, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MAIL_FROM_NAME, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MAIL_REPLY_TO, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MAIL_SEND, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MAIL_START, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MAX_SCREEN_COLUMNS, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MAX_SCREEN_ROWS, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MESSAGE_STATUS, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MODE_NAME, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MOUSE_COLUMN, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MOUSE_ROW, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.NODE_NAME, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.OCCURRENCE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.OPERATING_SYSTEM, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.PAGE_NO, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.PARAM_CNT, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.PID, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.PRONTO_RELEASE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.PROUSER_FLAGS, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.REFRESH_QUICK_LINKS, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.REPORT_IS_XML, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.REVIEW_ROW, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.REVOKE_DB_SCHEMA, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.RGB_TO_COLOUR, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SCREEN_MODE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SEARCH, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SEARCH_MODE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SET_APP_USER, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SET_DATA_AREA_NAME, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SET_BACKGROUND_IMAGE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SET_BACKGROUND_URL, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SET_ENVIRONMENT, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SET_FUNCTION_CODE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SET_HELP_CONTEXT, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SET_KEYBOARD_FOCUS, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SET_MODULE_CODE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SET_REGISTRY_VALUE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SET_WEB_WINDOW, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SLEEP, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SPOOL_FILE_NAME, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.TIME_ELAPSED, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.TRANSACTION_ACTIVE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.TTY, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.UID, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.USER_GROUP, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.VALID_ACTIVATION_KEY, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.WAIT_FOR_INPUT, new TextAttributesKey[]{FUNCTION_CALL});
        //String Functions
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.ASCII_CHAR, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.ASCII_NUM, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.BASE64TOBLOB, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.BLOBAPPEND, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.BLOBAPPENDBASE64, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.BLOBSECTION, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.BLOBTOBASE64, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.CONCAT, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.FORMATPICTURE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.FREEBLOB, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.FSTR, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.LEFTJUSTIFY, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.LOWERCASE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.LTRIM, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.MAXALPHAVALUE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.NUM, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.PATTERN, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.READBLOBFROMFILE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.RESERVED, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.RIGHTJUSTIFY, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.RTRIM, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SIZEOF, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SQLSUBSTRING, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.STR_FUNC, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.STRLEN, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SUBSTRING, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.SUBSTRINGUTF8, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.UPPERCASE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.VALIDNUMBER, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.WRITEBLOBTOFILE, new TextAttributesKey[]{FUNCTION_CALL});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.ZSTR, new TextAttributesKey[]{FUNCTION_CALL});

        //Other
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.STRING, new TextAttributesKey[]{STRING});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.NUMBER, new TextAttributesKey[]{NUMBER});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.COMMA, new TextAttributesKey[]{COMMA});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.LPAREN, new TextAttributesKey[]{PARENTHESES});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.RPAREN, new TextAttributesKey[]{PARENTHESES});

        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.COMMENT, new TextAttributesKey[]{COMMENT});
        TOKEN_HIGHLIGHT_MAP.put(SPLTypes.BLOCK_COMMENT, new TextAttributesKey[]{BLOCK_COMMENT});

        TOKEN_HIGHLIGHT_MAP.put(TokenType.BAD_CHARACTER, new TextAttributesKey[]{BAD_CHARACTER});
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new SPLLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        return TOKEN_HIGHLIGHT_MAP.getOrDefault(tokenType, new TextAttributesKey[0]);
    }
}
