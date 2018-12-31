package Main;

import java.util.ArrayList;

public class Executor {
    private ArrayList<String[]> instructions;
    private String executingResults;

    public Executor(ArrayList<String[]> instructions) {
        this.instructions = instructions;
        executingResults = "";
    }

    public String getExecutingResults() {
        return executingResults;
    }

    public void executeInstructions() {
        for (String[] line : instructions) {
            switch (line[0]) {
                //#################### SHIFT ADD SUB MOV ####################//
                case "LSLS":
                    if (line.length == 4)
                        lslS(line);
                    else //Data Processing
                        lslDP(line);
                    break;
                case "LSRS":
                    if (line.length == 4)
                        lsrS(line);
                    else //Data Processing
                        lsrDP(line);
                    break;
                case "ASRS":
                    if (line.length == 4)
                        asrS(line);
                    else //Data Processing
                        asrDP(line);
                    break;
                case "ADDS":
                    if (line.length == 4 && line[3].contains("#"))
                        addS2(line);
                    else
                        addS1(line);
                    break;
                case "SUBS":
                    if (line.length == 4 && line[3].contains("#"))
                        subS2(line);
                    else
                        subS1(line);
                    break;
                case "MOVS":
                    mov(line);
                    break;
                //#################### DATA PROCESSING ####################//
                case "ANDS":
                    and(line);
                    break;
                case "EORS":
                    eor(line);
                    break;
                case "ADCS":
                    adc(line);
                    break;
                case "SBCS":
                    sbc(line);
                    break;
                case "RORS":
                    ror(line);
                    break;
                case "TST":
                    tst(line);
                    break;
                case "RSBS":
                    rsb(line);
                    break;
                case "CMP":
                    cmp(line);
                    break;
                case "CMN":
                    cmn(line);
                    break;
                case "ORRS":
                    orr(line);
                    break;
                case "MULS":
                    mul(line);
                    break;
                case "BICS":
                    bic(line);
                    break;
                case "MVNS":
                    mvn(line);
                    break;
                //#################### LOAD/STORE ####################//
                case "STR":
                    str(line);
                    break;
                case "LDR":
                    ldr(line);
                    break;
                //#################### STACK POINTER ####################//
                case "ADD":
                    addSP(line);
                    break;
                case "SUB":
                    subSP(line);
                    break;
            }
        }
    }

    private String binaryConversion(String toConvert, int length){
        toConvert = Integer.toBinaryString(Integer.parseInt(toConvert));
        for(int n = toConvert.length(); n < length; n++) {
            toConvert = "0" + toConvert;
        }
        return toConvert;
    }

    private String inBinaryS1(String[] instructions, String binaire){
        String registre1 = binaryConversion(instructions[1].replace("R", ""), 3);
        String registre2 = binaryConversion(instructions[2].replace("R", ""), 3);
        String immediat = binaryConversion(instructions[3].replace("#", ""), 5);
        binaire += immediat + registre2 + registre1;
        return binaire;
    }

    private String inBinaryS2(String[] instructions, String binaire){
        String registre1 = binaryConversion(instructions[1].replace("R", ""), 3);
        String registre2 = binaryConversion(instructions[2].replace("R", ""), 3);
        String registre3 = binaryConversion(instructions[3].replace("R", ""), 3);
        binaire += registre3 + registre2 + registre1;
        return binaire;
    }

    private String inBinaryS3(String[] instructions, String binaire){
        String registre1 = binaryConversion(instructions[1].replace("R", ""), 3);
        String registre2 = binaryConversion(instructions[2].replace("R", ""), 3);
        String immediat = binaryConversion(instructions[3].replace("#", ""), 3);
        binaire += immediat + registre2 + registre1;
        return binaire;
    }

    private String inBinaryDP(String[] instructions, String binaire){
        String registre1 = binaryConversion(instructions[1].replace("R", ""), 3);
        String registre2 = binaryConversion(instructions[2].replace("R", ""), 3);
        binaire += registre2 + registre1;
        return binaire;
    }

    private String inBinaryLS(String[] instructions, String binaire){
        String registre1 = binaryConversion(instructions[1].replace("R", ""), 3);
        String immediat = binaryConversion(instructions[2].replace("#", ""), 8);
        binaire += registre1 + immediat;
        return binaire;
    }

    private String inBinarySP(String[] instructions, String binaire){
        String immediat = binaryConversion(instructions[2].replace("#", ""), 7);
        binaire += immediat;
        return binaire;
    }

    private String inHexa(String binaire){
        String hexa = Integer.toHexString(Integer.parseInt(binaire,2));
        for(int n = hexa.length(); n < 4; n++) {
            hexa = "0" + hexa;
        }
        return hexa;
    }

    //#################### SHIFT ADD SUB MOV ####################//
    private void lslS(String[] instructions){
        String binaire = "00000";
        binaire = inBinaryS1(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void lsrS(String[] instructions){
        String binaire = "00001";
        binaire = inBinaryS1(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void asrS(String[] instructions){
        String binaire = "00010";
        binaire = inBinaryS1(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void addS1(String[] instructions){
        String binaire = "0001100";
        binaire = inBinaryS2(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void subS1(String[] instructions){
        String binaire = "0001101";
        binaire = inBinaryS2(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void addS2(String[] instructions){
        String binaire = "0001110";
        binaire = inBinaryS3(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void subS2(String[] instructions){
        String binaire = "0001111";
        binaire = inBinaryS3(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void mov(String[] instructions){
        String binaire = "00100";
        binaire = inBinaryLS(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }
    //#################### DATA PROCESSING ####################//
    private void and(String[] instructions){
        String binaire = "0100000000";
        binaire = inBinaryDP(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void eor(String[] instructions){
        String binaire = "0100000001";
        binaire = inBinaryDP(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void lslDP(String[] instructions){
        String binaire = "0100000010";
        binaire = inBinaryDP(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void lsrDP(String[] instructions){
        String binaire = "0100000011";
        binaire = inBinaryDP(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void asrDP(String[] instructions){
        String binaire = "0100000100";
        binaire = inBinaryDP(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void adc(String[] instructions){
        String binaire = "0100000101";
        binaire = inBinaryDP(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void sbc(String[] instructions){
        String binaire = "0100000110";
        binaire = inBinaryDP(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void ror(String[] instructions){
        String binaire = "0100000111";
        binaire = inBinaryDP(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void tst(String[] instructions){
        String binaire = "0100001000";
        binaire = inBinaryDP(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void rsb(String[] instructions){
        String binaire = "0100001001";
        binaire = inBinaryDP(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void cmp(String[] instructions){
        String binaire = "0100001010";
        binaire = inBinaryDP(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void cmn(String[] instructions){
        String binaire = "0100001011";
        binaire = inBinaryDP(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void orr(String[] instructions){
        String binaire = "0100001100";
        binaire = inBinaryDP(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void mul(String[] instructions){
        String binaire = "0100001101";
        binaire = inBinaryDP(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void bic(String[] instructions){
        String binaire = "0100001110";
        binaire = inBinaryDP(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void mvn(String[] instructions){
        String binaire = "0100001111";
        binaire = inBinaryDP(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }
    //#################### LOAD/STORE ####################//
    private void str(String[] instructions){
        String binaire = "10010";
        binaire = inBinaryLS(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void ldr(String[] instructions){
        String binaire = "10011";
        binaire = inBinaryLS(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }
    //#################### STACK POINTER ####################//
    private void addSP(String[] instructions){
        String binaire = "101100000";
        binaire = inBinarySP(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }

    private void subSP(String[] instructions){
        String binaire = "101100001";
        binaire = inBinarySP(instructions, binaire);
        executingResults += inHexa(binaire) + " ";
    }
}
