import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String n = scanner.nextLine();
        ArrayList<Integer> arrayList = new ArrayList<>();
        String[] array = n.split(",");
        for (int i = 0; i < array.length; i++) {
            String[] part = array[i].split("-");
            if (part.length == 2){
                for (int j = Integer.parseInt(part[0]); j <= Integer.parseInt(part[1]); j++) {
                    arrayList.add(j);
                }
            } else if (part.length == 1) {
                arrayList.add(Integer.parseInt(part[0]));
            }
        }
        arrayList.sort(Comparator.naturalOrder());
        String out = "";
        for(int i = 0; i < arrayList.size(); i ++) {
            if (i == 0) {
                out = out + arrayList.get(i);
            } else {
                out = out + " " + arrayList.get(i);
            }
        }
        System.out.println(out);
    }
}
