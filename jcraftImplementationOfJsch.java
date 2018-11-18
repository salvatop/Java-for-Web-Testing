
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SshConnection implements Closeable {
    protected static final Logger LOG = LoggerFactory.getLogger(SshConnection.class);
    private final String host;
    private final String password;
    private final Session session;
    private static int port = 22;
    private static int timeout = 60000;


    private SshConnection(String userName, String password, String connectionHost,
                          int connectionPort, int timeOutMilliseconds) throws JSchException {
        JSch jschSshChannel = new JSch();
        session = jschSshChannel.getSession(userName, connectionHost, connectionPort);
        this.password = password;
        port = connectionPort;
        host = connectionHost;
        timeout = timeOutMilliseconds;
    }

    static SshConnection create(String userName, String password, String connectionHost,
                                int connectionPort, int timeOutMilliseconds) throws JSchException {
        SshConnection sshConnection = new SshConnection(userName, password, connectionHost, connectionPort, timeOutMilliseconds);
        sshConnection.connect();
        return sshConnection;
    }

    static SshConnection create(String userName, String password, String connectionHost, int connectionPort) throws JSchException {
        return create(userName, password, connectionHost, connectionPort, timeout);
    }

    static SshConnection create(String userName, String password, String connectionHost) throws JSchException {
        return create(userName, password, connectionHost, port);
    }

    private void connect() {
        try {
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(timeout);
        } catch (JSchException jschX) {
            logError(jschX);
            throw new RuntimeException(jschX);
        }
    }

    private void logWarning(Throwable exception) {
        LOG.warn("{0}:{1} - ", host, port, exception);
    }

    private void logError(Throwable exception) {
        LOG.error("{0}:{1} - ", host, port, exception);
    }

    public List<String> execCommand(String command, int limitOfOutputLines) {
        ChannelExec channel = null;
        try {
            channel = (ChannelExec) session.openChannel("exec");
            channel.setPty(true);
            channel.setCommand(command);
            InputStream commandOutput = channel.getInputStream();
            channel.connect();
            return new BufferedReader(new InputStreamReader(commandOutput)).lines().limit(limitOfOutputLines)
                .collect(Collectors.toList());
        } catch (IOException | JSchException ioX) {
            logWarning(ioX);
            throw new RuntimeException(ioX);
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

    public void close() {
        session.disconnect();
    }

}
