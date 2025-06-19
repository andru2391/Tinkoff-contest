import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        class Process{
            public Process(){
                this.dependentOnProcessesNumber = new ArrayList<>();
                this.executed = false;
                processCount++;
                this.processNumber = processCount;
                allProcesses.add(this);

            }
            private static ArrayList<Process> allProcesses = new ArrayList<>();
            private static int processCount = 0;
            private int processNumber;
            private int finishTime;
            private int executionTime;
            private boolean executed;
            private static int processesExecuted = 0;
            private ArrayList<Integer> dependentOnProcessesNumber;
            private static int biggestFinishTime = 0;

            private void biggest(int time){
                biggestFinishTime = Integer.max(time, biggestFinishTime);
            }



            private void execute(){
                if(this.isExecuted()) {
                    return;
                }
                if (this.getDependentOnProcessesNumber().isEmpty()) {
                    this.setExecuted(true);
                    this.setFinishTime(this.getExecutionTime());
                    biggest(this.finishTime);
                    return;
                }
                int biggestFinishTimeLocal = 0;

                for (int i = 0; i < this.dependentOnProcessesNumber.size(); i++) {

                    if (!allProcesses.get(this.dependentOnProcessesNumber.get(i) - 1).isExecuted()) {
                        return;
                    } else {

                        if (allProcesses.get(this.dependentOnProcessesNumber.get(i) - 1).getFinishTime() > biggestFinishTimeLocal) {
                            biggestFinishTimeLocal = allProcesses.get(this.dependentOnProcessesNumber.get(i) - 1).getFinishTime();
                        }

                    }


                }

                this.setFinishTime(biggestFinishTimeLocal + this.executionTime);
                biggest(this.getFinishTime());
                this.setExecuted(true);
            }

            public static int getBiggestFinishTime() {
                return biggestFinishTime;
            }


            public int getFinishTime() {
                return finishTime;
            }

            public void setFinishTime(int finishTime) {
                this.finishTime = finishTime;
            }

            public static ArrayList<Process> getAllProcesses() {
                return allProcesses;
            }

            public static int getProcessCount() {
                return processCount;
            }

            public static int getProcessesExecuted() {
                return processesExecuted;
            }

            public int getProcessNumber() {
                return processNumber;
            }

            public int getExecutionTime() {
                return executionTime;
            }

            public void setExecutionTime(int executionTime) {
                this.executionTime = executionTime;
            }

            public boolean isExecuted() {
                return executed;
            }

            public void setExecuted(boolean executed) {
                processesExecuted++;
                this.executed = executed;
            }

            public ArrayList<Integer> getDependentOnProcessesNumber() {
                return dependentOnProcessesNumber;
            }

            public void setDependentOnProcessesNumber(ArrayList<Integer> dependentOnProcesses) {
                this.dependentOnProcessesNumber = dependentOnProcesses;
            }
        }


        Scanner scanner = new Scanner(System.in);
        int threadCount = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < threadCount; i ++) {
            String[] input = scanner.nextLine().split(" ");
            Process process = new Process();
            process.setExecutionTime(Integer.parseInt(input[0]));
            ArrayList<Integer> dependentOnProcesses = new ArrayList<>();
            for (int j = 1; j < input.length; j++ ) {
                dependentOnProcesses.add(Integer.parseInt(input[j]));
            }
            process.setDependentOnProcessesNumber(dependentOnProcesses);
        }
        while(Process.getProcessesExecuted() < Process.getProcessCount()) {
            for (Process process: Process.getAllProcesses()) {
                process.execute();
            }
        }


        System.out.println(Process.getBiggestFinishTime());


    }
}