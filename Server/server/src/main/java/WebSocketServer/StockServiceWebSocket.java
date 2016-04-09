package WebSocketServer;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import ColonAi.World;
import ColonAi.WorldThread;
import ColonAi.PackageManager.Manager;
import ColonAi.PackageManager.Packages.ReliablePackage;
 
@WebSocket
public class StockServiceWebSocket {
 
	public static List<Session> sessions             = new ArrayList<Session>();
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    public static Charset charset             = Charset.forName("UTF-8");
    public static CharsetEncoder encoder      = charset.newEncoder();
    public Thread tobj;
    public World world1;
 
    // called when the socket connection with the browser is established
    @OnWebSocketConnect
    public void handleConnect(Session session) {
    	if (tobj == null)
    	{
    		this.world1         = new World(100,100);
    		this.world1.init();
    		WorldThread thread = new WorldThread(this);
        	this.tobj          = new Thread(thread); 
        	this.tobj.start();
    	}
    	
    	String message = this.world1.getForNewClient(session.hashCode()).getAsJson();
    	this.sendMessage(message, session);
    	
    	StockServiceWebSocket.sessions.add(session);
    	
    	System.out.println(session.hashCode() + " connected. count: " + StockServiceWebSocket.sessions.size());
    }
 
    // called when the connection closed
    @OnWebSocketClose
    public void handleClose(Session session, int statusCode, String reason) {
        System.out.println("Connection closed with statusCode=" 
            + statusCode + ", reason=" + reason);
        StockServiceWebSocket.sessions.remove(session);
    }
 
    // called when a message received from the browser
    @OnWebSocketMessage
    public void handleMessage(Session session, String message) {
    	System.out.println(session.hashCode() + " send: " + message);
    	
    	int pacakageId = Integer.parseInt(message);
    	
    	HashMap<Integer, ReliablePackage> packageStack = Manager.instance.reliablePacks.get(session.hashCode()); 
    	packageStack.remove(pacakageId);
  
//    	StockServiceWebSocket.sessions.forEach((s)->{
//    		try {
//				if(!s.equals(session)) this.sendMessage(message, session);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    			
 //   	});
    }
    
    @OnWebSocketMessage
    public void handleMessage( Session session, byte buf[], int offset, int length) {
    	
    	String buffString = "";

        for(int i = 0; i < buf.length; i++)
        {
        	buffString += (char)buf[i];
        }

    	this.handleMessage(session, buffString);
    }
 
    // called in case of an error
    @OnWebSocketError
    public void handleError(Throwable error) {
        error.printStackTrace();    
    }
    
    public void sendMessage(String message, Session session)
    {
    	try {
    		//System.out.println(session.hashCode() + " : " + message);
			session.getRemote().sendBytes(encoder.encode(CharBuffer.wrap(message)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
}
