package soccerTeam;

/**
 * The `Position` enum represents different positions for players in a sports team.
 * It defines four possible positions: GOALIE, DEFENDER, MIDFIELDER, and FORWARD.
 */
public enum Position {

    GOALIE,
    DEFENDER,
    MIDFIELDER,
    FORWARD;

    /**
     * Returns the string representation of each position.
     * The string representation is the same as the name of the enum constant, e.g., "GOALIE", "DEFENDER", "MIDFIELDER", or "FORWARD".
     *
     * @return The string representation of the position.
     */
    public String toString(){
        if (this == Position.GOALIE){
            return "GOALIE";
        } else if (this == Position.DEFENDER){
            return "DEFENDER";
        } else if (this == Position.MIDFIELDER){
            return "MIDFIELDER";
        } else {
            return "FORWARD";
        }
    }
}
