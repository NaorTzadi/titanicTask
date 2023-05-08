import java.io.*;
import java.util.ArrayList;
public class Test {
    private static final ArrayList<Passenger> passengers=new ArrayList<>();
    public String readData(File file) {
        String data;
        String firstLine;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.PATH_TO_DATA_FILE));
            firstLine = bufferedReader.readLine();
            while ((data = bufferedReader.readLine()) != null) {
                String[] values = data.split(",",13);

                String name=values[4]+" "+values[3];
                Passenger passenger = new Passenger(values);
                passenger.setName(modify(name));

                passengers.add(passenger);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return firstLine;
    }

    public static String modify(String fullName){
        String modifyName=fullName.replace("\"\"", "");
        modifyName=modifyName.replace("\"", "");
        modifyName=modifyName.substring(modifyName.indexOf(".")+1);
        //System.out.println(modifyName);
        return modifyName;
    }
    public static ArrayList<Passenger> getPassengers(){
        return passengers;
    }

}

