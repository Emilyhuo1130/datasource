package com.zhang.aws.sqs;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.zhang.aws.util.CredentialsUtil;

public class DeliveryQueue {
	/***
	 * 
	 * 延迟列队
	 * */
	static Logger logger=Logger.getLogger(DeliveryQueue.class);
	public static void main(String[] args) {
		AWSCredentials credentials = CredentialsUtil.getCredentials();
		AmazonSQS sqs=new AmazonSQSClient(credentials);
		sqs.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));
		//创建延迟队列
		Map<String,String> attributes = new HashMap();
		attributes.put("DelaySeconds", "30");//列队延迟30秒
		CreateQueueResult createQueue = sqs.createQueue(new CreateQueueRequest()
		//添加属性
		.withQueueName("DeliveryQueue")
		.withAttributes(attributes)
		);
		
		//发送消息
//		sqs.sendMessage(createQueue.getQueueUrl(), "延迟列队消息。。。。。。");
		sqs.sendMessage(sqs.getQueueUrl("DeliveryQueue").getQueueUrl(), "延迟列队消息。。。。。。3");
		
		logger.info("--------------------------");

	}

}
