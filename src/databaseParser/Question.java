package databaseParser;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import databaseParserInterface.IQuestion;

public class Question implements IQuestion
{

	private int questionNum;
	private Map<String, Integer> attributesMap;
	
	public Question(int questionNum)
	{
		this.setQuestionNum(questionNum);
		this.attributesMap = new HashMap<String, Integer>();
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
	public Map<String, Integer> getAttributes()
	{
		return Collections.unmodifiableMap(attributesMap);
	}
	
	/**
	 * @param attributes the attributes to set
	 */
	public void insertAttribute (String attribute)
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
	}


}
