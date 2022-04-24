/*Algo Problem 1 - Traffic Lane*/
import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.*;
class Problem1
{
    static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) 
	{
		int totalLane=sc.nextInt();
		int[][] lane=new int[totalLane][];
		LinkedHashMap laneOrder = new LinkedHashMap();
		int[] order=new int[2*totalLane];
		sc.nextLine();
		for(int i=0;i<totalLane;i++)
		{    
		    lane[i]=convert(sc.nextLine());
		    sortIt(lane[i],0,lane[i].length-1);
		    laneOrder.put(i,totalTime(lane[i]));
		}
		Map<String, String> sortedMap = sortMap(laneOrder);
		int[] result=convertToArray(sortedMap);
		System.out.println(Arrays.toString(result));
		return;
	}
	
    static void sortIt(int[] lane,int start,int end)
    {
        if (start < end)  
        {  
            int p = part(lane, start, end); // index of partition
            sortIt(lane, start, p - 1);  
            sortIt(lane, p + 1, end);  
        }  
    }
    
    static int totalTime(int[] lane)
    {
        int time=lane[0],len=lane.length;
        for(int i=1;i<len;i++)
            if(lane[i-1]!=lane[i])
                time+=lane[i];
        return time;
    }
    static int part(int[] values, int start, int end)  
    {  
        int pivot = values[end]; // pivot  
        int i = (start - 1);  
        for (int j = start; j <= end - 1; j++)  
        {  
            if (values[j] < pivot)  
            {  
                i++;  
                int swap = values[i];  
                values[i] = values[j];  
                values[j] = swap;  
            }  
        }  
        int t = values[i+1];  
        values[i+1] = values[end];  
        values[end] = t;  
        return (i + 1);  
    }  
    
	static int[] convert(String string) 
	{
        String[] partition = string.replace(", ",",").replace(",",", ").replace("[", "").replace("]", "").split(", ");
        int result[] = new int[partition.length],next=0;
    
        for (String part : partition) {                         //Conversion into Array
            try { result[next++] = Integer.parseInt(part); } 
            catch (NumberFormatException e) {   e.printStackTrace();    }
        }
        return result;
    }
    
    
    public static LinkedHashMap sortMap(LinkedHashMap map)      // Sorting LinkedHashMap
    {
        List <Entry<Integer, Integer>> newList = new LinkedList<>(map.entrySet());
        Collections.sort(newList, (l1, l2) -> l1.getValue().compareTo(l2.getValue()));
        LinkedHashMap<Integer, Integer> result = new LinkedHashMap();
        for (Map.Entry<Integer, Integer> entry : newList) {
        result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    
    static int[] convertToArray(Map map)    // Conversion of LinkedHashMap into Array
    {
      Iterator<Map.Entry<Integer, Integer>> itr = map.entrySet().iterator();
        int i=0; 
        int[] result=new int[2*map.size()];
        while(itr.hasNext())
        {
             Map.Entry<Integer, Integer> entry = itr.next();
             result[i++]=entry.getKey()+1;
             result[i++]=entry.getValue();
        }  
        return result;
    }
}