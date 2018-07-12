package portfolio;

import java.util.Objects;

/**
 * Investment Class has six instance variables which define every investment in this
 * project. Investment class contains the getters and setters methods that are used by
 * all its child classes, Stock and MutualFund. Setters and constructors now throw custom exceptions
 * Possess a few abstract methods that are defined later in its child classes
 * Also has equals and toString method that overrides and is overridden
 * Date: November 29th, 2017 
 * @author Amit Hampal
 */
abstract public class Investment {
    //Private instance variables
    private String Symbol;
    private String Name;
    private String Type;
    private int quantity;
    private float price;
    private float bookValue;
    private float gain;
    private int originalQuantity;
    private float ogBookVal;
    
    /**
     * Constructor
     * @param Symbol
     * @param Name
     * @param quantity
     * @param price
     * @param bookValue 
     * @param Type 
     * @param gain 
     * @throws portfolio.Investment.ExOne 
     * @throws portfolio.Investment.ExTwo 
     * @throws portfolio.Investment.ExThree 
     * @throws portfolio.Investment.ExFour 
     * @throws portfolio.Investment.ExFive 
     * @throws portfolio.Investment.ExSix 
     */
    public Investment(String Symbol, String Name, int quantity, float price, float bookValue, String Type, float gain) throws ExOne, ExTwo, ExThree, ExFour, ExFive, ExSix {
        if(Symbol.equals("") || !(Symbol.getClass().equals(String.class))) {
            throw new ExOne();
        }
        if(!(Name.getClass().equals(String.class)) || Name.equals("")) {
            throw new ExTwo();
        }
        if(quantity <= 0) {
            throw new ExThree();
        }
        if(price <= 0) {
            throw new ExFour();
        }
        this.Symbol = Symbol;
        this.Name = Name;
        this.quantity = quantity;
        this.price = price;
        this.bookValue = bookValue;
        this.Type = Type;
        this.gain = gain;
    }   

    /**
     * Copy Constructor
     * @param invest
     */
    public Investment(Investment invest) {
        this.Symbol = invest.Symbol;
        this.Name = invest.Name;
        this.quantity = invest.quantity;
        this.price = invest.price;
        this.bookValue = invest.bookValue;
        this.Type = invest.Type;
        this.gain = invest.gain;
    }    
    /**
     *Getter method for symbol field in Stock 
     * @return String containing Symbol of Stock
     */
    public String getSymbol() {
        return Symbol;
    }
     /**
     *Getter method for name field in Stock 
     * @return String containing Name of Stock
     */
    public String getName() {
        return Name;
    }  
    /**
     *Getter method for quantity field in Stock 
     * @return Integer equal to quantity of units of a stock
     */
    public int getQuantity() {
        return quantity;
    }   
    /**
     *Getter method for price field in Stock 
     * @return float equal to the price per unit of a stock
     */
    public float getPrice() {
        return price;
    }
    /**
     *Getter method for book value field in Stock 
     * @return float equal to the book value of a stock
     */
    public float getBookValue() {
        return bookValue;
    }
    /**
     * Getter method for type
     * @return string containing type
     */
    public String getType() {
        return Type;
    }
    /**
     * Getter method for gain
     * @return
     */
    public float getGain() {
        return gain;
    }
    /**
     * Getter method for originalQuantity field
     * @return integer
     */
    public int getOriginalQuantity() {
        return this.originalQuantity; 
    }
    /**
     * Getter method for ogBookVal field
     * @return float
     */
    public float getOriginalBV() {
        return this.ogBookVal; 
    }
    //mutator functions for instance variables
    /**
     * Setter method for originalQuantity field
     * @param quant
     */
    public void setOriginalQuantity(int quant) {
        this.originalQuantity = quant;
    }
    /**
     * Setter method for ogBookVal field
     * @param quant
     */
    public void setOGBV(float quant) {
        this.ogBookVal = quant;
    }
    /**
     *Setter method for symbol field in Stock
     *@param Symbol
     * @throws portfolio.Investment.ExOne
     */
    public void setSymbol(String Symbol) throws ExOne {
        if(Symbol.equals("")) {
            throw new ExOne();
        }
        this.Symbol = Symbol;
    }  
    /**
     *Setter method for Name field in Stock
     * @param Name
     * @throws portfolio.Investment.ExTwo
     */
    public void setName(String Name) throws ExTwo{
        if(!(Name.getClass().equals(String.class)) || Name.equals("")) {
            throw new ExTwo();
        }
        this.Name = Name;
    }    
    /**
     *Setter method for quantity field in Stock
     * @param quantity
     * @throws portfolio.Investment.ExThree
     */
    public void setQuantity(int quantity) throws ExThree {
        if(quantity <= 0) {
            throw new ExThree();
        }
        this.quantity = quantity;
    }    
    /**
     *Setter method for price field in Stock
     * @param price
     * @throws portfolio.Investment.ExFour
     */
    public void setPrice(float price) throws ExFour{
        if(price <= 0) {
            throw new ExFour();
        }
        this.price = price;
    }
    /**
     *Setter method for book value field in Stock
     * @param bookValue
     */
    public void setBookValue(float bookValue) {
        this.bookValue = bookValue;
    }
    /**
     * Setter method for Type
     * @param Type 
     */
    public void setType(String Type) {
        this.Type = Type;
    }
    /**
     * Setter method for gain
     * @param gain
     */
    public void setGain(float gain) {
        this.gain = gain;
    }
    /**
     *toString method used for printing out an investment
     * @return Formatted string containing all the fields of an investment
     */
    @Override
    public String toString() {
        return ("Type: " + Type + "\nSymbol: " + Symbol + "\nName: " + Name + "\nQuantity: " + quantity + "\nPrice: " + price + "\nBook Value: " + bookValue + "\n");
    }
    /**
     *Method to determine if investment object is identical to given object
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() == getClass()) {
            Investment i = (Investment)obj;
            return this.Name.equals(i.getName()) && this.Symbol.equals(i.getSymbol()) && this.Type.equals(i.getType()) && this.bookValue == i.getBookValue() && this.price == i.getPrice() && this.quantity == i.getQuantity();
        }
        else {
            return false;
        }
    }
    /**
     * Hash code function for investment objects
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.Symbol);
        hash = 97 * hash + Objects.hashCode(this.Name);
        hash = 97 * hash + Objects.hashCode(this.Type);
        hash = 97 * hash + this.quantity;
        hash = 97 * hash + Float.floatToIntBits(this.price);
        hash = 97 * hash + Float.floatToIntBits(this.bookValue);
        return hash;
    }    
    /**
     * Child class of Exception, and implements the exception thrown for 
     * an invalid symbol field
     */
    public class ExOne extends Exception {
        public ExOne() {
            super("Symbol Cannot be Empty");
        }
    }
    /**
     * Child class of Exception, and implements the exception thrown for 
     * an invalid Name field
     */ 
    public class ExTwo extends Exception {
        public ExTwo() {
            super("Name Cannot Be Empty");
        }
    }
    /**
     * Child class of Exception, and implements the exception thrown for 
     * an invalid quantity field
     */    
    public class ExThree extends Exception {
        public ExThree() {
            super("Quantity cannot be less than or equal to zero");
        }
    }
    /**
     * Child class of Exception, and implements the exception thrown for 
     * an invalid price field
     */   
    public class ExFour extends Exception {
        public ExFour() {
            super("Price cannot be less than or equal to zero");
        }
    }
    /**
     * Child class of Exception, and implements the exception thrown for 
     * an invalid book value input
     */    
    public class ExFive extends Exception {
        public ExFive() {
            super("book value is incorrect");
        }
    }
    /**
     * Child class of Exception, and implements the exception thrown for 
     * an invalid gain
     */    
    public class ExSix extends Exception {
        public ExSix() {
            super("gain is incorrect");
        }
    }    
    /**
     * abstract method to calculate the book value when buying
     * @param prevQuant
     */
    abstract public void calculateBuyBookValue(int prevQuant);    
    /**
     * abstract method to calculate the book value when selling
     * @param prevVal
     * @param q
     */
    abstract public void calculateSellBookValue(int prevVal, int q);   
    /**
     * abstract method to calculate the gain
     * @return
     */
    abstract public double Gain();   
    /**
     * abstract method to calculate payment received for an investment
     * @param quant
     * @return
     */
    abstract public double price(int quant);
}
