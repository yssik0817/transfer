package transfer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.table.AbstractTableModel;

public class TableData extends AbstractTableModel{
	private List<Transaction> list;
	private String[] headers = {"Name","Type","Amount","Note"};
	public TableData(){
		updateList();
	}
	
	//column�̸� ����
	public String getColumnName(int cell){
		return headers[cell];
	}
	
	public void updateList(){
		list = new ArrayList<>();
		try{			
			//���� �ִ� ���� �о����
			Scanner sc = new Scanner(new File("./data.csv"));
			for(int i = 0; sc.hasNextLine();i++){
				String[] data = sc.nextLine().split(",");
				if(i != 0){
					Transaction t = new Transaction();
					TransactionBuilder tb = new TransactionBuilder(t);
					t = tb
							.name(data[0])
							.type(data[1])
							.amount(Double.parseDouble(data[2]))
							.note(data[3])
							.transaction();
					list.add(t);
				}
			}
			//��ĳ�� ����
			sc.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
//		for(Transaction t: list){
//			System.out.println(t);
//		}
	}
	
	//���� ���� ī��Ʈ
	public int getColumnCount() {
		return 4;
	}

	//���� ���� ī��Ʈ
	public int getRowCount() {
		//����Ʈ�� �����ŭ ����
		return list.size();
	}
	
	public Object getValueAt(int row, int cell) {
		switch(cell){
			case 0:
				return list.get(row).getName();
			case 1:
				return list.get(row).getType();
			case 2:
				return list.get(row).getAmount();
			case 3:
				return list.get(row).getNote();
		}
		return null;
	}

	public void refresh(){
		updateList();
		//�ڵ����� ���̺� ������Ʈ
		super.fireTableDataChanged();
	}
}