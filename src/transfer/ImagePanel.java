package transfer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	private Image img;
	
	//컨스트럭터 만들기 Constructor
	public ImagePanel(Image img){
		this.img = img;
		//기본 사이즈 설정:이미지의 넓이, 높이 설정
		setSize(new Dimension(img.getWidth(null),img.getHeight(null)));
		//실제사이즈
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		//필요한 컴포넌트를 원하는 위치에 넣기 위해 레이아웃 설정
		setLayout(null);
	}
	
	//JPanel 실행될 때 자동으로 시작 (렌더링)
	public void paintComponent(Graphics g){
		g.drawImage(img, 0, 0, null);
	}
	
	//dimension통해 메인패널 크기 설정
	public Dimension getDim(){
		return new Dimension(img.getWidth(null),img.getHeight(null)+47);
	}
}
