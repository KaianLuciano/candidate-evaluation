package com.uol.candidate_evaluation_project.infrastructure.messaging;

public interface MessagingService {
    void sendMessage(String queueName, String message);
}
