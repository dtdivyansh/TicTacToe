import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.applet.*;
public class TicTacToe2 extends JFrame
{
	JButton bt[]=new JButton[9]; 
	JButton bc;
	Random random=new Random();
	JLabel la=new JLabel(new ImageIcon("images/t2.jpg"));
	JLabel msg=new JLabel("USER Turn...");
	JButton reset=new JButton("RESET");
	JPanel []pa=new JPanel[2];
	ImageIcon icon1=new ImageIcon(getClass().getResource("images/user1.png"));
	ImageIcon icon2=new ImageIcon(getClass().getResource("images/user2.png"));
	String player="";
	int g=0,count=0,flag=0,pc,flag2=1;
	boolean winner=false;
	int record[]=new int[9];
	public TicTacToe2()
	{
	  la.setLayout(null);
	  setSize(600,640);
	  la.setBounds(0,0,1000,700);
	  add(la);
	  setResizable(false);
	  msg.setForeground(Color.magenta);
	  setLocationRelativeTo(null);
	  setDefaultCloseOperation(3);
	  for(int f=0;f<9;f++)
	   record[f]=9;
	  addPanel();
	  setVisible(true);
	  
	}

	void addPanel()
	{
	  la.setLayout(null);
	  for(int i=0;i<2;i++)
	  {
	    pa[i]=new JPanel();
	    la.add(pa[i]);
	  }
	   pa[0].setBounds(190,20,250,60);
	   pa[1].setBounds(50,100,500,440);
	   pa[0].setBackground(Color.yellow);
	   msg.setFont(new Font("elephant",2,30));
	   pa[0].add(msg);
	   reset.setBounds(235,550,150,50);
	   reset.setMargin(new Insets(0,0,0,0));
	   reset.setFont(new Font("elephant",2,22));
	   la.add(reset);
	   reset.addActionListener(new resetListener());
	   reset.setEnabled(false);
	   addButton();
	}
	
	private void addButton()
	{
	  pa[1].setLayout(new GridLayout(3,3));
	  TicListener listener=new TicListener();
	  for(int i=0;i<9;i++)
	   {
	     bt[i]=new JButton();
	     bt[i].addActionListener(listener);
	     pa[1].add(bt[i]);
	   }
	}

	private void findWinner(ImageIcon img)
	{
	    if(bt[0].getIcon()==img && bt[1].getIcon()==img && bt[2].getIcon()==img)
		{
		  showWinner(0,1,2);
		}

	    if(bt[3].getIcon()==img && bt[4].getIcon()==img && bt[5].getIcon()==img)
		{
		  showWinner(3,4,5);
		}

	    if(bt[6].getIcon()==img && bt[7].getIcon()==img && bt[8].getIcon()==img)
		{
		  showWinner(6,7,8);
		}

	    if(bt[0].getIcon()==img && bt[3].getIcon()==img && bt[6].getIcon()==img)
		{
		  showWinner(0,3,6);
		}

	    if(bt[0].getIcon()==img && bt[4].getIcon()==img && bt[8].getIcon()==img)
		{
		  showWinner(0,4,8);
		}

	    if(bt[1].getIcon()==img && bt[4].getIcon()==img && bt[7].getIcon()==img)
		{
		  showWinner(1,4,7);
		}

	    if(bt[2].getIcon()==img && bt[5].getIcon()==img && bt[8].getIcon()==img)
		{
		  showWinner(5,8,2);
		}

	    if(bt[2].getIcon()==img && bt[4].getIcon()==img && bt[6].getIcon()==img)
		{
		  showWinner(6,4,2);
		}
	}

	private void showWinner(int i1,int i2,int i3)
	{
		  msg.setText(player+"  WON !!!!");
		  bt[i3].setBackground(Color.green);
	 	  bt[i1].setBackground(Color.green);
		  bt[i2].setBackground(Color.green);
		  winner=true;
		  reset.setEnabled(true);
		  flag2=1;
		  g=0;
		  flag=0;
			for(int f=0;f<9;f++)
	   		record[f]=9;
		  return;
	}

	class TicListener implements ActionListener
	{
	 	public void actionPerformed(ActionEvent evt)
		{
		  bc=(JButton)evt.getSource();
		  if(bc.getIcon()!=null || winner)
			return;
		  bc.setIcon(icon1);
	          msg.setText("PC Turn...");
		  player=" USER ";
		  g=1-g;
	 	  msg.setForeground(Color.blue);
		  findWinner(icon1);
		  for(int p=0;p<9;p++)
		    if(bt[p]==bc)
		     {
			record[p]=p;
			break;
		     }
		  PcTurn cturn=new PcTurn();
			if(g==1)
		    cturn.start();	
		count++;
		   if(count==9 && !winner)
		   {
		     msg.setForeground(Color.red);
		     msg.setText("MATCH TIE...");
		     reset.setEnabled(true);
		     flag2=1;
		     flag=0;
		     g=0;
			for(int f=0;f<9;f++)
	   		record[f]=9;
		   }
		}
	}

	private class PcTurn extends Thread
	{
		public void run()
		{
			
			try{
			if(g==1 && count!=9)
			{
			  Thread.sleep(500);
			 while(flag==0)
			 {
				pc=random.nextInt(9);
				for(int cc=0;cc<9;cc++)
		 		{
				  if(pc!=record[pc])
				  {
				   flag=1;
				   record[pc]=pc;
				   break;
				  }
				 
				}
			}
				if(flag==1 && flag2==1)
		  		{
					bt[pc].setIcon(icon2);
		   			msg.setText("USER Turn...");
		   			player=" PC ";
	 	   			msg.setForeground(Color.magenta);
		   			findWinner(icon2);
					count++;
					g=1-g;
					flag=0;
		     	        }  
			 
			}	
			}catch(Exception ex){}
			
		}
	}

	class resetListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
		  for(int i=0;i<9;i++)
		  {
	            bt[i].setIcon(null);
		    bt[i].setBackground(null);
		  }
		   reset.setEnabled(false);
		   msg.setText("USER Turn..");
		   msg.setForeground(Color.magenta);
		   count=0;
		   winner=false;
		   g=0;
		   for(int f=0;f<9;f++)
	   		record[f]=9;
		}
	}

	public static void main(String [] args)
	{
	  new TicTacToe2();
	  
	}
}