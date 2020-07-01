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
	
	//column이름 설정
	public String getColumnName(int cell){
		return headers[cell];
	}
	
	public void updateList(){
		list = new ArrayList<>();
		try{			
			//현재 있는 파일 읽어오기
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
			//스캐너 종료
			sc.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
//		for(Transaction t: list){
//			System.out.println(t);
//		}
	}
	
	//열의 개수 카운트
	public int getColumnCount() {
		return 4;
	}

	//행의 개수 카운트
	public int getRowCount() {
		//리스트의 사이즈만큼 리턴
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
		//자동으로 테이블 업데이트
		super.fireTableDataChanged();
	}
}