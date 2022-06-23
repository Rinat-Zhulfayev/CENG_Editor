# CENG_Editor
Simple console text editor

General Information
This project consists of an editor that provides various word processing capabilities in an active work space of 20*60 characters. 

Features
The editor has the capability of loading and saving the text files. It supports 26 letters of English alphabet (a-z, A-Z), digits (0-9), 
space and punctuation marks ( . , : ; ! ? - * + ). Other characters cannot be used and they are ignored.
The words should not be split between two lines. Paragraphs (ending with return) can be more than one line. 

The editor supports cursor movement in four directions, character insertion or deletion at a desired location. It has also two writing modes: 
insert and overwrite (default mode: insert). 


Special Keys
The editor supports the following special keys:
•	Cursor keys
•	Delete/backspace
•	Return
•	Home/End
•	PageUp/PageDown
•	Insert (to change the writing mode; insert/overwrite)
•	Function keys for various purposes

Selection and Cut/Copy/Paste
The editor provides cut/copy/paste capabilities by using the following keys:
•	F1: Marks the start of the selection		
•	F2: Marks the end of the selection
•	F3: Cut			
•	F4: Copy		
•	F5: Paste  

When a selection occurs, it should be highlighted.


Alignment
The editor supports different text alignment options through the following keys:
•	F9: Aligned to the left (default mode)			
•	F10: Justified

If an alignment key is used, current paragraph is aligned. If there is a selection, alignment is applied to the paragraphs of the selection. 
At the right of the editor active area, there are various items:
•	Function key explanations/reminders
•	Writing mode indicator: Insert/overwrite 
•	Paragraph alignment: When the user moves the cursor to another paragraph, the alignment of the paragraph must be shown at the right of the working area
