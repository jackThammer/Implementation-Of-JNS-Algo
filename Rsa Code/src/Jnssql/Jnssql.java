/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Jnssql;




import java.io.*;
import java.math.BigInteger;
import java.util.*;



public class Jnssql {

       private BigInteger N; 

    private BigInteger e; 
    private BigInteger d; 
     public long ETime;
    public long DTime;
    public Jnssql()
    {
        
    }
  public Jnssql(String n)
  {
       DBConnect connect=new DBConnect();   // TODO code application logic here
  //  connect.getData1();
   // connect.getData2();
      String x=n;
    N=new BigInteger(connect.search(x));
       
// N=new BigInteger(connect.searchN(120));
   System.out.println("N="+N);
   
     e=new BigInteger(connect.searchE(x));
     System.out.println("E="+e);
   d=new BigInteger(connect.searchD(x));
    System.out.println("D="+d);
  }
  
   public Jnssql(BigInteger e, BigInteger d, BigInteger N) { 
        this.e = e; 
        this.d = d; 
        this.N = N; 
    } 
  /*  public static void main(String[] args)throws IOException 
    {
     Jnssql j=new Jnssql();
     DataInputStream in=new DataInputStream(System.in);  
      /*  System.out.println("====Security Level====");
        System.out.println("Level 1 = 512 bits");
        System.out.println("Level 2 = 1024 bits");
        System.out.println("Level 3 = 2048 bits");
        System.out.println("Level 4 = 4096 bits");
        System.out.println("Enter the Security Level::");
          int  i =Integer.parseInt(in.readLine());
       
       
        String teststring ;
        System.out.println("Enter an alphabet:");
        teststring=in.readLine();
        System.out.println("Encrypting String: " + teststring); 
        System.out.println("String in Bytes: " + bytesToString(teststring.getBytes())); 

        // encrypt 
        byte[] encrypted = j.encrypt(teststring.getBytes());                   
        System.out.println("Encrypted String in Bytes: " + bytesToString(encrypted)); 
         
        // decrypt 
        byte[] decrypted = j.decrypt(encrypted);       
        System.out.println("Decrypted String in Bytes: " +  bytesToString(decrypted)); 
         
        System.out.println("Decrypted String: " + new String(decrypted)); 
       
       
     }*/
    
     public String[] Run1 (String args,String n) throws IOException
     {
           Jnssql j=new Jnssql(n);
          String teststring ;
          
          teststring=args;
       
        // encrypt 
        long startTime = System.currentTimeMillis();
        byte[] encrypted = j.encrypt(teststring.getBytes());                   
         long stopTime = System.currentTimeMillis();
        long ETime = stopTime - startTime;
      System.out.println(ETime);
      
      String t=Long.toString(ETime);
      String e=bytesToString(encrypted);
      String[] enc=new String[4];
       enc[0] = e;
        enc[1] = t;

        //decrypt
         startTime = System.currentTimeMillis();
        byte[] decrypted =j.decrypt(encrypted);       
          stopTime = System.currentTimeMillis();
         long DTime = stopTime - startTime;
         
          String t1=Long.toString(DTime);
      String d=new String(decrypted);
      String[] dec=new String[2];
       enc[2] = d;
        enc[3] = t1;
         
      System.out.println(DTime);
         return enc;
          
          
     }
    public String[] Run2 (String args,String n) throws IOException
{ 
      Jnssql j=new Jnssql(n);
         
        String teststring ;
        
        teststring=args;
       
        // encrypt 
        byte[] encrypted = j.encrypt(teststring.getBytes());                   
      
         
        // decrypt 
       long startTime = System.currentTimeMillis();
        byte[] decrypted =j.decrypt(encrypted);       
         long stopTime = System.currentTimeMillis();
         long DTime = stopTime - startTime;
         
          String t=Long.toString(DTime);
      String d=new String(decrypted);
      String[] dec=new String[2];
       dec[0] = d;
        dec[1] = t;
         
      System.out.println(DTime);
         return (dec);
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
        long startTime = System.currentTimeMillis();
          byte g[]=(new BigInteger(message)).modPow(e, N).toByteArray();
      long stopTime = System.currentTimeMillis();
       ETime = stopTime - startTime;
     System.out.println(ETime);  
        return g ; 
    } 
       
// Decrypt message
    public byte[] decrypt(byte[] message) { 
         long startTime = System.currentTimeMillis();
         byte[] g=(new BigInteger(message)).modPow(d, N).toByteArray();
           long stopTime = System.currentTimeMillis();
        DTime = stopTime - startTime;
      System.out.println(DTime);  
        return g; 
    }  


}