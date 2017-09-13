package hubble.frontend.web.interceptors;

import hubble.frontend.business.interfaces.BusinessApplicationManager;
import hubble.frontend.business.models.BusinessApplication;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CommonModelViewInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = Logger.getLogger(CommonModelViewInterceptor.class.getName());
    private BusinessApplicationManager availabilityManager;

    public CommonModelViewInterceptor(BusinessApplicationManager availabilityManager) {
        this.availabilityManager = availabilityManager;
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        if (modelAndView == null) {
            return;
        }

        List<BusinessApplication> applications = availabilityManager.getAllApplications();
        modelAndView.addObject("applications", applications);

        logger.log(Level.INFO, "Request URL::{0} Sent to Handler :: Current Time={1}",
                new Object[]{request.getRequestURL().toString(), System.currentTimeMillis()});
    }
}
