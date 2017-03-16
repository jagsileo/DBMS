import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;
import java.util.*;

public class WCMapper extends Mapper<Object, Text, Text, LongWritable> {
	 
	 private final static LongWritable one = new LongWritable(1);
	 private Text word = new Text();
	 
	 @Override
	 public void map(Object key, Text value, Context context) throws IOException,
     InterruptedException {
		 
	   String line = value.toString();
	   StringTokenizer tokenizer = new StringTokenizer(line);
	   
	   StringBuilder sb = new StringBuilder();  
	   
	   int words = 0;
	   
	   while (tokenizer.hasMoreTokens()) {
		   if(words++ == 4){
        	   word.set(sb.toString().trim());
        	   context.write(word, one);
        	   break;
           }
           sb.append(tokenizer.nextToken()).append(" ");
	   }
	   
	 }
	 
}