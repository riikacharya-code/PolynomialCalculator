import java.util.ArrayList;

/**
 * Class to represent a polynomial, e.g. 3.5x^4 + 3x^2 - 4.
 * 
 * Polynomials can be added, subtracted, multiplied, and divided.
 * 
 *
 * @author Riik Acharya
 * @version 1.0
 */
public class Poly implements Text
{

    /** 
     * ArrayList of Monomials stored
       */
    private ArrayList<Monomial> polyArray = new ArrayList<Monomial>();
    
    /**
     * Creates a new polynomial containing a single term with the coefficient
     * and exponent passed in as arguments. E.g. when called with coefficient
     * 3.5 and exponent 2, it should create a polynomial 3.5x^2.
     * 
     * @param coef the single term's coefficient.
     * @param exp the single term's exponent.
     * @return the polynomial created.
     */
    public Poly(double coef, int exp)
    {
        Monomial mon = new Monomial(coef, exp);
        polyArray.add(mon);
    }
    
    /**
     * Creates an empty polynomial containing a single term with the coefficient
     * and exponent passed in as arguments. E.g. when called with coefficient
     * 3.5 and exponent 2, it should create a polynomial 3.5x^2.
     * 
     * @return the polynomial created.
     */
    public Poly()
    {
    
    }
    
    /**
     * Adds the polynomial passed in as an argument, p, to the polynomial on which the 
     * method is called on (the "this" polynomial), and returns a new polynomial
     * with the result. I.e., it returns "this + p".
     * 
     * @param p the polynomial to add onto the polynomial on which the method is called on.
     * @return a polynomial representing the result of the addition.
     */
    public Poly add(Poly p)
    {
        boolean foundOne;
        
        Monomial m;
        
        ArrayList<Monomial> arr = new ArrayList<Monomial>();
        ArrayList<Monomial> inputArr = new ArrayList<Monomial>();
        
        for (int i = 0; i < this.getArray().size(); i++)
            arr.add(this.getArray().get(i));
        
        for (int i = 0; i < p.getArray().size(); i++)
            inputArr.add(p.getArray().get(i));
        
        for (int j = 0; j < inputArr.size(); j++)
        {
            foundOne = false;
            
            for (int i = 0; i < arr.size(); i++)
            {
                if (arr.get(i).getExp() == inputArr.get(j).getExp())
                {
                    m = new Monomial(arr.get(i).getCoef() + inputArr.get(j).getCoef(), 
                                        arr.get(i).getExp());
                    
                    arr.set(i, m);
                    
                    foundOne = true;
                }
            }
            
            if (foundOne == false)
                arr.add(inputArr.get(j));
                
        }
        
        Poly addedPoly = new Poly(0, 0);
        addedPoly.polyArray = arr;

        addedPoly.removeZeroCoef();
        
        addedPoly.sortPolys();
        
        return addedPoly;
    }
    
    /**
     * Subtracts the polynomial passed in as an argument, p, from the polynomial on which the 
     * method is called on (the "this" polynomial), and returns a new polynomial
     * with the result. I.e., it returns "this - p".
     * 
     * @param p the polynomial to be subtracted from the polynomial on which the method is called on.
     * @return a polynomial representing the result of the subtraction.
     */
    public Poly subtract(Poly p)
    {
        boolean foundOne;
        
        Monomial m;
        
        ArrayList<Monomial> arr = new ArrayList<Monomial>();
        ArrayList<Monomial> inputArr = new ArrayList<Monomial>();
        
        for (int i = 0; i < this.getArray().size(); i++)
            arr.add(this.getArray().get(i));
        
        for (int i = 0; i < p.getArray().size(); i++)
            inputArr.add(p.getArray().get(i));
        
        for (int j = 0; j < inputArr.size(); j++)
        {
            foundOne = false;
            
            for (int i = 0; i < arr.size(); i++)
            {
                if (arr.get(i).getExp() == inputArr.get(j).getExp())
                {
                    m = new Monomial(arr.get(i).getCoef() - inputArr.get(j).getCoef(), arr.get(i).getExp());
                                        
                    arr.set(i, m);
                    
                    foundOne = true;
                }
            }
            
            if (foundOne == false)
                arr.add(new Monomial (inputArr.get(j).getCoef() * -1, inputArr.get(j).getExp()));
        }
        
        Poly subtractedPoly = new Poly();
        subtractedPoly.polyArray = arr;
        
        subtractedPoly.removeZeroCoef();
        
        subtractedPoly.sortPolys();
            
        if (subtractedPoly.isZero())
            subtractedPoly = new Poly(0, 0);
            
        return subtractedPoly;
    }
 
    /**
     * Multiplies the polynomial passed in as an argument, p, by the polynomial on which the 
     * method is called on (the "this" polynomial), and returns a new polynomial
     * with the result. I.e., it returns "this * p".
     * 
     * @param p the polynomial to be multiplied by the polynomial on which the method is called on.
     * @return a polynomial representing the result of the multiplication.
     */
    public Poly multiply(Poly p)
    {
        ArrayList<Monomial> arr1 = new ArrayList<Monomial>();
        ArrayList<Monomial> arr2 = new ArrayList<Monomial>();
        
        Poly multipliedPoly = new Poly();
        
        // copy all monomials from this and p arrays to new arrays
        for (int i = 0; i < this.getArray().size(); i++)
            arr1.add(this.getArray().get(i));
        
        for (int i = 0; i < p.getArray().size(); i++)
            arr2.add(p.getArray().get(i));        
        
        ArrayList<Monomial> arr3 = new ArrayList<Monomial>();
    
        // loops through both arraylists, creates new monomial, and uses add method to account for if it has same exponent as anything else in the array
        for (int i = 0; i < arr1.size(); i++)
        {
            for (int j = 0; j < arr2.size(); j++)
            {
                
                Monomial m = new Monomial(getArray().get(i).getCoef() * p.getArray().get(j).getCoef(),
                                            getArray().get(i).getExp() + p.getArray().get(j).getExp());
                                            
                Poly newP = new Poly(m.getCoef(), m.getExp());
                
                multipliedPoly = multipliedPoly.add(newP);
                
            }
        }
        
        multipliedPoly.removeZeroCoef();
        
        multipliedPoly.sortPolys();
        
        return multipliedPoly;
    }

    /**
     * Divides the polynomial on which the method is called on (the "this" polynomial), by
     * the polynomial passed in as an argument, p, and returns a new polynomial
     * with the resulting quotient. I.e., it returns "this / p".
     * 
     * The division is performed according to the polynomial long division algorithm
     * ( https://en.wikipedia.org/wiki/Polynomial_long_division ).
     * 
     * If the division ends with a non-zero remainder, the polynomials are
     * indivisible so the method returns null. A division by zero also returns null.
     * 
     * @param p the polynomial to be multiplied by the polynomial on which the method is called on.
     * @return a polynomial representing the quotient of the division, or null if they're indivisible.
     */    
    public Poly divide(Poly p)
    {
        if (p.isZero())
        {
            System.out.println("Indivisible");
            return null;
        }
        
        Poly q = new Poly();
        Poly r = new Poly();
        Poly d = new Poly();
    
        for (int i = 0; i < this.getArray().size(); i++)
            r.polyArray.add(this.getArray().get(i));
        
        for (int i = 0; i < p.getArray().size(); i++)
            d.polyArray.add(p.getArray().get(i)); 
        
        Poly t;
        Monomial m;
        
        ArrayList<Monomial> arr = new ArrayList<Monomial>();
        ArrayList<Monomial> arrP = new ArrayList<Monomial>();
        
        for (int i = 0; i < r.getArray().size(); i++)
            arr.add(r.getArray().get(i));
        
        for (int i = 0; i < d.getArray().size(); i++)
            arrP.add(d.getArray().get(i));
        
        while (!(r.isZero()) && r.getArray().get(r.getArray().size() - 1).getExp() >= arrP.get(arrP.size() - 1).getExp())
        {
            
            m = new Monomial(r.getArray().get(r.getArray().size() - 1).getCoef() / arrP.get(arrP.size() - 1).getCoef(), 
                                r.getArray().get(r.getArray().size() - 1).getExp() - arrP.get(arrP.size() - 1).getExp());
            
            t = new Poly(m.getCoef(), m.getExp());
            q = q.add(t);
            r = r.subtract(t.multiply(d));
            
        }
        
        if (!(r.isZero()))
        {
            System.out.println("Indivisible");
            return null;
        }
        else
            return q;
    }
 
    /**
     * Compares the polynomial on which the method is called (the "this" polynomial), 
     * to the object passed in as argument, o. o is to be considered equal to the "this"
     * polynomial if they both represent equivalent polynomials.
     * 
     * E.g., "3.0x^4 + 0.0x^2 + 5.0" and "3.0x^4 + 5.0" should be considered equivalent.
     * "3.0x^4 + 5.0" and "3.0x^4 + 3.0" should not.
     * 
     * @param o the object to be compared against the polynomial the method is called on.
     * @return true if o is a polynomial equivalent to the one the method was called on,
     * and false otherwise.
     */
    public boolean equals(Object o)
    {
        Poly p;
        
        sortPolys();
        
        if(o instanceof Poly)
        {
            p = (Poly)o;
            
            p.sortPolys();
            
            ArrayList<Monomial> arr1 = new ArrayList<Monomial>();
            ArrayList<Monomial> arr2 = new ArrayList<Monomial>();
            
            removeZeroCoef();
            p.removeZeroCoef();
        
            for (int i = 0; i < this.getArray().size(); i++)
                arr1.add(this.getArray().get(i));
        
            for (int i = 0; i < p.getArray().size(); i++)
                arr2.add(p.getArray().get(i));
            
            if (arr1.size() != arr2.size())
            {
                return false;
            }
            
            for (int i = 0; i < arr1.size(); i++)
            { 
                if (arr1.get(i).getCoef() != 0 && arr2.get(i).getCoef() != 0)
                {
                    if (arr1.get(i).getCoef() != arr2.get(i).getCoef() || arr1.get(i).getExp() != arr2.get(i).getExp())
                    {
                        return false;
                    }
                }
            }

            return true;
        }
        
        return false;
    }

    /**
     * Returns a textual representation of the polynomial the method is called on in decreasing-exponent order.
     * The textual representation is a sum of monomials, with each monomial 
     * being defined by a double coefficient, the letters "x^", and an integer exponent.
     * Exceptions to this rule: coefficients of 1.0 are omitted, as should "^1",
     * and "x^0".
     * 
     * 
     * Rules for separating terms, applicable to all terms other that the largest exponent one:
     *   - Terms with positive coefficients should be preceeded by " + ".
     *   - Terms with negative coefficients should be preceeded by " - ".
     * 
     * Rules for the highest exponent term (i.e., the first):
     *   - If the coefficient is negative it should be preceeded by "-". E.g. "-3.0x^5".
     *   - If the coefficient is positive it should not preceeded by anything. E.g. "3.0x^5".
     * 
     * The zero/empty polynomial is represented as "0.0".
     *
     * @return a textual representation of the polynomial the method was called on.
     */
    public String toString()
    {
        String polyString = "";
        
        if (this.isZero())
            return "0.0";
        
        sortPolys();
        
        // special case if poly contains only 1 element
        if (this.polyArray.size() == 1) 
        {
                if (this.polyArray.get(0).getExp() == 0 && this.polyArray.get(0).getCoef() != 0)
                {
                    polyString += this.polyArray.get(0).getCoef();
                }
                else if (this.polyArray.get(0).getCoef() == 1 || this.polyArray.get(0).getCoef() == -1)
                {
                    if (this.polyArray.get(0).getCoef() == -1)
                    {
                        polyString += addIfCoefIsMinusOneOrOne(0, "", "-");
                    }
                    else
                    {
                        polyString += addIfCoefIsMinusOneOrOne(0, "", "");
                    }
                }
                else if (this.polyArray.get(0).getExp() == 1)
                {
                    polyString += this.polyArray.get(0).getCoef() + "x";
                }
                else if(this.polyArray.get(0).getCoef() == 0)
                {
                    polyString += "";
                }
                else
                {
                    polyString += this.polyArray.get(0).getCoef() + "x^" + this.polyArray.get(0).getExp();
                }
        }
        
        else
        {
            // loops from last to first since sorting was from least exponent to greatest
            
            for (int i = this.polyArray.size() - 1; i >= 0; i--)
            {
                // if we're currently on the first term
                
                if (i == this.polyArray.size() - 1)
                {
                    if (this.polyArray.get(i).getExp() == 0 && this.polyArray.get(i).getCoef() != 0)
                    {
                        polyString += this.polyArray.get(i).getCoef();
                    }
                    else if (this.polyArray.get(i).getCoef() == 1 || this.polyArray.get(i).getCoef() == -1)
                    {
                        if (this.polyArray.get(i).getCoef() == -1)
                        {
                            polyString += addIfCoefIsMinusOneOrOne(i, " ", "-");
                        }
                        else
                        {
                            polyString += addIfCoefIsMinusOneOrOne(i, " ", "");
                        }
                    }
                    else if (this.polyArray.get(i).getExp() == 1)
                    {
                        polyString += this.polyArray.get(i).getCoef() + "x ";
                    }
                    else if(this.polyArray.get(i).getCoef() == 0)
                    {
                        polyString += "";
                    }
                    else
                    {
                        polyString += this.polyArray.get(i).getCoef() + "x^" + this.polyArray.get(i).getExp() + " ";
                    }
                }
            
                // if we're on the last term
                else if (i == 0)
                {
                    if (this.polyArray.get(i).getExp() == 0 && this.polyArray.get(i).getCoef() != 0)
                    {
                        polyString += Math.abs(this.polyArray.get(i).getCoef());
                    }
                    else if (this.polyArray.get(i).getCoef() == 1)
                    {
                        polyString += addIfCoefIsMinusOneOrOne(i, "", "");
                    }
                    else if (this.polyArray.get(i).getExp() == 1)
                    {
                        polyString += Math.abs(this.polyArray.get(i).getCoef()) + "x";
                    }
                    else if(this.polyArray.get(i).getCoef() == 0)
                    {
                        polyString += "";
                    }
                    else
                    {
                        polyString += Math.abs(this.polyArray.get(i).getCoef()) + "x^" + this.polyArray.get(i).getExp();
                    }
                }
                
                // if we're in the middle
                else
                {
                    if (this.polyArray.get(i).getExp() == 0 && this.polyArray.get(i).getCoef() != 0)
                    {
                        polyString += Math.abs(this.polyArray.get(i).getCoef());
                    }
                    else if (this.polyArray.get(i).getCoef() == 1)
                    {
                        polyString += addIfCoefIsMinusOneOrOne(i, " ", "");
                    }
                    else if (this.polyArray.get(i).getExp() == 1)
                    {
                        polyString += Math.abs(this.polyArray.get(i).getCoef()) + "x ";
                    }
                    else if(this.polyArray.get(i).getCoef() == 0)
                    {
                        polyString += "";
                    }
                    else
                    {
                        polyString += Math.abs(this.polyArray.get(i).getCoef()) + "x^" + this.polyArray.get(i).getExp() + " ";
                    }
                }

                // determines which sign goes where based on the coeficient of the next exponent
                if (i > 0 && this.polyArray.get(i - 1).getCoef() != 0)
                {
                    if (this.polyArray.get(i - 1).getCoef() >= 0)
                        polyString += "+ ";
                    else 
                        polyString += "- ";
                }
            }
        }

        return polyString;
    }
    
    
    /**
     * Returns an object reference to the array of monomials
     * 
     * @return object reference to the array of monomials
       */
    public ArrayList<Monomial> getArray()
    {
        return polyArray;
    }
    
    /**
     * sorts array of monomials from least to greates exponent
       */
    public void sortPolys()
    {
        ArrayList<Monomial> arr = polyArray;
    
        for (int i = 1; i < polyArray.size(); i++)
        {
            Monomial elem = arr.get(i);
            int j = i - 1;
            
            for (; j >= 0 && polyArray.get(j).getExp() > elem.getExp(); j--)
            {
                polyArray.set(j + 1, arr.get(j));
            }
            
            polyArray.set(j + 1, elem);
        }
    }
    
    /**
     * Returns true if polynomial is equal to 0, false otherwise
     * 
     * @returns boolean telling whether polynomial is equal to 0
       */
    public boolean isZero()
    {
        if (this.polyArray.size() == 0)
            return true;
        
        for (int i = 0; i < this.polyArray.size(); i++)
            if (this.polyArray.get(i).getCoef() != 0)
                return false;
                
        return true;
    }
    
    /**
     * removes all monomials from polyArray that have zero as their coefficient
       */
    private void removeZeroCoef()
    {
        for (int i = 0; i < polyArray.size(); i++)
        {
            if (polyArray.get(i).getCoef() == 0)            
            {
                polyArray.remove(i);
                i--;
            }
        }
    }
    
    
    /**
     * If a monomial coefficient is -1 or 1, this method is invoked to determine how the string is printed in each special circumstance.
     * 
     * @param index of the array, and String containing a possible space after the monomial is printed
     * @return String of printed monomial
       */
    private String addIfCoefIsMinusOneOrOne(int index, String possibleSpace, String possibleMinus)
    {
        if (this.polyArray.get(index).getExp() == 1)
            return possibleMinus + "x" + possibleSpace;
        else
            return possibleMinus + "x^" + this.polyArray.get(index).getExp() + possibleSpace;
    }
}
