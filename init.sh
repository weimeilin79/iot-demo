#!/bin/sh 
DEMO="JBoss A-MQ Biomedical IoT Demo"
VERSION=6.1.0
AUTHORS="Christina Lin"
PROJECT="git@github.com:weimeilin79/amqp-demo-web-biomedical.git"
AMQ=jboss-a-mq-6.1.0.redhat-379
AMQ_BIN=jboss-a-mq-6.1.0.redhat-379.zip
DEMO_HOME=./target
AMQ_HOME=$DEMO_HOME/$AMQ
SERVER_CONF=$AMQ_HOME/etc
SERVER_BIN=$AMQ_HOME/bin
SRC_DIR=./installs
PRJ_DIR=./projects/amqp-example-web
PRJ2_DIR=./projects/mqtt-drools
# wipe screen.
clear 

# add executeable in installs
chmod +x installs/*.zip


echo
echo "#################################################################"
echo "##                                                             ##"   
echo "##  Setting up the ${DEMO}   ##"
echo "##                                                             ##"   
echo "##                                                             ##"   
echo "##                 ###        #   #   ###                      ##"
echo "##                #   #       ## ##  #   #                     ##"
echo "##                #####  ###  # # #  #   #                     ##"
echo "##                #   #       #   #  #  ##                     ##"
echo "##                #   #       #   #   #####                    ##"
echo "##                                                             ##"   
echo "##                                                             ##"   
echo "##  brought to you by,                                         ##"   
echo "##                    ${AUTHORS}                            ##"
echo "##                                                             ##"   
echo "##  ${PROJECT}    ##"
echo "##                                                             ##"   
echo "#################################################################"
echo

# double check for maven.
command -v mvn -q >/dev/null 2>&1 || { echo >&2 "Maven is required but not installed yet... aborting."; exit 1; }

# make some checks first before proceeding.	
if [[ -r $SRC_DIR/$AMQ_BIN || -L $SRC_DIR/$AMQ_BIN ]]; then
		echo $DEMO sources are present...
		echo
else
		echo Need to download $AMQ_BIN package from the Customer Support Portal 
		echo and place it in the $SRC_DIR directory to proceed...
		echo
		exit
fi


# Create the target directory if it does not already exist.
if [ ! -x $DEMO_HOME ]; then
		echo "  - creating the demo home directory..."
		echo
		mkdir $DEMO_HOME
else
		echo "  - detected demo home directory, moving on..."
		echo
fi


# Move the old JBoss instance, if it exists, to the OLD position.
if [ -x $AMQ_HOME ]; then
		echo "  - existing JBoss AMQ detected..."
		echo
		echo "  - moving existing JBoss AMQ aside..."
		echo
		rm -rf $AMQ_HOME.OLD
		mv $AMQ_HOME $AMQ_HOME.OLD

		# Unzip the JBoss instance.
		echo Unpacking JBoss AMQ $VERSION
		echo
		unzip -q -d $DEMO_HOME $SRC_DIR/$AMQ_BIN
else
		# Unzip the JBoss instance.
		echo Unpacking new JBoss AMQ...
		echo
		unzip -q -d $DEMO_HOME $SRC_DIR/$AMQ_BIN
fi


echo "  - enabling demo accounts logins in users.properties file..."
echo
cp support/users.properties $SERVER_CONF

echo "  - copying updated JBoss A-MQ configuration file activemq.xml from project..."
echo
cp support/activemq.xml $SERVER_CONF/activemq.xml

echo "  - making sure 'AMQ' for server is executable..."
echo
chmod u+x $AMQ_HOME/bin/amq

echo Now going to build the project.
echo
cd $PRJ_DIR
mvn clean install -DskipTests

echo Now going to build the project.      
echo                                      
cd $PRJ2_DIR                               
mvn clean install -DskipTests             


echo
echo You have two steps to start the demo:
echo
echo "First you can start the WEB Dashboard in $PRJ_DIR by executing 'mvn jetty:run'"
echo
echo "Then you need to start the $PRODUCT with $SERVER_BIN/amq"
echo

echo Red Hat $DEMO $VERSION Setup Completed.
echo

