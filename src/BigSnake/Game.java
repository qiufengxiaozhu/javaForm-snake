package BigSnake;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

/**
 * @version 1.0
 * 
 * @since 2019/2/28
 * 
 * @author qiu
 */

public class Game extends JFrame{
	
	Map m = new Map();//��ͼ��

	JPanel main_panel = new JPanel();	//�����
	JPanel map_panel = new JPanel();	//��Ϸ���
	JPanel option_panel = new JPanel();	//ѡ�����
	JPanel text_panel = new JPanel();	//˵�����

	JLabel label1 = new JLabel("��ʾ��",JLabel.LEFT);	//�ٶ���ʾ��
	JLabel label2 = new JLabel("������",JLabel.LEFT);	//������ʾ��
	JLabel label3 = new JLabel("ѡ  ��:",JLabel.CENTER);	//ѡ����ʾ��
	JLabel label4 = new JLabel();	//װ��ͼ��
	
	JTextArea textArea= new JTextArea(); //��Ϸ˵��
	JScrollPane text = new JScrollPane(textArea);	//�����������ı���

	ImageIcon image1 = new ImageIcon("Ƥ����.PNG");	//ͼ��
	ImageIcon image2 = new ImageIcon("Ƥ����2.gif");	//ͼ��

	
	JButton button1 = new JButton("�ָ�Ĭ������");	//�ָ�Ĭ������
	JButton button2 = new JButton("���ñ�����ɫ");	//���ñ�����ɫ
	JButton button3 = new JButton("��ɫ");			//����������ɫ
	JButton button4 = new JButton("������ͷ��ɫ");	//������ͷ��ɫ
	JButton button5 = new JButton("����������ɫ");	//����������ɫ
	JButton button6 = new JButton("����ʳ����ɫ");	//����ʳ����ɫ
	JButton button7 = new JButton("������Ϸ");		//ֹͣ��Ϸ
	JButton button8 = new JButton("����/��ͣ");		//������Ϸ
	JButton button9 = new JButton("��ʼ����Ϸ");		//��ʼ����Ϸ
	
	static JCheckBox checkBox = new JCheckBox("��ʾ����",false);	//����һ����ѡ��,�Ƿ���ʾ����

	ButtonGroup buttonGroup = new ButtonGroup();	//����һ�鰴ť������һ��ֻ��ѡ��һ����ť��
	JRadioButton radioButton_map1 = new JRadioButton("��ͼ1");//����һ�����δѡ��ĵ�ѡ��ť,��ͼ1��
	JRadioButton radioButton_map2 = new JRadioButton("��ͼ2");//����һ�����δѡ��ĵ�ѡ��ť,��ͼ2��

	JSeparator separator1 = new JSeparator();	//�ָ������1-ˮƽ
	JSeparator separator2 = new JSeparator();	//�ָ������2-ˮƽ
	JSeparator separator3 = new JSeparator();	//�ָ������3-ˮƽ
	JSeparator separator4 = new JSeparator();	//�ָ������4-ˮƽ
	JSeparator separator5 = new JSeparator();	//�ָ������5-��ֱ
	JSeparator separator6 = new JSeparator();	//�ָ������6-��ֱ

	//ʹ���ĸ�������ʾ�ĸ�����
	public static final int UP_DIRECTION = 1;  	//��	
	public static final int DOWN_DIRECTION = -1;//��	
	public static final int LEFT_DIRECTION = 2; //��	
	public static final int RIGHT_DIRECTION =-2;//��
	
	//�ߵ�ǰ�ķ���
	public static int currentDirection = -2; // ��Ĭ������������
	//�ߵ�ǰ���ٶ�
	public int speed = 300;
	
	//����/��ͣ
	int flag = 1;
	//�л�ͼƬ
	public static int image = 1;
	
	//��ͼ����ɫ
	public static Color gridding1 = Color.WHITE;
	//�������ɫ
	public static Color gridding2 = Color.ORANGE;
	//��ͷ����ɫ
	public static Color gridding3 = Color.RED;
	//�������ɫ
	public static Color gridding4 = Color.GREEN;
	//�������ɫ
	public static Color gridding5 = Color.BLUE;
	
	//������ʱ��
	Timer time = new Timer();  //��ʱ��
	TimerTask timer1;	//�߳����˶�����
	
	public Game(String string, int i, int j, int k, int l) {
		// TODO Auto-generated constructor stub

		init();		//��ʼ�����
		timer();	//���ö�ʱ��
		play();		//ִ�в���
		option();	//����ѡ��		
		setTitle(string);
		setLocation(i,j);
		setSize(k,l);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setVisible(true);
		setResizable(false);
	}

	//��ʼ�����
	void init() {
		// TODO Auto-generated method stub
		
		/***************************������岼��**********************************/
		
		setLayout(null);
		main_panel.setLayout(null);	
		map_panel.setLayout(new GridLayout(1,0));	
		option_panel.setLayout(null);
		text_panel.setLayout(null);
		
		/*****************************��Ϸ���*************************************/		
		
		label1.setBounds(10, 10, 400, 25);	//�ٶ���ʾ��		
		label1.setForeground(Color.black);
		label1.setFont(new Font("����",Font.PLAIN, 20));
		
		label2.setBounds(875, 10, 200, 25);	//������ʾ��		
		label2.setForeground(Color.RED);
		label2.setFont(new Font("����",Font.PLAIN,20));
		
		map_panel.setBounds(12, 38, 1060,  458);	//��Ϸ���
		map_panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));	//���ñ�Ե�ľ���	
		map_panel.add(m);
		
		/*****************************ѡ�����************************************/
		
		label3.setBounds(20, 15, 150, 30);	//ѡ����ʾ��	
		label3.setForeground(Color.BLUE);
		label3.setFont(new Font("����",Font.BOLD,20));
		
		button1.setBounds(200, 15, 150, 30);	//�ָ�Ĭ�����ð�ť
		button1.setFont(new Font("����",Font.BOLD,15));
		button1.setFocusable(false);
		
		separator1.setBounds(20, 10, 380, 50);	/*    ˮƽ�ָ���1   */
		separator1.add(label3);
		separator1.add(button1);
		
		button2.setBounds(20, 15, 150, 30);	//���ñ�����ɫ��ť
		button2.setFont(new Font("����",Font.BOLD,15));
		button2.setFocusable(false);

		checkBox.setBounds(200, 15, 90, 30);//����һ����ѡ��,�Ƿ���ʾ����
		checkBox.setFont(new Font("����", Font.PLAIN, 15));
		checkBox.setFocusable(false);
		
		button3.setBounds(300, 15, 70, 30);	//����������ɫ��ť
		button3.setFont(new Font("����",Font.BOLD,15));
		button3.setFocusable(false);
		button3.setVisible(false);

		separator2.setBounds(20, 70, 380, 50);	/*    ˮƽ�ָ���2   */
		separator2.add(checkBox);
		separator2.add(button2);
		separator2.add(button3);
		
		button4.setBounds(20, 15, 150, 30);	//������ͷ��ɫ��ť
		button4.setFont(new Font("����",Font.BOLD,15));
		button4.setFocusable(false);
		
		button5.setBounds(200, 15, 150, 30);	//����������ɫ��ť
		button5.setFont(new Font("����",Font.BOLD,15));
		button5.setFocusable(false);
		
		separator3.setBounds(20, 130, 380, 50);	/*    ˮƽ�ָ���3   */
		separator3.add(button4);
		separator3.add(button5);
		
		button6.setBounds(20, 15, 150, 30);	//����ʳ����ɫ��ť
		button6.setFont(new Font("����",Font.BOLD,15));
		button6.setFocusable(false);
		
		radioButton_map1.setBounds(200, 15, 90, 30);//����һ����ѡ��ť,��ʼ����Ϊ��ͼ1
		radioButton_map1.setFont(new Font("����", Font.PLAIN, 15));
		radioButton_map1.setSelected(true);
		radioButton_map1.setFocusable(false);
		buttonGroup.add(radioButton_map1);
		
		radioButton_map2.setBounds(300, 15, 90, 30);//����һ����ѡ��ť,��ʼ����Ϊ��ͼ2
		radioButton_map2.setFont(new Font("����", Font.PLAIN, 15));
		radioButton_map2.setFocusable(false);
		buttonGroup.add(radioButton_map2);

		separator4.setBounds(20, 190, 380, 50);	/*    ˮƽ�ָ���4   */
		separator4.add(button6);
		separator4.add(radioButton_map1);
		separator4.add(radioButton_map2);

		label4.setBounds(20, 0, 250, 225);
		label4.setIcon(image1);	//װ��ͼƬ
		
		separator5.setBounds(420, 10, 260, 225);/*    ��ֱ�ָ���5   */
		separator5.setOrientation(SwingConstants.VERTICAL);
		separator5.add(label4);
		
		option_panel.setBounds(5, 510, 700,250);//ѡ�����
		option_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));//���߿��ʽ
		option_panel.add(separator1);
		option_panel.add(separator2);
		option_panel.add(separator3);
		option_panel.add(separator4);
		option_panel.add(separator5);
		add(option_panel);
		
		/******************************˵�����******************************************/

		button7.setBounds(20, 25, 150, 50);	//ֹͣ��Ϸ��ť
		button7.setFont(new Font("����",Font.PLAIN,20));
		button7.setFocusable(false);
		button7.setEnabled(false);
		
		button8.setBounds(20, 95, 150, 50);	//��ͣ/������Ϸ��ť
		button8.setFont(new Font("����",Font.PLAIN,20));
		button8.setFocusable(false);
		button8.setEnabled(false);
			
		button9.setBounds(20, 165, 150, 50);//��ʼ����Ϸ��ť
		button9.setFont(new Font("����",Font.PLAIN,20));
		button9.setFocusable(false);
		
		text.setBounds(10, 0, 170, 225);	//������
		//Ĭ�ϵ������ǳ����ı���Ż���ʾ�����������������ù�����һֱ��ʾ
		text.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		textArea.setLineWrap(true);		//�����Զ����й���
		textArea.setEditable(false);	//���ɱ༭
		textArea.setFocusable(false);	//��ȡ��������
		textArea.setWrapStyleWord(true);// ������в����ֹ���
		textArea.setText("��Ϸ˵����\n  ��������Ʒ���, �س�����ͣ/������\n  PAGE UP,PAGE DOWN ���ٻ���١�\n"
				+ "  �����Ϸ������ͨ��ʤ��������̰���߲�һ����̰���ߣ���̰��������̰������"
				+ "����Ҳ��һ����ǧ��Ҫ���ڡ�̰�����ϡ�СС��Ϸ����������");
		textArea.setFont(new Font("����", Font.PLAIN, 15));	//���������С��ɫ
		
		separator6.setBounds(190, 10, 260, 225);/*    ��ֱ�ָ���6   */
		separator6.setOrientation(SwingConstants.VERTICAL);
		separator6.add(text);
		
		text_panel.setBounds(710, 510, 380,250);//˵�����
		text_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));//���߿��ʽ
		text_panel.add(button7);
		text_panel.add(button8);
		text_panel.add(button9);
		text_panel.add(separator6);
		add(text_panel);
		
		/*******************************�����*****************************************/

		
		main_panel.setBounds( 5, 5, 1085,  500);//�����
		main_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));//���߿��ʽ
		
		main_panel.add(map_panel);
		main_panel.add(label1);
		main_panel.add(label2);
		add(main_panel);
		
		
	}

	//���ö�ʱ�������߳����˶�	 
	void timer() {
	 			 
		timer1 = new TimerTask() {		

			@Override
			public void run() {
				// TODO Auto-generated method stub
				switch(currentDirection) {
				case 1:
					m.moveUp();
					//�ػ���Ϸ
					repaint(); //����repaint������ʱ��ʵ���Ͼ��ǵ�����paint������
					if(Map.living==false) {
						button7.setEnabled(false);
						button8.setEnabled(false);
					}
					else {
						button7.setEnabled(true);
						button8.setEnabled(true);
						button8.setText("��ͣ��Ϸ");
					}
						
					label2.setText("��ĵ÷֣�"+ m.score);//ˢ�µ÷�
					break;
				case -1:
					m.moveDown();				
					repaint();
					label2.setText("��ĵ÷֣�"+ m.score);
					if(Map.living==false) {
						button7.setEnabled(false);
						button8.setEnabled(false);
					}
					else {
						button7.setEnabled(true);
						button8.setEnabled(true);
						button8.setText("��ͣ��Ϸ");
					}	
					break;
				case 2:
					m.moveLeft();				
					repaint(); 
					label2.setText("��ĵ÷֣�"+ m.score);
					if(Map.living==false) {
						button7.setEnabled(false);
						button8.setEnabled(false);
					}
	
					break;
				case -2:							
					m.moveRight();			
					repaint(); 
					label2.setText("��ĵ÷֣�"+ m.score);
					if(Map.living==false) {
						button7.setEnabled(false);
						button8.setEnabled(false);
					}
					else {
						button7.setEnabled(true);
						button8.setEnabled(true);
						button8.setText("��ͣ��Ϸ");
					}	
					break;
				default: break;	
				}
			}
		};

	}
	
	//ִ�в���
	void play() {
		// TODO Auto-generated method stub
		
		addKeyListener(new KeyAdapter() {//����������¼�����		

			@Override
			public void keyPressed(KeyEvent e) {
				
				int code = e.getKeyCode();
				switch (code) {
				case KeyEvent.VK_UP:	//������ 1		
					//���ܵ���							
					if(1 + currentDirection != 0 && flag==1) {	
						currentDirection=1;						
						try {
							time.schedule(timer1 , 0, speed);
							label1.setText("��ʾ���ߵ�ǰ���ٶ�Ϊ" + speed + "����/��");
						} catch (Exception e2) {}
						
						//m.moveUp();
						//repaint();
					}					
					break;
				case KeyEvent.VK_DOWN:	//������ -1
					//���ܵ���
					if(-1 + currentDirection != 0 && flag==1) {
						currentDirection=-1;
						try {
							time.schedule(timer1 , 0, speed);
							label1.setText("��ʾ���ߵ�ǰ���ٶ�Ϊ" + speed + "����/��");
						} catch (Exception e2) {}
						
						//m.moveDown();
						//repaint(); 						
					}
					break;
				case KeyEvent.VK_LEFT:	//������ 2
					//���ܵ����������߳����˶�
					if(2 + currentDirection != 0 && flag==1) {
						currentDirection=2;
						try {
							time.schedule(timer1 , 0, speed);
							label1.setText("��ʾ���ߵ�ǰ���ٶ�Ϊ" + speed + "����/��");
						} catch (Exception e2) {}
						
						//m.moveLeft();
						//repaint(); 						
					}
					break;
				case KeyEvent.VK_RIGHT:	//������ -2
					//���ܵ���
					if(-2 + currentDirection != 0 && flag==1) {
						currentDirection=-2;							
						//���ö�ʱ�������߳����˶�
						try {
							time.schedule(timer1 ,0, speed);
							label1.setText("��ʾ���ߵ�ǰ���ٶ�Ϊ" + speed + "����/��");
						} catch (Exception e2) {}
						
						//m.moveRight();
						//repaint(); 						
					}
					break;				
				case KeyEvent.VK_PAGE_UP:	//����
					if(flag==0 && button8.getText().equals("������Ϸ")) {
						if(speed>1) {
							speed-=1;
							label1.setText("��ʾ���ߵ�ǰ���ٶ�Ϊ" + speed + "����/��");
						}
						else
							label1.setText("��ʾ���ߵ�ǰ���ٶ��Ѵ���죬Ϊ" + speed + "����/��");
					}
					else if(button8.isEnabled()==false)
						label1.setText("��ʾ����Ϸδ��ʼ���ܵ���");
					else
						label1.setText("��ʾ��������ͣ��Ϸ��ѡ�����");

					break;
				case KeyEvent.VK_PAGE_DOWN:	//����
					if(flag==0 && button8.getText().equals("������Ϸ")) {
						speed+=1;
						label1.setText("��ʾ���ߵ�ǰ���ٶ�Ϊ" + speed + "����/��");
					}
					else if(button8.isEnabled()==false)
						label1.setText("��ʾ����Ϸδ��ʼ���ܵ���");
					else
						label1.setText("��ʾ��������ͣ��Ϸ��ѡ�����");
					break;					
				case KeyEvent.VK_SPACE:	//��ͣ
					if(flag==1) {
						flag=0;
						button8.setText("������Ϸ");
						timer1.cancel();
						label1.setText("��ʾ����Ϸ����ͣ��");
					}
					break;
				case KeyEvent.VK_ENTER:	//����
					if(flag==0) {
						flag=1;
						button8.setText("��ͣ��Ϸ");
						label1.setText("��ʾ����Ϸ�Ѽ���������ƺ÷���");
						timer();
						play();
					}
					break;
				default: break;	
				}
			}
		});
	}
	
	//����ѡ��
	void option() {
		
		//�ָ�Ĭ������
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//��ͼ����ɫ
				 gridding1 = Color.WHITE;
				//�������ɫ
				 gridding2 = Color.ORANGE;
				//��ͷ����ɫ
				 gridding3 = Color.RED;
				//�������ɫ
				 gridding4 = Color.GREEN;
				//�������ɫ
				 gridding5 = Color.BLUE;
				//����ȡ��ѡ��
				 checkBox.setSelected(false);
				init();
				repaint();
			}
		});
		
		//���õ�ͼ�ı�����ɫ
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//����ϵͳ��ɫ��
				Color color=JColorChooser.showDialog(map_panel, "���õ�ͼ������ɫ", Color.green);  //�õ�ѡ�����ɫ
			    if (color==null)  //���δѡȡ
			       color=gridding1;  
			   gridding1 = color;
			   repaint();
			}		
		});
		
		//�Ƿ���ʾ����
		checkBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkBox.isSelected()==true) {
					button3.setVisible(true);
					repaint();
				}
				else {
					button3.setVisible(false);
					repaint();
				}			
			}
			
		});
		
		//�����������ɫ
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color color=JColorChooser.showDialog(map_panel, "���õ�ͼ������ɫ", Color.green);  //�õ�ѡ�����ɫ
			    if (color==null)  //���δѡȡ
			       color=gridding2;  
			   gridding2 = color;
			   repaint();
			}
		});
		
		//������ͷ��ɫ
		button4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color color=JColorChooser.showDialog(map_panel, "������ͷ��ɫ", Color.green);  //�õ�ѡ�����ɫ
			    if (color==null)  //���δѡȡ
			       color=Color.red;  
			   gridding3 = color;
			   repaint();
			}
		});
		
		//����������ɫ
		button5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color color=JColorChooser.showDialog(map_panel, "����������ɫ", Color.green);  //�õ�ѡ�����ɫ
			    if (color==null)  //���δѡȡ
			    	color=gridding4; 			   
			   gridding4 = color;
			   repaint();
			}
		});
		
		//����ʳ����ɫ
		button6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color color=JColorChooser.showDialog(map_panel, "����ʳ����ɫ", Color.green);  //�õ�ѡ�����ɫ
			    if (color==null)  //���δѡȡ
			    	color=gridding5; 			   
			   gridding5 = color;
			   repaint();
			}
		});
		
		//������Ϸ
		button7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				timer1.cancel();
				label1.setText("��ʾ����Ϸ�ѽ�����");
				button8.setText("����/��ͣ");
				speed=300;
				button8.setEnabled(true);
			}
		});
		
		//������Ϸ
		button8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(flag==1) {
					flag=0;
					label1.setText("��ʾ����Ϸ����ͣ�����������ť���Լ�����");
					button8.setText("������Ϸ");
					timer1.cancel();
					
				}
				else {
					flag=1;
					label1.setText("��ʾ����Ϸ�Ѽ���������ƺ÷���");
					button8.setText("��ͣ��Ϸ");
					timer();
					play();
				}
			}
		});
		
		//��ʼ����Ϸ
		button9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				label1.setText("��ʾ����Ϸ�����ã��������");
				button8.setText("��ͣ��Ϸ");
				currentDirection = -2;
				speed=300;
				Map.initSnake();
				repaint();
				timer1.cancel();
				timer();
				play();

			}
		});
	
		//��ͼ1
		radioButton_map1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(radioButton_map1.isSelected()) {
					Map.clear();
					repaint();
					Map.initBackground1();
					repaint();				
				}
			}
		});

		//��ͼ2
		radioButton_map2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(radioButton_map2.isSelected()) {
					Map.clear();
					repaint();
					Map.initBackground2();
					repaint();
				}
			}
		});
		
		//����
		label4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(image==1) {
					label4.setIcon(image2);	//װ��ͼƬ2
					image=0;
					//�ر���ͷ��ʳ�����ɫ����
					button6.setEnabled(false);
					button4.setEnabled(false);

				}
				else {
					label4.setIcon(image1);	//װ��ͼƬ1
					image=1;
					button6.setEnabled(true);
					button4.setEnabled(true);
				}
				repaint();
			}
		});
		
	
	}		
	
}
