import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class cereal {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] macro = new int[10];
        ArrayList<String> cerealNames = new ArrayList<>();
        System.out.println("Enter the type of macro (calories, protein, carbohydrates, fat):");
        String type = scan.nextLine().toLowerCase();

        Scanner fileScanner = null;

        try {
            fileScanner = new Scanner(new File("cereals.txt"));
            int count = 0;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                cerealNames.add(parts[0]);

                if (type.equals("calories")) {
                    macro[count] = Integer.parseInt(parts[2]);
                } else if (type.equals("protein")) {
                    macro[count] = Integer.parseInt(parts[3]);
                } else if (type.equals("carbohydrates") || type.equals("carbs")) {
                    macro[count] = Integer.parseInt(parts[4]);
                } else if (type.equals("fat")) {
                    macro[count] = Integer.parseInt(parts[5]);
                } else {
                    System.out.println("Invalid type entered.");
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: cereals.txt");
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
        }

        scan.close();

        for (int i = 0; i < macro.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < macro.length; j++) {
                if (macro[j] < macro[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = macro[i];
                macro[i] = macro[minIndex];
                macro[minIndex] = temp;

                String tempName = cerealNames.get(i);
                cerealNames.set(i, cerealNames.get(minIndex));
                cerealNames.set(minIndex, tempName);
            }
        }
        
        System.out.println("Three smallest values:");
        for (int i = 0; i < 3; i++) {
            System.out.println(cerealNames.get(i) + ": " + macro[i]);
        }

        System.out.println("Three largest values:");
        for (int i = macro.length - 1; i >= macro.length - 3; i--) {
            System.out.println(cerealNames.get(i) + ": " + macro[i]);
        }
    }
}
