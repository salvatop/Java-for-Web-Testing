
import com.jcraft.jsch.JSchException;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SshCommandSender {
    protected static final Logger LOG = LoggerFactory.getLogger(SshCommandSender.class);
    private final String userName;
    private final String password;
    private final String connectionHost;


    public SshCommandSender(String userName, String password, String connectionHost) {
        this.userName = userName;
        this.password = password;
        this.connectionHost = connectionHost;
    }

    public List<String> sendSshCommand(String command, int limitOfOutputLines) throws JSchException {
        try (SshConnection connection = SshConnection.create(userName, password, connectionHost)) {
            List<String> result = connection.execCommand(command, limitOfOutputLines);
            LOG.debug(command);
            LOG.debug(result.toString());
            return result;
        }
    }

    public List<String> sendSshCommands(List<String> commands, int limitOfOutputLines) throws JSchException {
        try (SshConnection connection = SshConnection.create(userName, password, connectionHost)) {
            return commands.stream()
                .flatMap((String command) -> connection.execCommand(
                    command.replace("DEFAULT_HOST", connectionHost),
                    limitOfOutputLines).stream())
                .collect(Collectors.toList());
        }
    }

}
