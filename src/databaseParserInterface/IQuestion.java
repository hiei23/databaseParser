package databaseParserInterface;

import java.util.Map;

public interface IQuestion
{
	
	public int getQuestionNum();
	public void setQuestionNum(int questionNum);
	public Map<String, Integer> getAttributes();
	public void insertAttribute(String attribute);
}
