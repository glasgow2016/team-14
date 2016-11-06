package model;


public class Visits {

    private int month;
    private int center;
    private int count;

    public Visits(){}


    public Visits(int month, int center, int count) {
        this.month = month;
        this.center = center;
        this.count = count;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getCenter() {
        return center;
    }

    public void setCenter(int center) {
        this.center = center;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Visitor_counting{" +
                "month=" + month +
                ", center=" + center +
                ", count=" + count +
                '}';
    }
}
