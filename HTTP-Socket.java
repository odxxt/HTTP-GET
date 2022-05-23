// Abdulaziz Al-Odat    S00053001
// Sala Ding            S00053000


import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class HTTPClient {

  // A function to generate HTTP request
  // The file we will fetch and download is at "raw.githack.com/odxxt/HTTP-GET/main/The-HTML-file.html"
  // host: Hostname of server (e.g. "raw.githack.com")
  // port: Port number (e.g. 80 )
  // path: URL path (e.g. "/odxxt/HTTP-GET/main/The-HTML-file.html")
  
  public static void HTTP_Request(String host, int port, String path) 
  throws UnknownHostException, IOException {
  
    String text = "\nHTTP Request Sending......\n";
    for(int i=0; i<text.length();i++){
      System.out.print(""+text.charAt(i));
       try {
          Thread.sleep(100); 
       } catch (Exception e) {
          e.printStackTrace();
       }
   }

    //Open socket to a specific host and port
    Socket socket = new Socket(host, port);
        
    //Get input and output streams for the socket
    OutputStream out = socket.getOutputStream();
    InputStream in = socket.getInputStream();

    // HTTP GET
    if (true) {
      // Constructe an HTTP GET request
      String request = "GET " + path + "?" +" HTTP/1.0\r\n"
          + "Accept: */*\r\n" + "Host: "+host+"\r\n"
          + "Connection: Close\r\n\r\n";
    
      // Sends off HTTP GET request
      out.write(request.getBytes());
      out.flush();
    } else {
      System.out.println("Invalid HTTP method");
      socket.close();
      return;
    }
        
    // Reads the server's response
    // Reads HTTP response
    StringBuffer response=new StringBuffer();
    byte[] buffer = new byte[4096];
    int bytes_read;

    while ((bytes_read = in.read(buffer, 0, 4096)) != -1) {
      // Print server's response 
      for(int i = 0; i < bytes_read; i++)
        response.append((char)buffer[i]);
    }
        
    if (response.substring(response.indexOf(" ") + 1,
        response.indexOf(" ") + 4).equals("200")) {
      //Save the HTTP response message
      File file = new File("your file.html");
      PrintWriter printWriter = new PrintWriter(file);
      printWriter.println(response.substring(response.indexOf("\r\n\r\n") + 4));
      printWriter.close();
      System.out.println("\nFile downloaded successfully :)");
    } else
      System.out.println("HTTP request failed");

    // Closes socket
    socket.close();
  }
    
  public static void main(String[] args) throws Exception {
     
       // Hostname
        String host = "raw.githack.com";

        // Port
        int port = 80;

       // Path
        String path = "/odxxt/HTTP-GET/main/The-HTML-file.html";

       // Generates HTTP request
        HTTP_Request(host, port, path);
    
  }
}
    
