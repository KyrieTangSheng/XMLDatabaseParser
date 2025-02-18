grammar XQuery;

import XPath;

// ---------------------
// XQuery entry rule
// ---------------------
xquery
    : var                                                   # XQueryVariable
    | stringConstant                                        # XQueryConstant
    | absolutePath                                          # XQueryAbsolutePath
    | '(' xquery ')'                                        # XQueryParentheses
    | xquery ',' xquery                                     # XQueryConcat
    | xquery '/' relativePath                               # XQueryPath
    | xquery '//' relativePath                              # XQueryDoubleSlash
    | '<' tagName '>' '{' xquery '}' '</' tagName '>'       # XQueryTag
    | forClause letClause? whereClause? returnClause        # XQueryFLWR
    | letClause xquery                                      # XQueryLet
    ;

// ---------------------
// FLWR clauses
// ---------------------
forClause
    : 'for' var 'in' xquery ( ',' var 'in' xquery )*
    ;

letClause
    : 'let' var ':=' xquery ( ',' var ':=' xquery )*
    ;

whereClause
    : 'where' cond
    ;

returnClause
    : 'return' xquery
    ;

// ---------------------
// Variables, conditions
// ---------------------
var
    : '$' Name
    ;

cond
    : xquery ('=' | 'eq') xquery                                 # XQueryValueEqual
    | xquery ('==' | 'is') xquery                                # XQueryIdentityEqual
    | 'empty' '(' xquery ')'                                     # XQueryEmpty
    | 'some' var 'in' xquery ( ',' var 'in' xquery )*
      'satisfies' cond                                           # XQuerySome
    | '(' cond ')'                                               # XQueryCondParentheses
    | cond 'and' cond                                            # XQueryCondAnd
    | cond 'or' cond                                             # XQueryCondOr
    | 'not' cond                                                 # XQueryCondNot
    ;

// ---------------------
// String constants
// (reusing StringConstant from XPath.g4)
// ---------------------
stringConstant
    : StringConstant
    ;

// ---------------------
// We skip whitespace
// ---------------------
WS
    : [ \t\r\n]+ -> skip
    ;
