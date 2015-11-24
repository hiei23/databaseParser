package databaseParser;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import databaseParserInterface.IQuestion;

public class databaseAnswersParser
{
	private JSONObject jsonObject;
	private JSONParser parser;
	private Questions questions;
	
	public databaseAnswersParser(String file) 
	{
		this.setParser(new JSONParser());
		questions = new Questions();
	}

	
	/**
	 * @return the jsonObject
	 */
	public JSONObject getJsonObject()
	{
		return jsonObject;
	}

	/**
	 * @param jsonObject the jsonObject to set
	 */
	public void setJsonObject(JSONObject jsonObject)
	{
		this.jsonObject = jsonObject;
	}
	
	public JSONParser getParser()
	{
		return parser;
	}

	public void setParser(JSONParser parser)
	{
		this.parser = parser;
	}
	/**
	 * Converts a JSON file to an object
	 * @param jsonObject
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void parseJSONFile(JSONObject jsonObject) throws FileNotFoundException, IOException, ParseException
	{
		Object obj = getParser().parse(new FileReader("Answer.json"));
		jsonObject = (JSONObject) obj;
		JSONArray result = (JSONArray) jsonObject.get("results");

		for(int i = 0; i < result.size(); i++)
		{
			JSONObject row = (JSONObject) result.get(i);
			JSONArray questions = (JSONArray) row.get("answers");
			extractValues(questions);
		}
	}
	
	/**
	 * Extracts the contents of the JSON file.
	 * @param questions
	 */
	private void extractValues(JSONArray questions)
	{
		if (questions!=null)
		{
			if (!questions.isEmpty())
			{	//System.out.println("Total Questions: " + questions.size());
				@SuppressWarnings("unchecked")
				Iterator<JSONArray> question = questions.listIterator();
				
				int currentQuestion = 1;
				while(question.hasNext())
				{
					JSONArray attributesArray = (JSONArray) question.next();
					
					@SuppressWarnings("unchecked")
					Iterator<String> attribute =  attributesArray.listIterator();
					IQuestion q = this.questions.createQuestion(currentQuestion);
					
					//System.out.println("this is current question: " + currentQuestion);
					while (attribute.hasNext())
					{
						String currentAttribute = attribute.next();
						q.insertAttribute(currentAttribute);
					}
					currentQuestion++;	
				}
			}			
		}
	}

	/**
	 * Writes the parsed JSON file to a csv file
	 * @throws IOException
	 */
	public void writeToFile() throws IOException
	{
		FileWriter fw = new FileWriter(new File("Attributes.csv"));
		BufferedWriter bw = new BufferedWriter(fw);

		for(IQuestion q : this.questions)
		{
			String questionNum = "\tQuestion "+ q.getQuestionNum() + "\n";
			bw.write(questionNum);
			String attributes = "";
			for (String attribute: q.getAttributes().keySet())
			{
				attributes = String.format("%s,%s\n", attribute , q.getAttributes().get(attribute));
				bw.write(attributes);
			}
			bw.write("\n\n");
		}
		bw.close();
	}
}
