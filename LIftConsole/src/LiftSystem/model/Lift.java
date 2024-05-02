package LiftSystem.model;

public class Lift {
	private String name;
	private int currentPosition;
	private int from;
	private int to;
	private int capacity;
	private boolean isMaintanence;

	public Lift(String name, int position,int from,int to) {
		this.name = name;
		this.currentPosition = position;
		this.from = from;
		this.to = to;
		this.capacity = 0;
		this.isMaintanence = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isMaintanence() {
		return isMaintanence;
	}

	public void setMaintanence(boolean isMaintanence) {
		this.isMaintanence = isMaintanence;
	}
}
