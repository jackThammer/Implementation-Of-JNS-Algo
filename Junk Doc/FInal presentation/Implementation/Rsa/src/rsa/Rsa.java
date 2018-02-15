package Rsa;
import java.math.BigInteger;

import java.util.Random;
import java.io.*;
  
public class Rsa { 

    private BigInteger p; 
    private BigInteger q; 
    private BigInteger N; 
    private BigInteger phi; 
    private BigInteger e; 
    private BigInteger d; 
    private int bitlength = 1024; 
    private int blocksize = 256; //blocksize in byte 
     
    private Random r; 
     public Rsa() { 
        r = new Random(); 
        p = BigInteger.probablePrime(bitlength, r); 
        q = BigInteger.probablePrime(bitlength, r); 
        N = p.multiply(q); 
           
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); 
        e = BigInteger.probablePrime(bitlength/2, r); 
         
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0 ) { 
            e.add(BigInteger.ONE); 
        } 
 d = e.modInverse(phi);  
    } 
     
    public Rsa(BigInteger e, BigInteger d, BigInteger N) { 
        this.e = e; 
        this.d = d; 
        this.N = N; 
    } 
     
    public String Run1 (String args) throws IOException
{ 
        Rsa rsa = new Rsa(); 
         
        String teststring ;
        
        teststring=args;
       
        // encrypt 
        byte[] encrypted = rsa.encrypt(teststring.getBytes());                   
       
         return bytesToString(encrypted);
    } 
 public String Run2 (String args) throws IOException
{ 
        Rsa rsa = new Rsa(); 
         
        String teststring ;
        
        teststring=args;
       
        // encrypt 
        byte[] encrypted = rsa.encrypt(teststring.getBytes());                   
      
         
        // decrypt 
        byte[] decrypted = rsa.decrypt(encrypted);       
       
         return (new String(decrypted));
    } 
   private static String bytesToString(byte[] encrypted) { 
        String test = ""; 
        for (byte b : encrypted) { 
            test += Byte.toString(b); 
        } 
        return test; 
    } 
     
 //Encrypt message
     public byte[] encrypt(byte[] message) {      
        return (new BigInteger(message)).modPow(e, N).toByteArray(); 
    } 
       
// Decrypt message
    public byte[] decrypt(byte[] message) { 
        return (new BigInteger(message)).modPow(d, N).toByteArray(); 
    }  
}
