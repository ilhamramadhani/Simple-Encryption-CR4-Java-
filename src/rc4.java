/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author IlhamRamadhani
 */
import java.io.*;

class rc4 {

    public static void main(String args[]) throws IOException {
        int temp = 0;
        DataInputStream in = new DataInputStream(System.in);
        String plaintext;
        String key;
        int s[] = new int[256]; //State
        int k[] = new int[256]; //Key

        System.out.print("(Insert Plain Text)Masukan Plaintext : ");
        plaintext = in.readLine();
        System.out.print("(Insert Key )Masukan Key : ");
        key = in.readLine();
        char plaintextc[] = plaintext.toCharArray(); //Diaraykan
        char keyc[] = key.toCharArray();
        //inisialisasi array
        int cipher[] = new int[plaintext.length()];
        int decrypt[] = new int[plaintext.length()];
        int plaintexti[] = new int[plaintext.length()];
        int keyi[] = new int[key.length()];
        //inisialisasi plaintext
        for (int i = 0; i < plaintext.length(); i++) {
            plaintexti[i] = (int) plaintextc[i];
        }
        //inisialisasi vektor
        for (int i = 0; i < key.length(); i++) {
            keyi[i] = (int) keyc[i];
        }
        for (int i = 0; i < 255; i++) {
            s[i] = i;
            k[i] = keyi[i % key.length()];
        }
        //KSA - pseudo code
        int j = 0;
        for (int i = 0; i < 255; i++) {
            j = (j + s[i] + k[i]) % 256;
            temp = s[i]; //swap
            s[i] = s[j];//swap
            s[j] = temp;//swap
        }
        //PRGA - pseudocode
        int i = 0;
        j = 0;
        int z = 0;
        for (int l = 0; l < plaintext.length(); l++) {
            i = (l + 1) % 256;
            j = (j + s[i]) % 256;
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            z = s[(s[i] + s[j]) % 256];
            cipher[l] = z ^ plaintexti[l];
            decrypt[l] = z ^ cipher[l];
        }
        System.out.print("\nHasil Enkripsi (Encrypted) : ");
        konversi(cipher);
        System.out.print("\nHasil Dekripsi (Decrypted): ");
        konversi(decrypt);
    }

    static void konversi(int nilaidata[]) {
        char convert[] = new char[nilaidata.length];
        for (int l = 0; l < nilaidata.length; l++) {
            convert[l] = (char) nilaidata[l];
            System.out.print(convert[l]);
        }
    }
}
