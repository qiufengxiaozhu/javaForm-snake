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
 
	//地图宽（列数）
	public static final int WIDTH = 53;
	//地图高（行数）
	public static final int HEIGHT =23;
	//格子宽
	public static final int CELLWIDTH = 20;	
	//格子高
	public static final int CELLHEIGHT = 20;
		
	/* 生命, 是否活着 */
	public static boolean living = true;
	/*是否吃到食物 */
	private boolean eating = true;
	//当前得分
	public static int score = 0;
	
	int red=0,green=0,blue=0,color=1;
	
	//使用集合保存蛇节点的所有信息
	public static LinkedList<Point>  snake = new LinkedList<Point>(); 
	//地图
	private static boolean[][] background = new boolean[HEIGHT][WIDTH];
	//食物
	public Point food = new Point();
	
	public Map() {
		
		//打扫地图
		clear();
		//初始化地图
		initBackground1();
		//initBackground2();
		//初始化蛇
		initSnake();
		//初始化食物
		initFood();
		//重画
		repaint();
		
	}
	
	//初始化地图
	static void initBackground1(){
		
		for(int rows = 0 ; rows<background.length ; rows++){
			for(int cols = 0  ; cols<background[rows].length ; cols++ ){
				if(rows==0||rows==(HEIGHT-1)||cols==0||cols==(WIDTH-1)) 
					//第一行、最后一行、 第一列与最后一列
					background[rows][cols] = true;
				else
					background[rows][cols] = false;		
			}
		}
		
	}
	
	//初始化地图
	static void initBackground2(){
		
		for(int rows = 0 ; rows<background.length ; rows++){
			for(int cols = 0  ; cols<background[rows].length ; cols++ ){
				if((rows==0 && cols<WIDTH/4) || (rows==0 && cols>WIDTH*3/4)) 
					//第一行前半截、第一行后半截
					background[rows][cols] = true;
				else if((rows==HEIGHT-1 && cols<WIDTH/4) || (rows==HEIGHT-1 && cols>WIDTH*3/4)) 
					//最后一行前半截、最后一行后半截
					background[rows][cols] = true;	
				else if(cols==WIDTH/4 && rows>HEIGHT/4 && rows<HEIGHT*3/4)
					//左边的中间一列
					background[rows][cols] = true;		
				else if(cols==WIDTH*3/4 && rows>HEIGHT/4 && rows<HEIGHT*3/4)
					//右边的中间一列
					background[rows][cols] = true;
				else
					background[rows][cols] = false;	
			}
		}
		
	}
	
	//打扫地图
	static void clear() {
		for(int rows = 0 ; rows<background.length ; rows++){
			for(int cols = 0  ; cols<background[rows].length ; cols++ )
				//石子全清
				background[rows][cols] = false;				
		}
	}
	
	//初始化蛇
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

	//初始化食物
	void initFood() {
		
		//创建随机的横纵坐标
		Random x =new Random();
		Random y =new Random();
		int a = x.nextInt(WIDTH-2)+1;
		int b = y.nextInt(HEIGHT-2)+1;
		
		//将坐标交给食物
		food = new Point(a,b);
		//判断食物坐标是否处于蛇的位置内
		Point body[] = new Point[snake.size()];
		for(int i=0;i<snake.size();i++) {
			body[i]=snake.get(i);		
			if( a==body[i].x && b==body[i].y ) 
				initFood();
		}
		//判断食物坐标是否处于墙壁的位置内
		for(int rows = 0 ; rows<background.length ; rows++)
			for(int cols = 0  ; cols<background[rows].length ; cols++ )
				if(background[rows][cols] == true && a == rows && b == cols) 
					initFood();		
	}
	
	//画图
	public void paint(Graphics g) {
			
		super.paint(g);
		//画地图	
		for(int rows = 0 ; rows<background.length ; rows++){ 
			for(int cols = 0  ; cols<background[rows].length ; cols++ ){
				if(background[rows][cols]){//石头
					//画矩形
					g.setColor(Color.GRAY);					
					g.fill3DRect(cols*CELLWIDTH, rows*CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
			
				}
				else{//空地
					//画背景颜色
					g.setColor(Game.gridding1);
					g.fillRect(cols*CELLWIDTH, rows*CELLHEIGHT, CELLWIDTH, CELLHEIGHT);
					//画网格
					if(Game.checkBox.isSelected()==true) {
						g.setColor(Game.gridding2);					
						g.draw3DRect(cols*CELLWIDTH, rows*CELLHEIGHT, CELLWIDTH, CELLHEIGHT,true);
					}
				}
				//画矩形
				//g.fill3DRect(cols*CELLWIDTH, rows*CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
			}
		}
		
		//画蛇	
		//画蛇身
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
				
			//取出蛇身
			Point body = snake.get(i);
			g.fill3DRect(body.x*CELLWIDTH, body.y*CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
		}		
		//取出蛇头
		Point head = snake.getFirst();
		//画蛇头
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
		//画食物
		if(Game.image==0) {
			ImageIcon foodIcon = new ImageIcon("食物1.gif");
			foodIcon.paintIcon(this,g,food.x*CELLWIDTH, food.y*CELLHEIGHT);
		}
		else {
			g.setColor(Game.gridding5);
			g.fillOval(food.x*CELLWIDTH, food.y*CELLHEIGHT, CELLWIDTH, CELLHEIGHT);
		}
//		//画图片
//		Image img = Toolkit.getDefaultToolkit().getImage("皮卡丘.PNG");
//		g.drawImage(img, 200, 105, 20, 20, Color.LIGHT_GRAY, this);
		
		//画 GameOver 与 最终得分 在窗口上 
		if(living==false) {
			g.setColor(Color.RED);
			g.setFont(new Font("宋体", Font.TYPE1_FONT, 40));	//设置字体大小颜色
			g.drawString("GAME OVER", WIDTH*CELLWIDTH/2-100, HEIGHT*CELLWIDTH/2-20); 
			g.setColor(Color.GREEN);
			g.drawString("你的最终得分："+ score, WIDTH*CELLWIDTH/2-150,HEIGHT*CELLWIDTH/2+50); 
		}

	}
	
	//游戏结束
	void dead() {
		
		//获取现在的蛇头
		Point head = snake.getFirst();
		//获取现在的蛇身
		Point body[] = new Point[snake.size()];
		
		//判断是否撞墙 
		if(head.x>=0&&head.y>=0&&head.x<=WIDTH-1&&head.y<=HEIGHT-1)//整个地图空间
			if(background[head.y][head.x])//撞墙
				living=false;
		
		//判断是否咬到自己
		for(int i =1; i<snake.size() ; i++ ) {
			 body[i] = snake.get(i);
			 if(head.x == body[i].x && head.y == body[i].y) {
				 living=false;
				 System.out.println("咬到自己");
			 }
		}
	}
	
	//游戏进行中
	void live() {
		
		living=true;

	}
	
	//是否吃到食物
	void eatFood() {
		//获取现在的蛇头
		Point head = snake.getFirst();
		if(head.x == food.x && head.y == food.y ) {
			score++;
			eating=true;
			initFood();
			
		}
	//没吃到食物
		else {
			//删除蛇尾
			snake.removeLast();
			eating=false;
		}
	}

	//向上移动
	public void moveUp(){
		if(living) {
			//获取原来蛇头 
			Point head = snake.getFirst();
			//添加新的蛇头
			if(head.y==0)
				snake.addFirst(new Point(head.x,HEIGHT-1));			
			else
				snake.addFirst(new Point(head.x,head.y-1));
			dead();	//判断游戏是否结束			
			eatFood();	//判断是否吃到食物
		}

	}
	
	//向下移动
	public void moveDown(){
		if(living) {
			//获取原来蛇头 
			Point head = snake.getFirst();
			//添加新的蛇头
			if(head.y==HEIGHT-1)
				snake.addFirst(new Point(head.x,0));
			else
				snake.addFirst(new Point(head.x,head.y+1));
			dead();	//判断游戏是否结束
			eatFood();	//判断是否吃到食物
		}
	}
		
	//向左移动
	public void moveLeft(){
		if(living) {
			//获取原来蛇头 
			Point head = snake.getFirst();
			//添加新的蛇头
			if(head.x==0)
				snake.addFirst(new Point(WIDTH-1,head.y));
			else
			 snake.addFirst(new Point(head.x-1,head.y));
			dead();	//判断游戏是否结束
			eatFood();	//判断是否吃到食物
		}
	}
		
	//向右移动
	public void moveRight(){
		if(living) {
			//获取原来蛇头 
			Point head = snake.getFirst();
			//添加新的蛇头
			if(head.x==WIDTH-1)
				snake.addFirst(new Point(0,head.y));
			else
				snake.addFirst(new Point(head.x+1,head.y));
			dead();	//判断游戏是否结束
			eatFood();	//判断是否吃到食物
		}
	}
	
		
}
