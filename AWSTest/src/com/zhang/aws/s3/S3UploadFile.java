package com.zhang.aws.s3;



import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class S3UploadFile {
	static Logger logger = Logger.getLogger(S3UploadFile.class);
	public static void main(String[] args) throws IOException {
		File f=new File("e:/222.jpg");
		String uploadToS3 = uploadToS3(f,"test");
		System.out.println(uploadToS3);

	}
	
    public static String uploadToS3(File tempFile, String remoteFileName) throws IOException {
//    	Properties properties=new Properties();
    	ResourceBundle bundle=ResourceBundle.getBundle("credentials");
        //é¦–å…ˆåˆ›å»ºä¸€ä¸ªs3çš„å®¢æˆ·ç«¯æ“�ä½œå¯¹è±¡ï¼ˆéœ€è¦�amazonæ��ä¾›çš„å¯†é’¥ï¼‰
        AmazonS3 s3 = new AmazonS3Client(
                new BasicAWSCredentials(bundle.getString("aws_access_key_id"),
                        bundle.getString("aws_secret_access_key")));
        Region usWest2 = Region.getRegion(Regions.US_WEST_2);
        s3.setRegion(usWest2);
        //è®¾ç½®bucket,key
        String bucketName ="zhangyinhaobucket";
        String key = UUID.randomUUID() + ".apk";
        try {
            //éªŒè¯�å��ç§°ä¸ºbucketNameçš„bucketæ˜¯å�¦å­˜åœ¨ï¼Œä¸�å­˜åœ¨åˆ™åˆ›å»º
            if (!checkBucketExists(s3, bucketName)) {
                s3.createBucket(bucketName);
            }
            //ä¸Šä¼ æ–‡ä»¶
            s3.putObject(new PutObjectRequest(bucketName, key, tempFile));
            S3Object object = s3.getObject(new GetObjectRequest(bucketName, key));
            //èŽ·å�–ä¸€ä¸ªrequest
            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(
                    bucketName, key);
            Date expirationDate = null;
            try {
                expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-31");
            } catch (Exception e) {
                e.printStackTrace();
            }
            //è®¾ç½®è¿‡æœŸæ—¶é—´
            urlRequest.setExpiration(expirationDate);
            //ç”Ÿæˆ�å…¬ç”¨çš„url
            URL url = s3.generatePresignedUrl(urlRequest);
            System.out.println("=========URL=================" + url + "============URL=============");
            if (url == null) {
            	logger.info("can't get s3 file url!");
//                throw new OperateFailureException("can't get s3 file url!");
            }
            return url.toString();
        } catch (AmazonServiceException ase) {
            ase.printStackTrace();
            logger.info("====================================AWS S3 UPLOAD ERROR START======================================");
            logger.info("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            logger.info("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            logger.info("Error Message:    " + ase.getMessage());
            logger.info("HTTP Status Code: " + ase.getStatusCode());
            logger.info("AWS Error Code:   " + ase.getErrorCode());
            logger.info("Error Type:       " + ase.getErrorType());
            logger.info("Request ID:       " + ase.getRequestId());
            logger.info(ase.getMessage(), ase);
            logger.info("====================================AWS S3 UPLOAD ERROR END======================================");
//            throw new OperateFailureException("error occurs during upload to s3!");
        } catch (AmazonClientException ace) {
            logger.info("====================================AWS S3 UPLOAD ERROR START======================================");
            logger.info("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            logger.info("Error Message: " + ace.getMessage());
            logger.info("====================================AWS S3 UPLOAD ERROR END======================================");
//            throw new OperateFailureException("error occurs during upload to s3!");
        }
        return null;
    }

    /**
     * éªŒè¯�s3ä¸Šæ˜¯å�¦å­˜åœ¨å��ç§°ä¸ºbucketNameçš„Bucket
     * @param s3
     * @param bucketName
     * @return
     */
    public static boolean checkBucketExists (AmazonS3 s3, String bucketName) {
        List<Bucket> buckets = s3.listBuckets();
        for (Bucket bucket : buckets) {
            if (Objects.equals(bucket.getName(), bucketName)) {
                return true;
            }
        }
        return false;
    }

}
