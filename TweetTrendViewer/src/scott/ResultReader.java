package scott;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import wordcram.Word;

public class ResultReader {
	private ArrayList<Word> _topwords;
	
	public void getWordCounts() {
		_topwords= new ArrayList<Word>();
		BufferedReader sc = null;
		while(true) {
			String path = JOptionPane.showInputDialog("Enter the path to the results from the MapReduce to visualize, enter q to quit");
			if(path.equals("q")) {
				break;
			}
			try {
				if(path.equals("q")) { System.exit(0); }
				sc = new BufferedReader(new FileReader(path));
				break;
			} catch (FileNotFoundException e1) {
				//JOptionPane.showMessageDialog(null, e1, path, 0, null);
				System.err.println("Unable to load the file specified.");
				e1.printStackTrace();
			}
		}
		
		//sc = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path)));
		String line="";
		StringTokenizer st;
		String word = "";
		int count=0;
		for(int i=0;i<200;i++) {
			try {
				line=sc.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("An Error Occured while reading the file");
			}
			if(line==null) {
				break;
			}
			st= new StringTokenizer(line);
			count = Integer.parseInt(st.nextToken());
			word = st.nextToken();
			Word myWord = new Word(word,count);
			System.out.println(word + " " + count);
			_topwords.add(myWord);
		}
	}
	
	public Word[] getWords() {
		Word[] ret = new Word[_topwords.size()];
		for(int i=0;i<_topwords.size();i++) {
			ret[i] = _topwords.get(i);
		}
		System.out.println("Ret size:" + ret.length);
		return ret;
	}
}
