import java.io.IOException;
import org.apache.hadoop.io.*;
import java.util.*;
import org.apache.hadoop.mapreduce.Reducer;

public class WCReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
	
	private HashMap<Text, LongWritable> hm = new HashMap<Text, LongWritable>();
		
	@Override
	protected void reduce(Text key, Iterable<LongWritable> values, Context context)
			 throws IOException, InterruptedException {
		long count = 0;
		
		for (LongWritable value : values) 
			count = count + value.get();
		
		hm.put(new Text(key), new LongWritable(count));
	}
	  
	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException {
		
		context.write(new Text("Total distinct 4 grams"), new LongWritable(hm.size()));
	}

}