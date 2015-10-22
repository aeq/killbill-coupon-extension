package org.killbill.billing.entitlement.api;

import java.util.UUID;

import javax.inject.Inject;

import org.killbill.billing.ErrorCode;
import org.killbill.billing.entitlement.plugin.api.EntitlementContext;
import org.killbill.billing.entitlement.plugin.api.EntitlementPluginApi;
import org.killbill.billing.entitlement.plugin.api.EntitlementPluginApiException;
import org.killbill.billing.entitlement.plugin.api.OnFailureEntitlementResult;
import org.killbill.billing.entitlement.plugin.api.OnSuccessEntitlementResult;
import org.killbill.billing.entitlement.plugin.api.PriorEntitlementResult;
import org.killbill.billing.osgi.api.OSGIServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Provider;

/**
 * The customized EntitlementPluginExecution.  
 * @author Kehuan
 *
 */
public class CouponEntitlementPluginExecution extends EntitlementPluginExecution {
	
	private static final Logger log = LoggerFactory.getLogger(CouponEntitlementPluginExecution.class);

    private final EntitlementApi entitlementApi;
    private final OSGIServiceRegistration<EntitlementPluginApi> pluginRegistry;

	@Inject
    public CouponEntitlementPluginExecution(final Provider<EntitlementApi> entitlementApi, final Provider<OSGIServiceRegistration<EntitlementPluginApi>> pluginRegistry) {
		super(entitlementApi.get(), pluginRegistry.get());
        this.entitlementApi = entitlementApi.get();
        this.pluginRegistry = pluginRegistry.get();
    }
	
	@Override
	public <T> T executeWithPlugin(final WithEntitlementPlugin<T> callback, final EntitlementContext pluginContext) throws EntitlementApiException {
        try {
            final PriorEntitlementResult priorEntitlementResult = executePluginPriorCalls(pluginContext);
            if (priorEntitlementResult != null && priorEntitlementResult.isAborted()) {
                throw new EntitlementApiException(ErrorCode.ENT_PLUGIN_API_ABORTED);
            }
            final EntitlementContext updatedPluginContext = new DefaultEntitlementContext(pluginContext, priorEntitlementResult);
            try {
                T result = callback.doCall(entitlementApi, updatedPluginContext);
                executePluginOnSuccessCalls(getSuccessfulEntitlementContext(updatedPluginContext, result));
                return result;
            } catch (final EntitlementApiException e) {
                executePluginOnFailureCalls(updatedPluginContext);
                throw e;
            }
        } catch (final EntitlementPluginApiException e) {
            throw new EntitlementApiException(ErrorCode.ENT_PLUGIN_API_ABORTED, e.getMessage());
        }
	}
	
	
	private PriorEntitlementResult executePluginPriorCalls(final EntitlementContext entitlementContextArg) throws EntitlementPluginApiException {

        // Return as soon as the first plugin aborts, or the last result for the last plugin
        PriorEntitlementResult prevResult = null;

        EntitlementContext currentContext = entitlementContextArg;
        for (final String pluginName : pluginRegistry.getAllServices()) {
            final EntitlementPluginApi plugin = pluginRegistry.getServiceForName(pluginName);
            if (plugin == null) {
                // First call to plugin, we log warn, if plugin is not registered
                log.warn("Skipping unknown entitlement control plugin {} when fetching results", pluginName);
                continue;
            }
            prevResult = plugin.priorCall(currentContext, currentContext.getPluginProperties());
            if (prevResult.isAborted()) {
                break;
            }
            currentContext = new DefaultEntitlementContext(currentContext, prevResult);
        }
        return prevResult;
    }

    private OnSuccessEntitlementResult executePluginOnSuccessCalls(final EntitlementContext context) throws EntitlementPluginApiException {
        for (final String pluginName : pluginRegistry.getAllServices()) {
            final EntitlementPluginApi plugin = pluginRegistry.getServiceForName(pluginName);
            if (plugin != null) {
                plugin.onSuccessCall(context, context.getPluginProperties());
            }
        }
        return null;
    }

    private OnFailureEntitlementResult executePluginOnFailureCalls(final EntitlementContext context) throws EntitlementPluginApiException {
        for (final String pluginName : pluginRegistry.getAllServices()) {
            final EntitlementPluginApi plugin = pluginRegistry.getServiceForName(pluginName);
            if (plugin != null) {
                plugin.onFailureCall(context, context.getPluginProperties());
            }
        }
        return null;
    }
    
    /*
     * TODO: This is a temporary solution. We create a new EntitlementContext and set destinationAccountId to be the new entitlementId. This
     * is because the original EntitlementContext does not have the information of entitlementId, but we need it when the Entitlement is created 
     * successfully. And we can't wait for the OSGIKillbillEventHandler, it is too late. We need the entitlementId before the first invoice is 
     * generated. 
     */
	private EntitlementContext getSuccessfulEntitlementContext(EntitlementContext updatedPluginContext, Object result) {
		UUID entitlementId = null;
		if (result instanceof Entitlement) {
			Entitlement entitlement = (Entitlement) result;
			entitlementId = entitlement.getId();
		}
		
		
		return new DefaultEntitlementContext(updatedPluginContext.getOperationType(), updatedPluginContext.getAccountId(), entitlementId,
				updatedPluginContext.getBundleId(), updatedPluginContext.getPlanPhaseSpecifier(), updatedPluginContext.getExternalKey(),
				updatedPluginContext.getPlanPhasePriceOverride(), updatedPluginContext.getEffectiveDate(), updatedPluginContext.getPluginProperties(),
				updatedPluginContext.getUserToken(), updatedPluginContext.getUserName(), updatedPluginContext.getCallOrigin(),
				updatedPluginContext.getUserType(), updatedPluginContext.getReasonCode(), updatedPluginContext.getComments(),
				updatedPluginContext.getCreatedDate(), updatedPluginContext.getUpdatedDate(), updatedPluginContext.getTenantId());

	}
}
