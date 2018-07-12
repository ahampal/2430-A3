GENERAL PROBLEM
The general problem of the assignment is to rewrite assignment two to maintain
a record of investments(portfolio) using one arraylist that can also save the record 
to a file on program exit and open the file record on program start, however this time it is
required to implement a GUI as well as make use of abstraction and polymorphism

ASSUMPTIONS AND LIMITATIONS
An assumption is that symbol fields can not be empty and are case-insensitive this time.
A limitation is that in some cases the gain values can be incorrect. Another assumption is
that the command loop does not need to be preserved. I have included sample output files that
my program has saved after it has finished running (was required in A2)

USER GUIDE
Command Line
1. Open command prompt and set the directory to the project file (ie. ahampal_a2)
2. Enter the following on the command line:
"javac -cp . portfolio\*.java"
"java -cp . portfolio/Portfolio "Enter/text/file/directory"

if text file directory does not exist, program will be unable to open given textfile
but program will create the textfile at the given directory

NetBeans
1. Open all .java files in netbeans
2. Click the run button
3. program will exit as there has not been valid command line arguements entered yet
4. open output pane and select the double yellow arrow
5. enter arguments at "application.args =" and select run

TEST PLAN
the following steps were used to test the solution
1. Test defensive coding/user proofing
2. if program asks for quantity. Program should output error message for the following:
	a. enter string
	b. enter negative number
	c. enter decimal
3. if program asks for price. Program should output error message for the following:
	a. enter string
	b. enter negative number
4. if program asks for symbol when buying enter empty string
5. Test functionality
6. Test buy stock
	a. go to buy, select stock
	b. enter TD
	c. enter Toronto Dominion Bank
	d. enter 500
	e. enter 50
	f. Press save

	Expected:
	Symbol: TD
	Name: Toronto Dominion Bank
	Quantity: 500
	Price: 50.0
	Book Value: 25009.99

	Output:
	Symbol: TD
	Name: Toronto Dominion Bank
	Quantity: 500
	Price: 50.0
	Book Value: 25009.99
7. Test Update:
	a. go to update
	b. find TD, enter 45
	c. press save
	Expected:
	Symbol: TD
	Name: Toronto Dominion Bank
	Quantity: 500
	Price: 45.0
	Book Value: 25009.99

	Output:
	Symbol: TD
	Name: Toronto Dominion Bank
	Quantity: 500
	Price: 45.0
	Book Value: 25009.99
8. Test by MutualFund
	
	Expected:
	Symbol: CIG677
	Name: CI Signature Select Canadian
	Quantity: 450
	Price: 20.0
	Book Value: 9000.0

	Output:
	Symbol: CIG677
	Name: CI Signature Select Canadian
	Quantity: 450
	Price: 20.0
	Book Value: 9000.0
9. Test Search
	
	Expected:
	Symbol: CIG677
	Name: CI Signature Select Canadian
	Quantity: 450
	Price: 20.0
	Book Value: 9000.0

	Output:
	Symbol: CIG677
	Name: CI Signature Select Canadian
	Quantity: 450
	Price: 20.0
	Book Value: 9000.0
	

	Expected:
	Symbol: TD
	Name: Toronto Dominion Bank
	Quantity: 500
	Price: 50.0
	Book Value: 25009.99

	Output:
	Symbol: TD
	Name: Toronto Dominion Bank
	Quantity: 500
	Price: 50.0
	Book Value: 25009.99


	Expected:
	Symbol: TD
	Name: Toronto Dominion Bank
	Quantity: 500
	Price: 50.0
	Book Value: 25009.99

	Output:
	Symbol: TD
	Name: Toronto Dominion Bank
	Quantity: 500
	Price: 50.0
	Book Value: 25009.99


	Expected:
	Symbol: TD
	Name: Toronto Dominion Bank
	Quantity: 500
	Price: 50.0
	Book Value: 25009.99

	Output:
	Symbol: TD
	Name: Toronto Dominion Bank
	Quantity: 500
	Price: 50.0
	Book Value: 25009.99


	Expected:
	Symbol: CIG677
	Name: CI Signature Select Canadian
	Quantity: 450
	Price: 20.0
	Book Value: 9000.0

	Output:
	Symbol: CIG677
	Name: CI Signature Select Canadian
	Quantity: 450
	Price: 20.0
	Book Value: 9000.0


	Expected:
	Symbol: CIG677
	Name: CI Signature Select Canadian
	Quantity: 450
	Price: 20.0
	Book Value: 9000.0

	Output:
	Symbol: CIG677
	Name: CI Signature Select Canadian
	Quantity: 450
	Price: 20.0
	Book Value: 9000.0

	s. enter search
	t. enter canadian
	u. enter CIG677
	v. enter 15 - 25 or 15-25

	Expected:
	Symbol: CIG677
	Name: CI Signature Select Canadian
	Quantity: 450
	Price: 20.0
	Book Value: 9000.0

	Output:
	Symbol: CIG677
	Name: CI Signature Select Canadian
	Quantity: 450
	Price: 20.0
	Book Value: 9000.0
11. Test sell
	a. Go to Sell, select Stock
	b. enter TD
	c. enter 69.31
	d. enter 200
	e. enter sell
	f. enter CIG677
	g. enter 26.95
	h. enter 150
	Expected:
	Symbol: TD
	Name: Toronto Dominion Bank
	Quantity: 300
	Price: 69.31
	Book Value: 15005.995

	Symbol: CIG677
	Name: CI Signature Select Canadian
	Quantity: 300
	Price: 26.95
	Book Value: 6000.0

	Output:
	Symbol: TD
	Name: Toronto Dominion Bank
	Quantity: 300
	Price: 69.31
	Book Value: 15005.995

	Symbol: CIG677
	Name: CI Signature Select Canadian
	Quantity: 300
	Price: 26.95
	Book Value: 6000.0
12. Test gain
	Expected:
	The gain of the portfolio is: 12717.52
	Output:
	The gain of the portfolio is: 12717.52

IMPROVEMENTS
1. fix the gain  calculations to give proper output
2. More testing to ensure that proper custom exception handling has been implemented	
