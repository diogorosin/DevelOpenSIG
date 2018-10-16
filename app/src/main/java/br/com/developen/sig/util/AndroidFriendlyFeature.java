package br.com.developen.sig.util;

import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.Filter;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

public class AndroidFriendlyFeature implements Feature {

    public boolean configure(FeatureContext context) {

        context.register(new AbstractBinder() {
            protected void configure() {
                addUnbindFilter(new Filter() {

                    public boolean matches(Descriptor d) {
                        String implClass = d.getImplementation();
                        return implClass.startsWith("org.glassfish.jersey.message.internal.DataSource") ||
                                implClass.startsWith("org.glassfish.jersey.message.internal.RenderedImage");
                    }

                });
            }
        });

        return true;

    }

}