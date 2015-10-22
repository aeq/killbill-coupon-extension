package org.killbill.billing.jaxrs.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RedeemCouponJson {
	@ApiModelProperty(required = true, dataType = "java.util.UUID")
	private final String subscriptionId;
	
	@ApiModelProperty(dataType = "java.util.UUID")
	private final String couponId;
	private final String couponCode;
	

	
	@JsonCreator
    public RedeemCouponJson(
    			@JsonProperty("couponId") final String couponId,
    			@JsonProperty("couponCode") final String couponCode,
    			@JsonProperty("subscriptionId") final String subscriptionId){
		this.couponId = couponId;
		this.couponCode = couponCode;
		this.subscriptionId = subscriptionId;
	}


	public String getCouponId() {
		return couponId;
	}


	public String getCouponCode() {
		return couponCode;
	}


	public String getSubscriptionId() {
		return subscriptionId;
	}

}
