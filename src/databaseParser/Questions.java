package databaseParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import databaseParserInterface.IQuestion;
import databaseParserInterface.IQuestions;

public class Questions implements IQuestions, Iterable<IQuestion>
{

	private List<IQuestion> questionList;
	
	public Questions()
	{
		questionList = new ArrayList<IQuestion>();
	}

	@Override
	public IQuestion createQuestion(int questionNum)
	{
		if (!containsQuestion(questionNum))
		{
			IQuestion q = new Question(questionNum);
			this.questionList.add(q);
			return  q;
		}
		if (containsQuestion(questionNum))
		{
			return getQuestion(questionNum);
		}
		return null;
	}

	@Override
	public IQuestion getQuestion(int questionNum)
	{
		for (IQuestion currentQuestion: this.questionList)
		{
			if (currentQuestion.getQuestionNum() == questionNum)
			{
				return currentQuestion;
			}
		}
		return null;
	}

	@Override
	public boolean containsQuestion(int questionNum)
	{
		for (IQuestion q: this.questionList)
		{
			if (q.getQuestionNum() == questionNum)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public List<IQuestion> getQuestions()
	{
		return Collections.unmodifiableList(this.questionList);
	}

	@Override
	public Iterator<IQuestion> iterator()
	{
		// TODO Auto-generated method stub
		return new QuestionsIterator(this.questionList);
	}
	
	private class QuestionsIterator implements Iterator<IQuestion>
	{
		private List<IQuestion> questionList;
		private int currentIndex;
		private int cap;
		
		public QuestionsIterator(List<IQuestion> questions)
		{
			questionList = questions;
			cap = questions.size();
			currentIndex = 0;
		}
		@Override
		public boolean hasNext()
		{
			// TODO Auto-generated method stub
			return cap != currentIndex;
		}

		@Override
		public IQuestion next()
		{
			IQuestion q = questionList.get(this.currentIndex);
			this.currentIndex ++;
			return q;
		}
		
	}

}
