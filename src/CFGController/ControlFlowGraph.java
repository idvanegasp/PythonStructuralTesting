package CFGController;

import classes.Python3Parser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by neillgiraldo on 5/3/17.
 */
public class ControlFlowGraph<T> {

    Node<T> root = new Node<>(0,"Begin",null,null);
    Node<T> currentNode = root;
    Integer id = 1;

    public ControlFlowGraph(){

    }

    public void addSequenceNode(String data, ParserRuleContext context){
        Node<T> newNode = new Node(this.id,data,currentNode,context);
        currentNode = newNode;
        this.id = this.id + 1;
    }

    public Node<T> addBifurcationOnNode(Node<T> node,String data,ParserRuleContext context){
        Node auxNode = currentNode;
        currentNode = node;
        Node<T> newNode = new Node<T>(this.id,data,currentNode,context);
        currentNode = newNode;
        this.id = this.id +1;
        return auxNode;
    }

    public void setCurrentNode(Node node){
        this.currentNode = node;
    }
    public Node<T> getCurrentNode(){
        return currentNode;
    }

    public Node<T> getRoot(){
        return root;
    }
    public void printTree(){
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        Node current;
        while(queue.size() != 0){
            current = queue.remove();
            System.out.println("Node Parent: "+current.getData());
            System.out.println("Children:");
            Node current_child;
            for(Object child:current.getChildren()){
                current_child = (Node) child;
                System.out.println("   "+ current_child.getData());
                if (current_child.getContextClass().equals(Python3Parser.Simple_stmtContext.class)) {
                    Python3Parser.Simple_stmtContext cont = (Python3Parser.Simple_stmtContext) current_child.getContext();
                    System.out.println("         "+cont.getClass());
                }
                if (current_child.getContextClass().equals(Python3Parser.TestContext.class)) {
                    Python3Parser.TestContext cont = (Python3Parser.TestContext) current_child.getContext();
                    System.out.println("         "+cont.getClass());
                }
                queue.add(current_child);
            }
        }
    }

    public static void main(String[] args){

        ControlFlowGraph cfg = new ControlFlowGraph();
        /*cfg.addSequenceNode("print ('hello world')");
        cfg.addSequenceNode("a = 5 +5");
        Node currentNode = cfg.getCurrentNode();
        Node bifurqued = cfg.addBifurcationOnNode(currentNode,"print wow");
        cfg.addSequenceNode("print jo");
        bifurqued = cfg.addBifurcationOnNode(bifurqued,"print child2");
        cfg.addSequenceNode("child of child2");
        cfg.printTree();*/



    }

}


