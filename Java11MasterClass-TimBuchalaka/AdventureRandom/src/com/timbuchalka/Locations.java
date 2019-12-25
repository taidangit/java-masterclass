package com.timbuchalka;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    private static Map<Integer, IndexRecord> index = new LinkedHashMap<>();

    private static RandomAccessFile randomAccessFile;

    public static void main(String[] args) throws IOException {

        /*try (BufferedWriter locFile = new BufferedWriter(new FileWriter("locations.txt"));
             BufferedWriter dirFile = new BufferedWriter(new FileWriter("directions.txt"))) {
            for (Location location : locations.values()) {
                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
                for (String direction : location.getExits().keySet()) {
                    dirFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");
                }
            }
        }*/

       /* try (DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
            for (Location location : locations.values()) {
                locFile.writeInt(location.getLocationID());
                locFile.writeUTF(location.getDescription());
                System.out.println("Writing location " + location.getLocationID() + ":" + location.getDescription());
                System.out.println("Writing " + (location.getExits().size() - 1) + " exits.");
                locFile.writeInt(location.getExits().size() - 1);

                for (String direction : location.getExits().keySet()) {
                    if (!direction.equalsIgnoreCase("Q")) {
                        System.out.println("\t\t" + direction + "," + location.getExits().get(direction));
                        locFile.writeUTF(direction);
                        locFile.writeInt(location.getExits().get(direction));
                    }
                }
            }
        }*/

        try (RandomAccessFile raf = new RandomAccessFile("location_rand.dat", "rwd")) {
            raf.writeInt(locations.size());
            int indexSize = locations.size() * 3 * Integer.BYTES;
            int locationStart = (int) (indexSize + raf.getFilePointer() * Integer.BYTES);
            raf.writeInt(locationStart);
            long indexStart = raf.getFilePointer();

            int startPointer = locationStart;
            raf.seek(startPointer);

            for (Location location : locations.values()) {
                raf.writeInt(location.getLocationID());
                raf.writeUTF(location.getDescription());
                StringBuilder builder = new StringBuilder();
                for (String direction : location.getExits().keySet()) {
                    if (!direction.equalsIgnoreCase("Q")) {
                        builder.append(direction);
                        builder.append(",");
                        builder.append(location.getExits().get(direction));
                        builder.append(",");
                    }
                }
                raf.writeUTF(builder.toString());

                IndexRecord record = new IndexRecord(startPointer, (int) (raf.getFilePointer() - startPointer));
                index.put(location.getLocationID(), record);

                startPointer = (int) raf.getFilePointer();
            }

            raf.seek(indexStart);
            for (Integer locationID : index.keySet()) {
                raf.writeInt(locationID);
                raf.writeInt(index.get(locationID).getStartByte());
                raf.writeInt(index.get(locationID).getLength());
            }


        }
    }

    static {

       /* try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("locations_big.txt")))) {
            //scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                //int loc = Integer.parseInt(scanner.next());
                //scanner.skip(scanner.delimiter());
                //String description = scanner.nextLine();

                String input = scanner.nextLine();
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String description = data[1];

                System.out.println("Imported loc:" + loc + ": " + description);
                Map<String, Integer> tempExit = new HashMap<>();
                locations.put(loc, new Location(loc, description, tempExit));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader dirFile = new BufferedReader(new FileReader("directions_big.txt"))) {
            String input;
            while ((input = dirFile.readLine()) != null) {
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);

                System.out.println(loc + ":" + direction + ":" + destination);
                Location location = locations.get(loc);
                location.addExit(direction, destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        try {
            randomAccessFile = new RandomAccessFile("location_rand.dat", "rwd");
            int numLocations = randomAccessFile.readInt();
            long locationStartPoint = randomAccessFile.readInt();

            while (randomAccessFile.getFilePointer() < locationStartPoint) {
                int locationId = randomAccessFile.readInt();
                int locationStart = randomAccessFile.readInt();
                int locationLenth = randomAccessFile.readInt();

                IndexRecord record = new IndexRecord(locationStart, locationLenth);
                index.put(locationId, record);
            }
        } catch (IOException e) {
            System.out.println("IO Exception " + e.getMessage());
        }

    }

    public Location getLocation(int locationId) {
        try {
            IndexRecord record = index.get(locationId);
            randomAccessFile.seek(record.getStartByte());
            int id = randomAccessFile.readInt();
            String description = randomAccessFile.readUTF();
            String exits = randomAccessFile.readUTF();
            String[] exitPart = exits.split(",");

            Location location = new Location(locationId, description, null);
            if(locationId != 0) {
                for(int i=0; i<exitPart.length; i++) {
                    System.out.println("exitPart = " + exitPart[i]);
                    System.out.println("exitPart[+1] = " + exitPart[i+1]);
                    String direction = exitPart[i];
                    int destination = Integer.parseInt(exitPart[++i]);
                    location.addExit(direction, destination);
                }
            }
            return location;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();

    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }

    public void close() throws IOException {
        randomAccessFile.close();
    }
}
