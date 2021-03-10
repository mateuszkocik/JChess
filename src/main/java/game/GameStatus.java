package game;

public enum GameStatus{
    ONGOING,
    WHITE_WON,
    BLACK_WON,
    DRAW;

    public static GameStatus winnerOfTeam(Team team){
        return team == Team.WHITE ? WHITE_WON : BLACK_WON;
    }
}
