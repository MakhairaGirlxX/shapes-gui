package GUIpack;

import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.*;

public class GUIApp {

	public static void main(String[] args) {		
		JFrame myFrame = new AppFrame("first frame");		
	}
}

class AppFrame extends JFrame
{
	public AppFrame(String title)
	{
		super(title);		
		this.setLayout(new GridLayout(1, 1));
		TextInfo info = new TextInfo();
		this.add(new mainPnl(info));
		this.setSize(600, 600);
		this.setLocation(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

class mainPnl extends JPanel
{
	JPanel mainPnl;
	
	JButton circle;
	JButton rectangle;
	JButton triangle;
	JButton undo;
	
	JTextField shapeSize;
	JTextField shapeColorR;
	JTextField shapeColorG;
	JTextField shapeColorB;
	JTextField shapeLocation;
	
	JLabel size;
	JLabel colorRLabel;
	JLabel colorGLabel;
	JLabel colorBLabel;
	JLabel location;
	
	Color color;
	
	int sizeInput = 1;
	int colorRInput;
	int colorGInput;
	int colorBInput;
	
	boolean circleAction = false;
	boolean rectAction = false;
	boolean triAction = false;
	
	java.util.List<Shape> shapes;
	TextInfo info;
	
    mainPnl(TextInfo info)
	{
		super();
		this.info = info;
		this.setBorder(BorderFactory.createTitledBorder("main panel"));
		mainPnl = new JPanel();
		
		this.setLayout(new BorderLayout());	
		this.add(mainPnl, "Center");
		
		shapes = new ArrayList<Shape>();
		
		organizeVisualComponents();
		organizeListeners();
	}
    
    private void organizeVisualComponents()
    {
    	GridLayout layout = new GridLayout(2, 5);
    	layout.setHgap(20);
    	
    	JPanel pnlContainer = new JPanel();
    	shapeSize = new JTextField();
    	shapeColorR = new JTextField();
    	shapeColorG = new JTextField();
    	shapeColorB = new JTextField();
    	shapeLocation = new JTextField();
    	
    	size = new JLabel("Change Size:");
    	colorRLabel = new JLabel("R Value:");
    	colorGLabel = new JLabel("G Value:");
    	colorBLabel = new JLabel("B Value:");
    	location = new JLabel("Location:");
		
		circle = new JButton("Circle");
		rectangle = new JButton("Rectangle");
		triangle = new JButton("Triangle");		
		undo = new JButton("Undo");
		
		circle.setBounds(150, 200, 100, 40);
		rectangle.setBounds(100, 200, 100, 40);
		triangle.setBounds(50, 200, 100, 40);
		
    	mainPnl.add(circle);
    	mainPnl.add(rectangle);
    	mainPnl.add(triangle);
    	
    	pnlContainer.setLayout(layout);

    	pnlContainer.add(size);   
    	pnlContainer.add(colorRLabel);
    	pnlContainer.add(colorGLabel);
    	pnlContainer.add(colorBLabel); 
    	pnlContainer.add(location);
    	pnlContainer.add(undo);
    	
    	pnlContainer.add(shapeSize);
    	pnlContainer.add(shapeColorR);
    	pnlContainer.add(shapeColorG);
    	pnlContainer.add(shapeColorB);
    	pnlContainer.add(shapeLocation);    	
  
    	this.add(pnlContainer, "South");

    }
    
	private void organizeListeners()
	{
		
	 	circle.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				changeShapeCircle();								
			}
			
		});	    
	 	
	 	rectangle.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				changeShapeRectangle();
								
			}			
		});	    	
    	
    	triangle.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Triangle");
				changeShapeTriangle();
				
			}			
		});
    	
    	shapeSize.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				changeShapeSize();				
			}			
		}); 
    	
    	undo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(final ActionEvent e) {
				if(!shapes.isEmpty())
				{
					shapes.remove(shapes.size() - 1);
					mainPnl.repaint();
			
				}
			}			
		});     	
	}
	
	public void changeShapeCircle()
	{ 
		circleAction = true;
		rectAction = false;
		triAction = false;
		
		this.addMouseListener(new MsListenerCircle());	
	}
	
	public void changeShapeRectangle()
	{
		rectAction = true;
		circleAction = false;
		triAction = false;
		
		this.addMouseListener(new MsListenerRectangle());	
	}
	
	public void changeShapeTriangle()
	{
		rectAction = false;
		circleAction = false;
		triAction = true;
		
		this.addMouseListener(new MsListenerTriangle());
	}
	
	public void setColor()
	{
		try
		{
			String r1 = shapeColorR.getText();
			colorRInput = Integer.parseInt(r1);
			
			String g1 = shapeColorG.getText();
			colorGInput = Integer.parseInt(g1);
			
			String b1 = shapeColorB.getText();
			colorBInput = Integer.parseInt(b1);
			
			
			int r = colorRInput;
			int g = colorGInput;
			int b = colorBInput;
			
			if(r > 255 || r < 0 || g > 255 || g < 0 || b > 255 || b < 0)
			{
				JOptionPane.showMessageDialog(null, "Please enter a number between 0 and 255");
				shapeColorR.setText("0");
				shapeColorG.setText("0");
				shapeColorB.setText("0");
			}
			
			else
			{
				color = new Color(r, g, b);
			}
			
		}
		
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "Please enter color value numbers");
			shapeColorR.setText("0");
			shapeColorG.setText("0");
			shapeColorB.setText("0");
		}
	}
	
	public void changeShapeSize()
	{
		String s1 = shapeSize.getText();
		sizeInput = Integer.parseInt(s1);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		
		for(Shape s : shapes)
		{
			g2.draw(s);
			System.out.println("Redrawn");
		}
	}
	
	class MsListenerCircle extends MouseAdapter
	{

		public void mouseClicked(MouseEvent e)
		{
			if(circleAction)
			{
				Graphics2D g2 = (Graphics2D) getGraphics().create();
				setColor();
				g2.setColor(color);
				Shape oval = new Ellipse2D.Double(e.getX(), e.getY(), 50 * sizeInput, 50 * sizeInput);	
				shapes.add(oval);
				g2.draw(oval);
				shapeLocation.setText("x: " + e.getX() + ", y: " + e.getY());
				rectAction = false;
			}
			else
			{
				return;
			}			
		}
		
	}
	
	class MsListenerRectangle extends MouseAdapter
	{

		public void mouseClicked(MouseEvent e)
		{
			if(rectAction)
			{
				Graphics2D g2 = (Graphics2D) getGraphics().create();
				setColor();
				g2.setColor(color);
				Shape rectangle = new Rectangle2D.Double(e.getX(), e.getY(), 100 * sizeInput, 50 * sizeInput);	
				shapes.add(rectangle);
				g2.draw(rectangle);
				shapeLocation.setText("x: " + e.getX() + ", y: " + e.getY());
				circleAction = false;
			}
			else
			{
				return;
			}					
		}
		
	}
	
	class MsListenerTriangle extends MouseAdapter
	{

		public void mouseClicked(MouseEvent e)
		{
			if(triAction)
			{
				Graphics2D g2 = (Graphics2D) getGraphics().create();				
				
				setColor();
				g2.setColor(color);
				
				int x[] = {20 * sizeInput, 50 * sizeInput, 80 * sizeInput};
				int y[] = {80 * sizeInput, 30 * sizeInput, 80 * sizeInput};
				
				int[] drawX = {x[0] + e.getX(), x[1] + e.getX(), x[2] + e.getX()};
				int[] drawY = {y[0] + e.getY(), y[1] + e.getY(), y[2] + e.getY()};
				
				Shape tri = new Polygon(drawX, drawY, 3);	
				shapes.add(tri);
				shapeLocation.setText("x: " + e.getX() + ", y: " + e.getY());
				g2.drawPolygon((Polygon) tri);
			}
			else
			{
				return;
			}
		
		}
		
	}    

}

class TextInfo implements InfoStore<String>
{

	String info;
	
	public TextInfo()
	{
		info = "";
	}
	@Override
	public void setInfo(String info) 
	{		
		this.info = info;
	}

	@Override
	public String getInfo() 
	{		
		return info;
	}
	
}

interface InfoStore<T>
{
	void setInfo(String info);
	T getInfo();
}

