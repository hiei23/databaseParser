package databaseParser;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class SimpleDatabaseParserTester
{
	public static void main(String[] args) throws ParseException, IOException
	{
		
		databaseAnswersParser dp = new databaseAnswersParser("Answer.json");
		dp.parseJSONFile(dp.getJsonObject());
		dp.writeToFile();
	}

}
