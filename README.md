# AWS EMR - Spring Boot
Submit jobs in EMR cluster using Spring Boot
- Spring Boot application should have the following policies to submit job:
  - elasticmapreduce:RunJobFlow
  - elasticmapreduce:AddJobFlowSteps
  - elasticmapreduce:DescribeJobFlows
  - elasticmapreduce:CancelSteps

### Create EMR Cluster
Use below command to create EMR cluster, use any private subnet:
```
aws emr create-cluster --name test-emr-cluster --use-default-roles --release-label emr-5.28.0 
    --instance-count 2 --instance-type m4.xlarge --applications Name=JupyterHub Name=Spark Name=Hadoop 
    --ec2-attributes KeyName=emr-cluster,SubnetIds=['<private subnet id>'] 
    --log-uri s3://<s3 bucket name>/
```
