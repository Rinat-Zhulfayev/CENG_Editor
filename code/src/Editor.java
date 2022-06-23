import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import enigma.console.TextAttributes;
import enigma.console.TextWindow;

import java.awt.Color;

public class Editor {
	public enigma.console.Console cn = Enigma.getConsole("Text Editor", 100, 30, 14, 1); // col,row,fontsize,fonttype
	public enigma.console.TextWindow cnt = cn.getTextWindow();
	public static TextAttributes att0 = new TextAttributes(Color.white, Color.black); // foreground, background color
	public static TextAttributes att1 = new TextAttributes(Color.black, Color.white);
	public TextMouseListener tmlis;
	public KeyListener klis;

	// ------ Standard variables for keyboard and mouse 2
	// --------------------------
	public int mousepr; // mouse pressed?
	public int mousex, mousey; // mouse text coords.
	public int keypr; // key pressed?
	public int rkey; // key (for press/release)
	public int rkeymod; // key modifiers
	public int capslock = 0; // 0:off 1:on
	// -----------------------------------------------------------------------------
	MLL MLL = new MLL();
	public int number_of_paragraph = 0;
	public int lineNumber = 0;
	public int cx = 1;
	public int cy = 1;
	public int c2x = 1;
	public int c2y = 1;
	public ParagraphNode currentNode = null;
	public int f1 = 0;
	public int f2 = 0;
	public String selectedString = "";
	public String strSearch = "";
	public ParagraphNode temp = null;
	public LetterNode temp2 = null;
	public ParagraphNode temp4 = null;
	public LetterNode temp3 = null;
	public int a = 0;

	public void printScreen() {
		String line;
		try {
			BufferedReader br = new BufferedReader(
					new FileReader("work_space.txt"));
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
		} catch (Exception e) {
		}
	}

	Editor() throws Exception { // --- Contructor
		printScreen();

		// ------ Standard code for keyboard and mouse 2 -------- Do not change
		// -----
		tmlis = new TextMouseListener() {
			public void mouseClicked(TextMouseEvent arg0) {
			}

			public void mousePressed(TextMouseEvent arg0) {
				if (mousepr == 0) {
					mousepr = 1;
					mousex = arg0.getX();
					mousey = arg0.getY();
				}
			}

			public void mouseReleased(TextMouseEvent arg0) {
			}
		};
		cn.getTextWindow().addTextMouseListener(tmlis);

		klis = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (keypr == 0) {
					keypr = 1;
					rkey = e.getKeyCode();
					rkeymod = e.getModifiersEx();
					if (rkey == KeyEvent.VK_CAPS_LOCK) {
						if (capslock == 0)
							capslock = 1;
						else
							capslock = 0;
					}
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		};
		cn.getTextWindow().addKeyListener(klis);
		// --------------------------------------------------------------------------

		int curtype;
		curtype = cnt.getCursorType(); // default:2 (invisible) 0-1:visible
		cnt.setCursorType(1);
		cn.setTextAttributes(att0);

		/*
		 * // console clear (white background) cn.setTextAttributes(att1); for(int x=0;
		 * x<100*30-1; x++) { System.out.print(" "); }
		 */
		boolean overwrite = false;
		boolean select = false;
		int px = 1, py = 1;
		cnt.setCursorPosition(px, py);
		System.out.print("");
		cnt.setCursorPosition(65, 15);
		System.out.print("Please press ALT button");
		cnt.setCursorPosition(65, 16);
		System.out.print("to change mode into overwrite.");
		int count = 0;
		int tempx = 0;
		int counter = 0;
		String paste = "";
		// --- main Editor loop ---
		while (true) {
			if (MLL.isEmpty()) {
				MLL.addLine();
			}

			if (keypr == 1) {
				if (rkey == KeyEvent.VK_LEFT && px >= 2)
					px--;
				if (rkey == KeyEvent.VK_RIGHT && px <= 60) {
					px++;
				}
				if (rkey == KeyEvent.VK_UP && py >= 2)
					py--;
				if (rkey == KeyEvent.VK_DOWN && py <= 19)
					py++;

				if (rkey == KeyEvent.VK_F1) {
					select = true;
					tempx = px;
				}
				if (select == true) {
					if (a == 0) {
						temp = MLL.getHead();
						temp2 = temp.getRight();
						a++;
					}
					if (rkey == KeyEvent.VK_F1) {
						f1 = 1;
					}
					if (f1 == 1) {
						int cc = tempx;
						if (rkey == KeyEvent.VK_RIGHT && px < 60) {
							if (lineNumber == 0) {
								for (int i = 0; i < cc - 1; i++) {
									temp2 = temp2.getNext();
								}
								lineNumber++;
							}
							px++;
							selectedString += temp2.getCh();
							temp2 = temp2.getNext();
							cn.setTextAttributes(att1);
							cnt.setCursorPosition(tempx, py);
							cnt.setCursorPosition(0, 24);
							System.out.print("selected;");
							for (int i = 0; i < selectedString.length(); i++) {
								System.out.print(selectedString.substring(i, i + 1));
							}

						}
						if (rkey == KeyEvent.VK_LEFT && px > 1) {
							px--;
							selectedString = selectedString.substring(0, selectedString.length() - 1);
							cn.setTextAttributes(att1);
							cnt.setCursorPosition(tempx, py);
							temp2 = temp2.getPrevious();
							cnt.setCursorPosition(0, 25);
							System.out.print("selected;");
							for (int i = 0; i < selectedString.length(); i++) {
								System.out.print(selectedString.substring(i, i + 1));
							}

						}
						if (rkey == KeyEvent.VK_DOWN && py < 19) {
							py++;
							for (int i = 0; i < 60; i++) {
								if (cx < 60) {
									selectedString += temp2.getCh();
									temp2 = temp2.getNext();
								}
								if (cx == 60) {
									cx = 1;
									selectedString += temp2.getCh();
									temp2 = temp2.getNext();
								}
								cnt.setCursorPosition(tempx, py);
							}
							cnt.setCursorPosition(0, 26);
							System.out.print("selected;");
							for (int i = 0; i < selectedString.length(); i++) {
								System.out.print(selectedString.substring(i, i + 1));
							}
						}
						if (rkey == KeyEvent.VK_F3 || rkey == KeyEvent.VK_F3) {
							f2 = 1;
						}
						if (f2 == 1) {
							cn.setTextAttributes(att0);
							f1 = 0;
							f2 = 0;

						}
					}
					counter++;
				}
				if (rkey == KeyEvent.VK_F3) {
					select = false;
					paste = MLL.cut(py, tempx, counter);
					MLL.display();
					counter = 0;
				}
				if (rkey == KeyEvent.VK_F4) {
					select = false;
					paste = MLL.coppy(py, tempx, counter);
					MLL.display();
					counter = 0;
				}
				if (rkey == KeyEvent.VK_F5) {
					MLL.paste(py, px, paste);
					MLL.display();
				}
				if (rkey == KeyEvent.VK_F6) {
					int xx2 = -1;
					temp4 = MLL.getHead();
					temp3 = temp4.getRight();
					cnt.setCursorPosition(20, 25);
					System.out.print("Please enter the word you want to search: ");
					Scanner scn = new Scanner(System.in);
					String wordFýnd = scn.nextLine();
					for (int j = 0; j < MLL.sizeLetter(1); j++) {
						xx2++;
						for (int j2 = 0; j2 < wordFýnd.length(); j2++) {
							if (temp3.getNext() != null) {
								strSearch += temp3.getCh();
								temp3 = temp3.getNext();
							}
							if (strSearch.equalsIgnoreCase(wordFýnd)) {
								while (true) {
									cnt.setCursorPosition(xx2 - strSearch.length() + 4, 1);
									cn.setTextAttributes(att1);
									System.out.print(strSearch);
								}

							}
						}
						strSearch = "";
						for (int j2 = 0; j2 < wordFýnd.length() - 1; j2++) {
							if (temp3.getPrevious() != null)
								temp3 = temp3.getPrevious();
						}
					}
					cnt.setCursorPosition(0, 25);
					System.out.print("                                                          ");
					cnt.setCursorPosition(px, py);
					cnt.setCursorType(1);

				}

//					if (rkey == KeyEvent.VK_F3) {
//                        select = false;
//                    	paste = MLL.cut(py, tempx, count);
//         //           	cut = false;
//                        MLL.display();
//                    	counter = 0;
//                    }
//					
//					if (rkey == KeyEvent.VK_F4) {
//                        select = false;
//                    	paste = MLL.coppy(py, tempx, counter);
//                    	//coppy = false;
//                        MLL.display();
//                    	counter = 0;
//                    }
				char rckey = (char) rkey;

				if (rkey == KeyEvent.VK_ALT) {
					overwrite = true;
					cnt.setCursorPosition(65, 15);
					System.out.print("Overwrite mode activated.       ");
					cnt.setCursorPosition(65, 16);
					System.out.print("                                ");
				}
				if (rkey == KeyEvent.VK_ALT_GRAPH) {
					overwrite = false;
					cnt.setCursorPosition(65, 15);
					System.out.print("Insert mode activated.       ");
					cnt.setCursorPosition(65, 16);
					System.out.print("                                ");
				}

				// left right up down
				if ((rckey == '%' || rckey == '\'' || rckey == '&' || rckey == '(')) { // test without using VK //
																						// (Virtual Keycode)
					cnt.setCursorPosition(px, py);
					// System.out.print("");
				} else {
					cnt.setCursorPosition(px, py);
					if (rckey >= '0' && rckey <= '9') {
						// System.out.print(rckey);
						if (overwrite == false)
							MLL.addChar(py, rckey);
						else
							MLL.addCharOver(px, py, rckey);
						MLL.display();
						count++;
					}
					if (rckey >= 'A' && rckey <= 'Z') {
						if (((rkeymod & KeyEvent.SHIFT_DOWN_MASK) > 0) || capslock == 1) {
							// System.out.print(rckey);
							if (overwrite == false)
								MLL.addChar(py, rckey);
							else
								MLL.addCharOver(px, py, rckey);
							MLL.display();
							count++;
						} else {
							if (overwrite == false)
								MLL.addChar(py, (char) (rckey + 32));
							else
								MLL.addCharOver(px, py, (char) (rckey + 32));
							MLL.display();
							count++;
						}
					}
					if ((rkeymod & KeyEvent.SHIFT_DOWN_MASK) == 0) {
						if (rckey == '.' || rckey == ',' || rckey == '-') {
							if (overwrite == false)
								MLL.addChar(py, rckey);
							else
								MLL.addCharOver(px, py, rckey);
							MLL.display();
							count++;
						}
					} else {
						if (rckey == '.') {
							if (overwrite == false)
								MLL.addChar(py, ':');
							else
								MLL.addCharOver(px, py, ':');
							MLL.display();
							count++;
						}
						if (rckey == ',') {
							if (overwrite == false)
								MLL.addChar(py, ';');
							else
								MLL.addCharOver(px, py, ';');
							MLL.display();
							count++;
						}
					}
					// px++;
					// if(px == 61) {
					// px=1;
					// py++;
					// lineNumber++;
					// }
				}
				if (rkey == KeyEvent.VK_SPACE) {
					if (overwrite == false)
						MLL.addChar(py, ' ');
					else
						MLL.addCharOver(px, py, ' ');
					MLL.display();
					count++;
				}

				if (count == 59) {
					MLL.addLine();
					py++;
					count = 0;
				}

				if (rkey == KeyEvent.VK_ENTER) {
					MLL.addChar(py, '$');
					MLL.addLine();
					count = 0;
					py++;
					px = 1;
					cx = px;
					cy = py;
					MLL.display();
				}
				if (rkey == KeyEvent.VK_HOME) {
					cnt.setCursorPosition(1, cy);
					px--;
				}
				if (c2x <= px) {
					c2x = px;
				}
				if (c2y <= py) {
					c2y = py;
				}
				if (rkey == KeyEvent.VK_END) {
					cnt.setCursorPosition(c2x, c2y);
					px--;
				}
				if (rkey == KeyEvent.VK_PAGE_DOWN) {
					cnt.setCursorPosition(c2x, c2y);
					px--;
				}
				if (rkey == KeyEvent.VK_PAGE_UP) {
					cnt.setCursorPosition(1, 1);
					px--;
				}

//					if (a == 0) {
//                        temp = MLL.getHead();
//                        temp2 = temp.getRight();
//                        a++;
//                    }

//                    if (f1 == 1) {
//                    	tempx = px;
//                    	
//                    	
//                    	while(true){
//                    		cnt.setCursorPosition(65, 23);
//            				System.out.print("zaaaaaaaaaa");
//                    		if (rkey == KeyEvent.VK_RIGHT && px < 60) {
//                                px++;
//                        		
//                                //cnt.setCursorPosition(px, py);
////                                selectedString += temp2.getCh();
////                                temp2 = temp2.getNext();
////                                cn.setTextAttributes(att1);
////                                cnt.setCursorPosition(px, py);
////                                cnt.setCursorPosition(0, 24);
////                                System.out.print(selectedString);
//                                counter++;
//                            }
//                    		 if ((rckey == '%' || rckey == '\'' || rckey == '&' || rckey == '(')) { // test without using VK // (Virtual Keycode)
//         						cnt.setCursorPosition(px, py);
//         						// System.out.print("");
//         					}
//	                		 if (rkey == KeyEvent.VK_F2) {
//	                			 
//	                             f2 = 1;
//	                             MLL.cut(py, tempx, counter);
//	                         	 MLL.display();
//	                         }
//	                         if (f2 == 1) {
//	                            // cn.setTextAttributes(att0);
//	                             f1 = 0;
//	                             f2 = 0;
//	                             break;
//	                         }
//                    	}
//                    	
////                        if (rkey == KeyEvent.VK_RIGHT && px < 60) {
////                            px++;
////                            selectedString += temp2.getCh();
////                            temp2 = temp2.getNext();
////                            cn.setTextAttributes(att1);
////                            cnt.setCursorPosition(px, py);
////                            cnt.setCursorPosition(0, 24);
////                            System.out.print(selectedString);
////                            counter++;
////                        }
////                        if (rkey == KeyEvent.VK_LEFT && px > 1) {
////                            px--;
////                            int temper = tempx;
////                            selectedString = selectedString.substring(0, selectedString.length() - 1);
////                            cn.setTextAttributes(att0);
////                            cnt.setCursorPosition(px, py);
////                            temp2 = temp2.getPrevious();
////                            cnt.setCursorPosition(0, 25);
////                            System.out.print(selectedString);
////                            temper--;
////
////                        }
////                        if (rkey == KeyEvent.VK_DOWN && py < 19) 
////                        {
////                            py++;
////                            for (int i = 0; i < 60; i++) 
////                            {
////                                if(cx<60) 
////                                {
////                                selectedString += temp2.getCh();
////                                temp2 = temp2.getNext();
////                                }
////                                if(cx==60) 
////                                {
////                                cx=1;
////                                selectedString += temp2.getCh();
////                                temp2 = temp2.getNext();
////                                }
////                                cnt.setCursorPosition(px, py);
////                            }
////                            cnt.setCursorPosition(0, 26);
////                            System.out.print(selectedString);
////                            
////                        }
////                        if (rkey == KeyEvent.VK_F2) {
////                            f2 = 1;
////                        }
////                        if (f2 == 1) {
////                            cn.setTextAttributes(att0);
////                            f1 = 0;
////                            f2 = 0;
////
////                        }
//                    }

				if (rkey == KeyEvent.VK_BACK_SPACE) {
					MLL.deleteLetter2(py, px);
					MLL.display();
				}

				if (rkey == KeyEvent.VK_F11) {
					cn.getTextWindow().setCursorPosition(5, 22);
					System.out.println("Please enter the name of the loaded file");
					Scanner sc = new Scanner(System.in);
					String file_name = sc.nextLine();
					String line;
					int number_of_par = 0;
					cnt.setCursorPosition(px, py);
					try {
						BufferedReader br = new BufferedReader(new FileReader(file_name));
						while ((line = br.readLine()) != null) {
							System.out.println(line);
							for (char ch : line.toCharArray()) {
								MLL.addChar(number_of_par, ch);
								if (ch == '\n')
									number_of_par++;
							}
						}
						br.close();
						cn.getTextWindow().setCursorPosition(5, 22);
						System.out.println("Loaded successfully!                         ");
					} catch (Exception e) {
						cn.getTextWindow().setCursorPosition(5, 22);
						System.out.println("Wrong input!                                 ");
					}

				}
				if (rkey == KeyEvent.VK_F12) {
					cn.getTextWindow().setCursorPosition(5, 22);
					System.out.println("Please enter the name to save the file");
					Scanner sc = new Scanner(System.in);
					String file_name = sc.nextLine();
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter(file_name));
						bw.append(MLL.save());
						bw.close();
						cn.getTextWindow().setCursorPosition(5, 22);
						System.out.println("Saved successfully!                            ");
					} catch (IOException e) {
						System.out.println("Wrong input!                                   ");
					}
				}

				keypr = 0; // last action
				if (rkey == KeyEvent.VK_ESCAPE) {
					break;
				}
			}
			Thread.sleep(20);

		} // end of Editor loop
		cn.getTextWindow().setCursorPosition(1, 24);
		MLL.display();
	}
}