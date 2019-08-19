package com.ppj.kafka;

import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class MsgProducer {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("1bootstrap.servers", "172.16.220.137:9092,172.16.220.137:9093,172.16.220.137:9094");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String,String >(props);
		Scanner sc = new Scanner(System.in);
		while (true){

			if(sc.hasNext()) {
				String next = sc.next();
				if("quit".equals(next.toLowerCase())){
					break;
				}
				//同步方式发送消息
				ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("test", 0, next, next);
			/*Future<RecordMetadata> result = producer.send(producerRecord);
			//等待消息发送成功的同步阻塞方法
			RecordMetadata metadata = result.get();
			System.out.println("同步方式发送消息结果：" + "topic-" + metadata.topic() + "|partition-"
			        + metadata.partition() + "|offset-" + metadata.offset());*/

				//异步方式发送消息
				producer.send(producerRecord, (metadata, exception) -> {
					if (exception != null) {
						System.err.println("发送消息失败：" + Arrays.toString(exception.getStackTrace()));

					}
					if (metadata != null) {
						System.out.println("异步方式发送消息结果：" + "topic-" + metadata.topic() + "|partition-"
								+ metadata.partition() + "|offset-" + metadata.offset());
					}
				});

				//送积分
				//fdfddfdasf
			}

		}

		producer.close();
	}
}
