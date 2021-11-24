// Compilation (CryptoLibTest contains the main-method):
//   javac CryptoLibTest.java
// Running:
//   java CryptoLibTest

public class CryptoLib {

//    /**
//     * Returns an array "result" with the values "result[0] = gcd",
//     * "result[1] = s" and "result[2] = t" such that "gcd" is the greatest
//     * common divisor of "a" and "b", and "gcd = a * s + b * t".
//     **/
//    public static int[] EEA(int a, int b) {
//        // Note: as you can see in the test suite,
//        // your function should work for any (positive) value of a and b.
//
//        // bezouts theorem
//        // as + bt = gcd(a,b)
//
//
////        int gcd = -1;
////        int s = -1;
////        int t = -1;
//
//        int[] result = new int[3];
//
//        //base case for recursion
//
//        if (b == 0) {
//            result[0] = a;
//            result[1] = 1;
//        } else {
//
//
//        }
//        =
//
//
////
////        int tmp; // standard EA
////        while (b > 0) {
////            tmp = a % b;
////            a = b;
////            b = tmp;
////        }
//
//        // when while loop breaks, a is gcd
//
//
//        return result;
//
//    }

    /**
     * Returns an array "result" with the values "result[0] = gcd",
     * "result[1] = s" and "result[2] = t" such that "gcd" is the greatest
     * common divisor of "a" and "b", and "gcd = a * s + b * t".
     **/
    public static int[] EEA(int a, int b) {
        // Note: as you can see in the test suite,
        // your function should work for any (positive) value of a and b.

        // bezouts theorem
        // as + bt = gcd(a,b)
        int bezoutT = 0;


        int s = 0;
        int r = b;
        int oldS = 1;
        int oldR = a;


        while (r != 0) {

            int q = oldR / r;

            int tmp = r;
            r = oldR - q * tmp;
            oldR = tmp;

            tmp = s;
            s = oldS - q * tmp;
            oldS = tmp;
        }

        if (b != 0) {
            bezoutT = (oldR - oldS * a) / b;
        }

        int[] res = new int[]{oldR, oldS, bezoutT};

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
    public static int ModInv(int n, int m) { //can only be found when gcd = 1
//
//        if (EEA(n, m)[0] != 1) {
//            System.out.println("Modular inverse does not exist for " + n + " and " + m);
//            return 0;
//        }
//
//        return EEA(n, m)[1];

        return 11111111;
    }

    /**
     * Returns 0 if "n" is a Fermat Prime, otherwise it returns the lowest
     * Fermat Witness. Tests values from 2 (inclusive) to "n/3" (exclusive).
     **/
    public static int FermatPT(int n) {
        return -1;
    }

}
