package soccerTeam;

import org.junit.Test;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class SoccerTeamTest {

    private teamModel team = new teamModel();

    /**
     * Test case for creating a new player using the playerImpl class.
     */
    @Test
    public void testCreatePlayers() {
        // Test case 1: Player's age is under 10
        LocalDate validDateOfBirth = LocalDate.of(2015, 1, 1);
        Position preferredPosition = Position.FORWARD;
        int validSkillLevel = 4;

        playerImpl player1 = new playerImpl("John", "Doe", validDateOfBirth, preferredPosition, validSkillLevel);
        assertEquals("John", player1.getFirstName());
        assertEquals("Doe", player1.getLastName());
        assertEquals(8, player1.getAge());
        assertEquals(preferredPosition, player1.getPreferredPosition());
        assertEquals(validSkillLevel, player1.getSkillLevel());
        assertEquals(0, player1.getJerseyNumber());
    }

    /**
     * Test case for creating a new player with invalid data, where the age is over 10 years.
     * @throws IllegalArgumentException if the player's age is over 10 years.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateInvalidPlayer(){
        LocalDate validDateOfBirth = LocalDate.of(2010, 1, 1);
        Position preferredPosition = Position.FORWARD;
        int validSkillLevel = 4;

        playerImpl player1 = new playerImpl("John", "Doe", validDateOfBirth, preferredPosition, validSkillLevel);
    }


    /**
     * Test case for adding players to the team where the age of players is under 10 years.
     * This test verifies that players with valid data (age under 10) can be successfully added to the team.
     * It checks that the team size increases and the correct player information is stored in the team.
     */
    @Test
    public void testAddPlayerUnder10() {
        team.addPlayer("John", "Doe", LocalDate.of(2014, 1, 1), Position.MIDFIELDER, 5);
        team.addPlayer("Alice", "Smith", LocalDate.of(2015, 5, 10), Position.DEFENDER, 3);

        List<player> currentTeam = team.getCurrentTeam();
        assertEquals(2, currentTeam.size());
        assertEquals("John", currentTeam.get(0).getFirstName());
        assertEquals("Alice", currentTeam.get(1).getFirstName());
    }

    /**
     * Test case for adding players to the team where the age of players is over 10 years.
     * @throws IllegalArgumentException if the player's age is over 10 years.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddPlayerOver10() {
        team.addPlayer("John", "Doe", LocalDate.of(2004, 1, 1), Position.MIDFIELDER, 80);
        team.addPlayer("Alice", "Smith", LocalDate.of(2005, 5, 10), Position.DEFENDER, 70);
    }

    /**
     * Test case for the `removePlayer` method in the `teamModel` class.
     * It checks the functionality of removing a player from the team and recycling their jersey number.
     */
    @Test
    public void testRemovePlayer(){
        team.addPlayer("John", "Doe", LocalDate.of(2015, 5, 15), Position.MIDFIELDER, 1);
        team.addPlayer("Alice", "Smith", LocalDate.of(2018, 9, 23), Position.DEFENDER, 2);
        team.addPlayer("Harry", "Potter", LocalDate.of(2014, 3, 1), Position.DEFENDER,3);

        team.removePlayer("John", "Doe", LocalDate.of(2015, 5, 15), Position.MIDFIELDER, 1);
        assertFalse(team.getCurrentTeam().contains(new playerImpl("John", "Doe", LocalDate.of(2015, 5, 15), Position.MIDFIELDER, 1)));
        assertEquals(2, team.getTeamSize());
        assertTrue(team.getAvailableJerseyNumber().contains(1));
    }

    /**
     * Test case for sorting the players in alphabetical order.
     * This test verifies that the team can sort its players in alphabetical order based on their last names.
     */
    @Test
    public void playerInAlphabeticalOrder(){
        team.addPlayer("John", "Doe", LocalDate.of(2015, 5, 15), Position.MIDFIELDER, 1);
        team.addPlayer("Alice", "Smith", LocalDate.of(2018, 9, 23), Position.DEFENDER, 2);
        team.addPlayer("Harry", "Potter", LocalDate.of(2014, 3, 1), Position.DEFENDER,3);
        team.addPlayer("Draco", "Malfoy", LocalDate.of(2016, 4, 4), Position.MIDFIELDER, 5);
        team.addPlayer("James", "Price", LocalDate.of(2015, 5, 21), Position.FORWARD, 2);
        team.addPlayer("Lily", "Allen", LocalDate.of(2016, 7, 6), Position.FORWARD, 3);
        team.addPlayer("Michael", "Jackson", LocalDate.of(2015, 3, 2), Position.GOALIE, 4);
        team.addPlayer("Robert", "King", LocalDate.of(2013, 12, 31), Position.DEFENDER, 5);
        team.addPlayer("Jennifer", "Lopez", LocalDate.of(2016, 3, 31), Position.MIDFIELDER, 1);
        team.addPlayer("Katy", "Perry", LocalDate.of(2016, 2, 1), Position.FORWARD, 2);
        team.addPlayer("Aohn", "Doe", LocalDate.of(2015, 5, 15), Position.MIDFIELDER, 1);

        team.reconstructTeamSize();
        assertEquals("Lily Allen,Jersey Number: 6\n" +
                "John Doe,Jersey Number: 1\n" +
                "Aohn Doe,Jersey Number: 11\n" +
                "Michael Jackson,Jersey Number: 7\n" +
                "Robert King,Jersey Number: 8\n" +
                "Jennifer Lopez,Jersey Number: 9\n" +
                "Draco Malfoy,Jersey Number: 4\n" +
                "Katy Perry,Jersey Number: 10\n" +
                "Harry Potter,Jersey Number: 3\n" +
                "James Price,Jersey Number: 5\n" +
                "Alice Smith,Jersey Number: 2\n",team.getAllPlayersList());
    }

    /**
     * Test case for the team size exceeding the maximum limit of 20 players.
     * After reconstructing the team size, it checks if the team has handled the exceeding players correctly.
     */
    @Test(expected = IllegalStateException.class)
    public void testTeamSizeOver20(){
        team.addPlayer("John", "Doe", LocalDate.of(2015, 5, 15), Position.MIDFIELDER, 1);
        team.addPlayer("Alice", "Smith", LocalDate.of(2018, 9, 23), Position.DEFENDER, 2);
        team.addPlayer("Harry", "Potter", LocalDate.of(2014, 3, 1), Position.DEFENDER,3);
        team.addPlayer("Ron", "Weasley", LocalDate.of(2015, 2, 2), Position.GOALIE, 4);
        team.addPlayer("Hermione", "Granger", LocalDate.of(2015, 3, 1), Position.MIDFIELDER, 5);
        team.addPlayer("Draco", "Malfoy", LocalDate.of(2016, 4, 4), Position.MIDFIELDER, 5);
        team.addPlayer("James", "Price", LocalDate.of(2015, 5, 21), Position.FORWARD, 2);
        team.addPlayer("Lily", "Allen", LocalDate.of(2016, 7, 6), Position.FORWARD, 3);
        team.addPlayer("Michael", "Jackson", LocalDate.of(2015, 3, 2), Position.GOALIE, 4);
        team.addPlayer("Robert", "King", LocalDate.of(2013, 12, 31), Position.DEFENDER, 5);
        team.addPlayer("Jennifer", "Lopez", LocalDate.of(2016, 3, 31), Position.MIDFIELDER, 1);
        team.addPlayer("Katy", "Perry", LocalDate.of(2016, 2, 1), Position.FORWARD, 2);
        team.addPlayer("Aohn", "Doe", LocalDate.of(2015, 5, 15), Position.MIDFIELDER, 1);
        team.addPlayer("Blice", "Smith", LocalDate.of(2018, 9, 23), Position.DEFENDER, 2);
        team.addPlayer("Carry", "Potter", LocalDate.of(2014, 3, 1), Position.DEFENDER,3);
        team.addPlayer("Don", "Weasley", LocalDate.of(2015, 2, 2), Position.GOALIE, 4);
        team.addPlayer("Eermione", "Granger", LocalDate.of(2015, 3, 1), Position.MIDFIELDER, 5);
        team.addPlayer("Fraco", "Malfoy", LocalDate.of(2016, 4, 4), Position.MIDFIELDER, 5);
        team.addPlayer("Games", "Price", LocalDate.of(2015, 5, 21), Position.FORWARD, 2);
        team.addPlayer("Hily", "Allen", LocalDate.of(2016, 7, 6), Position.FORWARD, 3);
        team.addPlayer("Iichael", "Jackson", LocalDate.of(2015, 3, 2), Position.GOALIE, 4);
        team.addPlayer("Jobert", "King", LocalDate.of(2013, 12, 31), Position.DEFENDER, 5);
        team.addPlayer("Kennifer", "Lopez", LocalDate.of(2016, 3, 31), Position.MIDFIELDER, 1);
        team.addPlayer("Laty", "Perry", LocalDate.of(2016, 2, 1), Position.FORWARD, 2);
    }

    /**
     * Test case for the team size under 10 players.
     * Since the team size is under 10, it expects an IllegalStateException to be thrown during the reconstruction process.
     */
    @Test(expected = IllegalStateException.class)
    public void testTeamSizeUnder10(){
        team.addPlayer("John", "Doe", LocalDate.of(2015, 5, 15), Position.MIDFIELDER, 1);
        team.addPlayer("Alice", "Smith", LocalDate.of(2018, 9, 23), Position.DEFENDER, 2);
        team.addPlayer("Harry", "Potter", LocalDate.of(2014, 3, 1), Position.DEFENDER,3);

        team.reconstructTeamSize();
    }

    /**
     * Test case for assigning jersey numbers to players in the team.
     * This test verifies that the team correctly assigns unique jersey numbers to each player between 1 and 20.
     */
    @Test
    public void testAssignJerseyNumber(){
        team.addPlayer("John", "Doe", LocalDate.of(2015, 5, 15), Position.MIDFIELDER, 1);
        team.addPlayer("Alice", "Smith", LocalDate.of(2018, 9, 23), Position.DEFENDER, 2);
        team.addPlayer("Harry", "Potter", LocalDate.of(2014, 3, 1), Position.DEFENDER,3);
        team.addPlayer("Ron", "Weasley", LocalDate.of(2015, 2, 2), Position.GOALIE, 4);
        team.addPlayer("Hermione", "Granger", LocalDate.of(2015, 3, 1), Position.MIDFIELDER, 5);
        team.addPlayer("Draco", "Malfoy", LocalDate.of(2016, 4, 4), Position.MIDFIELDER, 5);
        team.addPlayer("James", "Price", LocalDate.of(2015, 5, 21), Position.FORWARD, 2);
        team.addPlayer("Lily", "Allen", LocalDate.of(2016, 7, 6), Position.FORWARD, 3);
        team.addPlayer("Michael", "Jackson", LocalDate.of(2015, 3, 2), Position.GOALIE, 4);
        team.addPlayer("Robert", "King", LocalDate.of(2013, 12, 31), Position.DEFENDER, 5);
        team.addPlayer("Jennifer", "Lopez", LocalDate.of(2016, 3, 31), Position.MIDFIELDER, 1);
        team.addPlayer("Katy", "Perry", LocalDate.of(2016, 2, 1), Position.FORWARD, 2);
        team.addPlayer("Aohn", "Doe", LocalDate.of(2015, 5, 15), Position.MIDFIELDER, 1);
        team.addPlayer("Blice", "Smith", LocalDate.of(2018, 9, 23), Position.DEFENDER, 2);
        team.addPlayer("Carry", "Potter", LocalDate.of(2014, 3, 1), Position.DEFENDER,3);

        team.reconstructTeamSize();

        assertEquals("Lily Allen,Jersey Number: 8\n" +
                "John Doe,Jersey Number: 1\n" +
                "Aohn Doe,Jersey Number: 13\n" +
                "Hermione Granger,Jersey Number: 5\n" +
                "Michael Jackson,Jersey Number: 9\n" +
                "Robert King,Jersey Number: 10\n" +
                "Jennifer Lopez,Jersey Number: 11\n" +
                "Draco Malfoy,Jersey Number: 6\n" +
                "Katy Perry,Jersey Number: 12\n" +
                "Harry Potter,Jersey Number: 3\n" +
                "Carry Potter,Jersey Number: 15\n" +
                "James Price,Jersey Number: 7\n" +
                "Alice Smith,Jersey Number: 2\n" +
                "Blice Smith,Jersey Number: 14\n" +
                "Ron Weasley,Jersey Number: 4\n",team.getAllPlayersList());

        List<player> currentTeam = team.getCurrentTeam();
        // Check if the jersey numbers are between 1 and 20 and there are no duplicates
        Set<Integer> jerseyNumbersSet = new HashSet<>();
        for (player p : currentTeam) {
            int jerseyNumber = p.getJerseyNumber();
            assertTrue(jerseyNumber >= 1 && jerseyNumber <= 20);
            jerseyNumbersSet.add(jerseyNumber);
        }
        assertEquals(currentTeam.size(), jerseyNumbersSet.size());
    }

    /**
     * Test case for assigning jersey numbers to players in the team.
     * This test verifies that the team correctly assigns unique jersey numbers to each player between 1 and 20.
     */
    @Test
    public void testSelectStartingLineup(){
        team.addPlayer("John", "Doe", LocalDate.of(2015, 5, 15), Position.MIDFIELDER, 1);
        team.addPlayer("Alice", "Smith", LocalDate.of(2018, 9, 23), Position.DEFENDER, 2);
        team.addPlayer("Harry", "Potter", LocalDate.of(2014, 3, 1), Position.DEFENDER,3);
        team.addPlayer("Ron", "Weasley", LocalDate.of(2015, 2, 2), Position.GOALIE, 4);
        team.addPlayer("Hermione", "Granger", LocalDate.of(2015, 3, 1), Position.MIDFIELDER, 5);
        team.addPlayer("Draco", "Malfoy", LocalDate.of(2016, 4, 4), Position.MIDFIELDER, 5);
        team.addPlayer("James", "Price", LocalDate.of(2015, 5, 21), Position.FORWARD, 2);
        team.addPlayer("Lily", "Allen", LocalDate.of(2016, 7, 6), Position.FORWARD, 3);
        team.addPlayer("Michael", "Jackson", LocalDate.of(2015, 3, 2), Position.GOALIE, 4);
        team.addPlayer("Robert", "King", LocalDate.of(2013, 12, 31), Position.DEFENDER, 5);
        team.addPlayer("Jennifer", "Lopez", LocalDate.of(2016, 3, 31), Position.MIDFIELDER, 1);
        team.addPlayer("Katy", "Perry", LocalDate.of(2016, 2, 1), Position.FORWARD, 2);
        team.addPlayer("Aohn", "Doe", LocalDate.of(2015, 5, 15), Position.MIDFIELDER, 1);
        team.addPlayer("Blice", "Smith", LocalDate.of(2018, 9, 23), Position.DEFENDER, 2);

        team.selectStartingLineup();
        team.assignPosition();
        assertEquals("Michael Jackson,Jersey Number: 9,Position:GOALIE\n" +
                "Robert King,Jersey Number: 10,Position:DEFENDER\n" +
                "Harry Potter,Jersey Number: 3,Position:DEFENDER\n" +
                "Hermione Granger,Jersey Number: 5,Position:MIDFIELDER\n" +
                "Draco Malfoy,Jersey Number: 6,Position:MIDFIELDER\n" +
                "Ron Weasley,Jersey Number: 4,Position:MIDFIELDER\n" +
                "Lily Allen,Jersey Number: 8,Position:FORWARD\n",team.getStartingLineupList());
    }

    /**
     * Test case for selecting the starting lineup with players having preferred positions.
     */
    @Test
    public void testFirstSevenGetsPreferredPosition(){
        team.addPlayer("John", "Doe", LocalDate.of(2015, 5, 15), Position.GOALIE, 5);
        team.addPlayer("Alice", "Smith", LocalDate.of(2018, 9, 23), Position.DEFENDER, 5);
        team.addPlayer("Harry", "Potter", LocalDate.of(2014, 3, 1), Position.DEFENDER,5);
        team.addPlayer("Ron", "Weasley", LocalDate.of(2015, 2, 2), Position.MIDFIELDER, 5);
        team.addPlayer("Hermione", "Granger", LocalDate.of(2015, 3, 1), Position.MIDFIELDER, 5);
        team.addPlayer("Draco", "Malfoy", LocalDate.of(2016, 4, 4), Position.MIDFIELDER, 5);
        team.addPlayer("James", "Price", LocalDate.of(2015, 5, 21), Position.FORWARD, 5);
        team.addPlayer("Lily", "Allen", LocalDate.of(2016, 7, 6), Position.FORWARD, 1);
        team.addPlayer("Michael", "Jackson", LocalDate.of(2015, 3, 2), Position.GOALIE, 1);
        team.addPlayer("Robert", "King", LocalDate.of(2013, 12, 31), Position.DEFENDER, 1);

        team.reconstructTeamSize();
        team.selectStartingLineup();
        team.assignPosition();

        String startingLineupString = team.getStartingLineupList();

        assertTrue(startingLineupString.contains("GOALIE"));
        assertTrue(startingLineupString.contains("John Doe"));

        assertTrue(startingLineupString.contains("DEFENDER"));
        assertTrue(startingLineupString.contains("Alice Smith"));
        assertTrue(startingLineupString.contains("Harry Potter"));

        assertTrue(startingLineupString.contains("MIDFIELDER"));
        assertTrue(startingLineupString.contains("Ron Weasley"));
        assertTrue(startingLineupString.contains("Hermione Granger"));
        assertTrue(startingLineupString.contains("Draco Malfoy"));

        assertTrue(startingLineupString.contains("FORWARD"));
        assertTrue(startingLineupString.contains("James Price"));
    }

}
