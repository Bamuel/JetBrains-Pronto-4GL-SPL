package com.bamuel.spllang;

import com.bamuel.spllang.psi.SPLTypes;
import com.intellij.lang.documentation.DocumentationMarkup;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.util.text.HtmlChunk;
import com.intellij.psi.tree.IElementType;
import org.apache.commons.compress.utils.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.intellij.model.Pointer;
import com.intellij.platform.backend.documentation.DocumentationResult;
import com.intellij.platform.backend.documentation.DocumentationTarget;
import com.intellij.platform.backend.presentation.TargetPresentation;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class SPLDocumentationTarget implements DocumentationTarget {

    private final PsiElement element;
    private final PsiElement originalElement;
    private static final Map<IElementType, String> resourcePathMap = new HashMap<>();

    static {
        resourcePathMap.put(SPLTypes.API, "docs/declarations/api_declaration.htm");
        resourcePathMap.put(SPLTypes.FIELD, "docs/declarations/field_declaration.htm");
        resourcePathMap.put(SPLTypes.LOCAL, "docs/declarations/local_declaration.htm");
        resourcePathMap.put(SPLTypes.MENU, "docs/declarations/menu_declaration.htm");
        resourcePathMap.put(SPLTypes.MODE, "docs/declarations/mode_declaration.htm");
        resourcePathMap.put(SPLTypes.OBJECT, "docs/declarations/object_declaration.htm");
        resourcePathMap.put(SPLTypes.PARAMETERS, "docs/declarations/parameter_declaration.htm");
        resourcePathMap.put(SPLTypes.PROCEDURE, "docs/declarations/procedure_declaration.htm");
        resourcePathMap.put(SPLTypes.RETURNING, "docs/declarations/returning_declaration.htm");
        resourcePathMap.put(SPLTypes.SCREEN, "docs/declarations/screen_declaration.htm");
        resourcePathMap.put(SPLTypes.EXPORT, "docs/clauses/export_clause.htm");
        resourcePathMap.put(SPLTypes.ABORT, "docs/statements/abort_statement.htm");
        resourcePathMap.put(SPLTypes.ACCEPT, "docs/statements/accept_statement.htm");
        resourcePathMap.put(SPLTypes.ACKNOWLEDGE, "docs/statements/acknowledge_statement.htm");
        resourcePathMap.put(SPLTypes.AUDIT, "docs/statements/audit_statement.htm");
        resourcePathMap.put(SPLTypes.BACK_TO_DETAIL, "docs/statements/back_to_detail_statement.htm");
        resourcePathMap.put(SPLTypes.BEGINWORK_, "docs/statements/begin_work_statement.htm");
        resourcePathMap.put(SPLTypes.BOX, "docs/statements/box_statement.htm");
        resourcePathMap.put(SPLTypes.BREAK, "docs/statements/break_statement.htm");
        resourcePathMap.put(SPLTypes.CALL, "docs/statements/call_statement.htm");
        resourcePathMap.put(SPLTypes.CALL_URL, "docs/statements/call_url_statement.htm");
        resourcePathMap.put(SPLTypes.CHECK_BOX, "docs/statements/check_box_statement.htm");
        resourcePathMap.put(SPLTypes.CLEAR, "docs/statements/clear_statement.htm");
        resourcePathMap.put(SPLTypes.CLOSE, "docs/statements/close_statement.htm");
        resourcePathMap.put(SPLTypes.COMMAND, "docs/statements/command_statement.htm");
        resourcePathMap.put(SPLTypes.COMMITWORK_, "docs/statements/commit_work_statement.htm");
        resourcePathMap.put(SPLTypes.CONFIRM, "docs/statements/confirm_statement.htm");
        resourcePathMap.put(SPLTypes.CONTINUE, "docs/statements/continue_statement.htm");
        resourcePathMap.put(SPLTypes.CONTINUE_ENTRY, "docs/statements/continue_entry_statement.htm");
        resourcePathMap.put(SPLTypes.DELETE, "docs/statements/delete_statement.htm");
        resourcePathMap.put(SPLTypes.DISABLE_ALL_TRIGGERS, "docs/statements/disable_all_triggers_statement.htm");
        resourcePathMap.put(SPLTypes.DISPLAY, "docs/statements/display_statement.htm");
        resourcePathMap.put(SPLTypes.DO, "docs/statements/do_statement.htm");
        resourcePathMap.put(SPLTypes.EXIT, "docs/statements/exit_statement.htm");
        resourcePathMap.put(SPLTypes.EXTRACT, "docs/statements/extract_statement.htm");
        resourcePathMap.put(SPLTypes.FOR, "docs/statements/for_statement.htm");
        resourcePathMap.put(SPLTypes.GET, "docs/statements/get_statement.htm");
        resourcePathMap.put(SPLTypes.IF, "docs/statements/if_statement.htm");
        resourcePathMap.put(SPLTypes.INITIALISE, "docs/statements/initialise_statement.htm");
        resourcePathMap.put(SPLTypes.INSERT, "docs/statements/insert_statement.htm");
        resourcePathMap.put(SPLTypes.LINK, "docs/statements/link_statement.htm");
        resourcePathMap.put(SPLTypes.LOCK_METHOD, "docs/statements/lock_method_statement.htm");
        resourcePathMap.put(SPLTypes.MESSAGE, "docs/statements/message_statement.htm");
        resourcePathMap.put(SPLTypes.MESSAGEBOX, "docs/statements/message_box_statement.htm");
        resourcePathMap.put(SPLTypes.NEED, "docs/statements/need_statement.htm");
        resourcePathMap.put(SPLTypes.OPEN, "docs/statements/open_statement.htm");
        resourcePathMap.put(SPLTypes.OPTION, "docs/statements/option_statement.htm");
        resourcePathMap.put(SPLTypes.PAGE, "docs/statements/page_statement.htm");
        resourcePathMap.put(SPLTypes.PAUSE, "docs/statements/pause_statement.htm");
        resourcePathMap.put(SPLTypes.POP, "docs/statements/pop_statement.htm");
        resourcePathMap.put(SPLTypes.POSITION, "docs/statements/position_statement.htm");
        resourcePathMap.put(SPLTypes.PRINT, "docs/statements/print_statement.htm");
        resourcePathMap.put(SPLTypes.PUSH, "docs/statements/push_statement.htm");
        resourcePathMap.put(SPLTypes.QUERY, "docs/statements/query_statement.htm");
        resourcePathMap.put(SPLTypes.RADIO_BUTTON, "docs/statements/radio_button_statement.htm");
        resourcePathMap.put(SPLTypes.RE_ENTER, "docs/statements/re_enter_statement.htm");
        resourcePathMap.put(SPLTypes.REFRESH, "docs/statements/refresh_statement.htm");
        resourcePathMap.put(SPLTypes.REPEAT, "docs/statements/repeat_statement.htm");
        resourcePathMap.put(SPLTypes.REPORT, "docs/statements/report_statement.htm");
        resourcePathMap.put(SPLTypes.REPORTSECTION_, "docs/statements/report_section_statement.htm.htm");
        resourcePathMap.put(SPLTypes.RE_SELECT, "docs/statements/re_select_statement.htm");
        resourcePathMap.put(SPLTypes.RESTORE, "docs/statements/restore_statement.htm");
        resourcePathMap.put(SPLTypes.ROLLBACKWORK_, "docs/statements/rollback_work_statement.htm.htm");
        resourcePathMap.put(SPLTypes.SAVE, "docs/statements/save_statement.htm");
        resourcePathMap.put(SPLTypes.SELECT, "docs/statements/select_statement.htm");
        resourcePathMap.put(SPLTypes.SERIAL, "docs/statements/serial_statement.htm");
        resourcePathMap.put(SPLTypes.SET, "docs/statements/set_statement.htm");
        resourcePathMap.put(SPLTypes.SET_DATE_VALIDATION, "docs/statements/set_date_validation_statement.htm");
        resourcePathMap.put(SPLTypes.SKIP, "docs/statements/skip_statement.htm");
        resourcePathMap.put(SPLTypes.SQL_DELETE, "docs/statements/sql_delete_statement.htm");
        resourcePathMap.put(SPLTypes.SQL_UPDATE, "docs/statements/sql_update_statement.htm");
        resourcePathMap.put(SPLTypes.STATEMENT_GROUP, "docs/statements/statement_group_statement.htm");
        resourcePathMap.put(SPLTypes.STRINGSTATMENT, "docs/statements/string_statement.htm");
        resourcePathMap.put(SPLTypes.SWITCH, "docs/statements/switch_statement.htm");
        resourcePathMap.put(SPLTypes.TRANSACTION, "docs/statements/transaction_statement.htm");
        resourcePathMap.put(SPLTypes.UNLOCK, "docs/statements/unlock_statement.htm");
        resourcePathMap.put(SPLTypes.UPDATE, "docs/statements/update_statement.htm");
        resourcePathMap.put(SPLTypes.VERSION_NUMBER, "docs/statements/version_number_statement.htm");
        resourcePathMap.put(SPLTypes.WEB_CLIENT_LOCAL_AGENT, "docs/statements/web_client_local_agent_statement.htm");
        resourcePathMap.put(SPLTypes.WHILE, "docs/statements/while_statement.htm");
        resourcePathMap.put(SPLTypes.DROP_DOWN, "docs/statements/drop_down_statement.htm");
        resourcePathMap.put(SPLTypes.FIELD_GROUP, "docs/statements/field_group_statement.htm");
        resourcePathMap.put(SPLTypes.SCREEN_RESPONSIVE, "docs/clauses/responsive_clause.htm");
        resourcePathMap.put(SPLTypes.SCREEN_GROUP, "docs/statements/screen_group_statement.htm");
        resourcePathMap.put(SPLTypes.SCREEN_SECTION, "docs/statements/screen_section_statement.htm");
        resourcePathMap.put(SPLTypes.AAND, "docs/functions/aand.htm");
        resourcePathMap.put(SPLTypes.ABS, "docs/functions/abs.htm");
        resourcePathMap.put(SPLTypes.ACTIVE_PID, "docs/functions/active_pid.htm");
        resourcePathMap.put(SPLTypes.ADD_MONTH, "docs/functions/add_month.htm");
        resourcePathMap.put(SPLTypes.ANOT, "docs/functions/anot.htm");
        resourcePathMap.put(SPLTypes.AOR, "docs/functions/aor.htm");
        resourcePathMap.put(SPLTypes.API_APPLICATION_NAME, "docs/functions/api_application_name.htm");
        resourcePathMap.put(SPLTypes.ASCII_CHAR, "docs/functions/ascii_char.htm");
        resourcePathMap.put(SPLTypes.ASCII_NUM, "docs/functions/ascii_num.htm");
        resourcePathMap.put(SPLTypes.BASE64TOBLOB, "docs/functions/base64_to_blob.htm");
        resourcePathMap.put(SPLTypes.BATCHED, "docs/functions/batched.htm");
        resourcePathMap.put(SPLTypes.BLOBAPPEND, "docs/functions/blob_append.htm");
        resourcePathMap.put(SPLTypes.BLOBAPPENDBASE64, "docs/functions/blob_append_base64.htm");
        resourcePathMap.put(SPLTypes.BLOBSECTION, "docs/functions/blob_section.htm");
        resourcePathMap.put(SPLTypes.BLOBTOBASE64, "docs/functions/blob_to_base64.htm");
        resourcePathMap.put(SPLTypes.CAN_DDE, "docs/functions/can_dde.htm");
        resourcePathMap.put(SPLTypes.CD, "docs/functions/cd.htm");
        resourcePathMap.put(SPLTypes.CD_WITHOUT_CLOSE_ALL, "docs/functions/cd_without_close_all.htm");
        resourcePathMap.put(SPLTypes.CHECK_AUTH, "docs/functions/check_auth.htm");
        resourcePathMap.put(SPLTypes.CLIENT_DATE_TIME_STRING, "docs/functions/client_date_time_string.htm");
        resourcePathMap.put(SPLTypes.CLIENT_FILE_BROWSE, "docs/functions/client_file_browse.htm");
        resourcePathMap.put(SPLTypes.COLOUR_PICKER, "docs/functions/colour_picker.htm");
        resourcePathMap.put(SPLTypes.CONCAT, "docs/functions/concat.htm");
        resourcePathMap.put(SPLTypes.COS, "docs/functions/cos.htm");
        resourcePathMap.put(SPLTypes.CRC32, "docs/functions/crc32.htm");
        resourcePathMap.put(SPLTypes.CREATE_DB_SCHEMA, "docs/functions/create_db_schema.htm");
        resourcePathMap.put(SPLTypes.CREATE_DB_USER, "docs/functions/create_db_user.htm");
        resourcePathMap.put(SPLTypes.CURRENCY_SIGN, "docs/functions/currency_sign.htm");
        resourcePathMap.put(SPLTypes.DATABASE_TYPE, "docs/functions/database_type.htm");
        resourcePathMap.put(SPLTypes.DATE_FROM_DATE_TIME, "docs/functions/date_from_date_time.htm");
        resourcePathMap.put(SPLTypes.DATE_TIME, "docs/functions/date_time.htm");
        resourcePathMap.put(SPLTypes.DATE_TO_JULIAN, "docs/functions/date_to_julian.htm");
        resourcePathMap.put(SPLTypes.DAY, "docs/functions/day.htm");
        resourcePathMap.put(SPLTypes.DAY_NAME, "docs/functions/day_name.htm");
        resourcePathMap.put(SPLTypes.DAYS_IN_MONTH, "docs/functions/days_in_month.htm");
        resourcePathMap.put(SPLTypes.DB_COMMAND, "docs/functions/db_command.htm");
        resourcePathMap.put(SPLTypes.DB_TABLE_NAME, "docs/functions/db_table_name.htm");
        resourcePathMap.put(SPLTypes.DDE_ERROR_STATUS, "docs/functions/dde_error_status.htm");
        resourcePathMap.put(SPLTypes.DDE_EXECUTE, "docs/functions/dde_execute.htm");
        resourcePathMap.put(SPLTypes.DDE_INITIATE, "docs/functions/dde_initiate.htm");
        resourcePathMap.put(SPLTypes.DDE_POKE, "docs/functions/dde_poke.htm");
        resourcePathMap.put(SPLTypes.DDE_REQUEST, "docs/functions/dde_request.htm");
        resourcePathMap.put(SPLTypes.DDE_TERMINATE, "docs/functions/dde_terminate.htm");
        resourcePathMap.put(SPLTypes.DECRYPT, "docs/functions/decrypt.htm");
        resourcePathMap.put(SPLTypes.DELETE_REGISTRY_VALUE, "docs/functions/delete_registry_value.htm");
        resourcePathMap.put(SPLTypes.DIR, "docs/functions/dir.htm");
        resourcePathMap.put(SPLTypes.DOW, "docs/functions/dow.htm");
        resourcePathMap.put(SPLTypes.ENABLE_STATUS_BAR, "docs/functions/enable_status_bar.htm");
        resourcePathMap.put(SPLTypes.ENABLE_SYSTEM_MENU, "docs/functions/enable_system_menu.htm");
        resourcePathMap.put(SPLTypes.ENABLE_TOOL_BAR, "docs/functions/enable_tool_bar.htm");
        resourcePathMap.put(SPLTypes.ENCRYPT, "docs/functions/encrypt.htm");
        resourcePathMap.put(SPLTypes.ERROR_DESCRIPTION, "docs/functions/error_description.htm");
        resourcePathMap.put(SPLTypes.ESCAPE, "docs/functions/escape.htm");
        resourcePathMap.put(SPLTypes.EXIT_STATUS, "docs/functions/exit_status.htm");
        resourcePathMap.put(SPLTypes.FILE_EXISTS, "docs/functions/file_exists.htm");
        resourcePathMap.put(SPLTypes.FILE_NAME, "docs/functions/file_name.htm");
        resourcePathMap.put(SPLTypes.FILE_OWNER, "docs/functions/file_owner.htm");
        resourcePathMap.put(SPLTypes.FILE_SIZE, "docs/functions/file_size.htm");
        resourcePathMap.put(SPLTypes.FILE_STATUS, "docs/functions/file_status.htm");
        resourcePathMap.put(SPLTypes.FILE_VERSION, "docs/functions/file_version.htm");
        resourcePathMap.put(SPLTypes.FIND_PARAMETER, "docs/functions/find_parameter.htm");
        resourcePathMap.put(SPLTypes.FINISH_DIR_SEARCH, "docs/functions/finish_dir_search.htm");
        resourcePathMap.put(SPLTypes.FORMATPICTURE, "docs/functions/format_picture.htm");
        resourcePathMap.put(SPLTypes.FRACTION, "docs/functions/fraction.htm");
        resourcePathMap.put(SPLTypes.FREEBLOB, "docs/functions/free_blob.htm");
        resourcePathMap.put(SPLTypes.FSTR, "docs/functions/fstr.htm");
        resourcePathMap.put(SPLTypes.GETENV, "docs/functions/get_env.htm");
        resourcePathMap.put(SPLTypes.GET_FIELD_VALUE, "docs/functions/get_field_value.htm");
        resourcePathMap.put(SPLTypes.GET_FIELD_VALUE_NUMERIC, "docs/functions/get_field_value_numeric.htm");
        resourcePathMap.put(SPLTypes.GET_FUNCTION_CODE, "docs/functions/get_function_code.htm");
        resourcePathMap.put(SPLTypes.GET_MODULE_CODE, "docs/functions/get_module_code.htm");
        resourcePathMap.put(SPLTypes.GET_PARAM, "docs/functions/get_param.htm");
        resourcePathMap.put(SPLTypes.GET_REGISTRY_ENUM_KEY, "docs/functions/get_registry_enum_key.htm");
        resourcePathMap.put(SPLTypes.GET_REGISTRY_ENUM_VALUE, "docs/functions/get_registry_enum_value.htm");
        resourcePathMap.put(SPLTypes.GET_REGISTRY_VALUE, "docs/functions/get_registry_value.htm");
        resourcePathMap.put(SPLTypes.GET_SYSTEM_METRICS, "docs/functions/get_system_metrics.htm");
        resourcePathMap.put(SPLTypes.GID, "docs/functions/gid.htm");
        resourcePathMap.put(SPLTypes.GMT, "docs/functions/gmt.htm");
        resourcePathMap.put(SPLTypes.GRANT_DB_SCHEMA, "docs/functions/grant_db_schema.htm");
        resourcePathMap.put(SPLTypes.HIDE_DOCKABLE_WINDOWS, "docs/functions/hide_dockable_windows.htm");
        resourcePathMap.put(SPLTypes.HOUR, "docs/functions/hour.htm");
        resourcePathMap.put(SPLTypes.IDX, "docs/functions/idx.htm");
        resourcePathMap.put(SPLTypes.IF_THEN_ELSE, "docs/functions/if_then_else.htm");
        resourcePathMap.put(SPLTypes.INTEGER, "docs/functions/integer.htm");
        resourcePathMap.put(SPLTypes.IO_COUNT, "docs/functions/io_count.htm");
        resourcePathMap.put(SPLTypes.IS_A_DIR, "docs/functions/is_a_dir.htm");
        resourcePathMap.put(SPLTypes.JULIAN, "docs/functions/julian.htm");
        resourcePathMap.put(SPLTypes.JULIAN_TO_DATE, "docs/functions/julian_to_date.htm");
        resourcePathMap.put(SPLTypes.LEAP_YEAR, "docs/functions/leap_year.htm");
        resourcePathMap.put(SPLTypes.LEFTJUSTIFY, "docs/functions/left_justify.htm");
        resourcePathMap.put(SPLTypes.LINE_NO, "docs/functions/line_no.htm");
        resourcePathMap.put(SPLTypes.LOCAL_CD, "docs/functions/local_cd.htm");
        resourcePathMap.put(SPLTypes.LOCAL_CD_WITHOUT_CLOSE_ALL, "docs/functions/local_cd_without_close_all.htm");
        resourcePathMap.put(SPLTypes.LOCAL_DIR, "docs/functions/local_dir.htm");
        resourcePathMap.put(SPLTypes.LOCAL_NO_AND_LOCAL_YES, "docs/functions/local_no_and_local_yes.htm");
        resourcePathMap.put(SPLTypes.LOGIN_ID, "docs/functions/login_id.htm");
        resourcePathMap.put(SPLTypes.LOWERCASE, "docs/functions/lowercase.htm");
        resourcePathMap.put(SPLTypes.LSHIFT, "docs/functions/lshift.htm");
        resourcePathMap.put(SPLTypes.LTRIM, "docs/functions/ltrim.htm");
        resourcePathMap.put(SPLTypes.MAIL_ADD_LINE, "docs/functions/mail_add_line.htm");
        resourcePathMap.put(SPLTypes.MAIL_ATTACH, "docs/functions/mail_attach.htm");
        resourcePathMap.put(SPLTypes.MAIL_CANCEL, "docs/functions/mail_cancel.htm");
        resourcePathMap.put(SPLTypes.MAIL_FROM_NAME, "docs/functions/mail_from_name.htm");
        resourcePathMap.put(SPLTypes.MAIL_REPLY_TO, "docs/functions/mail_reply_to.htm");
        resourcePathMap.put(SPLTypes.MAIL_SEND, "docs/functions/mail_send.htm");
        resourcePathMap.put(SPLTypes.MAIL_START, "docs/functions/mail_start.htm");
        resourcePathMap.put(SPLTypes.MAXALPHAVALUE, "docs/functions/max_alpha_value.htm");
        resourcePathMap.put(SPLTypes.MAX_PRESENTATION_VALUE, "docs/functions/max_presentation_value.htm");
        resourcePathMap.put(SPLTypes.MAX_SCREEN_COLUMNS, "docs/functions/max_screen_columns.htm");
        resourcePathMap.put(SPLTypes.MAX_SCREEN_ROWS, "docs/functions/max_screen_rows.htm");
        resourcePathMap.put(SPLTypes.MAX_VALUE, "docs/functions/max_value.htm");
        resourcePathMap.put(SPLTypes.MESSAGE_STATUS, "docs/functions/message_status.htm");
        resourcePathMap.put(SPLTypes.MIN_VALUE, "docs/functions/min_value.htm");
        resourcePathMap.put(SPLTypes.MINUTE, "docs/functions/minute.htm");
        resourcePathMap.put(SPLTypes.MKDIR, "docs/functions/mkdir.htm");
        resourcePathMap.put(SPLTypes.MODE_NAME, "docs/functions/mode_name.htm");
        resourcePathMap.put(SPLTypes.MODIFICATION_TIME, "docs/functions/modification_time.htm");
        resourcePathMap.put(SPLTypes.MONTH, "docs/functions/month.htm");
        resourcePathMap.put(SPLTypes.MONTH_NAME, "docs/functions/month_name.htm");
        resourcePathMap.put(SPLTypes.MOUSE_COLUMN, "docs/functions/mouse_column.htm");
        resourcePathMap.put(SPLTypes.MOUSE_ROW, "docs/functions/mouse_row.htm");
        resourcePathMap.put(SPLTypes.NEXT_DIR_ENTRY, "docs/functions/next_dir_entry.htm");
        resourcePathMap.put(SPLTypes.NODE_NAME, "docs/functions/node_name.htm");
        resourcePathMap.put(SPLTypes.NUM, "docs/functions/num.htm");
        resourcePathMap.put(SPLTypes.OCCURRENCE, "docs/functions/occurrence.htm");
        resourcePathMap.put(SPLTypes.OLE_ADDREF, "docs/functions/ole_addref.htm");
        resourcePathMap.put(SPLTypes.OLE_ADVISE_EVENT, "docs/functions/ole_advise_event.htm");
        resourcePathMap.put(SPLTypes.OLE_BULK_PUT, "docs/functions/ole_bulk_put.htm");
        resourcePathMap.put(SPLTypes.OLE_CALL_INTERACTIVE_METHOD, "docs/functions/ole_call_interactive_method.htm");
        resourcePathMap.put(SPLTypes.OLE_CALL_METHOD, "docs/functions/ole_call_method.htm");
        resourcePathMap.put(SPLTypes.OLE_CREATE_CONTROL, "docs/functions/ole_create_control.htm");
        resourcePathMap.put(SPLTypes.OLE_CREATE_INSTANCE, "docs/functions/ole_create_instance.htm");
        resourcePathMap.put(SPLTypes.OLE_ENUM_NEXT, "docs/functions/ole_enum_next.htm");
        resourcePathMap.put(SPLTypes.OLE_ENUM_RESET, "docs/functions/ole_enum_reset.htm");
        resourcePathMap.put(SPLTypes.OLE_ERROR_DESCRIPTION, "docs/functions/ole_error_description.htm");
        resourcePathMap.put(SPLTypes.OLE_GET_ACTIVE_OBJECT, "docs/functions/ole_get_active_object.htm");
        resourcePathMap.put(SPLTypes.OLE_GET_DISPATCH_ID, "docs/functions/ole_get_dispatch_id.htm");
        resourcePathMap.put(SPLTypes.OLE_GET_EVENT, "docs/functions/ole_get_event.htm");
        resourcePathMap.put(SPLTypes.OLE_GET_PROPERTY, "docs/functions/ole_get_property.htm");
        resourcePathMap.put(SPLTypes.OLE_PUT_PROPERTY, "docs/functions/ole_put_property.htm");
        resourcePathMap.put(SPLTypes.OLE_PUT_PROPERTY_BYREF, "docs/functions/ole_put_property_byref.htm");
        resourcePathMap.put(SPLTypes.OLE_QUERY_INTERFACE, "docs/functions/ole_query_interface.htm");
        resourcePathMap.put(SPLTypes.OLE_RELEASE, "docs/functions/ole_release.htm");
        resourcePathMap.put(SPLTypes.OLE_STATUS, "docs/functions/ole_status.htm");
        resourcePathMap.put(SPLTypes.OLE_UNADVISE_ALL, "docs/functions/ole_unadvise_all.htm");
        resourcePathMap.put(SPLTypes.OLE_UNADVISE_EVENT, "docs/functions/ole_unadvise_event.htm");
        resourcePathMap.put(SPLTypes.OPERATING_SYSTEM, "docs/functions/operating_system.htm");
        resourcePathMap.put(SPLTypes.PAGE_NO, "docs/functions/page_no.htm");
        resourcePathMap.put(SPLTypes.PARAM_CNT, "docs/functions/param_cnt.htm");
        resourcePathMap.put(SPLTypes.PARAMTEXT, "docs/functions/param_text.htm");
        resourcePathMap.put(SPLTypes.PATTERN, "docs/functions/pattern.htm");
        resourcePathMap.put(SPLTypes.PID, "docs/functions/pid.htm");
        resourcePathMap.put(SPLTypes.POWER_OF, "docs/functions/power_of.htm");
        resourcePathMap.put(SPLTypes.PRONTO_RELEASE, "docs/functions/pronto_release.htm");
        resourcePathMap.put(SPLTypes.PROUSER_FLAGS, "docs/functions/prouser_flags.htm");
        resourcePathMap.put(SPLTypes.RANDOM, "docs/functions/random.htm");
        resourcePathMap.put(SPLTypes.READBLOBFROMFILE, "docs/functions/read_blob_from_file.htm");
        resourcePathMap.put(SPLTypes.REFRESH_QUICK_LINKS, "docs/functions/refresh_quick_links.htm");
        resourcePathMap.put(SPLTypes.REPORT_IS_XML, "docs/functions/report_is_xml.htm");
        resourcePathMap.put(SPLTypes.RESERVED, "docs/functions/reserved.htm");
        resourcePathMap.put(SPLTypes.REVIEW_ROW, "docs/functions/review_row.htm");
        resourcePathMap.put(SPLTypes.REVOKE_DB_SCHEMA, "docs/functions/revoke_db_schema.htm");
        resourcePathMap.put(SPLTypes.RGB_TO_COLOUR, "docs/functions/rgb_to_colour.htm");
        resourcePathMap.put(SPLTypes.RIGHTJUSTIFY, "docs/functions/right_justify.htm");
        resourcePathMap.put(SPLTypes.RMDIR, "docs/functions/rmdir.htm");
        resourcePathMap.put(SPLTypes.RSHIFT, "docs/functions/rshift.htm");
        resourcePathMap.put(SPLTypes.RTRIM, "docs/functions/rtrim.htm");
        resourcePathMap.put(SPLTypes.SCREEN_MODE, "docs/functions/screen_mode.htm");
        resourcePathMap.put(SPLTypes.SEARCH, "docs/functions/search.htm");
        resourcePathMap.put(SPLTypes.SEARCH_MODE, "docs/functions/search_mode.htm");
        resourcePathMap.put(SPLTypes.SECOND, "docs/functions/second.htm");
        resourcePathMap.put(SPLTypes.SECURITY_LEVEL, "docs/functions/security_level.htm");
        resourcePathMap.put(SPLTypes.SET_APP_USER, "docs/functions/set_app_user.htm");
        resourcePathMap.put(SPLTypes.SET_BACKGROUND_IMAGE, "docs/functions/set_background_image.htm");
        resourcePathMap.put(SPLTypes.SET_BACKGROUND_URL, "docs/functions/set_background_url.htm");
        resourcePathMap.put(SPLTypes.SET_DATA_AREA_NAME, "docs/functions/set_data_area_name.htm");
        resourcePathMap.put(SPLTypes.SET_ENVIRONMENT, "docs/functions/set_environment.htm");
        resourcePathMap.put(SPLTypes.SET_FUNCTION_CODE, "docs/functions/set_function_code.htm");
        resourcePathMap.put(SPLTypes.SET_HELP_CONTEXT, "docs/functions/set_help_context.htm");
        resourcePathMap.put(SPLTypes.SET_KEYBOARD_FOCUS, "docs/functions/set_keyboard_focus.htm");
        resourcePathMap.put(SPLTypes.SET_MODULE_CODE, "docs/functions/set_module_code.htm");
        resourcePathMap.put(SPLTypes.SET_REGISTRY_VALUE, "docs/functions/set_registry_value.htm");
        resourcePathMap.put(SPLTypes.SET_WEB_WINDOW, "docs/functions/set_web_window.htm");
        resourcePathMap.put(SPLTypes.SIGN_DATA, "docs/functions/sign_data.htm");
        resourcePathMap.put(SPLTypes.SIGN_OF, "docs/functions/sign_of.htm");
        resourcePathMap.put(SPLTypes.SIN, "docs/functions/sin.htm");
        resourcePathMap.put(SPLTypes.SIZEOF, "docs/functions/size_of.htm");
        resourcePathMap.put(SPLTypes.SLEEP, "docs/functions/sleep.htm");
        resourcePathMap.put(SPLTypes.SMALLEST_INCREMENT, "docs/functions/smallest_increment.htm");
        resourcePathMap.put(SPLTypes.SPOOL_FILE_NAME, "docs/functions/spool_file_name.htm");
        resourcePathMap.put(SPLTypes.SQLSUBSTRING, "docs/functions/sql_substring.htm");
        resourcePathMap.put(SPLTypes.SQUARE_ROOT, "docs/functions/square_root.htm");
        resourcePathMap.put(SPLTypes.START_DIR_SEARCH, "docs/functions/start_dir_search.htm");
        resourcePathMap.put(SPLTypes.STR_FUNC, "docs/functions/str.htm");
        resourcePathMap.put(SPLTypes.STRCONCAT, "docs/functions/str_concat.htm");
        resourcePathMap.put(SPLTypes.STRLEN, "docs/functions/str_len.htm");
        resourcePathMap.put(SPLTypes.SUBSTRING, "docs/functions/sub_string.htm");
        resourcePathMap.put(SPLTypes.SUBSTRINGUTF8, "docs/functions/substring_utf8.htm");
        resourcePathMap.put(SPLTypes.SUM, "docs/functions/sum.htm");
        resourcePathMap.put(SPLTypes.SUM_ARRAY, "docs/functions/sum_array.htm");
        resourcePathMap.put(SPLTypes.SYSTIME, "docs/functions/systime.htm");
        resourcePathMap.put(SPLTypes.TAN, "docs/functions/tan.htm");
        resourcePathMap.put(SPLTypes.TIME_ELAPSED, "docs/functions/time_elapsed.htm");
        resourcePathMap.put(SPLTypes.TIME_FROM_DATE_TIME, "docs/functions/time_from_date_time.htm");
        resourcePathMap.put(SPLTypes.TIME_ZONE, "docs/functions/time_zone.htm");
        resourcePathMap.put(SPLTypes.TOD, "docs/functions/tod.htm");
        resourcePathMap.put(SPLTypes.TODAY, "docs/functions/today.htm");
        resourcePathMap.put(SPLTypes.TRANSACTION_ACTIVE, "docs/functions/transaction_active.htm");
        resourcePathMap.put(SPLTypes.TTY, "docs/functions/tty.htm");
        resourcePathMap.put(SPLTypes.UID, "docs/functions/uid.htm");
        resourcePathMap.put(SPLTypes.UPPERCASE, "docs/functions/uppercase.htm");
        resourcePathMap.put(SPLTypes.USER_GROUP, "docs/functions/user_group.htm");
        resourcePathMap.put(SPLTypes.VALID_ACTIVATION_KEY, "docs/functions/valid_activation_key.htm");
        resourcePathMap.put(SPLTypes.VALIDNUMBER, "docs/functions/valid_number.htm");
        resourcePathMap.put(SPLTypes.VERIFY_SIGNED_DATA, "docs/functions/verify_signed_data.htm");
        resourcePathMap.put(SPLTypes.WAIT_FOR_INPUT, "docs/functions/wait_for_input.htm");
        resourcePathMap.put(SPLTypes.WRITEBLOBTOFILE, "docs/functions/write_blob_to_file.htm");
        resourcePathMap.put(SPLTypes.XML_ADD_CHILD_NODE, "docs/functions/xml_add_child_node.htm");
        resourcePathMap.put(SPLTypes.XML_ADD_CHILD_NODE_BLOB, "docs/functions/xml_add_child_node_blob.htm");
        resourcePathMap.put(SPLTypes.XML_ADD_CHILD_NODE_CLOB, "docs/functions/xml_add_child_node_clob.htm");
        resourcePathMap.put(SPLTypes.XML_ADD_CHILD_NODE_NO_QUOTES, "docs/functions/xml_add_child_node_no_quotes.htm");
        resourcePathMap.put(SPLTypes.XML_ADD_CHILD_NODE_NUMBER, "docs/functions/xml_add_child_node_number.htm");
        resourcePathMap.put(SPLTypes.XML_ADD_CHILD_NODE_TEXT, "docs/functions/xml_add_child_node_text.htm");
        resourcePathMap.put(SPLTypes.XML_ADD_NODE_ATTRIBUTE, "docs/functions/xml_add_node_attribute.htm");
        resourcePathMap.put(SPLTypes.XML_ADD_NS, "docs/functions/xml_add_ns.htm");
        resourcePathMap.put(SPLTypes.XML_CHILD_NODE_BLOB, "docs/functions/xml_child_node_blob.htm");
        resourcePathMap.put(SPLTypes.XML_CHILD_NODE_CLOB, "docs/functions/xml_child_node_clob.htm");
        resourcePathMap.put(SPLTypes.XML_CHILD_NODE_TEXT, "docs/functions/xml_child_node_text.htm");
        resourcePathMap.put(SPLTypes.XML_CLOSE_DOCUMENT, "docs/functions/xml_close_document.htm");
        resourcePathMap.put(SPLTypes.XML_COPY_NODE_HANDLE, "docs/functions/xml_copy_node_handle.htm");
        resourcePathMap.put(SPLTypes.XML_DELETE_NODE, "docs/functions/xml_delete_node.htm");
        resourcePathMap.put(SPLTypes.XML_DELETE_NODE_ATTRIBUTE, "docs/functions/xml_delete_node_attribute.htm");
        resourcePathMap.put(SPLTypes.XML_DOCUMENT_IS_JSON, "docs/functions/xml_document_is_json.htm");
        resourcePathMap.put(SPLTypes.XML_FREE_NODE_HANDLE, "docs/functions/xml_free_node_handle.htm");
        resourcePathMap.put(SPLTypes.XML_GET_CHILD_BY_NAME, "docs/functions/xml_get_child_by_name.htm");
        resourcePathMap.put(SPLTypes.XML_GET_DOC_ENCODING, "docs/functions/xml_get_doc_encoding.htm");
        resourcePathMap.put(SPLTypes.XML_GET_FIRST_ATTRIBUTE_NAME, "docs/functions/xml_get_first_attribute_name.htm");
        resourcePathMap.put(SPLTypes.XML_GET_FIRST_CHILD_NODE, "docs/functions/xml_get_first_child_node.htm");
        resourcePathMap.put(SPLTypes.XML_GET_LAST_CHILD_NODE, "docs/functions/xml_get_last_child_node.htm");
        resourcePathMap.put(SPLTypes.XML_GET_NEXT_ATTRIBUTE_NAME, "docs/functions/xml_get_next_attribute_name.htm");
        resourcePathMap.put(SPLTypes.XML_GET_NEXT_NODE, "docs/functions/xml_get_next_node.htm");
        resourcePathMap.put(SPLTypes.XML_GET_NODE_ATTRIBUTE, "docs/functions/xml_get_node_attribute.htm");
        resourcePathMap.put(SPLTypes.XML_GET_NS_PREFIX_URL, "docs/functions/xml_get_ns_prefix_url.htm");
        resourcePathMap.put(SPLTypes.XML_GET_PREV_NODE, "docs/functions/xml_get_prev_node.htm");
        resourcePathMap.put(SPLTypes.XML_GET_ROOT_NODE, "docs/functions/xml_get_root_node.htm");
        resourcePathMap.put(SPLTypes.XML_MODIFY_NODE_ATTRIBUTE, "docs/functions/xml_modify_node_attribute.htm");
        resourcePathMap.put(SPLTypes.XML_MODIFY_NODE_TEXT, "docs/functions/xml_modify_node_text.htm");
        resourcePathMap.put(SPLTypes.XML_NEW_DOCUMENT, "docs/functions/xml_new_document.htm");
        resourcePathMap.put(SPLTypes.XML_NEXT_ELEMENT_SIBLING, "docs/functions/xml_next_element_sibling.htm");
        resourcePathMap.put(SPLTypes.XML_NODE_BLOB, "docs/functions/xml_node_blob.htm");
        resourcePathMap.put(SPLTypes.XML_NODE_CLOB, "docs/functions/xml_node_clob.htm");
        resourcePathMap.put(SPLTypes.XML_NODE_NAME, "docs/functions/xml_node_name.htm");
        resourcePathMap.put(SPLTypes.XML_NODE_NS_PREFIX, "docs/functions/xml_node_ns_prefix.htm");
        resourcePathMap.put(SPLTypes.XML_NODE_NS_URL, "docs/functions/xml_node_ns_url.htm");
        resourcePathMap.put(SPLTypes.XML_NODE_STRING, "docs/functions/xml_node_string.htm");
        resourcePathMap.put(SPLTypes.XML_NODE_TEXT, "docs/functions/xml_node_text.htm");
        resourcePathMap.put(SPLTypes.XML_NODE_TYPE, "docs/functions/xml_node_type.htm");
        resourcePathMap.put(SPLTypes.XML_PARSE_FILE, "docs/functions/xml_parse_file.htm");
        resourcePathMap.put(SPLTypes.XML_PARSE_TEXT, "docs/functions/xml_parse_text.htm");
        resourcePathMap.put(SPLTypes.XML_PREV_ELEMENT_SIBLING, "docs/functions/xml_prev_element_sibling.htm");
        resourcePathMap.put(SPLTypes.XML_SAVE_AS_FILE, "docs/functions/xml_save_as_file.htm");
        resourcePathMap.put(SPLTypes.XML_SAVE_AS_FILE_EX, "docs/functions/xml_save_as_file_ex.htm");
        resourcePathMap.put(SPLTypes.XML_SAVE_AS_TEXT, "docs/functions/xml_save_as_text.htm");
        resourcePathMap.put(SPLTypes.XML_SAVE_AS_TEXT_EX, "docs/functions/xml_save_as_text_ex.htm");
        resourcePathMap.put(SPLTypes.XML_SET_JSON_ARRAY, "docs/functions/xml_set_json_array.htm");
        resourcePathMap.put(SPLTypes.XML_VALIDATE_DOC, "docs/functions/xml_validate_doc.htm");
        resourcePathMap.put(SPLTypes.YEAR, "docs/functions/year.htm");
        resourcePathMap.put(SPLTypes.ZSTR, "docs/functions/zstr.htm");
    }

    public SPLDocumentationTarget(@NotNull PsiElement element, @Nullable PsiElement originalElement) {
        this.element = element;
        this.originalElement = originalElement;
    }

    @Override
    public @Nullable DocumentationResult computeDocumentation() {

        String resourcePath = null;
        IElementType elementType = element.getNode().getElementType();
        resourcePath = getResourcePath(elementType);
        if (resourcePath == null) {
            return null;
        }

        String documentationSource = loadDocumentationFromFile(resourcePath);
        if (documentationSource == null) {
            documentationSource = "<p>Error loading documentation.</p>";
        }
        String documentationContent = generateDoc(element, originalElement, documentationSource);
        if (documentationContent != null) {
            return DocumentationResult.documentation(documentationContent);
        }
        return null;
    }

    @Override
    public @Nullable String computeDocumentationHint() {
        return null;
        //return generateDocumentationHint(element);
    }

    @Override
    public @NotNull TargetPresentation computePresentation() {
        return TargetPresentation.builder(element.getText()).presentation();
    }

    @Override
    public @NotNull Pointer<? extends DocumentationTarget> createPointer() {
        return new Pointer<DocumentationTarget>() {
            @Override
            public DocumentationTarget dereference() {
                return new SPLDocumentationTarget(element, originalElement);
            }
        };
    }

    public @Nullable String generateDoc(PsiElement element, @Nullable PsiElement originalElement, @Nullable String documentationSource) {
        // Retrieve necessary details for documentation
        StringBuilder documentationBuilder = new StringBuilder();

        ArrayList<Map<String, String>> documentationSections = getDocumentationSections(documentationSource);
        if (documentationSections != null) {
            int x = 0;
            boolean dl_toptable = false;
            for (Map<String, String> section : documentationSections) {
                x++;
                String href = section.get("href");
                String text = section.get("text");
                //System.out.println("Link: " + href + ", Text: " + text);
                String content = extractContent(documentationSource, href, text);
                //First section is the description or dl_toptable
                if (x == 1) {
                    documentationBuilder.append(DocumentationMarkup.CONTENT_START);
                    documentationBuilder.append("<h1>").append(text).append("</h1>");
                    if (containselementdl_toptable(content)) {
                        dl_toptable = true;
                        elementdl_toptable(content, documentationBuilder);
                    } else {
                        documentationBuilder.append(content);
                    }
                    documentationBuilder.append(DocumentationMarkup.CONTENT_END);

                } else {
                    if (x == 2 && !dl_toptable) {
                        documentationBuilder.append(DocumentationMarkup.SECTIONS_START);
                    }
                    addKeyValueSection(text + ":", content, documentationBuilder);
                }
            }
            documentationBuilder.append(DocumentationMarkup.SECTIONS_END);
        }
        return documentationBuilder.toString();
    }

    private static @Nullable String getResourcePath(IElementType elementType) {
        return resourcePathMap.getOrDefault(elementType, null);
    }

    private void elementdl_toptable(String content, StringBuilder documentationBuilder) {
        Document doc = Jsoup.parse(content);

        // Get the dl element with class "dl_toptable"
        Elements dlElements = doc.select("dl.dl_toptable");

        for (Element dlElement : dlElements) {
            Elements children = dlElement.children(); // Get all child elements of the dl (dt and dd)

            // Iterate over child elements
            for (int i = 0; i < children.size(); i++) {
                Element element = children.get(i);

                // If the element is <dt>, check if the text matches and find the corresponding <dd>
                if (element.tagName().equals("dt")) {
                    String key = element.text();
                    Element valueElement = element.nextElementSibling(); // Get the next <dd> element

                    if (valueElement != null && valueElement.tagName().equals("dd")) {
                        String value = valueElement.text();

                        // Check if it's the description section
                        if (key.equals("Description")) {
                            documentationBuilder.append(value); // Append description text
                            documentationBuilder.append(DocumentationMarkup.CONTENT_END);
                        } else {
                            if (i == 2){
                                documentationBuilder.append(DocumentationMarkup.SECTIONS_START);
                            }
                            addKeyValueSection(key + ":", value, documentationBuilder);
                        }
                    }
                }
            }
        }
    }

    private boolean containselementdl_toptable(String content) {
        Document doc = Jsoup.parse(content);
        if (doc.select("dl.dl_toptable").size() > 0) {
            return true;
        }
        return false;
    }

    private String extractContent(@Nullable String documentationSource, String href, String text) {
        StringBuilder content = new StringBuilder();
        boolean capture = false; // Flag to start capturing content
        href = href.replace("#", "");

        try {
            Document doc = Jsoup.parse(documentationSource);
            Element mainContent = doc.getElementById("mc-main-content");

            if (mainContent != null) {
                Elements elements = mainContent.children(); // Get all children of the main content

                for (Element element : elements) {
                    // Start capturing if the current element has the same ID
                    if (element.id().equals(href)) {
                        capture = true; // Start capturing content
                        continue; // Move to the next element
                    }

                    // Stop capturing if the next element has class "nontoc" or "unnumbered_nontoc"
                    if (capture && (element.hasClass("nontoc") || element.hasClass("unnumbered_nontoc"))) {
                        break;
                    }

                    // If capturing, add the current element's HTML to the content
                    if (capture) {
                        removeJavaScript(element);
                        element.select("*.note").prepend(String.valueOf(HtmlChunk.tag("icon").attr("src", "AllIcons.Actions.RealIntentionBulb")));
                        element.select("*.tip").prepend(String.valueOf(HtmlChunk.tag("icon").attr("src", "AllIcons.Actions.IntentionBulb")));
                        element.select("*.restriction").prepend(String.valueOf(HtmlChunk.tag("icon").attr("src", "AllIcons.General.TodoImportant")));
                        element.select("*.important").prepend(String.valueOf(HtmlChunk.tag("icon").attr("src", "AllIcons.General.ExclMark")));
                        element.select("*.warning").prepend(String.valueOf(HtmlChunk.tag("icon").attr("src", "AllIcons.General.Warning")));
                        element.select("*.caution").prepend(String.valueOf(HtmlChunk.tag("icon").attr("src", "AllIcons.General.Warning")));
                        element.select("*.example").prepend(String.valueOf(HtmlChunk.tag("icon").attr("src", "AllIcons.General.Pin_tab")));
                        element.select("*.recommendation").prepend(String.valueOf(HtmlChunk.tag("icon").attr("src", "AllIcons.General.InspectionsOK")));
                        element.select("*.admin").prepend(String.valueOf(HtmlChunk.tag("icon").attr("src", "AllIcons.General.GearPlain")));
                        element.select("*.responsive").prepend(String.valueOf(HtmlChunk.tag("icon").attr("src", "AllIcons.General.FitContent")));

                        element.select("*.note, *.tip, *.restriction, *.important, *.warning, *.caution, *.example, *.recommendation, *.admin, *.responsive").attr("style",
                                "padding-top: 0.5em;" +
                                        "padding-bottom: 0.5em;" +
                                        "padding-left: 3em;" +
                                        "padding-right: 1em;" +
                                        "margin-top: 0.5em;" +
                                        "margin-bottom: 0.5em;" +
                                        "margin-left: 0.5em;" +
                                        "border-left-color: rgba(122, 126, 133, 0.2)" +
                                        "position: relative;" +
                                        "border-left-width: 5px;" +
                                        "border-left-style: solid;");

                        element.select("span.ui_control, span.uicontrol, span.uipath, span.ui_path, span.functionname, span.function_name," +
                                " span.field_name, span.fieldname, span.varname, span.list_item_subject, span.emdash, span.emdash_code, span.module," +
                                " span.emdash_field, span.screen_title, span.screentitle, span.wintitle, span.screenarea, span.screen_area, span.codetable," +
                                " span.code_table, span.keybd"
                        ).attr("style", "font-weight: bold;");

                        element.select("span.file_path, dbtable, db_table, span.envar, span.status, span.emphasis, span.role, cite"
                        ).attr("style", "font-style: italic;");

                        element.select("*.code_comment").attr("style", "color: #7A7E85;");
                        element.select("*.code_highlight").attr("style", "color: #56A8F5;");


                        //convert img src to base64 if file exists
                        Elements imgElements = element.select("img");
                        for (Element imgElement : imgElements) {
                            String src = imgElement.attr("src");
                            if (src != null && src.length() > 0) {
                                String imgPath = src.replace("../../_local/", "docs/");
                                ClassLoader classLoader = getClass().getClassLoader();
                                InputStream inputStream = classLoader.getResourceAsStream(imgPath);
                                if (inputStream != null) {
                                    byte[] bytes = IOUtils.toByteArray(inputStream);
                                    String base64 = Base64.getEncoder().encodeToString(bytes);
                                    imgElement.attr("src", "data:image/png;base64," + base64);
                                }
                            }
                        }

                        content.append(element.outerHtml());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "<p>Error extracting content.</p>";
        }

        // Return the content, or null if it's empty
        return content.isEmpty() ? null : content.toString();
    }

    private ArrayList<Map<String, String>> getDocumentationSections(@Nullable String documentationSource) {
        // Extract the sections from the documentation, getting the href and text from class "on-this-page _Skins__global_sidenav_side_menu mc-component"
        ArrayList<Map<String, String>> sections = new ArrayList<>();
        try {
            Document doc = Jsoup.parse(documentationSource);
            Element mainContent = doc;
            if (mainContent != null) {
                // Select the main ul element with the specific class
                Elements elements = mainContent.select("ul.on-this-page._Skins__global_sidenav_side_menu.mc-component");
                for (Element element : elements) {
                    // Get all anchor tags within the current ul (including nested uls)
                    Elements sectionElements = element.select("a");
                    for (Element sectionElement : sectionElements) {
                        String href = sectionElement.attr("href");
                        String text = sectionElement.text();

                        // Create a map for each section
                        Map<String, String> section = new HashMap<>();
                        section.put("href", href);
                        section.put("text", text);

                        // Add the section map to the sections list
                        sections.add(section);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return sections;
    }

    private void addKeyValueSection(String key, String value, StringBuilder sb) {
        sb.append(DocumentationMarkup.SECTION_HEADER_START);
        sb.append("<strong>").append(key).append("</strong>");
        sb.append(DocumentationMarkup.SECTION_SEPARATOR);
        sb.append("<p>").append(value).append(DocumentationMarkup.SECTION_END);
    }

    private String generateDocumentationHint(@NotNull PsiElement element) {
        return "Definition: A brief overview of '" + element.getText() + "'.";
    }

    private @Nullable String loadDocumentationFromFile(String resourcePath) {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(resourcePath); // No leading slash
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            if (inputStream == null) {
                return null;
            }

            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void removeJavaScript(Element element) {
        // Remove all 'onclick' and other event handler attributes
        element.select("*[onclick]").removeAttr("onclick");
        element.select("*[onmouseover]").removeAttr("onmouseover");
        element.select("*[onmouseout]").removeAttr("onmouseout");
        element.select("*[onmousedown]").removeAttr("onmousedown");
        element.select("*[onmouseup]").removeAttr("onmouseup");
        element.select("*[ondblclick]").removeAttr("ondblclick");
        element.select("*[oncontextmenu]").removeAttr("oncontextmenu");
        element.select("*[onfocus]").removeAttr("onfocus");
        element.select("*[onblur]").removeAttr("onblur");
        element.select("*[onkeypress]").removeAttr("onkeypress");
        element.select("*[madcap:conditions]").removeAttr("madcap:conditions");

        //replace xml:space="preserve" with html and add css white-space: pre
        element.select("*[xml:space=preserve]").removeAttr("xml:space").attr("style", "white-space: pre;");
        // If you want to remove all script elements from the document:
        element.select("script").remove();
    }
}
