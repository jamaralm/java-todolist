package com.classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<Task>();
    }

    public void addTask(Scanner sc){
        System.out.println("Nome da tarefa: ");
        String taskName = sc.nextLine();
        System.out.println("Descrição da tarefa: ");
        String taskDescription = sc.nextLine();
        System.out.println("Usuario da tarefa: ");
        String userName = sc.nextLine();
        System.out.println("Prazo da tarefa (Deixe em branco para não ter prazo): ");
        String deadline = sc.nextLine();

        while(taskName.isEmpty() || taskDescription.isEmpty()){
            System.out.println("Os campos de texto não devem estar vazios!");
            System.out.println("Nome da tarefa: ");
            taskName = sc.nextLine();
            System.out.println("Descrição da tarefa: ");
            taskDescription = sc.nextLine();
        }

        if (deadline.isEmpty()) {
            User user = new User(userName);

            Task taskToAdd = new SimpleTask(taskName, taskDescription, user);
            taskList.add(taskToAdd);

            System.out.println("Tarefa adicionada com sucesso!");
            System.out.println("ID da tarefa: " + taskToAdd.getId());
        } else if (!deadline.isEmpty()) {
            User user = new User(userName);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate deadlineFormatted = LocalDate.parse(deadline);
            deadlineFormatted.format(formatter);

            Task taskToAdd = new TaskWithDeadline(taskName, taskDescription, user, deadlineFormatted);
            taskList.add(taskToAdd);

            System.out.println("Tarefa adicionada com sucesso!");
            System.out.println("ID da tarefa: " + taskToAdd.getId());
        }
    }

    public void showTaskList(){
        System.out.println("Total de Tarefas: " + taskList.size());
        System.out.println("Tarefas Completas: " + getCompletedTaskQuant());
        System.out.println("Tarefas Incompletas: " + getNotCompletedTaskQuant());

        System.out.println("\nTarefas: ");
        for (Task task : taskList)
            System.out.println("Nome da Tarefa: " + task.getName() + " (ID: " + task.getId() + " )" );
    }

    public void searchTaskById(Scanner sc){
        System.out.println("Id da tarefa: ");
        int id = sc.nextInt();
        sc.nextLine();

        Task task = getTaskById(id);
        if (task != null) task.getInfo();
    }

    public void searchUserTasks(Scanner sc) {
        System.out.println("Usuário: ");
        String username = sc.nextLine();

        User user = new User(username);

        Task taskUser = getUserTasks(user);
        if (taskUser != null) taskUser.getInfo();
    }

    public void editTaskById(Scanner sc){
        System.out.println("Id da tarefa: ");
        int id = sc.nextInt();
        sc.nextLine();

        Task task = getTaskById(id);
        if (task != null) {
            System.out.println("Novo nome da tarefa: ");
            String newTaskName = sc.nextLine();
            System.out.println("Nova descrição da tarefa: ");
            String newTaskDescription = sc.nextLine();

            if (!newTaskName.trim().isEmpty()) {
                task.setName(newTaskName);
                System.out.println("Nome alterado! " + task.getName());
            }
            if (!newTaskDescription.trim().isEmpty()) {
                task.setDescription(newTaskDescription);
                System.out.println("Descrição alterada! " + task.getDescription());
            }
        }
    }

    public void deleteTaskById(Scanner sc){
        System.out.println("Id da tarefa: ");
        int id = sc.nextInt();
        sc.nextLine();

        Task task = getTaskById(id);
        if (task != null) {
            taskList.remove(task);
            System.out.println("Tarefa removida com sucesso!");
        }
    }

    public void markTaskAsDone(Scanner sc){
        System.out.println("Id da tarefa: ");
        int id = sc.nextInt();
        sc.nextLine();

        Task task = getTaskById(id);
        
        if (task != null) task.changeStatus(task.getStatus());
    }

    private Task getTaskById(int id){
        for (Task task : taskList){
            if (task.getId() == id){
                return task;
            }
        }
        System.out.println("Tarefa não Encontrada!");
        return null;
    }

    private Task getUserTasks(User user){
        for (Task task : taskList){
            if (task.getUser() == user){
                task.getInfo();
            }
        }
        System.out.println("Tarefa não Encontrada!");
        return null;
    }

    private int getCompletedTaskQuant(){
        int completedLength = 0;
        for (Task task : taskList){
            if (task.getStatus().equals("Feito")){
                completedLength++;
            }
        }
        return completedLength;
    }

    private int getNotCompletedTaskQuant(){
        int notCompletedLength = 0;
        for (Task task : taskList){
            if (!task.getStatus().equals("Feito")){
                notCompletedLength++;
            }
        }
        return notCompletedLength;
    }
}