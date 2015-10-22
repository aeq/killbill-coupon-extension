package org.killbill.billing.jaxrs.json;

import java.math.BigDecimal;

import javax.annotation.Nullable;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class CouponJson {
	@ApiModelProperty(required = true)
	private final String couponName;
	@ApiModelProperty(required = true)
	private final String couponCode;
	@ApiModelProperty(required = true, allowableValues="P, A", value="P for percentage, A for amount")
	private final String couponType;
	@ApiModelProperty(required = true)
	private final BigDecimal couponValue;
	private final Integer maxRedemption;
	@ApiModelProperty(value="format: yyyy-MM-dd")
	private final LocalDate startRedeemDate;
	@ApiModelProperty(value="format: yyyy-MM-dd")
	private final LocalDate endRedeemDate;
	@ApiModelProperty(allowableValues="true, false")
	private final boolean active;
	@ApiModelProperty(value="Default is EVERGERRN")
	private final DurationJson duration;
	
	@JsonCreator
    public CouponJson(
    			@JsonProperty("couponName") final String couponName,
                @JsonProperty("couponCode") final String couponCode,
                @JsonProperty("couponType") final String couponType,
                @JsonProperty("couponValue") final BigDecimal couponValue,
                @JsonProperty("maxRedemption") @Nullable final Integer maxRedemption,
                @JsonProperty("startRedeemDate") @Nullable final LocalDate startRedeemDate,
                @JsonProperty("endRedeemDate") @Nullable final LocalDate endRedeemDate,
                @JsonProperty("active") @Nullable final boolean active,
                @JsonProperty("duration") @Nullable final DurationJson duration){
		this.couponName = couponName;
		this.couponCode = couponCode;
		this.couponType = couponType;
		this.couponValue = couponValue;
		this.maxRedemption = maxRedemption;
		this.startRedeemDate = startRedeemDate;
		this.endRedeemDate = endRedeemDate;
		this.active = active;
		this.duration = duration;
	}
	


	public String getCouponCode() {
		return couponCode;
	}
	
	public String getCouponName(){
		return couponName;
	}



	public String getCouponType() {
		return couponType;
	}



	public BigDecimal getCouponValue() {
		return couponValue;
	}



	public Integer getMaxRedemption() {
		return maxRedemption;
	}



	public LocalDate getStartRedeemDate() {
		return startRedeemDate;
	}



	public LocalDate getEndRedeemDate() {
		return endRedeemDate;
	}



	public boolean isActive() {
		return active;
	}



	public DurationJson getDuration() {
		return duration;
	}
	
}
