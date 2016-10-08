package dk.lundogbendsen.infrastructure

import org.apache.log4j.Logger
import org.xbill.DNS.Address
import spock.lang.Specification
import spock.lang.Unroll

class DNSIsSetupCorrectly extends Specification {
    Logger LOG = Logger.getLogger(DNSIsSetupCorrectly.class);
    private static final String MX_ATTRIB = "MX";
    private static final String ADDR_ATTRIB = "A";
    private static String[] MX_ATTRIBS = { MX_ATTRIB };
    private static String[] ADDR_ATTRIBS = { ADDR_ATTRIB };

    @Unroll
    def "host #hostname must be reachable on the internet"() {

        when:
        try {
            Address.getByName(hostname);
        } catch (UnknownHostException e) {
            LOG.error("Oops " + e.getMessage() + " [" + hostname + "]")
            throw e
        }

        then:
        notThrown(UnknownHostException)

        where:
        hostname << ["lundogbendsen.dk"]

    }
}
