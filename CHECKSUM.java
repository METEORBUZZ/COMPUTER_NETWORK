import java.util.*;

public class CHECKSUM {

    public static void main(String args[]) {

        Scanner my_scan = new Scanner(System.in);

        // Sender
        System.out.println("Enter the input string:");
        String my_in = my_scan.nextLine();

        int my_checksum = generate_checksum(my_in);

        System.out.println("The checksum that has been generated is "
                + String.format("%04X", my_checksum));

        // Receiver
        System.out.println("\nEnter the data that needs to be sent to the receiver:");
        String receiver_data = my_scan.nextLine();

        System.out.println("Enter the checksum that needs to be sent to the receiver:");
        String checksum_input = my_scan.nextLine();

        int receiver_checksum;

        try {
            receiver_checksum = Integer.parseInt(checksum_input.trim(), 16);
        } catch (NumberFormatException e) {
            System.out.println("Invalid checksum!");
            System.out.println("Please enter checksum in hexadecimal format, e.g. 2ABF");
            my_scan.close();
            return;
        }

        receive(receiver_data, receiver_checksum);

        my_scan.close();
    }


    // Generate checksum
    static int generate_checksum(String s) {

        int my_checksum = 0;
        int i = 0;

        while (i < s.length()) {

            int first = s.charAt(i);
            int second = 0;

            if (i + 1 < s.length()) {
                second = s.charAt(i + 1);
            }

            int value = (first << 8) | second;

            if (i + 1 < s.length()) {
                System.out.printf("%c%c : %04X%n",
                        s.charAt(i),
                        s.charAt(i + 1),
                        value);
            } else {
                System.out.printf("%c : %04X%n",
                        s.charAt(i),
                        value);
            }

            my_checksum += value;

            // Wrap around carry
            while (my_checksum > 0xFFFF) {
                my_checksum = (my_checksum & 0xFFFF)
                        + (my_checksum >>> 16);
            }

            i += 2;
        }

        // One's complement
        my_checksum = generate_complement(my_checksum);

        return my_checksum;
    }


    // Receiver
    static void receive(String s, int received_checksum) {

        int generated_checksum = generate_checksum(s);

        // Convert checksum back to original sum
        generated_checksum = generate_complement(generated_checksum);

        int total = generated_checksum + received_checksum;

        // Wrap around carry
        while (total > 0xFFFF) {
            total = (total & 0xFFFF) + (total >>> 16);
        }

        int syndrome = generate_complement(total);

        System.out.println("\nThe value of syndrome is "
                + String.format("%04X", syndrome));

        if (syndrome == 0) {
            System.out.println("Data has been received without any errors");
        } else {
            System.out.println("An error was encountered in the received data");
        }
    }


    // One's complement
    static int generate_complement(int value) {
        return 0xFFFF - value;
    }
}