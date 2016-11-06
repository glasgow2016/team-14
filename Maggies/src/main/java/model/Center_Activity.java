package model;



public class Center_Activity {

    private int center;
    private String activity;
    private int count;

    public Center_Activity(){}

    public Center_Activity(int center, String activity, int count) {
        this.center = center;
        this.activity = activity;
        this.count = count;
    }

    public int getCenter() {
        return center;
    }

    public void setCenter(int center) {
        this.center = center;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Center_Activity{" +
                "center=" + center +
                ", activity='" + activity + '\'' +
                ", count=" + count +
                '}';
    }
}
