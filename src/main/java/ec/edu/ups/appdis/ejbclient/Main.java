package ec.edu.ups.appdis.ejbclient;

import java.rmi.RemoteException;

//import java.util.Hashtable;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//
//import org.jboss.tools.examples.model.Member;
//import org.jboss.tools.examples.service.MemberRegistrationRemote;
//
//public class Main {
//	
//	private MemberRegistrationRemote memberRegistration;
//	
//	public void intanciarMemberRegistration() throws Exception {
//		try {  
//            final Hashtable<String, Comparable> jndiProperties =  
//                    new Hashtable<String, Comparable>();  
//            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  
//                    "org.wildfly.naming.client.WildFlyInitialContextFactory");  
//            jndiProperties.put("jboss.naming.client.ejb.context", true);  
//              
//            jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");  
//            jndiProperties.put(Context.SECURITY_PRINCIPAL, "eduardo");  
//            jndiProperties.put(Context.SECURITY_CREDENTIALS, "eduardo");  
//              
//            final Context context = new InitialContext(jndiProperties);  
//              
//            final String lookupName = "ejb:/01-ejb-server/MemberRegistration!org.jboss.tools.examples.service.MemberRegistrationRemote";  
//              
//            this.memberRegistration = (MemberRegistrationRemote) context.lookup(lookupName);  
//              
//        } catch (Exception ex) {
//            ex.printStackTrace();  
//            throw ex;
//        }  
//	}
//	
//	public void registrar(String email, String name, String phone) throws Exception {
//		Member m = new Member();
//		m.setEmail(email);
//		m.setName(name);
//		m.setPhoneNumber(phone);
//		
//		memberRegistration.register(m);
//	}
//
//	public static void main(String[] args) {	
//		Main main = new Main();
//		try {
//			main.intanciarMemberRegistration();
//			main.registrar("edu@hotmail.com", "Edu", "0984864931");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}

//import java.util.Properties;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//
//import org.jboss.tools.examples.service.MessageServerRemote;
//
//public class Main {
//    public static void main(String[] args) throws NamingException {
//        Properties jndiProps = new Properties();
//        jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
//        jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
//        jndiProps.put(Context.SECURITY_PRINCIPAL, "eduardo");
//        jndiProps.put(Context.SECURITY_CREDENTIALS, "eduardo");
//
//        Context context = new InitialContext(jndiProps);
////        ejb:/01-ejb-server/MemberRegistration!org.jboss.tools.examples.service.MemberRegistrationRemote
//        
//        MessageServerRemote server = (MessageServerRemote) context.lookup("java:app/jboss-javaee-webapp/MessageServerImpl!org.jboss.tools.examples.service.MessageServer");
////        MessageServer server = (MessageServer) context.lookup("ejb:/01-ejb-server/MessageServerImpl!org.jboss.tools.examples.service.MessageServer");
//        try {
//			server.sendMessage("Hello, world!");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
//}

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.jboss.tools.examples.service.MessageServerRemote;

public class Main {
	
	private MessageServerRemote messageServerRemote;
	
	public void instanciar() throws Exception {
		try {  
          final Hashtable<String, Comparable> jndiProperties =  
                  new Hashtable<String, Comparable>();  
          jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  
                  "org.wildfly.naming.client.WildFlyInitialContextFactory");  
          jndiProperties.put("jboss.naming.client.ejb.context", true);  
            
          jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");  
          jndiProperties.put(Context.SECURITY_PRINCIPAL, "eduardo");  
          jndiProperties.put(Context.SECURITY_CREDENTIALS, "eduardo");  
            
          final Context context = new InitialContext(jndiProperties);  
            
          final String lookupName = "ejb:/jboss-javaee-webapp/MessageServer!org.jboss.tools.examples.service.MessageServerRemote";  
            
          this.messageServerRemote = (MessageServerRemote) context.lookup(lookupName);
          
      } catch (Exception ex) {
          ex.printStackTrace();
          throw ex;
      }
	}
	
	public void message(String message) throws Exception {
		
		this.messageServerRemote.sendMessage(message);
	}

	public static void main(String[] args) {	
		Main main = new Main();
		try {
			main.instanciar();
			
			main.message("mi mensaje");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

