package ozbank.ozbankapp.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ozbank.ozbankapp.entity.Emi;


public class ResponseDto {

	@JsonInclude(Include.NON_NULL)
	private String message;
	@JsonInclude(Include.NON_NULL)
	private List<Emi> emi;
	@JsonInclude(Include.NON_NULL)
	private List<Map<Long, List<Emi>>> emiAllCustomers;

	@JsonInclude(Include.NON_NULL)
	public List<Map<Long, List<Emi>>> getEmiAllCustomers() {
		return emiAllCustomers;
	}

	public void setEmiAllCustomers(List<Map<Long, List<Emi>>> emiAllCustomers) {
		this.emiAllCustomers = emiAllCustomers;
	}

	public List<Emi> getEmi() {
		return emi;
	}

	public void setEmi(List<Emi> emi) {
		this.emi = emi;
	}

	public ResponseDto() {

	}

	public ResponseDto(String message) {
		// super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
