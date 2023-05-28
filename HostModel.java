package Model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import test.BookScrabbleHandler;
import test.MyServer;
import test.Tile;

public class HostModel extends Player implements GameModel{
	
	private Socket socket;
	private String serverAddress;
	private int port;
	private MyServer server;
	public HostModel(int id, String name) {
		super(id, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void openServer() {
		server = new MyServer(port,new HostHandler());
        server.start();
	}

	@Override
	public void closeServer() {
		if (server != null) {
            server.close();
            server = null;
        }
	}

	@Override
	public void connectToServer(String serverAddress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendRequest(String request) {
		 try {
	            // Get the output stream of the socket to send data to the server
	            OutputStream outputStream = socket.getOutputStream();

	            // Get the input stream of the socket to receive data from the server
	            InputStream inputStream = socket.getInputStream();

	            // Use the BookScrabbleHandler to handle the client
	            BookScrabbleHandler handler = new BookScrabbleHandler();
	            handler.handleClient(inputStream, outputStream);

	            // Optionally, you can send the request to the server after the client is handled
	            outputStream.write(request.getBytes());
	            outputStream.flush();

	            // Optionally, you can wait for a response from the server
	            // by reading from the input stream of the socket
	            // Read the response from the server here

	        } catch (IOException e) {
	            System.err.println("Error sending request: " + e.getMessage());
	        }
	    }

	@Override
	public void updateBoard(List<Tile> board) {
		// TODO Auto-generated method stub
		
	}

}
