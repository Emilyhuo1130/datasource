package com.zhang.aws.s3;

import java.util.List;

import org.apache.log4j.Logger;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.google.gson.Gson;
import com.zhang.aws.util.AmazonFactory;

public class S3BucketTest {
	static Logger logger=Logger.getLogger(S3BucketTest.class);
	static String bucket="elasticbeanstalk-us-west-2-948206320069";
	static String destinationBucket="zhangyinhaobucket";
	static AmazonS3 s3 = AmazonFactory.getAmazonS3();
	static Gson gson=new Gson();
	/****
	 * 桶的操作
	 * **/
	public static void main(String[] args) {
//		createBucket();
		
//		deleteBucket();
		
//		listBucket();
	}
	
	
	
	
	/***
	 * 创建桶
	 * **/
	public static void createBucket(){
		s3.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));
		try{
			Bucket createBucket = s3.createBucket("zhangapsoutheast1bucket");//创建失败抛异常
			logger.info("bucket owner:   "+createBucket.getOwner());
		}catch(AmazonServiceException ase){
			ase.printStackTrace();
		}
	}
	
	/**
	 * 列出数据桶 
	 * **/
	public static void listBucket(){
		List<Bucket> listBuckets = s3.listBuckets();
		for(Bucket bucket:listBuckets){
			logger.info("bucket name:         "+bucket.getName());
			logger.info("bucket createDate:   "+bucket.getCreationDate());
			logger.info("bucket woner:        "+bucket.getOwner());
			logger.info("===================================");
		}
	}
	/***
	 * 删除桶
	 * **/
	public static void deleteBucket(){
		//验证数据桶是否存在
		boolean doesBucketExist = s3.doesBucketExist("zhangapsoutheast1bucket");
		if(doesBucketExist){
			s3.deleteBucket("zhangapsoutheast1bucket");
		}
	}
	
}
