package com.cmb.okr.api.controller;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cmb.okr.api.BaseController;
import com.cmb.okr.attachment.AttachmentService;
import com.cmb.okr.frame.base.JsonResponse;
import com.cmb.okr.frame.exception.AppException;

@RequestMapping("/file")
@RestController
public class FileController extends BaseController {

	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping("/upload")
	public JsonResponse upload(@RequestParam("file") MultipartFile file) {
		return doBusiness((res) -> {
			InputStream is;
			try {
				is = file.getInputStream();
			} catch (Exception e) {
				throw new AppException(e);
			}
			String id = attachmentService.putObject(file.getOriginalFilename(), is);
			res.setResult(id);
		}, "文件上传失败");
	}

	@RequestMapping(value = "/url", method = RequestMethod.GET)
	public JsonResponse getUrl(@RequestParam("id") String id) {
		return doBusiness((res) -> {
			res.setResult(attachmentService.getUrl(id));
		}, "获取资源url失败");
	}

}
