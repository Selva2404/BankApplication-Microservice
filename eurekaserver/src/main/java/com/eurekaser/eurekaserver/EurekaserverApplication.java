package com.eurekaser.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaserverApplication.class, args);
	}

}
//docker image push docker.io/selva2404/eurekaserver:s5
//docker image push docker.io/selva2404/account:s5
//docker image push docker.io/selva2404/startservice:s5
//docker image push docker.io/selva2404/cardservice:s5
//docker image push docker.io/selva2404/loanservice:s5