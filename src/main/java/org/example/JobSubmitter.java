package org.example;

import static com.amazonaws.services.elasticmapreduce.model.ActionOnFailure.CONTINUE;

import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduce;
import com.amazonaws.services.elasticmapreduce.model.AddJobFlowStepsRequest;
import com.amazonaws.services.elasticmapreduce.model.AddJobFlowStepsResult;
import com.amazonaws.services.elasticmapreduce.model.HadoopJarStepConfig;
import com.amazonaws.services.elasticmapreduce.model.StepConfig;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobSubmitter {

  private final String EMR_CLUSTER_ID = "j-3HQT2QLJF82LW";

  private final AmazonElasticMapReduce emr;

  public void submitJob(String jobName) {
    log.info("Submitting job: {}", jobName);

    final List<String> sparkSubmitArgs =
        List.of(
            "spark-submit",
            "--deploy-mode",
            "cluster",
            "--master",
            "yarn",
            "--driver-memory",
            "1g",
            "--executor-memory",
            "1g",
            "--conf",
            "spark.dynamicAllocation.enabled=false",
            "--conf",
            "spark.yarn.maxAppAttempts=1",
            "s3://test-emr-code-us-east-1/code/pyspark-add-month.py",
            "add-months");

    HadoopJarStepConfig hadoopConfig =
        new HadoopJarStepConfig().withJar("command-runner.jar").withArgs(sparkSubmitArgs);
    StepConfig hadoopStep = new StepConfig(jobName, hadoopConfig).withActionOnFailure(CONTINUE);

    AddJobFlowStepsResult result =
        emr.addJobFlowSteps(
            new AddJobFlowStepsRequest().withJobFlowId(EMR_CLUSTER_ID).withSteps(hadoopStep));
    log.info("result: {}", result);
  }
}
