package sword.ios.app.utils;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

	public class LoggerFactory {

	    private static Logger logger = Logger.getLogger("com.taobao.tao.utils.LoggerFactory");
	    static{
	        try {
	            FileHandler fh = new FileHandler("tao.log", true);
	            fh.setFormatter(new SimpleFormatter());
	            logger.addHandler(fh);
	            logger.setLevel(Level.INFO);
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

	    public LoggerFactory(){
	        super();
	       
	    }

	    public static void log(Level level, String msg) {
	        logger.log(level, msg);
	    }
	    
	    public static void log(Level level, String sourceClass, String sourceMethod, String msg){
	        logger.logp(level, sourceClass, sourceMethod, msg);
	    }

	}

