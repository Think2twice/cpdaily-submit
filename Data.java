

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * �洢�����Լ��ӿڵ��࣬����������Լ�����޸�
 * 
 * @author kit chen
 *
 */
public class Data {
	/**
	 * ��λ�ĵص�
	 */
	public static final String address = "�й�xxʡxx��xx��";
	/**
	 * ѧУ��host
	 */
	public static final String host = "https://ccut.campusphere.net";
	/**
	 * ����session
	 */
	public static final String keepingUrl = host + "/portal/index.html";
	/**
	 * ��ȡ����Ϣ�ӿ�
	 */
	public static final String detailCollector = host + "/wec-counselor-collector-apps/stu/collector/detailCollector";
	/**
	 * ��ѯ���±��ӿ�
	 */
	public static final String queryCollector = host
			+ "/wec-counselor-collector-apps/stu/collector/queryCollectorProcessingList";
	/**
	 * ��ȡ����ϸ��Ϣ�ӿ�
	 */
	public static final String formFields = host + "/wec-counselor-collector-apps/stu/collector/getFormFields";
	/**
	 * �ύ���±��ӿ�
	 */
	public static final String submitForm = host + "/wec-counselor-collector-apps/stu/collector/submitForm";
	/**
	 * ���������䣬������β��ԣ���Ѷ��ҵ���������ȶ���
	 */
	public static final String fromMail = "xxx@xxx.onexmail.com";

	/**
	 * ��Ѷ��ҵ��������
	 */
	public static final String fromMailPw = "xxx";

	/**
	 * ���շ�����
	 */
	public static final String toMail = "meethigher@qq.com";

	/**
	 * Cpdaily-Extension��Ҫ����ʵ�����
	 * 
	 * @return
	 */
	public static Map<String, String> getSubHeaders() {
		Map<String, String> map = getHeaders();
		map.put("CpdailyStandAlone", "0");
		map.put("extension", "1");
		map.put("Cpdaily-Extension",
				"pHOOGjwfjlaj58u9gy81uhgiydfL42LmpjgBDFcbU+kEqAptH9XxFXqW5Ao8R9c xflkajgiofj9u58ghs788fshgo3My2oySUl8QPIbvbnD/CXOa+Blv01iFxgqgiiy 5clnHfdjlaj1eifd5h/gf+zI8EhyufCpcvfysmZiHyT8NWYNQuEy1nrK Ei2LtZtKxrx+37tNQ2tRHSEI5a+HXrm2Q6Y15+0BHHyg7EjIm1kymMTOcgAm imVlgyaeFHJ05Wd2");
		return map;
	}

	/**
	 * Cookie��Ҫ����ʵ�����
	 * 
	 * @return
	 */
	public static Map<String, String> getHeaders() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("tenantId", "ccut");
		map.put("User-Agent",
				"Mozilla/5.0 (Linux; Android 10; MI 9 Build/QKQ1.190825.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36 okhttp/3.8.1");
		map.put("Content-Type", "application/json;charset=utf-8");
		map.put("Host", "ccut.campusphere.net");
		map.put("Connection", "Keep-Alive");
		map.put("Accept-Encoding", "gzip");
		map.put("Cookie",
				"acw_tc=76b20fef15888014645921542e22c988f6643e4fde483d65f1e96045a37fc1; MOD_AUTH_CAS=ST-iap:10146111491819107:ST:895u9820c5-01a0-4763-a33d-b0ca9c9458a1:20200506131817; CASTGC=iap-10146111491819107-TGT-87fafa55-83cf-46e2-b01b-c6c0109754a9; AUTHTGC=iap-10146111491819107-TGT-87fafa55-83cf-46e2-b01b-c6c0109754a9");
		return map;
	}

}
