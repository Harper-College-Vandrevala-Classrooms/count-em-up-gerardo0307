import java.util.Scanner;

public class GroceryCounter {
    private int counter;
    private int overflowCount;

    
    public GroceryCounter() {
        this.counter = 0;
        this.overflowCount = 0;
    }

    
    public void incrementTens(int times) {
        incrementCounter(10 * times);
    }

    
    public void incrementOnes(int times) {
        incrementCounter(1 * times);
    }

    
    public void incrementTenths(int times) {
        incrementCounter(0.1 * times);
    }

    
    public void incrementHundreths(int times) {
        incrementCounter(0.01 * times);
    }

    
    public String total() {
        int dollars = counter / 100;
        int cents = counter % 100;
        return String.format("$%02d.%02d", dollars, cents);
    }

    
    public int number_of_overflows() {
        return overflowCount;
    }

    
    public void clear() {
        this.counter = 0;
        this.overflowCount = 0;
    }

    
    private void incrementCounter(double increment) {
        int incrementInCents = (int)(increment * 100);
        counter += incrementInCents;
        if (counter > 9999) {
            counter = counter % 10000;
            overflowCount++;
        }
    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GroceryCounter counter = new GroceryCounter();
        String command;

        System.out.println("Thank you for using the Grocery Counter");
        System.out.println("You can use the following commands:");
        System.out.println("  - tens(number) ");
        System.out.println("  - ones (number) ");
        System.out.println("  - tenths (number) ");
        System.out.println("  - hundreths (number) ");
        System.out.println("  - total : Display the current total amount");
        System.out.println("  - overflows : Display the number of overflows");
        System.out.println("  - clear : Reset the counter and overflow count");
        System.out.println("  - exit : Exit the program");

        while (true) {
            System.out.print("\nEnter command: ");
            command = scanner.nextLine().trim().toLowerCase();

            String[] parts = command.split(" ");
            String action = parts[0];
            int number = (parts.length > 1) ? parseIntSafely(parts[1]) : 1;

            switch (action) {
                case "tens":
                    counter.incrementTens(number);
                    System.out.println("Tens digit incremented by " + number + ".");
                    break;
                case "ones":
                    counter.incrementOnes(number);
                    System.out.println("Ones digit incremented by " + number + ".");
                    break;
                case "tenths":
                    counter.incrementTenths(number);
                    System.out.println("Tenths digit incremented by " + number + ".");
                    break;
                case "hundreths":
                    counter.incrementHundreths(number);
                    System.out.println("Hundredths digit incremented by " + number + ".");
                    break;
                case "total":
                    System.out.println("Current total: " + counter.total());
                    break;
                case "overflows":
                    System.out.println("Number of Overflows: " + counter.number_of_overflows());
                    break;
                case "clear":
                    counter.clear();
                    System.out.println("Counter has been reset.");
                    break;
                case "exit":
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command. Please enter a valid command.");
            }
        }
    }

    
    private static int parseIntSafely(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Using default value of 1.");
            return 1;
        }
    }
}
