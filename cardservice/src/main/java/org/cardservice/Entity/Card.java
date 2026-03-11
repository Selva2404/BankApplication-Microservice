package org.cardservice.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Card extends CommonValue{
	
	@Id
	private Long cardNumber;

	@Column
	private String mobileNumber;
	
	@Column
	private String cardType;
	
	@Column
	private String cardLimit;
	
	@Column
	private String billAmount;
	
	@Column
	private String outstandingAmount;
	

}
