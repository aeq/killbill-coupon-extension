package org.killbill.billing.server.listeners;

import javax.servlet.ServletContext;

import org.killbill.billing.entitlement.api.CouponEntitlementPluginExecution;
import org.killbill.billing.entitlement.api.EntitlementPluginExecution;
import org.killbill.billing.entitlement.glue.CouponEntitlementModule;
import org.killbill.billing.entitlement.glue.DefaultEntitlementModule;
import org.killbill.billing.server.modules.KillbillServerModule;

import com.google.inject.Binder;
import com.google.inject.Module;

public class KillbillCouponGuiceListener extends KillbillGuiceListener {

	@Override
    protected Module getModule(final ServletContext servletContext) {
		return new KillbillServerModule(servletContext, config, configSource){
			
			@Override
			protected void install(Module module) {
				if (module instanceof DefaultEntitlementModule) {
					super.install(new CouponEntitlementModule(configSource));
				} else {
					super.install(module);
				}
			}
		};
    }
}


class CouponModule implements Module {
	/**
	 * Override the default binding for EntitlementPluginExecution.
	 */
    public void configure(Binder binder) {
    	binder.bind(EntitlementPluginExecution.class).to(CouponEntitlementPluginExecution.class).asEagerSingleton();
    }
}