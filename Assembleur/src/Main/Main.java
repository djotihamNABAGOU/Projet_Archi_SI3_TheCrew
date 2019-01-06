package Main;

public class Main {

    public static void main(String[] args) {
        //Engine engine = new Engine("resources/input/input.txt","C://Users/ndaug/Documents/GitHub/Projet_Archi_SI3_TheCrew/Assembleur/resources/output/out.txt");
        Engine engine = new Engine("resources/input/testMax.txt","resources/output/outTestMax.txt");
        engine.run();
    }
}
