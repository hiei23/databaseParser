package databaseParser;

import java.util.Comparator;

public class tupleComparator implements Comparator<String>
{

	
	@Override
	/**
	 * sorts in increasing order
	 */
	public int compare(String o1, String o2)
	{
		String[] parts1 = o1.split(",");
		String[] parts2 = o2.split(",");
		
		int value1 = Integer.parseInt(parts1[1].trim());
		int value2 = Integer.parseInt(parts2[1].trim());
		
		if (value1 - value2 > 0)
		{
			return -1;
		}

		if (value1 - value2 < 0)
		{
			return 1;
		}
		
		return 0;
	}

}
