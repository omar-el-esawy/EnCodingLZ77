import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        int search_window, look_ahead;
        String s;
        try {
            Scanner sc = new Scanner(new FileInputStream("input.txt"));
            search_window = sc.nextInt();
            look_ahead = sc.nextInt();
            s = sc.next();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Esawy\\IdeaProjects\\TextJava\\input.txt"));

            for (int i = 0; i < s.length(); ) {
                String temp = "";
                temp += s.charAt(i);
                int counter = 0;//will help us not exceeding the look ahead buffer
                int ii;
                boolean found = true;
                int first_char = -1;
                while (found) {
                    found = false;
                    for (int j = Math.max(0, i - search_window); j < i; j++) { //to go through the search window
                        String substring = "";
                        for (int k = j; k < j + temp.length(); k++) {
                            if (k == i) break;
                            substring += s.charAt(k);
                        }
                        if (temp.equals(substring)) {
                            first_char = j;
                            found = true;
                        }
                    }
                    if (found) {
                        counter++;
                        if (counter + 1 == look_ahead) {
                            found = false;
                            continue;
                        }
                        temp += s.charAt(i + counter);
                    } else {
                        counter++;
                        i += counter;
                    }
                }
                if (first_char == -1) {
//                    System.out.println("0 0 " + s.charAt(i - 1));
                    bw.write("0 0 " + s.charAt(i - 1) + "\n");
                } else {
                    int go_back = i - (temp.length() - 1);
                    go_back -= first_char;
//                    System.out.println((go_back - 1) + " " + (temp.length() - 1) + " " + temp.charAt(temp.length() - 1));
                    bw.write((go_back - 1) + " " + (temp.length() - 1) + " " + temp.charAt(temp.length() - 1) + "\n");
                }
            }
            bw.close();
        } catch (IOException e) {
            return;
        }


    }
}