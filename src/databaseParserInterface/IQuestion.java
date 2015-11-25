package databaseParserInterface;

import java.util.Map;

public interface IQuestion
{
	
	public int getQuestionNum();
	public void setQuestionNum(int questionNum);
	public Map<Integer, Map<String, Integer>> getClick();
	public Map<String, Integer> insertAttribute(String attribute, Map<String, Integer> attributesMap);
	public void insertClick(int index, String attribute);
}
