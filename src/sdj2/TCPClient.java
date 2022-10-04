package sdj2;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
// We use TCP all the time, unless we are streaming

public class TCPClient {
    //Create a server that is able to calculate VAT
    // (use 25% as the rate). Use sockets for communication.
    private String ip;

    public TCPClient() {

    }

    public static void main(String[] args) {
        new TCPClient().run();

    }

    private void run() {
        this.ip = JOptionPane.showInputDialog((Component) null, "IP address");
        if (this.ip != null && this.ip.length() != 0)
        {
            for (String text = JOptionPane.showInputDialog((Component) null, "Price");
                 text != null && text.length() > 0; text = JOptionPane.showInputDialog((Component) null,
                    "price"))
            {
                String res = this.VAT(text);
                JOptionPane.showMessageDialog((Component) null, res);
            }
            for (String text = JOptionPane.showInputDialog((Component) null, "Number");
                 text != null && text.length() > 0; text = JOptionPane.showInputDialog((Component) null,
                    "number"))
            {
                String res = this.prime(text);
                JOptionPane.showMessageDialog((Component) null, res);
            }


        }
    }
    private String VAT(String text) {
        String result = "";

        try {
            Socket socket = new Socket(this.ip, 4714);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
            out.write(text + "\n");
            out.flush();
            result = in.readLine();
            socket.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return result;
    }
    private String prime(String text) {
        String result = "";

        try {
            Socket socket = new Socket(this.ip, 4711);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
            out.write(text + "\n");
            out.flush();
            result = in.readLine();
            socket.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return result;
    }
}
