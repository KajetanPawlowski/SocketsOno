package sdj2;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

    public class MethodsServer {
        // Server must be on before starting the program
        //Server is the slave
        // if you are running multithreaded program you need to make sure that
        // all the method are synchronized if you have a common variable to be

        public MethodsServer() {
        }

        public static void main(String[] args) {
            (new MethodsServer()).run();
        }

        private void run() {
            try {
                ServerSocket welcomeSocket = new ServerSocket(4711);
                System.out.println("Server listening on " + InetAddress.getLocalHost().getHostAddress());

                while(true) {
                    Socket clientSocket = welcomeSocket.accept();
                    (new Thread(new ServerThread(clientSocket))).start();
                    //here you ensure that the Thread is Multithreaded
                }
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

        private void doWork(Socket clientSocket) throws Exception {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream());

            String input = in.readLine();
            System.out.println("Number received: " + input);
            Thread.sleep(5000L);
            try {
               boolean output = isPrime(Double.valueOf(input));
                out.write(String.valueOf(output));
                out.flush();
                clientSocket.close();
            }
            catch(Exception e){
                out.write(e.toString());
                out.flush();
                clientSocket.close();
            }


        }

        private class ServerThread implements Runnable {
            private Socket clientSocket;

            public ServerThread(Socket clientSocket) {

                this.clientSocket = clientSocket;
            }

            public void run() {
                try {
                    MethodsServer.this.doWork(this.clientSocket);
                } catch (Exception var2) {
                    var2.printStackTrace();
                }

            }
        }
        public boolean isPrime(double n)
        {
            // Corner case
            if (n <= 1)
                return false;

            // Check from 2 to n-1
            for (int i = 2; i < n; i++)
                if (n % i == 0)
                    return false;

            return true;
        }
        public String reverseString(String string){
            StringBuilder sb=new StringBuilder(string);
            sb.reverse();
            return sb.toString();
        }

    }





