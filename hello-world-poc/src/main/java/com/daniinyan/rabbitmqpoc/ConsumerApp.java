package com.daniinyan.rabbitmqpoc;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ConsumerApp {

  public static final String QUEUE_NAME = "TestQueue";

  public static void main(String[] args) throws Exception {

    // create connection with localhost
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    // declare queue
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println("Started");

    // buff messages until need
    DeliverCallback deliverCallback = (consumerTag, received) -> {
      String message = new String(received.getBody(), "UTF-8");
      System.out.println(" -- Received: '" + message + "'");
    };

    // consume messages
    channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
  }
}
