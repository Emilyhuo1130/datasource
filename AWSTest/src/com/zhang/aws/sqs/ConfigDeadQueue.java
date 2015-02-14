package com.zhang.aws.sqs;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.zhang.aws.util.AmazonFactory;
import com.zhang.aws.util.CredentialsUtil;

public class ConfigDeadQueue {
	static Logger logger=Logger.getLogger(ConfigDeadQueue.class);
	/**
	 * 
	 * 给一个列队配置死信列队(更新Attributes信息)
	 * 当消息无法送达（超过一定时间还没有被接受那么将会进入死信列队）
	 * **/
	public static void main(String[] args) {
		String redrivePolicy = "{\"maxReceiveCount\":\"5\", \"deadLetterTargetArn\":\"arn:aws:sqs:ap-southeast-1:948206320069:MyDeadQueue\"}";
		AmazonSQS sqs = AmazonFactory.getAmazonSQS();
		Map<String,String> attributes=new HashMap();
		attributes.put("RedrivePolicy", redrivePolicy);
		attributes.put("VisibilityTimeout", "25");
		
		sqs.setQueueAttributes(sqs.getQueueUrl("queueTest").getQueueUrl(), attributes);
		
		logger.info("------------------------------------");
	}

}
