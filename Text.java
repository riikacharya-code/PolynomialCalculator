
/**
 * 
 * @author Riik Acharya
 * @version 1.0
 */
public interface Text
{
    /**
     * Adds the polynomial passed in as an argument, p, to the polynomial on which the 
     * method is called on (the "this" polynomial), and returns a new polynomial
     * with the result. I.e., it returns "this + p".
     * 
     * @param p the polynomial to add onto the polynomial on which the method is called on.
     * @return a polyn;omial representing the result of the addition.
     */
    public Poly add(Poly p);
    
    /**
     * Subtracts the polynomial passed in as an argument, p, from the polynomial on which the 
     * method is called on (the "this" polynomial), and returns a new polynomial
     * with the result. I.e., it returns "this - p".
     * 
     * @param p the polynomial to be subtracted from the polynomial on which the method is called on.
     * @return a polynomial representing the result of the subtraction.
     */
    public Poly subtract(Poly p);

    /**
     * Multiplies the polynomial passed in as an argument, p, by the polynomial on which the 
     * method is called on (the "this" polynomial), and returns a new polynomial
     * with the result. I.e., it returns "this * p".
     * 
     * @param p the polynomial to be multiplied by the polynomial on which the method is called on.
     * @return a polynomial representing the result of the multiplication.
     */
    public Poly multiply(Poly p);
    
    /**
     * Divides the polynomial on which the method is called on (the "this" polynomial), by
     * the polynomial passed in as an argument, p, and returns a new polynomial
     * with the resulting quotient. I.e., it returns "this / p".
     * 
     * The division is performed according to the polynomial long division algorithm
     * ( https://en.wikipedia.org/wiki/Polynomial_long_division ).
     * 
     * If polynomial division ends with a non-zero remainder, the polynomials are
     * indivisible so the method returns null. A division by zero also returns null.
     * 
     * @param p the polynomial to be multiplied by the polynomial on which the method is called on.
     * @return a polynomial representing the quotient of the division, or null if they're indivisible.
     */    
    public Poly divide(Poly p);
    
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
    public boolean equals(Object o);
    
    /**
     * Returns a textual representation of the polynomial the method is called on.
     * The textual representation is a sum of monomials, with each monomial 
     * being defined by a double coefficient, the letters "x^", and an integer exponent.
     * Exceptions to this rule: coefficients of 1.0 are omitted, as is "^1",
     * and "x^0".
     * 
     * @return a textual representation of the polynomial the method was called on.
     */
    public String toString();
}
