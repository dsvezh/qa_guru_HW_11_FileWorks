package model;

import java.util.ArrayList;

public class Beeline {

    public String titleRegion;
    public Region region;
    public ArrayList<Tariff> tariffs;

    public static class Region {
        public int id;
        public String label;
    }

    public static class Fee {
        public boolean isEmpty;
        public String textValue;
        public String value;
        public String unit;
        public String legal;
        public String title;
    }

    public static class OldFee {
        public boolean isEmpty;
        public Object textValue;
        public String value;
        public String unit;
        public Object legal;
        public String title;
    }

    public static class Speed {
        public String value;
        public String unit;
        public Object numberValue;
    }

    public static class Tariff {
        public int id;
        public String name;
        public String alias;
        public Speed speed;
        public String discription;
        public Fee fee;
        public OldFee oldFee;
        public int productType;
    }
}
