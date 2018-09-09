package com.luanoliveira.cursomc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {
	
	@Value("${s3.region}")
	private String region;
	
	@Bean
	public AmazonS3 s3client() {
		
		String AWS_KEY = System.getenv("AWS_ACCESS_KEY");
		String AWS_SECRET = System.getenv("AWS_SECRET_ACCESS_KEY");
		
		BasicAWSCredentials awsCred = new BasicAWSCredentials(AWS_KEY, AWS_SECRET);
		AmazonS3 s3client = AmazonS3ClientBuilder
				.standard()
				.withRegion(Regions.fromName(region))
				.withCredentials(new AWSStaticCredentialsProvider(awsCred))
				.build();
		
		return s3client;
	}
}
