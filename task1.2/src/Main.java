import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        boolean flag = true;
        String a =  scanner.nextLine();
        String[] numbers = a.split(" ");
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            arrayList.add(Integer.parseInt(numbers[i]));
        }
        int current = 0;
        int previous = 0;
        ArrayList<Integer> difference = new ArrayList<>();

        if (arrayList.get(0) == -1){
            arrayList.set(0,1);
        }

        for (int i = 0; i < arrayList.size(); i++) {

            if (arrayList.get(i) == -1){
                arrayList.set(i, arrayList.get(i-1) + 1);
                difference.add(1);
                current = arrayList.get(i);
                previous = arrayList.get(i);
                continue;
            }

            current = arrayList.get(i);
            if (current <= previous) {
                System.out.println("NO");
                flag = false;
                break;
            }
            difference.add(current - previous);
            previous = arrayList.get(i);
        }

        if (flag == true){
            String out = "";
            for (int i = 0; i < difference.size(); i++) {
                if (i == 0) {
                    out = out + difference.get(0);
                } else {
                    out = out + " " + difference.get(i);
                }
            }
            System.out.println("YES");
            System.out.println(out);
        }


    }
}
