package com.cmb.okr.attachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aliyun.oss.OSSClient;
import com.cmb.okr.attachment.conf.OSSConfig;

@Configuration
public class AttachmentFactory {

	@Autowired
	private OSSConfig ossConfig;

	@Bean
	public OSSClient ossClient() {
		OSSClient ossClient = new OSSClient(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(),
				ossConfig.getAccessKeySecret());
		if (!ossClient.doesBucketExist(ossConfig.getBucketName())) {
			ossClient.createBucket(ossConfig.getBucketName());
		}
		return ossClient;
	}

}
