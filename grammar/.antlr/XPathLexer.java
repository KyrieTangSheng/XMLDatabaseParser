// Generated from /Users/tony/Desktop/XMLDatabaseParser/grammar/xpath.g4 by ANTLR 4.13.1
package xpath;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class XPathLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, DOC=6, STAR=7, DOT=8, DOTDOT=9, 
		TEXTOPEN=10, CLOSE=11, AT=12, EQ=13, EQVALUE=14, ISEQ=15, ISVALUE=16, 
		StringConstant=17, AND=18, OR=19, NOT=20, Name=21, COMMA=22, WS=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "DOC", "STAR", "DOT", "DOTDOT", 
			"TEXTOPEN", "CLOSE", "AT", "EQ", "EQVALUE", "ISEQ", "ISVALUE", "StringConstant", 
			"AND", "OR", "NOT", "Name", "COMMA", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "'/'", "'//'", "'['", "']'", "'doc'", "'*'", "'.'", "'..'", 
			"'text('", "')'", "'@'", "'='", "'eq'", "'=='", "'is'", null, "'and'", 
			"'or'", "'not'", null, "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "DOC", "STAR", "DOT", "DOTDOT", "TEXTOPEN", 
			"CLOSE", "AT", "EQ", "EQVALUE", "ISEQ", "ISVALUE", "StringConstant", 
			"AND", "OR", "NOT", "Name", "COMMA", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public XPathLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "xpath.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0017~\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0005\u0010]\b\u0010\n\u0010\f\u0010`\t\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0014\u0001\u0014\u0005\u0014q\b\u0014\n\u0014\f\u0014t\t"+
		"\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0004\u0016y\b\u0016\u000b"+
		"\u0016\f\u0016z\u0001\u0016\u0001\u0016\u0000\u0000\u0017\u0001\u0001"+
		"\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f"+
		"\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f"+
		"\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017\u0001\u0000"+
		"\u0004\u0001\u0000\"\"\u0003\u0000AZ__az\u0005\u0000--09AZ__az\u0003\u0000"+
		"\t\n\r\r  \u0080\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001"+
		"\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001"+
		"\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000"+
		"\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000"+
		"\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000"+
		"\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000"+
		"\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000"+
		"\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000"+
		"\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000"+
		"%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001"+
		"\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000"+
		"\u0000\u0001/\u0001\u0000\u0000\u0000\u00031\u0001\u0000\u0000\u0000\u0005"+
		"3\u0001\u0000\u0000\u0000\u00076\u0001\u0000\u0000\u0000\t8\u0001\u0000"+
		"\u0000\u0000\u000b:\u0001\u0000\u0000\u0000\r>\u0001\u0000\u0000\u0000"+
		"\u000f@\u0001\u0000\u0000\u0000\u0011B\u0001\u0000\u0000\u0000\u0013E"+
		"\u0001\u0000\u0000\u0000\u0015K\u0001\u0000\u0000\u0000\u0017M\u0001\u0000"+
		"\u0000\u0000\u0019O\u0001\u0000\u0000\u0000\u001bQ\u0001\u0000\u0000\u0000"+
		"\u001dT\u0001\u0000\u0000\u0000\u001fW\u0001\u0000\u0000\u0000!Z\u0001"+
		"\u0000\u0000\u0000#c\u0001\u0000\u0000\u0000%g\u0001\u0000\u0000\u0000"+
		"\'j\u0001\u0000\u0000\u0000)n\u0001\u0000\u0000\u0000+u\u0001\u0000\u0000"+
		"\u0000-x\u0001\u0000\u0000\u0000/0\u0005(\u0000\u00000\u0002\u0001\u0000"+
		"\u0000\u000012\u0005/\u0000\u00002\u0004\u0001\u0000\u0000\u000034\u0005"+
		"/\u0000\u000045\u0005/\u0000\u00005\u0006\u0001\u0000\u0000\u000067\u0005"+
		"[\u0000\u00007\b\u0001\u0000\u0000\u000089\u0005]\u0000\u00009\n\u0001"+
		"\u0000\u0000\u0000:;\u0005d\u0000\u0000;<\u0005o\u0000\u0000<=\u0005c"+
		"\u0000\u0000=\f\u0001\u0000\u0000\u0000>?\u0005*\u0000\u0000?\u000e\u0001"+
		"\u0000\u0000\u0000@A\u0005.\u0000\u0000A\u0010\u0001\u0000\u0000\u0000"+
		"BC\u0005.\u0000\u0000CD\u0005.\u0000\u0000D\u0012\u0001\u0000\u0000\u0000"+
		"EF\u0005t\u0000\u0000FG\u0005e\u0000\u0000GH\u0005x\u0000\u0000HI\u0005"+
		"t\u0000\u0000IJ\u0005(\u0000\u0000J\u0014\u0001\u0000\u0000\u0000KL\u0005"+
		")\u0000\u0000L\u0016\u0001\u0000\u0000\u0000MN\u0005@\u0000\u0000N\u0018"+
		"\u0001\u0000\u0000\u0000OP\u0005=\u0000\u0000P\u001a\u0001\u0000\u0000"+
		"\u0000QR\u0005e\u0000\u0000RS\u0005q\u0000\u0000S\u001c\u0001\u0000\u0000"+
		"\u0000TU\u0005=\u0000\u0000UV\u0005=\u0000\u0000V\u001e\u0001\u0000\u0000"+
		"\u0000WX\u0005i\u0000\u0000XY\u0005s\u0000\u0000Y \u0001\u0000\u0000\u0000"+
		"Z^\u0005\"\u0000\u0000[]\b\u0000\u0000\u0000\\[\u0001\u0000\u0000\u0000"+
		"]`\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000"+
		"\u0000_a\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000\u0000ab\u0005\"\u0000"+
		"\u0000b\"\u0001\u0000\u0000\u0000cd\u0005a\u0000\u0000de\u0005n\u0000"+
		"\u0000ef\u0005d\u0000\u0000f$\u0001\u0000\u0000\u0000gh\u0005o\u0000\u0000"+
		"hi\u0005r\u0000\u0000i&\u0001\u0000\u0000\u0000jk\u0005n\u0000\u0000k"+
		"l\u0005o\u0000\u0000lm\u0005t\u0000\u0000m(\u0001\u0000\u0000\u0000nr"+
		"\u0007\u0001\u0000\u0000oq\u0007\u0002\u0000\u0000po\u0001\u0000\u0000"+
		"\u0000qt\u0001\u0000\u0000\u0000rp\u0001\u0000\u0000\u0000rs\u0001\u0000"+
		"\u0000\u0000s*\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000uv\u0005"+
		",\u0000\u0000v,\u0001\u0000\u0000\u0000wy\u0007\u0003\u0000\u0000xw\u0001"+
		"\u0000\u0000\u0000yz\u0001\u0000\u0000\u0000zx\u0001\u0000\u0000\u0000"+
		"z{\u0001\u0000\u0000\u0000{|\u0001\u0000\u0000\u0000|}\u0006\u0016\u0000"+
		"\u0000}.\u0001\u0000\u0000\u0000\u0004\u0000^rz\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}