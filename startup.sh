#!/bin/bash
cd /home/ec2-user
aws s3 cp s3://infinotes-cicd-bucket/infinotes-0.0.1-SNAPSHOT.jar
java -jar infinotes-0.0.1-SNAPSHOT.jar