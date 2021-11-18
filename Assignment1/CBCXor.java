package Assignment1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

public class CBCXor {

    public static void main(String[] args) {
        String filename = "Assignment1/input.txt";
        byte[] first_block = null;
        byte[] encrypted = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            first_block = br.readLine().getBytes();
            encrypted = DatatypeConverter.parseHexBinary(br.readLine());
            br.close();
        } catch (Exception err) {
            System.err.println("Error handling file.");
            err.printStackTrace();
            System.exit(1);
        }
        String m = recoverMessage(first_block, encrypted);
        System.out.println("Recovered message: " + m);
    }

    /**
     * Recover the encrypted message (CBC encrypted with XOR, block size = 12).
     *
     * @param first_block We know that this is the value of the first block of plain
     *                    text.
     * @param encrypted   The encrypted text, of the form IV | C0 | C1 | ... where each
     *                    block is 12 bytes long.
     */
    private static String recoverMessage(byte[] first_block, byte[] encrypted) {

        byte[] key = findKey(first_block, encrypted);

//        byte[] c0 = Arrays.copyOfRange(encrypted, 12, 24); seems to work
//        byte[] c1 = Arrays.copyOfRange(encrypted, 24, 36);
//        byte[] block = decryptBlock(c0, c1, key);

        byte[] cOld;
        byte[] cNew;

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 12; i < encrypted.length; i += 12) {
            cOld = Arrays.copyOfRange(encrypted, i, i + 12);
            cNew = Arrays.copyOfRange(encrypted, i + 12, i + 24); // i thought for sure this would cause an error, apparently not

            byte[] block = decryptBlock(cOld, cNew, key);

            for (int j = 0; j < block.length; j++) {
                if (((char) block[j]) == '0') { //TODO temp fix for random last block
                    return stringBuilder.toString();
                }
                stringBuilder.append((char) block[j]);
            }
        }


        return stringBuilder.toString();
    }

    private static byte[] findKey(byte[] m0, byte[] encrypted) {
        byte[] iv = Arrays.copyOfRange(encrypted, 0, 12); // get IV
        byte[] c0 = Arrays.copyOfRange(encrypted, 12, 24); // get first ciphertext

        byte[] key = new byte[12];

        for (int i = 0; i < iv.length; i++) { // max len(iv, c0, m0) == 12
            key[i] = (byte) (iv[i] ^ c0[i] ^ m0[i]); // xor is associative
        }

        return key;
    }

    private static byte[] decryptBlock(byte[] prev_block, byte[] block, byte[] key) {

        byte[] decryptedBlock = new byte[12];

        for (int i = 0; i < 12; i++) {
            decryptedBlock[i] = (byte) (prev_block[i] ^ block[i] ^ key[i]);
        }
        return decryptedBlock;
    }
}

