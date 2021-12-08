package cryptolib;//   javac cryptolib.CryptoLibTest.java
// Running:
//   java cryptolib.CryptoLibTest

public class CryptoLibTest {

	static int TestEEA(int a, int b, int gcd, int s, int t) {
		int[] res = CryptoLib.EEA(a, b);
		if (res[0] == gcd && res[1] == s && res[2] == t) {
			return 0;
		}
		System.out.println("EEA failed for input " + a + "," + b + ". Got "
				+ res[0] + "," + res[1] + "," + res[2]
				+ " expected values were " + gcd + "," + s + "," + t);
		return 1;
	}

	static void TestEEA() {
		int errors = 0;
		//errors += TestEEA(5, 5, 5, 1, 0);
		errors += TestEEA(18, 1, 1, 0, 1);
		errors += TestEEA(1, 18, 1, 1, 0);
		errors += TestEEA(21, 22, 1, -1, 1);
		errors += TestEEA(157, 111, 1, -41, 58);
		errors += TestEEA(6, 68, 2, -11, 1);
		errors += TestEEA(12, 36, 12, 1, 0);
		errors += TestEEA(42, 25, 1, 3, -5);
		errors += TestEEA(150, 340, 10, -9, 4);
		System.out.println("Total errors in the EEA function " + errors);

	}

    static int TestModExp(int n, int a, int k, int expected) {
        if (CryptoLib.ModExp(n, a, k) == expected)
			return 0;
		System.out.println("ModExp failed for input " + n + "," + a + "," + k + ". Got "
                                   + CryptoLib.ModExp(n, a, k) + " expected " + expected);
		return 1;
	}

	static void TestModExp() {
		int errors = 0;
		errors += TestModExp(11, 3, 10, 1);
		errors += TestModExp(561, 10, 560, 1);
		errors += TestModExp(569, 3, 71, 277);
		errors += TestModExp(1000, 57, 10, 249);
		errors += TestModExp(30, 5, 6, 25);
		System.out.println("Total errors in the ModExp function " + errors);

	}

	static int TestModInv(int n, int mod, int inv) {
		if (CryptoLib.ModInv(n, mod) == inv)
			return 0;
		System.out.println("ModInv failed for input " + n + " and " + mod
				+ ". Got " + CryptoLib.ModInv(n, mod) + " expected " + inv);
		return 1;
	}

	static void TestModInv() {
		int errors = 0;
		errors += TestModInv(25, 42, 37);
		errors += TestModInv(11, 20, 11);
		errors += TestModInv(13, 50, 27);
		errors += TestModInv(8954, 123, 59);
		errors += TestModInv(-9, 823, 640);
		System.out.println("Total errors in the ModInv function " + errors);

	}

	static int TestFermatPT(int n, int expected) {
		if (CryptoLib.FermatPT(n) == expected)
			return 0;
		System.out.println("FermatPT failed for input " + n + ". Got "
				+ CryptoLib.FermatPT(n) + " expected " + expected);
		return 1;
	}

	static void TestFermatPT() {
		int errors = 0;
		errors += TestFermatPT(7, 0);
		errors += TestFermatPT(12, 2);
		errors += TestFermatPT(53, 0);
		errors += TestFermatPT(111, 2);
		errors += TestFermatPT(157, 0);
		errors += TestFermatPT(158, 2);
		errors += TestFermatPT(341, 3);
		errors += TestFermatPT(1105, 5);
		errors += TestFermatPT(2821, 7);
		System.out.println("Total errors in the FermatPT function " + errors);
	}

 	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestEEA();
		TestModExp();
		TestModInv();
		TestFermatPT();

	}

}
