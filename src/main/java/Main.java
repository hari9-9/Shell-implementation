import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {


        Scanner scanner = new Scanner(System.in);
        TypeCommand typeCommand = new TypeCommand();
        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            if(Objects.equals(input,"exit 0"))
            {
                return;
            } else if (input.startsWith("echo")) {
                String toPrint = input.substring(5);
                System.out.println(toPrint);
            }
            else if (input.startsWith("type")){
                String toCheck = input.substring(5);
                typeCommand.printType(toCheck);
            }
            else {
                System.out.println(input + ": command not found");
            }
        }

    }
}
