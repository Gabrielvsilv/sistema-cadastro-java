package cadastro;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Gerenciador acoes = new Gerenciador();
        ArquivoService arquivoService = new ArquivoService();

        int opcao = -1;

        do {
            System.out.println("\n  -- MENU -- ");
            System.out.println("1- CADASTRAR PESSOAS");
            System.out.println("2- LISTAR PESSOAS");
            System.out.println("3- BUSCAR PESSOA");
            System.out.println("4- REMOVER PESSOA");
            System.out.println("5- EDITAR PESSOA");
            System.out.println("0- SAIR");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer
            } catch (InputMismatchException e) {
                System.out.println("⚠️ ERRO: Digite apenas os números do menu!");
                scanner.nextLine(); 
                continue; 
            }

            switch (opcao) {

                case 1:
                    System.out.println("\n--- NOVO CADASTRO ---");

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Idade: ");
                    int idade = 0;
                    try {
                        idade = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Idade inválida. Gravado como 0.");
                        scanner.nextLine();
                    }

                    String cpfDigitado;
                    while (true) {
                        System.out.print("CPF (000.000.000-00): ");
                        cpfDigitado = scanner.nextLine();
                        if (acoes.validarCPFReal(cpfDigitado)) {
                            break;
                        } else {
                            System.out.println("❌ CPF inválido! Tente novamente.");
                        }
                    }

                    System.out.print("CEP: ");
                    String cep = scanner.nextLine();

                    ViaCepService service = new ViaCepService();
                    String json = service.buscarEnderecoPeloCep(cep);

                   
                    String rua = "N/D", bairro = "N/D", cidade = "N/D", uf = "N/D";

                    if (json != null && !json.contains("\"erro\": true")) {
                        rua = extrair(json, "logradouro");
                        bairro = extrair(json, "bairro");
                        cidade = extrair(json, "localidade");
                        uf = extrair(json, "uf");
                    }

                   
                    Pessoa novaPessoa = new Pessoa(nome, idade, cpfDigitado, rua, bairro, cidade, uf);
                    
                  
                    acoes.cadastrarPessoa(novaPessoa);
                    
              
                    arquivoService.salvarDados(acoes.getListaPessoas());

                    System.out.println("✅ " + nome + " cadastrado e salvo no arquivo!");
                    break;

                case 2:
                    acoes.listarPessoas();
                    break;

                case 3:
                    System.out.print("Nome para buscar: ");
                    String busca = scanner.nextLine();
                    acoes.buscarPessoas(busca);
                    break;

                case 4:
                    System.out.print("Nome para remover: ");
                    String remover = scanner.nextLine();
                    acoes.removerPessoa(remover);
                
                    arquivoService.salvarDados(acoes.getListaPessoas());
                    break;

                case 5:
                    System.out.print("Nome para editar: ");
                    String editar = scanner.nextLine();
                    acoes.editarPessoa(editar, scanner);
                   
                    arquivoService.salvarDados(acoes.getListaPessoas());
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }

   

    public static String extrair(String json, String campo) {
        try {
            String busca = "\"" + campo + "\":";
            int inicio = json.indexOf(busca);
            if (inicio != -1) {
                // Procura a aspa de abertura após os dois pontos
                inicio = json.indexOf("\"", inicio + busca.length()) + 1;
                // Procura a aspa de fechamento
                int fim = json.indexOf("\"", inicio);
                return json.substring(inicio, fim);
            }
        } catch (Exception e) {
            return "Erro";
        }
        return "N/D";
    }
}
