
public class BSTcount {
	
	private long[] storer;

	public long howMany(int[] values){
		storer = new long[values.length];
		return counter(values.length);
	}
	
	public long counter(int len){
		if (len == 0){
			return 1;
		}

		long total = 0;
		
		for (int left = 0; left < len; left++){
			
			int right = len - left - 1;
			
			if (storer[left] == 0){
				storer[left] = counter(left);
			}
			if (storer[right] == 0){
				storer[right] = counter(right);
			}
			total += storer[left] * storer[right];
		}
		
		return total;
	}
		
}