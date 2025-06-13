import com.classes.TaskManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        boolean continuar;

        do {
            System.out.println(
                    "\nBem-vindo(a) à Central de Controle de Atividades\n" +
                            "1. Adicionar Tarefa\n" +
                            "2. Remover Tarefa por Título\n" +
                            "3. Buscar Tarefa por Título\n" +
                            "4. Buscar Tarefa por Usuário\n" +
                            "5. Listar Tarefas\n" +
                            "6. Editar Tarefa por Título\n" +
                            "7. Começar Tarefa\n" +
                            "8. Concluir Tarefa\n" +
                            "0. Sair\n" +
                            "Escolha uma opção: "
            );

            while (!sc.hasNextInt()) {
                System.out.println("Insira um número válido!");
                sc.nextLine();
            }
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    manager.addTask(sc);
                    break;
                case 2:
                    manager.deleteTaskById(sc);
                    break;
                case 3:
                    manager.searchTaskById(sc);
                    break;
                case 4:
                    manager.searchUserTasks(sc);
                    break;
                case 5:
                    manager.showTaskList();
                    break;
                case 6:
                    manager.editTaskById(sc);
                    break;
                case 7, 8:
                    manager.markTaskAsDone(sc);
                    break;
                case 0:
                    continuar = false;
                    System.out.println("Encerrando o sistema...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }

            System.out.println("Deseja continuar? (S/N)");
            String resposta = sc.nextLine();

            while (!resposta.equalsIgnoreCase("S") && !resposta.equalsIgnoreCase("N")) {
                System.out.println("Insira um valor válido (S/N):");
                resposta = sc.nextLine();
            }

            continuar = resposta.equalsIgnoreCase("S");

        } while (continuar);
    }
}
