public class Main {

	public static void main(String[] args) {
		System.out.println(btrList("+-", 5));
	}
	
	public static StringSList btrList(String btr, int n) {
		StringSList s = new StringSList();
		String num = btr;
		for (int i = 0; i < n; i++) {
			s = s.cons(num);
			num = btrSucc(num);
		}
		return s.reverse();
	}

	public static String btrSucc(String btr) {
		int n = btr.length();
		char lsb = btr.charAt(n-1);
		if (n == 1) {
			if (lsb == '+') {
				return "+-";
			} else {
				return "+";
			}
		} else {
			String pre = btr.substring(0, n-1);
			if (lsb == '+') {
				return btrSucc(pre) + "-";
			} else {
				return pre + ((lsb == '-')?".":"+");
			}
		}
	}

}