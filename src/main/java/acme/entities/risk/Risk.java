
package acme.entities.risk;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Risk extends AbstractEntity {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "R-[0-9]{3}", message = "{validation.project.code}")
	private String				reference;

	@Past
	private LocalDate			identificationDate;

	@NotNull
	@Min(0)
	private Integer				impact;

	@Min(0)
	private Double				probability;


	@Transient
	private Double value() {
		return this.impact * this.probability;
	}


	@NotBlank
	@Max(100)
	private String	description;

	@URL
	private String	link;

}
