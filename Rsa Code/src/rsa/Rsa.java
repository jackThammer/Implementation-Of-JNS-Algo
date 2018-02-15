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
    public long ETime;
    public long DTime;
     public long KTime;
  //  private int blocksize = 256; //blocksize in byte 
     
    private Random r; 
     public Rsa() { 
          long startTime = System.currentTimeMillis();
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
 
  long stopTime = System.currentTimeMillis();
       KTime = stopTime - startTime;
      System.out.println(KTime);
    } 
     
    public Rsa(BigInteger e, BigInteger d, BigInteger N) { 
        this.e = e; 
        this.d = d; 
        this.N = N; 
    } 
     
    public String[] Run1 (String args) throws IOException
{ 
        Rsa rsa = new Rsa(); 
         
        String teststring ;
        
        teststring=args;
       
        // encrypt 
        long startTime = System.currentTimeMillis();
        byte[] encrypted = rsa.encrypt(teststring.getBytes());                   
         long stopTime = System.currentTimeMillis();
        long ETime = stopTime - startTime;
      System.out.println(ETime);
      
      String t=Long.toString(ETime);
      String e=bytesToString(encrypted);
      String[] enc=new String[5];
       enc[0] = e;
        enc[1] = t;
        
        
 startTime = System.currentTimeMillis();
        byte[] decrypted = rsa.decrypt(encrypted);       
        stopTime = System.currentTimeMillis();
         long DTime = stopTime - startTime;
         
          String t1=Long.toString(DTime);
      String d=new String(decrypted);
     // String[] dec=new String[2];
       enc[2] = d;
        enc[3] = t1;
         String t2=Long.toString(KTime);
         enc[4]=t2;
      System.out.println(DTime);
         
         return enc;
    } 
/* public String[] Run2 (String args) throws IOException
{ 
        Rsa rsa = new Rsa(); 
         
        String teststring ;
        
        teststring=args;
       
        // encrypt 
        byte[] encrypted = rsa.encrypt(teststring.getBytes());                   
      
         
        // decrypt 
        long startTime = System.currentTimeMillis();
        byte[] decrypted = rsa.decrypt(encrypted);       
         long stopTime = System.currentTimeMillis();
         long DTime = stopTime - startTime;
         
          String t=Long.toString(DTime);
      String d=new String(decrypted);
      String[] dec=new String[2];
       dec[0] = d;
        dec[1] = t;
         
      System.out.println(DTime);
         return (dec);
    } */
 

 
   private static String bytesToString(byte[] encrypted) { 
        String test = ""; 
        for (byte b : encrypted) { 
            test += Byte.toString(b); 
        } 
        return test; 
    } 
     
 //Encrypt message
     public byte[] encrypt(byte[] message) {   
         long startTime = System.currentTimeMillis();
        byte[] x=  (new BigInteger(message).modPow(e, N)).toByteArray();
        long stopTime = System.currentTimeMillis();
        long ETime = stopTime - startTime;
      System.out.println(ETime);
        return (x); 
         
    } 
     
     
     
     
// Decrypt message
    public byte[] decrypt(byte[] message) { 
        long startTime = System.currentTimeMillis();
        byte[] g=(new BigInteger(message)).modPow(d, N).toByteArray();
         long stopTime = System.currentTimeMillis();
         long DTime = stopTime - startTime;
      System.out.println(DTime);
     
        return g ; 
        
    }  
}
