package databaseParserInterface;

import java.util.List;

public interface IQuestions
{
	public IQuestion createQuestion(int questionNum);
	public IQuestion getQuestion(int questionNum);
	public boolean containsQuestion(int questionNum);
	public List<IQuestion> getQuestions();
}
