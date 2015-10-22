package org.killbill.billing.jaxrs.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class UpdateCouponJson {
	@ApiModelProperty(required = true, allowableValues="true, false")
	private final Boolean active;

	
	@JsonCreator
    public UpdateCouponJson(
    			@JsonProperty("active") final Boolean active){
		this.active = active;
	}


	public Boolean getActive() {
		return active;
	}

	
}
