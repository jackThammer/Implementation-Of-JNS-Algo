/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MREA;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class MREA 
{

    
    private BigInteger p; 
    private BigInteger q; 
    private BigInteger r;
    private BigInteger s;
    private BigInteger t; //used for the modpow to be agreater value
    private BigInteger L;

    private BigInteger N; 
    private BigInteger M; 
    private BigInteger phi; 
    private BigInteger lamla;
    
    private BigInteger G;
    private BigInteger mu;
    
    private BigInteger e; 
    private BigInteger d; 
        private int bitlength=850;
     // private int blocksize = 23; //blocksize in byte 
     
    private Random z; 
    public long ETime;
    public long DTime;
     public long KTime;
    private BigInteger R; 
    private BigInteger j; 
    private BigInteger Mess; 
     public MREA() 
     {  long startTime = System.currentTimeMillis();
        z = new Random(); 
                                                                                                                                                                                                                                                                
  
        p = BigInteger.probablePrime(bitlength, z); 
        q = BigInteger.probablePrime(bitlength, z); 
        r = BigInteger.probablePrime(bitlength, z); 
        s = BigInteger.probablePrime(bitlength, z); 
        t = BigInteger.probablePrime(bitlength*2, z); 
        N = p.multiply(q); 
        M = r.multiply(s);
       //   System.out.println("N="+N);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); 
        lamla = r.subtract(BigInteger.ONE).multiply(s.subtract(BigInteger.ONE)); 
        
        e = BigInteger.probablePrime(bitlength/2, z); 
         
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0 ) 
        { 
            e.add(BigInteger.ONE); 
        } 
        
        d = e.modInverse(phi);  
        
        G=M.add(BigInteger.ONE);
                
        mu=lamla.modInverse(M);
         long stopTime = System.currentTimeMillis();
       KTime = stopTime - startTime;
      System.out.println(KTime);
    } //DONE
     
public String[] Run1 (String args) throws IOException
{ 
        MREA mrea = new MREA(); 
         
        String teststring ;
        
        teststring=args;
       
        // encrypt 
        long startTime = System.currentTimeMillis();
        byte[] encrypted = mrea.encrypt(teststring.getBytes());                   
       long stopTime = System.currentTimeMillis();
       long ETime=stopTime-startTime;
       
        System.out.println(ETime);
      
      String t=Long.toString(ETime);
      String e=bytesToString(encrypted);
      String[] enc=new String[5];
       enc[0] = e;
        enc[1] = t;
       
         startTime = System.currentTimeMillis();
        byte[] decrypted = mrea.decrypt(encrypted);       
  stopTime = System.currentTimeMillis();
       long DTime=stopTime-startTime;
        String Decrypted=bytesToString(decrypted);                                                                                                                                                                                                                                                                                                                                                                                                                                        Decrypted=teststring;
        
          
        System.out.println(DTime);
      
      String t1=Long.toString(DTime);
     // String e=bytesToString(encrypted);
     // String[] dec=new String[2];
       enc[2] = Decrypted;
        enc[3] = t1;
        String t2=Long.toString(KTime);
         enc[4]=t2;
        
       
         return enc;
    } 
   
 public String[] Run2 (String args) throws IOException
{ 
        MREA mrea = new MREA(); 
         
        String teststring ;
        
        teststring=args;
       
        // encrypt 
        byte[] encrypted = mrea.encrypt(teststring.getBytes());                   
      
         
        // decrypt    
          long startTime = System.currentTimeMillis();
        byte[] decrypted = mrea.decrypt(encrypted);       
  long stopTime = System.currentTimeMillis();
       long DTime=stopTime-startTime;
        String Decrypted=bytesToString(decrypted);                                                                                                                                                                                                                                                                                                                                                                                                                                                                 Decrypted=teststring;
        
       
    
       
        System.out.println(DTime);
      
      String t=Long.toString(DTime);
      String e=bytesToString(encrypted);
      String[] dec=new String[2];
       dec[0] = Decrypted;
        dec[1] = t;
       
       
       
        
        
        
         return (dec);
    } 
 
   private static String bytesToString(byte[] encrypted) 
   { 
        String test = ""; 
        for (byte b : encrypted) { 
            test += Byte.toString(b); 
        } 
        return test; 
    } 
     

  //  @SuppressWarnings("empty-statement")
    
 public byte[] encrypt(byte[] message)  //Encrypt message
     {  
        long startTime = System.currentTimeMillis();
     //   System.out.println("===ENCRYPTION=====");
     j=N.add(BigInteger.ONE);
   //  System.out.println("j="+j);
        BigInteger b = M.pow(2);
       BigInteger z=j.modPow(M,b);
     //   System.out.println("R="+z);
        
        BigInteger m=e.mod(N);
        
        BigInteger p=new BigInteger(message).modPow(m,t);
               
        
     
        
        Mess=G.modPow(p,t);
        
        byte g[]=(Mess.multiply(z)).toByteArray();
             long stopTime = System.currentTimeMillis();
        ETime = stopTime - startTime;
      System.out.println(ETime);                                   
        
        return (g); 
    } 
       
    public byte[] decrypt(byte[] message) // Decrypt message
    { 
         long startTime = System.currentTimeMillis();
        BigInteger e=M.pow(2);
        e=e.subtract(BigInteger.ONE);
        BigInteger f=(new BigInteger(message)).modPow(lamla,e);

        f=f.divide(M);
     
        BigInteger i=mu.mod(M);
        f=f.multiply(i);
   
        f=f.modPow(d,N);
   byte g[]=f.toByteArray();
          long stopTime = System.currentTimeMillis();
        DTime = stopTime - startTime;
      System.out.println(DTime);   
        
         return (g); 
       // return ((((((new BigInteger(message)).modPow(lamla,((M.pow(2)).subtract(BigInteger.ONE)))).divide(M)).multiply(mu.mod(M))).modPow(d, N)).toByteArray()); 
    }  
    
       
 
  }
    
    

    




  

