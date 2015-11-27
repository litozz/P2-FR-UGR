//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class YodafyClienteUDP {

	public static void main(String[] args) throws SocketException, UnknownHostException {
		
		byte[] buferEnvio = new byte[256];
                byte[] buferRecepcion = new byte[256];
                
                // Nombre del host donde se ejecuta el servidor:
		String host="localhost";
                // Puerto en el que espera el servidor:
		int port=8989;
                
                
                
                DatagramPacket packet_or = null;
                DatagramPacket packet_cp = null;
                InetAddress iaddress = InetAddress.getByName(host);
		DatagramSocket socket = null;
		
		
		// Socket para la conexión TCP
		//Socket socketServicio=null;
		
		try {
                    socket=new DatagramSocket();
                    
                    buferEnvio="Al monte del volcán debes ir sin demora".getBytes();
                    
                    packet_or = new DatagramPacket(buferEnvio, buferEnvio.length, iaddress, port);
                    socket.send(packet_or);

                    packet_cp = new DatagramPacket(buferRecepcion, buferRecepcion.length);
                    socket.receive(packet_cp);
                    
                    System.out.println("Recibido mensaje: "+new String(packet_cp.getData()));
			
			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
