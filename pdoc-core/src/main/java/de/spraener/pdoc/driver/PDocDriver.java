package de.spraener.pdoc.driver;

import de.spraener.pdoc.PDoc;
import de.spraener.pdoc.PDocPrintContext;

import java.util.List;
import java.util.ServiceLoader;

public interface PDocDriver {

    PDocDriver renderDocument(PDoc doc, PDocPrintContext context);

    /**
     * Does the driver provides the required format? Format could be any String
     * for example PDF oder HTML or LATEX. By convention deeper specifications
     * can be appanded by dots. For example PDF.iText or HTML.5
     *
     * Generally the string is just a convention between the applicaiton and the
     * driver wanted.
     *
     * @param providesType
     * @return
     */
    boolean provides(String providesType);

    static PDocDriver loadDriver(String className) throws Exception {
        return (PDocDriver)Class.forName(className).newInstance();
    }

    static PDocDriver loadDriverFor(String providesType) {
        ServiceLoader<PDocDriver> locator = ServiceLoader.load(PDocDriver.class);
        for( PDocDriver driver : locator ) {
            if( driver.provides(providesType) ) {
                return driver;
            }
        }
        return null;
    }

}
