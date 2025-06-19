import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String pool = scanner.nextLine();
        boolean flag = true;
        String requirements = scanner.nextLine();
        int k = Integer.parseInt(scanner.nextLine());
        ArrayList<String> poolList = new ArrayList<>();
        String password;
        Collections.addAll(poolList, pool.split(""));
        ArrayList<String> requirementsList = new ArrayList<>();
        Collections.addAll(requirementsList, requirements.split(""));
        for (int n = k; n >= requirementsList.size(); n--) {
            for (int i = poolList.size() - 1; i >= n; i--) {
                if (i == poolList.size()) {
                    password = pool.substring(i - (n - 1));
                } else {
                    password = pool.substring(i - (n - 1), i + 1);
                }
                for (int j = 0; j < requirementsList.size(); j++) {
                    if (!password.contains(requirementsList.get(j))) {
                        flag = false;
                        break;
                    } else {
                        flag = true;
                    }
                }
                if (flag) {
                    System.out.println(password);
                    break;
                }
            }
            if (flag) {
                break;
            }

        }
    }
}
