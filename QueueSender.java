package cn.itcast.activemq.producer.queue;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component("queueSender")
public class QueueSender {
	
	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsTemplate;

	/**
	 * 发送一条消息到指定的队列（目标）
	 * 
	 * @param queueName
	 *            队列名称
	 * @param message
	 *            消息内容
	 */

		public void send(String queueName,final String messager){
			jmsTemplate.send(queueName,new MessageCreator() {
				
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(messager);
				}
			});
			
			
		}
		
		//发送map的结构数据
		public void send(String queueName, final Map<String, String> map){
			jmsTemplate.send(queueName, new MessageCreator() {
				
				@Override
				public Message createMessage(Session session) throws JMSException {
					MapMessage message = session.createMapMessage();
					for (String key : map.keySet()) {
						message.setString(key, map.get(key));
					}
					return message;
				}
			});
			
			
		}
		
		
		
		
	
}
