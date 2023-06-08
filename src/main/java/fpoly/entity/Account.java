package fpoly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account implements Serializable{
	@Id
	@NotBlank()
	private String username;
	@Column(columnDefinition = "nvarchar(50)")
	@NotBlank
	private String fullname;
	@Column(columnDefinition = "varchar(100)")
	@NotBlank
	@NotEmpty
	private String password;
	@Column(columnDefinition = "varchar(150)")
	@NotBlank
	@Email
	private String email;
	private String photo;
	private Boolean activated;
	private Boolean admin;
	
	@OneToMany(mappedBy = "account")
	List<Order> orders;
}
