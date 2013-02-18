package com.diycomputerscience.minesweeper.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.diycomputerscience.minesweeper.Board;
import com.diycomputerscience.minesweeper.Square;

public class UITest {
	
	private FrameFixture window;
	private Board board;
	
	@BeforeClass 
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}
	
	@Before
	public void setUp() throws Exception {
		UI ui = GuiActionRunner.execute(new GuiQuery<UI>() {

			@Override
			protected UI executeInEDT() throws Throwable {
				board = new Board();
				return UI.build(board);
			}
			
		});
		
		this.window = new FrameFixture(ui);
		this.window.show();
	}

	@After
	public void tearDown() throws Exception {
		this.window.cleanUp();
	}

	@Test
	public void testUIVisibility() {
		this.window.requireVisible();		
	}

	@Test
	public void testUIDefaultCloseOperation() {
		assertEquals(JFrame.EXIT_ON_CLOSE, ((JFrame)this.window.target).getDefaultCloseOperation());
	}
	
	@Test
	public void testUITitle() {
		assertEquals("Minesweeper", this.window.target.getTitle());
	}
	
	@Test
	public void testMainPanel() {
		JPanel mainPanel = this.window.panel("MainPanel").target;
		
		// verify that the contentPane contains a JPanel called "MainPanel"
		assertNotNull(mainPanel);
		
		// verify that the layoutManaget of the mainPanel is GridLayout
		assertEquals(GridLayout.class, mainPanel.getLayout().getClass());
		
		// verify the dimensions of the GridLayout
		assertEquals(Board.MAX_ROWS, ((GridLayout)mainPanel.getLayout()).getRows());
		assertEquals(Board.MAX_COLS, ((GridLayout)mainPanel.getLayout()).getColumns());	
	}
	
	@Test
	public void testSquares() {
		JPanel mainPanel = this.window.panel("MainPanel").target;
		
		Component components[] = mainPanel.getComponents();
		
		// verify that the mainPanel has Board.MAX_ROWS x Board.MAX_COLS components
		assertEquals(Board.MAX_ROWS*Board.MAX_COLS, components.length);
		
		// verify that each component in the mainPanel is a JButton
		for(Component component : components) {
			assertEquals(JButton.class, component.getClass());
		}
	}
	
	@Test
	public void testMineIdentificationOnUI() throws Exception {		
		Square squares[][] = this.board.getSquares();
		
		for(int row=0; row<squares.length; row++) {
			for(int col=0; col<squares.length; col++) {
				JButtonFixture squareUI = this.window.button(row+","+col);				
				if(squares[row][col].isMine()) {
					assertEquals(Color.RED, squareUI.background().target());
				} else {
					assertFalse(Color.red.equals(squareUI.background().target()));
				}
			}
		}
	}
	
	@Test
	public void testCountOnSquares() throws Exception {	
		Square squares[][] = this.board.getSquares();
		
		for(int row=0; row<squares.length; row++) {
			for(int col=0; col<squares.length; col++) {
				JButtonFixture squareUI = this.window.button(row+","+col);
				if(squares[row][col].isMine()) {
					squareUI.requireText("");					
				} else {
					squareUI.requireText(String.valueOf(squares[row][col].getCount()));
				}
			}
		}
	}
}
