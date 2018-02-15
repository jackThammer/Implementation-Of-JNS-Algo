/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Jnssql;




import java.sql.*;


public class DBConnect {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public DBConnect()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con =DriverManager.getConnection("jdbc:mysql://localhost/db", "root", "");
            st= con.createStatement();
            
            
            
        }catch(Exception ex)
        {
            System.out.println("connection error"+ex);
        }
    }
/* 
public void getData1()
{
try{
    System.out.println("TABLE1");

CallableStatement cs;
cs=con.prepareCall("{call showdb1()}");

rs=cs.executeQuery();
while(rs.next()){
    String num1=rs.getString("Nid");
        String num3=rs.getString("p");
    String num4=rs.getString("q");
    String num5=rs.getString("n");
    String num6=rs.getString("phi");
    System.out.println("NID="+num1+" p="+num3+" q="+num4+" n="+num5+" phi="+num6);
}

}catch(SQLException se){}

}
public void getData2()
{
try{
    System.out.println("TABLE2");

CallableStatement cs;
cs=con.prepareCall("{call showdb2()}");

rs=cs.executeQuery();
while(rs.next()){
    String num1=rs.getString("phi");
        String num3=rs.getString("e");
    String num4=rs.getString("Eid");
    String num5=rs.getString("d");
    String num6=rs.getString("Did");
    System.out.println("phi="+num1+" e="+num3+" Eid="+num4+" d="+num5+" Did="+num6);
}

}catch(SQLException se){}

}

    /**
     *
     * @param a
     *//*
 public String searchN(int a)
{ String num5="000000000000";
try{
    System.out.println("SEARCHING");

CallableStatement cs;

cs=con.prepareCall("{call search1(a)}");

rs=cs.executeQuery();
while(rs.next()){
    String num1=rs.getString("Nid");
        String num3=rs.getString("p");
    String num4=rs.getString("q");
     num5=rs.getString("n");
    String num6=rs.getString("phi");
    System.out.println("NID="+num1+" p="+num3+" q="+num4+" n="+num5+" phi="+num6);
}

}catch(SQLException se){}
return(num5);
}*/

 public String searchE(String a)
{ String num8=null;
try{
    System.out.println("SEARCHING.....");

CallableStatement cs;

String z="{call searchful('"+a+"')}";
cs=con.prepareCall(z);
rs=cs.executeQuery();
while(rs.next()){
    String num1=rs.getString("Nid");
    String num3=rs.getString("p");
    String num4=rs.getString("q");
   String  num5=rs.getString("n");
    String num6=rs.getString("phi");
     String num7=rs.getString("phi");
    num8=rs.getString("e");
    String num9=rs.getString("Eid");
    String num10=rs.getString("d");
    String num11=rs.getString("Did");
   // System.out.println("NID="+num1+" p="+num3+" q="+num4+" n="+num5+" phi="+num6);
   // System.out.println("phi="+num7+" e="+num8+" Eid="+num9+" d="+num10+" Did="+num11);
}

}catch(SQLException se){}

return(num8);
}

  public String searchD(String a)
{ String num10=null;
try{
    System.out.println("SEARCHING.....");

CallableStatement cs;

String z="{call searchful('"+a+"')}";
cs=con.prepareCall(z);
rs=cs.executeQuery();
while(rs.next()){
    String num1=rs.getString("Nid");
    String num3=rs.getString("p");
    String num4=rs.getString("q");
    String num5=rs.getString("n");
    String num6=rs.getString("phi");
     String num7=rs.getString("phi");
    String num8=rs.getString("e");
    String num9=rs.getString("Eid");
     num10=rs.getString("d");
    String num11=rs.getString("Did");
  //  System.out.println("NID="+num1+" p="+num3+" q="+num4+" n="+num5+" phi="+num6);
    //System.out.println("phi="+num7+" e="+num8+" Eid="+num9+" d="+num10+" Did="+num11);
}

}catch(SQLException se){}

return(num10);
}
 
  
   public String search(String a)
{ String num5=null;
try{
    System.out.println("SEARCHING");

CallableStatement cs;
String z="{call searchful('"+a+"')}";
cs=con.prepareCall(z);

rs=cs.executeQuery();
while(rs.next()){
    String num1=rs.getString("Nid");
    String num3=rs.getString("p");
    String num4=rs.getString("q");
     num5=rs.getString("n");
    String num6=rs.getString("phi");
     String num7=rs.getString("phi");
    String num8=rs.getString("e");
    String num9=rs.getString("Eid");
    String num10=rs.getString("d");
    String num11=rs.getString("Did");
  //  System.out.println("NID="+num1+" p="+num3+" q="+num4+" n="+num5+" phi="+num6);
   // System.out.println("phi="+num7+" e="+num8+" Eid="+num9+" d="+num10+" Did="+num11);
}

}catch(SQLException se){}
return(num5);
}
  
  
  
  
  
}

