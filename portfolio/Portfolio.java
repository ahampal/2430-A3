package portfolio;
import java.io.*;
import static java.lang.Integer.MAX_VALUE;
import java.util.*;
import javax.swing.JTextArea;

/**
 * Portfolio class: class runs the main method and proceeds to run the GUI. 
 * The GUI then proceeds to call the necessary methods. The command loop from A2 
 * has been disabled.
 * Date: November 29th, 2017
 * @author Amit
 */
public class Portfolio {
    //instance variables of Portfolio class
    private ArrayList<Investment> investmentList;
    private HashMap <String, ArrayList<Integer>> nameIndex;
    /**main method containing command line interface that calls subsequent methods
     * depending on user input
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //variable declarations
        Portfolio s = new Portfolio();
        if(args.length != 1 || args[0].equals("") || args[0] == null) {
            System.err.println("Invalid command line arguements. Exiting program.");
            return;
        }
        s.load(args[0]);
        GUI newGUI = new GUI(s, args);
        s.save(args[0]);
    }
    /**
     *Constructor
     */
    public Portfolio() {
        this.investmentList = new ArrayList<>();
        this.nameIndex = new HashMap();
    }
    /**
     * Copy Constructor
     * @param p 
     */
    public Portfolio(Portfolio p) {
        this.investmentList = p.investmentList;
        this.nameIndex = p.nameIndex;
    }
    /**
     *method to buy more of an investment or to buy a new investment entirely
     * @param investmentList
     * @param newInvestment
     * @return array list of investments containing newly bought investment
     * @throws portfolio.Investment.ExOne
     * @throws portfolio.Investment.ExThree
     * @throws portfolio.Investment.ExTwo
     * @throws portfolio.Investment.ExFour
     * @throws portfolio.Investment.ExFive
     * @throws portfolio.Investment.ExSix
     */
    public void buy(Investment newInvestment) {
        //variable declarations
        int check = -1;
        int index = 0;
        String[] token;
        Investment tempStock;
        //check to see if new investment (based on symbol) already exists in list
        for(int g = 0; g < investmentList.size(); g++) {
            tempStock = investmentList.get(g);
            if(tempStock.getSymbol().toLowerCase().equals(newInvestment.getSymbol().toLowerCase())) {
                check = 1;
                index = g;
            }
        }
        //if investment is new add it to the list, otherwise update the 
        //existing investment based on price and quantity
        if(check == -1 ) {
            //determine type of investment and add it to the investment list
            float temp;
            newInvestment.calculateBuyBookValue(0);
            temp = newInvestment.getBookValue();
            newInvestment.setOGBV(temp);
            investmentList.add(newInvestment);
            token = newInvestment.getName().split(" ");
            for(int g = 0; g < token.length; g++) {
                ArrayList<Integer> newArray = nameIndex.get(token[g]);
                if(newArray == null) {
                    newArray = new ArrayList<>();
                }
                newArray.add(investmentList.indexOf(newInvestment));
                nameIndex.put(token[g], newArray);
                newArray = new ArrayList<>();
            }
        }
        //update existing investment
        else if(check == 1) {
            Investment tmpInvestment = investmentList.get(index);
            int previousQuantity = tmpInvestment.getQuantity();
            int newQuantity = previousQuantity + newInvestment.getQuantity();
            try {
                tmpInvestment.setQuantity(newQuantity);
                tmpInvestment.setPrice(newInvestment.getPrice());
            } catch (Investment.ExThree | Investment.ExFour ex) {
                
            }
            tmpInvestment.calculateBuyBookValue(previousQuantity);
            investmentList.set(index, tmpInvestment);
            token = tmpInvestment.getName().split(" ");
            for(int g = 0; g < token.length; g++) {
                ArrayList<Integer> newArray = nameIndex.get(token[g]);
                if(newArray == null) {
                    newArray = new ArrayList<>();
                }
                newArray.add(investmentList.indexOf(tmpInvestment));
                nameIndex.put(token[g], newArray);
            }
        }
    }
    /**
     *method to update the stock prices/mutual fund unit prices
     * @param index
     * @param price
     * @return 
     */
    public String update(int index, float price) {
        //attempt to set price of a particular investment to a certain price
        try {
            investmentList.get(index).setPrice(price);
        } catch (Investment.ExFour ex) {
        //output if it fails or succeeds
            return "Error in one or more fields: " + ex.getMessage() + "\n" + "Try again\n";
        }
        return "Sucessfully updated investment: \n" + investmentList.get(index).toString();
    }
    /**
     *Method to get information from user to sell investment
     * @param investmentList
     * @param sym
     * @param quant
     * @param price
     * @param u
     * @return 
     */
    public String sell(String sym, int quant, float price) {
        //variable declarations
        int index = -1;
        Investment toSell = null;
        //check to see if investment exists in list
        for(int q = 0; q < investmentList.size(); q++) {
            if(investmentList.get(q).getSymbol().toLowerCase().equals(sym.toLowerCase())) {
                toSell = investmentList.get(q);
                index = q; 
            }
        }
        //if stock exists, sell the stock
        if(index > -1 && toSell != null) {
            //check to make sure sell request is valid
            if(investmentList.get(index).getQuantity() < quant) {
                return "Available stocks less than requested stocks. Sale Failed.\n";
            }
            //if sold entire stock, remove from list. if it still exists, adjust quantity and values
            int prevVal = investmentList.get(index).getQuantity();
            try {
                toSell.setQuantity(investmentList.get(index).getQuantity() - quant);
            } catch (Investment.ExThree ex) {
                
            }
            try {
                toSell.setPrice((float) price);
            } catch (Investment.ExFour ex) {

            }
            if(investmentList.get(index).getQuantity() - quant == 0) {
                investmentList.remove(investmentList.get(index));
            }
            else if(toSell.getQuantity() > 0) {
                toSell.Gain();
                toSell.calculateSellBookValue(prevVal, quant);
                investmentList.set(index, toSell);
            }
            return "Payment Received " + (toSell.price(quant)) + "\n" + "Investment sold\n";
        }
        else {
            return "Investment could not be found\n";
        }
    }
    /**
     *Method to determine the entire gain of a portfolio by individually looking at the gains of each investment
     * @param investmentList
     * @param u
     * @return double equal to gain of entire portfolio
     */
    public double getNetGain(JTextArea u) {
        //get the individual gain of each of the investments and add them up
        Investment tmpInvestment;
        double netGain = 0;
        double individualGain = 0;
        String iGS;
        //gain for list
        for(int g = 0; g < investmentList.size(); g++) {
            tmpInvestment = investmentList.get(g);
                individualGain = tmpInvestment.Gain();
                u.append(investmentList.get(g).toString() +  "Gain of above investment is: " + String.format("%.2f", individualGain) + "\n\n");
                netGain = netGain + individualGain;
        }
        return netGain;
    }
     /**
     *Search method to go through both lists and search by symbol, name, and/or price range/value
     * @param investmentList
     * @param keyWords
     * @param sym
     * @param lowVal
     * @param maxVal
     * @param u
     */
    public void search(String keyWords, String sym, String maxVal, String lowVal, JTextArea u) {
        //variable declarations
        ArrayList<Investment> tmpList = new ArrayList<>(investmentList);
        float low;
        float high;
        float price;
        boolean flag = true;
        //sort list based on symbol. if no symbol given, no sort is performed.
        u.setText("");
        if(sym.equals("")) {
            //intentially left blank
        }
        //if symbol matches the list element, the element passes sort, otherwise it fails
        else {
            for(int g = 0; g < investmentList.size(); g++) {
                Investment tempInvestment = investmentList.get(g);
                if(!(tempInvestment.getSymbol().toLowerCase().equals(sym.toLowerCase()))) {
                    tmpList.remove(tempInvestment);
                }
            }
        }
        //sort list based on given keywords. if no key words given, no sort is
        //performed.
        if(keyWords.equals("")) {
            //intentially left blank
        }
        else {
            //hash map containing the keywords is initialized
            HashMap<String, ArrayList<Integer>> keywordsMap = new HashMap();
            Investment tempInvestment;
            //given string is split into tokens. Each token is then checked against
            //every investment name to see if it contains said keyword
            String[] tokens = keyWords.split(" ");
            String[] nameTokens;
            for(int h = 0; h < investmentList.size(); h++) {
                for(int g = 0; g < tokens.length; g++) {
                    //keyword check
                    if(!(investmentList.get(h).getName().toLowerCase().contains(tokens[g].toLowerCase()))) {
                        flag = false;
                    }
                }
                if(flag != false) {
                    //integer ArrayList is created and set to the array list stored in hashmap
                    //if it does not exist, a new array is created and used
                    for(int g = 0; g < tokens.length; g++) {
                        ArrayList<Integer> newArray = keywordsMap.get(tokens[g].toLowerCase());
                        if(newArray == null) {
                            newArray = new ArrayList<>();
                        }
                        //adding value to array, and then adding newly created/updated array to
                        //hashmap
                        newArray.add(h);
                        keywordsMap.put(tokens[g].toLowerCase(), newArray);                        
                    }
                }
                flag = true;
            }
            //every array list in the keyword map is then added to a 2-D ArrayList
            ArrayList<ArrayList<Integer>> listOfIndexLists = new ArrayList<>();
            for(int g = 0; g < tokens.length; g++) {
                ArrayList<Integer> indexList = keywordsMap.get(tokens[g].toLowerCase());
                if(indexList == null) {
                    indexList = new ArrayList<>();
                }
                listOfIndexLists.add(indexList);
                indexList = new ArrayList<>();
            }
            //the intersection of all the array lists in the 2-D array is found by
            //using the addAll method and a for-loop. The intersection ArrayList now
            //contains the indices of all investments that match all the keywords
            ArrayList<Integer> intersection = new ArrayList<>();
            for(int g = 0; g < listOfIndexLists.size(); g++) {
                intersection.addAll(listOfIndexLists.get(g));
            }
            //if an investment's index is not found within the intersection ArrayList
            //it is removed from the temporary list. Temporary List only contains
            //investments that pass the search
            for(int h = 0; h < investmentList.size(); h++) {
                tempInvestment = investmentList.get(h);
                if(!intersection.contains(h)) {
                    tmpList.remove(tempInvestment);
                }
                nameTokens = null;
            }
        }
        //sort based on given price ranges. if no range given, no sort is performed
        if(lowVal.equals("") && maxVal.equals("")) {
            //intentionally left blank
        }
        else {
            //if no low val provided, search defaults to a lowest possible val of zero
            if(lowVal.equals("")) {
                low = 0;
            }
            //attempt to parse low val from string provided by user
            else {
                try {
                    low = Float.parseFloat(lowVal);
                }catch(NumberFormatException nfe) {
                    u.append("Error in fields: " + nfe.getMessage());
                    return;
                }
            }
            //if no high val provided, search defaults to the lowest possible val of 2^31 -1
            if(maxVal.equals("")) {
                high = MAX_VALUE;
            }
            //attempt to parse high val from string provided by user
            else {
                try {
                    high = Float.parseFloat(maxVal);
                }catch(NumberFormatException nfe) {
                    u.append("Error in fields: " + nfe.getMessage());
                    return;
                }
            }
            //check to ensure given input is logical
            if( low > high ) {
                u.append("Low price must be less than High price. Retry Search.\n");
                return;
            }
            //search through investment list and remove investments that do not match
            for(int g = 0; g < investmentList.size(); g++) {
                price = investmentList.get(g).getPrice();
                if(price < low || price > high) {
                    tmpList.remove(investmentList.get(g));
                }
            }
        }
        //print out search results
        Investment ts;
        for(int g = 0; g < tmpList.size(); g++) {
            ts = tmpList.get(g);
            u.append(ts.toString() + "\n");
        }
    }
    /**
     * Method to save the state of the list 
     * @param file 
     */
    public void save(String file) {
        String filename = file;
        try {
            try(BufferedWriter w = new BufferedWriter(new FileWriter(filename))) {
                for(int i = 0; i < investmentList.size(); i++) {
                    w.write(investmentList.get(i).toString());
                }
            }
        }catch (IOException f) {
            System.err.println("Failed to write to " + filename);
        }
    }
    /**
     * Method to load given file information into an array list
     * @param file
     * @return array list of type investment containing information from file
     */
    public void load(String file) {
        ArrayList<Investment> iL = new ArrayList<>();
        BufferedReader r;
        String line;
        String[] split;
        int index;
        String[] nameToken;
        Investment currInvestment;
        try {
            r = new BufferedReader(new FileReader(file));
            line = r.readLine();
            index = 0;
            while(line != null && !line.equals("")) {
                split = line.split(": ");
                if(split.equals("Stock")) {
                    currInvestment = new Stock("`","`",1,1,10,">",0);
                }
                else {
                    currInvestment = new MutualFund("`","`",10,10,01,">",0);
                }
                currInvestment.setType(split[1]);
                line = r.readLine();
                split = line.split(": ");
                currInvestment.setSymbol(split[1]);
                line = r.readLine();
                split = line.split(": ");
                currInvestment.setName(split[1]);
                line = r.readLine();
                split = line.split(": ");
                currInvestment.setQuantity(Integer.parseInt(split[1]));
                line = r.readLine();
                split = line.split(": ");
                currInvestment.setPrice(Float.parseFloat(split[1]));
                line = r.readLine();
                split = line.split(": ");
                currInvestment.setBookValue(Float.parseFloat(split[1]));
                line = r.readLine();
                iL.add(currInvestment);
                nameToken = currInvestment.getName().split(" ");
                for(int g = 0; g < nameToken.length; g++) {
                    ArrayList<Integer> newArray = nameIndex.get(nameToken[g].toLowerCase());
                    if(newArray == null) {
                        newArray = new ArrayList<>();
                    }
                    newArray.add(iL.indexOf(currInvestment));
                    nameIndex.put(nameToken[g].toLowerCase(), newArray);
                    newArray = new ArrayList<>();
                }
                index++;
            }
        }catch(IOException e) {
            System.err.println("Unable to read " + file + ".\n");
        } catch (Investment.ExOne | Investment.ExTwo | Investment.ExThree | Investment.ExFour | Investment.ExFive | Investment.ExSix ex) {

        }
        investmentList = iL;
    }
    /**
     * Method to run the main command loop of the program 
     **DEPRECATED AS GUI HAS BEEN IMPLEMENTED**
     * @param args
     * @param p
     */
    @Deprecated
    public void commandLoop(String[] args, Portfolio p) {
        Scanner i = new Scanner(System.in);
        String a;
        int end = 0;
        //check to ensure command line arguements are correct
        if(args.length != 1 || args[0].equals("") || args[0] == null) {
            System.err.println("Invalid command line arguements. Exiting program.");
            return;
        }
        //command loop
        while(end != 1) {
            //get input from user
            System.out.println("**MENU OPTIONS**\nBuy" + "\nSell" + "\nUpdate" + "\nGet gain" + "\nSearch" + "\nQuit" + "\nEnter Command: ");
            a = i.nextLine();
            a = a.toLowerCase();
            switch(a) {
                //to buy an investment
                case "buy": 
                case "b":
                    System.out.println("Stock or Mutual Fund?");
                    a = i.nextLine();
                    a = a.toLowerCase();
                    //switch statement for stock case or mutual fund case
                    switch (a) {
                        case "stock":
                        case "s":
                            Stock newStock;
                            try {
                                newStock = new Stock("<", ">", 10, 01, 10, ";", 0);
                            } catch (Investment.ExOne | Investment.ExTwo | Investment.ExThree | Investment.ExFour | Investment.ExFive | Investment.ExSix ex) {
                                System.err.println("One hell of an error has occured\n");
                                return;
                            }
                            break;
                        case "mutual fund":
                        case "mf":
                        case "mutualfund":
                            MutualFund newMF;
                            try {
                                newMF = new MutualFund("`", "`", 01, 10, 10, "`", 0);
                            } catch (Investment.ExOne | Investment.ExTwo | Investment.ExThree | Investment.ExFour | Investment.ExFive | Investment.ExSix ex) {
                                System.err.println("Another crazy error has occured\n");
                                return;
                            }
                            break;
                        default:
                            System.err.println("Unknown input. Returing to Main Menu");
                            break;
                    }
                    break;
                //to sell an investment
                case "sell":
                    break;
                //to update an investment
                case "update":
                case "u":
                    break;
                //to get the gain of portfolio
                case "getgain":
                case "get gain":
                case "gg":
                case "g":
                case "get":
                    double gain = 1;
                    System.out.printf("The gain of the portfolio is: %.2f",gain);
                    System.out.println("");
                    break;
                //search through portfolio
                case "search":
                    break;
                //quit program
                case "quit":
                case "q":
                    end = 1;
                    break;
                //wrong input to command loop
                default:
                    System.err.println("Enter valid input please.");
                    break;
            }
        }
    }
    /**
     * Method for retrieving the investment list (unused)
     * @return ArrayList of containing objects of Investment type
     */
    public ArrayList<Investment> getInvestmentList() {
        //Since method returns an array of objects with private fields, the copy
        //Constructor is used to prevent privacy leaks by recreating the array with
        //Identical objects. This newly created list is then returned
        ArrayList<Investment> listToReturn = new ArrayList<>();
        Investment tmpInvestment;
        Stock stockInvestment;
        MutualFund mfInvestment;
        for(int i = 0; i < investmentList.size(); i++) {
            tmpInvestment = investmentList.get(i);
            if(tmpInvestment.getClass().equals(Stock.class)) {
                stockInvestment = (Stock)tmpInvestment;
                try {
                    listToReturn.add(new Stock(stockInvestment));
                } catch (Investment.ExOne | Investment.ExTwo | Investment.ExThree | Investment.ExFour | Investment.ExFive | Investment.ExSix ex) {
                    
                }
            }
            else {
                mfInvestment = (MutualFund)tmpInvestment;
                try {
                    listToReturn.add(new MutualFund(mfInvestment));
                } catch (Investment.ExOne | Investment.ExTwo | Investment.ExThree | Investment.ExFour | Investment.ExFive | Investment.ExSix ex) {
                    
                }
            }
        }
        return listToReturn;
    }
    /**
     * Method for setting the investment list to the given list (unused)
     * @param investmentList
     */
    public void setInvestmentList(ArrayList<Investment> investmentList) {
        //sets investment list to the one given
        this.investmentList = investmentList;
    }
}
