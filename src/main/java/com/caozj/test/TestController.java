package com.caozj.test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.caozj.annotation.TimeAnnotation;
import com.caozj.dubbo.provider.DubboServiceInterface;
import com.caozj.framework.util.ApplicationContextUtil;
import com.caozj.framework.util.schedule.ScheduleUtil;

@Controller
@RequestMapping("/test")
public class TestController {

	private static final Log logger = LogFactory.getLog(TestController.class);

	@Autowired
	private TestService testService;

	@Resource
	private DubboServiceInterface dubboRemoteService;

	@TimeAnnotation(overtime = 30)
	@RequestMapping("testChinese.do")
	public String testChinese(String info, ModelMap model) {
		System.out.println("收到信息:" + info);
		model.put("message", info);
		return "message";
	}

	@TimeAnnotation
	@RequestMapping
	public String testTaglib(String info, ModelMap model) {
		model.put("message", info);
		return "test/taglib";
	}

	@TimeAnnotation(overtime = 70)
	@PostConstruct
	public String init() {
		return "1";
	}

	@RequestMapping("/chatOnly.do")
	public String chatOnly() {
		return "chat/only";
	}

	@RequestMapping("/chatBoth.do")
	public String chatBoth() {
		return "chat/both";
	}

	@RequestMapping
	public String imagePreview() {
		return "test/imagePreview";
	}

	@RequestMapping
	public String uploadImages(MultipartHttpServletRequest request, ModelMap model) throws IOException {
		Map<String, MultipartFile> filesMap = request.getFileMap();
		System.out.println(filesMap.keySet().toString());
		for (Map.Entry<String, MultipartFile> entry : filesMap.entrySet()) {
			MultipartFile file = entry.getValue();
			FileUtils.writeByteArrayToFile(new File("d:/test/" + System.currentTimeMillis() + file.getOriginalFilename()), file.getBytes());
		}
		model.put("message", filesMap.keySet().toString() + "上传成功");
		return "message";
	}

	@RequestMapping
	public String showSlider() {
		return "test/slider";
	}

	@RequestMapping
	public String showMove() {
		return "test/move";
	}

	@RequestMapping
	public String addJob(ModelMap model) throws SchedulerException {
		ScheduleUtil.addJob("job1", TestSchedule.class, "test", "0 0/1 * * * * ");
		ScheduleUtil.addJob("job2", TestSchedule.class, "test", "30 0/1 * * * * ");
		model.put("message", "job add success!!!");
		return "message";
	}

	@RequestMapping
	public String exeJob(ModelMap model) {
		TestSchedule t = (TestSchedule) ApplicationContextUtil.getBean("job1");
		t.test();
		model.put("message", "execute add success!!!");
		return "message";
	}

	@RequestMapping
	public String dubbo(String info, ModelMap model) {
		String message = dubboRemoteService.test(info);
		model.put("message", message);
		return "message";
	}

	@RequestMapping
	public String cache(String info, ModelMap model) {
		testService.testCache(info);
		model.put("message", info);
		return "message";
	}

	@RequestMapping
	public String cache4(String info, ModelMap model) {
		testService.testCache4(info);
		model.put("message", info);
		return "message";
	}

	@RequestMapping
	public String cache3(String info, ModelMap model) {
		testService.testCache3(info);
		model.put("message", info);
		return "message";
	}

	@RequestMapping
	public String cache2(String info, ModelMap model) {
		testService.testCache2(info);
		model.put("message", info);
		return "message";
	}

	@RequestMapping
	public String clearCache(ModelMap model) {
		testService.clearCache();
		model.put("message", "clear successfully!!!");
		return "message";
	}

	@RequestMapping
	public String clear(String info, ModelMap model) {
		testService.clear(info);
		model.put("message", info);
		return "message";
	}

}
