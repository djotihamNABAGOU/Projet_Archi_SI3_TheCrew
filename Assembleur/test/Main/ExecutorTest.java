package Main;

import Parser.Parser;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExecutorTest {

    @Test
    public void executeInstructions() {
        Parser parser = new Parser("resources/input/testMax.txt");
        parser.parseTheFile();
        Executor executor = new Executor(parser.getParsingResults());
        executor.executeInstructions();
        String results = executor.getExecutingResults();
        System.out.println(results);
        String expected = "9800\n" +
                "9901\n" +
                "4288\n" +
                "d406\n" +
                "9002\n" +
                "de07\n" +
                "9102\n";
        assertEquals(expected,results);
    }
}