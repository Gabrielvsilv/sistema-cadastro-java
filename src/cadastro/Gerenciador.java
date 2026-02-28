package cadastro;

import java.util.ArrayList;
import java.util.List; // Import necessário para o método get
import java.util.Scanner;

public class Gerenciador {

    
    private ArrayList<Pessoa> listaDePessoas = new ArrayList<>();

    public void cadastrarPessoa(Pessoa p) {
        listaDePessoas.add(p);
        System.out.println("Sucesso! " + p.getNome() + " foi cadastrado com sucesso!");
    }

    public void listarPessoas() {
        System.out.println("\n--- LISTA DE PESSOAS ---");
        if (listaDePessoas.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
        } else {
            for (Pessoa p : listaDePessoas) {
                System.out.println(p.toString());
            }
        }
    }

    public void buscarPessoas(String nomeBusca) {
        boolean encontrado = false;
        for (Pessoa p : listaDePessoas) {
            if (p.getNome().equalsIgnoreCase(nomeBusca)) {
                System.out.println("Encontrada: " + p.toString());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) System.out.println("Usuário " + nomeBusca + " não encontrado.");
    }

    public void removerPessoa(String nomeRemover) {
        boolean removido = listaDePessoas.removeIf(p -> p.getNome().equalsIgnoreCase(nomeRemover));
        if (removido) {
            System.out.println("Pessoa removida com sucesso!");
        } else {
            System.out.println("Nome não encontrado.");
        }
    }

    public void editarPessoa(String nomeBusca, Scanner scanner) {
        for (Pessoa p : listaDePessoas) {
            if (p.getNome().equalsIgnoreCase(nomeBusca)) {
                System.out.println("Editando: " + p.toString());
                System.out.print("1- Nome\n2- Idade\nEscolha: ");
                int escolha = scanner.nextInt();
                scanner.nextLine();

                if (escolha == 1) {
                    System.out.print("Novo Nome: ");
                    p.setNome(scanner.nextLine());
                } else if (escolha == 2) {
                    System.out.print("Nova Idade: ");
                    p.setIdade(scanner.nextInt());
                    scanner.nextLine();
                }
                System.out.println("Atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Pessoa não encontrada.");
    }

    public boolean validarCPFReal(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;
        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) soma += (cpf.charAt(i) - '0') * (10 - i);
            int d1 = 11 - (soma % 11);
            if (d1 > 9) d1 = 0;
            soma = 0;
            for (int i = 0; i < 10; i++) soma += (cpf.charAt(i) - '0') * (11 - i);
            int d2 = 11 - (soma % 11);
            if (d2 > 9) d2 = 0;
            return (d1 == (cpf.charAt(9) - '0') && d2 == (cpf.charAt(10) - '0'));
        } catch (Exception e) { return false; }
    }

   
    public List<Pessoa> getListaPessoas() {
        return this.listaDePessoas;
    }
}