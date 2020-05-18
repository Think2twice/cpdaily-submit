

import java.util.Calendar;
import java.util.Scanner;

public class Main {
	/**
	 * ���ؽ�� 
	 * submited �������ύ 
	 * error �����ύʧ�� 
	 * success �����ύ�ɹ� 
	 * noform δ�������±�
	 * 
	 * @return
	 */
	public static String start() {
		String[] fields = CpDaily.getForm();
		String result;// �洢���еĽ�����ʼ�֪ͨ
		boolean flag;
		// �ж��Ƿ����±���yesΪ�У�noΪû��
		if (fields[0].equals("yes")) {
			flag = true;
			// �ж��Ƿ��ύ��1Ϊ���ύ��0Ϊδ�ύ
			if (fields[1].equals("1")) {
				System.out.println("���ձ��Ѿ��ύ���ˣ�");
				result = "submited";
			} else {
				System.out.println("����׼���ύ���ձ�...");
				String schoolTaskWid = CpDaily.getSchool(fields);
				String message = CpDaily.submit(fields, schoolTaskWid, Data.address);
				// �ж��Ƿ��ύ�ɹ�
				if (message.equals("SUCCESS")) {
					System.out.println("���ձ��ѳɹ��ύ��");
					result = "success";
				} else {
					System.out.println("���ձ��ύʧ�ܣ�ʧ�ܱ���->" + message);
					result = "error";
				}
			}
		} else {
			System.out.println("��δ�������±���");
			result = "noform";
		}
		return result;

	}

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.print("�����뿪ʼ����Сʱ��");
		int hour = Integer.parseInt(input.nextLine());// Сʱ
		System.out.print("�������������Сʱ��");
		int endHour=Integer.parseInt(input.nextLine());
		input.close();
		Calendar c;
		System.out.println("��������...");
		boolean flag;// ����Ⲣ�ɹ��ύ����true;��δ��⵽����������false
		while (true) {
			c = Calendar.getInstance();
			String result = null;
			int sleepHour=0;
			if (c.get(Calendar.HOUR_OF_DAY) >= hour&&c.get(Calendar.HOUR_OF_DAY)<endHour) {
				result = start();
			}else if(c.get(Calendar.HOUR_OF_DAY)>=endHour) {
				result="submited";
			}
			/*
			 * ��success����error����submited����ȵ��ڶ����ʱ����ִ��
			 * ��noform����ÿ��1Сʱ��ѯ�Ƿ񷢲��±�
			 */
			if ("success".equals(result)) {
				System.out.print(SendMail.send(new String[] { "����У԰�ʾ��ύ֪ͨ", "���ձ��ύ������ɹ���" }));
				sleepHour=24-c.get(Calendar.HOUR_OF_DAY)+hour;
				System.out.println("��������"+sleepHour+"Сʱ������");
			} else if ("error".equals(result)) {
				System.out.print(SendMail.send(new String[] { "����У԰�ʾ��ύ֪ͨ", "���ձ��ύ�����ʧ�ܡ����ֶ��ύ" }));
				sleepHour=24-c.get(Calendar.HOUR_OF_DAY)+hour;
				System.out.println("��������"+sleepHour+"Сʱ������");
			} else if ("noform".equals(result)) {
				sleepHour=1;
				System.out.println("��������"+sleepHour+"Сʱ������");
			} else if ("submited".equals(result)) {
				sleepHour=24-c.get(Calendar.HOUR_OF_DAY)+hour;
				System.out.println("��������"+sleepHour+"Сʱ������");
			}
			Thread.sleep(1000*60*60*sleepHour);
			
		}

	}
}
