package Parser;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ParserTest {

    @org.junit.Test
    public void parseTheFile() {
        Parser parser = new Parser("resources/input/testMax.txt");
        parser.parseTheFile();
        String excepted = "LDR R0 0 \n" +
                        "LDR R1 1 \n" +
                        "CMP R0 R1 \n" +
                        "BMI 6 \n" +
                        "STR R0 2 \n" +
                        "B 7 \n" +
                        "STR R1 2";
        ArrayList<String[]> results = parser.getParsingResults();
        System.out.println(results.get(0)[0]);
        assertTrue("bad parsing at line 0",results.get(0)[0].equals("LDR") && results.get(0)[1].equals("R0") && results.get(0)[2].equals("0"));
        assertTrue("bad parsing at line 1",results.get(1)[0].equals("LDR") && results.get(1)[1].equals("R1") && results.get(1)[2].equals("1"));
        assertTrue("bad parsing at line 2",results.get(2)[0].equals("CMP") && results.get(2)[1].equals("R0") && results.get(2)[2].equals("R1"));
        assertTrue("bad parsing at line 3",results.get(3)[0].equals("BMI") && results.get(3)[1].equals("6"));
        assertTrue("bad parsing at line 4",results.get(4)[0].equals("STR") && results.get(4)[1].equals("R0") && results.get(4)[2].equals("2"));
        assertTrue("bad parsing at line 5",results.get(5)[0].equals("B") && results.get(5)[1].equals("7"));
        assertTrue("bad parsing at line 6",results.get(6)[0].equals("STR") && results.get(6)[1].equals("R1") && results.get(6)[2].equals("2"));
    }
}