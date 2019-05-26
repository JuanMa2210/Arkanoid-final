package app;

public class Main {
    public static void main(String[] args) throws Exception {
        Menu menu=new Menu();
        menu.run(1.0 / 60.0);
        System.exit(0);
    }
}