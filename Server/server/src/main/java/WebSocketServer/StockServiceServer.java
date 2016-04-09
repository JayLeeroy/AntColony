package WebSocketServer;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import ColonAi.World;
import ColonAi.WorldObjects.Tunnel;
 
public class StockServiceServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(3040);
 
        ServletContextHandler ctx = new ServletContextHandler();
        ctx.addServlet(StockServiceSocketServlet.class, "");
        
        World world = new World(10,10);
        world.underGroundDimension.createObject(new Tunnel(), 2, 1);
        world.underGroundDimension.createObject(new Tunnel(), 2, 2);
        world.underGroundDimension.createObject(new Tunnel(), 2, 3);
        world.underGroundDimension.createObject(new Tunnel(), 2, 4);
        world.underGroundDimension.createObject(new Tunnel(), 2, 5);
        world.underGroundDimension.createObject(new Tunnel(), 2, 6);
 
        server.setHandler(ctx);
 
        server.start();
        server.join();
    }
 
    public static class StockServiceSocketServlet extends WebSocketServlet {

		private static final long serialVersionUID = 1L;

		@Override
        public void configure(WebSocketServletFactory factory) {
            factory.register(StockServiceWebSocket.class);
        }
    }
}
