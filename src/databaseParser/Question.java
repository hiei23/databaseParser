package databaseParser;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import databaseParserInterface.IQuestion;

public class Question implements IQuestion
{

	private int questionNum;
	//private Map<String, Integer> attributesMap;
	private Map<Integer, Map<String, Integer>> click;
	
	public Question(int questionNum)
	{
		this.setQuestionNum(questionNum);
		//this.attributesMap = new HashMap<String, Integer>();
		this.click = new HashMap<Integer, Map<String, Integer>>(); 
	}
	/**
	 * @return the questionNum
	 */
	public int getQuestionNum()
	{
		return questionNum;
	}
	/**
	 * @param questionNum the questionNum to set
	 */
	public void setQuestionNum(int questionNum)
	{
		this.questionNum = questionNum;
	}
	/**
	 * @return the attributes
	 */
	public Map<Integer, Map<String, Integer>> getClick()
	{
		return Collections.unmodifiableMap(this.click);
	}
	
	/**
	 * @param attributes the attributes to set
	 * @return 
	 */
	public Map<String, Integer> insertAttribute (String attribute, 
												 Map<String, Integer> attributesMap)
	{
		if (attributesMap.containsKey(attribute))
		{
			int counter = attributesMap.get(attribute);
			counter++;
			attributesMap.put(attribute, counter);
		}
		
		if (!attributesMap.containsKey(attribute))
		{
			attributesMap.put(attribute, 1);
		}
		return attributesMap;
	}
	
	public void insertClick(int index, String attribute)
	{
		if (click.containsKey(index))
		{
			click.put(index, insertAttribute(attribute, click.get(index)));
		}
		
		if (!click.containsKey(index))
		{
			Map<String, Integer> attributesMap = new HashMap<String, Integer>();
			
			click.put(index, insertAttribute(attribute, attributesMap));
		}
	}

}
