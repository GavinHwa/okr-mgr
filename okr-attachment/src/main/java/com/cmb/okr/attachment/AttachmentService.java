package com.cmb.okr.attachment;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.util.StringUtils;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.cmb.okr.attachment.conf.OSSConfig;
import com.cmb.okr.frame.exception.AppException;

@Component
public class AttachmentService {

	private static Logger logger = LoggerFactory.getLogger(AttachmentService.class);

	@Autowired
	private OSSClient ossClient;

	@Autowired
	private OSSConfig ossConfig;

	/**
	 * 文件上传
	 * 
	 * @param fileName
	 * @param is
	 * @return
	 */
	public String putObject(String fileName, InputStream is) {
		try {
			ObjectMetadata data = new ObjectMetadata();
			data.addUserMetadata("filename", fileName);
			String id = UUID.randomUUID().toString().concat(fileName);
			ossClient.putObject(ossConfig.getBucketName(), id, is, data);
			return id;
		} catch (Exception e) {
			logger.error("附件上传失败", e);
			throw new AppException("附件上传失败");
		}
	}

	/**
	 * 文件下载
	 * 
	 * @param id
	 * @return
	 */
	public AttachmentResponse getObject(String id) {
		try {
			OSSObject obj = ossClient.getObject(ossConfig.getBucketName(), id);
			if (obj == null) {
				return null;
			}
			AttachmentResponse response = new AttachmentResponse();
			InputStream is = obj.getObjectContent();
			response.setIs(is);
			ObjectMetadata data = obj.getObjectMetadata();
			if (data != null) {
				response.setFileName(data.getUserMetadata().get("filename"));
			}
			return response;
		} catch (Exception e) {
			logger.error("附件下载失败", e);
			throw new AppException("附件下载事变");
		}
	}

	/**
	 * 生成oss资源url
	 * 
	 * @param id
	 * @return
	 */
	public String getUrl(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		return ossClient
				.generatePresignedUrl(ossConfig.getBucketName(), id, DateUtils.addSeconds(new Date(), 60 * 60 * 12))
				.toString();
	}
}
