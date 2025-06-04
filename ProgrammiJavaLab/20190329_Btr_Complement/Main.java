class Main {

	public static void main(String[] args) {
		System.out.println("BTR successor of " + args[0] + ": " + btrSucc(args[0]));
		System.out.println("Ones' complement of " + args[1] + ": " + onesComplement(args[1]));
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

	public static String onesComplement(String bin) {
		String compl = "";
		for (int i = 0; i < bin.length(); i++) {
			compl += ((bin.charAt(i) == '0')?"1":"0");
		}
		return compl;
	}

}