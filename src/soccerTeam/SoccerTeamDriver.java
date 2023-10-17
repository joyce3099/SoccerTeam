package soccerTeam;

import java.time.LocalDate;
import java.util.Arrays;

public class SoccerTeamDriver {

    public static void main(String[] args){

        // create a new team
        teamModel soccerTeam = new teamModel();

        // Add players to the team
        soccerTeam.addPlayer("John","Doe", LocalDate.of(2015, 5, 15), Position.MIDFIELDER, 1);
        soccerTeam.addPlayer("Alice", "Smith", LocalDate.of(2018, 9, 23), Position.DEFENDER, 2);
        soccerTeam.addPlayer("Harry", "Potter", LocalDate.of(2014, 3, 1), Position.DEFENDER,3);
        soccerTeam.addPlayer("Ron", "Weasley", LocalDate.of(2015, 2, 2), Position.GOALIE, 4);
        soccerTeam.addPlayer("Hermione", "Granger", LocalDate.of(2015, 3, 1), Position.MIDFIELDER, 5);
        soccerTeam.addPlayer("Draco", "Malfoy", LocalDate.of(2016, 4, 4), Position.MIDFIELDER, 5);
        soccerTeam.addPlayer("James", "Price", LocalDate.of(2015, 5, 21), Position.FORWARD, 2);
        soccerTeam.addPlayer("Lily", "Allen", LocalDate.of(2016, 7, 6), Position.FORWARD, 3);
        soccerTeam.addPlayer("Michael", "Jackson", LocalDate.of(2015, 3, 2), Position.GOALIE, 4);
        soccerTeam.addPlayer("Robert", "King", LocalDate.of(2013, 12, 31), Position.DEFENDER, 5);
        soccerTeam.addPlayer("Jennifer", "Lopez", LocalDate.of(2016, 3, 31), Position.MIDFIELDER, 1);
        soccerTeam.addPlayer("Katy", "Perry", LocalDate.of(2016, 2, 1), Position.FORWARD, 2);

        // Create team and assure team size between 10 and 20
        soccerTeam.reconstructTeamSize();

        // Select the starting lineup
        soccerTeam.selectStartingLineup();

        // Assign positions to the starting lineup
        soccerTeam.assignPosition();

        // Display the list of all players in the team
        String allPlayersList = soccerTeam.getAllPlayersList();
        System.out.println("All Players in the Team:");
        System.out.println(allPlayersList);

        // Display the list of players in the starting lineup
        String startingLineupList = soccerTeam.getStartingLineupList();
        System.out.println("All Players in starting Lineup team:");
        System.out.println(startingLineupList);

    }
}
