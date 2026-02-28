package cadastro;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ViaCepService {

	public String buscarEnderecoPeloCep(String cep) {

		String cepLimpo = cep.replaceAll("\\D", "");

		String urlCaminho = "https://viacep.com.br/ws/" + cepLimpo + "/json/";

		try {
			HttpClient cliente = HttpClient.newHttpClient();

			HttpRequest requisicao = HttpRequest.newBuilder().uri(URI.create(urlCaminho))
					.timeout(Duration.ofSeconds(10)) // Evita que o programa fique "pendurado" se a internet cair
					.build();

			HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

			return resposta.body();

		} catch (Exception e) {
			System.out.println("Erro ao conectar com a API: " + e.getMessage());
			return null;
		}
	}
}