JBoss A-MQ Biomedical Signals Sensor Monitor DashBoard IoT Demo 
=====================================================================

Demo based on JBoss A-MQ product.

Setup and Configuration
-----------------------

- Download JBoss A-MQ product from RED HAT (version 6.1) into install folder

- run 'init.sh' 

- start JBoss A-MQ

   go to ./target/jboss-a-mq-{$version}/bin
   execute JBoss A-MQ by executing ./amq

- Startup the instance by

	goto project/amqp-demo-web-biomedical
	mvn jetty:run to startup the server 
	
	goto project/mqtt-drools
	mvn camel:run -Psimple

- verify Biomedical Signals Sensor Monitor DashBoard

    http://localhost:8282/demo/BiomedicalDashBoard.html

- click on start button.


Supporting Articles
-------------------

Coming soon...


Released versions
-----------------

See the tagged releases for the following versions of the product:

- v0.1 - JBoss A-MQ 6.1 and biometric dashboard installed.

![Biometrical Dashbaord](https://github.com/eschabell/amqp-demo-web-biomedical/blob/master/docs/demo-images/bio-dashboard.png?raw=true)

![Install Console](https://github.com/eschabell/amqp-demo-web-biomedical/blob/master/docs/demo-images/install-console.png?raw=true)

