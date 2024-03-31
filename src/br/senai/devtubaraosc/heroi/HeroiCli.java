// Pacote para a classe HeroiCli
package br.senai.devtubaraosc.heroi;

// Importações necessárias
import br.senai.devtubaraosc.heroi.enuns.Operacao;
import br.senai.devtubaraosc.heroi.model.Heroi;
import br.senai.devtubaraosc.heroi.utils.ConsoleColors;
import br.senai.devtubaraosc.heroi.utils.ConsoleLog;

import java.util.ArrayList;
import java.util.Scanner;

// Classe responsável pela lógica de interação com o usuário
public class HeroiCli {
    // Scanner para entrada de dados do usuário
    private Scanner scanner;
    // Lista de heróis cadastrados
    private ArrayList<Heroi> heroisCadastrados;

    // Construtor da classe
    public HeroiCli() {
        this.scanner = new Scanner(System.in);
        this.heroisCadastrados = new ArrayList<>();
    }

    // Método para exibir o menu da aplicação
    public void exibirMenu() {
        ConsoleLog.info("");
        ConsoleLog.info(ConsoleColors.BLUE + "===== MENU =====" + ConsoleColors.RESET);
        ConsoleLog.info("");
        ConsoleLog.info(ConsoleColors.BLUE + "1. Adicionar Herói" + ConsoleColors.RESET);
        ConsoleLog.info("");
        ConsoleLog.info(ConsoleColors.BLUE + "2. Listar Heróis" + ConsoleColors.RESET);
        ConsoleLog.info("");
        ConsoleLog.info(ConsoleColors.BLUE + "3. Sair" + ConsoleColors.RESET);
        ConsoleLog.info("");
        ConsoleLog.info(ConsoleColors.BLUE + "Escolha uma opção: " + ConsoleColors.RESET);
        ConsoleLog.info("");
    }

    // Método para solicitar e retornar a operação escolhida pelo usuário
    public Operacao solicitarOperacao() {
        String opcao = scanner.nextLine();
        try {
            int valor = Integer.parseInt(opcao);
            return Operacao.values()[valor - 1];
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return Operacao.SAIR; // Se a entrada não for válida, sair do programa
        }
    }

    // Método principal para execução do programa
    public void executar() {
        try {
            while (true) {
                exibirMenu(); // Exibe o menu

                // Ler a opção do usuário
                Operacao operacao = solicitarOperacao();

                // Realizar a ação correspondente à operação escolhida
                switch (operacao) {
                    case ADICIONAR:
                        // Adicionar um novo herói
                        adicionarHeroi();
                        break;
                    case LISTAR:
                        // Listar todos os heróis cadastrados
                        listarHerois();
                        break;
                    case SAIR:
                        // Sair do sistema
                        ConsoleLog.warning("Saindo do sistema...");
                        return; // Encerra o método executar()
                }
            }
        } finally {
            // Fecha o scanner ao sair do loop principal
            scanner.close();
        }
    }

    // Método para adicionar um novo herói à lista de heróis cadastrados
    private void adicionarHeroi() {
        ConsoleLog.info("");
        ConsoleLog.info("Nome do Herói: ");
        String nome = scanner.nextLine();

        // Verificar se já existe um herói com o mesmo nome na lista
        for (Heroi heroi : heroisCadastrados) {
            if (heroi.getNome().equalsIgnoreCase(nome)) {
                ConsoleLog.error("Já existe um herói com este nome. Cadastre um nome único.");
                return;
            }
        }

        ConsoleLog.info("Superpoder do Herói: ");
        String superpoder = scanner.nextLine();

        int idade;
        try {
            ConsoleLog.info("Idade do Herói: ");
            idade = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            ConsoleLog.error("Por favor, insira uma idade válida.");
            return;
        }

        heroisCadastrados.add(new Heroi(nome, superpoder, idade));
        ConsoleLog.success("");
        ConsoleLog.success("Herói adicionado com sucesso!");
        ConsoleLog.success("");
    }

    // Método para listar todos os heróis cadastrados
    private void listarHerois() {
        if (heroisCadastrados.isEmpty()) {
            ConsoleLog.warning("Nenhum herói cadastrado.");
            return;
        }

        ConsoleLog.info("");
        ConsoleLog.info(ConsoleColors.YELLOW + "===== LISTA DE HERÓIS =====" + ConsoleColors.RESET);
        for (Heroi heroi : heroisCadastrados) {
            ConsoleLog.info(ConsoleColors.YELLOW + "Nome: " + heroi.getNome());
            ConsoleLog.info(ConsoleColors.YELLOW + "Superpoder: " + heroi.getSuperpoder());
            ConsoleLog.info(ConsoleColors.YELLOW + "Idade: " + heroi.getIdade());
            ConsoleLog.info(ConsoleColors.YELLOW + "-----------------------------" + ConsoleColors.RESET);
        }
    }
}
