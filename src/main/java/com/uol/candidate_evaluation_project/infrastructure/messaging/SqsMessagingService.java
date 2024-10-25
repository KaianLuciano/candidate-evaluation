package com.uol.candidate_evaluation_project.infrastructure.messaging;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SqsMessagingService implements MessagingService {
    private final SqsTemplate sqsTemplate;

    public SqsMessagingService(SqsTemplate sqsTemplate) {
        this.sqsTemplate = sqsTemplate;
    }

    @Override
    public void sendMessage(String queueName, String message) {
        sqsTemplate.send(queueName, message);
    }
}
