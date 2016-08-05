package ch.ledcom.agent.jmx;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.rmi.server.RMIServerSocketFactory;

/**
 * Custom implementation that only creates server sockets on the specified
 * address.
 *
 * From http://vafer.org/blog/20061010091658/
 */
public final class RMIServerSocketFactoryImpl
        implements RMIServerSocketFactory {

    /**
     * Address to be used for socket creation.
     */
    private final InetAddress localAddress;

    /**
     * Creates a new {@link RMIServerSocketFactoryImpl} that will use the
     * given address for socket creation.
     *
     * @param pAddress Address to use for socket creation
     */
    public RMIServerSocketFactoryImpl(final InetAddress pAddress) {
        localAddress = pAddress;
    }

    /**
     * Creates a new server socket on the given port, using the address.
     * @param pPort Port on which to create the socket
     * @return Socket bound to the given port and using the address specified.
     * in {{@link #RMIServerSocketFactoryImpl(InetAddress)}}.
     * @throws IOException on any networking errors
     */
    public ServerSocket createServerSocket(final int pPort)
            throws IOException {
        return ServerSocketFactory.getDefault()
                .createServerSocket(pPort, 0, localAddress);
    }

    /**
     * Object obj is considered to be equal if and only if the class is the
     * same.
     *
     * @param obj The object with which this is compared
     * @return True if the two objects' classes are equal, otherwise false.
     */
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        return obj.getClass().equals(getClass());
    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return RMIServerSocketFactoryImpl.class.hashCode();
    }
}
