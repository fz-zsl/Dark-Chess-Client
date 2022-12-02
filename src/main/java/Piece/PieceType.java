package Piece;

public enum PieceType
{
    RADVISOR('仕',1, "file:/D:/DarkChess/ChessImages/RAdvisor1.png", "D:/DarkChess/src/audio/仕.mp3"),
    RCANNON('炮',6, "file:/D:/DarkChess/ChessImages/RCannon1.png", "D:/DarkChess/src/audio炮.mp3"),
    RCHARIOT('俥',3, "file:/D:/DarkChess/ChessImages/RChariot1.png","D:/DarkChess/src/audio/车.mp3"),
    RGENERAL('帥',0, "file:/D:/DarkChess/ChessImages/RGeneral.png","D:/DarkChess/src/audio/将.mp3"),
    RHORSE('傌',4, "file:/D:/DarkChess/ChessImages/RHorse1.png","D:/DarkChess/src/audio马.wav"),
    RMINISTER('相',2, "file:/D:/DarkChess/ChessImages/RMinister1.png","D:/DarkChess/src/audio象.mp3"),
    RSOLDIER('兵',5, "file:/D:/DarkChess/ChessImages/RSoldier1.png","D:/DarkChess/src/audio/兵.mp3"),

    BADVISOR('士',11, "file:/D:/DarkChess/ChessImages/BAdvisor1.png","D:/DarkChess/src/audio/仕.mp3"),
    BCANNON('砲',16, "file:/D:/DarkChess/ChessImages/BCannon1.png","D:/DarkChess/src/audio炮.mp3"),
    BCHARIOT('車',13, "file:/D:/DarkChess/ChessImages/BChariot1.png","D:/DarkChess/src/audio/车.mp3"),
    BGENERAL('將',10, "file:/D:/DarkChess/ChessImages/BGeneral.png","D:/DarkChess/src/audio/将.mp3"),
    BHORSE('馬',14, "file:/D:/DarkChess/ChessImages/BHorse1.png","D:/DarkChess/src/audio马.wav"),
    BMINISTER('象',12, "file:/D:/DarkChess/ChessImages/BMinister1.png","D:/DarkChess/src/audio象.mp3"),
    BSOLDIER('卒',15, "file:/D:/DarkChess/ChessImages/BSoldier1.png","D:/DarkChess/src/audio/兵.mp3"),

    BACK('N',-1,"file:/D:/DarkChess/ChessImages/EmptyChess.png",null);
    private final char desc;
    private final int num;
    private final String address;
    private final String audio;
    private PieceType(char desc, int num, String address, String audio)
    {
        this.desc = desc;
        this.num = num;
        this.address = address;
        this.audio = audio;
    }

    public int getNum()
    {
        return num;
    }

    public String getAddress()
    {
        return address;
    }

    public String getAudio(){return audio;}

}
