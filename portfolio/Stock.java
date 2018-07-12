package portfolio;

/**
 * Stock Class: child class of the Investment class. Has the same instance variables
 * as MutualFund and Investment. It however has different methods for calculating
 * the book value, gains, equals, and toString. Also has abstract methods defined here
 * Date: November 29th, 2017
 * @author Amit Hampal
 */
public class Stock extends Investment{
    //PUBLIC CONSTANT
    public static final double COMMISSION_FEE = 9.99;
    
    /**
     * Method used to determine payment received
     * @param quant
     * @return
     */
    @Override
    public double price(int quant) {
        return quant * getPrice() - COMMISSION_FEE;
    }
    /**
     * Constructor
     * @param Sym
     * @param name
     * @param quant
     * @param price
     * @param bookVal 
     * @param Type
     * @param gain
     * @throws portfolio.Investment.ExOne
     * @throws portfolio.Investment.ExTwo
     * @throws portfolio.Investment.ExThree
     * @throws portfolio.Investment.ExFour
     * @throws portfolio.Investment.ExFive
     * @throws portfolio.Investment.ExSix
     */
    public Stock(String Sym, String name, int quant, float price, float bookVal, String Type, float gain) throws ExOne, ExTwo, ExThree, ExFour, ExFive, ExSix {
        super(Sym, name, quant, price, bookVal, Type, gain);
    }
    
    public Stock(Stock invest) throws ExOne, ExTwo, ExThree, ExFour, ExFive, ExSix {
        super(invest.getSymbol(), invest.getName(), invest.getQuantity(), invest.getPrice(), invest.getBookValue(), invest.getType(), invest.getGain());
    }
    /**
     *Method to calculate book value of stock when buying a stock
     * @param prevQuant
     */
    @Override
    public void calculateBuyBookValue(int prevQuant) {
        setBookValue( (float)((float) ((getQuantity() - prevQuant) * getPrice()) + COMMISSION_FEE));
    } 
    /**
     * Method to calculate the book value after selling some units of stock
     * @param prevVal
     * @param q
     */
    @Override
    public void calculateSellBookValue(int prevVal, int q) {
        float fractional;
        float b;
        fractional = ((float) (prevVal - q)) / (float) prevVal;
        b = getBookValue();
        setBookValue((float) (b * fractional));
    }
    /**
     * Method to calculate gain before book value costs are considered
     * @return double containing gross gain
     */
    @Override
    public double Gain() {
        double gain;
        gain = (getOriginalQuantity() * getPrice()) - COMMISSION_FEE - (getOriginalBV());
        return gain;
    }
    /**
     * Method to determine if stock object is identical to given object
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Stock) {
            return super.equals(obj);
        }
        else {
            return false;
        }
    }
    /**
     * Hash Code function for stock object
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
}
