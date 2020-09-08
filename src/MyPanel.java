import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MyPanel extends JPanel{

	ServerForApp server;
	public static void addButton(String text,Container container)
	{
		JButton button=new JButton(text);
		button.setAlignmentX(Container.CENTER_ALIGNMENT);
		container.add(button);
	}
	
	public static void addLabel(String text,Container container)
	{
		JLabel label=new JLabel(text);
		label.setAlignmentX(Container.CENTER_ALIGNMENT);
		container.add(label);

	}
	public MyPanel(ServerForApp server)
	{
		
		//setam frame-ul in care lucram
		JFrame frame=new JFrame("Administrarea bazei de date");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(900,200);
		JPanel panel=new JPanel();
		panel.setSize(500,600);
		panel.setLocation(500, 600);
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.setLayout(new GridLayout(22,22,2,2));
		
		
		JLabel titlu=new JLabel("ADMINISTRAREA BAZEI DE DATE A FIRMEI DE AVOCATURA",SwingConstants.CENTER);
	//	titlu.setBorder((BorderFactory.createLineBorder(Color.BLACK , 2)));
		panel.add(titlu);
		//pentru prima interogare
		
		JLabel contrAsist=new JLabel("Contractele din anul 2019 cu numar de pagini intre numerele date de dumneavoastra",SwingConstants.CENTER);
		//contrAsist.setText("Contractele din anul 2019 cu numar de pagini intre numerele date de dumneavoastra");
		contrAsist.setBorder((BorderFactory.createLineBorder(Color.BLACK , 2)));
		panel.add(contrAsist);
		
		JTextField introdNrPag1=new JTextField("Introduceti numar pagini",SwingConstants.CENTER);
		JTextField introdNrPag2=new JTextField("Introduceti numar pagini",SwingConstants.CENTER);
		
		panel.add(introdNrPag1);
		panel.add(introdNrPag2);
		
		JButton buttonContrAsist=new JButton("Afisare contracte");
		buttonContrAsist.setBorder((BorderFactory.createLineBorder(Color.BLACK , 2)));
		buttonContrAsist.setBackground(Color.ORANGE);
		panel.add(buttonContrAsist);
		buttonContrAsist.addActionListener(new ActionListener()
				{
			JTable result=new JTable();
			@Override
			public void actionPerformed(ActionEvent e) {
			String number1=introdNrPag1.getText();
			String number2=introdNrPag2.getText();
			result=server.getContracteAsistenta(Integer.parseInt(number1),Integer.parseInt(number2));
			JFrame f2=new JFrame();
			//f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f2.setSize(600, 150);
			f2.setLocation(400,600);
			f2.add(result);
			f2.setVisible(true);
			}	
			});
		
		//a doua interogare
		
		JLabel contracteMunca=new JLabel("Contractele in ordine descrescatoare in functie de comision",SwingConstants.CENTER);
		contracteMunca.setBorder((BorderFactory.createLineBorder(Color.BLACK , 2)));
		panel.add(contracteMunca);
		
		JButton buttonContrMunca=new JButton("Afisare contracte de munca cu comisionul dorit in ordine descrescatoare"
				+ "a salariului de baza");
		JTextField introd2=new JTextField("Introduceti comision",SwingConstants.CENTER);
		
		introd2.setBorder((BorderFactory.createLineBorder(Color.BLACK,2)));
		
		panel.add(introd2);
		buttonContrMunca.setBorder((BorderFactory.createLineBorder(Color.BLACK , 2)));
		buttonContrMunca.setBackground(Color.ORANGE);
		panel.add(buttonContrMunca);
		buttonContrMunca.addActionListener(new ActionListener()
				{
			JTable result=new JTable();
			@Override
			public void actionPerformed(ActionEvent e) {
			String number=introd2.getText();
			result=server.getContracteMuncaInOrdine(Integer.parseInt(number));
			JFrame f2=new JFrame();
			//f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f2.setSize(600, 150);
			f2.setLocation(700,600);
			f2.add(result);
			f2.setVisible(true);
			}	
			});
		///adaug inca o interogare pt clientii avocatului cu nume Ionescu George
		
		JLabel	clientiiIG=new JLabel("Clientii avocatului Ionescu George",SwingConstants.CENTER);
		clientiiIG.setBorder((BorderFactory.createLineBorder(Color.BLACK , 2)));
		panel.add(clientiiIG);
		
		JButton buttonClienti=new JButton("Afisare clienti");
				
		buttonClienti.setBorder((BorderFactory.createLineBorder(Color.BLACK , 2)));
		buttonClienti.setBackground(Color.ORANGE);
		panel.add(buttonClienti);
		buttonClienti.addActionListener(new ActionListener()
				{
			JTable result=new JTable();
			@Override
			public void actionPerformed(ActionEvent e) {
			result=server.getClientiIonescuGeorge();
			JFrame f2=new JFrame();
			//f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f2.setSize(600, 150);
			f2.setLocation(1000,600);
			f2.add(result);
			f2.setVisible(true);
			}	
			});
		
		///interogare pt 4b)
		
		JLabel cJurid=new JLabel("Contracte juridice cu cel putin o rata achitata,cu id_r1 si id_r2 consecutive",SwingConstants.CENTER);
		
		cJurid.setBorder((BorderFactory.createLineBorder(Color.BLACK,2)));
		panel.add(cJurid);
		
		JButton buttonCJurid=new JButton("Afisare contracte");
		buttonCJurid.setBackground(Color.ORANGE);
		
		buttonCJurid.setBorder((BorderFactory.createLineBorder(Color.BLACK , 2)));
		panel.add(buttonCJurid);
		buttonCJurid.addActionListener(new ActionListener()
				{
			JTable result=new JTable();
			@Override
			public void actionPerformed(ActionEvent e) {
			result=server.getCJConsec();
			JFrame f2=new JFrame();
			//f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f2.setSize(600, 150);
			f2.setLocation(1200,600);
			f2.add(result);
			f2.setVisible(true);
			}	
			});
		
		///interogare 5a)
		
		JLabel cJurid2=new JLabel("Contracte juridice cu onorar mai mare decat onorarul contractului  cu id-ul dorit",SwingConstants.CENTER);
		
		cJurid2.setBorder((BorderFactory.createLineBorder(Color.BLACK,2)));
		panel.add(cJurid2);
		
		JButton buttonCJurid2=new JButton("Afisare contracte");
		buttonCJurid2.setBackground(Color.ORANGE);
		JTextField introd=new JTextField("Introduceti id",SwingConstants.CENTER);
		
		introd.setBorder((BorderFactory.createLineBorder(Color.BLACK,2)));
		
		panel.add(introd);
		
		buttonCJurid2.setBorder((BorderFactory.createLineBorder(Color.BLACK , 2)));
		panel.add(buttonCJurid2);
		buttonCJurid2.addActionListener(new ActionListener()
				{
			JTable result=new JTable();
			@Override
			public void actionPerformed(ActionEvent e) {
				
			String number=introd.getText();
			result=server.getCJcuID(Integer.parseInt(number));
			JFrame f2=new JFrame();
			JButton revenire=new JButton("Revenire");
			//f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f2.setSize(600, 150);
			f2.setLocation(1200,600);
			f2.add(result);
			
			f2.setVisible(true);
			}	
			});
		
		//avocatii cu un singur contract juridic
		
		JLabel avocatiUnCJ=new JLabel("Avocatii cu un singur contract juridic",SwingConstants.CENTER);
		
		avocatiUnCJ.setBorder((BorderFactory.createLineBorder(Color.BLACK,2)));
		panel.add(avocatiUnCJ);
		
		JButton buttonAvocatiUnCj=new JButton("Gasire avocati");
		buttonAvocatiUnCj.setBackground(Color.ORANGE);
		buttonAvocatiUnCj.setBorder((BorderFactory.createLineBorder(Color.BLACK , 2)));
		panel.add(buttonAvocatiUnCj);
		buttonAvocatiUnCj.addActionListener(new ActionListener()
				{
			JTable result=new JTable();
			@Override
			public void actionPerformed(ActionEvent e) {
			result=server.getAvocatUnSg();
			JFrame f2=new JFrame();
			//f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f2.setSize(600, 150);
			f2.setLocation(1200,600);
			f2.add(result);
			f2.setVisible(true);
			}	
			});
		
		JLabel mediaSal=new JLabel("Media salariului pe anul 2019",SwingConstants.CENTER);
		
		mediaSal.setBorder((BorderFactory.createLineBorder(Color.BLACK,2)));
		panel.add(mediaSal);
		
		JButton buttonMediaSal=new JButton("Afisare valoarea medie a salariului pentru fiecare angajat pe anul 2019");
		buttonMediaSal.setBackground(Color.ORANGE);
		buttonMediaSal.setBorder((BorderFactory.createLineBorder(Color.BLACK , 2)));
		panel.add(buttonMediaSal);
		buttonMediaSal.addActionListener(new ActionListener()
				{
			JTable result=new JTable();
			@Override
			public void actionPerformed(ActionEvent e) {
			result=server.getMediaSumaPe2019();
			JFrame f2=new JFrame();
			//f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f2.setSize(600, 150);
			f2.setLocation(1200,600);
			f2.add(result);
			f2.setVisible(true);
			}	
			});
		//A 8 A INTEROGARE
		
		JLabel cjNeachit=new JLabel("Contractele juridice neachitate in intregime",SwingConstants.CENTER);
		
		cjNeachit.setBorder((BorderFactory.createLineBorder(Color.BLACK,2)));
		panel.add(cjNeachit);
		
		JButton buttonCjNeachit=new JButton("Afisare contracte");
		buttonCjNeachit.setBackground(Color.ORANGE);
		buttonCjNeachit.setBorder((BorderFactory.createLineBorder(Color.BLACK , 2)));
		panel.add(buttonCjNeachit);
		buttonCjNeachit.addActionListener(new ActionListener()
				{
			JTable result=new JTable();
			@Override
			public void actionPerformed(ActionEvent e) {
			result=server.getIDCJRataNeachitata();
			JFrame f2=new JFrame();
			//f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//JPanel cont1=new JPanel();
		
			//JButton rev=new JButton("REVENIRE");
			
			
			f2.setSize(600, 150);
			f2.setLocation(1200,600);
			f2.add(result);
		
			
			f2.setVisible(true);
			}	
			});
		
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	public static void main(String[] args)
	{
		String url="jdbc:mysql://localhost/bazaDeDate";
		String uid="root";
		String pw="";
		ServerForApp server=new ServerForApp(url,uid,pw);
		MyPanel p1=new MyPanel(server);
		
	}
	
}
