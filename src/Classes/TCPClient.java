package Classes;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPClient {
    private Socket socket;
    private int port;
    private List<House> houselist;
    private List<Double> expenses;

    public TCPClient(int port) throws IOException {
        this.socket = new Socket("localhost", port);
        this.port = port;
        this.houselist = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    public void startTCPClient() {
        System.out.println("Client started");
        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
            expenses = readRatesfromFile("/Users/davidnanciu/Desktop/FirstTwoSubjects/FirstSubject/src/Classes/Rates.txt");
            System.out.println("Sending rates...");
            oos.writeObject(expenses);
            System.out.println("Sending houses...");
            houselist = readHousesFromFile("/Users/davidnanciu/Desktop/FirstTwoSubjects/FirstSubject/src/Classes/Houses.txt");
            oos.writeObject(houselist);

            System.out.println("Sent lists : " + houselist  + "\n" + expenses);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        try {
            TCPClient tcpClient = new TCPClient(7777);
            tcpClient.startTCPClient();
        } catch (IOException e) {
            System.err.println("Failed to start TCPClient: " + e.getMessage());
        }
    }

    public List<Double> readRatesfromFile (String filePath) {
        List<Double> list = new ArrayList<>();

        try(FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null && !br.equals("EOF")) {
                String[] parts;
                parts = line.split(",");

                double up_water_in = Double.parseDouble(parts[0]);
                double up_water_out = Double.parseDouble(parts[1]);
                double up_recycled_waste = Double.parseDouble(parts[2]);;
                double up_waste = Double.parseDouble(parts[3]);
                double up_shared_electricity = Double.valueOf(parts[4]);

                list.add(up_water_in);
                list.add(up_water_out);
                list.add(up_recycled_waste);
                list.add(up_waste);
                list.add(up_shared_electricity);


            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<House> readHousesFromFile(String filePath) {
        List<House> houses = new ArrayList<>();
        try (FileReader fr = new FileReader(filePath);
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            while((line = br.readLine()) != null && !br.equals("EOF")) {
                String[] parts;
                parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String location = parts[1];
                String insuranceCompany = parts[2];
                double waste_in = Double.valueOf(parts[3]);
                double waste_out = Double.valueOf(parts[4]);
                double recycled_waste = Double.parseDouble(parts[5]);
                double wasted_garbadge = Double.parseDouble(parts[6]);
                double shared_electricity = Double.parseDouble(parts[7]);

                House house = new House(id, location, insuranceCompany, waste_in, waste_out, recycled_waste, wasted_garbadge, shared_electricity);
                houses.add(house);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }


        return houses;
    }


}
