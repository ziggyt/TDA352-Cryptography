package Assignment2;// Compilation (Assignment2.CryptoLibTest contains the main-method):
//   javac Assignment2.CryptoLibTest.java
// Running:
//   java Assignment2.CryptoLibTest

import java.util.Arrays;

public class CryptoLib {

    /**
     * Returns an array "result" with the values "result[0] = gcd",
     * "result[1] = s" and "result[2] = t" such that "gcd" is the greatest
     * common divisor of "a" and "b", and "gcd = a * s + b * t".
     **/

    public static void main(String[] args) {
        System.out.println(ModInv(16, 23));
    }
    public static int[] EEA(int a, int b) {
        // Note: as you can see in the test suite,
        // your function should work for any (positive) value of a and b.

        // bezouts theorem
        // as + bt = gcd(a,b)
        int bezout_T = 0;


        int s = 0;
        int r = b;
        int old_S = 1;
        int old_R = a;


        while (r != 0) {

            int q = old_R / r;

            int tmp = r;
            r = old_R - q * tmp;
            old_R = tmp;

            tmp = s;
            s = old_S - q * tmp;
            old_S = tmp;
        }

        if (b != 0) {
            bezout_T = (old_R - old_S * a) / b;
        }

        int[] res = new int[]{old_R, old_S, bezout_T};


        return (res);

    }


    /**
     * Returns the modular exponentiation "a^k (mod n)".
     * <p>
     * Based on pseudocode from Applied Cryptography by Bruce Schneier
     **/
    public static int ModExp(int n, int a, int k) {

        if (n == 1) {
            return 0;
        }

        int res = 1;
        a = a % n;

        while (k > 0) {
            if (k % 2 == 1) {
                res = (res * a) % n;
            }
            k = k >> 1;
            a = (a * a) % n;
        }
        return res;
    }

    /**
     * Returns the value "v" such that "n*v = 1 (mod m)". Returns 0 if the
     * modular inverse does not exist.
     **/
    public static int ModInv(int n, int m) { //can only be found when gcd = 1 //TODO "ModInv failed for input -9 and 823. Got 183 expected 640" test case fails

        int[] eea = EEA(m, n);

        if (eea[0] != 1) {
            System.out.println("Modular inverse does not exist for " + n + " and " + m);
            return 0;
        }

        return Math.floorMod(eea[2], m);

    }

    /**
     * Returns 0 if "n" is a Fermat Prime, otherwise it returns the lowest
     * Fermat Witness. Tests values from 2 (inclusive) to "n/3" (exclusive).
     * <p>
     * <p>
     * Fermat's primality test. Instead of picking random values a to test the primality of
     * a number n, make a start from 2 and increment it by 1 at each new iteration, until
     * you have tested all the values below n=3.
     **/
    public static int FermatPT(int n) {

        int a = 2;

        int tmp = n - 1;

        while (a < n / 3) {

            if (ModExp(n, a, tmp) != 1) {

                return a;

            }
            a++;
        }

        return 0;
    }

}
