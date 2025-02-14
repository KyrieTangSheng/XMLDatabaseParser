// Generated from XQuery.g4 by ANTLR 4.13.2

package xpath;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link XQueryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface XQueryVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code XQueryParentheses}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXQueryParentheses(XQueryParser.XQueryParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XQueryVariable}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXQueryVariable(XQueryParser.XQueryVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XQueryFLWR}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXQueryFLWR(XQueryParser.XQueryFLWRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XQueryConstant}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXQueryConstant(XQueryParser.XQueryConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XQueryPath}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXQueryPath(XQueryParser.XQueryPathContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XQueryTag}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXQueryTag(XQueryParser.XQueryTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XQueryLet}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXQueryLet(XQueryParser.XQueryLetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XQueryAbsolutePath}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXQueryAbsolutePath(XQueryParser.XQueryAbsolutePathContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XQueryConcat}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXQueryConcat(XQueryParser.XQueryConcatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XQueryDoubleSlash}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXQueryDoubleSlash(XQueryParser.XQueryDoubleSlashContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#forClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForClause(XQueryParser.ForClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#letClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetClause(XQueryParser.LetClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#whereClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereClause(XQueryParser.WhereClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#returnClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnClause(XQueryParser.ReturnClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(XQueryParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XQueryCondParentheses}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXQueryCondParentheses(XQueryParser.XQueryCondParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XQueryValueEqual}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXQueryValueEqual(XQueryParser.XQueryValueEqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XQueryEmpty}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXQueryEmpty(XQueryParser.XQueryEmptyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XQuerySome}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXQuerySome(XQueryParser.XQuerySomeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XQueryCondAnd}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXQueryCondAnd(XQueryParser.XQueryCondAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XQueryIdentityEqual}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXQueryIdentityEqual(XQueryParser.XQueryIdentityEqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XQueryCondNot}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXQueryCondNot(XQueryParser.XQueryCondNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XQueryCondOr}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXQueryCondOr(XQueryParser.XQueryCondOrContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#stringConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringConstant(XQueryParser.StringConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#xpath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXpath(XQueryParser.XpathContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AbsoluteSlash}
	 * labeled alternative in {@link XQueryParser#absolutePath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbsoluteSlash(XQueryParser.AbsoluteSlashContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AbsoluteDoubleSlash}
	 * labeled alternative in {@link XQueryParser#absolutePath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbsoluteDoubleSlash(XQueryParser.AbsoluteDoubleSlashContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AllChildren}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllChildren(XQueryParser.AllChildrenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TextFunc}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextFunc(XQueryParser.TextFuncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TagNameMatch}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagNameMatch(XQueryParser.TagNameMatchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parent}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParent(XQueryParser.ParentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Attribute}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute(XQueryParser.AttributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Self}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelf(XQueryParser.SelfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RpConcat}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpConcat(XQueryParser.RpConcatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RpGrouping}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpGrouping(XQueryParser.RpGroupingContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RpSlash}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpSlash(XQueryParser.RpSlashContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RpDoubleSlash}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpDoubleSlash(XQueryParser.RpDoubleSlashContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RpFilter}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpFilter(XQueryParser.RpFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndFilter(XQueryParser.AndFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotFilter(XQueryParser.NotFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualityFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityFilter(XQueryParser.EqualityFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringFilter(XQueryParser.StringFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FilterGrouping}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilterGrouping(XQueryParser.FilterGroupingContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OrFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrFilter(XQueryParser.OrFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RpInFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpInFilter(XQueryParser.RpInFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdentityFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentityFilter(XQueryParser.IdentityFilterContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#tagName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagName(XQueryParser.TagNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#attributeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeName(XQueryParser.AttributeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#fileName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileName(XQueryParser.FileNameContext ctx);
}