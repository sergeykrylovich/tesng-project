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
public class ProductCreationDTO{
	private List<String> images;
	private Integer price;
	private String description;
	private String title;
	private Integer categoryId;
}