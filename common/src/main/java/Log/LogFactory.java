package Log;


import java.util.logging.Logger;

public class LogFactory {
    private final Logger logger;

    public LogFactory(Class<?> clazz){
        this.logger = Logger.getLogger(clazz.getName());
    }

    public void debugLog(String info){
        logger.info(info);
    }
}
