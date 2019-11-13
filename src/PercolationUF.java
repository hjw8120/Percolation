
public class PercolationUF implements IPercolate {
	
	protected boolean[][] myGrid;
	protected int myOpenCount;
	IUnionFind myFinder = new QuickUWPC();
	
	private final int VTOP;
	private final int VBOTTOM;
	
	/**
	 * Create constructor for PercolationUF
	 * @param size int size of simulated square grid
	 * @param finder IUnionFind object
	 */
	public PercolationUF (int size, IUnionFind finder){
		myGrid = new boolean[size][size];
		finder.initialize(size*size+2);
		myFinder = finder;
		VTOP = size*size;
		VBOTTOM = size*size+1;
		myOpenCount = 0;
	}
	
	/**
	 * Determine if (row,col) is valid for given grid
	 * @param row specifies row
	 * @param col specifies column
	 * @return true if (row,col) on grid, false otherwise
	 */
	protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
	
	/**
	 * Returns true if and only if site (row, col) is OPEN
	 * 
	 * @param row row index in range [0,N-1]
	 * @param col column index in range [0,N-1]
	 */
	@Override
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException();
		}
		return myGrid[row][col];
	}
	
	/**
	 * Returns true if and only if site (row, col) is FULL
	 * 
	 * @param row row index in range [0,N-1]
	 * @param col column index in range [0,N-1]
	 */
	@Override
	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException();
		}
		return myFinder.connected(VTOP, row*myGrid.length+col);
	}
	
	/**
	 * Returns true if the simulated percolation actually percolates,
	 * which means VTOP and VBOTTOM are connected.
	 * 
	 * @return true iff the simulated system percolates
	 */
	@Override
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}
	
	/**
	 * Returns the number of distinct sites that have been opened in this
	 * simulation
	 * 
	 * @return number of open sites
	 */
	@Override
	public int numberOfOpenSites() {
		return myOpenCount;
	}
	
	/**
	 * Open site (row, col) if it is not already open. Add to count of open cells.
	 * If any neighbors are open, union cell with neighbor. If in top or bottom row,
	 * union with VTOP or VBOTTOM.
	 * 
	 * @param row row index in range [0,N-1]
	 * @param col column index in range [0,N-1]
	 */
	@Override
	public void open(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException();
		}
		if(!isOpen(row,col)) {
			myGrid[row][col] = true;
			myOpenCount += 1;
			
			int[] rowDelta = {-1,1,0,0};
			int[] colDelta = {0,0,-1,1};
			for(int k = 0; k < rowDelta.length; k++) {
				int nRow = row + rowDelta[k];
				int nCol = col + colDelta[k];
				if(inBounds(nRow,nCol)&& isOpen(nRow,nCol)) {
					myFinder.union(row*myGrid.length+col,nRow*myGrid.length+nCol);
				}
			}
			if(row == 0) {
				myFinder.union(VTOP,row*myGrid.length+col);
			}
			if(row == myGrid.length-1) {
				myFinder.union(VBOTTOM,row*myGrid.length+col);
			}
		return;
		}
	}
	
}
	
