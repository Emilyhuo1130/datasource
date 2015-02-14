package com.zhang.aws.sqs;

import org.apache.log4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.zhang.aws.util.CredentialsUtil;

public class MessageTimmer {
	static Logger logger=Logger.getLogger(MessageTimmer.class);
	/***
	 * 消息定时器
	 * */
	public static void main(String[] args) {
		AWSCredentials credentials = CredentialsUtil.getCredentials();
		AmazonSQS sqs=new AmazonSQSClient(credentials);
		sqs.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));
		sqs.sendMessage(new SendMessageRequest()
			.withDelaySeconds(15)
			.withQueueUrl(sqs.getQueueUrl("queueTest").getQueueUrl())
			.withMessageBody("定时器消息。。。。。"));

	}

}
