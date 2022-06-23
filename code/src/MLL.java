import enigma.core.Enigma;


public class MLL {
	ParagraphNode head;
	static int count2 = 0;
	 public enigma.console.Console cm = Enigma.getConsole("Text Editor",100,30,14,1); // col,row,fontsize,fonttype
	
	 public ParagraphNode getHead() {
	        return head;
	    }
	 
	public void addParagraph(int number) {
		ParagraphNode newNode = new ParagraphNode(number);
		if(head == null)
			head = newNode;
		else {
			ParagraphNode temp = head;
			while(temp.getDown() != null)
				temp = temp.getDown();
			temp.setDown(newNode);
		}
	}
	
	public void addLine(){
		ParagraphNode newNode = new ParagraphNode();
		
		if(head == null)
			head = newNode;
		else {
			ParagraphNode temp = head;
			while(temp.getDown() != null)
				temp = temp.getDown();
			temp.setDown(newNode);
			
		}
		count2 = 0;
		
	}
	
	public void addChar(int number, Object ch) {
		if(head == null)
			System.out.println("Add a Paragraph before Char");
		else {
			ParagraphNode temp = head;
			while(temp != null) {
				if(number == temp.getId()) {
					LetterNode temp2 = temp.getRight();
					if(temp2 == null) {
						temp2 = new LetterNode(ch);
						temp.setRight(temp2);
					}
					else {
						while(temp2.getNext() != null)
							temp2 = temp2.getNext();
						LetterNode newNode = new LetterNode(ch);
						temp2.setNext(newNode);
					}
				}
				temp = temp.getDown();
			}
		}
	}
	
	public void addCharOver(int px ,int number, Object ch) {
		int count = 0;
		int counter = 1;
		if(head == null)
			System.out.println("Add a Paragraph before Char");
		else {
			ParagraphNode temp = head;
			LetterNode previous = null;
//			cm.getTextWindow().setCursorPosition(65, 25);
//			System.out.print(temp.getId());
			while(temp != null) {
				if(number == counter) {
					
					LetterNode temp2 = temp.getRight();
					while(temp2.getNext() != null){
						if(count == px-1){
							LetterNode newNode = new LetterNode(ch);
							previous.setNext(newNode);
							newNode.setNext(temp2);
						}
						previous = temp2;
						temp2 = temp2.getNext();
						count++;
					}
				} 
				counter++;
				temp = temp.getDown();
			}
		}		
	}
	
	public String coppy(int py ,int px, int amount){
		String cutted = "";
		int count = 0;
		int counter = 1;
		ParagraphNode temp = head;
		LetterNode previous = null;
//		cm.getTextWindow().setCursorPosition(65, 25);
//		System.out.print(temp.getId());
		while(temp != null) {
			if(py == counter) {
				LetterNode temp2 = temp.getRight();
				while(temp2.getNext() != null){
					if(count == px-1){
						for (int i = 0; i < amount-3; i++) {
							cutted += String.valueOf(temp2.getCh());
							temp2 = temp2.getNext();
						}
						cutted += String.valueOf(temp2.getCh());
						cm.getTextWindow().setCursorPosition(65, 23);
						System.out.print(cutted);
						break;
					}
					previous = temp2;
					temp2 = temp2.getNext();
					count++;
				}
			} 
			counter++;
			temp = temp.getDown();
		}
		return cutted;
	}
	
	public void paste(int py ,int px, String paste){
		int count = 0;
		int counter = 1;
		cm.getTextWindow().setCursorPosition(65, 23);
		System.out.print(paste.charAt(0));
		if(head == null)
			System.out.println("Add a Paragraph before Char");
		else {
			ParagraphNode temp = head;
			LetterNode previous = null;
//			cm.getTextWindow().setCursorPosition(65, 25);
//			System.out.print(temp.getId());
			while(temp != null) {
				if(py == counter) {
					
					LetterNode temp2 = temp.getRight();
					while(temp2.getNext() != null && count != px-1){
						previous = temp2;
						temp2 = temp2.getNext();
						count++;
					}
					if(count == px-1){
						cm.getTextWindow().setCursorPosition(65, 23);
						System.out.print("gaaa");
						for (int i = 0; i < paste.length(); i++) {
							LetterNode newNode = new LetterNode(paste.charAt(i));
							previous.setNext(newNode);
							newNode.setNext(temp2);
							previous = newNode;
							//temp2 = temp2.getNext();
						}
					}
					else if(temp2.getNext() == null){
						for (int i = 0; i < paste.length(); i++) {
							LetterNode newNode = new LetterNode(paste.charAt(i));
							temp2.setNext(newNode);
							temp2 = temp2.getNext();
						}
					}
				} 
				counter++;
				temp = temp.getDown();
			}
		}
	}
	
	public String cut(int py ,int px, int amount){
		
		String cutted = "";
		int count = 0;
		int counter = 1;
		boolean flag = false;
		ParagraphNode temp = head;
		LetterNode previous = null;
		LetterNode previous2 = null;
		
//		cm.getTextWindow().setCursorPosition(65, 25);
//		System.out.print(temp.getId());
		while(temp != null) {
			if(py == counter) {
				LetterNode temp2 = temp.getRight();
				while(temp2.getNext() != null){
					if(count == px-1){
						for (int i = 0; i < amount-3; i++) {
							cutted += String.valueOf(temp2.getCh());
							previous2 = temp2;
							temp2 = temp2.getNext();
						}
						cutted += String.valueOf(temp2.getCh());
						previous.setNext(temp2.getNext());
						
						//previous2.setNext(null);
						break;
					}
//					if(count == amount){
//						previousHolder.setNext(temp2.getNext());
//					}
					previous = temp2;
					temp2 = temp2.getNext();
					count++;
				}
			} 
			counter++;
			temp = temp.getDown();
		}
		return cutted;
	}
	
	public void deleteLetter2(int p, int x){
		ParagraphNode temp = head;
		int count = 1;
		int counter = 1;
		while(temp != null){
			if(counter== p){
				LetterNode temp2 = temp.getRight();
				LetterNode previous = null;
				while(temp2 != null){
					if(count == x -1){
						
						previous.setNext(temp2.getNext());
						//temp2 = previous;	
						break;
					}
					else if(temp2.getNext() == null){
						previous.setNext(null);
						break;
					}
					previous = temp2;
					temp2 = temp2.getNext();
					count++;
				}
			}
			counter++;
			temp = temp.getDown();
		}
	}
	
	
	
	 int sizeParagraph() {
		int count = 0;
		if(head == null)
			System.out.println("Linked list is empty");
		else {
			ParagraphNode temp = head;
			while(temp != null) {
				count++;
				temp = temp.getDown();
			}
		}
		return count;
	}
	
	 public int sizeLetter(int i) {
	        int count = 0;
	        if (head == null)
	            System.out.println("linked list is empty");
	        else {
	            ParagraphNode temp = head;
	            for (int j = 1; j < i; j++) {
	                temp = temp.getDown();
	            }
	            LetterNode temp2 = temp.getRight();
	            while (temp2 != null) {
	                count++;
	                temp2 = temp2.getNext();
	            }

	        }
	        return count;
	    }
	
	public int letters(int a){
		ParagraphNode temp = head;
		int count = 0;
		while(temp != null){
			if(temp.getId() == a){
				LetterNode temp2 = temp.getRight();
				while(temp2 != null){
					count++;
					temp2 = temp2.getNext();
				}
			}
			temp = temp.getDown();
		}
		return count;
	}
	
	public void clear(){
		for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 61; j++) {
				cm.getTextWindow().setCursorPosition(j, i);
				System.out.print(" ");
			}
		}
	}
	
	public void display() {
		if(head == null)
			System.out.println("Linked list is empty");
		else {
			clear();
			ParagraphNode temp = head;
			boolean line = false;
			int px = 1;
			int py = 1;
			while(temp != null) {
				LetterNode temp2 = temp.getRight();
				LetterNode previous = null;
				px = 1;
					while(temp2 != null) {
						
						if(px == 60){
							px=1;
							line = false;
							break;
						}
						if(temp2.getCh().equals('$')){
							line = true;
							break;
						}
						cm.getTextWindow().setCursorPosition(px, py);
						System.out.print(temp2.getCh());
						temp2 = temp2.getNext();
						px++;
						
						
					}
				
				temp = temp.getDown();
				px=1;
				py++;
			}
		}
	}
	
	public boolean intSearch(int data){
		ParagraphNode temp = head;
		boolean flag = false;
		while(temp != null){
			LetterNode temp2 = temp.getRight();
			while(temp2 != null){
				if(data == (int)temp2.getCh()){
					flag = true;
				}
				temp2 = temp2.getNext();
			}
			temp = temp.getDown();
		}
		return flag;
	}
	
	public String save() {
		String text = "";
		if(head == null)
			System.out.println("Linked list is empty");
		else {
			ParagraphNode temp = head;
			while(temp != null) {
				LetterNode temp2 = temp.getRight();
					while(temp2 != null) {
						text += temp2.getCh();
						temp2 = temp2.getNext();
					}
				temp = temp.getDown();
				text += "\n";
			}
		
		}
		return text;
	}
	
	public boolean isEmpty() {
		boolean flag = false;
		if(head == null) flag = true;
		return flag;
	}
	
	

}
