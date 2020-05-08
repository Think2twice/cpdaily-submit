package cpdailyAlpha;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * CpDaily�࣬�й�ͨ���ӿڻ�ȡ���ݵ�һϵ�����󷽷�
 * 
 * @author kit chen
 *
 */
public class CpDaily {
	/**
	 * ��������
	 * 
	 * @return
	 */
	public static String personalData() {
		return Data.form;
	}

	/**
	 * ����post���ύ�������ؽ��
	 * 
	 * @param fields
	 * @param schoolTaskWid
	 * @param address
	 * @return
	 */
	public static String submit(String[] fields, String schoolTaskWid, String address) {
		String formFields = getFields(fields);
		String param = new Form(fields[3], address, fields[2], schoolTaskWid, formFields).toString();
		String result = HttpUtil.sendPost(Data.submitForm, param, Data.getSubHeaders());
		return JSONObject.fromObject(result).get("message").toString();
	}

	/**
	 * ��ȡ�����ؾ���ı�ID
	 * 
	 * @return
	 */
	public static String[] getForm() {
		String todayData = HttpUtil.sendPost(Data.queryCollector, "{\"pageSize\": 6,\"pageNumber\": 1}",
				Data.getSubHeaders());
		JSONArray todayForm = null;
		try {
			todayForm = JSONObject.fromObject(todayData).getJSONObject("datas").getJSONArray("rows");
		} catch (Exception e) {
			System.out.println("��ȡ����Ϣʧ�ܣ�������ֹ�������¼�Ƿ�ʧЧ");
			System.exit(0);
		}
		String flag;// flag�����жϱ��β�ѯ�Ƿ�������
		String isHandled = "", collectWid = "", formWid = "";
		if (todayForm.size() < 1) {// û������
			flag = "no";
		} else {// ������
			flag = "yes";
			isHandled = todayForm.getJSONObject(0).getString("isHandled");
			collectWid = todayForm.getJSONObject(0).getString("wid");
			formWid = todayForm.getJSONObject(0).getString("formWid");
		}

		String[] fields = { flag, isHandled, collectWid, formWid };
		return fields;
	}

	/**
	 * ��ȡ������ѧУ��ID
	 * 
	 * @param fields
	 * @return
	 */
	public static String getSchool(String[] fields) {
		String param = "{\"collectorWid\":\"" + fields[2] + "\"}";
		String todayData = HttpUtil.sendPost(Data.detailCollector, param, Data.getSubHeaders());
		String todaySchool = null;
		try {
			todaySchool = JSONObject.fromObject(todayData).getJSONObject("datas").getJSONObject("collector")
					.get("schoolTaskWid").toString();
		} catch (Exception e) {
			System.out.println("��ȡѧУ��Ϣʧ�ܣ�������ֹ�������¼�Ƿ�ʧЧ");
			System.exit(0);
		}
		return todaySchool;
	}

	/**
	 * ��ȡ������ϸ��Ϣ���ҵ�ѧУ��23���ʾ��Ҵ����pageSize������30
	 * 
	 * @param fields
	 * @return
	 */
	public static String getFields(String[] fields) {
		String param = "{\"pageSize\": 30, \"pageNumber\": 1, \"formWid\": " + fields[3] + ", \"collectorWid\": "
				+ fields[2] + "}";
		String todayData = HttpUtil.sendPost(Data.formFields, param, Data.getHeaders());
		JSONArray todayRows = null;
		try {
			todayRows = JSONObject.fromObject(todayData).getJSONObject("datas").getJSONArray("rows");
		} catch (Exception e) {
			System.out.println("��ȡ����ϸ��Ϣʧ�ܣ�������ֹ�������¼�Ƿ�ʧЧ");
			System.exit(0);
		}
		return filterFields(todayRows);
	}

	/**
	 * ���˵�����δѡ�������
	 * 
	 * @param array
	 * @return
	 */
	public static String filterFields(JSONArray array) {
		ArrayList list = new ArrayList();
		for (Object o : array) {
			JSONObject item = JSONObject.fromObject(o);
			item.put("fieldItems", changeJsonArray(item.get("fieldItems").toString()));
			list.add(item);
		}
		return list.toString();
	}

	/**
	 * ������fieldItems�е�null����ɾ����ֻ����isSelected=1������
	 * 
	 * @param fieldItems
	 * @return
	 */
	public static String changeJsonArray(String fieldItems) {
		JSONArray array = JSONArray.fromObject(fieldItems);
		ArrayList list = new ArrayList();
		for (Object o : array) {
			JSONObject item = JSONObject.fromObject(o);
			if ("1".equals(item.get("isSelected").toString())) {
				list.add(item);
			}

		}
		return list.toString();
	}

}
