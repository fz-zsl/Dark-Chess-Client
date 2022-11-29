package Piece;

public enum PieceType
{
    RADVISOR('仕',10, "file:/D:/DarkChess/ChessImages/RAdvisor1.png"),
    RCANNON('炮',60, "file:/D:/DarkChess/ChessImages/RCannon1.png"),
    RCHARIOT('俥',30, "file:/D:/DarkChess/ChessImages/RChariot1.png"),
    RGENERAL('帥',00, "file:/D:/DarkChess/ChessImages/RGeneral.png"),
    RHORSE('傌',40, "file:/D:/DarkChess/ChessImages/RHorse1.png"),
    RMINISTER('相',20, "file:/D:/DarkChess/ChessImages/RMinister1.png"),
    RSOLDIER('兵',50, "file:/D:/DarkChess/ChessImages/RSoldier1.png"),

    BADVISOR('士',11, "file:/D:/DarkChess/ChessImages/BAdvisor1.png"),
    BCANNON('砲',61, "file:/D:/DarkChess/ChessImages/BCannon1.png"),
    BCHARIOT('車',31, "file:/D:/DarkChess/ChessImages/BChariot1.png"),
    BGENERAL('將',01, "file:/D:/DarkChess/ChessImages/BGeneral.png"),
    BHORSE('馬',41, "file:/D:/DarkChess/ChessImages/BHorse1.png"),
    BMINISTER('象',21, "file:/D:/DarkChess/ChessImages/BMinister1.png"),
    BSOLDIER('卒',51, "file:/D:/DarkChess/ChessImages/BSoldier1.png"),

    BACK('N',-1,"file:/D:/DarkChess/ChessImages/EmptyChess.png");
    private final char desc;
    private final int num;
    private final String address;
    private PieceType(char desc, int num, String address)
    {
        this.desc = desc;
        this.num = num;
        this.address = address;
    }

    public int getNum()
    {
        return num;
    }

    public String getAddress()
    {
        return address;
    }

}
