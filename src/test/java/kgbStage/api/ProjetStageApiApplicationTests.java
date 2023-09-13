package kgbStage.api;

import kgbStage.api.services.EcoleServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProjetStageApiApplicationTests {

	@Autowired
	private EcoleServices yourService; // Remplacez YourService par la classe ou le composant que vous souhaitez tester

	@Test
	void contextLoads() {
		// Vérifiez que le bean ou le composant est correctement autowired
		assertThat(yourService).isNotNull();
	}
}
