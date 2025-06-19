
import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        class Team {
            Team (LocalTime finish) {
                this.finish = finish;
                this.hackedServersCount = 0;
            }
            private String name;
            private int hackedServersCount;
            private LocalTime finish;

            private int time;
            private int potentialAddedTime = 0;

            public int getPlace() {
                return place;
            }

            public void setPlace(int place) {
                this.place = place;
            }

            private int place;

            public LocalTime getFinish() {
                return finish;
            }

            public void setFinish(LocalTime finish) {
                this.finish = finish;
            }


            public int getPotentialAddedTime() {
                return potentialAddedTime;
            }

            public void setPotentialAddedTime(int potentialAddedTime) {
                this.potentialAddedTime = potentialAddedTime;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getHackedServersCount() {
                return hackedServersCount;
            }

            public void setHackedServersCount(int hackedServersCount) {
                this.hackedServersCount = hackedServersCount;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Team team = (Team) o;
                return Objects.equals(name, team.name);
            }

            @Override
            public String toString() {
                return place + " " + name + " " + hackedServersCount + " " + time;
            }
        }
        Scanner scanner = new Scanner(System.in);

        String inputTime = scanner.nextLine();
        String[] decode = inputTime.split(":");
        LocalTime start = LocalTime.of(Integer.parseInt(decode[0]), Integer.parseInt(decode[1]),Integer.parseInt(decode[2]));
        ArrayList<Team> teams = new ArrayList<>();


        int requestsCount = Integer.parseInt(scanner.nextLine());


        for (int i = 0; i < requestsCount; i ++){

            String input = scanner.nextLine();
            Team team = new Team(start);

            String[] decode2 = input.split("\"");
            team.setName("\"" + decode2[1] + "\"");
            String[] decode3 = decode2[2].split(" ");
            String[] time = decode3[1].split(":");
            LocalTime finish = LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]),Integer.parseInt(time[2]));

            String status = decode3[3];
            switch (status) {
                case "PONG" -> {
                    if(!teams.contains(team)) {
                        teams.add(team);
                    }
                }
                case "DENIED", "FORBIDDEN" -> {
                    if(teams.contains(team)) {
                        teams.get(teams.indexOf(team))
                                .setPotentialAddedTime(teams.get(teams.indexOf(team)).getPotentialAddedTime() + 20);
                    } else {
                        team.setPotentialAddedTime(20);
                        teams.add(team);
                    }

                }
                case "ACCESSED" -> {
                    if(teams.contains(team)) {
                        teams.get(teams.indexOf(team)).setTime(teams.get(teams.indexOf(team)).getPotentialAddedTime() +
                                teams.get(teams.indexOf(team)).getTime());
                        teams.get(teams.indexOf(team)).setPotentialAddedTime(0);
                        teams.get(teams.indexOf(team))
                                .setHackedServersCount(teams.get(teams.indexOf(team)).getHackedServersCount() + 1);
                        teams.get(teams.indexOf(team)).setFinish(finish);

                    } else {
                        teams.add(team);
                        teams.get(teams.indexOf(team))
                                .setHackedServersCount(1);
                        teams.get(teams.indexOf(team)).setFinish(finish);
                    }
                }
            }
        }
        for (Team team : teams) {
            int summaryTime = (team.getFinish().minusHours(start.getHour()).getHour()) * 60 +
                    (team.getFinish().minusMinutes(start.getMinute()).getMinute());
            team.setTime(team.getTime() + summaryTime);
        }


        Comparator<Team> comparator = Comparator.comparing(Team::getHackedServersCount, Comparator.reverseOrder())
                .thenComparing(Team::getTime).thenComparing(Team::getName);

        teams.sort(comparator);
        for (int i = 0; i < teams.size(); i ++) {
            if (i == 0) {
                teams.get(i).setPlace(1);
            }
            if (i > 0 && teams.get(i).getHackedServersCount() == teams.get(i - 1).getHackedServersCount()
            && teams.get(i).getTime() == teams.get(i - 1).getTime()) {
                teams.get(i).setPlace(teams.get(i - 1).getPlace());
            }
            teams.get(i).setPlace(i + 1);
            System.out.println(teams.get(i));
        }
    }
}
