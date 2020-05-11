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
	
	Map m = new Map();//地图类

	JPanel main_panel = new JPanel();	//主面板
	JPanel map_panel = new JPanel();	//游戏面板
	JPanel option_panel = new JPanel();	//选项面板
	JPanel text_panel = new JPanel();	//说明面板

	JLabel label1 = new JLabel("提示：",JLabel.LEFT);	//速度提示框
	JLabel label2 = new JLabel("分数：",JLabel.LEFT);	//分数提示框
	JLabel label3 = new JLabel("选  项:",JLabel.CENTER);	//选项提示框
	JLabel label4 = new JLabel();	//装载图标
	
	JTextArea textArea= new JTextArea(); //游戏说明
	JScrollPane text = new JScrollPane(textArea);	//带滚动条的文本区

	ImageIcon image1 = new ImageIcon("皮卡丘.PNG");	//图标
	ImageIcon image2 = new ImageIcon("皮卡丘2.gif");	//图标

	
	JButton button1 = new JButton("恢复默认设置");	//恢复默认设置
	JButton button2 = new JButton("设置背景颜色");	//设置背景颜色
	JButton button3 = new JButton("颜色");			//设置网格颜色
	JButton button4 = new JButton("设置蛇头颜色");	//设置蛇头颜色
	JButton button5 = new JButton("设置蛇身颜色");	//设置蛇身颜色
	JButton button6 = new JButton("设置食物颜色");	//设置食物颜色
	JButton button7 = new JButton("结束游戏");		//停止游戏
	JButton button8 = new JButton("继续/暂停");		//继续游戏
	JButton button9 = new JButton("开始新游戏");		//开始新游戏
	
	static JCheckBox checkBox = new JCheckBox("显示网格",false);	//创建一个复选框,是否显示网格。

	ButtonGroup buttonGroup = new ButtonGroup();	//创建一组按钮，其中一次只能选择一个按钮。
	JRadioButton radioButton_map1 = new JRadioButton("地图1");//创建一个最初未选择的单选按钮,地图1。
	JRadioButton radioButton_map2 = new JRadioButton("地图2");//创建一个最初未选择的单选按钮,地图2。

	JSeparator separator1 = new JSeparator();	//分割线组件1-水平
	JSeparator separator2 = new JSeparator();	//分割线组件2-水平
	JSeparator separator3 = new JSeparator();	//分割线组件3-水平
	JSeparator separator4 = new JSeparator();	//分割线组件4-水平
	JSeparator separator5 = new JSeparator();	//分割线组件5-垂直
	JSeparator separator6 = new JSeparator();	//分割线组件6-垂直

	//使用四个常量表示四个方向
	public static final int UP_DIRECTION = 1;  	//上	
	public static final int DOWN_DIRECTION = -1;//下	
	public static final int LEFT_DIRECTION = 2; //左	
	public static final int RIGHT_DIRECTION =-2;//右
	
	//蛇当前的方向
	public static int currentDirection = -2; // 蛇默认是向右行走
	//蛇当前的速度
	public int speed = 300;
	
	//继续/暂停
	int flag = 1;
	//切换图片
	public static int image = 1;
	
	//地图的颜色
	public static Color gridding1 = Color.WHITE;
	//网格的颜色
	public static Color gridding2 = Color.ORANGE;
	//蛇头的颜色
	public static Color gridding3 = Color.RED;
	//蛇身的颜色
	public static Color gridding4 = Color.GREEN;
	//蛇身的颜色
	public static Color gridding5 = Color.BLUE;
	
	//创建定时器
	Timer time = new Timer();  //定时器
	TimerTask timer1;	//蛇持续运动任务
	
	public Game(String string, int i, int j, int k, int l) {
		// TODO Auto-generated constructor stub

		init();		//初始化组件
		timer();	//设置定时器
		play();		//执行操作
		option();	//设置选项		
		setTitle(string);
		setLocation(i,j);
		setSize(k,l);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setVisible(true);
		setResizable(false);
	}

	//初始化组件
	void init() {
		// TODO Auto-generated method stub
		
		/***************************设置面板布局**********************************/
		
		setLayout(null);
		main_panel.setLayout(null);	
		map_panel.setLayout(new GridLayout(1,0));	
		option_panel.setLayout(null);
		text_panel.setLayout(null);
		
		/*****************************游戏面板*************************************/		
		
		label1.setBounds(10, 10, 400, 25);	//速度提示框		
		label1.setForeground(Color.black);
		label1.setFont(new Font("宋体",Font.PLAIN, 20));
		
		label2.setBounds(875, 10, 200, 25);	//分数提示框		
		label2.setForeground(Color.RED);
		label2.setFont(new Font("宋体",Font.PLAIN,20));
		
		map_panel.setBounds(12, 38, 1060,  458);	//游戏面板
		map_panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));	//设置边缘的距离	
		map_panel.add(m);
		
		/*****************************选项面板************************************/
		
		label3.setBounds(20, 15, 150, 30);	//选项提示框	
		label3.setForeground(Color.BLUE);
		label3.setFont(new Font("宋体",Font.BOLD,20));
		
		button1.setBounds(200, 15, 150, 30);	//恢复默认设置按钮
		button1.setFont(new Font("宋体",Font.BOLD,15));
		button1.setFocusable(false);
		
		separator1.setBounds(20, 10, 380, 50);	/*    水平分隔符1   */
		separator1.add(label3);
		separator1.add(button1);
		
		button2.setBounds(20, 15, 150, 30);	//设置背景颜色按钮
		button2.setFont(new Font("宋体",Font.BOLD,15));
		button2.setFocusable(false);

		checkBox.setBounds(200, 15, 90, 30);//创建一个复选框,是否显示网格
		checkBox.setFont(new Font("宋体", Font.PLAIN, 15));
		checkBox.setFocusable(false);
		
		button3.setBounds(300, 15, 70, 30);	//设置网格颜色按钮
		button3.setFont(new Font("宋体",Font.BOLD,15));
		button3.setFocusable(false);
		button3.setVisible(false);

		separator2.setBounds(20, 70, 380, 50);	/*    水平分隔符2   */
		separator2.add(checkBox);
		separator2.add(button2);
		separator2.add(button3);
		
		button4.setBounds(20, 15, 150, 30);	//设置蛇头颜色按钮
		button4.setFont(new Font("宋体",Font.BOLD,15));
		button4.setFocusable(false);
		
		button5.setBounds(200, 15, 150, 30);	//设置蛇身颜色按钮
		button5.setFont(new Font("宋体",Font.BOLD,15));
		button5.setFocusable(false);
		
		separator3.setBounds(20, 130, 380, 50);	/*    水平分隔符3   */
		separator3.add(button4);
		separator3.add(button5);
		
		button6.setBounds(20, 15, 150, 30);	//设置食物颜色按钮
		button6.setFont(new Font("宋体",Font.BOLD,15));
		button6.setFocusable(false);
		
		radioButton_map1.setBounds(200, 15, 90, 30);//创建一个单选按钮,初始设置为地图1
		radioButton_map1.setFont(new Font("宋体", Font.PLAIN, 15));
		radioButton_map1.setSelected(true);
		radioButton_map1.setFocusable(false);
		buttonGroup.add(radioButton_map1);
		
		radioButton_map2.setBounds(300, 15, 90, 30);//创建一个单选按钮,初始设置为地图2
		radioButton_map2.setFont(new Font("宋体", Font.PLAIN, 15));
		radioButton_map2.setFocusable(false);
		buttonGroup.add(radioButton_map2);

		separator4.setBounds(20, 190, 380, 50);	/*    水平分隔符4   */
		separator4.add(button6);
		separator4.add(radioButton_map1);
		separator4.add(radioButton_map2);

		label4.setBounds(20, 0, 250, 225);
		label4.setIcon(image1);	//装载图片
		
		separator5.setBounds(420, 10, 260, 225);/*    垂直分隔符5   */
		separator5.setOrientation(SwingConstants.VERTICAL);
		separator5.add(label4);
		
		option_panel.setBounds(5, 510, 700,250);//选项面板
		option_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));//面板边框格式
		option_panel.add(separator1);
		option_panel.add(separator2);
		option_panel.add(separator3);
		option_panel.add(separator4);
		option_panel.add(separator5);
		add(option_panel);
		
		/******************************说明面板******************************************/

		button7.setBounds(20, 25, 150, 50);	//停止游戏按钮
		button7.setFont(new Font("宋体",Font.PLAIN,20));
		button7.setFocusable(false);
		button7.setEnabled(false);
		
		button8.setBounds(20, 95, 150, 50);	//暂停/继续游戏按钮
		button8.setFont(new Font("宋体",Font.PLAIN,20));
		button8.setFocusable(false);
		button8.setEnabled(false);
			
		button9.setBounds(20, 165, 150, 50);//开始新游戏按钮
		button9.setFont(new Font("宋体",Font.PLAIN,20));
		button9.setFocusable(false);
		
		text.setBounds(10, 0, 170, 225);	//滚动条
		//默认的设置是超过文本框才会显示滚动条，以下设置让滚动条一直显示
		text.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		textArea.setLineWrap(true);		//激活自动换行功能
		textArea.setEditable(false);	//不可编辑
		textArea.setFocusable(false);	//获取不到焦点
		textArea.setWrapStyleWord(true);// 激活断行不断字功能
		textArea.setText("游戏说明：\n  方向键控制方向, 回车键暂停/继续。\n  PAGE UP,PAGE DOWN 加速或减速。\n"
				+ "  许多游戏都可以通关胜利，但是贪吃蛇不一样。贪吃蛇，因贪而生，因贪而亡。"
				+ "人生也是一样，千万不要倒在“贪”字上。小小游戏，人生至理。");
		textArea.setFont(new Font("宋体", Font.PLAIN, 15));	//设置字体大小颜色
		
		separator6.setBounds(190, 10, 260, 225);/*    垂直分隔符6   */
		separator6.setOrientation(SwingConstants.VERTICAL);
		separator6.add(text);
		
		text_panel.setBounds(710, 510, 380,250);//说明面板
		text_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));//面板边框格式
		text_panel.add(button7);
		text_panel.add(button8);
		text_panel.add(button9);
		text_panel.add(separator6);
		add(text_panel);
		
		/*******************************主面板*****************************************/

		
		main_panel.setBounds( 5, 5, 1085,  500);//主面板
		main_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));//面板边框格式
		
		main_panel.add(map_panel);
		main_panel.add(label1);
		main_panel.add(label2);
		add(main_panel);
		
		
	}

	//设置定时器，让蛇持续运动	 
	void timer() {
	 			 
		timer1 = new TimerTask() {		

			@Override
			public void run() {
				// TODO Auto-generated method stub
				switch(currentDirection) {
				case 1:
					m.moveUp();
					//重画游戏
					repaint(); //调用repaint方法的时候实际上就是调用了paint方法。
					if(Map.living==false) {
						button7.setEnabled(false);
						button8.setEnabled(false);
					}
					else {
						button7.setEnabled(true);
						button8.setEnabled(true);
						button8.setText("暂停游戏");
					}
						
					label2.setText("你的得分："+ m.score);//刷新得分
					break;
				case -1:
					m.moveDown();				
					repaint();
					label2.setText("你的得分："+ m.score);
					if(Map.living==false) {
						button7.setEnabled(false);
						button8.setEnabled(false);
					}
					else {
						button7.setEnabled(true);
						button8.setEnabled(true);
						button8.setText("暂停游戏");
					}	
					break;
				case 2:
					m.moveLeft();				
					repaint(); 
					label2.setText("你的得分："+ m.score);
					if(Map.living==false) {
						button7.setEnabled(false);
						button8.setEnabled(false);
					}
	
					break;
				case -2:							
					m.moveRight();			
					repaint(); 
					label2.setText("你的得分："+ m.score);
					if(Map.living==false) {
						button7.setEnabled(false);
						button8.setEnabled(false);
					}
					else {
						button7.setEnabled(true);
						button8.setEnabled(true);
						button8.setText("暂停游戏");
					}	
					break;
				default: break;	
				}
			}
		};

	}
	
	//执行操作
	void play() {
		// TODO Auto-generated method stub
		
		addKeyListener(new KeyAdapter() {//给窗口添加事件监听		

			@Override
			public void keyPressed(KeyEvent e) {
				
				int code = e.getKeyCode();
				switch (code) {
				case KeyEvent.VK_UP:	//向上走 1		
					//不能倒退							
					if(1 + currentDirection != 0 && flag==1) {	
						currentDirection=1;						
						try {
							time.schedule(timer1 , 0, speed);
							label1.setText("提示：蛇当前的速度为" + speed + "毫秒/格");
						} catch (Exception e2) {}
						
						//m.moveUp();
						//repaint();
					}					
					break;
				case KeyEvent.VK_DOWN:	//向下走 -1
					//不能倒退
					if(-1 + currentDirection != 0 && flag==1) {
						currentDirection=-1;
						try {
							time.schedule(timer1 , 0, speed);
							label1.setText("提示：蛇当前的速度为" + speed + "毫秒/格");
						} catch (Exception e2) {}
						
						//m.moveDown();
						//repaint(); 						
					}
					break;
				case KeyEvent.VK_LEFT:	//向左走 2
					//不能倒退器，让蛇持续运动
					if(2 + currentDirection != 0 && flag==1) {
						currentDirection=2;
						try {
							time.schedule(timer1 , 0, speed);
							label1.setText("提示：蛇当前的速度为" + speed + "毫秒/格");
						} catch (Exception e2) {}
						
						//m.moveLeft();
						//repaint(); 						
					}
					break;
				case KeyEvent.VK_RIGHT:	//向右走 -2
					//不能倒退
					if(-2 + currentDirection != 0 && flag==1) {
						currentDirection=-2;							
						//设置定时器，让蛇持续运动
						try {
							time.schedule(timer1 ,0, speed);
							label1.setText("提示：蛇当前的速度为" + speed + "毫秒/格");
						} catch (Exception e2) {}
						
						//m.moveRight();
						//repaint(); 						
					}
					break;				
				case KeyEvent.VK_PAGE_UP:	//加速
					if(flag==0 && button8.getText().equals("继续游戏")) {
						if(speed>1) {
							speed-=1;
							label1.setText("提示：蛇当前的速度为" + speed + "毫秒/格");
						}
						else
							label1.setText("提示：蛇当前的速度已达最快，为" + speed + "毫秒/格");
					}
					else if(button8.isEnabled()==false)
						label1.setText("提示：游戏未开始不能调速");
					else
						label1.setText("提示：请先暂停游戏再选择调速");

					break;
				case KeyEvent.VK_PAGE_DOWN:	//减速
					if(flag==0 && button8.getText().equals("继续游戏")) {
						speed+=1;
						label1.setText("提示：蛇当前的速度为" + speed + "毫秒/格");
					}
					else if(button8.isEnabled()==false)
						label1.setText("提示：游戏未开始不能调速");
					else
						label1.setText("提示：请先暂停游戏再选择调速");
					break;					
				case KeyEvent.VK_SPACE:	//暂停
					if(flag==1) {
						flag=0;
						button8.setText("继续游戏");
						timer1.cancel();
						label1.setText("提示：游戏已暂停！");
					}
					break;
				case KeyEvent.VK_ENTER:	//继续
					if(flag==0) {
						flag=1;
						button8.setText("暂停游戏");
						label1.setText("提示：游戏已继续，请控制好方向！");
						timer();
						play();
					}
					break;
				default: break;	
				}
			}
		});
	}
	
	//各类选项
	void option() {
		
		//恢复默认设置
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//地图的颜色
				 gridding1 = Color.WHITE;
				//网格的颜色
				 gridding2 = Color.ORANGE;
				//蛇头的颜色
				 gridding3 = Color.RED;
				//蛇身的颜色
				 gridding4 = Color.GREEN;
				//蛇身的颜色
				 gridding5 = Color.BLUE;
				//网格取消选中
				 checkBox.setSelected(false);
				init();
				repaint();
			}
		});
		
		//设置地图的背景颜色
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//调用系统调色板
				Color color=JColorChooser.showDialog(map_panel, "设置地图背景颜色", Color.green);  //得到选择的颜色
			    if (color==null)  //如果未选取
			       color=gridding1;  
			   gridding1 = color;
			   repaint();
			}		
		});
		
		//是否显示网格
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
		
		//设置网格的颜色
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color color=JColorChooser.showDialog(map_panel, "设置地图网格颜色", Color.green);  //得到选择的颜色
			    if (color==null)  //如果未选取
			       color=gridding2;  
			   gridding2 = color;
			   repaint();
			}
		});
		
		//设置蛇头颜色
		button4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color color=JColorChooser.showDialog(map_panel, "设置蛇头颜色", Color.green);  //得到选择的颜色
			    if (color==null)  //如果未选取
			       color=Color.red;  
			   gridding3 = color;
			   repaint();
			}
		});
		
		//设置蛇身颜色
		button5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color color=JColorChooser.showDialog(map_panel, "设置蛇身颜色", Color.green);  //得到选择的颜色
			    if (color==null)  //如果未选取
			    	color=gridding4; 			   
			   gridding4 = color;
			   repaint();
			}
		});
		
		//设置食物颜色
		button6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color color=JColorChooser.showDialog(map_panel, "设置食物颜色", Color.green);  //得到选择的颜色
			    if (color==null)  //如果未选取
			    	color=gridding5; 			   
			   gridding5 = color;
			   repaint();
			}
		});
		
		//结束游戏
		button7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				timer1.cancel();
				label1.setText("提示：游戏已结束！");
				button8.setText("继续/暂停");
				speed=300;
				button8.setEnabled(true);
			}
		});
		
		//继续游戏
		button8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(flag==1) {
					flag=0;
					label1.setText("提示：游戏已暂停，点击继续按钮可以继续！");
					button8.setText("继续游戏");
					timer1.cancel();
					
				}
				else {
					flag=1;
					label1.setText("提示：游戏已继续，请控制好方向！");
					button8.setText("暂停游戏");
					timer();
					play();
				}
			}
		});
		
		//开始新游戏
		button9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				label1.setText("提示：游戏已重置！请继续。");
				button8.setText("暂停游戏");
				currentDirection = -2;
				speed=300;
				Map.initSnake();
				repaint();
				timer1.cancel();
				timer();
				play();

			}
		});
	
		//地图1
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

		//地图2
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
		
		//换肤
		label4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(image==1) {
					label4.setIcon(image2);	//装载图片2
					image=0;
					//关闭蛇头与食物的颜色调整
					button6.setEnabled(false);
					button4.setEnabled(false);

				}
				else {
					label4.setIcon(image1);	//装载图片1
					image=1;
					button6.setEnabled(true);
					button4.setEnabled(true);
				}
				repaint();
			}
		});
		
	
	}		
	
}
