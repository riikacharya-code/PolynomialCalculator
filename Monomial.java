/**
 * This represents an instance of a monomial, which contains a coefficient and an exponent.
 * 
 *@author: Riik Acharya
 *@version: 1.0
   */
public class Monomial
{
    /** Double representing coefficient of monomial*/
    private double coef;
    /** Double representing exponent of monomial*/
    private int exp;

    /**
     * Takes in a coefficient and an exponent and makes a monomial
     * @param double coef, int exp
     * @return Monomial
     */
    public Monomial(double coef, int exp)
    {
        this.coef = coef;
        this.exp = exp;
    }

    /**
     * Returns coefficient
     */
    public double getCoef()
    {
        return coef;
    }
    
    /**
     * Returns exponent
     */
    public int getExp()
    {
        return exp;
    }
}