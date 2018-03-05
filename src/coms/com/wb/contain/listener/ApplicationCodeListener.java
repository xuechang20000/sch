package com.wb.contain.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.wb.contain.listener.model.APPAA09;
import com.wb.contain.listener.model.APPAA10;
import com.wb.jdbcutils.CommonJdbcUtils;

public class ApplicationCodeListener implements ServletContextListener {
	private static Logger logger = Logger.getLogger(ApplicationCodeListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {

	}
 
	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		logger.info("loading code begin...");
		ServletContext context = contextEvent.getServletContext();
		List<APPAA09> aa09s = CommonJdbcUtils.query("select * from app_aa09 where aae100='1'", APPAA09.class);
		if (aa09s != null) {
			String aa10sql = "select * from app_aa10 where aaa100=? and aae100='1' order by cae008 asc";
			for (int i = 0; i < aa09s.size(); i++) {
				List<APPAA10> aa10s = CommonJdbcUtils.query(aa10sql, APPAA10.class, aa09s.get(i).getAaa100());
				if (aa10s != null && aa10s.size() > 0) {
					if (aa09s.get(i).getAaa100() != null)
						context.setAttribute(aa09s.get(i).getAaa100().toUpperCase(), aa10s);
				}
			}
		}
		logger.info("loading code end...");
	}

}
