package game;

public enum Team{
    WHITE,
    BLACK;

    public Team getEnemyTeam(){
        return this == WHITE ? BLACK : WHITE;
    }
}
