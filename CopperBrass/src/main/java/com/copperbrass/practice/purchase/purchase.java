package com.copperbrass.practice.purchase;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.copperbrass.practice.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	
	private String orderid;
	
	private LocalDateTime  orderdatetime;
	
	// 0:입금전 1:입금완료 2:구매확정 3:환불 9:기타
	private String deposit; 

	private String totalprice;
	
	@OneToMany(mappedBy="purchase", cascade = CascadeType.REMOVE)
	private List<purchasedetails> purchaseDetailsList;

	@ManyToOne
	private  SiteUser user;
}
