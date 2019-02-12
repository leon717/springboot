package com.leo.boot.easyrules.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain=true)
@Data
public class Income {
	private double before;
	private double tax;
	private double after;
	
	public void setTax(double tax) {
		this.tax = tax;
		this.after = this.before - this.tax;
	}
}
