import enigma.core.Enigma;


public class ParagraphNode {
	public enigma.console.Console cm = Enigma.getConsole("Text Editor",100,30,14,1); // col,row,fontsize,fonttype
	
	private int count;
	private static int id = 0;
	private ParagraphNode down;
	private ParagraphNode up;
	private LetterNode right;
	
	public ParagraphNode(int number){	
		count = number;
		down = null;
		up = null;
		right = null;
		id++;
	}
	
	
	public ParagraphNode(){	
		down = null;
		up = null;
		right = null;
		id++;
		
		cm.getTextWindow().setCursorPosition(65, 19);
		System.out.print(id);
	}
	
	public int getId() {
		return id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ParagraphNode getDown() {
		return down;
	}

	public void setDown(ParagraphNode down) {
		this.down = down;
	}

	public ParagraphNode getUp() {
		return up;
	}

	public void setUp(ParagraphNode up) {
		this.up = up;
	}

	public LetterNode getRight() {
		return right;
	}

	public void setRight(LetterNode right) {
		this.right = right;
	}
	
}
