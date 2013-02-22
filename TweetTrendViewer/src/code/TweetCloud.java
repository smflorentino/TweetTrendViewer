package code;

import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;

public class TweetCloud {
	private Cloud cloud;
	public TweetCloud(){
		cloud = new Cloud(); // create cloud cloud.setMaxWeight(38.0); // max font size
		cloud.setMaxTagsToDisplay(20); // at most 20 tags displayed
	}
	
	public void addTag(String word, int weight){
		Tag tag = new Tag(word, weight);
		
	}

}
