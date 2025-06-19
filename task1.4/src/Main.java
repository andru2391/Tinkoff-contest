import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numbs[] = scanner.nextLine().split(" ");
        long a = Long.parseLong(numbs[0]);
        long b = Long.parseLong(numbs[1]);
        long summaryCount = 0;
        long eachCount = 0;
        long innerCount = 0;
        for (long i = a; i <= b; i++ ) {

            for (long j = 1; j <= i; j++) {
                if(i % j == 0) {
                    eachCount++;
                }
            }
            for (long k = 1; k <= eachCount; k++){
                if(eachCount % k == 0) {
                    innerCount++;
                }
            }
            if (eachCount > 2 && innerCount <= 2) {
                summaryCount++;
            }
            eachCount = 0;
            innerCount = 0;

        }
        System.out.println(summaryCount);
    }
}
