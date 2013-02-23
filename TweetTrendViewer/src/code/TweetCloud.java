package code;

import java.util.ArrayList;

import javax.swing.JEditorPane;

import org.mcavallo.opencloud.*;
import org.mcavallo.opencloud.formatters.*;

import scott.ResultReader;


public class TweetCloud {
	private Cloud cloud;
	private ArrayList<Tag> tags;
	
//	Get the top tags (number of top tags defined in ResultReader
//	Add the top tags to the cloud
	public TweetCloud(){
		System.out.println("Welcome to TweetCloud");
		cloud = new Cloud(); // create cloud 
		cloud.setMaxWeight(50.0); // max font size
//		TODO: change to be variable path name
		ResultReader r= new ResultReader();
		//ResultReader r = new ResultReader("inputs/tags.txt");
		r.getWordCounts();
		tags = r.getTags();
		cloud.addTags(tags);
		for(Tag t:tags){
			t.setLink("");
	//		t.normalize(tags.get(0).getScore());
		}
		HTMLFormatter h = new HTMLFormatter();
		h.addCss("body","font-size","20");
		Visualizer v = new Visualizer(h.html(cloud));//this is a jframe
		v.start();
	}
	
//	public void addTag(String word, int weight){
//		Tag tag = new Tag(word, weight);	
//	}

}
