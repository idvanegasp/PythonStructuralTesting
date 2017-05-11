package GUI;

import CFGController.ControlFlowGraph;
import CFGController.Node;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.util.*;

public class PythonTesterGUI extends JFrame
{

	/**
	 * 
	 */

	public mxCell prev;
	public mxCell end;
	public mxCell cur;
	public HashMap<Integer, mxCell> dict = new HashMap<>();
	public HashMap<Integer, Integer> X = new HashMap<>();
	public HashMap<Integer, Integer> Y = new HashMap<>();

	private static final long serialVersionUID = -2707712944901661771L;

	public PythonTesterGUI(ControlFlowGraph cfg, int height, int width)
	{
		super("Control Flow Graph");

		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);

		LinkedList<Integer> visited = new LinkedList<>();

		graph.getModel().beginUpdate();
		Stack<Node> stack = new Stack<>();
		stack.add(cfg.getRoot());
		visited.add(cfg.getRoot().getId());
		int x = 20;
		int y = 20;
		HashMap<Integer,HashMap<Integer,Integer>> points = new HashMap<>();
		try
		{
			Node current = stack.peek();
			prev = (mxCell) graph.insertVertex(parent, null, current.getData(), x, y, 80,
					30);
			end = (mxCell) graph.insertVertex(parent,null, "End",20 ,height - 200, 80, 30);
			dict.put(current.getId(),prev);
			X.put(current.getId(),x);
			Y.put(current.getId(),y);
			while(!stack.isEmpty()){

				current = stack.pop();
				prev = dict.get(current.getId());
				y = Y.get(current.getId()) + 50;
				x = X.get(current.getId());
				if(current.getChildren().isEmpty()){
					graph.insertEdge(parent, null, "", prev, end);
				}
				for(Object da_node: current.getChildren()){
					Node node = (Node) da_node;
					if(points.containsKey(x) && points.get(x).containsKey(y)){

						int max_value = 0;
						for(Integer inte: points.keySet()){
							if(inte > max_value){
								max_value = inte;
							}
						};
						x = max_value + 120;
					}
					if(dict.containsKey(node.getId())){

						cur = dict.get(node.getId());

					}else {

						cur = (mxCell) graph.insertVertex(parent, null, node.getData(), x, y, 80,
								30);
					}
					graph.insertEdge(parent, null, "", prev, cur);
					if(points.containsKey(x)){
						if(!points.containsKey(y)){
							points.get(x).put(y,1);
						}
					}else{
						points.put(x,new HashMap<>());
						points.get(x).put(y,1);
					}
					if(!visited.contains(node.getId())){
						stack.add(node);
						visited.add(node.getId());
					}
					dict.put(node.getId(),cur);
					X.put(node.getId(),x);
					Y.put(node.getId(),y);
					x = x + 120;
				}
			}


		}
		finally
		{
			graph.getModel().endUpdate();
		}

		//graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "green", new Object[]{prev}); //changes the color to red
		//graphComponent.refresh();


	}

	public static void main(String[] args)
	{
		double height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		double width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		PythonTesterGUI frame = new PythonTesterGUI(null,(int)height,(int)width);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize((int)width, (int)height);
		frame.setVisible(true);
	}

}
