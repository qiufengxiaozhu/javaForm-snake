package BigSnake;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Main_Snake {

	public static final int WIDTH = 1100;	//850
	public static final int HEIGHT = 800;	//620
	public static int x;	
	public static int y;	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Toolkit toolkit = Toolkit.getDefaultToolkit(); //��ȡһ����ϵͳ��ع��������
		//��ȡ��Ļ�ķֱ���
		Dimension d = toolkit.getScreenSize();
		 x = (int) d.getWidth();
		 y = (int) d.getHeight();
		
		new Game("̰���ߴ���ս",(x-WIDTH)/2,(y-HEIGHT)/2,WIDTH,HEIGHT);

	}

}
