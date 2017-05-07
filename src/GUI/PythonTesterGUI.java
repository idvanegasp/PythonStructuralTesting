package GUI;

import CFGController.ControlFlowGraph;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;

public class PythonTesterGUI extends JFrame
{

	/**
	 * 
	 */

	public mxCell prev;
	public mxCell end;

	private static final long serialVersionUID = -2707712944901661771L;

	public PythonTesterGUI(ControlFlowGraph cfg, int height, int width)
	{
		super("Control Flow Graph");

		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);

		graph.getModel().beginUpdate();

		try
		{
			prev = (mxCell) graph.insertVertex(parent, null, "Begin", width >> 1, 20, 80,
					30);
			end = (mxCell) graph.insertVertex(parent,null, "End", width >> 1 ,height - 200, 80, 30);
			//graph.insertEdge(parent, null, "", prev, end);


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
