package action;

import Util.DBUtil;
import com.opensymphony.xwork2.ActionSupport;
import model.Center_Activity;
import model.Visits;

import java.util.LinkedList;

public class ReportAction extends ActionSupport{

	private LinkedList<Visits> visits = null;
	private LinkedList<Center_Activity> center_activities = null;

	public LinkedList<Visits> getVisits() {
		return visits;
	}

	public void setVisits(LinkedList<Visits> visits) {
		this.visits = visits;
	}

	public LinkedList<Center_Activity> getCenter_activities() {
		return center_activities;
	}

	public void setCenter_activities(LinkedList<Center_Activity> center_activities) {
		this.center_activities = center_activities;
	}

	public String list_visitors() {
		visits = DBUtil.INSTANCE.list_visitors();
		center_activities = DBUtil.INSTANCE.list_center_activities();
		return SUCCESS;
	}
}
