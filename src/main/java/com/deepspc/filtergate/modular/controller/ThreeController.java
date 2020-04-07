package com.deepspc.filtergate.modular.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Author didoguan
 * @Date 2020/3/13
 **/
@Controller
@RequestMapping("/three")
public class ThreeController {

	private String PREFIX = "/modular/three/";

	@RequestMapping("")
	public String monitorPage() {
		return PREFIX + "monitor.html";
	}
}
