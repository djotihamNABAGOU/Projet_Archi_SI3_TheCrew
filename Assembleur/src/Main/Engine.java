package Main;

import Parser.*;

public class Engine {
    private String inputPath;
    private String outputPath;
    private String instructions;
    private String results;

    public String getInputPath() {
        return inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public Engine(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
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
        //Writing
        Writer writer = new Writer(outputPath);
        writer.write();
    }

}
