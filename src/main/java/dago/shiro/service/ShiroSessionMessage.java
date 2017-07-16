package dago.shiro.service;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.data.redis.connection.DefaultMessage;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.io.Serializable;


@SuppressWarnings("WeakerAccess")
public class ShiroSessionMessage extends DefaultMessage {

    private final MessageBody msgBody;

    public ShiroSessionMessage(byte[] channel, byte[] body) {
        super(channel, body);
        msgBody = (MessageBody) new JdkSerializationRedisSerializer().deserialize(body);
    }

    public MessageBody getMsgBody() {
        return msgBody;
    }

    @SuppressWarnings("unused")
    @ToString
    @AllArgsConstructor
    public static class MessageBody implements Serializable {
        private final Serializable sessionId;
        private final String nodeId;
        private String msg = "";

        public Serializable getSessionId() {
            return sessionId;
        }

        public String getNodeId() {
            return nodeId;
        }

        public String getMsg() {
            return msg;
        }

        public MessageBody(Serializable sessionId, String nodeId) {
            this.sessionId = sessionId;
            this.nodeId = nodeId;
        }
    }
}
