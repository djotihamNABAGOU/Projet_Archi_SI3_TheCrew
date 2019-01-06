package Main;

import Parser.*;

import java.util.ArrayList;

public class Engine {
    private String inputPath;
    private String outputPath;
    private ArrayList<String[]> instructions;
    private String results;

    public Engine(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.instructions = new ArrayList<>();
        results = "";
    }

    void run(){
        //Parsing
        Parser parser = new Parser(inputPath);
        parser.parseTheFile();
        instructions = parser.getParsingResults();
        //Executing
        Executor executor = new Executor(instructions);
        executor.executeInstructions();
        results = executor.getExecutingResults();
        System.out.println("\n***Results***\n"+results);
        //Writing
        Writer writer = new Writer(outputPath);
        writer.write(results);
    }

}
