package CFGController;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by neillgiraldo on 5/5/17.
 */
public class Node<T>{

    private String data;
    private Node<T> parent;
    private List<Node<T>> children;
    private ParserRuleContext context;
    private Class contextClass;

    public Node(String data, Node<T> parent,ParserRuleContext context){
        this.data = data.replace("\n","");
        this.children = new LinkedList<>();
        this.context = context;
        if(this.context != null){
            this.contextClass = context.getClass();
        }
        if(parent != null) {
            this.setParent(parent);
        }
    }

    public void setParent(Node<T> parent){
        parent.addChild(this);
        this.parent = parent;
    }

    public void addChild(Node<T> child){
        this.children.add(child);
    }

    public String getData(){
        return this.data;
    }

    public Node<T> getParent(){
        return this.parent;
    }

    public List<Node<T>> getChildren(){
        return this.children;
    }

    public Class getContextClass(){
        return contextClass;
    }

    public ParserRuleContext getContext(){
        return context;
    }
}