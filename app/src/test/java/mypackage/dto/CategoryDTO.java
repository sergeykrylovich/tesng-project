package mypackage.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class CategoryDTO{
	private String image;
	private String name;
	private Integer id;
	private String updatedAt;
	private String creationAt;

	public CategoryDTO(String image, String name) {
		this.image = image;
		this.name = name;
	}
}