package com.bamuel.spllang;

import com.bamuel.spllang.psi.SPLTypes;
import com.intellij.lang.documentation.DocumentationMarkup;
import com.intellij.psi.tree.IElementType;
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
import java.util.HashMap;
import java.util.Map;

public class SPLDocumentationTarget implements DocumentationTarget {

    private final PsiElement element;
    private final PsiElement originalElement;

    public SPLDocumentationTarget(@NotNull PsiElement element, @Nullable PsiElement originalElement) {
        this.element = element;
        this.originalElement = originalElement;
    }

    @Override
    public @Nullable DocumentationResult computeDocumentation() {

        String resourcePath = null;
        IElementType elementType = element.getNode().getElementType();
        if (elementType.equals(SPLTypes.API)) {
            resourcePath = "docs/declarations/api_declaration.htm";
        } else if (elementType.equals(SPLTypes.FIELD)) {
            resourcePath = "docs/declarations/field_declaration.htm";
        } else if (elementType.equals(SPLTypes.LOCAL)) {
            resourcePath = "docs/declarations/local_declaration.htm";
        } else if (elementType.equals(SPLTypes.MENU)) {
            resourcePath = "docs/declarations/menu_declaration.htm";
        } else if (elementType.equals(SPLTypes.MODE)) {
            resourcePath = "docs/declarations/mode_declaration.htm";
        } else if (elementType.equals(SPLTypes.OBJECT)) {
            resourcePath = "docs/declarations/object_declaration.htm";
        } else if (elementType.equals(SPLTypes.PARAMETERS)) {
            resourcePath = "docs/declarations/parameter_declaration.htm";
        } else if (elementType.equals(SPLTypes.PROCEDURE)) {
            resourcePath = "docs/declarations/procedure_declaration.htm";
        } else if (elementType.equals(SPLTypes.RETURNING)) {
            resourcePath = "docs/declarations/returning_declaration.htm";
        } else if (elementType.equals(SPLTypes.SCREEN)) {
            resourcePath = "docs/declarations/screen_declaration.htm";
        } else if (elementType.equals(SPLTypes.EXPORT)) {
            resourcePath = "docs/clauses/export_clause.htm";
        } else if (elementType.equals(SPLTypes.ABORT)) {
            resourcePath = "docs/statements/abort_statement.htm";
        } else if (elementType.equals(SPLTypes.ACCEPT)) {
            resourcePath = "docs/statements/accept_statement.htm";
        } else if (elementType.equals(SPLTypes.ACKNOWLEDGE)) {
            resourcePath = "docs/statements/acknowledge_statement.htm";
        } else if (elementType.equals(SPLTypes.AUDIT)) {
            resourcePath = "docs/statements/audit_statement.htm";
        } else if (elementType.equals(SPLTypes.BACK_TO_DETAIL)) {
            resourcePath = "docs/statements/back_to_detail_statement.htm";
        } else if (elementType.equals(SPLTypes.BEGINWORK_)) {
            resourcePath = "docs/statements/begin_work_statement.htm";
        } else if (elementType.equals(SPLTypes.BOX)) {
            resourcePath = "docs/statements/box_statement.htm";
        } else if (elementType.equals(SPLTypes.BREAK)) {
            resourcePath = "docs/statements/break_statement.htm";
        } else if (elementType.equals(SPLTypes.CALL)) {
            resourcePath = "docs/statements/call_statement.htm";
        } else if (elementType.equals(SPLTypes.CALL_URL)) {
            resourcePath = "docs/statements/call_url_statement.htm";
        } else if (elementType.equals(SPLTypes.CHECK_BOX)) {
            resourcePath = "docs/statements/check_box_statement.htm";
        } else if (elementType.equals(SPLTypes.CLEAR)) {
            resourcePath = "docs/statements/clear_statement.htm";
        } else if (elementType.equals(SPLTypes.CLOSE)) {
            resourcePath = "docs/statements/close_statement.htm";
        } else if (elementType.equals(SPLTypes.COMMAND)) {
            resourcePath = "docs/statements/command_statement.htm";
        } else if (elementType.equals(SPLTypes.COMMITWORK_)) {
            resourcePath = "docs/statements/commit_work_statement.htm";
        } else if (elementType.equals(SPLTypes.CONFIRM)) {
            resourcePath = "docs/statements/confirm_statement.htm";
        } else if (elementType.equals(SPLTypes.CONTINUE)) {
            resourcePath = "docs/statements/continue_statement.htm";
        } else if (elementType.equals(SPLTypes.CONTINUE_ENTRY)) {
            resourcePath = "docs/statements/continue_entry_statement.htm";
        } else if (elementType.equals(SPLTypes.DELETE)) {
            resourcePath = "docs/statements/delete_statement.htm";
        } else if (elementType.equals(SPLTypes.DISABLE_ALL_TRIGGERS)) {
            resourcePath = "docs/statements/disable_all_triggers_statement.htm";
        } else if (elementType.equals(SPLTypes.DISPLAY)) {
            resourcePath = "docs/statements/display_statement.htm";
        } else if (elementType.equals(SPLTypes.DO)) {
            resourcePath = "docs/statements/do_statement.htm";
        } else if (elementType.equals(SPLTypes.EXIT)) {
            resourcePath = "docs/statements/exit_statement.htm";
        } else if (elementType.equals(SPLTypes.EXTRACT)) {
            resourcePath = "docs/statements/extract_statement.htm";
        } else if (elementType.equals(SPLTypes.FOR)) {
            resourcePath = "docs/statements/for_statement.htm";
        } else if (elementType.equals(SPLTypes.GET)) {
            resourcePath = "docs/statements/get_statement.htm";
        } else if (elementType.equals(SPLTypes.IF)) {
            resourcePath = "docs/statements/if_statement.htm";
        } else if (elementType.equals(SPLTypes.INITIALISE)) {
            resourcePath = "docs/statements/initialise_statement.htm";
        } else if (elementType.equals(SPLTypes.INSERT)) {
            resourcePath = "docs/statements/insert_statement.htm";
        } else if (elementType.equals(SPLTypes.LINK)) {
            resourcePath = "docs/statements/link_statement.htm";
        } else if (elementType.equals(SPLTypes.LOCK_METHOD)) {
            resourcePath = "docs/statements/lock_method_statement.htm";
        } else if (elementType.equals(SPLTypes.MESSAGE)) {
            resourcePath = "docs/statements/message_statement.htm";
        } else if (elementType.equals(SPLTypes.MESSAGEBOX)) {
            resourcePath = "docs/statements/message_box_statement.htm";
        } else if (elementType.equals(SPLTypes.NEED)) {
            resourcePath = "docs/statements/need_statement.htm";
        } else if (elementType.equals(SPLTypes.OPEN)) {
            resourcePath = "docs/statements/open_statement.htm";
        } else if (elementType.equals(SPLTypes.OPTION)) {
            resourcePath = "docs/statements/option_statement.htm";
        } else if (elementType.equals(SPLTypes.PAGE)) {
            resourcePath = "docs/statements/page_statement.htm";
        } else if (elementType.equals(SPLTypes.PAUSE)) {
            resourcePath = "docs/statements/pause_statement.htm";
        } else if (elementType.equals(SPLTypes.POP)) {
            resourcePath = "docs/statements/pop_statement.htm";
        } else if (elementType.equals(SPLTypes.POSITION)) {
            resourcePath = "docs/statements/position_statement.htm";
        } else if (elementType.equals(SPLTypes.PRINT)) {
            resourcePath = "docs/statements/print_statement.htm";
        } else if (elementType.equals(SPLTypes.PUSH)) {
            resourcePath = "docs/statements/push_statement.htm";
        } else if (elementType.equals(SPLTypes.QUERY)) {
            resourcePath = "docs/statements/query_statement.htm";
        } else if (elementType.equals(SPLTypes.RADIO_BUTTON)) {
            resourcePath = "docs/statements/radio_button_statement.htm";
        } else if (elementType.equals(SPLTypes.RE_ENTER)) {
            resourcePath = "docs/statements/re_enter_statement.htm";
        } else if (elementType.equals(SPLTypes.REFRESH)) {
            resourcePath = "docs/statements/refresh_statement.htm";
        } else if (elementType.equals(SPLTypes.REPEAT)) {
            resourcePath = "docs/statements/repeat_statement.htm";
        } else if (elementType.equals(SPLTypes.REPORT)) {
            resourcePath = "docs/statements/report_statement.htm";
        } else if (elementType.equals(SPLTypes.REPORTSECTION_)) {
            resourcePath = "docs/statements/report_section_statement.htm.htm";
        } else if (elementType.equals(SPLTypes.RE_SELECT)) {
            resourcePath = "docs/statements/re_select_statement.htm";
        } else if (elementType.equals(SPLTypes.RESTORE)) {
            resourcePath = "docs/statements/restore_statement.htm";
        } else if (elementType.equals(SPLTypes.ROLLBACKWORK_)) {
            resourcePath = "docs/statements/rollback_work_statement.htm.htm";
        } else if (elementType.equals(SPLTypes.SAVE)) {
            resourcePath = "docs/statements/save_statement.htm";
        } else if (elementType.equals(SPLTypes.SELECT)) {
            resourcePath = "docs/statements/select_statement.htm";
        } else if (elementType.equals(SPLTypes.SERIAL)) {
            resourcePath = "docs/statements/serial_statement.htm";
        } else if (elementType.equals(SPLTypes.SET)) {
            resourcePath = "docs/statements/set_statement.htm";
        } else if (elementType.equals(SPLTypes.SET_DATE_VALIDATION)) {
            resourcePath = "docs/statements/set_date_validation_statement.htm";
        } else if (elementType.equals(SPLTypes.SKIP)) {
            resourcePath = "docs/statements/skip_statement.htm";
        } else if (elementType.equals(SPLTypes.SQL_DELETE)) {
            resourcePath = "docs/statements/sql_delete_statement.htm";
        } else if (elementType.equals(SPLTypes.SQL_UPDATE)) {
            resourcePath = "docs/statements/sql_update_statement.htm";
        } else if (elementType.equals(SPLTypes.STATEMENT_GROUP)) {
            resourcePath = "docs/statements/statement_group_statement.htm";
        } else if (elementType.equals(SPLTypes.STRINGSTATMENT)) {
            resourcePath = "docs/statements/string_statement.htm";
        } else if (elementType.equals(SPLTypes.SWITCH)) {
            resourcePath = "docs/statements/switch_statement.htm";
        } else if (elementType.equals(SPLTypes.TRANSACTION)) {
            resourcePath = "docs/statements/transaction_statement.htm";
        } else if (elementType.equals(SPLTypes.UNLOCK)) {
            resourcePath = "docs/statements/unlock_statement.htm";
        } else if (elementType.equals(SPLTypes.UPDATE)) {
            resourcePath = "docs/statements/update_statement.htm";
        } else if (elementType.equals(SPLTypes.VERSION_NUMBER)) {
            resourcePath = "docs/statements/version_number_statement.htm";
        } else if (elementType.equals(SPLTypes.WEB_CLIENT_LOCAL_AGENT)) {
            resourcePath = "docs/statements/web_client_local_agent_statement.htm";
        } else if (elementType.equals(SPLTypes.WHILE)) {
            resourcePath = "docs/statements/while_statement.htm";
        } else if (elementType.equals(SPLTypes.DROP_DOWN)) {
            resourcePath = "docs/statements/drop_down_statement.htm";
        } else if (elementType.equals(SPLTypes.FIELD_GROUP)) {
            resourcePath = "docs/statements/field_group_statement.htm";
        } else if (elementType.equals(SPLTypes.SCREEN_RESPONSIVE)) {
            resourcePath = "docs/clauses/responsive_clause.htm";
        } else if (elementType.equals(SPLTypes.SCREEN_GROUP)) {
            resourcePath = "docs/statements/screen_group_statement.htm";
        } else if (elementType.equals(SPLTypes.SCREEN_SECTION)) {
            resourcePath = "docs/statements/screen_section_statement.htm";
        } else if (elementType.equals(SPLTypes.AAND)) {
            resourcePath = "docs/functions/aand.htm";
        } else if (elementType.equals(SPLTypes.ABS)) {
            resourcePath = "docs/functions/abs.htm";
        } else if (elementType.equals(SPLTypes.ACTIVE_PID)) {
            resourcePath = "docs/functions/active_pid.htm";
        } else if (elementType.equals(SPLTypes.ADD_MONTH)) {
            resourcePath = "docs/functions/add_month.htm";
        } else if (elementType.equals(SPLTypes.ANOT)) {
            resourcePath = "docs/functions/anot.htm";
        } else if (elementType.equals(SPLTypes.AOR)) {
            resourcePath = "docs/functions/aor.htm";
        } else if (elementType.equals(SPLTypes.API_APPLICATION_NAME)) {
            resourcePath = "docs/functions/api_application_name.htm";
        } else if (elementType.equals(SPLTypes.ASCII_CHAR)) {
            resourcePath = "docs/functions/ascii_char.htm";
        } else if (elementType.equals(SPLTypes.ASCII_NUM)) {
            resourcePath = "docs/functions/ascii_num.htm";
        } else if (elementType.equals(SPLTypes.BASE64TOBLOB)) {
            resourcePath = "docs/functions/base64_to_blob.htm";
        } else if (elementType.equals(SPLTypes.BATCHED)) {
            resourcePath = "docs/functions/batched.htm";
        } else if (elementType.equals(SPLTypes.BLOBAPPEND)) {
            resourcePath = "docs/functions/blob_append.htm";
        } else if (elementType.equals(SPLTypes.BLOBAPPENDBASE64)) {
            resourcePath = "docs/functions/blob_append_base64.htm";
        } else if (elementType.equals(SPLTypes.BLOBSECTION)) {
            resourcePath = "docs/functions/blob_section.htm";
        } else if (elementType.equals(SPLTypes.BLOBTOBASE64)) {
            resourcePath = "docs/functions/blob_to_base64.htm";
        } else if (elementType.equals(SPLTypes.CAN_DDE)) {
            resourcePath = "docs/functions/can_dde.htm";
        } else if (elementType.equals(SPLTypes.CD)) {
            resourcePath = "docs/functions/cd.htm";
        } else if (elementType.equals(SPLTypes.CD_WITHOUT_CLOSE_ALL)) {
            resourcePath = "docs/functions/cd_without_close_all.htm";
        } else if (elementType.equals(SPLTypes.CHECK_AUTH)) {
            resourcePath = "docs/functions/check_auth.htm";
        } else if (elementType.equals(SPLTypes.CLIENT_DATE_TIME_STRING)) {
            resourcePath = "docs/functions/client_date_time_string.htm";
        } else if (elementType.equals(SPLTypes.CLIENT_FILE_BROWSE)) {
            resourcePath = "docs/functions/client_file_browse.htm";
        } else if (elementType.equals(SPLTypes.COLOUR_PICKER)) {
            resourcePath = "docs/functions/colour_picker.htm";
        } else if (elementType.equals(SPLTypes.CONCAT)) {
            resourcePath = "docs/functions/concat.htm";
        } else if (elementType.equals(SPLTypes.COS)) {
            resourcePath = "docs/functions/cos.htm";
        } else if (elementType.equals(SPLTypes.CRC32)) {
            resourcePath = "docs/functions/crc32.htm";
        } else if (elementType.equals(SPLTypes.CREATE_DB_SCHEMA)) {
            resourcePath = "docs/functions/create_db_schema.htm";
        } else if (elementType.equals(SPLTypes.CREATE_DB_USER)) {
            resourcePath = "docs/functions/create_db_user.htm";
        } else if (elementType.equals(SPLTypes.CURRENCY_SIGN)) {
            resourcePath = "docs/functions/currency_sign.htm";
        } else if (elementType.equals(SPLTypes.DATABASE_TYPE)) {
            resourcePath = "docs/functions/database_type.htm";
        } else if (elementType.equals(SPLTypes.DATE_FROM_DATE_TIME)) {
            resourcePath = "docs/functions/date_from_date_time.htm";
        } else if (elementType.equals(SPLTypes.DATE_TIME)) {
            resourcePath = "docs/functions/date_time.htm";
        } else if (elementType.equals(SPLTypes.DATE_TO_JULIAN)) {
            resourcePath = "docs/functions/date_to_julian.htm";
        } else if (elementType.equals(SPLTypes.DAY)) {
            resourcePath = "docs/functions/day.htm";
        } else if (elementType.equals(SPLTypes.DAY_NAME)) {
            resourcePath = "docs/functions/day_name.htm";
        } else if (elementType.equals(SPLTypes.DAYS_IN_MONTH)) {
            resourcePath = "docs/functions/days_in_month.htm";
        } else if (elementType.equals(SPLTypes.DB_COMMAND)) {
            resourcePath = "docs/functions/db_command.htm";
        } else if (elementType.equals(SPLTypes.DB_TABLE_NAME)) {
            resourcePath = "docs/functions/db_table_name.htm";
        } else if (elementType.equals(SPLTypes.DDE_ERROR_STATUS)) {
            resourcePath = "docs/functions/dde_error_status.htm";
        } else if (elementType.equals(SPLTypes.DDE_EXECUTE)) {
            resourcePath = "docs/functions/dde_execute.htm";
        } else if (elementType.equals(SPLTypes.DDE_INITIATE)) {
            resourcePath = "docs/functions/dde_initiate.htm";
        } else if (elementType.equals(SPLTypes.DDE_POKE)) {
            resourcePath = "docs/functions/dde_poke.htm";
        } else if (elementType.equals(SPLTypes.DDE_REQUEST)) {
            resourcePath = "docs/functions/dde_request.htm";
        } else if (elementType.equals(SPLTypes.DDE_TERMINATE)) {
            resourcePath = "docs/functions/dde_terminate.htm";
        } else if (elementType.equals(SPLTypes.DECRYPT)) {
            resourcePath = "docs/functions/decrypt.htm";
        } else if (elementType.equals(SPLTypes.DELETE_REGISTRY_VALUE)) {
            resourcePath = "docs/functions/delete_registry_value.htm";
        } else if (elementType.equals(SPLTypes.DIR)) {
            resourcePath = "docs/functions/dir.htm";
        } else if (elementType.equals(SPLTypes.DOW)) {
            resourcePath = "docs/functions/dow.htm";
        } else if (elementType.equals(SPLTypes.ENABLE_STATUS_BAR)) {
            resourcePath = "docs/functions/enable_status_bar.htm";
        } else if (elementType.equals(SPLTypes.ENABLE_SYSTEM_MENU)) {
            resourcePath = "docs/functions/enable_system_menu.htm";
        } else if (elementType.equals(SPLTypes.ENABLE_TOOL_BAR)) {
            resourcePath = "docs/functions/enable_tool_bar.htm";
        } else if (elementType.equals(SPLTypes.ENCRYPT)) {
            resourcePath = "docs/functions/encrypt.htm";
        } else if (elementType.equals(SPLTypes.ERROR_DESCRIPTION)) {
            resourcePath = "docs/functions/error_description.htm";
        } else if (elementType.equals(SPLTypes.ESCAPE)) {
            resourcePath = "docs/functions/escape.htm";
        } else if (elementType.equals(SPLTypes.EXIT_STATUS)) {
            resourcePath = "docs/functions/exit_status.htm";
        } else if (elementType.equals(SPLTypes.FILE_EXISTS)) {
            resourcePath = "docs/functions/file_exists.htm";
        } else if (elementType.equals(SPLTypes.FILE_NAME)) {
            resourcePath = "docs/functions/file_name.htm";
        } else if (elementType.equals(SPLTypes.FILE_OWNER)) {
            resourcePath = "docs/functions/file_owner.htm";
        } else if (elementType.equals(SPLTypes.FILE_SIZE)) {
            resourcePath = "docs/functions/file_size.htm";
        } else if (elementType.equals(SPLTypes.FILE_STATUS)) {
            resourcePath = "docs/functions/file_status.htm";
        } else if (elementType.equals(SPLTypes.FILE_VERSION)) {
            resourcePath = "docs/functions/file_version.htm";
        } else if (elementType.equals(SPLTypes.FIND_PARAMETER)) {
            resourcePath = "docs/functions/find_parameter.htm";
        } else if (elementType.equals(SPLTypes.FINISH_DIR_SEARCH)) {
            resourcePath = "docs/functions/finish_dir_search.htm";
        } else if (elementType.equals(SPLTypes.FORMATPICTURE)) {
            resourcePath = "docs/functions/format_picture.htm";
        } else if (elementType.equals(SPLTypes.FRACTION)) {
            resourcePath = "docs/functions/fraction.htm";
        } else if (elementType.equals(SPLTypes.FREEBLOB)) {
            resourcePath = "docs/functions/free_blob.htm";
        } else if (elementType.equals(SPLTypes.FSTR)) {
            resourcePath = "docs/functions/fstr.htm";
        } else if (elementType.equals(SPLTypes.GETENV)) {
            resourcePath = "docs/functions/get_env.htm";
        } else if (elementType.equals(SPLTypes.GET_FIELD_VALUE)) {
            resourcePath = "docs/functions/get_field_value.htm";
        } else if (elementType.equals(SPLTypes.GET_FIELD_VALUE_NUMERIC)) {
            resourcePath = "docs/functions/get_field_value_numeric.htm";
        } else if (elementType.equals(SPLTypes.GET_FUNCTION_CODE)) {
            resourcePath = "docs/functions/get_function_code.htm";
        } else if (elementType.equals(SPLTypes.GET_MODULE_CODE)) {
            resourcePath = "docs/functions/get_module_code.htm";
        } else if (elementType.equals(SPLTypes.GET_PARAM)) {
            resourcePath = "docs/functions/get_param.htm";
        } else if (elementType.equals(SPLTypes.GET_REGISTRY_ENUM_KEY)) {
            resourcePath = "docs/functions/get_registry_enum_key.htm";
        } else if (elementType.equals(SPLTypes.GET_REGISTRY_ENUM_VALUE)) {
            resourcePath = "docs/functions/get_registry_enum_value.htm";
        } else if (elementType.equals(SPLTypes.GET_REGISTRY_VALUE)) {
            resourcePath = "docs/functions/get_registry_value.htm";
        } else if (elementType.equals(SPLTypes.GET_SYSTEM_METRICS)) {
            resourcePath = "docs/functions/get_system_metrics.htm";
        } else if (elementType.equals(SPLTypes.GID)) {
            resourcePath = "docs/functions/gid.htm";
        } else if (elementType.equals(SPLTypes.GMT)) {
            resourcePath = "docs/functions/gmt.htm";
        } else if (elementType.equals(SPLTypes.GRANT_DB_SCHEMA)) {
            resourcePath = "docs/functions/grant_db_schema.htm";
        } else if (elementType.equals(SPLTypes.HIDE_DOCKABLE_WINDOWS)) {
            resourcePath = "docs/functions/hide_dockable_windows.htm";
        } else if (elementType.equals(SPLTypes.HOUR)) {
            resourcePath = "docs/functions/hour.htm";
        } else if (elementType.equals(SPLTypes.IDX)) {
            resourcePath = "docs/functions/idx.htm";
        } else if (elementType.equals(SPLTypes.IF_THEN_ELSE)) {
            resourcePath = "docs/functions/if_then_else.htm";
        } else if (elementType.equals(SPLTypes.INTEGER)) {
            resourcePath = "docs/functions/integer.htm";
        } else if (elementType.equals(SPLTypes.IO_COUNT)) {
            resourcePath = "docs/functions/io_count.htm";
        } else if (elementType.equals(SPLTypes.IS_A_DIR)) {
            resourcePath = "docs/functions/is_a_dir.htm";
        } else if (elementType.equals(SPLTypes.JULIAN)) {
            resourcePath = "docs/functions/julian.htm";
        } else if (elementType.equals(SPLTypes.JULIAN_TO_DATE)) {
            resourcePath = "docs/functions/julian_to_date.htm";
        } else if (elementType.equals(SPLTypes.LEAP_YEAR)) {
            resourcePath = "docs/functions/leap_year.htm";
        } else if (elementType.equals(SPLTypes.LEFTJUSTIFY)) {
            resourcePath = "docs/functions/left_justify.htm";
        } else if (elementType.equals(SPLTypes.LINE_NO)) {
            resourcePath = "docs/functions/line_no.htm";
        } else if (elementType.equals(SPLTypes.LOCAL_CD)) {
            resourcePath = "docs/functions/local_cd.htm";
        } else if (elementType.equals(SPLTypes.LOCAL_CD_WITHOUT_CLOSE_ALL)) {
            resourcePath = "docs/functions/local_cd_without_close_all.htm";
        } else if (elementType.equals(SPLTypes.LOCAL_DIR)) {
            resourcePath = "docs/functions/local_dir.htm";
        } else if (elementType.equals(SPLTypes.LOCAL_NO_AND_LOCAL_YES)) {
            resourcePath = "docs/functions/local_no_and_local_yes.htm";
        } else if (elementType.equals(SPLTypes.LOGIN_ID)) {
            resourcePath = "docs/functions/login_id.htm";
        } else if (elementType.equals(SPLTypes.LOWERCASE)) {
            resourcePath = "docs/functions/lowercase.htm";
        } else if (elementType.equals(SPLTypes.LSHIFT)) {
            resourcePath = "docs/functions/lshift.htm";
        } else if (elementType.equals(SPLTypes.LTRIM)) {
            resourcePath = "docs/functions/ltrim.htm";
        } else if (elementType.equals(SPLTypes.MAIL_ADD_LINE)) {
            resourcePath = "docs/functions/mail_add_line.htm";
        } else if (elementType.equals(SPLTypes.MAIL_ATTACH)) {
            resourcePath = "docs/functions/mail_attach.htm";
        } else if (elementType.equals(SPLTypes.MAIL_CANCEL)) {
            resourcePath = "docs/functions/mail_cancel.htm";
        } else if (elementType.equals(SPLTypes.MAIL_FROM_NAME)) {
            resourcePath = "docs/functions/mail_from_name.htm";
        } else if (elementType.equals(SPLTypes.MAIL_REPLY_TO)) {
            resourcePath = "docs/functions/mail_reply_to.htm";
        } else if (elementType.equals(SPLTypes.MAIL_SEND)) {
            resourcePath = "docs/functions/mail_send.htm";
        } else if (elementType.equals(SPLTypes.MAIL_START)) {
            resourcePath = "docs/functions/mail_start.htm";
        } else if (elementType.equals(SPLTypes.MAXALPHAVALUE)) {
            resourcePath = "docs/functions/max_alpha_value.htm";
        } else if (elementType.equals(SPLTypes.MAX_PRESENTATION_VALUE)) {
            resourcePath = "docs/functions/max_presentation_value.htm";
        } else if (elementType.equals(SPLTypes.MAX_SCREEN_COLUMNS)) {
            resourcePath = "docs/functions/max_screen_columns.htm";
        } else if (elementType.equals(SPLTypes.MAX_SCREEN_ROWS)) {
            resourcePath = "docs/functions/max_screen_rows.htm";
        } else if (elementType.equals(SPLTypes.MAX_VALUE)) {
            resourcePath = "docs/functions/max_value.htm";
        } else if (elementType.equals(SPLTypes.MESSAGE_STATUS)) {
            resourcePath = "docs/functions/message_status.htm";
        } else if (elementType.equals(SPLTypes.MIN_VALUE)) {
            resourcePath = "docs/functions/min_value.htm";
        } else if (elementType.equals(SPLTypes.MINUTE)) {
            resourcePath = "docs/functions/minute.htm";
        } else if (elementType.equals(SPLTypes.MKDIR)) {
            resourcePath = "docs/functions/mkdir.htm";
        } else if (elementType.equals(SPLTypes.MODE_NAME)) {
            resourcePath = "docs/functions/mode_name.htm";
        } else if (elementType.equals(SPLTypes.MODIFICATION_TIME)) {
            resourcePath = "docs/functions/modification_time.htm";
        } else if (elementType.equals(SPLTypes.MONTH)) {
            resourcePath = "docs/functions/month.htm";
        } else if (elementType.equals(SPLTypes.MONTH_NAME)) {
            resourcePath = "docs/functions/month_name.htm";
        } else if (elementType.equals(SPLTypes.MOUSE_COLUMN)) {
            resourcePath = "docs/functions/mouse_column.htm";
        } else if (elementType.equals(SPLTypes.MOUSE_ROW)) {
            resourcePath = "docs/functions/mouse_row.htm";
        } else if (elementType.equals(SPLTypes.NEXT_DIR_ENTRY)) {
            resourcePath = "docs/functions/next_dir_entry.htm";
        } else if (elementType.equals(SPLTypes.NODE_NAME)) {
            resourcePath = "docs/functions/node_name.htm";
        } else if (elementType.equals(SPLTypes.NUM)) {
            resourcePath = "docs/functions/num.htm";
        } else if (elementType.equals(SPLTypes.OCCURRENCE)) {
            resourcePath = "docs/functions/occurrence.htm";
        } else if (elementType.equals(SPLTypes.OLE_ADDREF)) {
            resourcePath = "docs/functions/ole_addref.htm";
        } else if (elementType.equals(SPLTypes.OLE_ADVISE_EVENT)) {
            resourcePath = "docs/functions/ole_advise_event.htm";
        } else if (elementType.equals(SPLTypes.OLE_BULK_PUT)) {
            resourcePath = "docs/functions/ole_bulk_put.htm";
        } else if (elementType.equals(SPLTypes.OLE_CALL_INTERACTIVE_METHOD)) {
            resourcePath = "docs/functions/ole_call_interactive_method.htm";
        } else if (elementType.equals(SPLTypes.OLE_CALL_METHOD)) {
            resourcePath = "docs/functions/ole_call_method.htm";
        } else if (elementType.equals(SPLTypes.OLE_CREATE_CONTROL)) {
            resourcePath = "docs/functions/ole_create_control.htm";
        } else if (elementType.equals(SPLTypes.OLE_CREATE_INSTANCE)) {
            resourcePath = "docs/functions/ole_create_instance.htm";
        } else if (elementType.equals(SPLTypes.OLE_ENUM_NEXT)) {
            resourcePath = "docs/functions/ole_enum_next.htm";
        } else if (elementType.equals(SPLTypes.OLE_ENUM_RESET)) {
            resourcePath = "docs/functions/ole_enum_reset.htm";
        } else if (elementType.equals(SPLTypes.OLE_ERROR_DESCRIPTION)) {
            resourcePath = "docs/functions/ole_error_description.htm";
        } else if (elementType.equals(SPLTypes.OLE_GET_ACTIVE_OBJECT)) {
            resourcePath = "docs/functions/ole_get_active_object.htm";
        } else if (elementType.equals(SPLTypes.OLE_GET_DISPATCH_ID)) {
            resourcePath = "docs/functions/ole_get_dispatch_id.htm";
        } else if (elementType.equals(SPLTypes.OLE_GET_EVENT)) {
            resourcePath = "docs/functions/ole_get_event.htm";
        } else if (elementType.equals(SPLTypes.OLE_GET_PROPERTY)) {
            resourcePath = "docs/functions/ole_get_property.htm";
        } else if (elementType.equals(SPLTypes.OLE_PUT_PROPERTY)) {
            resourcePath = "docs/functions/ole_put_property.htm";
        } else if (elementType.equals(SPLTypes.OLE_PUT_PROPERTY_BYREF)) {
            resourcePath = "docs/functions/ole_put_property_byref.htm";
        } else if (elementType.equals(SPLTypes.OLE_QUERY_INTERFACE)) {
            resourcePath = "docs/functions/ole_query_interface.htm";
        } else if (elementType.equals(SPLTypes.OLE_RELEASE)) {
            resourcePath = "docs/functions/ole_release.htm";
        } else if (elementType.equals(SPLTypes.OLE_STATUS)) {
            resourcePath = "docs/functions/ole_status.htm";
        } else if (elementType.equals(SPLTypes.OLE_UNADVISE_ALL)) {
            resourcePath = "docs/functions/ole_unadvise_all.htm";
        } else if (elementType.equals(SPLTypes.OLE_UNADVISE_EVENT)) {
            resourcePath = "docs/functions/ole_unadvise_event.htm";
        } else if (elementType.equals(SPLTypes.OPERATING_SYSTEM)) {
            resourcePath = "docs/functions/operating_system.htm";
        } else if (elementType.equals(SPLTypes.PAGE_NO)) {
            resourcePath = "docs/functions/page_no.htm";
        } else if (elementType.equals(SPLTypes.PARAM_CNT)) {
            resourcePath = "docs/functions/param_cnt.htm";
        } else if (elementType.equals(SPLTypes.PARAMTEXT)) {
            resourcePath = "docs/functions/param_text.htm";
        } else if (elementType.equals(SPLTypes.PATTERN)) {
            resourcePath = "docs/functions/pattern.htm";
        } else if (elementType.equals(SPLTypes.PID)) {
            resourcePath = "docs/functions/pid.htm";
        } else if (elementType.equals(SPLTypes.POWER_OF)) {
            resourcePath = "docs/functions/power_of.htm";
        } else if (elementType.equals(SPLTypes.PRONTO_RELEASE)) {
            resourcePath = "docs/functions/pronto_release.htm";
        } else if (elementType.equals(SPLTypes.PROUSER_FLAGS)) {
            resourcePath = "docs/functions/prouser_flags.htm";
        } else if (elementType.equals(SPLTypes.RANDOM)) {
            resourcePath = "docs/functions/random.htm";
        } else if (elementType.equals(SPLTypes.READBLOBFROMFILE)) {
            resourcePath = "docs/functions/read_blob_from_file.htm";
        } else if (elementType.equals(SPLTypes.REFRESH_QUICK_LINKS)) {
            resourcePath = "docs/functions/refresh_quick_links.htm";
        } else if (elementType.equals(SPLTypes.REPORT_IS_XML)) {
            resourcePath = "docs/functions/report_is_xml.htm";
        } else if (elementType.equals(SPLTypes.RESERVED)) {
            resourcePath = "docs/functions/reserved.htm";
        } else if (elementType.equals(SPLTypes.REVIEW_ROW)) {
            resourcePath = "docs/functions/review_row.htm";
        } else if (elementType.equals(SPLTypes.REVOKE_DB_SCHEMA)) {
            resourcePath = "docs/functions/revoke_db_schema.htm";
        } else if (elementType.equals(SPLTypes.RGB_TO_COLOUR)) {
            resourcePath = "docs/functions/rgb_to_colour.htm";
        } else if (elementType.equals(SPLTypes.RIGHTJUSTIFY)) {
            resourcePath = "docs/functions/right_justify.htm";
        } else if (elementType.equals(SPLTypes.RMDIR)) {
            resourcePath = "docs/functions/rmdir.htm";
        } else if (elementType.equals(SPLTypes.RSHIFT)) {
            resourcePath = "docs/functions/rshift.htm";
        } else if (elementType.equals(SPLTypes.RTRIM)) {
            resourcePath = "docs/functions/rtrim.htm";
        } else if (elementType.equals(SPLTypes.SCREEN_MODE)) {
            resourcePath = "docs/functions/screen_mode.htm";
        } else if (elementType.equals(SPLTypes.SEARCH)) {
            resourcePath = "docs/functions/search.htm";
        } else if (elementType.equals(SPLTypes.SEARCH_MODE)) {
            resourcePath = "docs/functions/search_mode.htm";
        } else if (elementType.equals(SPLTypes.SECOND)) {
            resourcePath = "docs/functions/second.htm";
        } else if (elementType.equals(SPLTypes.SECURITY_LEVEL)) {
            resourcePath = "docs/functions/security_level.htm";
        } else if (elementType.equals(SPLTypes.SET_APP_USER)) {
            resourcePath = "docs/functions/set_app_user.htm";
        } else if (elementType.equals(SPLTypes.SET_BACKGROUND_IMAGE)) {
            resourcePath = "docs/functions/set_background_image.htm";
        } else if (elementType.equals(SPLTypes.SET_BACKGROUND_URL)) {
            resourcePath = "docs/functions/set_background_url.htm";
        } else if (elementType.equals(SPLTypes.SET_DATA_AREA_NAME)) {
            resourcePath = "docs/functions/set_data_area_name.htm";
        } else if (elementType.equals(SPLTypes.SET_ENVIRONMENT)) {
            resourcePath = "docs/functions/set_environment.htm";
        } else if (elementType.equals(SPLTypes.SET_FUNCTION_CODE)) {
            resourcePath = "docs/functions/set_function_code.htm";
        } else if (elementType.equals(SPLTypes.SET_HELP_CONTEXT)) {
            resourcePath = "docs/functions/set_help_context.htm";
        } else if (elementType.equals(SPLTypes.SET_KEYBOARD_FOCUS)) {
            resourcePath = "docs/functions/set_keyboard_focus.htm";
        } else if (elementType.equals(SPLTypes.SET_MODULE_CODE)) {
            resourcePath = "docs/functions/set_module_code.htm";
        } else if (elementType.equals(SPLTypes.SET_REGISTRY_VALUE)) {
            resourcePath = "docs/functions/set_registry_value.htm";
        } else if (elementType.equals(SPLTypes.SET_WEB_WINDOW)) {
            resourcePath = "docs/functions/set_web_window.htm";
        } else if (elementType.equals(SPLTypes.SIGN_DATA)) {
            resourcePath = "docs/functions/sign_data.htm";
        } else if (elementType.equals(SPLTypes.SIGN_OF)) {
            resourcePath = "docs/functions/sign_of.htm";
        } else if (elementType.equals(SPLTypes.SIN)) {
            resourcePath = "docs/functions/sin.htm";
        } else if (elementType.equals(SPLTypes.SIZEOF)) {
            resourcePath = "docs/functions/size_of.htm";
        } else if (elementType.equals(SPLTypes.SLEEP)) {
            resourcePath = "docs/functions/sleep.htm";
        } else if (elementType.equals(SPLTypes.SMALLEST_INCREMENT)) {
            resourcePath = "docs/functions/smallest_increment.htm";
        } else if (elementType.equals(SPLTypes.SPOOL_FILE_NAME)) {
            resourcePath = "docs/functions/spool_file_name.htm";
        } else if (elementType.equals(SPLTypes.SQLSUBSTRING)) {
            resourcePath = "docs/functions/sql_substring.htm";
        } else if (elementType.equals(SPLTypes.SQUARE_ROOT)) {
            resourcePath = "docs/functions/square_root.htm";
        } else if (elementType.equals(SPLTypes.START_DIR_SEARCH)) {
            resourcePath = "docs/functions/start_dir_search.htm";
        } else if (elementType.equals(SPLTypes.STR_FUNC)) {
            resourcePath = "docs/functions/str.htm";
        } else if (elementType.equals(SPLTypes.STRCONCAT)) {
            resourcePath = "docs/functions/str_concat.htm";
        } else if (elementType.equals(SPLTypes.STRLEN)) {
            resourcePath = "docs/functions/str_len.htm";
        } else if (elementType.equals(SPLTypes.SUBSTRING)) {
            resourcePath = "docs/functions/sub_string.htm";
        } else if (elementType.equals(SPLTypes.SUBSTRINGUTF8)) {
            resourcePath = "docs/functions/substring_utf8.htm";
        } else if (elementType.equals(SPLTypes.SUM)) {
            resourcePath = "docs/functions/sum.htm";
        } else if (elementType.equals(SPLTypes.SUM_ARRAY)) {
            resourcePath = "docs/functions/sum_array.htm";
        } else if (elementType.equals(SPLTypes.SYSTIME)) {
            resourcePath = "docs/functions/systime.htm";
        } else if (elementType.equals(SPLTypes.TAN)) {
            resourcePath = "docs/functions/tan.htm";
        } else if (elementType.equals(SPLTypes.TIME_ELAPSED)) {
            resourcePath = "docs/functions/time_elapsed.htm";
        } else if (elementType.equals(SPLTypes.TIME_FROM_DATE_TIME)) {
            resourcePath = "docs/functions/time_from_date_time.htm";
        } else if (elementType.equals(SPLTypes.TIME_ZONE)) {
            resourcePath = "docs/functions/time_zone.htm";
        } else if (elementType.equals(SPLTypes.TOD)) {
            resourcePath = "docs/functions/tod.htm";
        } else if (elementType.equals(SPLTypes.TODAY)) {
            resourcePath = "docs/functions/today.htm";
        } else if (elementType.equals(SPLTypes.TRANSACTION_ACTIVE)) {
            resourcePath = "docs/functions/transaction_active.htm";
        } else if (elementType.equals(SPLTypes.TTY)) {
            resourcePath = "docs/functions/tty.htm";
        } else if (elementType.equals(SPLTypes.UID)) {
            resourcePath = "docs/functions/uid.htm";
        } else if (elementType.equals(SPLTypes.UPPERCASE)) {
            resourcePath = "docs/functions/uppercase.htm";
        } else if (elementType.equals(SPLTypes.USER_GROUP)) {
            resourcePath = "docs/functions/user_group.htm";
        } else if (elementType.equals(SPLTypes.VALID_ACTIVATION_KEY)) {
            resourcePath = "docs/functions/valid_activation_key.htm";
        } else if (elementType.equals(SPLTypes.VALIDNUMBER)) {
            resourcePath = "docs/functions/valid_number.htm";
        } else if (elementType.equals(SPLTypes.VERIFY_SIGNED_DATA)) {
            resourcePath = "docs/functions/verify_signed_data.htm";
        } else if (elementType.equals(SPLTypes.WAIT_FOR_INPUT)) {
            resourcePath = "docs/functions/wait_for_input.htm";
        } else if (elementType.equals(SPLTypes.WRITEBLOBTOFILE)) {
            resourcePath = "docs/functions/write_blob_to_file.htm";
        } else if (elementType.equals(SPLTypes.XML_ADD_CHILD_NODE)) {
            resourcePath = "docs/functions/xml_add_child_node.htm";
        } else if (elementType.equals(SPLTypes.XML_ADD_CHILD_NODE_BLOB)) {
            resourcePath = "docs/functions/xml_add_child_node_blob.htm";
        } else if (elementType.equals(SPLTypes.XML_ADD_CHILD_NODE_CLOB)) {
            resourcePath = "docs/functions/xml_add_child_node_clob.htm";
        } else if (elementType.equals(SPLTypes.XML_ADD_CHILD_NODE_NO_QUOTES)) {
            resourcePath = "docs/functions/xml_add_child_node_no_quotes.htm";
        } else if (elementType.equals(SPLTypes.XML_ADD_CHILD_NODE_NUMBER)) {
            resourcePath = "docs/functions/xml_add_child_node_number.htm";
        } else if (elementType.equals(SPLTypes.XML_ADD_CHILD_NODE_TEXT)) {
            resourcePath = "docs/functions/xml_add_child_node_text.htm";
        } else if (elementType.equals(SPLTypes.XML_ADD_NODE_ATTRIBUTE)) {
            resourcePath = "docs/functions/xml_add_node_attribute.htm";
        } else if (elementType.equals(SPLTypes.XML_ADD_NS)) {
            resourcePath = "docs/functions/xml_add_ns.htm";
        } else if (elementType.equals(SPLTypes.XML_CHILD_NODE_BLOB)) {
            resourcePath = "docs/functions/xml_child_node_blob.htm";
        } else if (elementType.equals(SPLTypes.XML_CHILD_NODE_CLOB)) {
            resourcePath = "docs/functions/xml_child_node_clob.htm";
        } else if (elementType.equals(SPLTypes.XML_CHILD_NODE_TEXT)) {
            resourcePath = "docs/functions/xml_child_node_text.htm";
        } else if (elementType.equals(SPLTypes.XML_CLOSE_DOCUMENT)) {
            resourcePath = "docs/functions/xml_close_document.htm";
        } else if (elementType.equals(SPLTypes.XML_COPY_NODE_HANDLE)) {
            resourcePath = "docs/functions/xml_copy_node_handle.htm";
        } else if (elementType.equals(SPLTypes.XML_DELETE_NODE)) {
            resourcePath = "docs/functions/xml_delete_node.htm";
        } else if (elementType.equals(SPLTypes.XML_DELETE_NODE_ATTRIBUTE)) {
            resourcePath = "docs/functions/xml_delete_node_attribute.htm";
        } else if (elementType.equals(SPLTypes.XML_DOCUMENT_IS_JSON)) {
            resourcePath = "docs/functions/xml_document_is_json.htm";
        } else if (elementType.equals(SPLTypes.XML_FREE_NODE_HANDLE)) {
            resourcePath = "docs/functions/xml_free_node_handle.htm";
        } else if (elementType.equals(SPLTypes.XML_GET_CHILD_BY_NAME)) {
            resourcePath = "docs/functions/xml_get_child_by_name.htm";
        } else if (elementType.equals(SPLTypes.XML_GET_DOC_ENCODING)) {
            resourcePath = "docs/functions/xml_get_doc_encoding.htm";
        } else if (elementType.equals(SPLTypes.XML_GET_FIRST_ATTRIBUTE_NAME)) {
            resourcePath = "docs/functions/xml_get_first_attribute_name.htm";
        } else if (elementType.equals(SPLTypes.XML_GET_FIRST_CHILD_NODE)) {
            resourcePath = "docs/functions/xml_get_first_child_node.htm";
        } else if (elementType.equals(SPLTypes.XML_GET_LAST_CHILD_NODE)) {
            resourcePath = "docs/functions/xml_get_last_child_node.htm";
        } else if (elementType.equals(SPLTypes.XML_GET_NEXT_ATTRIBUTE_NAME)) {
            resourcePath = "docs/functions/xml_get_next_attribute_name.htm";
        } else if (elementType.equals(SPLTypes.XML_GET_NEXT_NODE)) {
            resourcePath = "docs/functions/xml_get_next_node.htm";
        } else if (elementType.equals(SPLTypes.XML_GET_NODE_ATTRIBUTE)) {
            resourcePath = "docs/functions/xml_get_node_attribute.htm";
        } else if (elementType.equals(SPLTypes.XML_GET_NS_PREFIX_URL)) {
            resourcePath = "docs/functions/xml_get_ns_prefix_url.htm";
        } else if (elementType.equals(SPLTypes.XML_GET_PREV_NODE)) {
            resourcePath = "docs/functions/xml_get_prev_node.htm";
        } else if (elementType.equals(SPLTypes.XML_GET_ROOT_NODE)) {
            resourcePath = "docs/functions/xml_get_root_node.htm";
        } else if (elementType.equals(SPLTypes.XML_MODIFY_NODE_ATTRIBUTE)) {
            resourcePath = "docs/functions/xml_modify_node_attribute.htm";
        } else if (elementType.equals(SPLTypes.XML_MODIFY_NODE_TEXT)) {
            resourcePath = "docs/functions/xml_modify_node_text.htm";
        } else if (elementType.equals(SPLTypes.XML_NEW_DOCUMENT)) {
            resourcePath = "docs/functions/xml_new_document.htm";
        } else if (elementType.equals(SPLTypes.XML_NEXT_ELEMENT_SIBLING)) {
            resourcePath = "docs/functions/xml_next_element_sibling.htm";
        } else if (elementType.equals(SPLTypes.XML_NODE_BLOB)) {
            resourcePath = "docs/functions/xml_node_blob.htm";
        } else if (elementType.equals(SPLTypes.XML_NODE_CLOB)) {
            resourcePath = "docs/functions/xml_node_clob.htm";
        } else if (elementType.equals(SPLTypes.XML_NODE_NAME)) {
            resourcePath = "docs/functions/xml_node_name.htm";
        } else if (elementType.equals(SPLTypes.XML_NODE_NS_PREFIX)) {
            resourcePath = "docs/functions/xml_node_ns_prefix.htm";
        } else if (elementType.equals(SPLTypes.XML_NODE_NS_URL)) {
            resourcePath = "docs/functions/xml_node_ns_url.htm";
        } else if (elementType.equals(SPLTypes.XML_NODE_STRING)) {
            resourcePath = "docs/functions/xml_node_string.htm";
        } else if (elementType.equals(SPLTypes.XML_NODE_TEXT)) {
            resourcePath = "docs/functions/xml_node_text.htm";
        } else if (elementType.equals(SPLTypes.XML_NODE_TYPE)) {
            resourcePath = "docs/functions/xml_node_type.htm";
        } else if (elementType.equals(SPLTypes.XML_PARSE_FILE)) {
            resourcePath = "docs/functions/xml_parse_file.htm";
        } else if (elementType.equals(SPLTypes.XML_PARSE_TEXT)) {
            resourcePath = "docs/functions/xml_parse_text.htm";
        } else if (elementType.equals(SPLTypes.XML_PREV_ELEMENT_SIBLING)) {
            resourcePath = "docs/functions/xml_prev_element_sibling.htm";
        } else if (elementType.equals(SPLTypes.XML_SAVE_AS_FILE)) {
            resourcePath = "docs/functions/xml_save_as_file.htm";
        } else if (elementType.equals(SPLTypes.XML_SAVE_AS_FILE_EX)) {
            resourcePath = "docs/functions/xml_save_as_file_ex.htm";
        } else if (elementType.equals(SPLTypes.XML_SAVE_AS_TEXT)) {
            resourcePath = "docs/functions/xml_save_as_text.htm";
        } else if (elementType.equals(SPLTypes.XML_SAVE_AS_TEXT_EX)) {
            resourcePath = "docs/functions/xml_save_as_text_ex.htm";
        } else if (elementType.equals(SPLTypes.XML_SET_JSON_ARRAY)) {
            resourcePath = "docs/functions/xml_set_json_array.htm";
        } else if (elementType.equals(SPLTypes.XML_VALIDATE_DOC)) {
            resourcePath = "docs/functions/xml_validate_doc.htm";
        } else if (elementType.equals(SPLTypes.YEAR)) {
            resourcePath = "docs/functions/year.htm";
        } else if (elementType.equals(SPLTypes.ZSTR)) {
            resourcePath = "docs/functions/zstr.htm";
        } else {
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
            for (Map<String, String> section : documentationSections) {
                x++;
                String href = section.get("href");
                String text = section.get("text");
                //System.out.println("Link: " + href + ", Text: " + text);
                String content = extractContent(documentationSource, href, text);
                //First section is the description or dl_toptable
                if (x == 1) {
                    documentationBuilder.append(DocumentationMarkup.CONTENT_START);
                    documentationBuilder.append("<h1>").append(text);
                    if (containselementdl_toptable(content)) {
                        elementdl_toptable(content, documentationBuilder);
                    } else {
                        documentationBuilder.append(content);
                    }
                    documentationBuilder.append(DocumentationMarkup.CONTENT_END);

                } else {
                    if (x == 2) {
                        documentationBuilder.append(DocumentationMarkup.SECTIONS_START);
                    }
                    addKeyValueSection(text + ":", content, documentationBuilder);
                }
            }
            documentationBuilder.append(DocumentationMarkup.SECTIONS_END);
        }
        return documentationBuilder.toString();
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
                        } else {
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
                        element.select("p.note").prepend(String.valueOf(DocumentationMarkup.INFORMATION_ICON));
                        element.select("p.important").prepend(String.valueOf(DocumentationMarkup.INFORMATION_ICON));
                        element.select("p.restriction").prepend(String.valueOf(DocumentationMarkup.INFORMATION_ICON));

                        element.select("*.keybd").tagName("strong");
                        element.select("*.function_name").tagName("strong");
                        element.select("*.emphasis").tagName("em");

                        element.select("*.code_comment").attr("style", "color: #7A7E85;");
                        element.select("*.code_highlight").attr("style", "color: #56A8F5;");
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

    private String getDescription(String documentationSource) {
        StringBuilder description = new StringBuilder(); // Use StringBuilder
        try {
            Document doc = Jsoup.parse(documentationSource);
            Element mainContent = doc.getElementById("mc-main-content");
            if (mainContent != null) {
                Elements elements = mainContent.children();
                for (Element element : elements) {
                    // This only apply to function documentation
                    if (element.tagName().equals("dl") && element.hasClass("dl_toptable")) {
                        element = element.select("dt:contains(Description)").first();
                        if (element != null) {
                            element = element.nextElementSibling();
                            description.append("<p>").append(element.outerHtml()).append("</p>");
                            break;
                        }
                    }

                    element.select(".MCExpandingBody").remove();

                    //remove JS and tags
                    removeJavaScript(element);
                    element.select("a[href]").removeAttr("href");
                    element.select("a").tagName("span");

                    //put the contents of the p.note, p.important, p.restriction in a span with the icon
                    element.select("p.note").prepend(String.valueOf(DocumentationMarkup.INFORMATION_ICON));
                    element.select("p.important").prepend(String.valueOf(DocumentationMarkup.INFORMATION_ICON));
                    element.select("p.restriction").prepend(String.valueOf(DocumentationMarkup.INFORMATION_ICON));
                    element.select("*.code_comment").attr("style", "color: #7A7E85;");
                    element.select("*.code_highlight").attr("style", "color: #56A8F5;");

                    if ((element.hasClass("nontoc") || element.hasClass("unnumbered_nontoc"))) {
                        break;
                    }
                    description.append(element.outerHtml());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "<p>Error extracting description.</p>";
        }
        // Return the description, or a fallback message if it's empty
        return description.isEmpty() ? null : description.toString();
    }

    // Helper method to load the HTML content from the resources directory
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

    // Method to remove JavaScript and event handlers from an element
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
