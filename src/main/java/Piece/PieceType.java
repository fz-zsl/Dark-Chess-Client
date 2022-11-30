package Piece;

public enum PieceType
{
    RADVISOR('仕',1, "file:/D:/DarkChess/ChessImages/RAdvisor1.png"),
    RCANNON('炮',6, "file:/D:/DarkChess/ChessImages/RCannon1.png"),
    RCHARIOT('俥',3, "file:/D:/DarkChess/ChessImages/RChariot1.png"),
    RGENERAL('帥',0, "file:/D:/DarkChess/ChessImages/RGeneral.png"),
    RHORSE('傌',4, "file:/D:/DarkChess/ChessImages/RHorse1.png"),
    RMINISTER('相',2, "file:/D:/DarkChess/ChessImages/RMinister1.png"),
    RSOLDIER('兵',5, "file:/D:/DarkChess/ChessImages/RSoldier1.png"),

    BADVISOR('士',11, "file:/D:/DarkChess/ChessImages/BAdvisor1.png"),
    BCANNON('砲',16, "file:/D:/DarkChess/ChessImages/BCannon1.png"),
    BCHARIOT('車',13, "file:/D:/DarkChess/ChessImages/BChariot1.png"),
    BGENERAL('將',10, "file:/D:/DarkChess/ChessImages/BGeneral.png"),
    BHORSE('馬',14, "file:/D:/DarkChess/ChessImages/BHorse1.png"),
    BMINISTER('象',12, "file:/D:/DarkChess/ChessImages/BMinister1.png"),
    BSOLDIER('卒',15, "file:/D:/DarkChess/ChessImages/BSoldier1.png"),

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
