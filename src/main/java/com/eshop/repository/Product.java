package com.eshop.repository;

import java.util.Date;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	String image = "new.jpg";
	@Column(name = "unitprice")
	private double unitPrice;
	double discount;
	private int quantity = 1;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="productdate")
	private Date productDate;
	private String description;
	private boolean special;
	@Column(name = "likecount")
	private int likeCount = 0;
	private boolean available = true;
	
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;
	
	@OneToMany(mappedBy = "product")
	private List<Share> shares;
	
	@ManyToOne
	@JoinColumn(name="Categoryid")
	private Category category;
	
	public double getPromotePrice() {
		return this.unitPrice * (1 - this.discount);
	}
	
}
