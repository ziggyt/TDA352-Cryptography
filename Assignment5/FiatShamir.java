package Assignment5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;

public class FiatShamir {

    public static class ProtocolRun {
        public final BigInteger R;
        public final int c;
        public final BigInteger s;

        public ProtocolRun(BigInteger R, int c, BigInteger s) {
            this.R = R;
            this.c = c;
            this.s = s;


        }

        @Override
        public String toString() {
            return "ProtocolRun{" +
                    "R=" + R +
                    ", c=" + c +
                    ", s=" + s +
                    '}';
        }
    }

    public static void main(String[] args) {
        String filename = "Assignment5/input.txt";
        BigInteger N = BigInteger.ZERO;
        BigInteger X = BigInteger.ZERO;
        ProtocolRun[] runs = new ProtocolRun[10];
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            N = new BigInteger(br.readLine().split("=")[1]);
            X = new BigInteger(br.readLine().split("=")[1]);
            for (int i = 0; i < 10; i++) {
                String line = br.readLine();
                String[] elem = line.split(",");
                runs[i] = new ProtocolRun(
                        new BigInteger(elem[0].split("=")[1]),
                        Integer.parseInt(elem[1].split("=")[1]),
                        new BigInteger(elem[2].split("=")[1]));
            }
            br.close();
        } catch (Exception err) {
            System.err.println("Error handling file.");
            err.printStackTrace();
            System.exit(1);
        }
        BigInteger m = recoverSecret(N, X, runs);
        System.out.println("Recovered message: " + m);
        System.out.println("Decoded text: " + decodeMessage(m));
    }

    public static String decodeMessage(BigInteger m) {
        return new String(m.toByteArray());
    }

    /**
     * Recovers the secret used in this collection of Fiat-Shamir protocol runs.
     *
     * @param N    The modulus
     * @param X    The public component
     * @param runs Ten runs of the protocol.
     * @return
     */
    private static BigInteger recoverSecret(BigInteger N, BigInteger X,
                                            ProtocolRun[] runs) {

        //p and q are two large secret primes and N = pq is public.
        //Peggy’s private key is a random x belongs to Zn
        // and her public key is X = x2 mod N.

        //random bit b = c

        BigInteger sZero = BigInteger.valueOf(0);
        BigInteger sOne = BigInteger.valueOf(0);


        for (int i = 0; i < runs.length; i++) {
            // System.out.println(runs[i]);
            for (int j = 0; j < runs.length; j++) {
                if (i == j) continue; // no self duplicates

                if (runs[i].R.equals(runs[j].R)) {
                    System.out.println(runs[i].toString());
                    if (runs[i].c == 0) {
                        sZero = runs[i].s;
                    }
                    if (runs[i].c == 1) {
                        sOne = runs[i].s;
                    }
                }
            }
        }

        BigInteger x = sOne.multiply(sZero.modInverse(N)).mod(N);


        // TODO. Recover the secret value x such that x^2 = X (mod N).
        return x;
    }
}
