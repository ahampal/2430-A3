package portfolio;

/**
 * MutualFund Class: child class of the Investment class. Has the exact same instance
 * variables as Investment and stock, however it has different methods for calculating
 * book value, gains, equals, and toString. Also has abstract methods defined here  
 * Date: November 29th, 2017
 * @author Amit Hampal
 */
public class MutualFund extends Investment{
    //PUBLIC CONSTANT
    public static final double REDEMPTION_FEE = 45.00;
    
    /**
     *
     * @param quant
     * @return
     */
    @Override
    public double price(int quant) {
        return quant * getPrice() - REDEMPTION_FEE;
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
    public MutualFund(String Sym, String name, int quant, float price, float bookVal, String Type, float gain) throws ExOne, ExTwo, ExThree, ExFour, ExFive, ExSix {
        super(Sym, name, quant, price, bookVal, Type, gain);
    }
    
    /**
     * COPY CONSTRUCTOR
     * @param invest
     * @throws ExOne
     * @throws ExTwo
     * @throws ExThree
     * @throws ExFour
     * @throws ExFive
     * @throws ExSix
     */
    public MutualFund(MutualFund invest) throws ExOne, ExTwo, ExThree, ExFour, ExFive, ExSix {
        super(invest.getSymbol(), invest.getName(), invest.getQuantity(), invest.getPrice(), invest.getBookValue(), invest.getType(), invest.getGain());
    }
    
    /**
     *Method to calculate book value of mutual fund when buying a mutual fund
     */
    @Override
    public void calculateBuyBookValue(int prevQuant) {
        float b = getBookValue();
        this.setBookValue((float) (getQuantity() * getPrice()));
        
    }
    
    /**
     *Method to calculate the book value after selling some units of mutual fund
     * @param prevVal (of the quantity of units)
     * @param q (current quantity of units of a mutual fund)
     */
    @Override
    public void calculateSellBookValue(int prevVal, int q) {
        float fractional;
        float b;
        fractional = ((float) (prevVal - q)) / (float) prevVal;
        b = getBookValue();
        this.setBookValue((float)(b * fractional));
    }

    /**
     *Method to calculate gain before book value costs are considered
     * @return double containing gain
     */
    @Override
    public double Gain() {
        double gain;
        gain = getOriginalQuantity() * getPrice() - (getOriginalBV()) - REDEMPTION_FEE;
        return gain;
    }
    /**
     * Method to determine if MutualFund object is identical to given object
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MutualFund) {
            return super.equals(obj);
        }
        else {
            return false;
        }
    }
    /**
     * Hash code function for mutual fund objects
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
}
