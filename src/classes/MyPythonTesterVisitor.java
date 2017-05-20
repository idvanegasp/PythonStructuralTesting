// Generated from /Users/neillgiraldo/IdeaProjects/PythonTester/Python3.g4 by ANTLR 4.7
package classes;
import CFGController.ControlFlowGraph;
import CFGController.Node;
import GUI.PythonTesterGUI;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

import javax.naming.LimitExceededException;
import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * This class provides an empty implementation of {@link Python3Visitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class MyPythonTesterVisitor<T> extends AbstractParseTreeVisitor<T> implements Python3Visitor<T> {
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */

    ControlFlowGraph cfg = new ControlFlowGraph();
    Stack<Node> bifurquedNodes = new Stack<>();
    Integer val = 0;
    HashMap<Integer, LinkedList<Node>> terminal_nodes = new HashMap<>();
    LinkedList<Node> breaks = new LinkedList<>();
    LinkedList<Node> auxNodes = new LinkedList<>();

    @Override public T visitSingle_input(Python3Parser.Single_inputContext ctx) {

        System.out.println(ctx.getText());

        if(ctx.simple_stmt() != null){
            visitSimple_stmt(ctx.simple_stmt());
        }

        if(ctx.compound_stmt() != null){
            visitCompound_stmt(ctx.compound_stmt());
        }

        return visitChildren(ctx);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitFile_input(Python3Parser.File_inputContext ctx) {
        for(Python3Parser.StmtContext stmt: ctx.stmt()) {
            if (stmt != null) {
                visitStmt(stmt);
            }

        }
        cfg.printTree();
        double height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        double width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        PythonTesterGUI frame = new PythonTesterGUI(cfg,(int)height,(int)width);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int)width, (int)height);
        frame.setVisible(true);
        return null; }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitEval_input(Python3Parser.Eval_inputContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitDecorator(Python3Parser.DecoratorContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitDecorators(Python3Parser.DecoratorsContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitDecorated(Python3Parser.DecoratedContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitFuncdef(Python3Parser.FuncdefContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitParameters(Python3Parser.ParametersContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitTypedargslist(Python3Parser.TypedargslistContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitTfpdef(Python3Parser.TfpdefContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitVarargslist(Python3Parser.VarargslistContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitVfpdef(Python3Parser.VfpdefContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitStmt(Python3Parser.StmtContext ctx) {
        if(ctx.simple_stmt() != null){
            if(auxNodes.size() == 0) {
                cfg.addSequenceNode(ctx.simple_stmt().getText(), ctx.simple_stmt());
                if(cfg.getCurrentNode().getData().toLowerCase().compareTo("break ") == 0){
                    breaks.add(cfg.getCurrentNode());
                }
            }else{
                try {
                    cfg.replace_aux_Node(auxNodes.pop(),ctx.simple_stmt().getText(), ctx.simple_stmt());
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            for(Integer key:terminal_nodes.keySet()){
                if(key > val){
                    System.out.println(key);
                    for(Node node:terminal_nodes.get(key)){
                        if(!cfg.getCurrentNode().getParent().contains(node)) {
                            cfg.getCurrentNode().setParent(node);
                        }
                    }
                    terminal_nodes.remove(key);
                }
            }
            //System.out.println("Will visit statement: \n"+ctx.simple_stmt().getText());
            //visitSimple_stmt(ctx.simple_stmt());
        }

        if(ctx.compound_stmt() != null){

            visitCompound_stmt(ctx.compound_stmt());
        }
        return null;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitSimple_stmt(Python3Parser.Simple_stmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitSmall_stmt(Python3Parser.Small_stmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitExpr_stmt(Python3Parser.Expr_stmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitTestlist_star_expr(Python3Parser.Testlist_star_exprContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitAugassign(Python3Parser.AugassignContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitDel_stmt(Python3Parser.Del_stmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitPass_stmt(Python3Parser.Pass_stmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitFlow_stmt(Python3Parser.Flow_stmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitBreak_stmt(Python3Parser.Break_stmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitContinue_stmt(Python3Parser.Continue_stmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitReturn_stmt(Python3Parser.Return_stmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitYield_stmt(Python3Parser.Yield_stmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitRaise_stmt(Python3Parser.Raise_stmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitImport_stmt(Python3Parser.Import_stmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitImport_name(Python3Parser.Import_nameContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitImport_from(Python3Parser.Import_fromContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitImport_as_name(Python3Parser.Import_as_nameContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitDotted_as_name(Python3Parser.Dotted_as_nameContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitImport_as_names(Python3Parser.Import_as_namesContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitDotted_as_names(Python3Parser.Dotted_as_namesContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitDotted_name(Python3Parser.Dotted_nameContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitGlobal_stmt(Python3Parser.Global_stmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitNonlocal_stmt(Python3Parser.Nonlocal_stmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitAssert_stmt(Python3Parser.Assert_stmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitCompound_stmt(Python3Parser.Compound_stmtContext ctx) {
        if(ctx.if_stmt() != null){
            visitIf_stmt(ctx.if_stmt());
        }
        if(ctx.while_stmt() != null){
            visitWhile_stmt(ctx.while_stmt());
        }
        return null;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitIf_stmt(Python3Parser.If_stmtContext ctx) {



        if(ctx.IF() != null){
            if(ctx.test(0) != null){
                cfg.addSequenceNode(ctx.IF().getText() + " " + ctx.test(0).getText(),ctx.test(0));
                bifurquedNodes.add(cfg.getCurrentNode());
                System.out.println("Bifurqued if");
            }
            for(Integer key:terminal_nodes.keySet()){
                if(key > val){
                    System.out.println(key);
                    for(Node node:terminal_nodes.get(key)){
                        if(!cfg.getCurrentNode().getParent().contains(node)) {
                            cfg.getCurrentNode().setParent(node);
                        }
                    }
                    terminal_nodes.remove(key);
                }
            }

            val = val + 1;


            if(ctx.suite(0) != null) {
                visitSuite(ctx.suite(0));
            }
            if(!terminal_nodes.containsKey(val)){
                terminal_nodes.put(val,new LinkedList());
            }
            terminal_nodes.get(val).add(cfg.getCurrentNode());

        }
        if(ctx.ELIF() != null){
            int index = 0;

            while(index < ctx.ELIF().size()){
                Node<T> bifurqued = bifurquedNodes.pop();
                if(ctx.test(index+1) != null) {
                    cfg.addBifurcationOnNode(bifurqued,ctx.ELIF(index).getText()+" "+ctx.test(index+1).getText(),ctx.test(index+1));
                    bifurquedNodes.add(cfg.getCurrentNode());
                    System.out.println("Bifurqued elif");
                }
                if(ctx.suite(index+1) != null) {
                    visitSuite(ctx.suite(index+1));
                }
                if(!terminal_nodes.containsKey(val)){
                    terminal_nodes.put(val,new LinkedList());
                }
                terminal_nodes.get(val).add(cfg.getCurrentNode());
                index = index + 1;
            }
        }

        if(ctx.ELSE() != null){
            Node<T> bifurqued = bifurquedNodes.pop();
            cfg.setCurrentNode(bifurqued);
            if(ctx.suite(ctx.suite().size()-1) != null) {
                visitSuite(ctx.suite(ctx.suite().size()-1));
            }
            if(!terminal_nodes.containsKey(val)){
                terminal_nodes.put(val,new LinkedList());
            }
            terminal_nodes.get(val).add(cfg.getCurrentNode());
        }

        val = val -1;

        return null;

    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitWhile_stmt(Python3Parser.While_stmtContext ctx) {
        if(ctx.WHILE() != null){
            if(ctx.test() != null){
                cfg.addSequenceNode(ctx.WHILE().getText() + " " + ctx.test().getText(),ctx.test());
                bifurquedNodes.add(cfg.getCurrentNode());
                System.out.println("Bifurqued While");
            }

            cfg.addSequenceNode("Not_valid",ctx);
            Node<T> auxNode = cfg.getCurrentNode();

            if(ctx.suite(0) != null) {
                Node<T> bifurqued = bifurquedNodes.pop();
                cfg.setCurrentNode(bifurqued);
                visitSuite(ctx.suite(0));
                cfg.pointAlreadyCreatedNode(bifurqued);
                for(Integer key:terminal_nodes.keySet()){
                    if(key > val){
                        System.out.println(key);
                        for(Node mynode:terminal_nodes.get(key)){
                            if(!cfg.getCurrentNode().getParent().contains(mynode)) {
                                cfg.getCurrentNode().setParent(mynode);
                            }
                        }
                        terminal_nodes.remove(key);
                    }
                }
            }

            for(Node node: breaks){
                while(node.getChildren().size() != 0){
                    node.getChildren().remove(0);
                }
            }
            auxNodes.add(auxNode);

        }
        return null;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitFor_stmt(Python3Parser.For_stmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitTry_stmt(Python3Parser.Try_stmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitWith_stmt(Python3Parser.With_stmtContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitWith_item(Python3Parser.With_itemContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitExcept_clause(Python3Parser.Except_clauseContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitSuite(Python3Parser.SuiteContext ctx) {

        if(ctx.stmt() != null){
            for(Python3Parser.StmtContext stmtContext: ctx.stmt()) {
                visitStmt(stmtContext);
            }
        }

        return null;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitTest(Python3Parser.TestContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitTest_nocond(Python3Parser.Test_nocondContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitLambdef(Python3Parser.LambdefContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitLambdef_nocond(Python3Parser.Lambdef_nocondContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitOr_test(Python3Parser.Or_testContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitAnd_test(Python3Parser.And_testContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitNot_test(Python3Parser.Not_testContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitComparison(Python3Parser.ComparisonContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitComp_op(Python3Parser.Comp_opContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitStar_expr(Python3Parser.Star_exprContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitExpr(Python3Parser.ExprContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitXor_expr(Python3Parser.Xor_exprContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitAnd_expr(Python3Parser.And_exprContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitShift_expr(Python3Parser.Shift_exprContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitArith_expr(Python3Parser.Arith_exprContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitTerm(Python3Parser.TermContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitFactor(Python3Parser.FactorContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitPower(Python3Parser.PowerContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitAtom(Python3Parser.AtomContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitTestlist_comp(Python3Parser.Testlist_compContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitTrailer(Python3Parser.TrailerContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitSubscriptlist(Python3Parser.SubscriptlistContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitSubscript(Python3Parser.SubscriptContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitSliceop(Python3Parser.SliceopContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitExprlist(Python3Parser.ExprlistContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitTestlist(Python3Parser.TestlistContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitDictorsetmaker(Python3Parser.DictorsetmakerContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitClassdef(Python3Parser.ClassdefContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitArglist(Python3Parser.ArglistContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitArgument(Python3Parser.ArgumentContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitComp_iter(Python3Parser.Comp_iterContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitComp_for(Python3Parser.Comp_forContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitComp_if(Python3Parser.Comp_ifContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitYield_expr(Python3Parser.Yield_exprContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitYield_arg(Python3Parser.Yield_argContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitStr(Python3Parser.StrContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitNumber(Python3Parser.NumberContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitInteger(Python3Parser.IntegerContext ctx) { return visitChildren(ctx); }
}