import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;
import java.util.*;

public class TGMapper extends Mapper<Object, Text, Text, LongWritable> {
	 
	 private final static LongWritable one = new LongWritable(1);
	 private Text word1 = new Text();
	 private Text word2 = new Text();
	 private Text word3 = new Text();
	 
	 @Override
	 public void map(Object key, Text value, Context context) throws IOException,
     InterruptedException {
		 
	   String line = value.toString();
	   StringTokenizer tokenizer = new StringTokenizer(line);
	   
	   StringBuilder sb1 = new StringBuilder();  
	   StringBuilder sb2 = new StringBuilder();
	   StringBuilder sb3 = new StringBuilder();
	   
	   int words = 0;
	   String nextString = new String();
	   while (tokenizer.hasMoreTokens()) {
		   
		   if(words == 0) {
			   sb1.append(tokenizer.nextToken()).append(" ");
		   }
		   if(words == 1) {
			   nextString = tokenizer.nextToken(); 
			   sb1.append(nextString).append(" ");
			   word1.set(sb1.toString().trim());
        	   context.write(word1, one);
			   sb2.append(nextString).append(" ");
		   }
		   if(words == 2) {
			   nextString = tokenizer.nextToken();
			   sb2.append(nextString).append(" ");
			   word2.set(sb2.toString().trim());
        	   context.write(word2, one);
			   sb3.append(nextString).append(" ");
		   }
		   if(words == 3) {
			   nextString = tokenizer.nextToken();
			   sb3.append(nextString).append(" ");
			   word3.set(sb3.toString().trim());
        	   context.write(word3, one);
        	   break;
		   }
		   words++;
		   
	   }
	   
	 }
	 
}