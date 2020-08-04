package com.daniinyan.rabbitmqpoc;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ProducerApp {

  public static final String QUEUE_NAME = "TestQueue";

  public static void main(String[] args) throws Exception {

    // create connection with localhost
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");

    try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {
      // declare queue
      channel.queueDeclare(QUEUE_NAME, false, false, false, null);

      // define message to send
      String message = "Sending a message";

      // publish message
      channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
      System.out.println("-- Message sent: '" + message + "'");
    }
  }

}
