package com.lotto;


import javax.swing.JFrame;
import javax.swing.plaf.FontUIResource;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import javax.swing.JFrame;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import java.net.URLConnection;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.event.KeyEvent;
import org.json.JSONObject;

public class Lotto extends JFrame implements MouseListener, KeyListener{
	MyButton btn1 = new MyButton("1");
	MyButton btn2 = new MyButton("2");
	MyButton btn3 = new MyButton("3");
	MyButton btn4 = new MyButton("4");
	MyButton btn5 = new MyButton("5");
	MyButton btn6 = new MyButton("6");
	MyButton btn7 = new MyButton("7");
//	String number = "";
//	String number1 = "";
//	String number2 = "";
//	String number3 = "";
//	String number4 = "";
//	String number5 = "";
//	String number6 = "";
//	String number7 = "";
	 
	JButton btn_result = new JButton("해당 회차로");
	
	JTextField turn_text = new JTextField();//텍스트 입력
	JLabel turn_label = new JLabel("746회차");
	JLabel turn_label1 = new JLabel("최근 로또번호");
	JLabel label = new JLabel("+");
	
	
	
	public void init(){
		//init
		getContentPane().setLayout(null);
		btn1.setBounds(40,110,60,60);
		getContentPane().add(btn1);
		//btn1.setHorizontalAlignment(SwingConstants.CENTER);
		btn2.setBounds(120,110,60,60);
		getContentPane().add(btn2);
		btn2.setHorizontalAlignment(SwingConstants.CENTER);
		btn3.setBounds(200,110,60,60);
		getContentPane().add(btn3);
		btn3.setHorizontalAlignment(SwingConstants.CENTER);
		btn4.setBounds(280,110,60,60);
		getContentPane().add(btn4);
		btn4.setHorizontalAlignment(SwingConstants.CENTER);
		btn5.setBounds(360,110,60,60);
		getContentPane().add(btn5);
		btn5.setHorizontalAlignment(SwingConstants.CENTER);
		btn6.setBounds(440,110,60,60);
		getContentPane().add(btn6);
		btn6.setHorizontalAlignment(SwingConstants.CENTER);
		btn7.setBounds(570,110,60,60);
		getContentPane().add(btn7);
		btn7.setHorizontalAlignment(SwingConstants.CENTER);
		//textbox
		turn_text.setColumns(10);
		turn_text.setBounds(250, 290, 150, 40);
		getContentPane().add(turn_text);
		//글씨 부분, 입력하면 해당 회차로 텍스트 바뀌는 부분
		turn_label.setBounds(65, 290, 170, 40);
		getContentPane().add(turn_label);
		turn_label.setHorizontalAlignment(SwingConstants.CENTER);
		turn_label1.setBounds(40,40,200,70);
		getContentPane().add(turn_label1);
		turn_label1.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(505,110,60,60);
		getContentPane().add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		//해당 회차 입력후 전달하는 박스
		btn_result.setBounds(410, 290, 170, 40);
		getContentPane().add(btn_result);
		turn_label.setHorizontalAlignment(SwingConstants.CENTER);
		
	}
	
	public void event() {
		btn_result.addMouseListener(this);
		turn_text.addKeyListener(this);
	}
	
	Lotto() throws Exception{
		super("로또 번호 조회");
		init();
		event();
		
		setVisible(true); // 화면에 보여주는 메소드
		setSize(700,400); // 화면 크기 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 화면 닫으면 프로그램도 종료
	}
	
	public static void main(String[] args) throws Exception{
		String fontName1 = "NanumGothic-Regular.ttf";
		Font f1 = Font.createFont(Font.TRUETYPE_FONT, new File(fontName1));
		f1 = f1.deriveFont(23f);	//	글자 크기를 20으로 지정, float 형식
		
		// f1 글자 폰트를 전체 글꼴로 지정
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while(keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if(value instanceof FontUIResource) {
				UIManager.put(key, new FontUIResource(f1));
			}
		}
		
		new Lotto();
	}
	
	public void printResult() {
		JSONObject jsonObj = getNumber();
		if(jsonObj==null)
		{
			// 회차 결과 없음.
			turn_label.setText("결과 없음");
		} 
		else {
		btn1.setText(String.valueOf(jsonObj.get("drwtNo1")));
		btn2.setText(String.valueOf(jsonObj.get("drwtNo2")));
		btn3.setText(String.valueOf(jsonObj.get("drwtNo3")));
		btn4.setText(String.valueOf(jsonObj.get("drwtNo4")));
		btn5.setText(String.valueOf(jsonObj.get("drwtNo5")));
		btn6.setText(String.valueOf(jsonObj.get("drwtNo6")));
		btn7.setText(String.valueOf(jsonObj.get("bnusNo")));
		turn_label.setText(turn_text.getText());
		turn_label1.setText(turn_text.getText() + "회차 로또번호");
		}
	}
	public void checkNumber() {
		
	}
	public JSONObject getNumber() {
		 String no = turn_text.getText();
		 System.out.println(no);
		 String number="";
		 String url="https://www.nlotto.co.kr/common.do?method=getLottoNumber&drwNo=";
		 HttpsURLConnection conn=null;
		 JSONObject result = null;
		 try {
			 conn = (HttpsURLConnection)new URL(url+no).openConnection();
			 //conn.setHostnameVerifier(new CustomizedHostnameVerifier());
			 conn.setHostnameVerifier(new HostnameVerifier() {        
		            public boolean verify(String hostname, SSLSession session)  {  
		            return true;
		            }
		        });
			 InputStreamReader isr = new InputStreamReader(conn.getInputStream(), "UTF-8");
			 BufferedReader br = new BufferedReader(isr);
			 number = br.readLine();
			 // 임시 테스트
			 //lbl_title.setText(number);

			 // json 파싱 
			 result = new JSONObject(number);
			 if(result.get("returnValue").equals("fail")) {
				 result = null;
			 } 
			 //else {
//				 number1 = String.valueOf(result.get("drwtNo1"));
//				 number2 = String.valueOf(result.get("drwtNo2"));
//				 number3 = String.valueOf(result.get("drwtNo3"));
//				 number4 = String.valueOf(result.get("drwtNo4"));
//				 number5 = String.valueOf(result.get("drwtNo5"));
//				 number6 = String.valueOf(result.get("drwtNo6"));
//				 number7 = String.valueOf(result.get("bnusNo"));	
//			 }			 
		 } catch (Exception e) {
			 	e.printStackTrace();
		 }finally {
			 conn.disconnect();
		 }
		 return result;			
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			turn_label.setText("Key Pressed");
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource()==btn_result) {
			getNumber();
			printResult();
		}
//		if(arg0.getSource()==turn_text) {
//			
//		}
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}






















