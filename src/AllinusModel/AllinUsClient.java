package AllinusModel;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class AllinUsClient {
	private static int port = 5252;
	private static String ip = "127.0.0.1";
	private static Socket client;
	private static DataInputStream dis;
	private static DataOutputStream dos;
	private static Boolean checklogin;
	
	
	//private static BufferedReader bufferFromclient;
    //private static DataOutputStream bufferFromServer;
    
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		
		String sentence;
		String modifiedSentence;
		Socket clientSocket = new Socket(ip, port);
		
		Scanner sc = new Scanner (System.in);
		
		dis = new DataInputStream(clientSocket.getInputStream()); 
	        
		dos = new DataOutputStream(clientSocket.getOutputStream()); 
		
		
		Thread thread_send = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
								
				while(true) {
					String msg = sc.nextLine();
					try {
						//sendMessage(msg+'\n',clientSocket);
						onMessage(dos,msg);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
		
		Thread thread_receiv = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String msg;
				try {
					while(dis.available() > 0 && (msg = dis.readUTF()) != null) {
						//String msg = onReceivMessage(dis);
						String[] data = msg.split("~");
						if(data[1].equals("returnlogin")) {
							if(data[0].equals("true")) {
								checklogin = true;
							}else {
								checklogin = false;
							}
						}else if(data[1].equals("returnsign")) {
							if(data[0].equals("true")) {
								
							}else {
								
							}
						}else if(data[1].equals("returnchat")) {
							if(data[0].equals("true")) {
								
							}else {
								
							}
						}else {
							System.out.println("no message returned");
						}
						System.out.println(msg);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		thread_receiv.start();
		thread_send.start();
	}
	
	public static void onMessage(DataOutputStream dos,String msg) throws IOException {
		dos.writeUTF(msg);
	}
	
	public static String onReceivMessage(DataInputStream dis) throws IOException {
		String message;
		message = dis.readUTF();
		return message;
	}

	public static DataInputStream getDis() {
		return dis;
	}

	public static void setDis(DataInputStream dis) {
		AllinUsClient.dis = dis;
	}

	public static DataOutputStream getDos() {
		return dos;
	}

	public static void setDos(DataOutputStream dos) {
		AllinUsClient.dos = dos;
	}

	public static Boolean getChecklogin() {
		return checklogin;
	}

	public static void setChecklogin(Boolean checklogin) {
		AllinUsClient.checklogin = checklogin;
	}
	
	
}


