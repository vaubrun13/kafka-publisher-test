package com.craft.demo.kafkapublishertest.publishers;

import com.craft.demo.kafkapublishertest.avro.MyEventAvro;
import com.craft.demo.kafkapublishertest.avro.MyKeyAvro;
import com.craft.demo.kafkapublishertest.models.MyEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

  private final KafkaTemplate<MyKeyAvro, MyEventAvro> kafkaTemplate;

  public EventPublisher(KafkaTemplate<MyKeyAvro, MyEventAvro> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void publish(MyEvent event) {
    // Map Event to avro
    MyEventAvro myEventAvro =
        MyEventAvro.newBuilder()
            .setId(event.id())
            .setVersion(event.version())
            .setOccurredAt(event.occurredAt().toString())
            .build();

    MyKeyAvro myKeyAvro = MyKeyAvro.newBuilder().setId(event.id()).build();

    this.kafkaTemplate.send("topic", myKeyAvro, myEventAvro);
  }
}
