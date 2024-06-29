package Classes;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {
    private ServerSocket serverSocket;
    private int port;
    private List<Double> list;
    private List<House> hlist;
    private ExecutorService threadPool;

    public TCPServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.port = port;
        this.threadPool = Executors.newCachedThreadPool();
        this.list = new ArrayList<>();
        this.hlist = new ArrayList<>();
    }

    public void sortListByCost(List<House> hList) {
            double up_water_in = list.get(0);
            double up_water_out = list.get(1);
            double up_recycled_waste = list.get(2);
            double up_waste = list.get(3);
            double up_shared_electricity = list.get(4);

            for (int i = 0; i < hList.size() - 1; i++) {
                for (int j = i + 1; j < hList.size(); j++) {
                    if (hList.get(i).computeCost(up_water_in, up_water_out, up_recycled_waste, up_waste, up_shared_electricity) <
                            hList.get(j).computeCost(up_water_in, up_water_out, up_recycled_waste, up_waste, up_shared_electricity)) {

                        House aux = hList.get(j);
                        hList.set(j, hList.get(i));
                        hList.set(i, aux);
                    }
                }
            }
        }


    public void startTCPServer() {
        System.out.println("Server Started on Localhost on port " + this.port);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected from " + socket.getInetAddress());
                threadPool.execute(() -> {
                    try(ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
                        list = (List<Double>) ois.readObject();
                        hlist = (List<House>) ois.readObject();

                        System.out.println("Received uprates : " + list);
                        System.out.println(("Received houses : " + hlist));

                        sortListByCost(hlist);

                        System.out.println(hlist);

                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (IOException e) {
                System.err.println("Server accept error: " + e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        try {
            TCPServer server = new TCPServer(7777);
            server.startTCPServer();
        } catch (IOException e) {
            System.err.println("Failed to start server: " + e.getMessage());
        }
    }
}
