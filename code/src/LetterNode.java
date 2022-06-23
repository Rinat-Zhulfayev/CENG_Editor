
public class LetterNode {
	private Object ch;
	private LetterNode next;
	private LetterNode previous;
	
	public LetterNode(Object charToAdd){
		ch = String.valueOf(charToAdd).charAt(0);
		next = null;
		previous = null;
	}

	
	public Object getCh() {
		return ch;
	}


	public void setCh(Object ch) {
		this.ch = ch;
	}


	public LetterNode getNext() {
		return next;
	}

	public void setNext(LetterNode next) {
		this.next = next;
	}

	public LetterNode getPrevious() {
		return previous;
	}

	public void setPrevious(LetterNode previous) {
		this.previous = previous;
	}
}
