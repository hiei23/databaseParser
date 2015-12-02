package databaseParser;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	private String fileName;
	public databaseAnswersParser(String fileName) 
	{
		this.setParser(new JSONParser());
		questions = new Questions();
		this.fileName = fileName;
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
		Object obj = getParser().parse(new FileReader(this.fileName));
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
					int currentClick = 1;
					//System.out.println("this is current question: " + currentQuestion);
					while (attribute.hasNext())
					{
						//System.out.println("this is current click: " + currentClick);
						String currentAttribute = attribute.next();
						q.insertClick(currentClick, currentAttribute);
						currentClick++;
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
			//stores the attributes that have been visited
			List<String> usedAttributes = new ArrayList<String>();
			String questionNum = "\tQuestion "+ q.getQuestionNum() + "\n";
			bw.write(questionNum);
			
			for (int click : q.getClick().keySet())
			{	
				String currentClick = "Click " + click + "\n";
				bw.write(currentClick);
				Map<String, Integer> attributesMap = q.getClick().get(click);
				List<String> tuples = new ArrayList<String>();
				getTuples (attributesMap.keySet(), tuples, attributesMap,usedAttributes);
				
				//tuples.sort(new tupleComparator());
				
				for (String tuple: tuples)
				{
					bw.write(tuple);
				}
				bw.write("\n\n");
			}
		}
		bw.close();
	}
	
	private void getTuples (Set<String> key,List<String> tuples, Map<String, Integer> attributesMap,List<String> usedAttributes)
	{
		int max = 0;
		
		for (String attribute: key)
		{
			if (attributesMap.get(attribute) > max)
			{
				max = attributesMap.get(attribute);
			}
		}
		
		for (String attribute: key)
		{
			int current_counter = attributesMap.get(attribute);
			if ((current_counter == max) && (!usedAttributes.contains(attribute)))
			{
				String attributes = String.format("%s\n", attribute);
				tuples.add(attributes);
				usedAttributes.add(attribute);
			}
			
		}
	}
}