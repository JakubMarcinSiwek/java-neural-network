import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class Helper {

    private static final char[] letters="abcdefghijklmnopqrstuvwxyz".toCharArray();
    public static double[] getCharRatio(String string){
        double[] arr=new double[letters.length];
        int counter=0;
        for (char c : string.toLowerCase().toCharArray()) {
            for (int i = 0; i < letters.length; i++) {
                if(c == letters[i]){
                    arr[i]++;
                    counter++;
                    break;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i]=arr[i]/counter;
        }

        return arr;
    }
    public static ArrayList<Row> getInfo(String path) throws IOException {
        ArrayList<Row> rows = new ArrayList<Row>();
        FileVisitor<Path> odwiedzacz = new FileVisitor<Path>(){

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                if(file.getFileName().toString().endsWith(".txt")){
                    String content = Files.readString(file);
                    String language = file.getParent().toFile().getName().toString();
                    double[] arr=getCharRatio(content);
                    Row row = new Row(arr,language);
                    rows.add(row);

                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        };
        Files.walkFileTree(Paths.get(path),odwiedzacz);
        System.out.println(rows.get(0));
        return rows;

    }

    public static void main(String[] args) throws IOException {
        //getInfo("C:\\Users\\Kuba\\Desktop\\nai_mpp_Sieci\\Languages");

    }


}
