package main.ar.com.globant.springmvc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="EXPENSE_DETAIL")
public class ExpenseDetail {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="CATEGORY")
	private Category category;
	
	@Column(name="ESTABLISHMENT")
	private String establishment;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Size(min=1, max=24)	
	@Column(name="currentInstallment")
	private int currentInstallment;
	
	@Size(min=1, max=24)
	@Column(name="totalInstallments")
	private int totalInstallments;
	
	@Column(name="splitType", nullable = false)
	private int splitType = SplitTypeEnum.PAY_1_DIVIDE_ALL_EQUAL.getSplitTypeEnum();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Comment> comments = new ArrayList<Comment>();
	
	public ExpenseDetail(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getEstablishment() {
		return establishment;
	}

	public void setEstablishment(String establishment) {
		this.establishment = establishment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCurrentInstallment() {
		return currentInstallment;
	}

	public void setCurrentInstallment(int currentInstallment) {
		this.currentInstallment = currentInstallment;
	}

	public int getTotalInstallments() {
		return totalInstallments;
	}

	public void setTotalInstallments(int totalInstallments) {
		this.totalInstallments = totalInstallments;
	}

	public int getSplitType() {
		return splitType;
	}

	public void setSplitType(int splitType) {
		this.splitType = splitType;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
}
