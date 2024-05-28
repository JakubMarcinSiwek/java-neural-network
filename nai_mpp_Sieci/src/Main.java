import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String path="C:\\Users\\Kuba\\Desktop\\nai_mpp_Sieci\\Languages";
        int epochs=10000;
        double a=0.01;


        ArrayList<Row> rows = Helper.getInfo(path);
        ArrayList<Perceptron> perceptrons = new ArrayList<>();
        rows.stream().map(row -> row.atrybutDecyzyjny).distinct().forEach(b -> {
            Perceptron perceptron = Perceptron.train(rows,epochs,a, b, 26);
            perceptrons.add(perceptron);
        });

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        double[] tab = Helper.getCharRatio(input);

        for (Perceptron perceptron : perceptrons) {
            System.out.println(perceptron.nazwa+" "+perceptron.obliczaczv2(tab));

        }






    }
}
