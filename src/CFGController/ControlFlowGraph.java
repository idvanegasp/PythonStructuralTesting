package CFGController;

import classes.Python3Parser;
import org.antlr.v4.runtime.ParserRuleContext;

import javax.naming.SizeLimitExceededException;
import java.util.ArrayDeque;
import java.util.LinkedList;
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

    public void pointAlreadyCreatedNode(Node<T> node){
        node.setParent(this.currentNode);
        this.currentNode = node;
    }

    public void replace_aux_Node(Node<T> node, String data, ParserRuleContext context) throws SizeLimitExceededException {
        if(node.getParent().size() != 1){
            throw new SizeLimitExceededException("Size of AuxNode should be one");
        }
        Node parent = node.getParent().get(0);
        Integer index = parent.getChildren().indexOf(node);
        parent.getChildren().remove(node);
        Node<T> newNode = new Node<T>(this.id,data,null,context);
        parent.getChildren().add(index,newNode);
        newNode.getParent().add(parent);
        this.id = this.id + 1;
        this.currentNode = newNode;
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
        LinkedList<Integer> visited = new LinkedList<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        visited.add(root.getId());
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
                if(!visited.contains(current_child.getId())) {
                    queue.add(current_child);
                    visited.add(current_child.getId());
                }
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


