import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ServerForApp {
	String url;
	String uid;
	String pw;
	private static final Connection NULL = null;
	public ServerForApp(String url,String uid,String pw)
	{
		this.url=url;
		this.uid=uid;
		this.pw=pw;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			}
		catch(java.lang.ClassNotFoundException e)
		{
			System.out.println("Class not found exception" +e);
			
		}
		
	}
	
	
	
	
	public JTable getTable(String[][] data,String[] numeCol)
	{
		JTable j=new JTable(data,numeCol);
		return j;
	}
	
	
	
	//PRIMA INTEROGARE
	
	public JTable getContracteAsistenta(int num1,int num2)
	{
			JTable result=new JTable();
			Connection con=NULL;
		
		try {
			con=DriverManager.getConnection(url,uid,pw);
			String sql="call new_procedure(?,?)";
			CallableStatement st=con.prepareCall(sql);
			
			st.setInt(1, num1);
			st.setInt(2, num2);
			
			ResultSet r=st.executeQuery(); 
			
			int lastIdCj=-1;
			String lastDate="x";
			String lastObj="x";
			int lastOnorar=-1;
			int lastNrPag=-1;
			int lastIdClient=-1;
			int lastIdAvocat=-1;
			int lastTVA=-1;
			int i=1;
			String numeCol[]= {"id_cj","data1","obiect","onorar","nr_pagini","id_client","id_vocat","TVA"};
			String[][] data=new String[6][9];
			data[0][0]="id_cj";
			data[0][1]="data1";
			data[0][2]="obiect";
			data[0][3]="onorar";
			data[0][4]="nr_pagini";
			data[0][5]="id_client";
			data[0][6]="id_avocat";
			data[0][7]="TVA";
			
			while(r.next())
			{
				int currentIdCj=r.getInt(1);
				String currentDate=r.getString(2);
				String currentObiect=r.getString(3);
				int currentOnorar=r.getInt(4);
				int currentNrPag=r.getInt(5);
				int currentIdClient=r.getInt(6);
				int currentIdAvocat=r.getInt(7);
				int currentTVA=r.getInt(8);
				if (currentIdCj!=lastIdCj && currentDate!=lastDate && currentObiect!=lastObj && currentOnorar!=lastOnorar
					&& currentNrPag!=lastNrPag && currentIdClient!=lastIdClient && currentIdAvocat!=lastIdAvocat
					&& currentTVA!=lastTVA)
					{
					//System.out.println(currentIdCj+" "+currentDate+" "+currentObiect+" "+currentOnorar+" "
					//+currentNrPag+" "+currentIdClient+" "+currentIdAvocat+" "+currentTVA);
					
					
							data[i][0]=Integer.toString(currentIdCj);
							data[i][1]=currentDate;
							data[i][2]=currentObiect;
							data[i][3]=Integer.toString(currentOnorar);
							data[i][4]=Integer.toString(currentNrPag);
							data[i][5]=Integer.toString(currentIdClient);
							data[i][6]=Integer.toString(currentIdAvocat);
							data[i][7]=Integer.toString(currentTVA);
							i++;
					}
				
				}
				result=this.getTable(data, numeCol);
			
			}
		catch (SQLException e)
		{ 
			System.out.println("SQLException" + e);
		}
		return result;
	}
	
	
	//A 2 A INTEROGARE
	
	public JTable getContracteMuncaInOrdine(int num)
	{
			JTable result=new JTable();
			Connection con=NULL;
		
		try {
			con=DriverManager.getConnection(url,uid,pw);
			
			
			
			String sql="call new_procedure2(?)";
			CallableStatement st=con.prepareCall(sql);
			st.setInt(1, num);
			ResultSet r=st.executeQuery();
			int lastIdCm=-1;
			String lastDate="x";
			String lastFct="x";
			int lastSalar=-1;
			int lastComision=-1;
			int lastIdAngajat=-1;
			String lastTelNum="x";
			int i=1;
			String numeCol[]= {"id_cm","data1","functie","salar_baza","comision","id_angajat","nr_telefon"};
			String[][] data=new String[7][8];
			data[0][0]="id_cm";
			data[0][1]="data1";
			data[0][2]="functie";
			data[0][3]="salar_baza";
			data[0][4]="comision";
			data[0][5]="id_angajat";
			data[0][6]="nr_telefon";
			
			
			while(r.next())
			{
				int currentIdCm=r.getInt(1);
				String currentDate=r.getString(2);
				String currentFct=r.getString(3);
				int currentSalar=r.getInt(4);
				int currentComision=r.getInt(5);
				int currentIdAngajat=r.getInt(6);
				String currentNrTel=r.getString(7);
			
				if (currentIdCm!=lastIdCm && currentDate!=lastDate && currentFct!=lastFct && currentSalar!=lastSalar
					&& currentComision!=lastComision && currentIdAngajat!=lastIdAngajat && currentNrTel!=lastTelNum
				)
					{
					//System.out.println(currentIdCj+" "+currentDate+" "+currentObiect+" "+currentOnorar+" "
					//+currentNrPag+" "+currentIdClient+" "+currentIdAvocat+" "+currentTVA);
					
					
							data[i][0]=Integer.toString(currentIdCm);
							data[i][1]=currentDate;
							data[i][2]=currentFct;
							data[i][3]=Integer.toString(currentSalar);
							data[i][4]=Integer.toString(currentComision);
							data[i][5]=Integer.toString(currentIdAngajat);
							data[i][6]=currentNrTel;
							i++;
					}
				
				}
				result=this.getTable(data, numeCol);
			
			}
		catch (SQLException e)
		{ 
			System.out.println("SQLException" + e);
		}
		return result;
	}
	
	
	//A 3 A INTEROGARE
	
	public JTable getClientiIonescuGeorge()
	{
			JTable result=new JTable();
			Connection con=NULL;
		
		try {
			con=DriverManager.getConnection(url,uid,pw);
			Statement st=con.createStatement();
			
			
			String sql="select p.nume from Persoana p inner join Contract_j c on"
					+ " p.id_p=c.id_client and"
					+ " c.id_avocat=(select id_p from Persoana "
					+ "where nume='Ionescu George');";
			
			ResultSet r=st.executeQuery(sql);
			
			String lastNume="x";
			int i=1;
			String numeCol[]= {"Nume clienti "};
			String[][] data=new String[4][1];
			data[0][0]="Nume clienti";
			
			
			while(r.next())
			{
				String currentNume=r.getString(1);
				if (currentNume!=lastNume)
					{
					//System.out.println(currentIdCj+" "+currentDate+" "+currentObiect+" "+currentOnorar+" "
					//+currentNrPag+" "+currentIdClient+" "+currentIdAvocat+" "+currentTVA);
					
					
							data[i][0]=currentNume;
							i++;
					}
				
				}
				result=this.getTable(data, numeCol);
			
			}
		catch (SQLException e)
		{ 
			System.out.println("SQLException" + e);
		}
		return result;
	}
	
	
	
	//A 4 A INTEROGARE
	
	public JTable getCJConsec()
	{
			JTable result=new JTable();
			Connection con=NULL;
		
		try {
			con=DriverManager.getConnection(url,uid,pw);
			Statement st=con.createStatement();
			
			
			String sql="select a.id_cj, a.id_r, a.data1, a.suma, b.id_r,b.data1, b.suma\r\n " + 
					"from Rata a join Rata b on (a.id_cj=b.id_cj)\r\n " + 
					"where (a.id_r+1)=b.id_r and mod(a.id_r,2)=1;\r\n" + 
					"";
			
			ResultSet r=st.executeQuery(sql);
			
			int lastIdCj=-1;
			int lastIdR1=-1;
			int lastIdR2=-1;
			String lastData1="x";
			String lastData2="x";
			int lastSuma1=-1;
			int lastSuma2=-1;
			int i=1;
			String numeCol[]= {"id_cj","id_r1","dataA","suma1","id_r2","dataB","suma2"};
			String[][] data=new String[3][8];
			data[0][0]="id_cj";
			data[0][1]="id_r1";
			data[0][2]="data1";
			data[0][3]="suma1";
			data[0][4]="id_r2";
			data[0][5]="data2";
			data[0][6]="suma2";
			
			while(r.next())
			{
				int currentIdCj=r.getInt(1);
				int currentIdR1=r.getInt(2);
				String currentDate=r.getString(3);
				int currentSuma=r.getInt(4);
				int currentIdR2=r.getInt(5);
				String currentDate2=r.getString(6);
				int currentSuma2=r.getInt(7);
				if (currentIdCj!=lastIdCj && currentIdR1!=lastIdR1 && currentDate!=lastData1 && currentSuma!=lastSuma1
					&& currentIdR2!=lastIdR2 && currentDate2!=lastData2 && currentSuma2!=lastSuma2)
					{
					//System.out.println(currentIdCj+" "+currentDate+" "+currentObiect+" "+currentOnorar+" "
					//+currentNrPag+" "+currentIdClient+" "+currentIdAvocat+" "+currentTVA);
							data[i][0]=Integer.toString(currentIdCj);
							data[i][1]=Integer.toString(currentIdR1);
							data[i][2]=currentDate;
							data[i][3]=Integer.toString(currentSuma);
							data[i][4]=Integer.toString(currentIdR2);
							data[i][5]=currentDate2;
							data[i][6]=Integer.toString(currentSuma2);
							i++;
					}
				
				}
				result=this.getTable(data, numeCol);
			
			}
		catch (SQLException e)
		{ 
			System.out.println("SQLException" + e);
		}
		return result;
	}
	
	///A 5 A INTEROGARE
	public JTable getCJcuID(int num)
	{
		Connection con=NULL;
		JTable result=new JTable();
		try {
			con=DriverManager.getConnection(url,uid,pw);	
			
			String sql="call new_procedure3(?)";
			CallableStatement ptsm=con.prepareCall(sql);
			ptsm.setInt(1, num);
			
			
			String numeCol[]= {"id_cj","data1","obiect","onorar","nr_pagini","id_client","id_avocat","TVA"};
			
			String[][] data=new String[9][8];
			
			data[0][0]="id_cj";
			data[0][1]="data1";
			data[0][2]="obiect";
			data[0][3]="onorar";
			data[0][4]="nr_pagini";
			data[0][5]="id_client";
			data[0][6]="id_avocat";
			data[0][7]="TVA";
			
			
			ResultSet r=ptsm.executeQuery();
			int lastIdCj=-1;
			String lastDate="x";
			String lastObj="x";
			int lastOnorar=-1;
			int lastNrPag=-1;
			int lastIdClient=-1;
			int lastIdAvocat=-1;
			int lastTVA=-1;
			int i=1;
			while(r.next())
			{
				int currentIdCj=r.getInt(1);
				String currentDate=r.getString(2);
				String currentObiect=r.getString(3);
				int currentOnorar=r.getInt(4);
				int currentNrPag=r.getInt(5);
				int currentIdClient=r.getInt(6);
				int currentIdAvocat=r.getInt(7);
				int currentTVA=r.getInt(8);
				if (currentIdCj!=lastIdCj && currentDate!=lastDate && currentObiect!=lastObj && currentOnorar!=lastOnorar
						&& currentNrPag!=lastNrPag && currentIdClient!=lastIdClient && currentIdAvocat!=lastIdAvocat
						&& currentTVA!=lastTVA)
						{
					//	System.out.println(currentIdCj+" "+currentDate+" "+currentObiect+" "+currentOnorar+" "
					//	+currentNrPag+" "+currentIdClient+" "+currentIdAvocat+" "+currentTVA);
						data[i][0]=Integer.toString(currentIdCj);
						data[i][1]=currentDate;
						data[i][2]=currentObiect;
						data[i][3]=Integer.toString(currentOnorar);
						data[i][4]=Integer.toString(currentNrPag);
						data[i][5]=Integer.toString(currentIdClient);
						data[i][6]=Integer.toString(currentIdAvocat);
						data[i][7]=Integer.toString(currentTVA);
						i++;
						}
					
					}
			result=this.getTable(data, numeCol);
				
			}
		catch (SQLException e)
		{ 
			System.out.println("SQLException" + e);
		}
		return result;
	}
	
	//A 6 A INTEROGARE
	
	public JTable getAvocatUnSg()
	{
			JTable result=new JTable();
			Connection con=NULL;
		
		try {
			con=DriverManager.getConnection(url,uid,pw);
			Statement st=con.createStatement();
			
			
			String sql="select p.nume\r\n" + 
					"from Persoana p\r\n " + 
					"where id_p in(select id_avocat\r\n " + 
					"from Contract_j cj1\r\n " + 
					"where (select count(cj2.id_cj)\r\n " + 
					"from Contract_j cj2\r\n " + 
					"where cj1.id_avocat = cj2.id_avocat)=1)";
			
			
			ResultSet r=st.executeQuery(sql);
			
			String lastNume="x";
			int i=1;
			String numeCol[]= {"Nume avocat"};
			String[][] data=new String[2][1];
			data[0][0]="Nume avocat";
			
			
			while(r.next())
			{
				String currentNume=r.getString(1);
				if (currentNume!=lastNume)
					{
					//System.out.println(currentIdCj+" "+currentDate+" "+currentObiect+" "+currentOnorar+" "
					//+currentNrPag+" "+currentIdClient+" "+currentIdAvocat+" "+currentTVA);
					
					
							data[i][0]=currentNume;
							i++;
					}
				
				}
				result=this.getTable(data, numeCol);
			
			}
		catch (SQLException e)
		{ 
			System.out.println("SQLException" + e);
		}
		return result;
	}
	
	//A 7 A INTEROGARE
	
	public JTable getMediaSumaPe2019()
	{
			JTable result=new JTable();
			Connection con=NULL;
		
		try {
			con=DriverManager.getConnection(url,uid,pw);
			Statement st=con.createStatement();
			
			
			String sql="select p.nume,avg(c.salar_baza)\r\n " + 
					"from Contract_m c inner join Persoana p on\r\n " + 
					"p.id_p=c.id_angajat\r\n " + 
					"where data1>='2019-01-01' and data1<='2019-12-31'\r\n " + 
					"group by p.nume\r\n" + 
					"";
			
			
			ResultSet r=st.executeQuery(sql);
			
			String lastNume="x";
			int lastSuma=-1;
			int i=1;
			String numeCol[]= {"Nume angajat","Media salariului pe anul 2019"};
			String[][] data=new String[4][2];
			data[0][0]="Nume avocat";
			data[0][1]="Media salariului pe anul 2019";
			
			
			
			while(r.next())
			{
				String currentNume=r.getString(1);
				int currentSuma=r.getInt(2);
				if (currentNume!=lastNume && currentSuma!=lastSuma)
					{
					//System.out.println(currentIdCj+" "+currentDate+" "+currentObiect+" "+currentOnorar+" "
					//+currentNrPag+" "+currentIdClient+" "+currentIdAvocat+" "+currentTVA);
					
							data[i][0]=currentNume;
							data[i][1]=Integer.toString(currentSuma);
							i++;
					}
				
				}
				result=this.getTable(data, numeCol);
			
			}
		catch (SQLException e)
		{ 
			System.out.println("SQLException" + e);
		}
		return result;
	}
	
	//A 8 A INTEROGARE
	
	public JTable getIDCJRataNeachitata()
	{
			JTable result=new JTable();
			Connection con=NULL;
		
		try {
			con=DriverManager.getConnection(url,uid,pw);
			Statement st=con.createStatement();
			
			
			String sql="select distinct(c.id_cj)\r\n " + 
					"from Contract_j c inner join Rata r on c.id_cj=r.id_cj\r\n " + 
					"where (select sum(suma) from Rata where id_cj=c.id_cj)<c.onorar;";
			
			
			ResultSet r=st.executeQuery(sql);
			
			int lastIdCj=-1;
			
			int i=1;
			String numeCol[]= {"id_cj"};
			String[][] data=new String[2][1];
			data[0][0]="id_cj";
			
			
			while(r.next())
			{
				
				int currentIdCj=r.getInt(1);
				if (currentIdCj!=lastIdCj)
					{
					//System.out.println(currentIdCj+" "+currentDate+" "+currentObiect+" "+currentOnorar+" "
					//+currentNrPag+" "+currentIdClient+" "+currentIdAvocat+" "+currentTVA);
					
							data[i][0]=Integer.toString(currentIdCj);
							i++;
					}
				
				}
				result=this.getTable(data, numeCol);
			
			}
		catch (SQLException e)
		{ 
			System.out.println("SQLException" + e);
		}
		return result;
	}
	
	
		public static void main(String[] args)
		{
			String url="jdbc:mysql://localhost/bazaDeDate";
			String uid="root";
			String pw="";
			ServerForApp server=new ServerForApp(url,uid,pw);
		//	server.getContracteAsistenta();
			
		}
}
