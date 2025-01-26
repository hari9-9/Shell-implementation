import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {


        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            if(Objects.equals(input,"exit 0"))
            {
                return;
            }
            System.out.println(input + ": command not found");
        }

    }
}
