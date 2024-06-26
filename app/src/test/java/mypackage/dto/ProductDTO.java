package mypackage.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO{
	private List<String> images;
	private String creationAt;
	private Integer price;
	private String description;
	private Integer id;
	private String title;
	private CategoryDTO category;
	private String updatedAt;
}