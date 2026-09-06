import java.util.Scanner;
public class CRC {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter data: ");
        String data = sc.next();

        System.out.print("Enter generator: ");
        String generator = sc.next();

        int dataLength = data.length();
        int generatorLength = generator.length();

        // Add zeros to data
        String temp = data;
        for (int i = 0; i < generatorLength - 1; i++) {
            temp += "0";
        }

        char[] dividend = temp.toCharArray();
        char[] divisor = generator.toCharArray();

        // CRC division
        for (int i = 0; i <= dividend.length - generatorLength; i++) {

            if (dividend[i] == '1') {

                for (int j = 0; j < generatorLength; j++) {

                    if (dividend[i + j] == divisor[j]) {
                        dividend[i + j] = '0';
                    } else {
                        dividend[i + j] = '1';
                    }
                }
            }
        }

        // Get remainder
        String remainder = "";

        for (int i = dataLength; i < dividend.length; i++) {
            remainder += dividend[i];
        }

        System.out.println("CRC remainder: " + remainder);

        // Transmitted data
        String transmittedData = data + remainder;

        System.out.println("Transmitted data: " + transmittedData);

        // Receiver
        System.out.print("Enter received data: ");
        String received = sc.next();

        char[] receivedBits = received.toCharArray();

        // CRC checking
        for (int i = 0; i <= receivedBits.length - generatorLength; i++) {

            if (receivedBits[i] == '1') {

                for (int j = 0; j < generatorLength; j++) {

                    if (receivedBits[i + j] == divisor[j]) {
                        receivedBits[i + j] = '0';
                    } else {
                        receivedBits[i + j] = '1';
                    }
                }
            }
        }

        // Check remainder
        boolean error = false;

        for (char bit : receivedBits) {
            if (bit == '1') {
                error = true;
                break;
            }
        }

        if (error) {
            System.out.println("Error detected in received data.");
        } else {
            System.out.println("No error detected.");
        }

        sc.close();
    }
}
