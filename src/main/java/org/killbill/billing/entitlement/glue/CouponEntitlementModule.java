package org.killbill.billing.entitlement.glue;

import org.killbill.billing.entitlement.api.CouponEntitlementPluginExecution;
import org.killbill.billing.entitlement.api.EntitlementPluginExecution;
import org.killbill.billing.entitlement.plugin.api.EntitlementPluginApi;
import org.killbill.billing.osgi.api.OSGIServiceRegistration;
import org.killbill.billing.platform.api.KillbillConfigSource;

import com.google.inject.TypeLiteral;

public class CouponEntitlementModule extends DefaultEntitlementModule {

	public CouponEntitlementModule(KillbillConfigSource configSource) {
		super(configSource);
	}

	
	@Override
	protected void installEntitlementPluginApi() {
        bind(new TypeLiteral<OSGIServiceRegistration<EntitlementPluginApi>>() {}).toProvider(DefaultEntitlementProviderPluginRegistryProvider.class).asEagerSingleton();
        bind(EntitlementPluginExecution.class).to(CouponEntitlementPluginExecution.class).asEagerSingleton();
    }
}
