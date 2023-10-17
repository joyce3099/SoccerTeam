package soccerTeam;

import java.time.LocalDate;
import java.util.*;

/**
 * The `teamModel` class represents a model for a soccer team. It implements the `team` interface, which defines the behavior and properties of a team.
 * This class allows adding players to the team, assigning jersey numbers, selecting a starting lineup, and assigning positions to players.
 */
public class teamModel implements team{

    private int teamSize; // The current size of the team
    private List<player> startLineUpTeam; // The list of players in the starting lineup
    private List<Integer> jerseyNumber; // The list to store jersey numbers for players
    private List<player> currentTeam; // The list of all current players in the team

    /**
     * Constructor for the `teamModel` class. Initializes the team properties.
     */
    public teamModel(){
        this.teamSize = 0;
        this.startLineUpTeam = new ArrayList<>();
        this.jerseyNumber = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            jerseyNumber.add(i);
        }
        this.currentTeam = new ArrayList<>();
    }

    @Override
    public List<player> getCurrentTeam() {
        return currentTeam;
    }

    @Override
    public List<Integer> getAvailableJerseyNumber(){
        return jerseyNumber;
    }

    @Override
    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public void addPlayer(String firstName, String lastName, LocalDate dateOfBirth, Position preferredPosition, int skillLevel) throws IllegalStateException{

        player newPlayer = new playerImpl(firstName, lastName, dateOfBirth, preferredPosition, skillLevel);

        // Check if the team is over 20 players
        if (teamSize >= 20){
            throw new IllegalStateException("The team is full and cannot add players.");
        }

        int assignedNumber = jerseyNumber.remove(0);

        newPlayer.setJerseyNumber(assignedNumber);
        currentTeam.add(newPlayer);
        teamSize++;

        // Sort the current team in alphabetical order by last name
        Collections.sort(currentTeam, Comparator.comparing(player::getLastName));
    }

    @Override
    public void reconstructTeamSize() throws IllegalStateException{
        if (teamSize < 10){
            throw new IllegalStateException("The team should have at least 10 players");
        }

        Collections.sort(currentTeam, Comparator.comparing(player::getLastName));
    }


    @Override
    public boolean removePlayer (String firstName, String lastName, LocalDate dateOfBirth, Position preferredPosition, int skillLevel)throws IllegalArgumentException{

        player playerToRemove = null;
        for (player p : currentTeam) {
            if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName) && p.getDateOfBirth().equals(dateOfBirth)
                    && p.getPreferredPosition().equals(preferredPosition) && p.getSkillLevel() == (skillLevel)) {
                playerToRemove = p;
                break;
            }
        }

        if (playerToRemove != null) {
            int recycledNumber = playerToRemove.getJerseyNumber();

            currentTeam.remove(playerToRemove);
            teamSize--;

            if (recycledNumber >= 1 && recycledNumber <= 20) {
                // Add the recycled number back to the available numbers list
                jerseyNumber.add(recycledNumber);
            }

            Collections.sort(currentTeam, Comparator.comparing(player::getLastName));
            return true;
        }else {
            return false;
        }
    }


    @Override
    public void selectStartingLineup() {
        startLineUpTeam.clear();
        // Sort the current team by skill level (highest to lowest)
        Collections.sort(currentTeam,Comparator.comparingInt(player::getSkillLevel).reversed());

        // Add the top seven players (or all players if less than seven) to the starting lineup
        int numLineupPlayers = Math.min(7,currentTeam.size());
        for (int i =0;i<numLineupPlayers;i++){
            startLineUpTeam.add(currentTeam.get(i));
        }
    }

    @Override
    public void assignPosition() {

        for (player p : startLineUpTeam) {
            p.setActualPosition(null);
        }

        List<player> assignedPositions = new ArrayList<>();
        List<player> unassignedPlayers = new ArrayList<>(startLineUpTeam);
        Map<Position, Integer> positionCounts = new HashMap<>();

        Collections.sort(startLineUpTeam, Comparator.comparingInt(player::getSkillLevel).reversed());

        // Assign preferred positions to players in the starting lineup based on skill level
        for (player p : startLineUpTeam) {
            Position preferredPosition = p.getPreferredPosition();
            // Check if the player has a preferred position and the position count is not exceeded
            if (preferredPosition != null && positionCounts.getOrDefault(preferredPosition, 0) < getMaxPositionCount(preferredPosition)) {
                p.setActualPosition(preferredPosition);
                assignedPositions.add(p);
                unassignedPlayers.remove(p);
                // Increment the position count for the preferred position
                positionCounts.put(preferredPosition, positionCounts.getOrDefault(preferredPosition, 0) + 1);
            }
        }

        int numPlayersInLineup = assignedPositions.size();
        if (numPlayersInLineup < 7) {
            // Assign remaining positions to players in the order of priority (Goalie -> Defenders -> Midfielders -> Forward)
            Position[] priorityOrder = { Position.GOALIE, Position.DEFENDER, Position.MIDFIELDER, Position.FORWARD };
            for (Position position : priorityOrder) {
                int maxPositionCount = getMaxPositionCount(position);
                // Continue assigning players while the lineup size is below 7 and the position count limit is not reached
                while (numPlayersInLineup < 7 && positionCounts.getOrDefault(position, 0) < maxPositionCount) {
                    // Find the next player who has not been assigned a position
                    for (player p : unassignedPlayers) {
                        if (p.getActualPosition() == null) {
                            p.setActualPosition(position);
                            assignedPositions.add(p);
                            positionCounts.put(position, positionCounts.getOrDefault(position, 0) + 1);
                            numPlayersInLineup++;
                            break;
                        }
                    }
                }
            }
        }

        // Update the starting lineup with the assigned positions
        startLineUpTeam = assignedPositions;
    }

    @Override
    public String getAllPlayersList() {
        List<player> allPlayers = new ArrayList<>(currentTeam);
        Collections.sort(allPlayers, Comparator.comparing(player::getLastName));
        StringBuilder sb = new StringBuilder();

        for (player p :allPlayers){
            sb.append(p.getFirstName()).append(" ").append(p.getLastName()).append(",Jersey Number: ").append(p.getJerseyNumber()).append("\n");
        }

        return sb.toString();
    }

    @Override
    public String getStartingLineupList() {
        try {

            List<player> startingLineUpPlayers = new ArrayList<>(startLineUpTeam);
            startingLineUpPlayers.sort(Comparator.comparing(player::getActualPosition).thenComparing(player::getLastName));
            StringBuilder sb = new StringBuilder();

            for (player p : startingLineUpPlayers) {
                sb.append(p.getFirstName()).append(" ").append(p.getLastName()).append(",Jersey Number: ").append(p.getJerseyNumber()).
                        append(",Position:").append(p.getActualPosition()).append("\n");
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while generating the starting lineup.";
        }
    }

    /**
     * Helper method to get the maximum allowed count for a specific position.
     *
     * @param position The position for which to get the maximum allowed count.
     * @return The maximum allowed count for the specified position.
     */
    private int getMaxPositionCount(Position position){
        if(position == Position.GOALIE){
            return 1;
        } else if (position == Position.DEFENDER){
            return 2;
        } else if (position == Position.MIDFIELDER){
            return 3;
        } else if (position == Position.FORWARD){
            return 1;
        } else {
            return 0;
        }
    }
}
