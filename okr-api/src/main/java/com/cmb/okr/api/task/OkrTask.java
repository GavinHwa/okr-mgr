package com.cmb.okr.api.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cmb.okr.frame.auth.OkrContext;
import com.cmb.okr.frame.auth.SessionBean;

@Component
public class OkrTask {

	/**
	 * 删除失效的session
	 */
	@Scheduled(fixedRate = 10000)
	public void expireSession() {
		long currentTime = System.currentTimeMillis();
		for (String key : OkrContext.sessions.keySet()) {
			SessionBean session = OkrContext.sessions.get(key);
			if (session == null) {
				continue;
			}
			if (session.getExpire() <= currentTime) {
				OkrContext.sessions.remove(key);
			}
		}
	}
}
