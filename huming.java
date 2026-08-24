import java.util.*;

public class huming {

    public void check() {

        Scanner sc = new Scanner(System.in);

        System.out.print("ENTER THE FIRST HAMMING CODE STREAM: ");
        String a = sc.next();

        System.out.print("ENTER THE SECOND HAMMING CODE STREAM: ");
        String b = sc.next();

        int count = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
            }
        }

        System.out.println("Number of different bits: " + count);

        sc.close();
    }

    public static void main(String[] args) {
        huming h = new huming();
        h.check();
    }
}
