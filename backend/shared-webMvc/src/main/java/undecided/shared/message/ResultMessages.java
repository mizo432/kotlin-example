package undecided.shared.message;

import com.google.common.collect.Lists;
import undecided.common.primitive.strings.Strings2;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ResultMessages implements Serializable, Iterable<ResultMessage> {

    /**
     * message type
     */
    private final ResultMessageType type;

    private final List<ResultMessage> list = Lists.newArrayList();

    public static final String DEFAULT_MESSAGES_ATTRIBUTE_NAME = Strings2
            .uncapitalize(ResultMessages.class.getSimpleName());

    public ResultMessages(ResultMessageType type) {
        this(type, (ResultMessage[]) null);
    }

    public ResultMessages(ResultMessageType type, ResultMessage... messages) {
        if (type == null) {
            throw new IllegalArgumentException("type must not be null!");
        }
        this.type = type;
        if (messages != null) {
            addAll(messages);
        }
    }

    public ResultMessageType getType() {
        return type;
    }

    public List<ResultMessage> getList() {
        return list;
    }

    public ResultMessages add(ResultMessage message) {
        if (message != null) {
            this.list.add(message);
        } else {
            throw new IllegalArgumentException("message must not be null");
        }
        return this;
    }

    public ResultMessages add(String code) {
        if (code != null) {
            this.add(ResultMessage.fromCode(code));
        } else {
            throw new IllegalArgumentException("code must not be null");
        }
        return this;
    }

    public ResultMessages add(String code, Object... args) {
        if (code != null) {
            this.add(ResultMessage.fromCode(code, args));
        } else {
            throw new IllegalArgumentException("code must not be null");
        }
        return this;
    }

    public ResultMessages addAll(ResultMessage... messages) {
        if (messages != null) {
            for (ResultMessage message : messages) {
                add(message);
            }
        } else {
            throw new IllegalArgumentException("messages must not be null");
        }
        return this;
    }

    public ResultMessages addAll(Collection<ResultMessage> messages) {
        if (messages != null) {
            for (ResultMessage message : messages) {
                add(message);
            }
        } else {
            throw new IllegalArgumentException("messages must not be null");
        }
        return this;
    }

    public boolean isNotEmpty() {
        return !list.isEmpty();
    }

    public static ResultMessages success() {
        return new ResultMessages(StandardResultMessageType.SUCCESS);

    }

    public static ResultMessages info() {
        return new ResultMessages(StandardResultMessageType.INFO);

    }

    public static ResultMessages warning() {
        return new ResultMessages(StandardResultMessageType.WARNING);

    }

    public static ResultMessages error() {
        return new ResultMessages(StandardResultMessageType.ERROR);

    }

    public static ResultMessages danger() {
        return new ResultMessages(StandardResultMessageType.DANGER);

    }

    public static ResultMessages primary() {
        return new ResultMessages(StandardResultMessageType.PRIMARY);

    }

    public static ResultMessages secondary() {
        return new ResultMessages(StandardResultMessageType.SECONDARY);

    }

    public static ResultMessages light() {
        return new ResultMessages(StandardResultMessageType.LIGHT);

    }

    public static ResultMessages dark() {
        return new ResultMessages(StandardResultMessageType.DARK);

    }

    @Override
    public Iterator<ResultMessage> iterator() {
        return list.iterator();

    }

    @Override
    public String toString() {
        return "ResultMessages [type=" + type + ", list=" + list + "]";

    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

    }

    @Serial
    private void readObject(
            ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

    }
}
