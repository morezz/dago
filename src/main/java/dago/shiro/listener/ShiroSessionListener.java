package dago.shiro.listener;


import dago.shiro.repository.CachingShiroSessionDao;
import dago.shiro.service.ShiroSessionService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class ShiroSessionListener implements SessionListener {


    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroSessionListener.class);

    private ShiroSessionService shiroSessionService;

    public void setShiroSessionService(ShiroSessionService shiroSessionService) {
        this.shiroSessionService = shiroSessionService;
    }

    private CachingShiroSessionDao sessionDao;

    public void setSessionDao(CachingShiroSessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    @Override
    public void onStart(@NotNull final Session session) {
        // 会话创建时触发
        LOGGER.info("session {} onStart", session.getId());
    }

    @Override
    public void onStop(@NotNull final Session session) {
        sessionDao.delete(session);
        shiroSessionService.sendUnCacheSessionMessage(session.getId());
        LOGGER.info("session {} onStop", session.getId());
    }

    @Override
    public void onExpiration(@NotNull final Session session) {
        sessionDao.delete(session);
        shiroSessionService.sendUnCacheSessionMessage(session.getId());
        LOGGER.info("session {} onExpiration", session.getId());
    }

}
