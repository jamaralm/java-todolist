package com.classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

        while(userName.trim().isEmpty()) {
            System.out.println("Nome de usuário não pode ser vazio!");
            System.out.println("Usuário da tarefa: ");
            userName = sc.nextLine();
        }

        while(taskName.isEmpty() || taskDescription.isEmpty()){
            System.out.println("Os campos de texto não devem estar vazios!");
            System.out.println("Nome da tarefa: ");
            taskName = sc.nextLine();
            System.out.println("Descrição da tarefa: ");
            taskDescription = sc.nextLine();
        }

        User user = new User(userName);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Task taskToAdd;

        if (deadline.isEmpty()) {
            taskToAdd = new SimpleTask(taskName, taskDescription, user);
        } else {
            try {
                LocalDate deadlineFormatted = LocalDate.parse(deadline, formatter);
                taskToAdd = new TaskWithDeadline(taskName, taskDescription, user, deadlineFormatted);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato dd-MM-yyyy.");
                return;
            }
        }

        taskList.add(taskToAdd);
        System.out.println("Tarefa adicionada com sucesso!");
        System.out.println("ID da tarefa: " + taskToAdd.getId());
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

        boolean found = false;
        for (Task task : taskList) {
            if (task.getUser().getUsername().equals(username)) {
                task.getInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Nenhuma tarefa encontrada para esse usuário.");
        }
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

        if (task != null) {
            Task.Status newStatus = task.getStatus() == Task.Status.Concluida
                    ? Task.Status.Pendente
                    : Task.Status.Concluida;
            task.changeStatus(newStatus);
            System.out.println("Status da tarefa atualizado para: " + newStatus);
        }
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

    private List<Task> getUserTasks(User user) {
        List<Task> userTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getUser().getUsername().equals(user.getUsername())) {
                userTasks.add(task);
            }
        }
        return userTasks;
    }


    private int getCompletedTaskQuant(){
        int completedLength = 0;
        for (Task task : taskList){
            if (task.getStatus() == Task.Status.Concluida){
                completedLength++;
            }
        }
        return completedLength;
    }

    private int getNotCompletedTaskQuant(){
        int notCompletedLength = 0;
        for (Task task : taskList){
            if (task.getStatus() != Task.Status.Concluida){
                notCompletedLength++;
            }
        }
        return notCompletedLength;
    }
}