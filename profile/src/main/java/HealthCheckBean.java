
import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.inject.Inject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by Aljaz on 22/11/2017.
 */

@Health
@ApplicationScoped
@ConfigBundle("profile-config")
public class HealthCheckBean implements HealthCheck {

    private static final String url = "https://google.si";

    private static final Logger LOG = Logger.getLogger(HealthCheckBean.class.getSimpleName());

    @Override
    public HealthCheckResponse call() {
//        if (ConfigurationUtil.getInstance().getBoolean("is-running").isPresent() && !ConfigurationUtil.getInstance().getBoolean("is-running").get()) {
//            return HealthCheckResponse.named(HealthCheckBean.class.getSimpleName()).down().build();
//        }
        if (!ProfileResource.isRunning()) {
            return HealthCheckResponse.named(HealthCheckBean.class.getSimpleName()).down().build();
        }
        try {

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");

            if (connection.getResponseCode() == 200) {
                return HealthCheckResponse.named(HealthCheckBean.class.getSimpleName()).up().build();
            }
        } catch (Exception exception) {
            LOG.severe(exception.getMessage());
        }
        return HealthCheckResponse.named(HealthCheckBean.class.getSimpleName()).down().build();
    }
}