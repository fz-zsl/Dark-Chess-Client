package fileOperations;

public class UserAndCredit implements Comparable<UserAndCredit> {
	public String userName;
	public int credit;

	public UserAndCredit(String userName,int credit) {
		this.userName=userName;
		this.credit=credit;
	}

	@Override
	public int compareTo(UserAndCredit o) {
		Integer cre=this.credit;
		return -cre.compareTo(o.credit);
	}
}