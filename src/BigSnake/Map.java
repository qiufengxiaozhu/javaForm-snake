package BigSnake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Map extends JPanel{
 
	//��ͼ��������
	public static final int WIDTH = 53;
	//��ͼ�ߣ�������
	public static final int HEIGHT =23;
	//���ӿ�
	public static final int CELLWIDTH = 20;	
	//���Ӹ�
	public static final int CELLHEIGHT = 20;
		
	/* ����, �Ƿ���� */
	public static boolean living = true;
	/*�Ƿ�Ե�ʳ�� */
	private boolean eating = true;
	//��ǰ�÷�
	public static int score = 0;
	
	int red=0,green=0,blue=0,color=1;
	
	//ʹ�ü��ϱ����߽ڵ��������Ϣ
	public static LinkedList<Point>  snake = new LinkedList<Point>(); 
	//��ͼ
	private static boolean[][] background = new boolean[HEIGHT][WIDTH];
	//ʳ��
	public Point food = new Point();
	
	public Map() {
		
		//��ɨ��ͼ
		clear();
		//��ʼ����ͼ
		initBackground1();
		//initBackground2();
		//��ʼ����
		initSnake();
		//��ʼ��ʳ��
		initFood();
		//�ػ�
		repaint();
		
	}
	
	//��ʼ����ͼ
	static void initBackground1(){
		
		for(int rows = 0 ; rows<background.length ; rows++){
			for(int cols = 0  ; cols<background[rows].length ; cols++ ){
				if(rows==0||rows==(HEIGHT-1)||cols==0||cols==(WIDTH-1)) 
					//��һ�С����һ�С� ��һ�������һ��
					background[rows][cols] = true;
				else
					background[rows][cols] = false;		
			}
		}
		
	}
	
	//��ʼ����ͼ
	static void initBackground2(){
		
		for(int rows = 0 ; rows<background.length ; rows++){
			for(int cols = 0  ; cols<background[rows].length ; cols++ ){
				if((rows==0 && cols<WIDTH/4) || (rows==0 && cols>WIDTH*3/4)) 
					//��һ��ǰ��ء���һ�к���
					background[rows][cols] = true;
				else if((rows==HEIGHT-1 && cols<WIDTH/4) || (rows==HEIGHT-1 && cols>WIDTH*3/4)) 
					//���һ��ǰ��ء����һ�к���
					background[rows][cols] = true;	
				else if(cols==WIDTH/4 && rows>HEIGHT/4 && rows<HEIGHT*3/4)
					//��ߵ��м�һ��
					background[rows][cols] = true;		
				else if(cols==WIDTH*3/4 && rows>HEIGHT/4 && rows<HEIGHT*3/4)
					//�ұߵ��м�һ��
					background[rows][cols] = true;
				else
					background[rows][cols] = false;	
			}
		}
		
	}
	
	//��ɨ��ͼ
	static void clear() {
		for(int rows = 0 ; rows<background.length ; rows++){
			for(int cols = 0  ; cols<background[rows].length ; cols++ )
				//ʯ��ȫ��
				background[rows][cols] = false;				
		}
	}
	
	//��ʼ����
	static void initSnake(){
		
		living = true;
		int x = WIDTH/2;
		int y = HEIGHT/2;
		
		if(snake.size()>=6)
			snake.removeAll(snake);
		
		snake.addFirst(new Point(x-1,y));
		snake.addFirst(new Point(x,y));
		snake.addFirst(new Point(x+1,y));
		snake.addFirst(new Point(x+2,y));
		snake.addFirst(new Point(x+3,y));
		snake.addFirst(new Point(x+4,y));
			
	}

	//��ʼ��ʳ��
	void initFood() {
		
		//��������ĺ�������
		Random x =new Random();
		Random y =new Random();
		int a = x.nextInt(WIDTH-2)+1;
		int b = y.nextInt(HEIGHT-2)+1;
		
		//�����꽻��ʳ��
		food = new Point(a,b);
		//�ж�ʳ�������Ƿ����ߵ�λ����
		Point body[] = new Point[snake.size()];
		for(int i=0;i<snake.size();i++) {
			body[i]=snake.get(i);		
			if( a==body[i].x && b==body[i].y ) 
				initFood();
		}
		//�ж�ʳ�������Ƿ���ǽ�ڵ�λ����
		for(int rows = 0 ; rows<background.length ; rows++)
			for(int cols = 0  ; cols<background[rows].length ; cols++ )
				if(background[rows][cols] == true && a == rows && b == cols) 
					initFood();		
	}
	
	//��ͼ
	public void paint(Graphics g) {
			
		super.paint(g);
		//����ͼ	
		for(int rows = 0 ; rows<background.length ; rows++){ 
			for(int cols = 0  ; cols<background[rows].length ; cols++ ){
				if(background[rows][cols]){//ʯͷ
					//������
					g.setColor(Color.GRAY);					
					g.fill3DRect(cols*CELLWIDTH, rows*CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
			
				}
				else{//�յ�
					//��������ɫ
					g.setColor(Game.gridding1);
					g.fillRect(cols*CELLWIDTH, rows*CELLHEIGHT, CELLWIDTH, CELLHEIGHT);
					//������
					if(Game.checkBox.isSelected()==true) {
						g.setColor(Game.gridding2);					
						g.draw3DRect(cols*CELLWIDTH, rows*CELLHEIGHT, CELLWIDTH, CELLHEIGHT,true);
					}
				}
				//������
				//g.fill3DRect(cols*CELLWIDTH, rows*CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
			}
		}
		
		//����	
		//������
		red=Game.gridding4.getRed();
		green=Game.gridding4.getGreen();
		blue=Game.gridding4.getBlue();
		//Random ra =new Random();
		for(int i =1; i<snake.size() ; i++ ){
			color++;
			if((red+color)<255)
				g.setColor(new Color(red+color,green,blue));
			else if((green+color)<255)
				g.setColor(new Color(red,green+color,blue));
			else if((blue+color)<255)
				g.setColor(new Color(red,green,blue+color));
			else {
				color=1;
				g.setColor(new Color(red+color,green,blue));
				//g.setColor(new Color(ra.nextInt(255)+1,ra.nextInt(255)+1,ra.nextInt(255)+1));
			} 
				
			//ȡ������
			Point body = snake.get(i);
			g.fill3DRect(body.x*CELLWIDTH, body.y*CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
		}		
		//ȡ����ͷ
		Point head = snake.getFirst();
		//����ͷ
		if(Game.image==0) {
			if(Game.currentDirection==1) {
				ImageIcon up = new ImageIcon("up2.png");
				up.paintIcon(this,g,head.x*CELLWIDTH-2, head.y*CELLHEIGHT);
			}
			else if(Game.currentDirection==-1) {
				ImageIcon down = new ImageIcon("down2.png");
				down.paintIcon(this,g,head.x*CELLWIDTH-2, head.y*CELLHEIGHT);
			}
			else if(Game.currentDirection==2) {
				ImageIcon left = new ImageIcon("left2.png");
				left.paintIcon(this,g,head.x*CELLWIDTH, head.y*CELLHEIGHT-2);
			}
			else if(Game.currentDirection==-2) {
				ImageIcon right = new ImageIcon("right2.png");
				right.paintIcon(this,g,head.x*CELLWIDTH, head.y*CELLHEIGHT-2);
			} 		
		}
		else {
			g.setColor(Game.gridding3);
			g.fill3DRect(head.x*CELLWIDTH, head.y*CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
		}
		//��ʳ��
		if(Game.image==0) {
			ImageIcon foodIcon = new ImageIcon("ʳ��1.gif");
			foodIcon.paintIcon(this,g,food.x*CELLWIDTH, food.y*CELLHEIGHT);
		}
		else {
			g.setColor(Game.gridding5);
			g.fillOval(food.x*CELLWIDTH, food.y*CELLHEIGHT, CELLWIDTH, CELLHEIGHT);
		}
//		//��ͼƬ
//		Image img = Toolkit.getDefaultToolkit().getImage("Ƥ����.PNG");
//		g.drawImage(img, 200, 105, 20, 20, Color.LIGHT_GRAY, this);
		
		//�� GameOver �� ���յ÷� �ڴ����� 
		if(living==false) {
			g.setColor(Color.RED);
			g.setFont(new Font("����", Font.TYPE1_FONT, 40));	//���������С��ɫ
			g.drawString("GAME OVER", WIDTH*CELLWIDTH/2-100, HEIGHT*CELLWIDTH/2-20); 
			g.setColor(Color.GREEN);
			g.drawString("������յ÷֣�"+ score, WIDTH*CELLWIDTH/2-150,HEIGHT*CELLWIDTH/2+50); 
		}

	}
	
	//��Ϸ����
	void dead() {
		
		//��ȡ���ڵ���ͷ
		Point head = snake.getFirst();
		//��ȡ���ڵ�����
		Point body[] = new Point[snake.size()];
		
		//�ж��Ƿ�ײǽ 
		if(head.x>=0&&head.y>=0&&head.x<=WIDTH-1&&head.y<=HEIGHT-1)//������ͼ�ռ�
			if(background[head.y][head.x])//ײǽ
				living=false;
		
		//�ж��Ƿ�ҧ���Լ�
		for(int i =1; i<snake.size() ; i++ ) {
			 body[i] = snake.get(i);
			 if(head.x == body[i].x && head.y == body[i].y) {
				 living=false;
				 System.out.println("ҧ���Լ�");
			 }
		}
	}
	
	//��Ϸ������
	void live() {
		
		living=true;

	}
	
	//�Ƿ�Ե�ʳ��
	void eatFood() {
		//��ȡ���ڵ���ͷ
		Point head = snake.getFirst();
		if(head.x == food.x && head.y == food.y ) {
			score++;
			eating=true;
			initFood();
			
		}
	//û�Ե�ʳ��
		else {
			//ɾ����β
			snake.removeLast();
			eating=false;
		}
	}

	//�����ƶ�
	public void moveUp(){
		if(living) {
			//��ȡԭ����ͷ 
			Point head = snake.getFirst();
			//����µ���ͷ
			if(head.y==0)
				snake.addFirst(new Point(head.x,HEIGHT-1));			
			else
				snake.addFirst(new Point(head.x,head.y-1));
			dead();	//�ж���Ϸ�Ƿ����			
			eatFood();	//�ж��Ƿ�Ե�ʳ��
		}

	}
	
	//�����ƶ�
	public void moveDown(){
		if(living) {
			//��ȡԭ����ͷ 
			Point head = snake.getFirst();
			//����µ���ͷ
			if(head.y==HEIGHT-1)
				snake.addFirst(new Point(head.x,0));
			else
				snake.addFirst(new Point(head.x,head.y+1));
			dead();	//�ж���Ϸ�Ƿ����
			eatFood();	//�ж��Ƿ�Ե�ʳ��
		}
	}
		
	//�����ƶ�
	public void moveLeft(){
		if(living) {
			//��ȡԭ����ͷ 
			Point head = snake.getFirst();
			//����µ���ͷ
			if(head.x==0)
				snake.addFirst(new Point(WIDTH-1,head.y));
			else
			 snake.addFirst(new Point(head.x-1,head.y));
			dead();	//�ж���Ϸ�Ƿ����
			eatFood();	//�ж��Ƿ�Ե�ʳ��
		}
	}
		
	//�����ƶ�
	public void moveRight(){
		if(living) {
			//��ȡԭ����ͷ 
			Point head = snake.getFirst();
			//����µ���ͷ
			if(head.x==WIDTH-1)
				snake.addFirst(new Point(0,head.y));
			else
				snake.addFirst(new Point(head.x+1,head.y));
			dead();	//�ж���Ϸ�Ƿ����
			eatFood();	//�ж��Ƿ�Ե�ʳ��
		}
	}
	
		
}
