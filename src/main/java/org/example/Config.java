package org.example;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduce;
import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduceClientBuilder;
import io.awspring.cloud.autoconfigure.context.properties.AwsRegionProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class Config {

  private final DefaultAWSCredentialsProviderChain providerChain;
  private final AwsRegionProperties regionProperties;

  @Bean
  public AmazonElasticMapReduce emr() {
    final Regions region = Regions.fromName(regionProperties.getStatic());
    log.info("region: {}", region);
    return AmazonElasticMapReduceClientBuilder.standard()
        .withCredentials(providerChain)
        .withRegion(region)
        .build();
  }
}
