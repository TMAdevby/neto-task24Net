package ru.netology.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8085;

        System.out.println("Сервер запущен на порту " + port);
        System.out.println("Ожидаем подключение ... ");

        try (ServerSocket serverSocket = new ServerSocket(port)){
            while(true){
                try(Socket clientSocket = serverSocket.accept()){
                    String clientIP = clientSocket.getInetAddress().getHostAddress();
                    int clientPort = clientSocket.getPort();

                    System.out.println("New connection accepted");
                    System.out.println("IP клиента: " + clientIP);
                    System.out.println("Порт клиента: " + clientPort);

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream())
                    );

                    PrintWriter out = new PrintWriter(
                            clientSocket.getOutputStream(),true
                    );

                    for(int i = 0; i < 3 ; i++ ) {
                        String name = null;
                        String answer = null;
                        if(i == 0) {
                            out.println("Write your name");
                            name = in.readLine();
                        }
                        if(i == 1){
                            System.out.println("Client : " + name);
                            out.println("Are you child? (yes/no)");
                            answer = in.readLine();

                        }
                        System.out.println("Получено от клиента: " + name);

                        out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));

                        System.out.println("Ответ отправлен клиенту");
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка при работе с клиентом: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при работе с клиентом: " + e.getMessage());
        }
    }
}
