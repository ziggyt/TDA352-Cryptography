package Assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.Objects;


// time=2015-12-25 12:30:59

public class ElGamal {

    public static int milliseconds = 999;


    public static String decodeMessage(BigInteger m) {
        return new String(m.toByteArray());
    }

    public static void main(String[] arg) {
        String filename = "Assignment3/input.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            BigInteger p = new BigInteger(br.readLine().split("=")[1]);
            BigInteger g = new BigInteger(br.readLine().split("=")[1]);
            BigInteger y = new BigInteger(br.readLine().split("=")[1]);
            String line = br.readLine().split("=")[1];
            String date = line.split(" ")[0];
            String time = line.split(" ")[1];
            int year = Integer.parseInt(date.split("-")[0]);
            int month = Integer.parseInt(date.split("-")[1]);
            int day = Integer.parseInt(date.split("-")[2]);
            int hour = Integer.parseInt(time.split(":")[0]);
            int minute = Integer.parseInt(time.split(":")[1]);
            int second = Integer.parseInt(time.split(":")[2]);
            BigInteger c1 = new BigInteger(br.readLine().split("=")[1]);
            BigInteger c2 = new BigInteger(br.readLine().split("=")[1]);
            br.close();
            BigInteger m = recoverSecret(p, g, y, year, month, day, hour, minute,
                    second, c1, c2);
            System.out.println("Recovered message: " + m);
            System.out.println("Decoded text: " + decodeMessage(m));
        } catch (Exception err) {
            System.err.println("Error handling file.");
            err.printStackTrace();
            System.exit(1);
        }
    }

    public static BigInteger recoverSecret(BigInteger p, BigInteger g,
                                           BigInteger y, int year, int month, int day, int hour, int minute,
                                           int second, BigInteger c1, BigInteger c2) {


        BigInteger gRand = BigInteger.valueOf(0); // efter loopen stora X
        BigInteger randomNumber = BigInteger.valueOf(0); // lilla x


        for (int i = 0; i < milliseconds; i++) {
            randomNumber = createRandomNumber(year, month, day, hour, minute, second, i);

            gRand = g.modPow(randomNumber, p);

            if (Objects.equals(c1, gRand)) {
                System.out.println("Found value");
                break;
            }
        }


        BigInteger yInv = y.modInverse(p);

        BigInteger k = yInv.modPow(randomNumber, p);

        BigInteger plaintext = k.multiply(c2).mod(p);


//        BigInteger k = g.modPow(randomNumber.multiply(y), p);
//
//        BigInteger kInv = k.modInverse(p);
//
//        c2 = c2.multiply(kInv); // need to get this modulo p
//
//        c2 = c2.mod(p);

//        c2 = BigInteger.valueOf(c2.longValue() % p.longValue());


        return plaintext;
    }

    public static BigInteger createRandomNumber(int year, int month, int day, int hour, int minute,
                                                int second, int millisecs) {

        BigInteger rBint = BigInteger.valueOf((long) (year * Math.pow(10, 10)));
        rBint = rBint.add(BigInteger.valueOf((long) (month * Math.pow(10, 8))));
        rBint = rBint.add(BigInteger.valueOf((long) (day * Math.pow(10, 6))));
        rBint = rBint.add(BigInteger.valueOf((long) (hour * Math.pow(10, 4))));
        rBint = rBint.add(BigInteger.valueOf((long) (minute * Math.pow(10, 2))));
        rBint = rBint.add(BigInteger.valueOf(second));
        rBint = rBint.add(BigInteger.valueOf(millisecs));

        return rBint;
    }


}
