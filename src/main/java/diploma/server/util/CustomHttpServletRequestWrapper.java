package diploma.server.util;

import diploma.server.model.type.CustomSession;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private CustomSession session;

    public CustomHttpServletRequestWrapper(HttpServletRequest req) {
        super(req);
    }

    public void setCustomSession(CustomSession session) {
        this.session = session;
    }

    public CustomSession getCustomSession() {
        return this.session;
    }
}
