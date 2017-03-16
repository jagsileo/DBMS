import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;
import java.util.*;

public class OMapper extends Mapper<Object, Text, Text, LongWritable> {
	 private Text word = new Text();
	 
	 @Override
	 public void map(Object key, Text value, Context context) throws IOException,
     InterruptedException {
		 
	   String line = value.toString();
	   StringTokenizer tokenizer = new StringTokenizer(line);
	   LongWritable count = new LongWritable();
	   
	   StringBuilder sb = new StringBuilder();  
	   
	   int words = 0;
	   while (tokenizer.hasMoreTokens()) {
		   
		   if(words == 4){
        	   word.set(sb.toString().trim());        	   
           }
		   else if(words == 5) {
			   count.set(Long.parseLong(tokenizer.nextToken()));
			   context.write(word, count);
			   break;
		   }
		   if(words < 4) 
		   		sb.append(tokenizer.nextToken()).append(" ");	
		   else
			   	tokenizer.nextToken();
		   words++;
	   }
	   
	 }
	 
}