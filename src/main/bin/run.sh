#!/usr/bin/env bash

binDir=$(cd "$(dirname "$0")"; pwd)
baseDir=$(readlink -f $binDir/../)
libDir=$baseDir/lib
confDir=$baseDir/conf
webDir=$baseDir/webapp

CLASSPATH=""
for jarPath in $libDir/*.jar; do
 CLASSPATH=$CLASSPATH:$jarPath
done

CLASSPATH=$CLASSPATH:$confDir
CLASSPATH=$CLASSPATH:$webDir

export CLASSPATH
export LANG="zh_CN.UTF-8"

cd $baseDir

if [[ "$JAVA_HOME" == "" ]]; then
  JAVA_HOME=$(ls /usr/jdk64/jdk* -d 2>/dev/null | xargs | awk '{print $1}')
fi
export PATH=$JAVA_HOME/bin:$PATH

command="-Dfile.encoding="UTF-8" -Dorg.eclipse.jetty.util.URI.charset="UTF-8" -Duser.language="Zh" -Duser.region="CN" -Duser.timezone="GMT+08" -Xms1024m -Xmx2048m com.tencent.tbds.alert.AlertMain"

is_test=$TEST
mkdir -p $baseDir/log
if [[ "$is_test" == "true" ]]; then
  java -Dspring.profiles.active="dev" $command >/dev/null 2>$baseDir/log/error.log &
else
  java $command >/dev/null 2>$baseDir/log/error.log &
  # java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=8080 $command
fi