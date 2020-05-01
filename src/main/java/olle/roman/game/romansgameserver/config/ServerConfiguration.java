package olle.roman.game.romansgameserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.support.RemoteExporter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import olle.roman.game.romansgameclient.service.GameService;
import olle.roman.game.romansgameserver.domain.service.impl.DefaultGameService;

@EnableWebMvc
@Configuration
public class ServerConfiguration {

	//hessian version
	@Bean(name="/mojehra")
	public RemoteExporter gameServiceInitializr() {
		HessianServiceExporter exporter = new HessianServiceExporter();
		exporter.setService(createBean());
		exporter.setServiceInterface(GameService.class);
		return exporter;
	}

	//RMI version
//	@Bean
//	public RemoteExporter gameServiceInitializr() {
//		RmiServiceExporter exporter = new RmiServiceExporter();
//		exporter.setService(createBean());
//		exporter.setServiceInterface(GameService.class);
//		exporter.setServiceName("mojehra");
//		return exporter;
//	}

	@Bean
	public GameService createBean() {
		return new DefaultGameService();
	}
}
