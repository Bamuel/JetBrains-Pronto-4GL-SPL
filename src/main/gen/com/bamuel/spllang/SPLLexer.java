// Generated by JFlex 1.9.1 http://jflex.de/  (tweaked for IntelliJ platform)
// source: SPL.flex

package com.bamuel.spllang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.bamuel.spllang.psi.SPLTypes;
import com.intellij.psi.TokenType;


class SPLLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0, 0
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\u10ff\u0100";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\1\0\1\1\1\3\22\0\1\1"+
    "\1\0\1\4\4\0\1\5\2\0\1\6\2\0\1\7"+
    "\1\0\1\10\12\11\1\0\1\12\5\0\1\13\1\14"+
    "\1\15\1\14\1\16\1\17\5\14\1\20\1\14\1\21"+
    "\1\22\1\23\1\14\1\24\1\25\1\26\1\27\3\14"+
    "\1\30\1\31\4\0\1\14\1\0\1\32\1\33\1\34"+
    "\1\35\1\36\1\37\1\40\1\41\1\42\1\43\1\44"+
    "\1\45\1\46\1\47\1\50\1\51\1\14\1\52\1\53"+
    "\1\54\1\55\1\56\1\14\1\57\1\60\1\14\u0185\0";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[512];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\3\1\1\3\1\4\25\5\1\0"+
    "\1\6\1\0\1\7\1\10\1\5\1\11\33\5\1\7"+
    "\5\5\1\12\26\5\1\7\31\5\1\11\34\5";

  private static int [] zzUnpackAction() {
    int [] result = new int[147];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\61\0\142\0\223\0\304\0\365\0\u0126\0\61"+
    "\0\u0157\0\u0188\0\u01b9\0\u01ea\0\u021b\0\u024c\0\u027d\0\u02ae"+
    "\0\u02df\0\u0310\0\u0341\0\u0372\0\u03a3\0\u03d4\0\u0405\0\u0436"+
    "\0\u0467\0\u0498\0\u04c9\0\u04fa\0\u052b\0\223\0\61\0\304"+
    "\0\u055c\0\u058d\0\u05be\0\u0157\0\u05ef\0\u0620\0\u0651\0\u0682"+
    "\0\u06b3\0\u06e4\0\u0715\0\u0746\0\u0777\0\u07a8\0\u07d9\0\u080a"+
    "\0\u083b\0\u086c\0\u089d\0\u08ce\0\u08ff\0\u0930\0\u0961\0\u0992"+
    "\0\u09c3\0\u09f4\0\u0a25\0\u0a56\0\u0a87\0\u0ab8\0\u0ae9\0\u0b1a"+
    "\0\u0b4b\0\u0b7c\0\u0bad\0\u0bde\0\u0c0f\0\u0157\0\u0c40\0\u0c71"+
    "\0\u0ca2\0\u0cd3\0\u0d04\0\u0d35\0\u0d66\0\u0d97\0\u0dc8\0\u0df9"+
    "\0\u0e2a\0\u0e5b\0\u0e8c\0\u0ebd\0\u0eee\0\u0f1f\0\u0f50\0\u0f81"+
    "\0\u0fb2\0\u0fe3\0\u1014\0\u1045\0\61\0\u1076\0\u10a7\0\u10d8"+
    "\0\u1109\0\u113a\0\u116b\0\u119c\0\u11cd\0\u11fe\0\u122f\0\u1260"+
    "\0\u1291\0\u12c2\0\u12f3\0\u1324\0\u1355\0\u1386\0\u13b7\0\u13e8"+
    "\0\u1419\0\u144a\0\u147b\0\u14ac\0\u14dd\0\u150e\0\u153f\0\u1570"+
    "\0\u15a1\0\u15d2\0\u1603\0\u1634\0\u1665\0\u1696\0\u16c7\0\u16f8"+
    "\0\u1729\0\u175a\0\u178b\0\u17bc\0\u17ed\0\u181e\0\u184f\0\u1880"+
    "\0\u18b1\0\u18e2\0\u1913\0\u1944\0\u1975\0\u19a6\0\u19d7\0\u1a08"+
    "\0\u1a39\0\u1a6a\0\u1a9b";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[147];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length() - 1;
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpacktrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\2\3\1\2\1\4\1\5\2\2\1\6\1\7"+
    "\1\10\4\11\1\12\1\11\1\13\3\11\1\14\1\15"+
    "\1\11\1\16\1\17\1\20\1\21\1\11\1\22\1\23"+
    "\1\24\1\25\1\11\1\26\2\11\1\27\1\30\1\11"+
    "\1\31\1\32\1\33\1\34\2\11\1\35\2\11\62\0"+
    "\2\3\56\0\4\36\1\37\54\36\5\40\1\37\53\40"+
    "\6\0\1\41\1\0\1\42\61\0\1\7\56\0\1\11"+
    "\1\0\1\11\1\0\46\11\7\0\1\11\1\0\1\11"+
    "\1\0\1\43\45\11\7\0\1\11\1\0\1\11\1\0"+
    "\7\11\1\44\36\11\7\0\1\11\1\0\1\11\1\0"+
    "\10\11\1\45\35\11\7\0\1\11\1\0\1\11\1\0"+
    "\11\11\1\46\34\11\7\0\1\11\1\0\1\11\1\0"+
    "\3\11\1\47\42\11\7\0\1\11\1\0\1\11\1\0"+
    "\3\11\1\50\42\11\7\0\1\11\1\0\1\11\1\0"+
    "\21\11\1\51\2\11\1\52\11\11\1\53\7\11\7\0"+
    "\1\11\1\0\1\11\1\0\23\11\1\54\13\11\1\55"+
    "\6\11\7\0\1\11\1\0\1\11\1\0\23\11\1\56"+
    "\3\11\1\57\5\11\1\44\10\11\7\0\1\11\1\0"+
    "\1\11\1\0\34\11\1\60\7\11\1\61\1\11\7\0"+
    "\1\11\1\0\1\11\1\0\27\11\1\62\16\11\7\0"+
    "\1\11\1\0\1\11\1\0\23\11\1\63\22\11\7\0"+
    "\1\11\1\0\1\11\1\0\34\11\1\64\11\11\7\0"+
    "\1\11\1\0\1\11\1\0\27\11\1\65\5\11\1\66"+
    "\10\11\7\0\1\11\1\0\1\11\1\0\23\11\1\67"+
    "\11\11\1\70\10\11\7\0\1\11\1\0\1\11\1\0"+
    "\20\11\1\71\15\11\1\72\7\11\7\0\1\11\1\0"+
    "\1\11\1\0\17\11\1\73\17\11\1\74\6\11\7\0"+
    "\1\11\1\0\1\11\1\0\23\11\1\75\22\11\7\0"+
    "\1\11\1\0\1\11\1\0\21\11\1\76\1\11\1\63"+
    "\22\11\7\0\1\11\1\0\1\11\1\0\23\11\1\77"+
    "\22\11\6\41\1\100\52\41\2\42\2\0\55\42\7\0"+
    "\1\11\1\0\1\11\1\0\5\11\1\101\40\11\7\0"+
    "\1\11\1\0\1\11\1\0\1\102\45\11\7\0\1\11"+
    "\1\0\1\11\1\0\14\11\1\103\31\11\7\0\1\11"+
    "\1\0\1\11\1\0\12\11\1\44\33\11\7\0\1\11"+
    "\1\0\1\11\1\0\11\11\1\13\34\11\7\0\1\11"+
    "\1\0\1\11\1\0\21\11\1\104\24\11\7\0\1\11"+
    "\1\0\1\11\1\0\41\11\1\105\4\11\7\0\1\11"+
    "\1\0\1\11\1\0\27\11\1\106\16\11\7\0\1\11"+
    "\1\0\1\11\1\0\24\11\1\107\21\11\7\0\1\11"+
    "\1\0\1\11\1\0\23\11\1\110\22\11\7\0\1\11"+
    "\1\0\1\11\1\0\41\11\1\111\4\11\7\0\1\11"+
    "\1\0\1\11\1\0\40\11\1\112\5\11\7\0\1\11"+
    "\1\0\1\11\1\0\22\11\1\113\23\11\7\0\1\11"+
    "\1\0\1\11\1\0\27\11\1\63\16\11\7\0\1\11"+
    "\1\0\1\11\1\0\23\11\1\114\22\11\7\0\1\11"+
    "\1\0\1\11\1\0\41\11\1\44\4\11\7\0\1\11"+
    "\1\0\1\11\1\0\27\11\1\115\16\11\7\0\1\11"+
    "\1\0\1\11\1\0\34\11\1\116\11\11\7\0\1\11"+
    "\1\0\1\11\1\0\21\11\1\117\24\11\7\0\1\11"+
    "\1\0\1\11\1\0\34\11\1\120\11\11\7\0\1\11"+
    "\1\0\1\11\1\0\22\11\1\121\23\11\7\0\1\11"+
    "\1\0\1\11\1\0\30\11\1\122\15\11\7\0\1\11"+
    "\1\0\1\11\1\0\23\11\1\123\22\11\7\0\1\11"+
    "\1\0\1\11\1\0\37\11\1\124\6\11\7\0\1\11"+
    "\1\0\1\11\1\0\27\11\1\125\5\11\1\126\10\11"+
    "\7\0\1\11\1\0\1\11\1\0\23\11\1\127\1\130"+
    "\11\11\1\131\2\11\1\132\4\11\7\0\1\11\1\0"+
    "\1\11\1\0\37\11\1\133\6\11\7\0\1\11\1\0"+
    "\1\11\1\0\37\11\1\134\6\11\6\41\1\100\1\41"+
    "\1\135\50\41\7\0\1\11\1\0\1\11\1\0\12\11"+
    "\1\103\33\11\7\0\1\11\1\0\1\11\1\0\2\11"+
    "\1\16\43\11\7\0\1\11\1\0\1\11\1\0\3\11"+
    "\1\44\42\11\7\0\1\11\1\0\1\11\1\0\23\11"+
    "\1\136\22\11\7\0\1\11\1\0\1\11\1\0\23\11"+
    "\1\137\22\11\7\0\1\11\1\0\1\11\1\0\35\11"+
    "\1\140\10\11\7\0\1\11\1\0\1\11\1\0\17\11"+
    "\1\116\26\11\7\0\1\11\1\0\1\11\1\0\17\11"+
    "\1\141\26\11\7\0\1\11\1\0\1\11\1\0\36\11"+
    "\1\142\7\11\7\0\1\143\1\0\1\11\1\0\17\11"+
    "\1\144\16\11\1\145\1\11\1\146\5\11\7\0\1\11"+
    "\1\0\1\11\1\0\32\11\1\147\13\11\7\0\1\11"+
    "\1\0\1\11\1\0\41\11\1\150\4\11\7\0\1\11"+
    "\1\0\1\11\1\0\31\11\1\44\14\11\7\0\1\11"+
    "\1\0\1\11\1\0\17\11\1\151\26\11\7\0\1\11"+
    "\1\0\1\11\1\0\42\11\1\44\3\11\7\0\1\11"+
    "\1\0\1\11\1\0\23\11\1\44\22\11\7\0\1\11"+
    "\1\0\1\11\1\0\23\11\1\152\22\11\7\0\1\11"+
    "\1\0\1\11\1\0\34\11\1\44\11\11\7\0\1\11"+
    "\1\0\1\11\1\0\17\11\1\153\26\11\7\0\1\11"+
    "\1\0\1\11\1\0\33\11\1\154\1\63\11\11\7\0"+
    "\1\11\1\0\1\11\1\0\21\11\1\155\24\11\7\0"+
    "\1\11\1\0\1\11\1\0\34\11\1\52\11\11\7\0"+
    "\1\11\1\0\1\11\1\0\37\11\1\156\6\11\7\0"+
    "\1\11\1\0\1\11\1\0\35\11\1\157\10\11\7\0"+
    "\1\11\1\0\1\11\1\0\42\11\1\160\3\11\7\0"+
    "\1\11\1\0\1\11\1\0\23\11\1\161\22\11\7\0"+
    "\1\11\1\0\1\11\1\0\40\11\1\162\5\11\7\0"+
    "\1\11\1\0\1\11\1\0\36\11\1\63\7\11\7\0"+
    "\1\11\1\0\1\11\1\0\37\11\1\44\6\11\7\0"+
    "\1\11\1\0\1\11\1\0\37\11\1\121\6\11\7\0"+
    "\1\11\1\0\1\11\1\0\27\11\1\163\16\11\7\0"+
    "\1\11\1\0\1\11\1\0\32\11\1\164\13\11\7\0"+
    "\1\11\1\0\1\11\1\0\17\11\1\144\16\11\1\145"+
    "\1\11\1\146\5\11\7\0\1\11\1\0\1\11\1\0"+
    "\36\11\1\53\7\11\7\0\1\11\1\0\1\11\1\0"+
    "\37\11\1\165\6\11\7\0\1\11\1\0\1\11\1\0"+
    "\21\11\1\76\24\11\7\0\1\11\1\0\1\11\1\0"+
    "\22\11\1\44\23\11\7\0\1\11\1\0\1\11\1\0"+
    "\27\11\1\166\16\11\7\0\1\11\1\0\1\11\1\0"+
    "\32\11\1\167\13\11\7\0\1\11\1\0\1\11\1\0"+
    "\21\11\1\63\24\11\7\0\1\11\1\0\1\11\1\0"+
    "\33\11\1\170\12\11\7\0\1\11\1\0\1\11\1\0"+
    "\17\11\1\171\26\11\7\0\1\11\1\0\1\11\1\0"+
    "\23\11\1\172\22\11\7\0\1\11\1\0\1\11\1\0"+
    "\23\11\1\173\22\11\7\0\1\11\1\0\1\11\1\0"+
    "\37\11\1\63\6\11\7\0\1\11\1\0\1\11\1\0"+
    "\37\11\1\174\6\11\7\0\1\11\1\0\1\11\1\0"+
    "\23\11\1\175\22\11\7\0\1\11\1\0\1\11\1\0"+
    "\27\11\1\176\16\11\7\0\1\11\1\0\1\11\1\0"+
    "\32\11\1\44\13\11\7\0\1\11\1\0\1\11\1\0"+
    "\17\11\1\177\26\11\7\0\1\11\1\0\1\11\1\0"+
    "\35\11\1\126\10\11\7\0\1\11\1\0\1\11\1\0"+
    "\17\11\1\200\26\11\7\0\1\11\1\0\1\11\1\0"+
    "\24\11\1\24\21\11\7\0\1\11\1\0\1\11\1\0"+
    "\23\11\1\201\22\11\7\0\1\11\1\0\1\11\1\0"+
    "\37\11\1\177\6\11\7\0\1\11\1\0\1\11\1\0"+
    "\22\11\1\202\23\11\7\0\1\11\1\0\1\11\1\0"+
    "\40\11\1\203\5\11\7\0\1\11\1\0\1\11\1\0"+
    "\34\11\1\204\11\11\7\0\1\11\1\0\1\11\1\0"+
    "\34\11\1\106\11\11\7\0\1\11\1\0\1\11\1\0"+
    "\35\11\1\205\10\11\7\0\1\11\1\0\1\11\1\0"+
    "\45\11\1\44\7\0\1\11\1\0\1\11\1\0\32\11"+
    "\1\206\13\11\7\0\1\11\1\0\1\11\1\0\41\11"+
    "\1\207\4\11\7\0\1\11\1\0\1\11\1\0\42\11"+
    "\1\210\3\11\7\0\1\11\1\0\1\11\1\0\26\11"+
    "\1\44\17\11\7\0\1\11\1\0\1\11\1\0\27\11"+
    "\1\211\16\11\7\0\1\11\1\0\1\11\1\0\34\11"+
    "\1\212\11\11\7\0\1\11\1\0\1\11\1\0\27\11"+
    "\1\213\16\11\7\0\1\11\1\0\1\11\1\0\23\11"+
    "\1\214\22\11\7\0\1\11\1\0\1\11\1\0\37\11"+
    "\1\215\6\11\7\0\1\11\1\0\1\11\1\0\34\11"+
    "\1\216\11\11\7\0\1\217\1\0\1\11\1\0\46\11"+
    "\7\0\1\11\1\0\1\11\1\0\40\11\1\121\5\11"+
    "\7\0\1\11\1\0\1\11\1\0\37\11\1\220\6\11"+
    "\7\0\1\11\1\0\1\11\1\0\23\11\1\106\22\11"+
    "\7\0\1\11\1\0\1\11\1\0\25\11\1\44\20\11"+
    "\7\0\1\11\1\0\1\11\1\0\34\11\1\221\11\11"+
    "\7\0\1\11\1\0\1\11\1\0\40\11\1\44\5\11"+
    "\7\0\1\11\1\0\1\11\1\0\42\11\1\222\3\11"+
    "\7\0\1\11\1\0\1\11\1\0\33\11\1\223\12\11"+
    "\7\0\1\11\1\0\1\11\1\0\20\11\1\105\25\11";

  private static int [] zzUnpacktrans() {
    int [] result = new int[6860];
    int offset = 0;
    offset = zzUnpacktrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpacktrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\5\1\1\11\25\1\1\0\1\11\1\0"+
    "\74\1\1\11\66\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[147];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** Number of newlines encountered up to the start of the matched text. */
  @SuppressWarnings("unused")
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  @SuppressWarnings("unused")
  protected int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  private boolean zzEOFDone;


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  SPLLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** Returns the maximum size of the scanner buffer, which limits the size of tokens. */
  private int zzMaxBufferLen() {
    return Integer.MAX_VALUE;
  }

  /**  Whether the scanner buffer can grow to accommodate a larger token. */
  private boolean zzCanGrow() {
    return true;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      {@code false}, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position {@code pos} from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException
  {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { return TokenType.BAD_CHARACTER;
            }
          // fall through
          case 11: break;
          case 2:
            { return TokenType.WHITE_SPACE;
            }
          // fall through
          case 12: break;
          case 3:
            { return SPLTypes.NUMBER;
            }
          // fall through
          case 13: break;
          case 4:
            { return SPLTypes.SEMICOLON;
            }
          // fall through
          case 14: break;
          case 5:
            { return SPLTypes.IDENTIFIER;
            }
          // fall through
          case 15: break;
          case 6:
            { return SPLTypes.STRING;
            }
          // fall through
          case 16: break;
          case 7:
            { return SPLTypes.BLOCK_COMMENT;
            }
          // fall through
          case 17: break;
          case 8:
            { return SPLTypes.COMMENT;
            }
          // fall through
          case 18: break;
          case 9:
            { return SPLTypes.KEYWORD;
            }
          // fall through
          case 19: break;
          case 10:
            { return SPLTypes.FUNCTION_DECLARATION;
            }
          // fall through
          case 20: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
