// RSAPublicKey: RSA public key

import java.math.*; // for BigInteger
public class RSAPublicKey { 
   public BigInteger n; // public modulus
   public BigInteger e = new BigInteger("3"); // encryption exponent
   public String userName; // attach name to each public/private key pair

   public RSAPublicKey(String name) {
	  userName = name;
   }

   // setN: to give n a value in case only have public key
   public void setN(BigInteger newN) {
	  n = newN;
   }

   // getN: provide n
   public BigInteger getN() {
	  return n;
   }

   // RSAEncrypt: just raise m to power e (3) mod n
   public BigInteger RSAEncrypt(BigInteger m) {
	  return m.modPow(e, n);
   }
}

// RSAPrivateKey: RSA private key
import java.math.*; // for BigInteger
import java.util.*; // for Random
public class RSAPrivateKey extends RSAPublicKey{ 
   private final BigInteger TWO = new BigInteger("2");
   private final BigInteger THREE = new BigInteger("3");

   private BigInteger p; // first prime
   private BigInteger q; // second prime
   private BigInteger d; // decryption exponent

   public RSAPrivateKey(int size, Random rnd, String name) 
  {
	  super(name); generateKeyPair(size, rnd);
   }

   public void generateKeyPair(int size, Random rnd) { // size = n in bits
	  // want sizes of primes close, but not too close. Here 10-20 bits apart.
	  int size1 = size/2;
	  int size2 = size1;
	  int offset1 = (int)(5.0*(rnd.nextDouble()) + 5.0);
	  int offset2 = -offset1;
	  if (rnd.nextDouble() < 0.5) {
		 offset1 = -offset1; offset2 = -offset2;
	  }
	  size1 += offset1; size2 += offset2;
	  // generate two random primes, so that p*q = n has size bits
	  BigInteger p1   = new BigInteger(size1, rnd); // random int
				 p	= nextPrime(p1);
	  BigInteger pM1  = p.subtract(BigInteger.ONE);
	  BigInteger q1   = new BigInteger(size2, rnd);
				 q	= nextPrime(q1);
	  BigInteger qM1  = q.subtract(BigInteger.ONE);
				 n	= p.multiply(q);
	  BigInteger phiN = pM1.multiply(qM1); // (p-1)*(q-1)
	  BigInteger e = THREE;
				 d	= e.modInverse(phiN);
   }

   // nextPrime: next prime p after x, with p-1 and 3 relatively prime
   public BigInteger nextPrime(BigInteger x) {
	  if ((x.remainder(TWO)).equals(BigInteger.ZERO))
		 x = x.add(BigInteger.ONE);
	  while(true) {
		 BigInteger xM1 = x.subtract(BigInteger.ONE);
		 if (!(xM1.remainder(THREE)).equals(BigInteger.ZERO))
			if (x.isProbablePrime(10)) break;
		 x = x.add(TWO);
	  }
	  return x;
   }

   // RSADecrypt: decryption function
   public BigInteger RSADecrypt(BigInteger c) 
{
	  return c.modPow(d, n);
   }
}

// RSATest: Test RSA Implementation
import java.math.*; // for BigInteger
import java.util.*; // for Random
public class RSATest { 

   public static void main(String[] args) {
	  Random rnd = new Random();
	  BigInteger m, m1, m2, m3, c, s, s1;
	  RSAPrivateKey alice = new RSAPrivateKey(1024, rnd, "Alice");
	  RSAPrivateKey bob   = new RSAPrivateKey(1024, rnd, "Bob  ");
	  m = new BigInteger(
		"1234567890987654321012345678909876543210" + 
		"1234567890987654321012345678909876543210" +
		"1234567890987654321012345678909876543210" + 
		"1234567890987654321012345678909876543210" +
		"1234567890987654321012345678909876543210" + 
		"1234567890987654321012345678909876543210");
	  System.out.println("Message m:\n" + m + "\n");
	  System.out.println("ALICE ENCRYPTS m FOR BOB; BOB DECRYPTS IT:");
	  c = bob.RSAEncrypt(m); // Using Bob's public key
	  System.out.println("Message encrypted with Bob's public key:\n" +
		 c + "\n");
	  m1 = bob.RSADecrypt(c); // Using Bob's private key
	  System.out.println("Original message back, decrypted:\n" + m1 + "\n");
  }
}
