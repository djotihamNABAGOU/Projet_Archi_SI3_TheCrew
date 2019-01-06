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

    public void parseTheFile() {
        ArrayList<String> data = new ArrayList<>();
        boolean dataFound = false;
        boolean textFound = false;
        int index = 0;
        int ifIndex = -1, elseIndex = -1, endifIndex = -1;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                //line = line.replaceAll(" ", "");
                if (!line.equals("")) {//ligne vide
                    if (!line.substring(0, 1).equals("@")) {
                        //collecte des données
                        if (line.equals(".data")) dataFound = true;
                        else if (line.equals(".end") && dataFound == true) dataFound = false;
                        else if (dataFound == true) {
                            line = line.replaceAll(" ", "");
                            data.add(line.split(":")[0]);
                        }
                        //collecte des instructions
                        if (line.equals(".text")) textFound = true;
                        else if (line.equals(".end") && textFound == true) textFound = false;
                        else if (textFound == true) {
                            //On supprime les éventuels espaces devant
                            int i = 0;
                            while (line.charAt(i) == ' ') i++;
                            line = line.substring(i);
                            String temp[] = line.split(" |,|:");
                            if (temp[0].equals("if")) ifIndex = index;
                            else if (temp[0].equals("else")) elseIndex = index;
                            else if (temp[0].equals("endif")) endifIndex = index;
                            else {
                                //System.out.println(line); on ajoute seulement les lignes d'instruction
                                parsingResults.add(temp);
                                index++;
                            }
                        }
                    }
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Printing
        /*for (String[] line : parsingResults) {
            for (String s : line) {
                System.out.print(s + " ");
            }
            System.out.println();
        }*/
        //Remplacement des variables par leurs valeurs
        for (String[] line : parsingResults) {
            for (int i = 1; i<line.length; i++) {
                //variables de conditions
                if (line[i].equals("if")){
                    line[i] = ""+ifIndex;
                    break;
                }
                if (line[i].equals("else")){
                    line[i] = ""+elseIndex;
                    break;
                }
                if (line[i].equals("endif")){
                    line[i] = ""+endifIndex;
                    break;
                }
                //data
                for (int j = 0; j < data.size(); j++) {
                    if (line[i].equals(data.get(j))) line[i] = ""+j;
                }
            }
        }
        //Printing
        for (String[] line : parsingResults) {
            for (String s : line) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

    public ArrayList<String[]> getParsingResults() {
        return parsingResults;
    }
}
