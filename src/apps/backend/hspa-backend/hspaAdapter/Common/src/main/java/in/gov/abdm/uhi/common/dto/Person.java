package in.gov.abdm.uhi.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Person {
	private String id;
	private String name;
	private String gender;
	private String image;
	private String cred;
	private Map<String, String> tags;
	private String dob;
	private Descriptor descriptor;
}
