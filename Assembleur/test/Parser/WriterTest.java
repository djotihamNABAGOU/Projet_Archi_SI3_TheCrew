package Parser;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class WriterTest {

    @Test
    public void write() {
        Writer writer = new Writer("resources/output/outTestMax.txt");
        writer.write("9800\n" +
                "9901\n" +
                "4288\n" +
                "d406\n" +
                "9002\n" +
                "de07\n" +
                "9102");
        String results = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("resources/output/outTestMax.txt")));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                results += line+"\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String excepted = "v2.0 raw\n" +
                "9800\n" +
                "9901\n" +
                "4288\n" +
                "d406\n" +
                "9002\n" +
                "de07\n" +
                "9102\n";
        assertEquals(excepted,results);
    }
}