package org.killbill.billing.jaxrs.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class DurationJson {
	@ApiModelProperty(required = true, allowableValues="INVOICES, DAYS, WEEKS, MONTHS, YEARS")
	private final String unit;
	@ApiModelProperty(required = true)
	private final Integer number;

	
	@JsonCreator
    public DurationJson(
    			@JsonProperty("unit") final String unit,
                @JsonProperty("number") final Integer number){
		this.unit = unit;
		this.number = number;
	}


	public String getUnit() {
		return unit;
	}


	public Integer getNumber() {
		return number;
	}
	
	
}
