package action;

import Util.DBUtil;
import com.opensymphony.xwork2.ActionSupport;
import model.Record;

import java.util.LinkedList;

public class ListPatientAction extends ActionSupport{

	private LinkedList<Record> record_list = new LinkedList<>();

	public LinkedList<Record> getRecord_list() {
		return record_list;
	}

	public void setRecord_list(LinkedList<Record> record_list) {
		this.record_list = record_list;
	}

	public String list_patients() {
		record_list = DBUtil.INSTANCE.list_patients();
		return SUCCESS;
	}
}
