package battle;

import droids.Droid;
import java.util.Random;

public class Battle {
    public String fightResult;

    public String fight1vs1(Droid droid1, Droid droid2) {
        fightResult = "";
        fightResult += "1vs1 fight between " + droid1.getName() + " and " + droid2.getName() + "\n";
        System.out.println("1vs1 fight between " + droid1.getName() + " and " + droid2.getName());
        int i = 1;
        while (droid1.isAlive() && droid2.isAlive()) {
            fightResult += "----------Round " + i + "----------\n";
            System.out.println("----------Round " + i + "----------");
            droneAttackDrone(droid1, droid2);
            i++;
        }
        return fightResult;
    }

    public String TeamFight(Droid[] team1, Droid[] team2) {
        fightResult = "";
        int round = 1;
        while(isTeamAlive(team1) && isTeamAlive(team2)) {
            fightResult += "----------Round " + round + "----------\n";
            System.out.println("----------Round " + round + "----------");

            int smallerTeamLength = team1.length == team2.length ? team1.length : (team1.length < team2.length ? team1.length : team2.length);

            for(int i = 0; i < smallerTeamLength && isTeamAlive(team1) && isTeamAlive(team2); i++) {
                Droid team1Droid = chooseRandomAliveDroid(team1);
                Droid team2Droid = chooseRandomAliveDroid(team2);

                droneAttackDrone(team1Droid, team2Droid);

//                if(isTeamAlive(team2) && isTeamAlive(team1)) {
//
//                    team1Droid = chooseRandomAliveDroid(team1);
//                    team2Droid = chooseRandomAliveDroid(team2);
//
//                    droneAttackDrone(team2Droid, team1Droid);
//                }
            }
            fightResult += "\nTeam1 Health:\n";
            System.out.println("\nTeam1 Health:\n");
            printTeamStats(team1);
            fightResult += "\nTeam2 Health:\n";
            System.out.println("\nTeam2 Health:\n");
            printTeamStats(team2);
            round++;
        }
        return fightResult;
    }

    public void droneAttackDrone(Droid droid1, Droid droid2) {
        fightResult += "\tHealth: " + droid1.getName() + "[" + droid1.getHealth() + "], " + droid2.getName() + "[" + droid2.getHealth() + "]\n";
        System.out.println("\tHealth: " + droid1.getName() + "[" + droid1.getHealth() + "], " + droid2.getName() + "[" + droid2.getHealth() + "]");

        droid1.attack(droid2);
        fightResult += droid1.getName() + " hits " + droid2.getName() + " by " + droid1.getDamage() + "\n";
        droid1.useSkill();
        fightResult += droid1.getName() + "Uses skill: " + droid1.getSkill() + "\n";
        System.out.println(droid1.getName() + "Uses skill: " + droid1.getSkill() + "\n");
        if(!droid2.isAlive()) {
            printKillCase(droid1, droid2);
        }
        if(droid2.isAlive()) {
            droid2.attack(droid1);
            fightResult += droid2.getName() + " hits " + droid1.getName() + " by " + droid2.getDamage() + "\n";
            droid2.useSkill();
            fightResult += droid2.getName() + "Uses skill: " + droid2.getSkill() + "\n";
            System.out.println(droid2.getName() + "Uses skill: " + droid2.getSkill() + "\n");
            if(!droid1.isAlive()) {
                printKillCase(droid2, droid1);
            }
        }
    }

    public void printKillCase(Droid droid1, Droid droid2) {
        fightResult += "---------------\n";
        fightResult += "\tHealth: " + droid1.getName() + "[" + droid1.getHealth() + "], " + droid2.getName() + "[" + droid2.getHealth() + "]\n";
        fightResult += droid1.getName() + " kills " + droid2.getName() + "\n";
        fightResult += "---------------\n";

        System.out.println("---------------");
        System.out.println("\tHealth: " + droid1.getName() + "[" + droid1.getHealth() + "], " + droid2.getName() + "[" + droid2.getHealth() + "]");
        System.out.println(droid1.getName() + " kills " + droid2.getName());
        System.out.println("---------------");
    }

    public boolean isTeamAlive(Droid[] team){
        boolean result = false;
        for(Droid item : team) {
            if(item.isAlive()) {
                result = true;
            }
        }
        return result;
    }

    public static Droid chooseRandomAliveDroid(Droid[] team) {
        Random rand = new Random();

        int randomIndex = rand.nextInt(team.length);
        while (!team[randomIndex].isAlive()) {
            randomIndex = rand.nextInt(team.length);
        }
        return team[randomIndex];
    }

    public void printTeamStats(Droid[] team){
        for(Droid item : team) {
            fightResult += item.getName() + "[" + item.getHealth() + "]\n";
            System.out.println(item.getName() + "[" + item.getHealth() + "]");
        }
    }
}
