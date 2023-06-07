package fpoly.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class Product implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(columnDefinition = "nvarchar(200)")
	private String name;
	private String image;
	private Double price;
	private Integer quantity;
	private Double discount;
	@Column(columnDefinition = "nvarchar(500)")
	private String description;
	@Temporal(TemporalType.DATE)
	@Column(name = "CreateDate")
	Date createDate = new Date();
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	Category category;
	
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;
}
