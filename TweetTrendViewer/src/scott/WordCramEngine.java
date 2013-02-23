package scott;

/*
 Copyright 2010 Daniel Bernier

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

import java.util.*;

import processing.core.PApplet;
import processing.core.PFont;
import wordcram.*;

public class WordCramEngine extends PApplet {
	
	WordCram wordcram;
	
	public void setup() {

		// destination.image.getGraphics():
		// P2D -> sun.awt.image.ToolkitImage, JAVA2D -> java.awt.image.BufferedImage.

		// parent.getGraphics():
		// P2D -> sun.java2d.SunGraphics2D, JAVA2D -> same thing.

		// P2D can't draw to destination.image.getGraphics(). Interesting.

		size(800, 600); // (int)random(300, 800)); //1200, 675); //1600, 900);
		smooth();
		colorMode(HSB);
		initWordCram();
		//frameRate(1);
	}
	
	private PFont randomFont() {
		String[] fonts = PFont.list();
		String noGoodFontNames = "Dingbats|Standard Symbols L";
		String blockFontNames = "OpenSymbol|Mallige Bold|Mallige Normal|Lohit Bengali|Lohit Punjabi|Webdings";
		Set<String> noGoodFonts = new HashSet<String>(Arrays.asList((noGoodFontNames+"|"+blockFontNames).split("|")));
		String fontName;
		do {
			fontName = fonts[(int)random(fonts.length)];
		} while (fontName == null || noGoodFonts.contains(fontName));
		System.out.println(fontName);
		return createFont(fontName, 1);
		//return createFont("Molengo", 1);
	}
	
	//PGraphics pg;
	private void initWordCram() {
		background(100);
		/*Word[] alphabet = new Word[] {
				  new Word("A", 8000), new Word("B", 4000), new Word("C", 200), 
				  new Word("D", 23), new Word("E", 22), new Word("F", 21),
				  new Word("G", 20), new Word("H", 19), new Word("I", 18),
				  new Word("J", 17), new Word("K", 16), new Word("L", 15),
				  new Word("M", 14), new Word("N", 13), new Word("O", 12),
				  new Word("P", 11), new Word("Q", 10), new Word("R", 9),
				  new Word("S", 8),  new Word("T", 7),  new Word("U", 6),
				  new Word("V", 5),  new Word("W", 4),  new Word("X", 3),
				  new Word("Y", 2),  new Word("Z", 1)
				};*/
		ResultReader r = new ResultReader();
		r.getWordCounts();
		Word[] alphabet= r.getWords();
		//pg = createGraphics(800, 600, JAVA2D);
		//pg.beginDraw();

		wordcram = new WordCram(this)
//					.withCustomCanvas(pg)
					.fromWords(alphabet)
//					.fromTextFile(textFilePath())
//					.fromWords(alphabet())
//					.upperCase()
//					.excludeNumbers()
					.withFonts(randomFont())
//					.withColorer(Colorers.twoHuesRandomSats(this))
//					.withColorer(Colorers.complement(this, random(255), 200, 220))
					.withAngler(Anglers.mostlyHoriz())
					.withPlacer(Placers.horizLine())
//					.withPlacer(Placers.centerClump())
					.withSizer(Sizers.byWeight(5, 90))
					
					.withWordPadding(1)
					
//					.minShapeSize(0)
//					.withMaxAttemptsForPlacement(10)
//					.maxNumberOfWordsToDraw(1000)
					
//					.withNudger(new PlottingWordNudger(this, new SpiralWordNudger()))
//					.withNudger(new RandomWordNudger())
					
					;
	}
	
	private void finishUp() {
		//pg.endDraw();
		//image(pg, 0, 0);
		
		//println(wordcram.getSkippedWords());
		
		println("Done");
		save("wordcram.png");
		noLoop();
	}
	
	public void draw() {
		//fill(55);
		//rect(0, 0, width, height);
		
		boolean allAtOnce = true;
		if (allAtOnce) {
			wordcram.drawAll();
			finishUp();
		}
		else {
			int wordsPerFrame = 1;
			while (wordcram.hasMore() && wordsPerFrame-- > 0) {
				wordcram.drawNext();
			}
			
			if (!wordcram.hasMore()) {
				finishUp();
			}
		}
	}
	
	public void mouseMoved() {
		/*
		Word word = wordcram.getWordAt(mouseX, mouseY);
		if (word != null) {
			System.out.println(round(mouseX) + "," + round(mouseY) + " -> " + word.word);
		}
		*/
	}
		
	public void mouseClicked() {
		initWordCram();
		loop();
	}
	
	public void keyPressed() {
		if (keyCode == ' ') {
			saveFrame("wordcram-##.png");
		}
	}
	
	private String textFilePath() {
		return "example/tao-te-ching.txt";
	}
	
	private Word[] alphabet() {
		Word[] w = new Word[26];
		for (int i = 0; i < w.length; i++) {
			w[i] = new Word(new String(new char[]{(char)(i+65)}), 26-i);
		}
		return w;
	}
}
