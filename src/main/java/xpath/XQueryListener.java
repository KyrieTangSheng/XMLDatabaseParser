// Generated from XQuery.g4 by ANTLR 4.13.2

package xpath;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XQueryParser}.
 */
public interface XQueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code XQueryParentheses}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void enterXQueryParentheses(XQueryParser.XQueryParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XQueryParentheses}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void exitXQueryParentheses(XQueryParser.XQueryParenthesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XQueryVariable}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void enterXQueryVariable(XQueryParser.XQueryVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XQueryVariable}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void exitXQueryVariable(XQueryParser.XQueryVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XQueryFLWR}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void enterXQueryFLWR(XQueryParser.XQueryFLWRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XQueryFLWR}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void exitXQueryFLWR(XQueryParser.XQueryFLWRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XQueryConstant}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void enterXQueryConstant(XQueryParser.XQueryConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XQueryConstant}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void exitXQueryConstant(XQueryParser.XQueryConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XQueryPath}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void enterXQueryPath(XQueryParser.XQueryPathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XQueryPath}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void exitXQueryPath(XQueryParser.XQueryPathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XQueryTag}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void enterXQueryTag(XQueryParser.XQueryTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XQueryTag}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void exitXQueryTag(XQueryParser.XQueryTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XQueryLet}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void enterXQueryLet(XQueryParser.XQueryLetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XQueryLet}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void exitXQueryLet(XQueryParser.XQueryLetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XQueryAbsolutePath}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void enterXQueryAbsolutePath(XQueryParser.XQueryAbsolutePathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XQueryAbsolutePath}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void exitXQueryAbsolutePath(XQueryParser.XQueryAbsolutePathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XQueryConcat}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void enterXQueryConcat(XQueryParser.XQueryConcatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XQueryConcat}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void exitXQueryConcat(XQueryParser.XQueryConcatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XQueryDoubleSlash}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void enterXQueryDoubleSlash(XQueryParser.XQueryDoubleSlashContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XQueryDoubleSlash}
	 * labeled alternative in {@link XQueryParser#xquery}.
	 * @param ctx the parse tree
	 */
	void exitXQueryDoubleSlash(XQueryParser.XQueryDoubleSlashContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#forClause}.
	 * @param ctx the parse tree
	 */
	void enterForClause(XQueryParser.ForClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#forClause}.
	 * @param ctx the parse tree
	 */
	void exitForClause(XQueryParser.ForClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#letClause}.
	 * @param ctx the parse tree
	 */
	void enterLetClause(XQueryParser.LetClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#letClause}.
	 * @param ctx the parse tree
	 */
	void exitLetClause(XQueryParser.LetClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(XQueryParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(XQueryParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void enterReturnClause(XQueryParser.ReturnClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void exitReturnClause(XQueryParser.ReturnClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(XQueryParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(XQueryParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XQueryCondParentheses}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterXQueryCondParentheses(XQueryParser.XQueryCondParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XQueryCondParentheses}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitXQueryCondParentheses(XQueryParser.XQueryCondParenthesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XQueryValueEqual}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterXQueryValueEqual(XQueryParser.XQueryValueEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XQueryValueEqual}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitXQueryValueEqual(XQueryParser.XQueryValueEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XQueryEmpty}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterXQueryEmpty(XQueryParser.XQueryEmptyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XQueryEmpty}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitXQueryEmpty(XQueryParser.XQueryEmptyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XQuerySome}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterXQuerySome(XQueryParser.XQuerySomeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XQuerySome}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitXQuerySome(XQueryParser.XQuerySomeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XQueryCondAnd}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterXQueryCondAnd(XQueryParser.XQueryCondAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XQueryCondAnd}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitXQueryCondAnd(XQueryParser.XQueryCondAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XQueryIdentityEqual}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterXQueryIdentityEqual(XQueryParser.XQueryIdentityEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XQueryIdentityEqual}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitXQueryIdentityEqual(XQueryParser.XQueryIdentityEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XQueryCondNot}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterXQueryCondNot(XQueryParser.XQueryCondNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XQueryCondNot}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitXQueryCondNot(XQueryParser.XQueryCondNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XQueryCondOr}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterXQueryCondOr(XQueryParser.XQueryCondOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XQueryCondOr}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitXQueryCondOr(XQueryParser.XQueryCondOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#stringConstant}.
	 * @param ctx the parse tree
	 */
	void enterStringConstant(XQueryParser.StringConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#stringConstant}.
	 * @param ctx the parse tree
	 */
	void exitStringConstant(XQueryParser.StringConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#xpath}.
	 * @param ctx the parse tree
	 */
	void enterXpath(XQueryParser.XpathContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#xpath}.
	 * @param ctx the parse tree
	 */
	void exitXpath(XQueryParser.XpathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AbsoluteSlash}
	 * labeled alternative in {@link XQueryParser#absolutePath}.
	 * @param ctx the parse tree
	 */
	void enterAbsoluteSlash(XQueryParser.AbsoluteSlashContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AbsoluteSlash}
	 * labeled alternative in {@link XQueryParser#absolutePath}.
	 * @param ctx the parse tree
	 */
	void exitAbsoluteSlash(XQueryParser.AbsoluteSlashContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AbsoluteDoubleSlash}
	 * labeled alternative in {@link XQueryParser#absolutePath}.
	 * @param ctx the parse tree
	 */
	void enterAbsoluteDoubleSlash(XQueryParser.AbsoluteDoubleSlashContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AbsoluteDoubleSlash}
	 * labeled alternative in {@link XQueryParser#absolutePath}.
	 * @param ctx the parse tree
	 */
	void exitAbsoluteDoubleSlash(XQueryParser.AbsoluteDoubleSlashContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AllChildren}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterAllChildren(XQueryParser.AllChildrenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AllChildren}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitAllChildren(XQueryParser.AllChildrenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TextFunc}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterTextFunc(XQueryParser.TextFuncContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TextFunc}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitTextFunc(XQueryParser.TextFuncContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TagNameMatch}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterTagNameMatch(XQueryParser.TagNameMatchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TagNameMatch}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitTagNameMatch(XQueryParser.TagNameMatchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parent}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterParent(XQueryParser.ParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parent}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitParent(XQueryParser.ParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Attribute}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(XQueryParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Attribute}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(XQueryParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Self}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterSelf(XQueryParser.SelfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Self}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitSelf(XQueryParser.SelfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpConcat}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterRpConcat(XQueryParser.RpConcatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpConcat}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitRpConcat(XQueryParser.RpConcatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpGrouping}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterRpGrouping(XQueryParser.RpGroupingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpGrouping}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitRpGrouping(XQueryParser.RpGroupingContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpSlash}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterRpSlash(XQueryParser.RpSlashContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpSlash}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitRpSlash(XQueryParser.RpSlashContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpDoubleSlash}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterRpDoubleSlash(XQueryParser.RpDoubleSlashContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpDoubleSlash}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitRpDoubleSlash(XQueryParser.RpDoubleSlashContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpFilter}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterRpFilter(XQueryParser.RpFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpFilter}
	 * labeled alternative in {@link XQueryParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitRpFilter(XQueryParser.RpFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterAndFilter(XQueryParser.AndFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitAndFilter(XQueryParser.AndFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterNotFilter(XQueryParser.NotFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitNotFilter(XQueryParser.NotFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualityFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterEqualityFilter(XQueryParser.EqualityFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualityFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitEqualityFilter(XQueryParser.EqualityFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterStringFilter(XQueryParser.StringFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitStringFilter(XQueryParser.StringFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FilterGrouping}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilterGrouping(XQueryParser.FilterGroupingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FilterGrouping}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilterGrouping(XQueryParser.FilterGroupingContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OrFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterOrFilter(XQueryParser.OrFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OrFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitOrFilter(XQueryParser.OrFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpInFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterRpInFilter(XQueryParser.RpInFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpInFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitRpInFilter(XQueryParser.RpInFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdentityFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterIdentityFilter(XQueryParser.IdentityFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdentityFilter}
	 * labeled alternative in {@link XQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitIdentityFilter(XQueryParser.IdentityFilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#tagName}.
	 * @param ctx the parse tree
	 */
	void enterTagName(XQueryParser.TagNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#tagName}.
	 * @param ctx the parse tree
	 */
	void exitTagName(XQueryParser.TagNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#attributeName}.
	 * @param ctx the parse tree
	 */
	void enterAttributeName(XQueryParser.AttributeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#attributeName}.
	 * @param ctx the parse tree
	 */
	void exitAttributeName(XQueryParser.AttributeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#fileName}.
	 * @param ctx the parse tree
	 */
	void enterFileName(XQueryParser.FileNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#fileName}.
	 * @param ctx the parse tree
	 */
	void exitFileName(XQueryParser.FileNameContext ctx);
}