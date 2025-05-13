import com.classes.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        boolean continuar;

        do{
            System.out.println(
                    "\nO que deseja fazer?\n" +
                            "1. Adicionar Tarefa\n" +
                            "2. Remover Tarefa\n" +
                            "3. Buscar Tarefa pelo ID\n" +
                            "4. Listar Tarefas\n" +
                            "5. Editar Tarefa\n" +
                            "6. Concluir Tarefa\n"
            );

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
                case 5:
                    editTaskById(taskList);
                    break;
                case 6:
                    markTaskAsDone(taskList);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
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
        System.out.println("Total de Tarefas: " + getTaskQuantity(taskList));
        System.out.println("Tarefas Completas: " + getCompletedTaskQuant(taskList));
        System.out.println("Tarefas Incompletas: " + getNotCompletedTaskQuant(taskList));

        System.out.println("\nTarefas: ");
        for (Task task : taskList) System.out.println("Nome da Tarefa: " + task.getName());
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

    public static void editTaskById(ArrayList<Task> taskList){
        Scanner sc =  new Scanner(System.in);
        System.out.println("Id da tarefa:");
        int id = sc.nextInt();

        for (Task task : taskList){
            if(id == task.getId()){
                System.out.println("Novo nome da tarefa:");
                String newTaskName = sc.nextLine();
                System.out.println("Nova descricao da tarefa:");
                String newTaskDescription = sc.nextLine();

                if (!newTaskName.equals(task.getName()) || newTaskName.equals(" ")){
                    task.setName(newTaskName);
                    System.out.println("Nome alterado! " + task.getName());
                }else if (!newTaskDescription.equals(task.getDescription()) || newTaskDescription.equals(" ")) {
                    task.setDescription(newTaskDescription);
                    System.out.println("Descricao alterada! " + task.getDescription());
                } else {
                    System.out.println("Nenhuma informacao alterada!");
                }
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

    public static void markTaskAsDone(ArrayList<Task> taskList){
        Scanner sc = new Scanner(System.in);
        System.out.println("Id da tarefa:");
        int id = sc.nextInt();

        for (Task task : taskList){
            if (task.getId() == id){
                task.changeStatus();
                task.setCompleted_at();
                System.out.println("Tarefa " + task.getName() + " Marcada como FEITO!");
            }
        }
    }

    //Retorna a quantidade total de tarefas
    public static int getTaskQuantity(ArrayList<Task> taskList){
        return taskList.size();
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