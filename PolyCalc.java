import java.util.*;

/**
 * This is the PolyCalc class which runs a polynomial calculator.
 * The user enters two Polynomials and an operator and a result is displayed
 *
 * @author Riik Acharya
 * @version 1.0
 */
public class PolyCalc
{
    /** Main method of PolyCalc class */
    public static void main(String[] args)
    {
        Scanner keybScanner = new Scanner(System.in); // scanner for System.in input
        
        System.out.println("Welcome. This is Riik Acharya's Polynomial Calculator.");
        
        // loop if user wants to keep using program
        while (true)
        {
            // varible collection
            
            String line;

            double firstCoef;
            Integer firstExp;
            
            double secondCoef;
            Integer secondExp;
            
            String operator;
                
            Poly p1 = new Poly();  
            Poly p2 = new Poly();
            
            Poly p3;
            
            // loop  to read first exponent until it's valid or until user enters quit
            while (true)
            {
                System.out.println("Enter a new Polynomial as a separated pair of coefficients and exponents. " +
                                "Ex: '2x^5 - 3' should be entered as '2.0 5 -3 0': ");

                // scan's user's input
                line = keybScanner.nextLine();
                    
                Scanner lineScanner = new Scanner(line);
                
                // accounts for the fact if operators are used in input
                while (true)
                {
                    // if line contains operators, asks for user's input again
                    if (line.contains("- ") || line.contains("+"))
                    {
                        System.out.println("Polynomial must not contain any operators unless it's a negative sign attached to a number (-1).");
                            
                        System.out.println("Enter a new Polynomial as a separated pair of coefficients and exponents. " +
                            "Ex: '2x^5 - 3' should be entered as '2.0 5 -3 0': ");
                        
                        // reads user's input again
                        line = keybScanner.nextLine();
                        
                        if (line.toLowerCase().contains("quit"))
                            break;
                        
                        lineScanner = new Scanner(line);
                    }
                    else
                        break;
                }
                                   
                // loop to determine if there is an exception, if there is asks user for input again
                while(true)
                {
                    try
                    {
                        if (line.toLowerCase().contains("quit"))
                            break;
                        
                        // loops through string and adds pairs of coefs and exps to p1
                        while (true)
                        {
                            firstCoef = Double.parseDouble(lineScanner.next());
                                    
                            firstExp = Integer.parseInt(lineScanner.next());
                    
                            p1 = p1.add(new Poly(firstCoef, firstExp));
                            
                            if (!lineScanner.hasNextDouble())
                                break;
                        }
                        
                        break;
                    }
                    catch(Exception e)
                    {
                        System.out.println("Polynomial was incorrectly entered");
                        
                        System.out.println("Enter a new Polynomial as a separated pair of coefficients and exponents. " +
                                "Ex: '2x^5 - 3' should be entered as '2.0 5 -3 0': ");

                        line = keybScanner.nextLine();

                        if (line.toLowerCase().contains("quit"))
                            break;
                        
                        lineScanner = new Scanner(line);
                    }
                }
                
                break;
            }
            
            // if quit is ever entered or contained within user's input, program stops automatically, there are many statements like this to make sure
            if (line.toLowerCase().contains("quit"))
                break;
                
            // keeps looping until user enter's valid operator or quit
            while(true)
            {
                System.out.println("Enter an operator (+, - , * or /): ");
                operator = keybScanner.nextLine();
                
                if ((operator.charAt(0) == '+') || (operator.charAt(0) == '-') || (operator.charAt(0) == '*') || (operator.charAt(0) == '/')) 
                    break;
                
                if (line.toLowerCase().contains("quit"))
                    break;
                    
                System.out.println("Operator was incorrectly entered. Please try again");
            }
            
            if (line.toLowerCase().contains("quit"))
                break;
            
            // loop  to read second exponent until it's valid or until user enters quit
            while (true)
            {
                System.out.println("Enter a new Polynomial as a separated pair of coefficients and exponents. " + 
                        "Ex: '2x^5 - 3' should be entered as '2.0 5 -3 0': ");
                    
                line = keybScanner.nextLine();
                
                if (line.toLowerCase().contains("quit"))
                    break;
                
                Scanner lineScanner = new Scanner(line);
                
                    // accounts for the fact if operators are used in input
                    while (true)
                    {
                        if (line.contains("- ") || line.contains("+"))
                        {
                                System.out.println("Polynomial must not contain any operators unless it's a negative sign attached to a number (-1).");
                                
                                System.out.println("Enter a new Polynomial as a separated pair of coefficients and exponents. " +
                                            "Ex: '2x^5 - 3' should be entered as '2.0 5 -3 0': ");
                                
                                line = keybScanner.nextLine();
                                
                                if (line.toLowerCase().contains("quit"))
                                    break;
                                    
                                lineScanner = new Scanner(line);
                        }
                        else
                            break;
                    }
                
                // loop to determine if there is an exception, if there is asks user for input again
                while(true)
                {
                    try
                    {
                        if (line.toLowerCase().contains("quit"))
                            break;       
                        
                        // loops through user input adding each pair of coefs and exps to p2
                        while (true)
                        {
                            secondCoef = Double.parseDouble(lineScanner.next());
                                        
                            secondExp = Integer.parseInt(lineScanner.next());
                        
                            p2 = p2.add(new Poly(secondCoef, secondExp));
                            
                            if (!lineScanner.hasNextDouble())
                                break; 
                        }
                        
                        break;
                    }
                    catch(Exception e)
                    {
                        System.out.println("Polynomial was incorrectly entered");
                                
                        System.out.println("Enter a new Polynomial as a separated pair of coefficients and exponents. " + 
                        "Ex: '2x^5 - 3' should be entered as '2.0 5 -3 0': ");
                        
                        line = keybScanner.nextLine();
                    
                        if (line.toLowerCase().contains("quit"))
                            break;
                    
                        lineScanner = new Scanner(line);
                    }
                }
                    
                break;
            }
            
            if (line.toLowerCase().contains("quit"))
                break;
        
            // performs operation based on what was entered
            if (operator.charAt(0) == '+')
            {
                p3 = p1.add(p2);
                System.out.println(p3.toString());
            }
            else if (operator.charAt(0) == '-')
            {
                p3 = p1.subtract(p2);
                System.out.println(p1.subtract(p2).toString());
            }
            else if (operator.charAt(0) == '*')
            {
                p3 = p1.multiply(p2);
                System.out.println(p3.toString());
            }
            else if (operator.charAt(0) == '/')
            {
                p3 = p1.divide(p2);
                
                if (p3 == null)
                    System.out.println("ERROR!");
                else
                    System.out.println(p3.toString());
            }

            // asks if user would like to do another calculation, still also exits if user enters quit
            while(true)
            {
                try 
                {
                    System.out.println("Would you like to do another calculation? (Enter y for yes and n for no): ");
                    line = keybScanner.nextLine();
                    
                    if (line.charAt(0) == 'y' || line.charAt(0) == 'n' || line.toLowerCase().contains("quit"))
                        break;
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Sorry, your response could not be understood");
                }
            }
            
            if (line.charAt(0) == 'n' || line.toLowerCase().contains("quit"))
                break;
        }
    }
}