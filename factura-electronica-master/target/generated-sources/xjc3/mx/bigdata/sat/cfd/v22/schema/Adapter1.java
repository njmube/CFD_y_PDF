//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.07 at 03:18:18 PM CDT 
//


package mx.bigdata.sat.cfd.v22.schema;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, Date>
{


    public Date unmarshal(String value) {
        return (mx.bigdata.sat.schema.binder.DateTimeCustomBinder.parseDateTime(value));
    }

    public String marshal(Date value) {
        return (mx.bigdata.sat.schema.binder.DateTimeCustomBinder.printDateTime(value));
    }

}