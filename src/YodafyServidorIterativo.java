import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
public class YodafyServidorIterativo {

	public static void main(String[] args) {
                
                byte[] buferEnvio = new byte[256];
                byte[] buferRecepcion = new byte[256];
		// Puerto de escucha
		int port=8989;
		String host="localhost";
                // array de bytes auxiliar para recibir o enviar datos.
		//byte []buffer=new byte[256];
		// Número de bytes leídos
		//int bytesLeidos=0;
                
                DatagramPacket packet_or = null;
                DatagramPacket packet_cp = null;
                InetAddress iaddress = null;
		DatagramSocket socketServicio = null;
                

                String mensaje;
		
		try {
                        socketServicio=new DatagramSocket(port);
			// Abrimos el socket en modo pasivo, escuchando el en puerto indicado por "port"
			//////////////////////////////////////////////////
			//ServerSocket serverSocket=new ServerSocket(port);
			//////////////////////////////////////////////////
			// Mientras ... siempre!
			do {
                            packet_or = new DatagramPacket(buferRecepcion, buferRecepcion.length);
                            socketServicio.receive(packet_or);
                            
                            System.out.println("He recibido el mensaje :"+new String(packet_or.getData()));
                            
                            iaddress = packet_or.getAddress();
                            port = packet_or.getPort();
                            
                                                
                            ProcesadorYodafy procesador=new ProcesadorYodafy(socketServicio);
                            String yodificado=procesador.yodaDo(new String(packet_or.getData()));
                            
                            
                            buferEnvio = yodificado.getBytes();
                            packet_or = new DatagramPacket(buferEnvio, buferEnvio.length, iaddress, port);
                            socketServicio.send(packet_or);
			} while (true);
			
		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}
