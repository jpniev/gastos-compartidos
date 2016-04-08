package main.ar.com.globant.springmvc.model;

public enum SplitTypeEnum {
	PAY_1_DIVIDE_ALL_EQUAL(1),
	PAY_1_DIVIDE_ALL_UNEQUAL(2),
	PAY_1_DIVIDE_SOME_EQUAL(3),
	PAY_1_DIVIDE_SOME_UNEQUAL(4),
	PAY_SOME_DIVIDE_ALL_EQUAL(5),
	PAY_SOME_DIVIDE_ALL_UNEQUAL(6),
	PAY_SOME_DIVIDE_SOME_EQUAL(7),
	PAY_SOME_DIVIDE_SOME_UNEQUAL(8),
	SETTLE(9);
	
	int splitTypeEnum;
	
	private SplitTypeEnum(int splitTypeEnum){
		this.splitTypeEnum = splitTypeEnum;
	}

	public int getSplitTypeEnum() {
		return splitTypeEnum;
	}	
	
}
