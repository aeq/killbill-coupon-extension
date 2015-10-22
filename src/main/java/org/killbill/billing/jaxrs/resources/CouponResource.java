package org.killbill.billing.jaxrs.resources;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.killbill.billing.jaxrs.json.CouponJson;
import org.killbill.billing.jaxrs.json.RedeemCouponJson;
import org.killbill.billing.jaxrs.json.UpdateCouponJson;

import com.codahale.metrics.annotation.Timed;
import com.sun.jersey.spi.resource.Singleton;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Singleton
@Api(value = "coupons", description = "Operations on coupons")
public class CouponResource {
	public static final String UUID_PATTERN = "\\w+-\\w+-\\w+-\\w+-\\w+";
	public static final String STRING_PATTERN = "\\w+";
	public static final String HDR_CREATED_BY = "X-Killbill-CreatedBy";
	public static final String HDR_REASON = "X-Killbill-Reason";
	public static final String HDR_COMMENT = "X-Killbill-Comment";

	public CouponResource() {

	}

	@Timed
	@GET
	@Path("/plugins/killbill-coupon/coupons/{couponId:" + UUID_PATTERN + "}")
	@Produces(APPLICATION_JSON)
	@ApiOperation(value = "Retrieve a coupon by id", response = CouponJson.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid coupon id supplied"), @ApiResponse(code = 404, message = "Coupon not found") })
	public Response getAccount(@PathParam("couponId") final String couponId, @javax.ws.rs.core.Context final HttpServletRequest request) {
		return null;
	}

	@Timed
	@POST
	@Path("/plugins/killbill-coupon/coupons")
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	@ApiOperation(value = "Create coupon")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid coupon data supplied") })
	public Response createCoupon(final CouponJson json, @HeaderParam(HDR_CREATED_BY) final String createdBy, @HeaderParam(HDR_REASON) final String reason,
			@HeaderParam(HDR_COMMENT) final String comment, @javax.ws.rs.core.Context final HttpServletRequest request,
			@javax.ws.rs.core.Context final UriInfo uriInfo) {
		return null;
	}

	@Timed
	@PUT
	@Path("/plugins/killbill-coupon/coupons/{couponId:" + UUID_PATTERN + "}")
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	@ApiOperation(value = "Update coupon")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid coupon data supplied") })
	public Response updateCoupon(final UpdateCouponJson updateCoupon, @PathParam("couponId") final String couponId,
			@HeaderParam(HDR_CREATED_BY) final String createdBy, @javax.ws.rs.core.Context final HttpServletRequest request,
			@javax.ws.rs.core.Context final UriInfo uriInfo) {
		return null;

	}

	public static final String SUBSCRIPTIONS = "subscriptions";

	@Timed
	@POST
	@Path("/plugins/killbill-coupon/coupons/subscriptions")
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	@ApiOperation(value = "Redeem coupon")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid coupon data supplied") })
	public Response couponRedemption(final RedeemCouponJson json,
			@HeaderParam(HDR_CREATED_BY) final String createdBy, @javax.ws.rs.core.Context final HttpServletRequest request,
			@javax.ws.rs.core.Context final UriInfo uriInfo) {
		return null;
	}

	@Timed
	@GET
	@Path("/plugins/killbill-coupon/coupons")
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	@ApiOperation(value = "Retrieve coupons")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid coupon data supplied") })
	public Response getCoupons(@javax.ws.rs.core.Context final HttpServletRequest request, @javax.ws.rs.core.Context final UriInfo uriInfo) {
		return null;
	}

}
