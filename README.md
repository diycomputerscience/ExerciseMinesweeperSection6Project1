<h1>Section 6 Project 1</h1>

<h2>Overview</h2>

In the previous activity, we mentioned that we have not tested the code which computes the counts. We need to fix this soon, because untested code is very dangerous, especially when it is important code. However, before we fix this, we need to fix something else, that is glaring at us.

Code Snippet 1 below, shows the ```computeNeibhbours``` method in ```MinesweeperUtils```.
 
    public static int[][] computeNeibhbours(int row, int col) {
        // At most a square can have 8 neighbours and at least 
        // it will have 3 neighbours
        int neighbours[][] = new int[8][2]; 
        
        // keep a count because we may have to compact the array
        int count = 0; 
		// row-1, col-1
		if(row-1 >=0 && col-1 >= 0) {
			neighbours[count][0] = row-1;
			neighbours[count][1] = col-1;
			count++;
		}
        
        // implementation not shown
        
        return neighbours;
    }

_**Code Snippet 1**_

As you can see, the method ```computeNeibhbours``` returns a 2 dimensional array, where every row of the array contains two values to represent the co-ordinates of a square on the Minesweeper board.  Look at the lines below in Code Snippet 2 (they have been copied from Code Snippet 1)

    neighbours[count][0] = row-1;
    neighbours[count][1] = col-1;

_**Code Snippet 2**_


This code, if not re-factored will make it very cumbersome, because every time we need to represent a set of locations, we will have to do it as a 2 dimensional array. Such an array is not only cumbersome to populate, but also unintuitive to create and use. Such a design is prone to errors.

Object oriented coding is all about encapsulating concepts into classes. The concept of a location represented by an x and y co-ordinate should be encapsulated as a ```Point``` class. It should not be represented by two values in an array.

The method above should ideally have a signature which returns an array of ```Point``` objects as shown in Code Snippet 3
.

    public static Point[] computeNeibhbours(int row, int col) {
        // return an array of Point objects where each point 
        // represents a mine location
    }

_**Code Snippet 3**_

Further more, all methods which ealier accepted ```row```, and ```col``` index as arguments should now accept a ```Point``` object. For example the ```uncover``` method in Board shown in Code Snippet 4 should be changed as shown in Code Snippet 5.

    public void uncover(int row, int col) throws UncoveredMineException {
	    this.squares[row][col].uncover();
    }

_**Code Snippet 4**_


    public void uncover(Point p) throws UncoveredMineException {
	    this.squares[p.row][p.col].uncover();
    }

_**Code Snippet 5**_


Code Snippet 6 shows what a ```Point``` class should look like.

    package com.diycomputerscience.minesweeper;

    public class Point {

	    public final int row;
	    public final int col;
	
	    public Point(int row, int col) {
		    this.row = row;
		    this.col = col;
	    }
    }

_**Code Snippet 6**_

Notice how the instance attributes in ```Point``` are public. This is safe because they are also ```final```. 

As an aside, the ```Point``` class is an immutable class, since once created it's state cannot be changed. Immutable classes have a lot of benefits, especially when in multi-threaded code. Joshua Bloch explains the benefits of immutable classes in his book, <a href="http://www.amazon.com/gp/product/B000WJOUPA/ref=as_li_qf_sp_asin_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=B000WJOUPA&linkCode=as2&tag=adaplearonli-20">Effective Java (2nd Edition) (Java Series)</a><img src="http://www.assoc-amazon.com/e/ir?t=adaplearonli-20&l=as2&o=1&a=B000WJOUPA" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;" />.


<h2>Steps For This Project</h2>

In Code Snippet 6 we have shown you code which you can use to create a ```Point``` class. Besides this, in this project, we have also changed all unit tests to expect a Point wherever a row, col pair was earlier given. Due to this, when you open this project, you will see a whole bunch of compile errors. Fixing these errors will give you an indication of what changes are required to method signatures. Once the compile errors are fixed, you need to refactor the methods to get AllTests to pass.

You may have noticed a little difference in the way we have outlined steps for this project. We need to reduce hand holding, gradually :-) 
