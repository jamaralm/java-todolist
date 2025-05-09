import com.classes.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();

        boolean continuar;

        do{
            System.out.println("Oque deseja fazer?\n 1. Adicionar Tarefa\n2. Remover Tarefa\n3. Listar Tarefas\n4. Buscar Tarefa pelo ID");

            int userInput = sc.nextInt();
            sc.nextLine();

            switch (userInput){
                case 1:
                    addTask(taskList);
                    break;
                case 2:
                    deleteTaskById(taskList);
                    break;
                case 3:
                    searchTaskById(taskList);
                    break;
                case 4:
                    showTaskList(taskList);
                    break;
            }

            System.out.println("Deseja Continuar? (S/N)");
            String userInputToContinue = sc.nextLine();

            continuar = userInputToContinue.equals("S");
        }while(continuar);
    }

    public static void addTask(ArrayList<Task> taskList){
        Scanner sc = new Scanner(System.in);

        System.out.println("Nome da tarefa:");
        String taskName = sc.nextLine();
        System.out.println("Descricao da tarefa: ");
        String taskDescription = sc.nextLine();

        taskList.add(new Task(taskName,taskDescription));
        System.out.println("Tarefa adicionada com sucesso!");
    }

    public static void showTaskList(ArrayList<Task> taskList){
        System.out.println("\nTarefas: ");
        for (Task task : taskList) System.out.println("TASK NAME: " + task.getName());;
    }

    public static void searchTaskById(ArrayList<Task> taskList){
        Scanner sc = new Scanner(System.in);

        System.out.println("Id da tarefa:");
        int id = sc.nextInt();

        for (Task task : taskList){
            if (id == task.getId()){
                task.getInfo();
            }
        }
    }

    public static void deleteTaskById(ArrayList<Task> taskList){
        Scanner sc = new Scanner(System.in);

        System.out.println("Id da tarefa:");
        int id = sc.nextInt();

        for (Task task : taskList){
            if (id == task.getId()){
                taskList.remove(task);
                System.out.println("Tarefa removida com sucesso!");
            }
        }
    }

    //Retorna a quantidade total de tarefas
    public int getLength(ArrayList<Task> taskList){
        int length = 0;
        for (Task task : taskList){
            length++;
        }
        return length;
    }

    //Retorna a quantidade de Tarefas concluidas
    public static int getCompletedTaskQuant(ArrayList<Task> taskList){
        int completedLength = 0;
        for (Task task : taskList){
            if (task.getStatus().equals("Feito")){
                completedLength++;
            }
        }
        return completedLength;
    }

    //Retorna a quantidade de tarefas NAO concluidas
    public static int getNotCompletedTaskQuant(ArrayList<Task> taskList){
        int notCompletedLength = 0;
        for (Task task : taskList){
            if (!task.getStatus().equals("Feito")){
                notCompletedLength++;
            }
        }
        return  notCompletedLength;
    }
}