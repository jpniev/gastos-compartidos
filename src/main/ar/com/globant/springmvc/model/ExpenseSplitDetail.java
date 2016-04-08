package main.ar.com.globant.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EXPENSE_SPLITDETAIL")
public class ExpenseSplitDetail {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "EXPENSE_ID")
	private ExpenseDetail expenseDetail;
	
	@Column(name = "USER_ID")
	private long userId;
	
	@Column(name = "amountPayed")
	private double amountPayed;
	
	@Column(name = "shouldHavePayed")
	private double shouldHavePayed;
	
	public ExpenseSplitDetail(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ExpenseDetail getExpenseDetail() {
		return expenseDetail;
	}

	public void setExpenseDetail(ExpenseDetail expenseDetail) {
		this.expenseDetail = expenseDetail;
	}


	public double getAmountPayed() {
		return amountPayed;
	}

	public void setAmountPayed(double amountPayed) {
		this.amountPayed = amountPayed;
	}

	public double getShouldHavePayed() {
		return shouldHavePayed;
	}

	public void setShouldHavePayed(double shouldHavePayed) {
		this.shouldHavePayed = shouldHavePayed;
	}
	
	
}
