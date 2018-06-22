#!/bin/bash

jarFileName=huashi-cloud-customer-0.1-SNAPSHOT.jar
cloudRoot=/opt/project/cloud
Url=http://47.96.108.175:8888/jenkins/view/liangkun_view/job

netstat -antp|grep 18001|grep LISTEN|awk '{print $7}'|awk  -F '/' '{print $1}'|xargs kill -9

cd ${cloudRoot}
rm -rf ${jarFileName} 

curl -u test:testtest -G -o ${jarFileName} ${Url}/spring_hs_cloud/ws/huashi-cloud-customer/target/${jarFileName}

chmod +x ./${jarFileName}

nohup java -jar ${cloudRoot}/${jarFileName} >/dev/null 2>&1 &

#[[ -L /etc/init.d/${jarFileName} ]] && service ${jarFileName} restart || ln -s ${cloudRoot}/${jarFileName}  /etc/init.d/${jarFileName} && service ${jarFileName} restart