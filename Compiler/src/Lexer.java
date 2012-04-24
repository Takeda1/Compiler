/* The following code was generated by JFlex 1.4.3 on 3/27/12 3:32 PM */

/*
 * Eric Brodersen and Mark Harder
 * CS333 Topics in Computint - Compilers
 * Programming Assignment 3 - CUP Parser
 * 2012.03.15
 *
 * Lexer.jflex
 * To compile:
 * $ jflex Lexer.jflex
 *
 * A lexer created with JFlex for the cool programming language
 * Uses the CUP framework to integrate with a CUP parser.
 * Makes use of Symbol and Token classes
 * With help from Charles Cooley and Dee Weikle
 * http://ostermiller.org/findcomment.html consulted for multi-line comments
 */

/* The lexer for scanning command tokens. */
import java_cup.runtime.*;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 3/27/12 3:32 PM from the specification file
 * <tt>Lexer.jflex</tt>
 */
class Lexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int STRING = 4;
  public static final int YYINITIAL = 0;
  public static final int MULTICOMMENT = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2, 2
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\5\1\16\1\0\1\5\1\10\22\0\1\5\1\0\1\15"+
    "\5\0\1\12\1\14\1\13\1\74\1\62\1\7\1\63\1\73\1\11"+
    "\11\4\1\61\1\67\1\60\1\71\1\72\1\0\1\64\1\23\1\3"+
    "\1\21\1\52\1\27\1\33\1\3\1\41\1\35\2\3\1\31\1\3"+
    "\1\37\1\50\1\54\1\3\1\43\1\25\1\45\1\3\1\46\1\56"+
    "\1\3\1\76\1\3\1\0\1\17\2\0\1\2\1\0\1\22\1\1"+
    "\1\20\1\51\1\26\1\32\1\1\1\40\1\34\2\1\1\30\1\1"+
    "\1\36\1\47\1\53\1\1\1\42\1\24\1\44\1\57\1\6\1\55"+
    "\1\1\1\75\1\1\1\65\1\0\1\66\1\70\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\0\1\2\1\3\1\4\1\5\1\6"+
    "\1\7\1\5\1\10\1\11\1\12\1\13\1\3\2\4"+
    "\1\3\1\4\1\3\1\4\1\3\1\4\1\3\1\4"+
    "\1\3\1\4\1\3\1\4\1\3\1\4\1\3\1\4"+
    "\1\3\1\4\1\14\1\15\1\16\1\17\1\20\1\21"+
    "\1\22\1\23\1\24\1\25\1\26\1\27\3\1\1\11"+
    "\2\30\1\31\1\32\1\30\1\33\1\34\2\3\3\4"+
    "\2\3\2\4\2\3\2\4\1\3\2\35\1\3\1\36"+
    "\1\37\1\4\1\36\1\37\2\3\2\4\3\3\1\4"+
    "\2\40\1\3\1\4\1\3\1\4\1\41\1\42\1\43"+
    "\1\0\1\33\1\44\1\45\1\33\1\30\2\3\3\4"+
    "\2\3\2\4\1\46\1\3\1\46\1\4\3\3\2\4"+
    "\1\47\1\50\1\47\1\50\3\3\1\4\1\3\1\4"+
    "\1\3\1\4\1\33\1\51\1\3\1\51\2\4\1\52"+
    "\1\53\1\52\1\53\2\54\3\3\2\4\1\55\1\56"+
    "\1\57\1\55\2\60\1\3\1\4\2\61\1\0\1\62"+
    "\2\3\2\4\2\63\1\0\1\64\1\3\1\64\1\4"+
    "\1\0\1\3\1\4\1\0\2\65\1\4";

  private static int [] zzUnpackAction() {
    int [] result = new int[180];
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
    "\0\0\0\77\0\176\0\275\0\374\0\u013b\0\u017a\0\275"+
    "\0\u01b9\0\275\0\u01f8\0\275\0\275\0\275\0\u0237\0\u0276"+
    "\0\u02b5\0\u02f4\0\u0333\0\u0372\0\u03b1\0\u03f0\0\u042f\0\u046e"+
    "\0\u04ad\0\u04ec\0\u052b\0\u056a\0\u05a9\0\u05e8\0\u0627\0\u0666"+
    "\0\u06a5\0\u06e4\0\u0723\0\u0762\0\275\0\275\0\275\0\275"+
    "\0\275\0\275\0\275\0\275\0\u07a1\0\275\0\275\0\u07e0"+
    "\0\u081f\0\u085e\0\u089d\0\u08dc\0\u091b\0\275\0\275\0\u095a"+
    "\0\u0999\0\275\0\u09d8\0\u0a17\0\u0a56\0\u0a95\0\u0ad4\0\u0b13"+
    "\0\u0b52\0\u0b91\0\u0bd0\0\u0c0f\0\u0c4e\0\u0c8d\0\u0ccc\0\u0d0b"+
    "\0\374\0\u013b\0\u0d4a\0\374\0\u0d89\0\u0dc8\0\u013b\0\u0e07"+
    "\0\u0e46\0\u0e85\0\u0ec4\0\u0f03\0\u0f42\0\u0f81\0\u0fc0\0\u0fff"+
    "\0\374\0\u013b\0\u103e\0\u107d\0\u10bc\0\u10fb\0\275\0\275"+
    "\0\275\0\u113a\0\u1179\0\u113a\0\275\0\u11b8\0\275\0\u11f7"+
    "\0\u1236\0\u1275\0\u12b4\0\u12f3\0\u1332\0\u1371\0\u13b0\0\u13ef"+
    "\0\374\0\u142e\0\u013b\0\u146d\0\u14ac\0\u14eb\0\u152a\0\u1569"+
    "\0\u15a8\0\374\0\374\0\u013b\0\u013b\0\u15e7\0\u1626\0\u1665"+
    "\0\u16a4\0\u16e3\0\u1722\0\u1761\0\u17a0\0\u17df\0\374\0\u181e"+
    "\0\u013b\0\u185d\0\u189c\0\374\0\374\0\u013b\0\u013b\0\374"+
    "\0\u013b\0\u18db\0\u191a\0\u1959\0\u1998\0\u19d7\0\374\0\374"+
    "\0\374\0\u013b\0\374\0\u013b\0\u1a16\0\u1a55\0\374\0\u013b"+
    "\0\u1a94\0\374\0\u1ad3\0\u1b12\0\u1b51\0\u1b90\0\374\0\u013b"+
    "\0\u1bcf\0\374\0\u1c0e\0\u013b\0\u1c4d\0\u1c8c\0\u1ccb\0\u1d0a"+
    "\0\u1d49\0\374\0\u013b\0\275";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[180];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\1\5\1\4\1\6\1\7\1\10\1\5\1\11"+
    "\1\10\1\12\1\13\1\14\1\15\1\16\1\10\1\4"+
    "\1\17\1\20\1\5\1\6\1\5\1\21\1\22\1\23"+
    "\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33"+
    "\1\5\1\6\1\5\1\6\1\34\1\35\1\6\1\36"+
    "\1\37\1\5\1\6\1\40\1\41\1\42\1\43\1\5"+
    "\1\44\1\45\1\46\1\47\1\50\1\51\1\52\1\53"+
    "\1\54\1\55\1\4\1\56\1\57\1\5\1\6\7\60"+
    "\1\61\2\60\1\62\1\63\63\60\7\64\1\65\5\64"+
    "\1\66\1\67\1\70\57\64\100\0\4\5\1\0\1\5"+
    "\2\0\1\5\6\0\40\5\15\0\2\5\1\0\1\6"+
    "\1\0\2\6\1\0\1\6\2\0\1\6\6\0\40\6"+
    "\15\0\2\6\4\0\1\7\4\0\1\7\74\0\1\71"+
    "\102\0\1\72\64\0\4\5\1\0\1\5\2\0\1\5"+
    "\6\0\2\5\2\73\4\5\2\74\26\5\15\0\2\5"+
    "\1\0\1\6\1\0\2\6\1\0\1\6\2\0\1\6"+
    "\6\0\2\6\2\75\4\6\2\76\26\6\15\0\2\6"+
    "\1\0\1\6\1\0\2\6\1\0\1\6\2\0\1\6"+
    "\6\0\7\6\1\77\30\6\15\0\2\6\1\0\4\5"+
    "\1\0\1\5\2\0\1\5\6\0\4\5\2\100\2\5"+
    "\2\101\26\5\15\0\2\5\1\0\1\6\1\0\2\6"+
    "\1\0\1\6\2\0\1\6\6\0\4\6\2\102\2\6"+
    "\2\103\26\6\15\0\2\6\1\0\4\5\1\0\1\5"+
    "\2\0\1\5\6\0\6\5\2\104\17\5\2\105\7\5"+
    "\15\0\2\5\1\0\1\6\1\0\2\6\1\0\1\6"+
    "\2\0\1\6\6\0\6\6\2\106\17\6\2\107\7\6"+
    "\15\0\2\6\1\0\4\5\1\0\1\5\2\0\1\5"+
    "\6\0\2\5\1\110\11\5\2\111\22\5\15\0\2\5"+
    "\1\0\1\6\1\0\2\6\1\0\1\6\2\0\1\6"+
    "\6\0\14\6\2\112\22\6\15\0\2\6\1\0\4\5"+
    "\1\0\1\5\2\0\1\5\6\0\4\5\2\113\4\5"+
    "\2\114\2\5\2\115\20\5\15\0\2\5\1\0\1\6"+
    "\1\0\2\6\1\0\1\6\2\0\1\6\6\0\4\6"+
    "\2\116\4\6\2\117\2\6\2\120\20\6\15\0\2\6"+
    "\1\0\4\5\1\0\1\5\2\0\1\5\6\0\6\5"+
    "\2\121\17\5\2\122\7\5\15\0\2\5\1\0\1\6"+
    "\1\0\2\6\1\0\1\6\2\0\1\6\6\0\6\6"+
    "\2\123\17\6\2\124\7\6\15\0\2\6\1\0\4\5"+
    "\1\0\1\5\2\0\1\5\6\0\20\5\2\125\1\126"+
    "\15\5\15\0\1\127\1\5\1\0\1\6\1\0\2\6"+
    "\1\0\1\6\2\0\1\6\6\0\20\6\2\130\16\6"+
    "\15\0\2\6\1\0\4\5\1\0\1\5\2\0\1\5"+
    "\6\0\12\5\2\131\24\5\15\0\2\5\1\0\1\6"+
    "\1\0\2\6\1\0\1\6\2\0\1\6\6\0\12\6"+
    "\2\132\24\6\15\0\2\6\1\0\4\5\1\0\1\5"+
    "\2\0\1\5\6\0\27\5\2\133\7\5\15\0\2\5"+
    "\1\0\1\6\1\0\2\6\1\0\1\6\2\0\1\6"+
    "\6\0\27\6\2\134\7\6\15\0\2\6\1\0\4\5"+
    "\1\0\1\5\2\0\1\5\6\0\20\5\2\135\16\5"+
    "\15\0\2\5\1\0\1\6\1\0\2\6\1\0\1\6"+
    "\2\0\1\6\6\0\20\6\2\136\16\6\15\0\2\6"+
    "\7\0\1\137\61\0\1\140\77\0\1\141\4\0\13\60"+
    "\1\142\72\60\1\143\3\60\1\142\76\60\1\144\77\60"+
    "\1\145\62\60\15\64\3\0\66\64\1\146\5\64\3\0"+
    "\57\64\15\0\1\147\61\0\10\71\1\0\5\71\1\0"+
    "\60\71\1\0\4\5\1\0\1\5\2\0\1\5\6\0"+
    "\4\5\2\150\32\5\15\0\2\5\1\0\4\5\1\0"+
    "\1\5\2\0\1\5\6\0\2\5\2\151\34\5\15\0"+
    "\2\5\1\0\1\6\1\0\2\6\1\0\1\6\2\0"+
    "\1\6\6\0\4\6\2\152\32\6\15\0\2\6\1\0"+
    "\1\6\1\0\2\6\1\0\1\6\2\0\1\6\6\0"+
    "\2\6\2\153\34\6\15\0\2\6\1\0\1\6\1\0"+
    "\2\6\1\0\1\6\2\0\1\6\6\0\11\6\1\154"+
    "\26\6\15\0\2\6\1\0\4\5\1\0\1\5\2\0"+
    "\1\5\6\0\2\5\2\155\34\5\15\0\2\5\1\0"+
    "\4\5\1\0\1\5\2\0\1\5\6\0\4\5\2\156"+
    "\32\5\15\0\2\5\1\0\1\6\1\0\2\6\1\0"+
    "\1\6\2\0\1\6\6\0\2\6\2\157\34\6\15\0"+
    "\2\6\1\0\1\6\1\0\2\6\1\0\1\6\2\0"+
    "\1\6\6\0\4\6\2\160\32\6\15\0\2\6\1\0"+
    "\4\5\1\0\1\5\2\0\1\5\6\0\24\5\2\161"+
    "\12\5\15\0\2\5\1\0\4\5\1\0\1\5\2\0"+
    "\1\5\6\0\27\5\2\162\7\5\15\0\2\5\1\0"+
    "\1\6\1\0\2\6\1\0\1\6\2\0\1\6\6\0"+
    "\24\6\2\163\12\6\15\0\2\6\1\0\1\6\1\0"+
    "\2\6\1\0\1\6\2\0\1\6\6\0\27\6\2\164"+
    "\7\6\15\0\2\6\1\0\4\5\1\0\1\5\2\0"+
    "\1\5\6\0\10\5\1\165\27\5\15\0\2\5\1\0"+
    "\4\5\1\0\1\166\2\0\1\5\6\0\26\5\1\166"+
    "\11\5\15\0\2\5\1\0\4\5\1\0\1\5\2\0"+
    "\1\5\6\0\20\5\2\167\16\5\15\0\2\5\1\0"+
    "\1\6\1\0\2\6\1\0\1\170\2\0\1\6\6\0"+
    "\26\6\1\170\11\6\15\0\2\6\1\0\1\6\1\0"+
    "\2\6\1\0\1\6\2\0\1\6\6\0\20\6\2\171"+
    "\16\6\15\0\2\6\1\0\4\5\1\0\1\5\2\0"+
    "\1\5\6\0\35\5\2\172\1\5\15\0\2\5\1\0"+
    "\4\5\1\0\1\5\2\0\1\5\6\0\24\5\2\173"+
    "\12\5\15\0\2\5\1\0\1\6\1\0\2\6\1\0"+
    "\1\6\2\0\1\6\6\0\35\6\2\174\1\6\15\0"+
    "\2\6\1\0\1\6\1\0\2\6\1\0\1\6\2\0"+
    "\1\6\6\0\24\6\2\175\12\6\15\0\2\6\1\0"+
    "\4\5\1\0\1\5\2\0\1\5\6\0\6\5\2\176"+
    "\30\5\15\0\2\5\1\0\4\5\1\0\1\5\2\0"+
    "\1\5\6\0\37\5\1\177\15\0\2\5\1\0\4\5"+
    "\1\0\1\5\2\0\1\5\6\0\33\5\1\200\4\5"+
    "\15\0\2\5\1\0\1\6\1\0\2\6\1\0\1\6"+
    "\2\0\1\6\6\0\6\6\2\201\30\6\15\0\2\6"+
    "\1\0\4\5\1\0\1\5\2\0\1\5\6\0\27\5"+
    "\2\202\7\5\15\0\2\5\1\0\1\6\1\0\2\6"+
    "\1\0\1\6\2\0\1\6\6\0\27\6\2\203\7\6"+
    "\15\0\2\6\1\0\4\5\1\0\1\5\2\0\1\5"+
    "\6\0\14\5\2\204\22\5\15\0\2\5\1\0\1\6"+
    "\1\0\2\6\1\0\1\6\2\0\1\6\6\0\14\6"+
    "\2\205\22\6\15\0\2\6\14\60\1\0\62\60\10\143"+
    "\1\60\2\143\1\206\2\143\1\60\60\143\10\146\1\64"+
    "\4\146\1\71\1\0\1\71\57\146\1\0\4\5\1\0"+
    "\1\5\2\0\1\5\6\0\6\5\2\207\30\5\15\0"+
    "\2\5\1\0\4\5\1\0\1\5\2\0\1\5\6\0"+
    "\4\5\2\210\32\5\15\0\2\5\1\0\1\6\1\0"+
    "\2\6\1\0\1\6\2\0\1\6\6\0\6\6\2\211"+
    "\30\6\15\0\2\6\1\0\1\6\1\0\2\6\1\0"+
    "\1\6\2\0\1\6\6\0\4\6\2\212\32\6\15\0"+
    "\2\6\1\0\1\6\1\0\2\6\1\0\1\6\2\0"+
    "\1\6\6\0\13\6\1\213\24\6\15\0\2\6\1\0"+
    "\4\5\1\0\1\5\2\0\1\5\6\0\2\214\36\5"+
    "\15\0\2\5\1\0\4\5\1\0\1\5\2\0\1\5"+
    "\6\0\6\5\2\215\30\5\15\0\2\5\1\0\1\6"+
    "\1\0\2\6\1\0\1\6\2\0\1\6\6\0\2\216"+
    "\36\6\15\0\2\6\1\0\1\6\1\0\2\6\1\0"+
    "\1\6\2\0\1\6\6\0\6\6\2\217\30\6\15\0"+
    "\2\6\1\0\4\5\1\0\1\5\2\0\1\5\6\0"+
    "\33\5\2\220\3\5\15\0\2\5\1\0\1\6\1\0"+
    "\2\6\1\0\1\6\2\0\1\6\6\0\33\6\2\221"+
    "\3\6\15\0\2\6\1\0\4\5\1\0\1\5\2\0"+
    "\1\5\6\0\4\5\1\222\33\5\15\0\2\5\1\0"+
    "\4\5\1\0\1\5\2\0\1\5\6\0\27\5\2\223"+
    "\7\5\15\0\2\5\1\0\4\5\1\0\1\5\2\0"+
    "\1\5\6\0\6\5\2\224\30\5\15\0\2\5\1\0"+
    "\1\6\1\0\2\6\1\0\1\6\2\0\1\6\6\0"+
    "\27\6\2\225\7\6\15\0\2\6\1\0\1\6\1\0"+
    "\2\6\1\0\1\6\2\0\1\6\6\0\6\6\2\226"+
    "\30\6\15\0\2\6\1\0\4\5\1\0\1\5\2\0"+
    "\1\5\6\0\16\5\2\227\20\5\15\0\2\5\1\0"+
    "\4\5\1\0\1\5\2\0\1\5\6\0\6\5\1\230"+
    "\31\5\15\0\2\5\1\0\4\5\1\0\1\5\2\0"+
    "\1\5\6\0\6\5\1\231\31\5\15\0\2\5\1\0"+
    "\1\6\1\0\2\6\1\0\1\6\2\0\1\6\6\0"+
    "\16\6\2\232\20\6\15\0\2\6\1\0\4\5\1\0"+
    "\1\5\2\0\1\5\6\0\10\5\2\233\26\5\15\0"+
    "\2\5\1\0\1\6\1\0\2\6\1\0\1\6\2\0"+
    "\1\6\6\0\10\6\2\234\26\6\15\0\2\6\1\0"+
    "\4\5\1\0\1\5\2\0\1\5\6\0\10\5\2\235"+
    "\26\5\15\0\2\5\1\0\1\6\1\0\2\6\1\0"+
    "\1\6\2\0\1\6\6\0\10\6\2\236\26\6\15\0"+
    "\2\6\10\143\1\60\3\143\1\71\1\143\1\60\60\143"+
    "\1\0\4\5\1\0\1\5\2\0\1\5\6\0\4\5"+
    "\2\237\32\5\15\0\2\5\1\0\1\6\1\0\2\6"+
    "\1\0\1\6\2\0\1\6\6\0\4\6\2\240\32\6"+
    "\15\0\2\6\1\0\1\6\1\241\2\6\1\0\1\6"+
    "\2\0\1\6\6\0\40\6\15\0\2\6\1\0\4\5"+
    "\1\0\1\5\2\0\1\5\6\0\6\5\1\242\31\5"+
    "\15\0\2\5\1\0\4\5\1\0\1\5\2\0\1\5"+
    "\6\0\14\5\2\243\22\5\15\0\2\5\1\0\4\5"+
    "\1\0\1\5\2\0\1\5\6\0\22\5\2\244\14\5"+
    "\15\0\2\5\1\0\1\6\1\0\2\6\1\0\1\6"+
    "\2\0\1\6\6\0\14\6\2\245\22\6\15\0\2\6"+
    "\1\0\1\6\1\0\2\6\1\0\1\6\2\0\1\6"+
    "\6\0\22\6\2\246\14\6\15\0\2\6\1\0\4\5"+
    "\1\0\1\5\2\0\1\5\6\0\6\5\2\247\30\5"+
    "\15\0\2\5\1\0\1\6\1\0\2\6\1\0\1\6"+
    "\2\0\1\6\6\0\6\6\2\250\30\6\15\0\2\6"+
    "\45\0\1\251\32\0\4\5\1\0\1\5\2\0\1\5"+
    "\6\0\31\5\2\252\5\5\15\0\2\5\1\0\4\5"+
    "\1\0\1\5\2\0\1\5\6\0\14\5\2\253\22\5"+
    "\15\0\2\5\1\0\1\6\1\0\2\6\1\0\1\6"+
    "\2\0\1\6\6\0\31\6\2\254\5\6\15\0\2\6"+
    "\1\0\1\6\1\0\2\6\1\0\1\6\2\0\1\6"+
    "\6\0\14\6\2\255\22\6\15\0\2\6\76\0\1\256"+
    "\1\0\4\5\1\0\1\5\2\0\1\5\6\0\24\5"+
    "\2\257\12\5\15\0\2\5\1\0\1\6\1\0\2\6"+
    "\1\0\1\6\2\0\1\6\6\0\24\6\2\260\12\6"+
    "\15\0\2\6\54\0\1\261\23\0\4\5\1\0\1\5"+
    "\2\0\1\5\6\0\4\5\2\262\32\5\15\0\2\5"+
    "\1\0\1\6\1\0\2\6\1\0\1\6\2\0\1\6"+
    "\6\0\4\6\2\263\32\6\15\0\2\6\27\0\1\264"+
    "\47\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[7560];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
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
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\1\1\0\1\11\3\1\1\11\1\1\1\11"+
    "\1\1\3\11\26\1\10\11\1\1\2\11\6\1\2\11"+
    "\2\1\1\11\44\1\3\11\1\0\2\1\1\11\1\1"+
    "\1\11\71\1\1\0\7\1\1\0\4\1\1\0\2\1"+
    "\1\0\2\1\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[180];
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
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */

  private Symbol symbol(int type) {
    return new Symbol(type,yyline,yycolumn);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type,yyline,yycolumn,value);
  }

  private Token tokenize(int line) {
    return new Token(line);
  }

  private Token tokenize(int line, Object value) {
    return new Token(line, value);
  }

  String str_value;
  int comment_count;


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Lexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Lexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 176) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
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
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
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
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
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
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
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
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 23: 
          { return symbol(sym.PLUS,tokenize(yyline));
          }
        case 54: break;
        case 26: 
          { throw new Error("Must not include newline in string <"+yytext()+">");
          }
        case 55: break;
        case 14: 
          { return symbol(sym.COMMA,tokenize(yyline));
          }
        case 56: break;
        case 53: 
          { return symbol(sym.INHERITS,tokenize(yyline));
          }
        case 57: break;
        case 5: 
          { long i = Long.parseLong(yytext());
  if ( i <= 2147483647 )
    return symbol(sym.INT,tokenize(yyline,i));
  else
    throw new Error("That is not a 32-bit integer.");
          }
        case 58: break;
        case 24: 
          { str_value += yytext();
          }
        case 59: break;
        case 39: 
          { return symbol(sym.NEW,tokenize(yyline));
          }
        case 60: break;
        case 34: 
          { return symbol(sym.LE,tokenize(yyline));
          }
        case 61: break;
        case 18: 
          { return symbol(sym.RBRACE,tokenize(yyline));
          }
        case 62: break;
        case 25: 
          { yybegin(YYINITIAL); return symbol(sym.STR,tokenize(yyline,str_value));
          }
        case 63: break;
        case 6: 
          { /* Ignore Whitespace */
          }
        case 64: break;
        case 42: 
          { return symbol(sym.ESAC,tokenize(yyline));
          }
        case 65: break;
        case 3: 
          { return symbol(sym.IDENTIFIER,tokenize(yyline,yytext()));
          }
        case 66: break;
        case 2: 
          { throw new Error("Illegal character <"+yytext()+">");
          }
        case 67: break;
        case 44: 
          { return symbol(sym.LOOP,tokenize(yyline));
          }
        case 68: break;
        case 22: 
          { return symbol(sym.DIVIDE,tokenize(yyline));
          }
        case 69: break;
        case 17: 
          { return symbol(sym.LBRACE,tokenize(yyline));
          }
        case 70: break;
        case 38: 
          { return symbol(sym.LET,tokenize(yyline));
          }
        case 71: break;
        case 15: 
          { return symbol(sym.DOT,tokenize(yyline));
          }
        case 72: break;
        case 52: 
          { return symbol(sym.ISVOID,tokenize(yyline));
          }
        case 73: break;
        case 30: 
          { return symbol(sym.IF,tokenize(yyline));
          }
        case 74: break;
        case 19: 
          { return symbol(sym.SEMI,tokenize(yyline));
          }
        case 75: break;
        case 29: 
          { return symbol(sym.FI,tokenize(yyline));
          }
        case 76: break;
        case 16: 
          { return symbol(sym.AT,tokenize(yyline));
          }
        case 77: break;
        case 43: 
          { return symbol(sym.ELSE,tokenize(yyline));
          }
        case 78: break;
        case 37: 
          { comment_count -= 1; if ( comment_count == 0 ) yybegin(YYINITIAL);
          }
        case 79: break;
        case 36: 
          { comment_count += 1;
          }
        case 80: break;
        case 12: 
          { return symbol(sym.LT,tokenize(yyline));
          }
        case 81: break;
        case 50: 
          { return symbol(sym.FALSE,tokenize(yyline));
          }
        case 82: break;
        case 31: 
          { return symbol(sym.IN,tokenize(yyline));
          }
        case 83: break;
        case 27: 
          { /* ignore comments */
          }
        case 84: break;
        case 49: 
          { return symbol(sym.CLASS,tokenize(yyline));
          }
        case 85: break;
        case 28: 
          { comment_count = 1; yybegin(MULTICOMMENT);
          }
        case 86: break;
        case 51: 
          { return symbol(sym.WHILE,tokenize(yyline));
          }
        case 87: break;
        case 10: 
          { return symbol(sym.RPAREN,tokenize(yyline));
          }
        case 88: break;
        case 45: 
          { return symbol(sym.THEN,tokenize(yyline));
          }
        case 89: break;
        case 35: 
          { return symbol(sym.RARROW,tokenize(yyline));
          }
        case 90: break;
        case 13: 
          { return symbol(sym.COLON,tokenize(yyline));
          }
        case 91: break;
        case 32: 
          { return symbol(sym.OF,tokenize(yyline));
          }
        case 92: break;
        case 20: 
          { return symbol(sym.TILDE,tokenize(yyline));
          }
        case 93: break;
        case 41: 
          { return symbol(sym.CASE,tokenize(yyline));
          }
        case 94: break;
        case 9: 
          { return symbol(sym.TIMES,tokenize(yyline));
          }
        case 95: break;
        case 48: 
          { return symbol(sym.POOL,tokenize(yyline));
          }
        case 96: break;
        case 47: 
          { return symbol(sym.TYPE,tokenize(yyline));
          }
        case 97: break;
        case 33: 
          { return symbol(sym.LARROW,tokenize(yyline));
          }
        case 98: break;
        case 8: 
          { return symbol(sym.LPAREN,tokenize(yyline));
          }
        case 99: break;
        case 4: 
          { return symbol(sym.TYPE,tokenize(yyline,yytext()));
          }
        case 100: break;
        case 11: 
          { str_value = ""; yybegin(STRING);
          }
        case 101: break;
        case 7: 
          { return symbol(sym.MINUS,tokenize(yyline));
          }
        case 102: break;
        case 40: 
          { return symbol(sym.NOT,tokenize(yyline));
          }
        case 103: break;
        case 21: 
          { return symbol(sym.EQUALS,tokenize(yyline));
          }
        case 104: break;
        case 46: 
          { return symbol(sym.TRUE,tokenize(yyline));
          }
        case 105: break;
        case 1: 
          { 
          }
        case 106: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return new java_cup.runtime.Symbol(sym.EOF); }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
