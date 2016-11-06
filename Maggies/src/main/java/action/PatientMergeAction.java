package action;

import Util.DBUtil;
import com.opensymphony.xwork2.ActionSupport;
import model.Record;

import java.util.*;

public class PatientMergeAction extends ActionSupport{

	public String[] original_day_ids;
	private String[] day_ids;

	public String[] getDay_ids() {
		return day_ids;
	}

	public void setDay_ids(String[] day_ids) {
		this.day_ids = day_ids;
	}

	public String[] getOriginal_day_ids() {
		return original_day_ids;
	}

	public void setOriginal_day_ids(String[] original_day_ids) {
		this.original_day_ids = original_day_ids;
	}

	private Set<String> unique_day_ids = new LinkedHashSet<>();
	private Set<String> duplicated_day_ids = new LinkedHashSet<>();

	public List<Record> merged_record = new LinkedList<>();

	public List<Record> getRecord_list() {
		return merged_record;
	}

	public void setRecord_list(List<Record> record_list) {
		this.merged_record = record_list;
	}

	public String merge() {
		for (int i = 0; i < day_ids.length; i++) {
			if (original_day_ids[i] != day_ids[i]) {
				DBUtil.INSTANCE.update_day_id(Integer.parseInt(original_day_ids[i]), Integer.parseInt(day_ids[i]));
			}
		}



		for (int i = 0; i < day_ids.length; i++) {
			String day_id = day_ids[i];
			if (unique_day_ids.contains(day_id)) {
				duplicated_day_ids.add(day_id);
			} else {
				unique_day_ids.add(day_id);
			}
		}
		unique_day_ids.removeAll(duplicated_day_ids);

		Record record = null;
		for (String id : unique_day_ids) {
			record = DBUtil.INSTANCE.unique_patient_record(Integer.parseInt(id));
			merged_record.add(record);
		}
		for (String id : duplicated_day_ids) {
			record = DBUtil.INSTANCE.duplicate_patient_record(Integer.parseInt(id));
			merged_record.add(record);
		}

		Collections.sort(merged_record, new Comparator<Record>() {
			@Override
			public int compare(Record o1, Record o2) {
				int day_id1 = o1.getDay_id();
				int day_id2 = o2.getDay_id();
				return day_id2 - day_id1;
			}
		});
		return SUCCESS;
	}
}
