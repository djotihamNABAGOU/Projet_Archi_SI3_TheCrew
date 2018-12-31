package Parser;

import java.io.*;
import java.util.ArrayList;

public class Parser {
    private String path;
    private ArrayList<String[]> parsingResults;

    public Parser(String path) {
        this.path = path;
        this.parsingResults = new ArrayList<>();
    }

    public void parseTheFile(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                parsingResults.add(line.split(" |,"));
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Printing
        for (String[] line: parsingResults) {
            System.out.println(line[0]);
        }
    }

    public ArrayList<String[]> getParsingResults() {
        return parsingResults;
    }
}
