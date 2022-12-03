package fileOperations;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class RankList {
	private static ArrayList<UserAndCredit> result=new ArrayList<>();

	private static int calcCredit(ArrayList<Integer> userGameCounter) {
		//Todo
		return 1500
				+userGameCounter.get(0)*2
				+userGameCounter.get(1)*3
				+userGameCounter.get(2)*3
				+userGameCounter.get(3)*3
				+userGameCounter.get(4)*3
				+userGameCounter.get(5)*3
				+userGameCounter.get(6)*3
				+userGameCounter.get(7)*3;
	}

	private static int getUserCredit(String userFile)
	{
		Scanner sc=new Scanner(System.in);
		try {
			sc=new Scanner(new File("database/"+userFile));
		} catch (Exception e) {
			System.out.println(e);
		}
		sc.next();
		ArrayList<Integer> userGameCounter=new ArrayList<>();
		for (int i=0;i<8;++i) userGameCounter.add(sc.nextInt());
		return calcCredit(userGameCounter);
	}

	public static ArrayList<UserAndCredit> getRankList() {
		File[] files=(new File("database/")).listFiles();
		for (File file: files) {
			if (!file.isFile()) continue;
			String fileName=file.getName();
			if (!fileName.endsWith(".user.txt")) continue;
			String userName=fileName.split(".user")[0];
			result.add(new UserAndCredit(userName,getUserCredit(fileName)));
		}
		result.sort(Comparator.naturalOrder());
		return result;
	}

	public static int findUserRank() {
		for (int i=0;i<result.size();++i) {
			if (result.get(i).userName.equals(LoginInterface.userFile.getName().split(".user")[0])) {
				return i;
			}
		}
		return -1;
	}
}
