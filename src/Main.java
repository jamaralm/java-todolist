import com.classes.TaskManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        boolean continuar;

        do {
            System.out.println(
                    "\nBem vindo(a) a central de controle de atividades!\n" +
                            "1. Adicionar Tarefa\n" +
                            "2. Remover Tarefa\n" +
                            "3. Buscar Tarefa pelo ID\n" +
                            "4. Listar Tarefas\n" +
                            "5. Editar Tarefa\n" +
                            "6. Concluir Tarefa\n\n" +
                            "O que deseja fazer? "
            );

            while(!sc.hasNextInt()){
                System.out.println("Insira um número válido!");
                sc.nextLine();
            }
            int userInput = sc.nextInt();
            sc.nextLine();

            while(userInput < 0 || userInput > 6){
                System.out.println("Insira um número válido!");
                userInput = sc.nextInt();
                sc.nextLine();
            }

            switch (userInput){
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
                    manager.showTaskList();
                    break;
                case 5:
                    manager.editTaskById(sc);
                    break;
                case 6:
                    manager.markTaskAsDone(sc);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            System.out.println("Deseja Continuar? (S/N)");
            String userInputToContinue = sc.nextLine();

            while (!userInputToContinue.equalsIgnoreCase("S") && !userInputToContinue.equalsIgnoreCase("N")) {
                System.out.println("Insira um valor válido! (S/N)");
                userInputToContinue = sc.nextLine();
            }

            continuar = userInputToContinue.equalsIgnoreCase("S");

        } while(continuar);
    }
}
