grammar XPath;
@header {
package my.xpath.parser;
}

// -------------------------------------------------------------------
// PARSER RULES
// -------------------------------------------------------------------

/*
 * For convenience, let's define a single top-level parse rule
 * that can handle either absolutePath or relativePath. In practice,
 * you might want separate entry points or more refined ones.
 */
xpath
    : absolutePath
    | relativePath
    ;

absolutePath
    : 'doc' '(' fileName ')' '/' relativePath                     # AbsoluteSlash
    | 'doc' '(' fileName ')' '//' relativePath                    # AbsoluteDoubleSlash
    ;

/*
 * The relativePath is basically the union of all rp constructs
 * in your grammar. We keep them in multiple alternatives with
 * labeled rules or references to sub-rules.
 */
relativePath
    : tagName                                                  # TagNameMatch
    | STAR                                                     # AllChildren
    | DOT                                                      # Self
    | DOTDOT                                                   # Parent
    | TEXTOPEN CLOSE                                          # TextFunc
    | AT attributeName                                        # Attribute
    | '(' relativePath ')'                                    # RpGrouping
    | relativePath '/' relativePath                           # RpSlash
    | relativePath '//' relativePath                          # RpDoubleSlash
    | relativePath '[' filter ']'                             # RpFilter
    | relativePath ',' relativePath                           # RpConcat
    ;

filter
    : relativePath                                            # RpInFilter
    | relativePath (EQ | EQVALUE) relativePath                # EqualityFilter
    | relativePath (ISEQ | ISVALUE) relativePath              # IdentityFilter
    | relativePath EQ StringConstant                          # StringFilter
    | '(' filter ')'                                          # FilterGrouping
    | filter 'and' filter                                     # AndFilter
    | filter 'or' filter                                      # OrFilter
    | 'not' filter                                            # NotFilter
    ;

// -------------------------------------------------------------------
// LEXER RULES
// -------------------------------------------------------------------

DOC         : 'doc';
STAR        : '*';
DOT         : '.';
DOTDOT      : '..';
TEXTOPEN    : 'text(';
CLOSE       : ')';
AT          : '@';

// equality operators
EQ          : '=';
EQVALUE     : 'eq';
ISEQ        : '==';
ISVALUE     : 'is';

StringConstant
    : '"' (~["])* '"'
    ;

// keywords
AND         : 'and';
OR          : 'or';
NOT         : 'not';

// For simplicity, let's say tagName and attributeName match identifiers
tagName
    : Name
    ;
attributeName
    : Name
    ;

/*
 * fileName is a simplistic parse for something like "filename.xml".
 * Adjust as needed for your environment.
 */
fileName
    : StringConstant
    ;

Name
    : [a-zA-Z_][a-zA-Z0-9_-]*
    ;

// symbols
COMMA       : ',';

// skip whitespace
WS
    : [ \t\r\n]+ -> skip
    ;
