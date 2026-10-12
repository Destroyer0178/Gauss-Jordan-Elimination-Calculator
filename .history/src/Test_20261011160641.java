package src;
public class Test {

    public static void main(String[] args) {
        int total = 0; 
        for (int i = 1; i <= 3; i++) {
            for (int j = i; j <= 3; j++) {
                total += j; 
            }
        }
        System.out.println(total);
    }
}