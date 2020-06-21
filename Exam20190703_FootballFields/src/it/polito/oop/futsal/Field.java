package it.polito.oop.futsal;

public class Field implements FieldOption{
	private Integer num;
	private Integer occ;
	
	public Field(Integer num, Integer occ) {
		super();
		this.num = num;
		this.occ = occ;
	}

	@Override
	public int getField() {
		return num;
	}

	@Override
	public int getOccupation() {
		return occ;
	}

}
