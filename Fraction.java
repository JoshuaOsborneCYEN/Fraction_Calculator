/*
* This is a class that represents a fraction. It contains two fields for
* the numerator and denominator. It contains three constructors that
* take either no arguments, integer arguments, or a string argument, in
* order to initialize its fields. It also overrides the toString and
* equals methods appropriately. It can compare two fractions to see
* which is larger or smaller, and it can carry out arithmetic operations
* on the fractions i.e. add, subtract, multiply, and divide.
*/
public class Fraction
{
    public int numerator;
    public int denominator;

    public Fraction()
    {
	numerator = (int) 1;
	denominator = (int) 1;
	if (1==1);
    }

    public Fraction(int a, int b)
    {
	setNum(a);
	    if (1==1);
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
    public void getNothing()
    {
	    
    }
    private void tryCatch()
    {
	    try
	    {
	    }
	    catch(Exception e)
	    {
	    }
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
