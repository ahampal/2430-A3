package portfolio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
/**
 * GUI Class: child class of JFrame. Possesses the implementation of the GUI designed
 * using swing and GridBagLayout. As a result, GUI Class handles all input and output between
 * the user and the portfolio class (which contains all functionality). 
 * @author Amit
 */
public class GUI extends JFrame {
    private GridBagConstraints gbc = new GridBagConstraints();
    int index = 0;
    
    public GUI(Portfolio p, String[] args){
        //Component Declarations
        String[] list = {"Stock", "MutualFund"};
        JMenuBar bar = new JMenuBar();
        JMenu commands = new JMenu("Commands");
        JMenuItem buy = new JMenuItem("buy");
        JMenuItem sell = new JMenuItem("sell");
        JMenuItem update = new JMenuItem("update");
        JMenuItem gg = new JMenuItem("getGain");
        JMenuItem search = new JMenuItem("search");
        JMenuItem quits = new JMenuItem("quit");
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        JPanel buyPanel = new JPanel(new GridBagLayout());
        JPanel buyButtonPanel = new JPanel(new GridBagLayout());
        JPanel sellButtonPanel = new JPanel(new GridBagLayout());
        JPanel updateButtonPanel = new JPanel(new GridBagLayout());
        JPanel searchButtonPanel = new JPanel(new GridBagLayout());
        JPanel formBuyPanel = new JPanel(new GridBagLayout());
        JPanel formSellPanel = new JPanel(new GridBagLayout());
        JPanel formUpdatePanel = new JPanel(new GridBagLayout());
        JPanel formSearchPanel = new JPanel(new GridBagLayout());
        JPanel msgPanel = new JPanel(new GridBagLayout());
        JPanel sellMsgPanel = new JPanel(new GridBagLayout());
        JPanel upMsgPanel = new JPanel(new GridBagLayout());
        JPanel searchMsgPanel = new JPanel(new GridBagLayout());
        JPanel sellPanel = new JPanel(new GridBagLayout());
        JPanel updatePanel = new JPanel(new GridBagLayout());
        JPanel gainPanel = new JPanel(new GridBagLayout());
        JPanel gainTop = new JPanel(new GridBagLayout());
        JPanel gainBottom = new JPanel(new GridBagLayout());
        JPanel searchPanel = new JPanel(new GridBagLayout());
        JLabel welcome = new JLabel("Welcome to Investment Portfolio.");
        JLabel usrInstructions = new JLabel("<html>Choose a command from the \"Commands\" menu to buy or sell <br> an investment, update prices for all investments, get gain for <br> the portfolio, search for relevant investments, or quit the <br>program.</html>");
        JComboBox typeField = new JComboBox(list);
        JTextField totalGain = new JTextField(10);
        JTextField symbolField = new JTextField(10);
        JTextField sellSymbolField = new JTextField(20);
        JTextField searchSymbolField = new JTextField(10);
        JTextField searchNameField = new JTextField(20);
        JTextField searchLowField = new JTextField(4);
        JTextField searchHighField = new JTextField(4);
        JTextField updateSymbolField = new JTextField(8);
        JTextField sellQuantityField = new JTextField(4);
        JTextField sellPriceField = new JTextField(4);
        JTextField updatePriceField = new JTextField(4);
        JTextField nameField = new JTextField(20);
        JTextField updateNameField = new JTextField(20);
        JTextField quantityField = new JTextField(4);
        JTextField priceField = new JTextField(4);
        JButton resetButton = new JButton("Reset");
        JButton anotherReset = new JButton("Reset");
        JButton yetAnotherReset = new JButton("Reset");
        JButton searchButton = new JButton("Search");
        JButton buyButton = new JButton("Buy");
        JButton sellButton = new JButton("Sell");
        JButton prev = new JButton("Prev");
        JButton Next = new JButton("Next");
        JButton Save = new JButton("Save");
        JTextArea msgArea = new JTextArea(5,50);
        JTextArea msgsArea = new JTextArea(5,50);
        JTextArea msgssArea = new JTextArea(5,50);
        JTextArea msgsssArea = new JTextArea(5,50);
        JTextArea msgssssArea = new JTextArea(5,50);
        JScrollPane sMsgArea = new JScrollPane(msgArea);
        JScrollPane ssMsgArea = new JScrollPane(msgsArea);
        JScrollPane sssMsgArea = new JScrollPane(msgssArea);
        JScrollPane ssssMsgArea = new JScrollPane(msgsssArea);
        JScrollPane sssssMsgArea = new JScrollPane(msgssssArea);
        
        //All items are added to the command menu and the command menu is added
        //to the bar which is then added to the frame
        commands.add(buy);
        commands.add(sell);
        commands.add(update);
        commands.add(update);
        commands.add(gg);
        commands.add(search);
        commands.add(quits);
        
        bar.add(commands);
        
        //set all communication fields to be uneditable by user
        msgArea.setEditable(false);
        msgsArea.setEditable(false);
        msgssArea.setEditable(false);
        msgsssArea.setEditable(false);
        msgssssArea.setEditable(false);
        
        //create welcome panel
        welcome.setFont(new Font("Calibri", Font.ITALIC, 42));
        usrInstructions.setFont(new Font("Calibri", Font.PLAIN, 38));
        
        //add messages to welcome panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(5,5,0,0);
        gbc.anchor = GridBagConstraints.WEST;
        welcomePanel.add(welcome, gbc);
        
        gbc.gridy++;
        welcomePanel.add(usrInstructions, gbc);
        
        //reset GridBagLayout to begin creating all the top left form panels
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 5;
        gbc.ipady = 5;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(0,0,0,0);
        gbc.anchor = GridBagConstraints.LINE_START;
        
        //set minimum size
        formBuyPanel.setMinimumSize(new Dimension(752,420));
        formSellPanel.setMinimumSize(new Dimension(752,420));
        formUpdatePanel.setMinimumSize(new Dimension(752,420));
        formSearchPanel.setMinimumSize(new Dimension(752,420));
        
        //add titles
        formBuyPanel.add(new JLabel("Buying an Investment"),gbc);
        formSellPanel.add(new JLabel("Selling an investment"), gbc);
        formUpdatePanel.add(new JLabel("Updating Investments"), gbc);
        formSearchPanel.add(new JLabel("Searching investments"), gbc);
        gainTop.add(new JLabel("Getting total gain"), gbc);
        
        //set insets
        gbc.insets = new Insets(2,15,0,0);
        gbc.gridy++;
        //add the necessary labels to each of the panels
        formBuyPanel.add(new JLabel("Type"), gbc);
        formSellPanel.add(new JLabel("Symbol"), gbc);
        formUpdatePanel.add(new JLabel("Symbol"), gbc);
        formSearchPanel.add(new JLabel("Symbol"), gbc);
        gainTop.add(new JLabel("Total Gain"),gbc);
        gbc.gridy++;
        formSellPanel.add(new JLabel("Quantiy"),gbc);
        formBuyPanel.add(new JLabel("Symbol"), gbc);
        formUpdatePanel.add(new JLabel("Name"), gbc);
        formSearchPanel.add(new JLabel("Name Keywords"), gbc);
        gbc.gridy++;
        formSellPanel.add(new JLabel("Price"), gbc);
        formBuyPanel.add(new JLabel("Name"), gbc);
        formUpdatePanel.add(new JLabel("Price"), gbc);
        formSearchPanel.add(new JLabel("High Price"), gbc);
        gbc.gridy++;
        formBuyPanel.add(new JLabel("Quantity"), gbc);
        formSearchPanel.add(new JLabel("Low Price"), gbc);
        gbc.gridy++;
        formBuyPanel.add(new JLabel("Price"), gbc);
        
        //reset GridBagLayout and add the text fields
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.NONE;
        formBuyPanel.add(typeField, gbc);
        formSellPanel.add(sellSymbolField, gbc);
        formSearchPanel.add(searchSymbolField, gbc);
        updateSymbolField.setEditable(false);
        totalGain.setEditable(false);
        formUpdatePanel.add(updateSymbolField, gbc);
        gainTop.add(totalGain, gbc);
        gbc.gridy++;
        formSellPanel.add(sellQuantityField, gbc);
        formBuyPanel.add(symbolField, gbc);
        formSearchPanel.add(searchNameField, gbc);
        updateNameField.setEditable(false);
        formUpdatePanel.add(updateNameField, gbc);
        gbc.gridy++;
        formSellPanel.add(sellPriceField, gbc);
        formBuyPanel.add(nameField, gbc);
        formSearchPanel.add(searchHighField, gbc);
        formUpdatePanel.add(updatePriceField, gbc);
        gbc.gridy++;
        formSearchPanel.add(searchLowField, gbc);
        formBuyPanel.add(quantityField, gbc);
        gbc.gridy++;
        formBuyPanel.add(priceField, gbc);
        
        //set minimum dinensions for button panel
        buyButtonPanel.setMinimumSize(new Dimension(370,420));
        sellButtonPanel.setMinimumSize(new Dimension(370,420));
        updateButtonPanel.setMinimumSize(new Dimension(370,420));
        searchButtonPanel.setMinimumSize(new Dimension(370,420));
        
        //reset gridbaglayout and add necessary buttons
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(25,100,5,10);
        buyButtonPanel.add(resetButton,gbc);
        gbc.gridy++;
        buyButtonPanel.add(buyButton,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 0;   
        gbc.insets = new Insets(25,100,5,10);
        sellButtonPanel.add(anotherReset,gbc);
        gbc.gridy++;
        sellButtonPanel.add(sellButton,gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5,100,5,10);
        updateButtonPanel.add(prev,gbc);
        gbc.gridy++;
        updateButtonPanel.add(Next,gbc);
        gbc.gridy++;
        updateButtonPanel.add(Save,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5,100,5,10);
        searchButtonPanel.add(yetAnotherReset,gbc);
        gbc.gridy++;
        searchButtonPanel.add(searchButton,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,3,0,3);
        
        //create message panel for each of the interfaces
        msgPanel.add(new JLabel("Messages"), gbc);
        sellMsgPanel.add(new JLabel("Messages"), gbc);
        upMsgPanel.add(new JLabel("Messages"), gbc);
        gainBottom.add(new JLabel("Individual gains"));
        searchMsgPanel.add(new JLabel("Search results"));
        
        gbc.gridy++;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gainBottom.add(ssssMsgArea, gbc);
        msgPanel.add(ssMsgArea, gbc);
        sellMsgPanel.add(sMsgArea, gbc);
        upMsgPanel.add(sssMsgArea, gbc);
        searchMsgPanel.add(sssssMsgArea, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NORTHEAST;
        
        //set minimum size for each of the interfaces
        welcomePanel.setPreferredSize(new Dimension(1130,620));
        buyPanel.setMinimumSize(new Dimension(1130,600));
        sellPanel.setMinimumSize(new Dimension(1130,600));
        updatePanel.setMinimumSize(new Dimension(1130,600));
        gainPanel.setMinimumSize(new Dimension(1130,600));
        searchPanel.setMinimumSize(new Dimension(1130,600));
        
        //create each of the interfaces
        buyPanel.add(formBuyPanel,gbc);
        sellPanel.add(formSellPanel,gbc);
        updatePanel.add(formUpdatePanel,gbc);
        gainPanel.add(gainTop,gbc);
        searchPanel.add(formSearchPanel,gbc);
        
        gbc.gridx++;
        buyPanel.add(buyButtonPanel,gbc);
        sellPanel.add(sellButtonPanel,gbc);
        updatePanel.add(updateButtonPanel,gbc);
        searchPanel.add(searchButtonPanel,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gainPanel.add(gainBottom, gbc);
        buyPanel.add(msgPanel,gbc);
        sellPanel.add(sellMsgPanel,gbc);
        updatePanel.add(upMsgPanel,gbc);
        searchPanel.add(searchMsgPanel,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        //set properties of JFrame
        setSize(new Dimension(1130,630));
        setLayout(new GridBagLayout());
        setJMenuBar(bar);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Investment Portfolio"); 
        add(welcomePanel,gbc);
        pack();
        //set initial button and field settings for update
        setVisible(true);
        prev.setEnabled(false);
        if(index == p.getInvestmentList().size()) {
            Next.setEnabled(false);
        }
        if(p.getInvestmentList().size() > 0) {
            Investment toDisplay = p.getInvestmentList().get(index);
            updateSymbolField.setText(toDisplay.getSymbol());
            updateNameField.setText(toDisplay.getName());
        }
        //add listener to save button
        totalGain.setText(String.format("%.2f", p.getNetGain(msgsssArea)));
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Investment t;
                float z = -1;
                try {
                    z = Float.parseFloat(updatePriceField.getText());
                }catch(NumberFormatException  nfe) {
                    msgssArea.append("Entered Field is incorrect: " + nfe.getMessage() + "\n");
                    return;
                }
                msgssArea.append(p.update(index, z));
            }
        });
        //add listener to Next button
        Next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index++;
                if(index == p.getInvestmentList().size() - 1) {
                    Next.setEnabled(false);
                    prev.setEnabled(true);
                }
                else {
                    prev.setEnabled(true);
                    Next.setEnabled(true);
                }
                if(index < p.getInvestmentList().size()) {
                    try {
                        Investment toDisplay = p.getInvestmentList().get(index);
                        updateSymbolField.setText(toDisplay.getSymbol());
                        updateNameField.setText(toDisplay.getName());
                    }catch(IndexOutOfBoundsException j) {

                    }
                }
            }
        });
        //add listener to previous button
        prev.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                index--;
                if(index == 0) {
                    prev.setEnabled(false);
                    Next.setEnabled(true);
                }
                else {
                    Next.setEnabled(true);
                    prev.setEnabled(true);
                }
                if(index > -1) {
                    try {
                        Investment toDisplay = p.getInvestmentList().get(index);
                        updateSymbolField.setText(toDisplay.getSymbol());
                        updateNameField.setText(toDisplay.getName());
                    }catch(IndexOutOfBoundsException j) {

                    }
                }
           }
        });
        //add listener to sell button
        sellButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                int q = -1;
                float z = -1;
                Investment o;
                try {
                     o = new Stock("`","`",1,1,1,">",0);
                } catch (Investment.ExOne | Investment.ExTwo | Investment.ExThree | Investment.ExFour | Investment.ExFive | Investment.ExSix ex) {
                    return;
                }
                try {
                    q = Integer.parseInt(sellQuantityField.getText());
                    z = Float.parseFloat(sellPriceField.getText());
                    o.setSymbol(sellSymbolField.getText());
                    o.setQuantity(q);
                    o.setPrice(z);
                }catch(NumberFormatException | Investment.ExOne | Investment.ExFour | Investment.ExThree nfe) {
                   if(nfe.getMessage().contains("For input")) {
                        msgArea.append("Entered fields is incorrect; One or more empty field(s)\n");
                    }
                    else {
                        msgArea.append("Entered fields is incorrect; " + nfe.getMessage() + "\n");
                    }
                    return;
                }
                msgArea.append(p.sell( sellSymbolField.getText(), q, z));
                totalGain.setText(String.format("%.2f", p.getNetGain(msgsssArea)));
           }
        });
        //add listener to buy button
        buyButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                Investment newInvestment;
                if(typeField.getSelectedItem().toString().equals("Stock")) {
                    try { 
                        newInvestment = new Stock("`","`",1,1,1,">",0);
                    } catch (Investment.ExOne | Investment.ExTwo | Investment.ExThree | Investment.ExFour | Investment.ExFive | Investment.ExSix ex) {
                        msgsArea.append(ex.getMessage());
                        return;
                    }
                }
                else {
                    try {
                        newInvestment = new MutualFund("`","`",1,1,1,">",0);
                    } catch (Investment.ExOne | Investment.ExTwo | Investment.ExThree | Investment.ExFour | Investment.ExFive | Investment.ExSix ex) {
                        return;
                    }
                }
                try {
                    int g;
                    g = Integer.parseInt(quantityField.getText());
                    float f;
                    f = Float.parseFloat(priceField.getText());
                    newInvestment.setType(typeField.getSelectedItem().toString());
                    newInvestment.setSymbol(symbolField.getText());
                    newInvestment.setName(nameField.getText());
                    newInvestment.setQuantity(g);
                    newInvestment.setOriginalQuantity(g);
                    newInvestment.setPrice(f);
                }catch(NumberFormatException | Investment.ExOne | Investment.ExTwo | Investment.ExThree | Investment.ExFour nfe) {
                    if(nfe.getMessage().contains("For input")) {
                        msgsArea.append("Entered fields is incorrect; One or more empty field(s)\n");
                    }
                    else {
                        msgsArea.append("Entered fields is incorrect; " + nfe.getMessage() + "\n");
                    }
                    return;
                }
                p.buy(newInvestment);
                if(p.getInvestmentList().size() == 1) {
                    Investment toDisplay = p.getInvestmentList().get(index);
                    updateSymbolField.setText(toDisplay.getSymbol());
                    updateNameField.setText(toDisplay.getName());
                }
                if(p.getInvestmentList().size() == 2) {
                    Next.setEnabled(true);
                }
                msgsArea.append("Bought investment\n");
                totalGain.setText(String.format("%.2f", p.getNetGain(msgsssArea)));
           }
        });
        //add listener to search button
        searchButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               p.search(searchNameField.getText(), searchSymbolField.getText(), searchHighField.getText(), searchLowField.getText(), msgssssArea);
           }
        });
        //add listener to reset buttons for each interface
        yetAnotherReset.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               searchSymbolField.setText("");
               searchNameField.setText("");
               searchHighField.setText("");
               searchLowField.setText("");
               msgssssArea.setText("");
           }
        });
        anotherReset.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               sellSymbolField.setText("");
               sellQuantityField.setText("");
               sellPriceField.setText("");
               msgssssArea.setText("");
           }
        });
        resetButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               typeField.setSelectedIndex(0);
               symbolField.setText("");
               nameField.setText("");
               quantityField.setText("");
               priceField.setText("");
               msgsArea.setText("");
           }
        });
        ////add listener to quit option
        quits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.save( args[0]);
                System.exit(0);
            }
        });
        ////add listener to buy option
        buy.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                remove(welcomePanel);
                remove(sellPanel);
                remove(updatePanel);
                remove(gainPanel);
                remove(searchPanel);
                resetButton.doClick();
                setSize(new Dimension(1130,630));
                setLayout(new GridBagLayout());
                setJMenuBar(bar);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setTitle("Investment Portfolio");
                add(buyPanel,gbc);
                pack();
           }
        });
        ////add listener to sell option
        sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(welcomePanel);
                remove(buyPanel);
                remove(updatePanel);
                remove(gainPanel);
                remove(searchPanel);
                anotherReset.doClick();
                setSize(new Dimension(1130,630));
                setJMenuBar(bar);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setTitle("Investment Portfolio");
                add(sellPanel,gbc);
                pack();
            }
        });
        ////add listener to update option
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(welcomePanel);
                remove(buyPanel);
                remove(sellPanel);
                remove(searchPanel);
                remove(gainPanel);
                remove(searchPanel);
                index = 0;
                prev.setEnabled(false);
                if(index == p.getInvestmentList().size()) {
                    Next.setEnabled(false);
                }
                if(p.getInvestmentList().size() > 0) {
                    Investment toDisplay = p.getInvestmentList().get(index);
                    updateSymbolField.setText(toDisplay.getSymbol());
                    updateNameField.setText(toDisplay.getName());
                }
                if(p.getInvestmentList().size() == 1) {
                    Investment toDisplay = p.getInvestmentList().get(index);
                    updateSymbolField.setText(toDisplay.getSymbol());
                    updateNameField.setText(toDisplay.getName());
                    prev.setEnabled(false);
                    Next.setEnabled(false);
                }
                if(p.getInvestmentList().size() == 2) {
                    Next.setEnabled(true);
                }
                updatePriceField.setText("");
                msgssArea.setText("");
                setSize(new Dimension(1130,630));
                setJMenuBar(bar);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setTitle("Investment Portfolio");
                add(updatePanel,gbc);
                pack();
            }   
        });
        ////add listener to gg option
        gg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(welcomePanel);
                remove(buyPanel);
                remove(updatePanel);
                remove(sellPanel);
                remove(searchPanel);
                
                setSize(new Dimension(1130,630));
                setJMenuBar(bar);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setTitle("Investment Portfolio");
                add(gainPanel,gbc);
                pack();
            }               
        });
        ////add listener to search option
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(welcomePanel);
                remove(buyPanel);
                remove(sellPanel);
                remove(gainPanel);
                remove(updatePanel);
                yetAnotherReset.doClick();
                setSize(new Dimension(1130,630));
                setJMenuBar(bar);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setTitle("Investment Portfolio");
                add(searchPanel,gbc);
                pack();
            }     
        });
        //save on window close
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            @Override
            public void run() {
                p.save( args[0]);
            }
        }));
    }
    /**
     * Copy Constructor for GUI
     * @param g
     */
    public GUI(GUI g) {
        this.gbc = g.gbc;
        this.index = g.index;
    }
}
