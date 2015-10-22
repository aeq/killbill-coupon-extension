# killbill-coupon-extension
The extension for the coupon feature on Killbill. This extension must be used with  [Coupon-plugin-for-KillBill](https://github.com/aeq/Coupon-plugin-for-KillBill). It has following features
* Swagger integration. Enable the coupon operations shown on the SwaggerUI.
* Customized the parameters that pass to the Coupon plugin when an entitlement is created successfully.



# How to setup
* Add the dependency to the the [killbill/profiles/killbill/pom.xml](https://github.com/killbill/killbill/blob/master/profiles/killbill/pom.xml).

        <dependency>
            <groupId>ca.aeq</groupId>
            <artifactId>couponjson</artifactId>
            <version>0.15.2</version>
        </dependency>

* Change the listener in [web.xml](https://github.com/killbill/killbill/blob/master/profiles/killbill/src/main/webapp/WEB-INF/web.xml) to KillbillCouponGuiceListener (from KillbillGuiceListener).

        <listener>
            <!-- Context listener: called at startup time and creates the injector -->
            <listener-class>org.killbill.billing.server.listeners.KillbillCouponGuiceListener</listener-class>
        </listener>
     
* Install the [Coupon-plugin-for-KillBill](https://github.com/aeq/Coupon-plugin-for-KillBill)
     

For more information, please contact [Aequilibrium](http://www.aequilibrium.ca)








