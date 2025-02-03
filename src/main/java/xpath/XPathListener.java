// Generated from XPath.g4 by ANTLR 4.13.2

package xpath;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XPathParser}.
 */
public interface XPathListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link XPathParser#xpath}.
	 * @param ctx the parse tree
	 */
	void enterXpath(XPathParser.XpathContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#xpath}.
	 * @param ctx the parse tree
	 */
	void exitXpath(XPathParser.XpathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AbsoluteSlash}
	 * labeled alternative in {@link XPathParser#absolutePath}.
	 * @param ctx the parse tree
	 */
	void enterAbsoluteSlash(XPathParser.AbsoluteSlashContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AbsoluteSlash}
	 * labeled alternative in {@link XPathParser#absolutePath}.
	 * @param ctx the parse tree
	 */
	void exitAbsoluteSlash(XPathParser.AbsoluteSlashContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AbsoluteDoubleSlash}
	 * labeled alternative in {@link XPathParser#absolutePath}.
	 * @param ctx the parse tree
	 */
	void enterAbsoluteDoubleSlash(XPathParser.AbsoluteDoubleSlashContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AbsoluteDoubleSlash}
	 * labeled alternative in {@link XPathParser#absolutePath}.
	 * @param ctx the parse tree
	 */
	void exitAbsoluteDoubleSlash(XPathParser.AbsoluteDoubleSlashContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AllChildren}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterAllChildren(XPathParser.AllChildrenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AllChildren}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitAllChildren(XPathParser.AllChildrenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TextFunc}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterTextFunc(XPathParser.TextFuncContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TextFunc}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitTextFunc(XPathParser.TextFuncContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TagNameMatch}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterTagNameMatch(XPathParser.TagNameMatchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TagNameMatch}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitTagNameMatch(XPathParser.TagNameMatchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parent}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterParent(XPathParser.ParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parent}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitParent(XPathParser.ParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Attribute}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(XPathParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Attribute}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(XPathParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Self}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterSelf(XPathParser.SelfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Self}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitSelf(XPathParser.SelfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpConcat}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterRpConcat(XPathParser.RpConcatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpConcat}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitRpConcat(XPathParser.RpConcatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpGrouping}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterRpGrouping(XPathParser.RpGroupingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpGrouping}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitRpGrouping(XPathParser.RpGroupingContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpSlash}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterRpSlash(XPathParser.RpSlashContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpSlash}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitRpSlash(XPathParser.RpSlashContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpDoubleSlash}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterRpDoubleSlash(XPathParser.RpDoubleSlashContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpDoubleSlash}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitRpDoubleSlash(XPathParser.RpDoubleSlashContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpFilter}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void enterRpFilter(XPathParser.RpFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpFilter}
	 * labeled alternative in {@link XPathParser#relativePath}.
	 * @param ctx the parse tree
	 */
	void exitRpFilter(XPathParser.RpFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndFilter}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterAndFilter(XPathParser.AndFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndFilter}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitAndFilter(XPathParser.AndFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotFilter}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterNotFilter(XPathParser.NotFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotFilter}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitNotFilter(XPathParser.NotFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualityFilter}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterEqualityFilter(XPathParser.EqualityFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualityFilter}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitEqualityFilter(XPathParser.EqualityFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringFilter}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterStringFilter(XPathParser.StringFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringFilter}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitStringFilter(XPathParser.StringFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FilterGrouping}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilterGrouping(XPathParser.FilterGroupingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FilterGrouping}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilterGrouping(XPathParser.FilterGroupingContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OrFilter}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterOrFilter(XPathParser.OrFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OrFilter}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitOrFilter(XPathParser.OrFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RpInFilter}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterRpInFilter(XPathParser.RpInFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RpInFilter}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitRpInFilter(XPathParser.RpInFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdentityFilter}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterIdentityFilter(XPathParser.IdentityFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdentityFilter}
	 * labeled alternative in {@link XPathParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitIdentityFilter(XPathParser.IdentityFilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#tagName}.
	 * @param ctx the parse tree
	 */
	void enterTagName(XPathParser.TagNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#tagName}.
	 * @param ctx the parse tree
	 */
	void exitTagName(XPathParser.TagNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#attributeName}.
	 * @param ctx the parse tree
	 */
	void enterAttributeName(XPathParser.AttributeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#attributeName}.
	 * @param ctx the parse tree
	 */
	void exitAttributeName(XPathParser.AttributeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#fileName}.
	 * @param ctx the parse tree
	 */
	void enterFileName(XPathParser.FileNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#fileName}.
	 * @param ctx the parse tree
	 */
	void exitFileName(XPathParser.FileNameContext ctx);
}