package Assignment4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;


public class AttackRSA {

	public static void main(String[] args) {
		String filename = "Assignment4/input.txt";
		BigInteger[] N = new BigInteger[3];
		BigInteger[] e = new BigInteger[3];
		BigInteger[] c = new BigInteger[3];
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			for (int i = 0; i < 3; i++) {
				String line = br.readLine();
				String[] elem = line.split(",");
				N[i] = new BigInteger(elem[0].split("=")[1]);
				e[i] = new BigInteger(elem[1].split("=")[1]);
				c[i] = new BigInteger(elem[2].split("=")[1]);
			}
			br.close();
		} catch (Exception err) {
			System.err.println("Error handling file.");
			err.printStackTrace();
		}
		BigInteger m = recoverMessage(N, e, c);
		System.out.println("Recovered message: " + m);
		System.out.println("Decoded text: " + decodeMessage(m));
	}

	public static String decodeMessage(BigInteger m) {
		return new String(m.toByteArray());
	}

	/**
	 * Tries to recover the message based on the three intercepted cipher texts.
	 * In each array the same index refers to same receiver. I.e. receiver 0 has
	 * modulus N[0], public key d[0] and received message c[0], etc.
	 * 
	 * @param N
	 *            The modulus of each receiver.
	 * @param e
	 *            The public key of each receiver (should all be 3).
	 * @param c
	 *            The cipher text received by each receiver.
	 * @return The same message that was sent to each receiver.
	 */
	private static BigInteger recoverMessage(BigInteger[] N, BigInteger[] e,
			BigInteger[] c) {



		BigInteger first = crt(c[0], c[1], N[0], N[1]);
		BigInteger second = crt(first, c[2], N[0].multiply(N[1]), N[2]);

		BigInteger result = CubeRoot.cbrt(second);

		return result;
	}

	//chinese remainder theorem

	private static BigInteger crt(BigInteger x1, BigInteger x2, BigInteger p, BigInteger q){

		BigInteger u = BigInteger.valueOf(0);

		u = x2.subtract(x1).multiply(p.modInverse(q)).mod(q);

		BigInteger res = x1.add(u.multiply(p));


		return res;
	}



}
