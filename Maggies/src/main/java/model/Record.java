package model;


import java.sql.Timestamp;

public class Record {

    private String remark;
    private int day_id;
    private String seen_by;
    private String person;
    private String visit_type;
    private String gender;
    private String cancer_site;
    private String journey_stage;
    private String nature_of_visit;
    private String activity;
    private int center;
    private Timestamp record_timestamp;

    public Record(String remark, int day_id, String seen_by, String person, String visit_type, String gender, String cancer_site, String journey_stage, String nature_of_visit, String activity, int center, Timestamp record_timestamp) {
        this.remark = remark;
        this.day_id = day_id;
        this.seen_by = seen_by;
        this.person = person;
        this.visit_type = visit_type;
        this.gender = gender;
        this.cancer_site = cancer_site;
        this.journey_stage = journey_stage;
        this.nature_of_visit = nature_of_visit;
        this.activity = activity;
        this.center = center;
        this.record_timestamp = record_timestamp;
    }

    public Record(String remark, int day_id, String seen_by, String person, String visit_type, String gender, String cancer_site, String journey_stage, String nature_of_visit, String activity, int center) {
        this.remark = remark;
        this.day_id = day_id;
        this.seen_by = seen_by;
        this.person = person;
        this.visit_type = visit_type;
        this.gender = gender;
        this.cancer_site = cancer_site;
        this.journey_stage = journey_stage;
        this.nature_of_visit = nature_of_visit;
        this.activity = activity;
        this.center = center;
    }

    public Record(String remark, int day_id, String seen_by, String person, String visit_type, String gender, String cancer_site, String journey_stage, String nature_of_visit, String activity) {
        this.remark = remark;
        this.day_id = day_id;
        this.seen_by = seen_by;
        this.person = person;
        this.visit_type = visit_type;
        this.gender = gender;
        this.cancer_site = cancer_site;
        this.journey_stage = journey_stage;
        this.nature_of_visit = nature_of_visit;
        this.activity = activity;
    }

    public Record(String remark, String seen_by, String person, String visit_type, String gender, String cancer_site, String journey_stage, String nature_of_visit, String activity) {
        this.remark = remark;
        this.seen_by = seen_by;
        this.person = person;
        this.visit_type = visit_type;
        this.gender = gender;
        this.cancer_site = cancer_site;
        this.journey_stage = journey_stage;
        this.nature_of_visit = nature_of_visit;
        this.activity = activity;
    }

    public Record(int day_id, String seen_by, String person, String visit_type, String gender, String cancer_site, String journey_stage, String nature_of_visit, String activity, int center, Timestamp record_timestamp) {
        this.day_id = day_id;
        this.seen_by = seen_by;
        this.person = person;
        this.visit_type = visit_type;
        this.gender = gender;
        this.cancer_site = cancer_site;
        this.journey_stage = journey_stage;
        this.nature_of_visit = nature_of_visit;
        this.activity = activity;
        this.center = center;
        this.record_timestamp = record_timestamp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getDay_id() {
        return day_id;
    }

    public void setDay_id(int day_id) {
        this.day_id = day_id;
    }

    public String getSeen_by() {
        return seen_by;
    }

    public void setSeen_by(String seen_by) {
        this.seen_by = seen_by;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getVisit_type() {
        return visit_type;
    }

    public void setVisit_type(String visit_type) {
        this.visit_type = visit_type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCancer_site() {
        return cancer_site;
    }

    public void setCancer_site(String cancer_site) {
        this.cancer_site = cancer_site;
    }

    public String getJourney_stage() {
        return journey_stage;
    }

    public void setJourney_stage(String journey_stage) {
        this.journey_stage = journey_stage;
    }

    public String getNature_of_visit() {
        return nature_of_visit;
    }

    public void setNature_of_visit(String nature_of_visit) {
        this.nature_of_visit = nature_of_visit;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getCenter() {
        return center;
    }

    public void setCenter(int center) {
        this.center = center;
    }

    public Timestamp getRecord_timestamp() {
        return record_timestamp;
    }

    public void setRecord_timestamp(Timestamp record_timestamp) {
        this.record_timestamp = record_timestamp;
    }

    @Override
    public String toString() {
        return "Record{" +
                "remark='" + remark + '\'' +
                ", day_id=" + day_id +
                ", seen_by='" + seen_by + '\'' +
                ", person='" + person + '\'' +
                ", visit_type='" + visit_type + '\'' +
                ", gender='" + gender + '\'' +
                ", cancer_site='" + cancer_site + '\'' +
                ", journey_stage='" + journey_stage + '\'' +
                ", nature_of_visit='" + nature_of_visit + '\'' +
                ", activity='" + activity + '\'' +
                ", center=" + center +
                ", record_timestamp=" + record_timestamp +
                '}';
    }
}
