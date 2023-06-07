package fpoly.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Order implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CreateDate")
	Date createDate = new Date();
	@Column(columnDefinition = "nvarchar(150)")
	private String address;
	
	@ManyToOne
	@JoinColumn(name = "Username")
	Account account;
	
	@OneToMany(mappedBy = "order")
	List<OrderDetail> orderDetails;
}
