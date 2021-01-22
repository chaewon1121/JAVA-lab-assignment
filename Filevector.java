
import java.util.Vector ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Filevector {

   public static void main(String[] args) throws FileNotFoundException,IOException {
      Vector<String> word = new Vector<String>(30);
      InputStream fis = new FileInputStream("input.txt");
      InputStreamReader isr = new InputStreamReader(fis);
      BufferedReader br = new BufferedReader(isr);
      FileOutputStream fos = new
            FileOutputStream("output.txt");
      OutputStreamWriter osw = new OutputStreamWriter(fos);
      BufferedWriter bw = new BufferedWriter(osw);
      String tmpstring = new String();
      while((tmpstring=br.readLine())!=null)  
      {  
         word.add(tmpstring);
      }
      
      for(int i=0; i<word.size();i++) {
         bw.write((i+1)+" : "+word.get(i));
         bw.newLine();
         bw.flush();
      }
      br.close();
      bw.close();
   }

}