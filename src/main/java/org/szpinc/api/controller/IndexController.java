package org.szpinc.api.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	private static final Log log = LogFactory.getLog(IndexController.class);
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index () {
		if (log.isDebugEnabled()) {
			log.debug("跳转至主页");
		}
		return "index";
	}
}
