package model;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class DataCache {


    public static DataCache instance;
    public static LocationData locData;
    public static MaleNameData maleNameData;
    public static FemaleNameData femaleNameData;
    public static SurNameData surNameData;

    public static DataCache getInstance() {
        if(instance == null) {
            instance = new DataCache();
        }
        return instance;
    }

    public static Location[] getLocations() {
        if(locData == null) {
            Reader reader = null;
            try {
                reader = new FileReader("json/locations.json");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Gson myGson = new Gson();
            locData = (DataCache.LocationData)myGson.fromJson(reader, DataCache.LocationData.class);
        }
        return locData.data;
    }

    public static String[] getMaleNames() {
        if(maleNameData == null) {
            Reader reader = null;
            try {
                reader = new FileReader("json/mnames.json");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Gson myGson = new Gson();
            maleNameData = (DataCache.MaleNameData)myGson.fromJson(reader, DataCache.MaleNameData.class);
        }
        return maleNameData.data;
        }



    public static String[] getFemaleNames() {
        if(femaleNameData == null) {
            Reader reader = null;
            try {
                reader = new FileReader("json/fnames.json");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Gson myGson = new Gson();
            femaleNameData = (DataCache.FemaleNameData)myGson.fromJson(reader, DataCache.FemaleNameData.class);
        }
        return femaleNameData.data;
    }

    public static String[] getSurNames()  {
        if (surNameData == null) {
            Reader reader = null;
            try {
                reader = new FileReader("json/snames.json");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Gson myGson = new Gson();
            try {
                surNameData = myGson.fromJson(reader, DataCache.SurNameData.class);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return surNameData.data;
    }

    public class Location{
        double latitude;
        double longitude;
        String city;
        String country;

        public Location(double latitude, double longitude, String city, String country) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.city = city;
            this.country = country;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

    public class LocationData {
        public Location[] data;

        public LocationData(Location[] data) {
            this.data = data;
        }

        public Location[] getData() {
            return data;
        }

        public void setData(Location[] data) {
            this.data = data;
        }
    }


    public class SurNameData {
        public String[] data;
        public SurNameData() {

        }

        public String[] getData() {
            return data;
        }

        public void setData(String[] data) {
            this.data = data;
        }
    }

    public class FemaleNameData {
         String[] data;

        public FemaleNameData(String[] data) {
            this.data = data;
        }

        public String[] getData() {
            return data;
        }

        public void setData(String[] data) {
            this.data = data;
        }
    }

    public class MaleNameData {
        String[] data;

        public MaleNameData(String[] data) {
            this.data = data;
        }

        public String[] getData() {
            return data;
        }

        public void setData(String[] data) {
            this.data = data;
        }
    }
}
