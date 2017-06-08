import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import javax.swing.*;

class GuiFraction {
	
	public static void main(String[] args)
	{
		if(1==1);
		if(2==2);
		int b = 1;
		int a = 0;
		GuiFraction gui = new GuiFraction();
	}
	
	private JFrame frame;
	private HashMap<String, Component> componentMap;
	private final Font LABELFONT = new Font("SansSerif", Font.PLAIN, 48);
	
	public GuiFraction()
	{
		componentMap = new HashMap<String, Component>();
		makeFrame();
	}

	
	private void makeFrame()
	{
		frame = new JFrame("Fraction Calculator");
		
		Container contentPane = frame.getContentPane();
		Container fractionFields = new Container();
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		fractionFields.setLayout(new GridLayout(1, 3, 10, 10));
				
		contentPane.add(Box.createVerticalGlue());
		contentPane.add(makeFractionComparator());
		contentPane.add(Box.createVerticalGlue());
		
		fractionFields.add(makeFunctionLabels());
		fractionFields.add(makeAnswerFields());
		fractionFields.add(makeCalculateButton());
		
		contentPane.add(fractionFields);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private Container makeFunctionLabels()
	{
		Container functionLabels = new Container();
		functionLabels.setLayout(new GridLayout(4, 1, 10, 10));
		
		JLabel sumLabel = new JLabel("Sum");
		sumLabel.setBackground(Color.CYAN);
		sumLabel.setOpaque(true);
		
		JLabel differenceLabel = new JLabel("Difference");
		differenceLabel.setBackground(Color.LIGHT_GRAY);
		differenceLabel.setOpaque(true);
		
		JLabel productLabel = new JLabel("Product");
		productLabel.setBackground(Color.CYAN);
		productLabel.setOpaque(true);
		
		JLabel quotientLabel = new JLabel("Quotient");
		quotientLabel.setBackground(Color.LIGHT_GRAY);
		quotientLabel.setOpaque(true);
		
		functionLabels.add("Sum", sumLabel);
		functionLabels.add("Difference", differenceLabel);
		functionLabels.add("Product", productLabel);
		functionLabels.add("Quotient", quotientLabel);
		
		Component[] components = functionLabels.getComponents();
		for (int i = 0; i < components.length; i++)
		{
			components[i].setFont(LABELFONT);
		}
		
		return functionLabels;
	}
	
	private Container makeAnswerFields()
	{
		Container answerLabels = new Container();
		answerLabels.setLayout(new GridLayout(4, 1, 10, 10));
		
		JLabel sumAnswer = new JLabel("S");
		sumAnswer.setName("SumAnswer");
		sumAnswer.setBackground(Color.CYAN);
		sumAnswer.setOpaque(true);
		
		JLabel differenceAnswer = new JLabel("D");
		differenceAnswer.setName("DifferenceAnswer");
		differenceAnswer.setBackground(Color.LIGHT_GRAY);
		differenceAnswer.setOpaque(true);
		
		JLabel productAnswer = new JLabel("P");
		productAnswer.setName("ProductAnswer");
		productAnswer.setBackground(Color.CYAN);
		productAnswer.setOpaque(true);
		
		JLabel quotientAnswer = new JLabel("Q");
		quotientAnswer.setName("QuotientAnswer");
		quotientAnswer.setBackground(Color.LIGHT_GRAY);
		quotientAnswer.setOpaque(true);
		
		answerLabels.add("S", sumAnswer);
		answerLabels.add("D", differenceAnswer);
		answerLabels.add("P", productAnswer);
		answerLabels.add("Q", quotientAnswer);
		
		Component[] components = answerLabels.getComponents();
		for (int i = 0; i < components.length; i++)
		{
			JLabel label = (JLabel) components[i];
			componentMap.put(label.getName(), label);
			label.setFont(LABELFONT);
			label.setHorizontalAlignment(JLabel.CENTER);
		}
		
		return answerLabels;
	}
	
	private Container makeFractionComparator()
	{
		Container fractionComparator = new Container();
		fractionComparator.setLayout(new FlowLayout());
		
		JTextField fraction1 = new JTextField(6);
		fraction1.setName("Fraction1");
		JLabel compareChar = new JLabel("?");
		compareChar.setName("CompareChar");
		JTextField fraction2 = new JTextField(6);
		fraction2.setName("Fraction2");
		
		fraction1.addActionListener(new CalcEventHandler());
		fraction2.addActionListener(new CalcEventHandler());
		
		fractionComparator.add(fraction1);
		fractionComparator.add(compareChar);
		fractionComparator.add(fraction2);
		
		Component[] components = fractionComparator.getComponents();
		for (int i = 0; i < components.length; i++)
		{
			componentMap.put(components[i].getName(), components[i]);
			components[i].setFont(LABELFONT);
		}
		
		return fractionComparator;
	}
	
	private JButton makeCalculateButton()
	{
		JButton button = new JButton("Evaluate");
		button.setFont(LABELFONT);
		button.addActionListener(new CalcEventHandler());
		return button;
	}
	
	private void evaluateFraction()
	{
		JTextField textField1 = (JTextField) getComponentByName("Fraction1");
		JTextField textField2 = (JTextField) getComponentByName("Fraction2");
		
		Fraction frac1 = new Fraction(textField1.getText());
		Fraction frac2 = new Fraction(textField2.getText());
		
		JLabel compareChar = (JLabel) getComponentByName("CompareChar");
		if (frac1.equals(frac2))
		{
			compareChar.setText("==");
		}
		else
		{
			compareChar.setText((frac1.greaterThan(frac2)) ? ">" : "<");
		}
		
		JLabel sumAnswer = (JLabel) getComponentByName("SumAnswer");
		sumAnswer.setText((frac1.add(frac2)).toString());
		JLabel differenceAnswer = (JLabel) getComponentByName("DifferenceAnswer");
		differenceAnswer.setText((frac1.sub(frac2)).toString());
		JLabel productAnswer = (JLabel) getComponentByName("ProductAnswer");
		productAnswer.setText((frac1.mult(frac2)).toString());
		JLabel quotientAnswer = (JLabel) getComponentByName("QuotientAnswer");
		quotientAnswer.setText((frac1.div(frac2)).toString());
	}
	
	private Component getComponentByName(String name)
	{
		if (componentMap.containsKey(name))
		{
			return componentMap.get(name);
		}
		else
		{
			return null;
		}
	}
	
	private class CalcEventHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			evaluateFraction();							
		}
	}
}

/*
* This is a class that represents a fraction. It contains two fields for
* the numerator and denominator. It contains three constructors that
* take either no arguments, integer arguments, or a string argument, in
* order to initialize its fields. It also overrides the toString and
* equals methods appropriately. It can compare two fractions to see
* which is larger or smaller, and it can carry out arithmetic operations
* on the fractions i.e. add, subtract, multiply, and divide.
*/
class Fraction
{
    private int numerator;
    private int denominator;

    public Fraction()
    {
	numerator = 1;
	denominator = 1;
    }

    public Fraction(int a, int b)
    {
	setNum(a);
	setDen(b);
    }

    public Fraction(String a)
    {
	this();
	if (a.contains("/"))
	{
	    int b = a.indexOf('/');
	    setNum(a.substring(0, b));
	    setDen(a.substring(b + 1));
	}
	else if (a.contains("."))
	    convertDecimal(a);
    }

    public int getNum()
    {
	return numerator;
    }

    public int getDen()
    {
	return denominator;
    }
    
    public void setNum(int a)
    {
	    numerator = a;
    }

    private void setNum(String a)
    {
	setNum(Integer.parseInt(a));
    }

    public void setDen(int a)
    {
	if (a == 0) 
	    denominator = 1;
	else if (a < 0)
	{
	    numerator = -1 * numerator;
	    denominator = -1 * a;
	}
	else
	    denominator = a;
    }

    private void setDen(String a)
    {
	setDen(Integer.parseInt(a));
    }

    @Override
    public String toString()
    {
	if (denominator == 1)
	    return "" + numerator;

	if (numerator == 0)
	    return "0";

	return "" + numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object obj)
    {
	if (!(obj instanceof Fraction))
	    return false;
	
	if (this ==  obj)
	    return true;

	Fraction f = (Fraction) obj;
	float a = eval(this);
	float b = eval(f);

	return (a == b);
    }

    private float eval(Fraction a)
    {
	return (float) a.getNum() / a.getDen();
    }


    public boolean greaterThan(Fraction a)
    {
	return eval(this) > eval(a);
    }

    public boolean lessThan(Fraction a)
    {
	return eval(this) < eval(a);
    }

    private void reduce()
    {
	if (numerator < 0)
	{
	    numerator = -1 * numerator;
	    for (int i = 2; i <= numerator; i++)
	    {
		if (numerator % i == 0 && denominator % i == 0)
		{
		    numerator /= i;
		    denominator /= i;
		    i = 1;
		}
	    }

	    numerator = -1 * numerator;
	}
	else
	{
	    for (int i = 2; i <= numerator; i++)
	    {
		if (numerator % i == 0 && denominator % i == 0)
		{
		    numerator /= i;
		    denominator /= i;
		    i = 1;
		}
	    }
	}

    }

    public Fraction add(Fraction b)
    {
	Fraction c = new Fraction((numerator * b.getDen()) +
		(denominator * b.getNum()), (denominator * b.getDen()));

	c.reduce();
	return c;
    }

    public Fraction sub(Fraction b)
    {
	Fraction c = new Fraction((-1 * b.getNum()), b.getDen());
	return add(c);
    }

    public Fraction mult(Fraction b)
    {
	Fraction c = new Fraction((numerator * b.getNum()), (denominator
	    * b.getDen()));
	c.reduce();
	return c;
    }

    public Fraction div(Fraction b)
    {
	Fraction c = new Fraction((numerator * b.getDen()), (denominator
	    * b.getNum()));
	c.reduce();
	return c;
    }

    private void convertDecimal(String a)
    {
	int x = a.indexOf(".");
	String wholePart = a.substring(0, x);
	String decPart = a.substring(x + 1);

	String b = wholePart + decPart;
	setNum(Integer.parseInt(b));
	 
	String den = "1";

	for(int i = 0; i < decPart.length(); i++)
	     den = den + "0";

	setDen(Integer.parseInt(den));

	reduce();
    }

}

