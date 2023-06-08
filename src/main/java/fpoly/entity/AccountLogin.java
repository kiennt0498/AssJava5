package fpoly.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountLogin {
	@NotBlank
	@NotEmpty
	private String username;
	@NotBlank
	@NotEmpty
	private String pass;
	private Boolean check;
}
