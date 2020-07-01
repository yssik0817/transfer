package transfer;

public class Transaction {
	 private String name;
	 private String type;
	 private double amount;
	 private String note;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	//프린트시 바로 깔끔하게 보이기 위해서
	@Override
	public String toString() {
		return "Transaction [name=" + name + ", type=" + type + ", amount=" + amount + ", note=" + note + "]";
	}
}
