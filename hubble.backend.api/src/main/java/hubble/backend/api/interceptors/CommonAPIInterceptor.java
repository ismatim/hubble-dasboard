package hubble.backend.api.interceptors;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CommonAPIInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = Logger.getLogger(CommonAPIInterceptor.class.getName());

    public CommonAPIInterceptor() {
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        logger.log(Level.INFO, "Request URL::{0} Sent to Handler :: Current Time={1}",
                new Object[]{request.getRequestURL().toString(), System.currentTimeMillis()});
    }
}
