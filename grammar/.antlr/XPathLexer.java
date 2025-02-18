// Generated from /Users/tangsheng/Desktop/CodeProjects/XMLDatabaseParser/grammar/XPath.g4 by ANTLR 4.13.1

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
			null, "'('", "'/'", "'//'", "'['", "']'", null, "'*'", "'.'", "'..'", 
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
	public String getGrammarFileName() { return "XPath.g4"; }

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
		"\u0004\u0000\u0017\u0087\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005F\b\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0005\u0010"+
		"f\b\u0010\n\u0010\f\u0010i\t\u0010\u0001\u0010\u0001\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0005\u0014z\b\u0014\n\u0014\f\u0014}\t\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0016\u0004\u0016\u0082\b\u0016\u000b\u0016\f\u0016\u0083\u0001"+
		"\u0016\u0001\u0016\u0000\u0000\u0017\u0001\u0001\u0003\u0002\u0005\u0003"+
		"\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015"+
		"\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012"+
		"%\u0013\'\u0014)\u0015+\u0016-\u0017\u0001\u0000\u0004\u0001\u0000\"\""+
		"\u0003\u0000AZ__az\u0005\u0000--09AZ__az\u0003\u0000\t\n\r\r  \u008a\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0001/"+
		"\u0001\u0000\u0000\u0000\u00031\u0001\u0000\u0000\u0000\u00053\u0001\u0000"+
		"\u0000\u0000\u00076\u0001\u0000\u0000\u0000\t8\u0001\u0000\u0000\u0000"+
		"\u000bE\u0001\u0000\u0000\u0000\rG\u0001\u0000\u0000\u0000\u000fI\u0001"+
		"\u0000\u0000\u0000\u0011K\u0001\u0000\u0000\u0000\u0013N\u0001\u0000\u0000"+
		"\u0000\u0015T\u0001\u0000\u0000\u0000\u0017V\u0001\u0000\u0000\u0000\u0019"+
		"X\u0001\u0000\u0000\u0000\u001bZ\u0001\u0000\u0000\u0000\u001d]\u0001"+
		"\u0000\u0000\u0000\u001f`\u0001\u0000\u0000\u0000!c\u0001\u0000\u0000"+
		"\u0000#l\u0001\u0000\u0000\u0000%p\u0001\u0000\u0000\u0000\'s\u0001\u0000"+
		"\u0000\u0000)w\u0001\u0000\u0000\u0000+~\u0001\u0000\u0000\u0000-\u0081"+
		"\u0001\u0000\u0000\u0000/0\u0005(\u0000\u00000\u0002\u0001\u0000\u0000"+
		"\u000012\u0005/\u0000\u00002\u0004\u0001\u0000\u0000\u000034\u0005/\u0000"+
		"\u000045\u0005/\u0000\u00005\u0006\u0001\u0000\u0000\u000067\u0005[\u0000"+
		"\u00007\b\u0001\u0000\u0000\u000089\u0005]\u0000\u00009\n\u0001\u0000"+
		"\u0000\u0000:;\u0005d\u0000\u0000;<\u0005o\u0000\u0000<F\u0005c\u0000"+
		"\u0000=>\u0005d\u0000\u0000>?\u0005o\u0000\u0000?@\u0005c\u0000\u0000"+
		"@A\u0005u\u0000\u0000AB\u0005m\u0000\u0000BC\u0005e\u0000\u0000CD\u0005"+
		"n\u0000\u0000DF\u0005t\u0000\u0000E:\u0001\u0000\u0000\u0000E=\u0001\u0000"+
		"\u0000\u0000F\f\u0001\u0000\u0000\u0000GH\u0005*\u0000\u0000H\u000e\u0001"+
		"\u0000\u0000\u0000IJ\u0005.\u0000\u0000J\u0010\u0001\u0000\u0000\u0000"+
		"KL\u0005.\u0000\u0000LM\u0005.\u0000\u0000M\u0012\u0001\u0000\u0000\u0000"+
		"NO\u0005t\u0000\u0000OP\u0005e\u0000\u0000PQ\u0005x\u0000\u0000QR\u0005"+
		"t\u0000\u0000RS\u0005(\u0000\u0000S\u0014\u0001\u0000\u0000\u0000TU\u0005"+
		")\u0000\u0000U\u0016\u0001\u0000\u0000\u0000VW\u0005@\u0000\u0000W\u0018"+
		"\u0001\u0000\u0000\u0000XY\u0005=\u0000\u0000Y\u001a\u0001\u0000\u0000"+
		"\u0000Z[\u0005e\u0000\u0000[\\\u0005q\u0000\u0000\\\u001c\u0001\u0000"+
		"\u0000\u0000]^\u0005=\u0000\u0000^_\u0005=\u0000\u0000_\u001e\u0001\u0000"+
		"\u0000\u0000`a\u0005i\u0000\u0000ab\u0005s\u0000\u0000b \u0001\u0000\u0000"+
		"\u0000cg\u0005\"\u0000\u0000df\b\u0000\u0000\u0000ed\u0001\u0000\u0000"+
		"\u0000fi\u0001\u0000\u0000\u0000ge\u0001\u0000\u0000\u0000gh\u0001\u0000"+
		"\u0000\u0000hj\u0001\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000jk\u0005"+
		"\"\u0000\u0000k\"\u0001\u0000\u0000\u0000lm\u0005a\u0000\u0000mn\u0005"+
		"n\u0000\u0000no\u0005d\u0000\u0000o$\u0001\u0000\u0000\u0000pq\u0005o"+
		"\u0000\u0000qr\u0005r\u0000\u0000r&\u0001\u0000\u0000\u0000st\u0005n\u0000"+
		"\u0000tu\u0005o\u0000\u0000uv\u0005t\u0000\u0000v(\u0001\u0000\u0000\u0000"+
		"w{\u0007\u0001\u0000\u0000xz\u0007\u0002\u0000\u0000yx\u0001\u0000\u0000"+
		"\u0000z}\u0001\u0000\u0000\u0000{y\u0001\u0000\u0000\u0000{|\u0001\u0000"+
		"\u0000\u0000|*\u0001\u0000\u0000\u0000}{\u0001\u0000\u0000\u0000~\u007f"+
		"\u0005,\u0000\u0000\u007f,\u0001\u0000\u0000\u0000\u0080\u0082\u0007\u0003"+
		"\u0000\u0000\u0081\u0080\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000"+
		"\u0000\u0000\u0083\u0081\u0001\u0000\u0000\u0000\u0083\u0084\u0001\u0000"+
		"\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0086\u0006\u0016"+
		"\u0000\u0000\u0086.\u0001\u0000\u0000\u0000\u0005\u0000Eg{\u0083\u0001"+
		"\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}