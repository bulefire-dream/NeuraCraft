import com.bulefire.neuracraft.ai.yy.YY;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> key = List.of("银影","YY","yy","y","Y","AI","ai","Ai","aI","A","a","I","i","ssss");
        for (String s : key) {
            System.out.print(s + " is  ");
            test(s);
        }
    }

    private static void test(@NotNull String message){
        List<String> key = List.of("银影","YY","yy","y","Y","AI","ai","Ai","aI","A","a","I","i");
        if (key.stream().anyMatch(message::contains)){
            System.out.println("AI");
        }else {
            System.out.println("not AI");
        }
    }
}
