package soccerTeam;

/**
 * The main class to start the U10 Soccer Team Management System application.
 */

public class main {
    public static void main(String[] args) {
        teamModel model = new teamModel();
        SwingSoccerTeamView view = new SwingSoccerTeamView("U10 Soccer Team Management System");
        SoccerTeamController controller = new SwingSoccerTeamController(model,view);

        view.setVisible(true);
    }
}