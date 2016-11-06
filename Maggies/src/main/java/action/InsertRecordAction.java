package action;

import Util.DBUtil;
import com.opensymphony.xwork2.ActionSupport;
import model.Record;

import java.util.LinkedList;

public class InsertRecordAction extends ActionSupport{

	private String seen_by;
	private String person;
	private String visit_type;
	private String gender;
	private String cancer_site;
	private String journey_stage;
	private String nature_of_visit;
	private String activity;
	private String remark;

	public LinkedList<Record> record_list = null;

	public LinkedList<Record> getRecord_list() {
		return record_list;
	}

	public void setRecord_list(LinkedList<Record> record_list) {
		this.record_list = record_list;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String insert() {
		Record record = new Record(this.remark, this.seen_by, this.person, this.visit_type, this.gender, this.cancer_site, this.journey_stage, this.nature_of_visit, this.activity);
		DBUtil.INSTANCE.insert_record(record);

		record_list = DBUtil.INSTANCE.list_patients();
		return SUCCESS;
	}

}
