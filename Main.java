package cpdailyAlpha;

public class Main {
	public static void main(String[] args) throws Exception {
		while (true) {
			// ���ص������У��±�0��ʾ�Ƿ������ݣ��±�1��ʾisHandled���±�2��ʾcollectWid���±�3��ʾformWid
			String[] fields = CpDaily.getForm();
			/*
			 * �����������������м����ʱ�� 
			 * �����true���ύ���ձ���24Сʱ���ٴμ���±� 
			 * �����false����������Ƿ񷢲����±������2Сʱ
			 */
			String result;//�洢���еĽ�����ʼ�֪ͨ
			boolean flag;
			if (fields[0].equals("yes")) {
				flag = true;
				if (fields[1].equals("1")) {
					System.out.println("���ձ��Ѿ��ύ���ˣ�");
				} else {
					System.out.println("����׼���ύ���ձ�...");
					String schoolTaskWid = CpDaily.getSchool(fields);
					String message = CpDaily.submit(fields, schoolTaskWid, Data.address);
					if (message.equals("SUCCESS")) {
						result="���ձ��ѳɹ��ύ��";
						System.out.println(result);
					} else {
						result="���ձ��ύʧ�ܣ�ʧ�ܱ���->" + message;
						System.out.println(result);
					}
					SendMail.send(new String[] {"����У԰�ύ֪ͨ",result});
				}
			} else {
				flag = false;
				System.out.println("��δ�������±���");
			}
			if (flag) {
				System.out.println("24Сʱ���ٴμ���Ƿ����±���");
				Thread.sleep(1000 * 60 * 60 * 24);
			} else {
				System.out.println("2Сʱ��������Ƿ񷢲�����");
				Thread.sleep(1000 * 60 * 60 * 2);
			}
		}
	}
}
