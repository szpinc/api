package org.szpinc.api.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;


public class BaseController {
	
	protected final Log log = LogFactory.getLog(BaseController.class);

	public BaseController() throws LogConfigurationException {
	}
}
