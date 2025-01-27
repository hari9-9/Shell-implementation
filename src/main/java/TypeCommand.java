import java.util.Objects;

public class TypeCommand {
    public void printType(String type){
        if (Objects.equals(type,"echo")){
            System.out.println(type + " is a shell builtin");
        } else if (Objects.equals(type,"exit")) {
            System.out.println(type + " is a shell builtin");
        }
        else if (Objects.equals(type,"type")) {
            System.out.println(type + " is a shell builtin");
        }
        else {
            System.out.println(type + ": not found");
        }
    }
}
