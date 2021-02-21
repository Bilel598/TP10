package Client;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    Socket s1=null;
    BufferedReader br=null;
    BufferedReader is=null;
    PrintWriter os=null;
    private static Client instance;

    public void connectToServer() {
        try {
            s1=new Socket("127.0.0.1", 8080); // You can use static final constant PORT_NUM
            br= new BufferedReader(new InputStreamReader(System.in));
            is=new BufferedReader(new InputStreamReader(s1.getInputStream()));
            os= new PrintWriter(s1.getOutputStream());
        }
        catch (IOException e){
            e.printStackTrace();
            System.err.print("IO Exception");
        }
    }

    public JSONArray askServerArray(String requete) {
        String response=null;
        if(s1==null){
            connectToServer();
        }
        try{
            os.println(requete);
            os.flush();
            response=is.readLine();
            System.out.println("Server Response : "+ response);

        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Socket read Error");
        }

        assert response != null;
        return new JSONArray(response);
    }

    public static Client getInstance() {
        if(Client.instance==null) {
            Client.instance = new Client();
        }
        return instance;
    }
}