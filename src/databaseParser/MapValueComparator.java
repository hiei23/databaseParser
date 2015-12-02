package databaseParser;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class MapValueComparator implements Comparator<Map.Entry<String, Integer>>
{

	public MapValueComparator()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * sorts it in descending order
	 * returns  1 if mapEntry1 > mapEntry2
	 *          0 if both are the same value
	 *         -1 if mapEntry1 < mapEntry2
	 */
	public int compare(Entry<String, Integer> mapEntry1, Entry<String, Integer> mapEntry2)
	{
		return (mapEntry1.getValue()).compareTo(mapEntry2.getValue());
	}

}
