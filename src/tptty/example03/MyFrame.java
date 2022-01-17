package tptty.example03;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
//오류발생 : 처음에 javax.swing.JFrame이 import되지 않음
//오류원인 : java.base 기본모듈을 사용중인데 javax.swing.JFrame은 기본모듈이 아니라 java.desktop모듈사용
//해결방법 : 모듈에 requires java.desktop; 추가

@SuppressWarnings("serial")
public class MyFrame extends JFrame {
	int width;
	int height;
	JPanel panel1, panel2;
	
	
	
	public MyFrame() { //생성자
		
		//밑에 있는 생성자 이용하기
		this("202110547 황윤선");
		
//		this.setTitle("202110547 황윤선"); //프레임 타이틀 설정
//		//super("202110547 황윤선");과 같은 방식
//		
		//반드시 해야하는 디폴트 설정
//		this.setSize(500,500); //setSize : 프레임 크기 설정
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //창을 닫으면 프로그램 종료(감춰지는 것x)
//		this.setVisible(true); //화면에 창 보여지기
		
	}
	
	public MyFrame(String title) {
		super(title);
		
//		this.setSize(500,500); //setSize : 프레임 크기 설정
		//프레임 크기를 직접 설정하지 않고, 스크린 크기에 맞추어 생성
		Toolkit kit = Toolkit.getDefaultToolkit(); //Toolkit정보를 얻을 수 있음(스크린 정보)
		Dimension screenSize = kit.getScreenSize(); //getScreenSize : 스크린 사이즈 정보를 얻어옴(dimension 타입 반환)
		//타입 Dimension : 폭, 너비
		this.width = screenSize.width;
		this.height = screenSize.height;
		this.setSize(width/2, height/2);
		
		//프레임이 화면에 출력되는 위치 설정
		this.setLocation(100, 100); //x는 오른쪽으로 갈수록, y는 아래로 갈수록 증가
//		this.setLocationRelativeTo(null); //인자에 컴포넌트 입력 : null을 입력하면 스크린의 중앙에 출력
		
		//프레임 좌측상단의 아이콘 변경
		//1. 해당프로젝트에 folder생성
		//2. 폴더안에 이미지 붙여넣기
		Image img = kit.getImage("img/char1.png"); //3. getImage이용해서 파일경로 입력(확장자까지 입력)
		this.setIconImage(img); //4. setIconIamge에 전달 : 아이콘 변경
		
		//컴포넌트 추가
		init();
//		this.pack(); //pack : 위에서 추가한 컴포넌트만큼만 프레임의 사이즈를 설정 (setSize필요 없음)

		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //창을 닫으면 프로그램 종료(감춰지는 것x)
		this.setVisible(true); //화면에 창 보여지기
	}

	public void init() {
		Container container = this.getContentPane(); //컨텐트페인 획득
		//오류발생 : 디폴트 배치관리자 BorderLayout사용 시, 5번 버튼만 보임
		//오류원인 : 사이즈를 지정해주지 않으면 자동으로 center구역에 프레임 사이즈의 버튼출력
		//해결방법 : 1. 사이즈를 지정, 2. 배치관리자 변경(setLayout)
		
		//버튼 객체 생성
//		JButton btn1 = new JButton("1번 버튼");
//		JButton btn2 = new JButton("2번 버튼");
//		JButton btn3 = new JButton("3번 버튼");
//		JButton btn4 = new JButton("4번 버튼");
//		JButton btn5 = new JButton("5번 버튼");
		
		//1.
//		container.setLayout(new BorderLayout(20,20)); //갭
		BorderLayout lm = (BorderLayout) container.getLayout();
		lm.setHgap(20); //setHgap : 레이아웃의 수평갭 변경
		
		//BorderLayout은 한 구역에 하나씩만 배치 가능
		//해결방법 : 프레임(=벽), BorderLayout은 벽에 직접 버튼 붙이기 -> JPanel(=도화지)사용
//		container.add(btn1, BorderLayout.CENTER);
//		container.add(btn2, BorderLayout.WEST);
//		container.add(btn3, BorderLayout.EAST);
//		container.add(btn4, BorderLayout.NORTH);
//		container.add(btn5, BorderLayout.SOUTH);
		
		//2. 
//		container.setLayout(new FlowLayout()); //기본적으로 center정렬
//		container.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 30)); //설정가능(왼쪽정렬, 수평갭, 수직갭)

		// 컨테이너를 통해 버튼을 프레임에 추가(add)
//		container.add(btn1);
//		container.add(btn2);
//		container.add(btn3);
//		container.add(btn4);
//		container.add(btn5);

		// 패널생성 (디폴트 배치관리자 : FlowLayout)
		panel1 = new JPanel();
		panel2 = new JPanel();

		// 패널의 배경색 변경(setBackGround)
		panel1.setBackground(Color.ORANGE); //상수는 대문자사용
		panel2.setBackground(Color.BLACK);
		
		// 패널의 레이아웃 정보 가져오기
		FlowLayout lm1 = (FlowLayout) panel1.getLayout();
		lm1.setHgap(20); // setHgap : 레이아웃의 갭 변경

		JButton btn1 = new JButton("RED");
		
		//버튼의 이벤트처리방법
		btn1.addActionListener(new ActionListener() { //인터페이스활용

			@Override
			public void actionPerformed(ActionEvent e) {
				//bt1에서 action이벤트가 발생하면 actionPerformed메소드 호출
				panel2.setBackground(Color.RED);
				
			}
			
		});
		
		JButton btn2 = new JButton("GREEN");
		btn2.addActionListener(new ActionListener() { //인터페이스활용

			@Override
			public void actionPerformed(ActionEvent e) {
				//bt1에서 action이벤트가 발생하면 actionPerformed메소드 호출
				panel2.setBackground(Color.GREEN);
				
			}
			
		});
		JButton btn3 = new JButton("BLUE");
		btn3.addActionListener(new ActionListener() { //인터페이스활용

			@Override
			public void actionPerformed(ActionEvent e) {
				//bt1에서 action이벤트가 발생하면 actionPerformed메소드 호출
				panel2.setBackground(Color.BLUE);
				
			}
			
		});

		panel1.add(btn1);
		panel1.add(btn2);
		panel1.add(btn3);
		
		// 프레임에 추가시켜야 화면에 출력됨
		container.add(panel1, BorderLayout.NORTH);
		container.add(panel2, BorderLayout.CENTER); 
		
	}
	
}
