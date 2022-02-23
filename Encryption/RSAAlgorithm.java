package rsa;

import java.util.Scanner;

public class RSAAlgorithm {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter text you want to encrypt: ");
		String el = sc.nextLine();
		sc.close();
		
		
		int p = 3, q = 11; // p and q are arbitrary, these are suggested values
		int phi = (p - 1) * (q - 1);
		int modulus = p * q;
		int lock = 0, key = 0;
		boolean found = true;
		
		
		for(int i = 2; i < phi; i++) { // loop for public key generation, where key and phi must be coprime
			for(int j = 2; j <= i; j++) {
				if((i % j == 0) && (phi % j == 0)) {
					found = false;
					break;
				}
			}
			if(found) {
				lock = i;
				break;
			}
			found = true;
		}
		
		
		for(int i = 1; ; i++) { // loop for private key generation
			if((i * lock) % phi == 1) {
				key = i;
				break;
			}
		}
			
		System.out.println(lock + " " + key);
		
		String[] stringArray = el.split(" "); // enables encryption/decryption of multiple words in single string of text

		
		for(String st : stringArray) {
			char[] array = st.toCharArray();
			
			int[] array2 = new int[array.length];
			
			for(int i = 0; i < array.length; i++) {
				array2[i] = (int) array[i] - 68; // 68 chosen as optimal number for encryption of A-Z characters, at the expense of encryption
			}                                        // possibilities of other characters
			
			
			int[] array3 = new int[array2.length];
			
			for(int i = 0; i < array2.length; i++) {   // encryption
				array3[i] = (int) ((Math.pow(array2[i], lock)) % modulus); 
				array[i] = (char) (array3[i] + 68);
				System.out.print(array[i]);
			}
			System.out.println("    Encrypted text");
			
			
			for(int i = 0; i < array3.length; i++) {   // decryption
				array[i] = (char) (((Math.pow(array3[i], key)) % modulus) + 68);
				System.out.print(array[i]);
			}
			System.out.println("    Decrypted text");
		}
		
	}
	
}